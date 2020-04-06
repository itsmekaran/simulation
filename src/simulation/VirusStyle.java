package simulation;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class VirusStyle extends DefaultStyleOGL2D {
	
	@Override
	public Color getColor(Object agent) {
		Virus v = (Virus) agent;
		if (v.h.immune <= 0 && v.h.isInfected == true)
			return Color.green;
		else
			return Color.red;
	}

}
