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


	int x_pacman, y_pacman, distClosestDot;
	boolean ghost_up, ghost_down, ghost_left, ghost_right;

	//List<StateAgentPacman> ghosts;

	public EtatPacmanMDPClassic(StateGamePacman _stategamepacman){

	x_pacman = _stategamepacman.getPacmanState(0).getX();
	y_pacman = _stategamepacman.getPacmanState(0).getY();

	StateAgentPacman ghost = _stategamepacman.getGhostState(0);
	ghost_left = ghost.getX() == x_pacman - 1 && ghost.getY() == y_pacman;
	ghost_right = ghost.getX() == x_pacman + 1 && ghost.getY() == y_pacman;
	ghost_up = ghost.getX() == x_pacman && ghost.getY() == y_pacman + 1;
	ghost_down = ghost.getX() == x_pacman && ghost.getY() == y_pacman - 1;

	distClosestDot =_stategamepacman.getClosestDot(_stategamepacman.getPacmanState(0));

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
		if (ghost_left){ result += 1000;}
		else if (ghost_right){ result += 1001;}
		else if (ghost_down){ result += 1002;}
		else if (ghost_up){ result += 1003;}
		result += distClosestDot;

		return result;
	}
	
	@Override
	public String toString() {
		String posGhost = " unknown";
		if (ghost_left){ posGhost = " left";}
		else if (ghost_right){ posGhost = " right";}
		else if (ghost_up){ posGhost = " up";}
		else if (ghost_down){ posGhost = " down";}

		return " pacman[" + x_pacman + "," + y_pacman + "] and pos ghost" + posGhost + " and closest dot at " + distClosestDot ;
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
