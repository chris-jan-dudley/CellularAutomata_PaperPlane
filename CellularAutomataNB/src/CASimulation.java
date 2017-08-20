
import java.util.Scanner;

/**
 * The simulation class runs the user input of the application. It is the main
 * class for the application. This class records the number of ticks, and
 * contains an instance of the RuleTable class, and the Automata class.
 *
 * @author Chris
 */
public final class CASimulation {

    /**
     * The CASimulation contains instances of RuleTable, and Automata. There is
     * a field for the total number of ticks desired, and the current tick is
     * recorded.
     */
    private RuleTable rule_table;
    private Automata automata;
    private int num_ticks, current_tick;

    /**
     * Constructor for CASimulation. Class fields are initialized using user input in the run_sim method.
     *
     */
    public CASimulation() {
    }

    /**
     * Method contains the user input prompts and assignments, and calls the
     * methods to set up the simulation and run it.
     */
    public void run_sim() {

        //Starting a Scanner to allow user input to be read
        Scanner read_in = new Scanner(System.in);

        System.out.println("Welcome to the Cellular Automaton simulation...");

        /**
         * Prompt to enter the initial state of the automaton. The size of the
         * automaton is inferred form this input, so an explicit size input is
         * not necessary. Validation occurs to ensure the initial state only
         * contains 1's and 0's.
         */
        System.out.println("\nPlease enter the initial state of your automaton, as a string of 1's and 0's (e.g. 101010):     ");
        String initial = read_in.next();

        while (!initial.matches("[01]+")) {
            System.out.println("Please use only 1's and 0's in the initial state!");
            System.out.println("\nPlease enter the initial state of your automaton, as a string of 1's and 0's (e.g. 101010):     ");
            initial = read_in.next();
        }

        /**
         * Prompt to enter the number of neighbours to be considered in the
         * rules of the automaton
         */
        System.out.println("\nHow many neighbours would to like to consider in the rules?    ");
        String nb_in = read_in.next();

        while (!nb_in.matches("[0-9]+")) {
            System.out.println("Please enter a number!");
            nb_in = read_in.next();
        }
        int nb = Integer.parseInt(nb_in);
        /**
         * This checks if the number of neighbours would cause the same cell to
         * be used multiple times. This can be bypassed, or corrected. Inputs
         * are validated as well.
         */
        while (nb > (initial.length() - 1) / 2) {
            System.out.println("Using " + nb + " neighbours will wrap round and use some cells multiple times.");
            System.out.println("Do you wish to proceed? (y/n)");
            String choice = read_in.next();
            if (choice.equals("n")) {
                System.out.println("\nHow many neighbours would to like to consider in the rules?    ");
                nb_in = read_in.next();
                nb = Integer.parseInt(nb_in);
            } else if (!choice.equals("y")) {
                System.out.println("That wasn't a valid option");
            }
        }

        /**
         * A new RuleTable is initialised, using the number of neighbours as
         * parameter.
         */
        rule_table = new RuleTable(nb);

        /**
         * The rules table is displayed to the user, with placeholder values
         * (9's), by calling the RuleTable to_print() method.
         */
        System.out.println("\nHere is the (incomplete) rules table:\n\n" + rule_table.to_print());

        /**
         * Prompt to enter the outcomes desired for the rules. This saves the
         * user from inputting a potentially very long string of 1's and 0's,
         * since only the corresponding rule outcomes are needed. The Validity
         * of the input is checked (too long, only 1's and 0's) then the
         * outcomes are set in the RuleTable.
         */
        int r_t_size = rule_table.get_rules().size();
        System.out.println("\nPlease enter the desired outcomes, " + r_t_size + " in total,from top to bottom (e.g. 01100110):    ");
        String rules_res = read_in.next();

        while (rules_res.length() != r_t_size || !rules_res.matches("[01]+")) {
            if (rules_res.length() != r_t_size) {
                System.out.println("\nYou entered " + rules_res.length() + " outcomes!");
            } else {
                System.out.println("\nThe outcomes must only contain 1's and 0's!");
            }
            System.out.println("Please enter the desired outcomes, " + rule_table.get_rules().size() + " in total,from top to bottom:    ");
            rules_res = read_in.next();
        }

        rule_table.create_rules(nb, rules_res);

        /**
         * The complete RuleTable is printed out for the user, using the
         * RuleTable's to_print method.
         */
        System.out.println("\nHere is the rules table for your simulation:\n\n" + rule_table.to_print());

        /**
         * A new Automata is built, now that the complete RuleTable has been
         * assembled. The initial configuration is passed, as well as the number
         * of neighbours to be considered.
         */
        automata = new Automata(initial, nb, rule_table);

        /**
         * Prompt to enter the number of ticks the simulation should run for.
         * This is then set, and the current tick is set to 0. Validation takes
         * place to ensure a number is entered.
         */
        System.out.println("\nHow many ticks would you like the simulation to run for?     ");
        String ticks_in = read_in.next();
        while (!ticks_in.matches("[0-9]+")) {
            System.out.println("Please enter a number!");
            ticks_in = read_in.next();
        }
        int ticks = Integer.parseInt(ticks_in);
        set_num_ticks(ticks);

        /**
         * The user is prompted to start the simulation. Validation checks are
         * put in place to permit only valid inputs "y" or "n".
         */
        System.out.println("\nReady to run your Cellular Automata? (y/n)     ");
        String chosen = read_in.next();
        while (!chosen.equals("y") && !chosen.equals("n")) {
            System.out.println("\nI'm sorry, I did not recognise that input. Please try again.");
            System.out.println("\nReady to run your Cellular Automata? (y/n)     ");
            chosen = read_in.next();
        }
        if (chosen.equals("y")) {
            System.out.println("\nStarting Cellular Automaton...\n");
            for (int i = 0; i <= num_ticks; i++) {
                System.out.println("\n---- Tick " + current_tick + " ----");
                System.out.println(automata.to_print());
                run_tick();
            }
        }
        System.out.println("\nYour Cellular Automaton has terminated. Thank you for using this simulation!");

    }

    /**
     * Sets the number of ticks over which the simulation will run_sim, and sets
     * the current tick counter to 0.
     *
     * @param nt Number of ticks to be run_sim
     */
    public void set_num_ticks(int nt) {
        num_ticks = nt;
        current_tick = 0;
    }

    /**
     * Runs a tick of the simulation. This increments the current tick, and
     * calls on the do_update() method of the Automata.
     */
    public void run_tick() {
        automata.do_update();
        current_tick++;
    }

}
