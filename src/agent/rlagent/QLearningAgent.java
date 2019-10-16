package agent.rlagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;
import environnement.Action;
import environnement.Environnement;
import environnement.Etat;
/**
 * Renvoi 0 pour valeurs initiales de Q
 * @author laetitiamatignon
 *
 */
public class QLearningAgent extends RLAgent {
	/**
	 *  format de memorisation des Q valeurs: utiliser partout setQValeur car cette methode notifie la vue
	 */
	protected HashMap<Etat,HashMap<Action,Double>> qvaleurs;
	
	//AU CHOIX: vous pouvez utiliser une Map avec des Pair pour clés si vous préférez
	//protected HashMap<Pair<Etat,Action>,Double> qvaleurs;

	
	/**
	 * 
	 * @param alpha
	 * @param gamma
	 * @param Environnement
	 */
	public QLearningAgent(double alpha, double gamma,
			Environnement _env) {
		super(alpha, gamma,_env);
		qvaleurs = new HashMap<Etat,HashMap<Action,Double>>();
		
		
	
	}


	
	
	/**
	 * renvoi action(s) de plus forte(s) valeur(s) dans l'etat e
	 *  (plusieurs actions sont renvoyees si valeurs identiques)
	 *  renvoi liste vide si aucunes actions possibles dans l'etat (par ex. etat absorbant)

	 */
	@Override
	public List<Action> getPolitique(Etat e) {
		// retourne action de meilleures valeurs dans e selon Q : utiliser getQValeur()
		// retourne liste vide si aucune action legale (etat terminal)
		List<Action> returnactions = new ArrayList<Action>();
		if (this.getActionsLegales(e).size() == 0){//etat  absorbant; impossible de le verifier via environnement
			System.out.println("aucune action legale");
			return new ArrayList<Action>();

		}

		double best_value = Integer.MIN_VALUE;
		for (Action action: this.qvaleurs.get(e).keySet()){
			if (this.getQValeur(e,action) > best_value){
				returnactions.clear();
				returnactions.add(action);
				best_value = this.getQValeur(e,action);
			} else if (this.getQValeur(e,action) == best_value){
				returnactions.add(action);
			}
		}


		//*** VOTRE CODE
		return returnactions;
		
		
	}
	
	@Override
	public double getValeur(Etat e) {
		//*** VOTRE CODE
		Double max = Double.valueOf(Integer.MIN_VALUE);

		if (!this.qvaleurs.containsKey(e)){
			return 0.0;
		}

		for (Action action : this.qvaleurs.get(e).keySet()){
			if (this.qvaleurs.get(e).get(action) > max){
				max = this.qvaleurs.get(e).get(action);
			}
		}
		return max;
	}

	@Override
	public double getQValeur(Etat e, Action a) {
		//*** VOTRE CODE
		if (!this.qvaleurs.containsKey(e)){
			return 0.0;
		} else if (!this.qvaleurs.get(e).containsKey(a)){
			return 0.0;
		}

		return this.qvaleurs.get(e).get(a);
	}
	
	
	
	@Override
	public void setQValeur(Etat e, Action a, double d) {
		//*** VOTRE CODE
		if (!this.qvaleurs.containsKey(e)){
			this.qvaleurs.put(e, new HashMap<>());
		}

		this.qvaleurs.get(e).put(a, d);

		// mise a jour vmax et vmin pour affichage du gradient de couleur:
				//vmax est la valeur de max pour tout s de V
				//vmin est la valeur de min pour tout s de V
				// ...

		this.vmax = Math.max(this.vmax, d);
		this.vmin = Math.min(this.vmin, d);

		this.notifyObs();
		
	}	
	
	
	/**
	 * mise a jour du couple etat-valeur (e,a) apres chaque interaction <etat e,action a, etatsuivant esuivant, recompense reward>
	 * la mise a jour s'effectue lorsque l'agent est notifie par l'environnement apres avoir realise une action.
	 * @param e
	 * @param a
	 * @param esuivant
	 * @param reward
	 */
	@Override
	public void endStep(Etat e, Action a, Etat esuivant, double reward) {
		if (RLAgent.DISPRL)
			System.out.println("QL mise a jour etat "+e+" action "+a+" etat' "+esuivant+ " r "+reward);

		//*** VOTRE CODE
	}

	@Override
	public Action getAction(Etat e) {
		this.actionChoisie = this.stratExplorationCourante.getAction(e);
		return this.actionChoisie;
	}

	@Override
	public void reset() {
		super.reset();
		//*** VOTRE CODE

		this.qvaleurs.clear();

		this.episodeNb =0;
		this.notifyObs();
	}









	


}
