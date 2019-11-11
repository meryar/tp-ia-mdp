**Nom/Prénom Etudiant 1 : Cabanis Pierre

**Nom/Prénom Etudiant 2 : Guédet Adrien

# Rapport TP1

## Question 5.1 Brigde Grid
*Donnez les valeurs des paramètres et la justification de ces choix*
En mettant le bruit très faible 0.01 ou moins.
Le risque de tomber dans les -100 est très faible et l'agent décide de traverser le pont.

## Question 5.2 Discount Grid
*Donnez les valeurs des paramètres dans cahque cas et la justification de ces choix*
1) En mettant les autre récompenses à -2, l'agent vas privilégier le trajet le plus cours possible et vas donc viser l'etat à récompense +1.	
2) En mettant le bruit à 0, l'agent n'aura plus aucun risque à prendre le chemin inférieur
3) En mettant gamma 0.2, l'agent considerera toujours le chemin par en-bas trop dangereux mais cherchera la récompense la plus proche et rejoindra donc le +1 par en-haut
4) En mettant les autres récompenses à 10 ou plus, l'agent aura plus interet à ne jamais aller dans un etat absorbant (même le +10) et vas chercher à continuer à se déplacer sur la grille infiniment

# Rapport TP2

## Question 1:
*Précisez et justifiez les éléments que vous avez utilisés pour la définition d’un état du MDP pour le jeu du Pacman (partie 2.2)*


## Question 2:
*Précisez et justifiez les fonctions caractéristiques que vous avez choisies pour la classe FeatureFunctionPacman (partie 2.3).*
