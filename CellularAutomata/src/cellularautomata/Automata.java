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
    int neighbours, a_size;
    RuleTable rules;

    public Automata(ArrayList<Integer> initial_states, int nb, RuleTable rl) {
        automata = new LinkedList<>();
        neighbours = nb;
        for (int num : initial_states) {
            automata.add(new Cell(num));
        }
        a_size = automata.size();
        rules = rl;
    }

    public String to_string() {
        String output = "";
        for (Cell c : automata) {
            output = output.concat(c.to_string());
        }
        System.out.println(output);
        return output;
    }

    public ArrayList get_local(int index) {
        ArrayList neighbourhood = new ArrayList<>();
        neighbourhood.addAll(get_l_local(index));
        neighbourhood.add(automata.get(index).to_string());
        neighbourhood.addAll(get_r_local(index));
        return neighbourhood;
    }

    public ArrayList get_l_local(int index) {
        ArrayList l_neighbours = new ArrayList<>();
        for (int i = neighbours; i > 0; i--) {
            if (index - i < 0) {
                l_neighbours.add(automata.get(index - i + a_size).to_string());
            } else {
                l_neighbours.add(automata.get(index - 1).to_string());
            }
        }

        return l_neighbours;
    }

    public ArrayList get_r_local(int index) {
        ArrayList r_neighbours = new ArrayList<>();
        for (int i = 1; i <= neighbours; i++) {
            if (index + i >= a_size) {
                r_neighbours.add(automata.get(index + i - a_size).to_string());
            } else {
                r_neighbours.add(automata.get(index + i).to_string());
            }
        }
        return r_neighbours;
    }

    public void do_update() {
        ArrayList new_automata = new ArrayList<>();
        for (Cell automata1 : automata) {
            ArrayList local = get_local(automata.indexOf(automata1));
            String local_key = String.join("", local);
            new_automata.add(new Cell(rules.lookup_rule(local_key)));
        }
    }

}
