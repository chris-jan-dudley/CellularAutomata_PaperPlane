
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Chris
 */
public class CellularAutomataTesting {
    
    CASimulation test_casimulation;
    GASimulation test_gasimulation;
    RuleTable test_rules_1, test_rules_2;
    Automata test_automata_1, test_automata_2;
    
    public CellularAutomataTesting() {
    }
    
    @Before
    public void setUp() {
        //Setting up testing CASimulation, Rules and Automata
        test_casimulation = new CASimulation();
        test_gasimulation = new GASimulation();
        test_rules_1 = new RuleTable(1);
        test_rules_1.create_rules(1, "10101010");
        test_rules_2 = new RuleTable(2);
        test_rules_2.create_rules(2, "10101010101010101010101010101010");
        
        String test_initial = "10101";
        test_automata_1 = new Automata(test_initial, 1, test_rules_1);
        test_automata_2 = new Automata(test_initial, 2, test_rules_2);
    }
    
    @Test
    public void test_cell_functions() {
        //Testing if Cell initialisation functions
        Cell test_cell = new Cell(1);
        Assert.assertEquals("1", test_cell.to_string());
    }
    
    @Test
    public void test_automata_functions() {
        //Testing if automata is set and read properly
        Assert.assertEquals("10101", test_automata_1.to_string());

        //Testing reading of local area around cell with 1 neighbour
        String test_local = String.join("", test_automata_1.get_local(2));
        Assert.assertEquals("010", test_local);
        
        String test_r_local = String.join("", test_automata_1.get_r_local(2));
        Assert.assertEquals("0", test_r_local);
        
        String test_l_local = String.join("", test_automata_1.get_l_local(2));
        Assert.assertEquals("0", test_l_local);

        //Testing reading of local area around cell with 2 neighbour
        test_local = String.join("", test_automata_2.get_local(2));
        Assert.assertEquals("10101", test_local);
        
        test_r_local = String.join("", test_automata_2.get_r_local(2));
        Assert.assertEquals("01", test_r_local);
        
        test_l_local = String.join("", test_automata_2.get_l_local(2));
        Assert.assertEquals("10", test_l_local);

        //Visual test of print out
        System.out.println(test_automata_1.to_print());

        //run update
        test_automata_1.do_update();

        //testing manually calculated outputs against simulated outputs
        System.out.println(test_automata_1.to_print());
        Assert.assertEquals(1, test_automata_1.get_container().get(0).get_value());
        Assert.assertEquals(0, test_automata_1.get_container().get(1).get_value());
        Assert.assertEquals(1, test_automata_1.get_container().get(2).get_value());
        Assert.assertEquals(0, test_automata_1.get_container().get(3).get_value());
        Assert.assertEquals(0, test_automata_1.get_container().get(4).get_value());
    }
    
    @Test
    public void test_rules_functions() {
        //testing generation of rules table
        int nb = 1;
        String rule_res = "10101010";
        test_rules_1.create_rules(nb, rule_res);
        int val = test_rules_1.lookup_rule("101");
        Assert.assertTrue(0 == val);

        //testing that each key is correctly generated in the correct orer
        Set test_keys = test_rules_1.get_rules().keySet();
        ArrayList expected = new ArrayList<>(Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111"));
        Assert.assertEquals(expected.get(0), test_keys.toArray()[0]);
        Assert.assertEquals(expected.get(1), test_keys.toArray()[1]);
        Assert.assertEquals(expected.get(2), test_keys.toArray()[2]);
        Assert.assertEquals(expected.get(3), test_keys.toArray()[3]);
        Assert.assertEquals(expected.get(4), test_keys.toArray()[4]);
        Assert.assertEquals(expected.get(5), test_keys.toArray()[5]);
        Assert.assertEquals(expected.get(6), test_keys.toArray()[6]);
        Assert.assertEquals(expected.get(7), test_keys.toArray()[7]);

        //Visual test of printed rule table
        System.out.println(test_rules_1.to_print());
    }
    
    @Test
    public void test_gasimulation() {
        
        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(test_gasimulation.random_bin_string(8).matches("[0,1]+"));
        }
        
        Automata ta = new Automata("0000011111", 1, test_rules_1);
        Assert.assertEquals("1111111111", test_gasimulation.get_expected_final(ta));
        ta = new Automata("0000001111", 1, test_rules_1);
        Assert.assertEquals("0000000000", test_gasimulation.get_expected_final(ta));
        String string_a = "1000";
        String string_b = "1111";
        Assert.assertEquals(3, test_gasimulation.get_error_rate(string_a, string_b));
    }
    
}
