
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * This class holds the rule table used to update the automaton.
 *
 * @author Chris
 */
public final class RuleTable {

    /**
     * The rules are held in the rules LinkedHashMap. A LinkedHashMap allows
     * key/value pairs to be stored, but also iterated over consistently. The
     * neighbours field holds the number of neighbours to be considered in the
     * rules.
     */
    private final LinkedHashMap<String, Integer> rules;
    private final int neighbours;

    /**
     * Constructor for the RuleTable. A new LinkedHashMap is initialised, and
     * the create_rules method is called, with the "none" parameter, to indicate
     * that the rules have not yet been passed.
     *
     * @param nb Number of neighbours used in the rules.
     */
    public RuleTable(int nb) {
        neighbours = nb;
        rules = new LinkedHashMap<>();
        create_rules(nb, "none");
    }

    /**
     * Method to lookup the new value for a cell based on it and its neighbours,
     * passed as the key.
     *
     * @param key The key to be looked up in the HashMap.
     * @return The new cell value as looked up in the rule table.
     */
    public int lookup_rule(String key) {
        return (int) rules.get(key);
    }

    /**
     * This method creates the rules table by adding a key for each combination
     * of 1's and 0's for the given number of neighbours. Binary strings are
     * used to populate the table, so that each combination is represented. For
     * 1 neighbour either side of the cell, 3 numbers are considered (Left,
     * cell, Right), so 2^3 (8) keys are generated, from 000 to 111. If the
     * value "none" is passed as the res, placeholder values of 9 are set as the
     * values until a valid set of rules are passed by the user.
     *
     * @param nb The number of neighbours to be considered.
     * @param res The rule values to be set.
     */
    public void create_rules(int nb, String res) {
        int pwr = (nb * 2) + 1;
        for (int i = 0; i < Math.pow(2, pwr); i++) {
            String local = Integer.toBinaryString(i);
            while (local.length() < pwr) {
                local = "0" + local;
            }
            if ("none".equals(res)) {
                rules.put(local, 9);
            } else {
                rules.put(local, Character.getNumericValue(res.charAt(i)));
            }
        }
    }

    /**
     * Returns the LinkedHashMap representing the rules table
     *
     * @return rules, the rules table.
     */
    public LinkedHashMap get_rules() {
        return rules;
    }

    /**
     * This method generates and returns a "prettified" String representation of
     * the rule table, with borders and headings, suitable for printing.
     *
     * @return print_out, String representation of the rule table.
     */
    public String to_print() {
        String print_out = "";
        for (int i = 0; i < neighbours; i++) {
            print_out = print_out.concat(" | L");
        }
        print_out = print_out.concat(" | i");
        for (int i = 0; i < neighbours; i++) {
            print_out = print_out.concat(" | R");
        }
        print_out = print_out.concat(" || rule\n");
        for (String rule : rules.keySet()) {
            for (String num : rule.split("")) {
                print_out = print_out.concat(" | " + num);
            }
            print_out = print_out.concat(" ||  " + rules.get(rule) + "\n");
        }
        return print_out;
    }

}
