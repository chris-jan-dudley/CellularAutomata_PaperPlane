/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellularautomata;

import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class Simulation {

    private static RuleTable rule_table;
    private static Automata automata;
    private static int num_ticks, current_tick;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner read_in = new Scanner(System.in);

        System.out.println("Welcome to the Cellular Automaton simulation...");

        System.out.println("Please enter the initial state of your automaton, as a string of 1's and 0's (e.g. 101010):     ");
        String initial = read_in.next();

        System.out.println("How many neighbours would to like to consider in the rules?    ");
        int nb = read_in.nextInt();

        while (nb > (initial.length() - 1) / 2) {
            System.out.println("Using " + nb + " neighbours will wrap round and use some cells multiple times.");
            System.out.println("Do you wish to proceed? (y/n)");
            String choice = read_in.next();
            if (choice.equals("n")) {
                System.out.println("How many neighbours would to like to consider in the rules?    ");
                nb = read_in.nextInt();
            } else if (!choice.equals("y")){
                System.out.println("That wasn't a valid option");
            }
        }

        rule_table = new RuleTable(nb);

        System.out.println("Here is the (incomplete) rules table:\n");
        for (Object rule : rule_table.get_keys()) {
            System.out.println("| " + rule.toString() + " |");
        }

        System.out.println("\nPlease enter the desired outcomes, "+ rule_table.get_keys().size() +" in total,from top to bottom:    ");
        String rules_res = read_in.next();
        rule_table.create_rules(nb, rules_res);

        System.out.println("\nHere is the rules table for your simulation:");
        for (Object rule : rule_table.get_keys()) {
            System.out.println("| " + rule.toString() + " | " + rule_table.get_rules().get(rule).toString() + " |");
        }

        automata = new Automata(initial, nb, rule_table);

        System.out.println("\nHow many ticks would you like the simulation to run for?     ");
        int ticks = read_in.nextInt();
        set_num_ticks(ticks);

        System.out.println("\nReady to run your Cellular Automata? (y/n)     ");
        String chosen = read_in.next();
        while (!chosen.equals("y") && !chosen.equals("n")) {
            System.out.println("\nI'm sorry, I did not recognise that input. Please try again.");
            System.out.println("\nReady to run your Cellular Automata? (y/n)     ");
            chosen = read_in.next();
        }
        if (chosen.equals("y")) {
            System.out.println("Starting Cellular Automaton...\n");
            for (int i = 0; i < num_ticks; i++) {
                System.out.println(automata.get_print());
                run_tick();
            }
        }

    }

    public static void set_num_ticks(int nt) {
        num_ticks = nt;
        current_tick = 0;
    }

    public int get_num_ticks() {
        return num_ticks;
    }

    public int get_current_tick() {
        return current_tick;
    }

    public static void run_tick() {
        automata.do_update();
        current_tick++;
    }

}
