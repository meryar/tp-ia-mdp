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

	int dimension = 3000;

	//Point dot, gost;

	int [][] tab;


	public EtatPacmanMDPClassic(StateGamePacman _stategamepacman){
		/**
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
		}**/

		//TEST DE FENETRAGE
		int xPac = _stategamepacman.getPacmanState(0).getX();
		int yPac = _stategamepacman.getPacmanState(0).getY();
		tab = new int[3][3];

		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				if (_stategamepacman.getMaze().isWall(xPac + i, yPac + j)){
					tab[i+1][j+1] = 4;
				}
				if (_stategamepacman.getMaze().isFood(xPac + i, yPac + j)){
					tab[i+1][j+1] = 2;
				}
			}
		}

		for (int g = 0; g < _stategamepacman.getNumberOfGhosts(); g++){
			int xGhost = _stategamepacman.getGhostState(g).getX();
			int yGhost = _stategamepacman.getGhostState(g).getY();
			for (int i = -1; i <= 1; i++){
				for (int j = -1; j <= 1; j++){
					if (xGhost == xPac + i && yGhost == yPac + j){
						tab[i+1][j+1] = 3;
					}
				}
			}
		}

		tab[1][1] = 1;

		if (false){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					System.out.print(tab[i][j]);
				}
				System.out.print("\n");
			}
		}



		/*
		List<Point> dots2 = new ArrayList<>();
		for (int i = 1; i < _stategamepacman.getMaze().getSizeX() - 1; i++) {
			for (int j = 1; j < _stategamepacman.getMaze().getSizeY() - 1; j++) {
				if (_stategamepacman.getMaze().isFood(i,j)){
					dots2.add(new Point( i, j));
				}
			}
		}
		int closestDis = Integer.MAX_VALUE;
		if (!dots2.isEmpty()){
			for (Point i : dots2){
				int disX = i.x - _stategamepacman.getPacmanState(0).getX();
				int disY = i.y - _stategamepacman.getPacmanState(0).getY();
				if (disX + disY < closestDis){
					closestDis = disX + disY;
					dot = i;
				}
			}
		}
		dot = new Point(0,0);
		gost = new Point(_stategamepacman.getGhostState(0).getX() - _stategamepacman.getPacmanState(0).getX(),
				_stategamepacman.getGhostState(0).getY() - _stategamepacman.getPacmanState(0).getY());*/

	}

	@Override
	public int hashCode() {
		//int result = ghosts.hashCode();
		//result = result * 32 + pacmans.hashCode();
		//result = result * 32 + dots.hashCode();
		//return result;
		return tab.hashCode();

		//return dot.hashCode() + gost.hashCode() * 32;
	}
	
	@Override
	public String toString() {

		return "" ;
	}

	@Override
	public boolean equals(Object e){
		EtatPacmanMDPClassic e1 = (EtatPacmanMDPClassic) e;
		return e.hashCode() == this.hashCode();
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

	public int getDimensions(){
		return dimension;
	}


	

}
