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

    private RuleTable rule_table;
    private Automata automata;
    private int num_ticks, current_tick;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner read_in = new Scanner(System.in);
        System.out.println("Enter Neighbours:    ");
        int nb = read_in.nextInt();
        
        System.out.println("Here is the (incomplete) rules table:\n");

        
        
        System.out.println("Please enter the desired outcomes, from top to bottom:    ");
        int pwr = (nb*2) + 1;
        for (int i = 0; i < Math.pow(2, pwr); i++) {
            String local = Integer.toBinaryString(i);
            while (local.length() < pwr) {
                local = "0" + local;
            }
            System.out.println(local);
        }
    }

}
