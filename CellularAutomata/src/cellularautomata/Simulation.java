/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellularautomata;

import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class Simulation {

    static private RuleTable rule_table;
    private Automata automata;
    private int num_ticks, current_tick;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner read_in = new Scanner(System.in);

        System.out.println("Enter Neighbours:    ");
        int nb = read_in.nextInt();
        rule_table = new RuleTable(nb);

        System.out.println("Here is the (incomplete) rules table:\n");
        for (Object rule : rule_table.get_keys()) {
            System.out.println("| " + rule.toString() + " |");
        }

        System.out.println("\nPlease enter the desired outcomes, from top to bottom:    ");
        String rules_res = read_in.next();
        rule_table.create_rules(nb, rules_res);

        System.out.println("\nHere is the rules table for your simulation:");
        for (Object rule : rule_table.get_keys()) {
            System.out.println("| " + rule.toString() + " | " + rule_table.get_rules().get(rule).toString()+" |");
        }
       

    }

}
