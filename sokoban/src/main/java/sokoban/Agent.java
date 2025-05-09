package sokoban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Agent {
    public static void main(String[] args) {
        String sol = "";
        try (BufferedReader buff = new BufferedReader(new FileReader("move.txt"))){
            sol = buff.readLine();
            if(sol == null){
                sol = "";
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        for (char c : sol.toCharArray()) System.out.println(c);
    }
}
