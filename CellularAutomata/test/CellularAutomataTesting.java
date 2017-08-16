
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

    Simulation test_simulation;
    RuleTable test_rules;
    Automata test_automata;

    public CellularAutomataTesting() {
    }

    @Before
    public void setUp() {
        test_simulation = new Simulation();
        test_rules = new RuleTable(1);
        test_rules.create_rules(1, "10101010");
        String test_initial = "10101";
        test_automata = new Automata(test_initial, 1, test_rules);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_cell_functions() {
        Cell test_cell = new Cell(1);
        Assert.assertEquals("1", test_cell.to_string());

        test_cell.switch_value();
        Assert.assertFalse(test_cell.get_value() == 1);

        Assert.assertEquals("0", test_cell.to_string());

        test_cell.set_value(1);
        Assert.assertTrue(test_cell.get_value() == 1);
    }

    @Test
    public void test_automata_functions() {
        Assert.assertEquals("10101", test_automata.to_string());

        String test_local = String.join("", test_automata.get_local(2));
        Assert.assertEquals("010", test_local);

        String test_r_local = String.join("", test_automata.get_r_local(2));
        Assert.assertEquals("0", test_r_local);

        String test_l_local = String.join("", test_automata.get_l_local(2));
        Assert.assertEquals("0", test_l_local);

        System.out.println(test_automata.get_print());
        
        test_automata.do_update();
        
        System.out.println(test_automata.get_print());
        
        Assert.assertEquals(1, test_automata.get_container().get(0).get_value());
        Assert.assertEquals(0, test_automata.get_container().get(1).get_value());
        Assert.assertEquals(1, test_automata.get_container().get(2).get_value());
        Assert.assertEquals(0, test_automata.get_container().get(3).get_value());
        Assert.assertEquals(0, test_automata.get_container().get(4).get_value());
    }

    @Test
    public void test_rules_functions() {
        int nb = 1;
        String rule_res = "10101010";
        test_rules.create_rules(nb, rule_res);
        int val = test_rules.lookup_rule("101");
        Assert.assertTrue(0 == val);

        Set test_keys = test_rules.get_keys();
        ArrayList expected = new ArrayList<>(Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111"));
        Assert.assertEquals(expected.get(0), test_keys.toArray()[0]);
        Assert.assertEquals(expected.get(1), test_keys.toArray()[1]);
        Assert.assertEquals(expected.get(2), test_keys.toArray()[2]);
        Assert.assertEquals(expected.get(3), test_keys.toArray()[3]);
        Assert.assertEquals(expected.get(4), test_keys.toArray()[4]);
        Assert.assertEquals(expected.get(5), test_keys.toArray()[5]);
        Assert.assertEquals(expected.get(6), test_keys.toArray()[6]);
        Assert.assertEquals(expected.get(7), test_keys.toArray()[7]);
        
        for (Object rule : test_rules.get_keys()) {
            System.out.println("| " + rule.toString() + " | " + test_rules.get_rules().get(rule).toString() + " |");
        }
        
        System.out.println(test_rules.to_print());

    }

    @Test
    public void test_simulation_functions() {
        int num_ticks = 10;
        test_simulation.set_num_ticks(num_ticks);
        Assert.assertEquals(num_ticks, test_simulation.get_num_ticks());
        Assert.assertEquals(0, test_simulation.get_current_tick());
    }
}
