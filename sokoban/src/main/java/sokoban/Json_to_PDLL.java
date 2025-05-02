package sokoban;


import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Json_to_PDLL {

    public static void main(String[] args) {

        if(args.length < 2) {
            System.err.println("Usage: java Json_to_PDLL <jsonFilePath> <pddlFilePath>");
            return;
        }

        String jsonFilePath = args[0];
        String pddlFilePath = args[1];
        // Vérifier si le fichier JSON existe
        if (!Files.exists(Paths.get(jsonFilePath))) {
            System.err.println("JSON file does not exist: " + jsonFilePath);
            return;
        }


        try {
            // Lire le fichier JSON
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONObject jsonObject = new JSONObject(content);

            // Extraire les données du JSON
            String title = jsonObject.getJSONObject("title").optString("2", "Default Title");
            String testIn = jsonObject.optString("testIn", "");

            //recupere le nombre de ligne et de colonne
            int nbr_col = 0;
            while (testIn.charAt(nbr_col) != '\n') {
                nbr_col++;
            }
            int nbr_ligne = (testIn.length()+1) / (nbr_col + 1);  //len+1 car le json na pas de \n a la fin et +1 a colonne pour le \n
            System.err.println("nbr_ligne: " + nbr_ligne);
            System.err.println("nbr_col: " + nbr_col);
            System.err.println("testIn: " + testIn);
            // Générer le contenu PDDL
            StringBuilder pddlContent = new StringBuilder();
            pddlContent.append("(define (problem sokoban-");
                    pddlContent.append(title.replace(" ", "-").toLowerCase()); //ajoute le titre en remplasant les espace par des tirets
                    pddlContent.append(")\n");
                    pddlContent.append("  (:domain sokoban)\n");
                    pddlContent.append("  (:objects \n     ");
                    //definition de toute les cases
                    for(int i = 0; i < nbr_ligne; i++) {
                        for(int j = 0; j < nbr_col; j++) {
                            pddlContent.append("l").append(i).append("-").append(j).append(" ");    //l1-2 la case de ligne 1 et colonne 2
                        }
                    }
                    
                    pddlContent.append(" - lieu)\n");
                    pddlContent.append("  (:init \n");
                    int index = 0;
                    char currentChar;

                    for(int i = 0; i < nbr_ligne; i++) {
                        for(int j = 0; j < nbr_col; j++) {
                            //ajoute les connection entre les cases
                            currentChar = testIn.charAt(index);
                            index++;
                            if(currentChar == '\n') {
                                currentChar = testIn.charAt(index);
                                index++;
                            }
                            if(i!= 0){
                                pddlContent.append("        (connectU ").append("l").append(i).append("-").append(j).append(" l").append(i-1).append("-").append(j).append(")\n");
                            }
                            if(j!= 0){
                                pddlContent.append("        (connectL ").append("l").append(i).append("-").append(j).append(" l").append(i).append("-").append(j-1).append(")\n");
                            }
                            if(i!= nbr_ligne-1){
                                pddlContent.append("        (connectD ").append("l").append(i).append("-").append(j).append(" l").append(i+1).append("-").append(j).append(")\n");
                            }
                            if(j!= nbr_col-1){
                                pddlContent.append("        (connectR ").append("l").append(i).append("-").append(j).append(" l").append(i).append("-").append(j+1).append(")\n");
                            }
                            //ajoute le type des case
                            System.err.println("case: " + currentChar + " : ");
                            switch(currentChar) {
                                case ' ':
                                    System.err.println("case vide\n");
                                    pddlContent.append("        (clear ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '$':
                                    System.err.println("case avec un caissse\n");
                                    pddlContent.append("        (Con ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '.':
                                    pddlContent.append("        (isG ").append("l").append(i).append("-").append(j).append(")\n");
                                    pddlContent.append("        (clear ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '@':
                                    pddlContent.append("        (Pon ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '*':
                                    pddlContent.append("        (Con ").append("l").append(i).append("-").append(j).append(")\n");
                                    pddlContent.append("        (isG ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '+':
                                    pddlContent.append("        (isG ").append("l").append(i).append("-").append(j).append(")\n");
                                    pddlContent.append("        (Pon ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                default:
                                    break;
                            }
                            //System.err.println("sort\n");
                        }
                    }

                    pddlContent.append("  )\n");
                    pddlContent.append("  (:goal \n");
                    pddlContent.append("    (and \n");
                    //ajout de la configuration rechercher

                    index = 0;
                    for(int i = 0; i < nbr_ligne; i++) {
                        for(int j = 0; j < nbr_col; j++) {
                            //ajoute le type des case
                            currentChar = testIn.charAt(index);
                            index++;
                            if(currentChar == '\n') {
                                index++;
                                currentChar = testIn.charAt(index);
                            }
                            switch(currentChar) {
                                case '.':
                                    pddlContent.append("         (Con ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '*':
                                    pddlContent.append("         (Con ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                case '+':
                                    pddlContent.append("         (Con ").append("l").append(i).append("-").append(j).append(")\n");
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    pddlContent.append("    ))\n");
                    pddlContent.append("  )\n");


            // Écrire le contenu PDDL dans un fichier
            Files.write(Paths.get(pddlFilePath), pddlContent.toString().getBytes());
            System.out.println("PDDL file '" + pddlFilePath + "' generated successfully.");

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing JSON: " + e.getMessage());
        }
    }
}