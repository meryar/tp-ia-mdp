package agent.rlapproxagent;

import java.util.*;

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
	Map<Pair<Etat,Action>,Integer> repertoire;
	int last_attributed_line;

	public FeatureFunctionIdentity(int _nbEtat, int _nbAction){
		int nb_key = _nbAction * _nbEtat;
		tab = new double[nb_key][nb_key];
		for (int i = 0; i < nb_key; i++){
			for (int j = 0; j < nb_key; j++){
				if(i == j){
					tab[i][j] = 1;
				} else {
					tab[i][j] = 0;
				}
			}
		}

		last_attributed_line = -1;

		repertoire = new HashMap<>();
	}
	
	@Override
	public int getFeatureNb() {

		return tab.length;
	}

	@Override
	public double[] getFeatures(Etat e, Action a){
		//*** VOTRE CODE
		Pair<Etat,Action> key = new Pair<>(e,a);
		if (!repertoire.containsKey(key)) {

			repertoire.put(key,last_attributed_line + 1);
			last_attributed_line += 1;
		}
		return tab[repertoire.get(key)];
	}
	

}
