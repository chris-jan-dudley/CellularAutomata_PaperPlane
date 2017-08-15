/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellularautomata;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Chris
 */
class Automata {
    
    LinkedList<Cell> automata;
    int neighbours;
    
    public Automata(ArrayList<Integer> initial_states){
        automata = new LinkedList<>();
        for (int num: initial_states){
            automata.add(new Cell(num));
        }
    }
    
    public String to_string() {
        String output = "";
        for (Cell c : automata){
            output = output.concat(c.to_string());
        }
        System.out.println(output);
        return output;
    }
    
    public ArrayList<Integer> get_neighbours() {
        
    }
    
    public void do_update() {
        
    }
    
}
