package cellularautomata;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        test_automata = new Automata();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_cell_functions() {
        Cell test_cell = new Cell(true);
        test_cell.switch_value();
        Assert.assertFalse(test_cell.get_value());
    }
}
