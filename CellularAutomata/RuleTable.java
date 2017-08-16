
import java.util.LinkedHashMap;
import java.util.Set;

/**
 *
 * @author Chris
 */
public final class RuleTable {

    /**
     *
     */
    private final LinkedHashMap<String, Integer> rules;

    /**
     *
     */
    private int neighbours;

    /**
     *
     * @param nb
     */
    public RuleTable(int nb) {
        neighbours = nb;
        rules = new LinkedHashMap<>();
        create_rules(nb, "none");
    }

    /**
     *
     * @param key
     * @return
     */
    public int lookup_rule(String key) {
        return (int) rules.get(key);
    }

    /**
     *
     * @param nb
     * @param res
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
     *
     * @return
     */
    public Set get_keys() {
        return rules.keySet();
    }

    /**
     *
     * @return
     */
    public LinkedHashMap get_rules() {
        return rules;
    }

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
