package agent.planningagent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import util.HashMapUtil;

import java.util.HashMap;

import environnement.Action;
import environnement.Etat;
import environnement.IllegalActionException;
import environnement.MDP;
import environnement.Action2D;


/**
 * Cet agent met a jour sa fonction de valeur avec value iteration 
 * et choisit ses actions selon la politique calculee.
 * @author laetitiamatignon
 *
 */
public class ValueIterationAgent extends PlanningValueAgent{
	/**
	 * discount facteur
	 */
	protected double gamma;

	/**
	 * fonction de valeur des etats
	 */
	protected HashMap<Etat,Double> V;
	
	/**
	 * 
	 * @param gamma
	 * @param mdp
	 */
	public ValueIterationAgent(double gamma,  MDP mdp) {
		super(mdp);
		this.gamma = gamma;
		
		this.V = new HashMap<Etat,Double>();
		for (Etat etat:this.mdp.getEtatsAccessibles()){
			V.put(etat, 0.0);
		}
	}
	
	
	
	
	public ValueIterationAgent(MDP mdp) {
		this(0.9,mdp);

	}
	
	/**
	 * 
	 * Mise a jour de V: effectue UNE iteration de value iteration (calcule V_k(s) en fonction de V_{k-1}(s'))
	 * et notifie ses observateurs.
	 * Ce n'est pas la version inplace (qui utilise la nouvelle valeur de V pour mettre a jour ...)
	 */
	@Override
	public void updateV(){
		//delta est utilise pour detecter la convergence de l'algorithme
		//Dans la classe mere, lorsque l'on planifie jusqu'a convergence, on arrete les iterations        
		//lorsque delta < epsilon 
		//Dans cette classe, il  faut juste mettre a jour delta 
		this.delta=0.0;
		//*** VOTRE CODE
		
		HashMap<Etat,Double> nextV = new HashMap<Etat,Double>();

		for (Etat etat_abs : this.V.keySet()) {
			if (this.mdp.estAbsorbant(etat_abs)){
				nextV.put(etat_abs,0.0);
			}
		}

		double new_val,reward,propagation,proba;
		for (Etat etat_source : this.mdp.getEtatsAccessibles()) {
				for (Action action : this.mdp.getActionsPossibles(etat_source)) {
					new_val = 0.0;
					try {
						for (Etat etat_cible : this.mdp.getEtatTransitionProba(etat_source, action).keySet()) {
							proba = this.mdp.getEtatTransitionProba(etat_source, action).get(etat_cible);
							reward = this.mdp.getRecompense(etat_source, action, etat_cible);
							propagation = this.V.get(etat_cible) * this.gamma;
							new_val += proba * (reward + propagation);

						}
					} catch (IllegalActionException e) {
						System.out.println("illegal action!");
					} catch (Exception e) {
						System.out.println("unknown exception");
					}
					if (!nextV.containsKey(etat_source)) {
						nextV.put(etat_source, new_val);
					} else {
						if (nextV.get(etat_source) < new_val) {
							nextV.put(etat_source, new_val);
						}
					}
				}

		}
		double old_value;
		double new_value;
		for (Etat etat : this.V.keySet()){
			assert(nextV.containsKey(etat));
			old_value = this.V.get(etat);
			new_value = nextV.get(etat);
			this.delta = Math.max(this.delta,
					Math.abs(old_value - new_value));
		}

		this.V = nextV;
		
		//mise a jour de vmax et vmin utilise pour affichage du gradient de couleur:
		//vmax est la valeur max de V pour tout s 
		//vmin est la valeur min de V pour tout s
		// ...
		
		//******************* laisser cette notification a la fin de la methode	
		this.notifyObs();
	}
	
	
	/**
	 * renvoi l'action executee par l'agent dans l'etat e 
	 * Si aucune actions possibles, renvoi Action2D.NONE
	 */
	@Override
	public Action getAction(Etat e) {
		//*** VOTRE CODE
		
		return Action2D.NONE;
		
	}


	@Override
	public double getValeur(Etat _e) {
                 //Renvoie la valeur de l'Etat _e, c'est juste un getter, ne calcule pas la valeur ici
                 //(la valeur est calculee dans updateV
		//*** VOTRE CODE
		
		return this.V.get(_e);
	}

	/**
	 * renvoi action(s) de plus forte(s) valeur(s) dans etat 
	 * (plusieurs actions sont renvoyees si valeurs identiques, liste vide si aucune action n'est possible)
	 */
	@Override
	public List<Action> getPolitique(Etat _e) {
		//*** VOTRE CODE
		
		// retourne action de meilleure valeur dans _e selon V, 
		// retourne liste vide si aucune action legale (etat absorbant)

		List<Action> returnactions = new ArrayList<Action>();

		HashMap<Action,Double> score_action = new HashMap<Action,Double>();

		double new_val,reward,propagation,proba;
		for (Action action : this.mdp.getActionsPossibles(_e)) {
			new_val = 0.0;
			try {
				for (Etat etat_cible : this.mdp.getEtatTransitionProba(_e, action).keySet()) {
					proba = this.mdp.getEtatTransitionProba(_e, action).get(etat_cible);
					reward = this.mdp.getRecompense(_e, action, etat_cible);
					propagation = this.V.get(etat_cible) * this.gamma;
					new_val += proba * (reward + propagation);
				}
			} catch (IllegalActionException e) {
				System.out.println("illegal action!");
			} catch (Exception e) {
				System.out.println("unknown exception");
			}
			score_action.put(action,new_val);
		}


		double best_score = Integer.MIN_VALUE;
		for (Action action: score_action.keySet()){
			if (score_action.get(action) > best_score){
				best_score = score_action.get(action);
				returnactions.clear();
				returnactions.add(action);
			} else if (score_action.get(action) == best_score) {
				returnactions.add(action);
			}

		}

		if (returnactions.isEmpty()){
			returnactions.add(Action2D.NONE);
		}

		return returnactions;
		
	}
	
	@Override
	public void reset() {
		super.reset();
                //reinitialise les valeurs de V 
		//*** VOTRE CODE

		this.V.clear();
		for (Etat etat:this.mdp.getEtatsAccessibles()){
			V.put(etat, 0.0);
		}

		this.notifyObs();
	}

	

	

	public HashMap<Etat,Double> getV() {
		return V;
	}
	public double getGamma() {
		return gamma;
	}
	@Override
	public void setGamma(double _g){
		System.out.println("gamma= "+gamma);
		this.gamma = _g;
	}


	
	

	
}