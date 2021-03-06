package simulation;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;
import repast.simphony.util.SimUtilities;

public class Virus {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private boolean moved;
	private int infectionRate;
	public Human h;
	
	

	public Virus(ContinuousSpace<Object> space, Grid<Object> grid, int infectionRate) {
		this.space = space;
		this.grid = grid;
		this.infectionRate = infectionRate;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		// get the grid location of this virus
		GridPoint pt = grid.getLocation(this);

		// use the GridCellNgh class to create GridCells for
		// the surrounding neighborhood.
		GridCellNgh<Human> nghCreator = new GridCellNgh<Human>(grid, pt,
				Human.class, 1, 1);
		List<GridCell<Human>> gridCells = nghCreator.getNeighborhood(true);
		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());

		GridPoint pointWithMostHumans = null;
		int maxCount = -1;
		for (GridCell<Human> cell : gridCells) {
			if (cell.size() > maxCount) {
				pointWithMostHumans = cell.getPoint();
				maxCount = cell.size();
			}
		}
		moveTowards(pointWithMostHumans);
		infect();
		recover();
		reduceImunnity();
	}
	
	public void recover() {
		if(this.h != null && this.h.isInfected == true ) {
			if(this.h.immune <100)
				this.h.immune += (0.8 + this.h.moral);
			if(this.h.immune >= 100) {
				GridPoint pt = this.grid.getLocation(this);
				NdPoint spacePt = space.getLocation(this);
				Context<Object> context = ContextUtils.getContext(this);
				context.remove(this);
				Human human = this.h;
				human.isInfected = false;
				human.immune = human.orgImunne+25;
				context.add(human);
				space.moveTo(human, spacePt.getX(), spacePt.getY());
				grid.moveTo(human, pt.getX(), pt.getY());
			}
		}
	}
	
	public void reduceImunnity() {
		if(this.h != null && this.h.isInfected == true)
			this.h.immune -= 0.4;
	}
	
	public void moveTowards(GridPoint pt) {
		// only move if we are not already in this grid location
		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint,
					otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
			moved = true;
		}
	}

	public void infect() {
		GridPoint pt = grid.getLocation(this);
		List<Object> humans = new ArrayList<Object>();
		for (Object obj : grid.getObjectsAt(pt.getX(), pt.getY())) {
			if (obj instanceof Human) {
				humans.add(obj);
			}
		}

		if (humans.size() > 0) {
			int index = RandomHelper.nextIntFromTo(0, humans.size() - 1);
			Human obj = (Human) humans.get(index);
			NdPoint spacePt = space.getLocation(obj);
			Context<Object> context = ContextUtils.getContext(obj);
			if(this.infectionRate > (obj.immune) && obj.immune > 0)
			{
				
				Virus virus = new Virus(space, grid, this.infectionRate);
				obj.isInfected = true; 
				virus.h = obj;
				context.remove(obj);
				context.add(virus);
				space.moveTo(virus, spacePt.getX(), spacePt.getY());
				grid.moveTo(virus, pt.getX(), pt.getY());
				Network<Object> net = (Network<Object>)context.getProjection("infection network");
				net.addEdge(this, virus);
			}
		}
	}
}
