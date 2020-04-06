package simulation;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class VirusStyle extends DefaultStyleOGL2D {
	
	@Override
	public Color getColor(Object agent) {
		Virus v = (Virus) agent;
		if (v.h != null) {
			if(v.h.immune <= 0) {
				if(v.h.isInfected == true) {
					System.out.println("Going in green part");
					
				}else
					System.out.println("isinfected false");
			}
			else
				System.out.println("imunne is greater");
			return Color.green;
		}
		/*if (v.h != null && v.h.immune <= 0 && v.h.isInfected == true) {
			System.out.println("Going in green part");
			return Color.green;
		}*/
		else if( v.h != null && v.h.isInfected == true) {
			System.out.println("Going in cyan part");
			return Color.cyan;
		}
		else
			return Color.cyan;
	}

}
