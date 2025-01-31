**Nom/Prénom Etudiant 1 : Cabanis Pierre

**Nom/Prénom Etudiant 2 : Guédet Adrien

# Rapport TP1

## Question 5.1 Brigde Grid
*Donnez les valeurs des paramètres et la justification de ces choix*

En mettant le bruit très faible (0.01 ou moins), le risque de tomber dans les -100 est très faible et l'agent décide de traverser le pont.

## Question 5.2 Discount Grid
*Donnez les valeurs des paramètres dans chaque cas et la justification de ces choix*

1) En mettant les autre récompenses à -2, l'agent vas privilégier le trajet le plus cours possible et vas donc viser l'etat à récompense +1. ![img1](img/1_reckless.PNG)
2) En mettant le bruit à 0, l'agent n'aura plus aucun risque à prendre le chemin inférieur. ![img2](img/10_reckless.PNG)
3) En mettant gamma à 0.2, l'agent considerera toujours le chemin par en-bas trop dangereux mais cherchera la récompense la plus proche et rejoindra donc le +1 par en-haut. ![img3](img/1_safe.PNG)
4) En mettant les autres récompenses à 10 ou plus, l'agent aura plus interêt à ne jamais aller dans un etat absorbant (même le +10) et vas chercher à continuer à se déplacer sur la grille infiniment. ![img4](img/traveller.PNG)

# Rapport TP2

## Question 1:
*Précisez et justifiez les éléments que vous avez utilisés pour la définition d’un état du MDP pour le jeu du Pacman (partie 2.2)*

Pour représenter l'état, nous avons utilisé un fenêtrage de taille 2. De cette façon, le pacman serat toujours capable de voir les fantômes avant qu'ils l'atteignent tout en étant capable de faire des parallèles entre des situations similaires ayant lieu dans des endroits différents du labyrinthe.
Pour l'implémentation, nous avons utilisé un tableau d'entiers de taille 5x5, avec dedans des symboles correspondant au type d'entité sur la case:
	- 0 pour une case vide
	- 1 pour le pacman
	- 2 pour la nouriture
	- 3 pour les fantômes
	- 4 pour les murs
nous obtenons donc des tableau ressemblant à:
```
0 4 0 4 0 
3 4 0 4 0
0 4 1 4 0
0 4 2 4 0
0 2 2 2 2
```
résultats:
Avec 2 séries de 20 000 runs d'apprentissage et 300 runs d'exploitation de politique (gamma = 0.8, alpha = 0.1, epsilon = 0.15), nous avons obtenu jusqu'à 62% de taux de réussite.


## Question 2:
*Précisez et justifiez les fonctions caractéristiques que vous avez choisies pour la classe FeatureFunctionPacman (partie 2.3).*

Nous n'avons malheureusement eu le temps d'implémenter que les fonctions proposées dans le sujet, à savoir:
- le biais (1)
- le nombre de fantomes menaçants (à une case de distance de la position d'arrivée (-1 si aucuns)
- la presence ou non de nouriture sur la prochaine position (0 si oui, -1 sinon -> ces valeurs donnent de meilleurs resultats.)
- la distance à la nouriture la plus proche (normalisée par rapport à la somme des largeur et hauteur du labyrinthe.

resultats: 
Avec ces fonctions, nous obtenons des taux de succès autour de 25% dans des conditions similaires à celles de la question précédente

