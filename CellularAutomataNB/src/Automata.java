
import java.util.ArrayList;

/**
 * This class represents the automata, and contains all the cells.
 *
 * @author Chris
 */
public class Automata {

    /**
     * ArrayList of Cells is used to hold all the cells of the automaton. Number
     * of neighbours and array size fields kept as fields. RuleTable used to
     * lookup rules of the automaton.
     */
    ArrayList<Cell> container;
    int neighbours, a_size;
    RuleTable rules;

    /**
     * Constructor for the Automata class. The initial states are passed as a
     * string by the user. Neighbours passed as an int, and a RuleTable is
     * passed as well. container ArrayList is populated by using each character
     * of the initial_states as the parameter of the Cell constructor.
     *
     * @param initial_states String of initial state of the automaton.
     * @param nb Number of neighbours the rules will consider.
     * @param rl RuleTable used to lookup new values.
     */
    public Automata(String initial_states, int nb, RuleTable rl) {

        container = new ArrayList<>();
        neighbours = nb;

        for (char num : initial_states.toCharArray()) {
            container.add(new Cell(Character.getNumericValue(num)));
        }

        a_size = container.size();
        rules = rl;
    }

    /**
     * Returns a string representation of the container. (used for testing)
     *
     * @return
     */
    public String to_string() {

        String output = "";
        for (Cell c : container) {
            output = output.concat(c.to_string());
        }
        return output;
    }

    /**
     * Returns the ArrayList container
     *
     * @return container
     */
    public ArrayList<Cell> get_container() {
        return container;
    }

    /**
     * Returns a String of the "prettified" automaton, with borders. Suitable
     * for printing to the console.
     *
     * @return String representation of the automaton.
     */
    public String to_print() {

        String output = "";
        output = output.concat("| ");
        for (Cell c : container) {
            output = output.concat(c.to_string());
            output = output.concat(" | ");
        }
        return output;
    }

    /**
     * Method to get the local neighbourhood of a particular cell at the given
     * index. First the neighbours to the left are added, followed by the cell
     * queried, followed by cells to the right. This is returned as an ArrayList
     *
     * @param index Cell at the centre of the local neighbourhood.
     * @return local The cell values around the queried cell.
     */
    public ArrayList get_local(int index) {

        ArrayList local = new ArrayList<>();
        local.addAll(get_l_local(index));
        local.add(container.get(index).to_string());
        local.addAll(get_r_local(index));
        return local;
    }

    /**
     * Returns the left cells. Gets the number of cell values equal to
     * neighbours, from left to right. Will wrap round the list of cells if
     * needed. i.e. for neighbour = 1, the last cell in container will be used
     * when the first cell is queried.
     *
     * @param index Index of cell in container to be queried.
     * @return l_local The cells left of the queried cell.
     */
    public ArrayList get_l_local(int index) {

        ArrayList l_local = new ArrayList<>();
        for (int i = neighbours; i > 0; i--) {
            if (index - i < 0) {
                l_local.add(container.get(index - i + a_size).to_string());
            } else {
                l_local.add(container.get(index - i).to_string());
            }
        }

        return l_local;
    }

    /**
     * Returns the right cells. Gets the number of cell values equal to
     * neighbours, from left to right. Will wrap round the list of cells if
     * needed. i.e. for neighbour = 1, the first cell will be used when the last
     * cell is queried. when the first cell is queried.
     *
     * @param index Index of cell in container to be queried.
     * @return r_local The cells right of the queried cell.
     */
    public ArrayList get_r_local(int index) {

        ArrayList r_local = new ArrayList<>();
        for (int i = 1; i <= neighbours; i++) {
            if (index + i >= a_size) {
                r_local.add(container.get(index + i - a_size).to_string());
            } else {
                r_local.add(container.get(index + i).to_string());
            }
        }
        return r_local;
    }

    /**
     * Called when the next tick occurs. This runs the update of the container.
     * A temporary ArrayList is used to store new Cell values, which are
     * obtained by looking up each local "neighbourhood" and looking this up as
     * a key in the RuleTable. The associated value is used in the constructor
     * of the new Cell. The container is then replaced by the new set of values
     * in new_automata.
     */
    public void do_update() {

        ArrayList new_automata = new ArrayList<>();
        for (Cell automata1 : container) {
            ArrayList local = get_local(container.indexOf(automata1));
            String local_key = String.join("", local);
            new_automata.add(new Cell(rules.lookup_rule(local_key)));
        }
        container = new_automata;
    }

}
