
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chris
 */
public class MasterSimulation {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner read_in = new Scanner(System.in);
        System.out.println("Which simulation would you like to run; Cellular Automata or Genetic Algorithm? (ca/ga)");
        String choice = read_in.next();

        while (!choice.equals("ca") && !choice.equals("ga")) {
            System.out.println("Sorry, choice not recognised!\n");
            System.out.println("Which simulation would you like to run; Cellular Automata or Genetic Algorithm? (ca/ga)");
            choice = read_in.next();
        }

        if (choice.equals("ca")) {
            CASimulation sim_ca = new CASimulation();
            sim_ca.run_sim();
        } else {
            GASimulation sim_ga = new GASimulation();
            sim_ga.set_up();
        }

    }
}
