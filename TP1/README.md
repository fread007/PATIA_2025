# TP2

Pour lancer le TP1, vous pouvez utiliser le fichier Python generate_npuzzle.py pour générer les fichiers de test, 
puis solve_npuzzle.py pour résoudre ces problèmes. Les arguments nécessaires pour ces deux fichiers sont affichés lorsqu’ils sont exécutés sans arguments.

Pour le graphique de performance, il se trouve dans le notebook Jupyter "test.ipynb" qui est dans le dossier n-puzzle:  
-Le premier bloc de code correspond aux tests sur des puzzles de taille 4x4.  
-Le deuxième bloc est également pour des puzzles de taille 4x4.  
-Le troisieme bloc est un test avec des puzzle qui fon la taille 10x10 mais seulement sur bfs et astar pour pouvoir voir leur difference réels

Nous avons ajouté un timeout (80 seconde) pour éviter que la pile Python ne déborde lors de l’exécution avec Depth-First Search (DFS).
