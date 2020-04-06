package simulation;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class HumanStyle extends DefaultStyleOGL2D {
	
	@Override
	public Color getColor(Object agent) {
		Human human = (Human)agent;
		if (human.immune <= 0) {
			if(human.isInfected == false)
				return Color.black;
		}
        return Color.blue;
    }
	
}
