/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellularautomata;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Chris
 */
final class RuleTable {

    private final LinkedHashMap<String, Integer> rules;
    private int neighbours;

    public RuleTable(int nb) {
        neighbours = nb;
        rules = new LinkedHashMap<>();
        create_rules(nb, "none");
    }

    public int lookup_rule(String key) {
        return (int) rules.get(key);
    }

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

    public Set get_keys() {
        return rules.keySet();
    }

    public LinkedHashMap get_rules() {
        return rules;
    }

}
