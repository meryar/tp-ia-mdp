package agent.rlapproxagent;


import java.util.ArrayList;
import java.util.List;

import agent.rlagent.QLearningAgent;
import agent.rlagent.RLAgent;
import environnement.Action;
import environnement.Environnement;
import environnement.Etat;
/**
 * Agent qui apprend avec QLearning en utilisant approximation de la Q-valeur : 
 * approximation lineaire de fonctions caracteristiques 
 * 
 * @author laetitiamatignon
 *
 */
public class QLApproxAgent extends QLearningAgent{

	FeatureFunction featureFunction;
	double[] theta;
	
	public QLApproxAgent(double alpha, double gamma, Environnement _env,FeatureFunction _featurefunction) {
		super(alpha, gamma, _env);
		//*** VOTRE CODE
		featureFunction = _featurefunction;

		theta = new double[_featurefunction.getFeatureNb()];
		for (int i = 0; i < theta.length; i++){
			theta[i] = 1;
		}
		
	}

	
	@Override
	public double getQValeur(Etat e, Action a) {
		//*** VOTRE CODE
		double[] features = featureFunction.getFeatures(e, a);
		double res = 0;
		for(int i = 0; i< featureFunction.getFeatureNb(); i++){
			res += theta[i] * features[i];
		}

		return res;

	}
	
	
	
	
	@Override
	public void endStep(Etat e, Action a, Etat esuivant, double reward) {
		if (RLAgent.DISPRL){
			System.out.println("QL: mise a jour poids pour etat \n"+e+" action "+a+" etat' \n"+esuivant+ " r "+reward);
		}
       //inutile de verifier si e etat absorbant car dans runEpisode et threadepisode 
		//arrete episode lq etat courant absorbant


		//*** VOTRE CODE
		double Qvaleur = this.getQValeur(e,a);
		double[] features = featureFunction.getFeatures(e, a);
		double bestQValeur = Integer.MIN_VALUE;

		for(Action action: this.getActionsLegales(esuivant)){
			if (this.getQValeur(esuivant,action) > bestQValeur){
				bestQValeur = this.getQValeur(esuivant,action);
			}
		}

		for(int i = 0; i< featureFunction.getFeatureNb(); i++){
			theta[i] += this.alpha * features[i] * (reward + this.gamma * bestQValeur - Qvaleur);
		}
		
		
	}
	
	@Override
	public void reset() {
		super.reset();
		this.qvaleurs.clear();
	
		//*** VOTRE CODE

		for (int i = 0; i < theta.length; i++){
			theta[i] = 1;
		}
		
		this.episodeNb =0;
		this.notifyObs();
	}
	
	
}
