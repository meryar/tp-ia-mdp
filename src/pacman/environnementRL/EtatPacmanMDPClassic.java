package pacman.environnementRL;

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


	int x_pacman, y_pacman, x_ghost, y_ghost;
	//List<StateAgentPacman> ghosts;

	public EtatPacmanMDPClassic(StateGamePacman _stategamepacman){

	x_pacman = _stategamepacman.getPacmanState(0).getX();
	y_pacman = _stategamepacman.getPacmanState(0).getY();
	x_ghost = _stategamepacman.getGhostState(0).getX();
	y_ghost = _stategamepacman.getGhostState(0).getY();

	/*ghosts = new ArrayList<>();
	for (int i = 0; i<_stategamepacman.getNumberOfGhosts(); i++) {
		ghosts.add(_stategamepacman.getGhostState(i));
	}*/

	}

	@Override
	public int hashCode() {
		int result = x_pacman * y_pacman;
		/*for (int i = 0; i<ghosts.size(); i++) {
			result += ghosts.get(i).getX()*ghosts.get(i).getY();
		}*/
		result += x_ghost * y_ghost;

		return result;
	}
	
	@Override
	public String toString() {
		
		return "";
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
