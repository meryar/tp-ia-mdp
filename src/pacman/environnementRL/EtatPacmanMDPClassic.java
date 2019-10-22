package pacman.environnementRL;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pacman.elements.MazePacman;
import pacman.elements.StateAgentPacman;
import pacman.elements.StateGamePacman;
import environnement.Etat;
/**
 * Classe pour définir un etat du MDP pour l'environnement pacman avec QLearning tabulaire

 */
public class EtatPacmanMDPClassic implements Etat , Cloneable{


	List<Point> ghosts;
	List<Point> pacmans;
	List<Point> dots;

	public EtatPacmanMDPClassic(StateGamePacman _stategamepacman){

	pacmans = new ArrayList<>();
	ghosts = new ArrayList<>();
	dots = new ArrayList<>();

	for (int i = 0; i < _stategamepacman.getNumberOfPacmans(); i++) {
		pacmans.add(new Point(_stategamepacman.getPacmanState(i).getX(), _stategamepacman.getPacmanState(i).getY()));
	}

	for (int i = 0; i < _stategamepacman.getNumberOfGhosts(); i++) {
		ghosts.add(new Point(_stategamepacman.getGhostState(i).getX(), _stategamepacman.getGhostState(i).getY()));
	}

	for (int i = 1; i < _stategamepacman.getMaze().getSizeX() - 1; i++) {
		for (int j = 1; j < _stategamepacman.getMaze().getSizeY() - 1; j++) {
			if (_stategamepacman.getMaze().isFood(i,j)){
				dots.add(new Point( i, j));
			}
		}
	}

	}

	@Override
	public int hashCode() {
		int result = 0;

		for( Point point: pacmans){
			result += point.getX() * point.getY();
		}
		for( Point point: ghosts){
			result += point.getX() * point.getY();
		}
		for( Point point: dots){
			result += point.getX() * point.getY();
		}

		return result;
	}
	
	@Override
	public String toString() {

		return "" ;
	}
	
	
	public Object clone() {
		EtatPacmanMDPClassic clone = null;
		try {
			// On recupere l'instance a renvoyer par l'appel de la 
			// methode super.clone()
			clone = (EtatPacmanMDPClassic)super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implementons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		


		// on renvoie le clone
		return clone;
	}



	

}
