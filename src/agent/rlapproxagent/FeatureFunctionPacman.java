package agent.rlapproxagent;

import pacman.elements.ActionPacman;
import pacman.elements.StateAgentPacman;
import pacman.elements.StateGamePacman;
import pacman.environnementRL.EnvironnementPacmanMDPClassic;
import environnement.Action;
import environnement.Etat;

import java.awt.*;


/**
 * Vecteur de fonctions caracteristiques pour jeu de pacman: 4 fonctions phi_i(s,a)
 *  
 * @author laetitiamatignon
 *
 */
public class FeatureFunctionPacman implements FeatureFunction{
	private double[] vfeatures ;
	
	private static int NBACTIONS = 4;//5 avec NONE possible pour pacman, 4 sinon 
	//--> doit etre coherent avec EnvironnementPacmanRL::getActionsPossibles


	public FeatureFunctionPacman() {

	}

	@Override
	public int getFeatureNb() {
		return 4;
	}

	@Override
	public double[] getFeatures(Etat e, Action a) {
		vfeatures = new double[4];
		StateGamePacman stategamepacman ;
		//EnvironnementPacmanMDPClassic envipacmanmdp = (EnvironnementPacmanMDPClassic) e;

		//calcule pacman resulting position a partir de Etat e
		if (e instanceof StateGamePacman){
			stategamepacman = (StateGamePacman)e;
		}
		else{
			System.out.println("erreur dans FeatureFunctionPacman::getFeatures n'est pas un StateGamePacman");
			return vfeatures;
		}
	
		StateAgentPacman pacmanstate_next= stategamepacman.movePacmanSimu(0, new ActionPacman(a.ordinal()));
		 
		//*** VOTRE CODE

		// feature function 1: biais
		vfeatures[0] = 1.;

		// feature function 2: nb ghosts menaçants
		vfeatures[1] = 0;
		Point pacman = new Point(pacmanstate_next.getX(), pacmanstate_next.getY());
		for (int i = 0; i < stategamepacman.getNumberOfGhosts(); i++){
			Point ghost = new Point(stategamepacman.getGhostState(i).getX(),stategamepacman.getGhostState(i).getY());
			Point diff = new Point((int)(ghost.getX() - pacman.getX()), (int)(ghost.getY() - pacman.getY()));
			if (diff.getX() <= 1 && diff.getX() >= -1 && diff.getY() <= 1 && diff.getY() >= -1){
				if (Math.abs( diff.getX() + diff.getY() ) == 1){
					vfeatures[1] += 1;
				}
			}
		}
		if (vfeatures[1] == 0) vfeatures[1] = -1;


		// feature function 3: presence de food à la prochaine position
		vfeatures[2] = stategamepacman.getMaze().isFood(pacmanstate_next.getX(),pacmanstate_next.getY())? 0 : -1;

		// feature function 4: distance au dot le plus proche
		vfeatures[3] = (double)stategamepacman.getClosestDot(pacmanstate_next) /
				((double)stategamepacman.getMaze().getSizeX() + (double)stategamepacman.getMaze().getSizeY());

		//for (int i = 0; i<4; i++) System.out.println(vfeatures[i]);
		//System.out.println("-----------");

		return vfeatures;
	}

	public void reset() {
		vfeatures = new double[4];
		
	}

}
