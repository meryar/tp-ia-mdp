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


//	List<Point> ghosts;
//	List<Point> pacmans;
//	List<Point> dots;

	int dimension;

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
		}

		 **/

		//TEST DE FENETRAGE
		int xPac = _stategamepacman.getPacmanState(0).getX();
		int yPac = _stategamepacman.getPacmanState(0).getY();
		int window = 5;
		int windowR = 2;
		tab = new int[window][window];

		for (int i = 0; i < window; i++) {
			for (int j = 0; j < window; j++) {
				int x = xPac - windowR + i;
				int y = yPac - windowR + j;
				for (int u = 1; u < _stategamepacman.getMaze().getSizeX() - 1; u++) {
					for (int v = 1; v < _stategamepacman.getMaze().getSizeY() - 1; v++) {
						if (u == x && v == y && _stategamepacman.getMaze().isFood(u, v)) {
							tab[i][j] = 2;
						}
						if (u == x && v == y && _stategamepacman.getMaze().isWall(u,v)) {
							tab[i][j] = 4;
						}
					}
				}
				for (int u = 0; u < _stategamepacman.getNumberOfGhosts() - 1; u++) {
					int xGost = _stategamepacman.getGhostState(u).getX();
					int yGost = _stategamepacman.getGhostState(u).getY();
					if (xGost == x && yGost == y) {
						tab[i][j] = 3;
					}
				}
			}
		}
		tab[windowR][windowR] = 1;

		for (int i = 0; i < 5; i++){
			String msg = "";
			for (int j = 0; j < 5; j++){
				msg += tab[i][j] + " ";
			}
			//System.out.println(msg);
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

		dimension = 3000;

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
