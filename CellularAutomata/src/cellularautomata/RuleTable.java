/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellularautomata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris
 */
class RuleTable {

    private HashMap<String, Integer> rules;
    private int neighbours;

    public RuleTable(int nb) {
        neighbours = nb;
        
        
    }

    public int lookup_rule(String key) {
        return rules.get(key);
    }

}
