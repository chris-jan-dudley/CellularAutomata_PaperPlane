package cellularautomata;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        test_rules = new RuleTable();
        ArrayList<Integer> test_initial = new ArrayList<>(Arrays.asList(1,0,1,0,1));
        test_automata = new Automata(test_initial);
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
        test_automata.to_string();
    }
}
