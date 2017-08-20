
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Genetic Algorithm simulation runs a custom number of genotypes, trials
 * per generation, and number of generations to try and evolve the best genotype
 * to solve the density problem.
 *
 * @author Chris
 */
public final class GASimulation {

    /**
     * Genotypes Map used to store the RuleTable, genotype, and the number of
     * incorrect cells its output has each trial. The number of trials,
     * genotypes, and generations is stored. Class also contains a Random
     * generator used to create random genotypes and initial automata.
     */
    public static Map<RuleTable, Double> genotypes;
    public int trials,
            num_genotypes,
            generations;
    public Random rangen;

    /**
     * Constructor for the GASimulation class.
     */
    public GASimulation() {

    }

    /**
     * Sets up the pool of RuleTables (genotypes) and adds them to the genotypes
     * HashMap with an initial value (error) of 0.0. Fields are initialised, and
     * user inputs set for the number of genotypes, the number of trials and the
     * number of generations.
     */
    public void set_up_simulation() {
        rangen = new Random();
        genotypes = new ConcurrentHashMap<>();

        Scanner read_in = new Scanner(System.in);
        System.out.println("\nWelcome to the Genetic Algorithm Simulation...");

        /**
         * The user is prompted to enter the number of genotypes they would like
         * to try. Validation ensures numbers are entered.
         */
        String choice = "null";
        while (!choice.matches("[0-9]+")) {
            System.out.println("\nPlease enter the number of genotypes you'd like to simulate: ");
            choice = read_in.next();
        }
        num_genotypes = Integer.valueOf(choice);

        /**
         * The user is prompted to enter the number of trials they would like to
         * run for each generation. Validation ensures numbers are entered.
         */
        choice = "null";
        while (!choice.matches("[0-9]+")) {
            System.out.println("\nPlease enter the number of trials per generation you'd like to simulate: ");
            choice = read_in.next();
        }
        trials = Integer.valueOf(choice);

        /**
         * The user is prompted to enter the number of generations they would
         * like to run the simulation for. Validation ensures numbers are
         * entered.
         */
        choice = "null";
        while (!choice.matches("[0-9]+")) {
            System.out.println("\nPlease enter the number of generations you'd like to simulate: ");
            choice = read_in.next();
        }
        generations = Integer.valueOf(choice);
        for (int i = 0; i < num_genotypes; i++) {
            String genotype = gen_binary_string(8);
            genotypes.put(new RuleTable(1, genotype), 0.0);
        }

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
            System.out.println("\nStarting Genetic Algorithm...\n");
            run_simulation();
        }
        System.out.println("\nYour Genetic Algorithm has terminated. Thank you for using this simulation!");

    }

    /**
     * This method handles the running of each of the simulation's generations,
     * and is the class called by the MainSimulation class.
     */
    public void run_simulation() {
        for (int i = 0; i < generations; i++) {
            System.out.println("\n+++++ GENERATION " + (i + 1) + " +++++");
            run_generation();
        }
    }

    /**
     * Method to run generations of the simulation. It calls the run_trial()
 method to run each cellular automata a number of times equal to the
 number of trials set. After this, the mean number of errors is
 calculated, and placed as the values of the genotypes HashMap by calling
 the set_mean_error() method. The genotypes are then ranked, and the
 weakest is replaced using the best two.
     */
    public void run_generation() {
        for (int i = 0; i < trials; i++) {
            run_trial();
        }
        set_mean_error();
        List<Entry<RuleTable, Double>> ranked_genotypes = rank_genotypes();
        print_best_worst(ranked_genotypes);
        replace_weakest(ranked_genotypes);
        reset_values();
    }

    /**
     * Method to randomly initialise and run cellular automata for each
     * genotype, counting the errors of the output of the automata and the
     * expected values.
     */
    public void run_trial() {

        for (RuleTable genotype : genotypes.keySet()) {
            String initial = gen_binary_string(10);
            Automata automaton = new Automata(initial, 1, genotype);

            String expected_final = get_expected_final(automaton);
            Automata target_automaton = new Automata(expected_final, 1, genotype);

            for (int j = 0;
                    j < 100; j++) {
                automaton.do_update();
            }
            Double error = count_errors(automaton.to_string(), target_automaton.to_string());

            Double old_error = genotypes.get(genotype);

            genotypes.replace(genotype, old_error
                    + error);
        }
    }

    /**
     * Method to reset_values the values of the genotypes to 0 for the next generation.
     */
    public void reset_values() {

        for (RuleTable genotype : genotypes.keySet()) {
            genotypes.put(genotype, 0.0);
        }
    }

    /**
     * This method replaces the values of the genotypes HashMap with the mean
     * errors per run, since they have until now held the sum of all errors.
     */
    public void set_mean_error() {

        for (RuleTable genotype : genotypes.keySet()) {
            genotypes.put(genotype, genotypes.get(genotype) / trials);
        }
    }

    /**
     * Method to print out information of the best and worst genotypes.
     *
     * @param ranked_genotypes List of the ranked genotypes from which to access
     * the best and worst.
     */
    public void print_best_worst(List<Entry<RuleTable, Double>> ranked_genotypes) {

        System.out.println("-----\nBEST GENOTYPE: " + ranked_genotypes.get(0).getKey().get_outcomes());
        System.out.println("MEAN ERRORS: " + ranked_genotypes.get(0).getValue());

        System.out.println("-----\nWORST GENOTYPE: " + ranked_genotypes.get(num_genotypes - 1).getKey().get_outcomes());
        System.out.println("MEAN ERRORS: " + ranked_genotypes.get(num_genotypes - 1).getValue());

    }

    /**
     * Method to generate a random binary string of length len. Used to generate
     * random genotypes, and random automata initial states.
     *
     * @param len The length of the random string to be generated.
     * @return A string of length len containing 1's and 0's.
     */
    public String gen_binary_string(int len) {
        String genotype = "";
        rangen = new Random();
        for (int i = 0; i < len; i++) {
            if (rangen.nextBoolean()) {
                genotype += "1";
            } else {
                genotype += "0";
            }
        }
        return genotype;
    }

    /**
     * Method to calculate the expected outcome of an automata. In this problem,
     * an automata with >5 1's should have a final state of only 1's, and 0's
     * otherwise.
     *
     * @param automaton The automaton from which the target outcome can be
     * determined.
     * @return expected, either "1111111111" or "0000000000" depending on the
     * initial automaton.
     */
    public String get_expected_final(Automata automaton) {
        String initial = automaton.to_string();
        String expected;
        if (initial.length() - initial.replace("0", "").length() <= 5) {
            expected = "1111111111";
        } else {
            expected = "0000000000";
        }
        return expected;
    }

    /**
     * Method to compare the expected automata outputs with the actual outputs,
     * and counts how many discrepancies there are.
     *
     * @param actual String representation of the output of an automaton.
     * @param wanted Target String representation of the target automaton
     * output.
     * @return errors the number of discrepancies between the two Strings.
     */
    public double count_errors(String actual, String wanted) {
        double errors = 0.0;
        for (int i = 0; i < actual.length(); i++) {
            if (actual.charAt(i) != wanted.charAt(i)) {
                errors++;
            }
        }
        return errors;
    }

    /**
     * Creates a list with the genotypes sorted by their values.
     *
     * @return a List of Entries sorted by the value (mean error).
     */
    public List<Entry<RuleTable, Double>> rank_genotypes() {
        List<Entry<RuleTable, Double>> sorted = new ArrayList<>(genotypes.entrySet());
        Collections.sort(sorted, (Entry<RuleTable, Double> e1, Entry<RuleTable, Double> e2) -> e1.getValue().compareTo(e2.getValue()));
        return sorted;
    }

    /**
     * Method to pick the best, second best and worst genotypes, ranked by mean
     * error, and replace the worst with the offspring of the two best.
     *
     * @param sorted_genotypes The list of sorted genotypes based on mean error.
     */
    public void replace_weakest(List<Entry<RuleTable, Double>> sorted_genotypes) {
        Entry<RuleTable, Double> first = sorted_genotypes.get(0);
        Entry<RuleTable, Double> second = sorted_genotypes.get(1);
        int min_index = (int) 0.5 * num_genotypes;
        int max_index = num_genotypes - 1;

        int worst_index = rangen.nextInt(min_index + 1 - min_index) + min_index;
        Entry<RuleTable, Double> worst = sorted_genotypes.get(worst_index);

        genotypes.remove(worst.getKey());
        String child_genotype = get_offspring(first, second);
        System.out.println("-----\nNEW GENOTYPE: " + child_genotype + " REPLACED: " + worst.getKey().get_outcomes());
        genotypes.put(new RuleTable(1, child_genotype), 0.0);

    }

    /**
     * This method generates an offspring genotype based on the two passed
     * parents. Each gene will be randomly taken from one or the other parent.
     * The ratio of errors made by each parent is used to give the probability
     * of a gene coming from either parent. e.g. if both parents had 2 errors,
     * there would be a 50:50 chance of getting the gene from one parent or the
     * other.
     *
     *
     * @param father One of two best genotypes
     * @param mother One of two best genotypes
     * @return a genotype which is generated using the parent genotypes.
     */
    public String get_offspring(Entry<RuleTable, Double> father, Entry<RuleTable, Double> mother) {

        Double chance_for_father = father.getValue() / (father.getValue() + mother.getValue());
        String child = "";
        for (int gene = 0; gene < 8; gene++) {
            if (rangen.nextDouble() < chance_for_father) {
                child += father.getKey().get_outcomes().charAt(gene);
            } else {
                child += mother.getKey().get_outcomes().charAt(gene);
            }
        }
        return child;
    }

}
