package agent.rlapproxagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import environnement.Action;
import environnement.Action2D;
import environnement.Etat;
import javafx.util.Pair;
/**
 * Vecteur de fonctions caracteristiques phi_i(s,a): autant de fonctions caracteristiques que de paire (s,a),
 * <li> pour chaque paire (s,a), un seul phi_i qui vaut 1  (vecteur avec un seul 1 et des 0 sinon).
 * <li> pas de biais ici 
 * 
 * @author laetitiamatignon
 *
 */
public class FeatureFunctionIdentity implements FeatureFunction {
	//*** VOTRE CODE
	double [][] tab;

	public FeatureFunctionIdentity(int _nbEtat, int _nbAction){
		tab = new double[_nbEtat][_nbAction];
		for (int i = 0; i < _nbEtat; i++){
			for (int j = 0; j < _nbAction; j++){
				if(i == j){
					tab[i][j] = 1;
				} else {
					tab[i][j] = 0;
				}
			}
		}
	}
	
	@Override
	public int getFeatureNb() {
		//*** VOTRE CODE
		return null;
	}

	@Override
	public double[] getFeatures(Etat e,Action a){
		//*** VOTRE CODE
		return e.;
	}
	

}
