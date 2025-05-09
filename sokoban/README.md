#TP3

Pour compiler et lancer :

-mvn clean  

-mvn install:install-file -Dfile=libs/pddl4j-4.0.0.jar -DgroupId=fr.uga -DartifactId=pddl4j -Dversion=4.0.0 -Dpackaging=jar -DgeneratePom=true  

-mvn package  

-./resolution.sh <nom du fichier de terrain en .json qui sont dans config/> <nom que l'on souhaite donner au fichier pddl>  



Pour le fonctionnement, on commence par fournir le fichier JSON au parseur Json_Pddl, qui va le transformer en un fichier PDDL. Ce fichier est ensuite traité par PDDL4J pour être résolu. Enfin, les mouvements sont extraits à l'aide du fichier extract.py, puis transmis au jeu Sokoban via le fichier Agent.java.