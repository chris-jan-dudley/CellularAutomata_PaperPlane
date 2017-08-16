
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Chris
 */
class Automata {

    /**
     *
     */
    LinkedList<Cell> container;
    int neighbours,
            /**
             *
             */
            /**
             *
             */
            a_size;

    /**
     *
     */
    RuleTable rules;

    /**
     *
     * @param initial_states
     * @param nb
     * @param rl
     */
    public Automata(String initial_states, int nb, RuleTable rl) {

        container = new LinkedList<>();
        neighbours = nb;

        for (char num : initial_states.toCharArray()) {
            container.add(new Cell(Character.getNumericValue(num)));
        }

        a_size = container.size();
        rules = rl;
    }

    /**
     *
     * @return
     */
    public String to_string() {

        String output = "";
        for (Cell c : container) {
            output = output.concat(c.to_string());
        }
        System.out.println(output);
        return output;
    }

    public LinkedList<Cell> get_container() {
        return container;
    }

    /**
     *
     * @return
     */
    public String get_print() {

        String output = "";
        output = output.concat("| ");
        for (Cell c : container) {
            output = output.concat(c.to_string());
            output = output.concat(" | ");
        }
        return output;
    }

    /**
     *
     * @param index
     * @return
     */
    public ArrayList get_local(int index) {

        ArrayList neighbourhood = new ArrayList<>();
        neighbourhood.addAll(get_l_local(index));
        neighbourhood.add(container.get(index).to_string());
        neighbourhood.addAll(get_r_local(index));
        return neighbourhood;
    }

    /**
     *
     * @param index
     * @return
     */
    public ArrayList get_l_local(int index) {

        ArrayList l_neighbours = new ArrayList<>();
        for (int i = neighbours; i > 0; i--) {
            if (index - i < 0) {
                l_neighbours.add(container.get(index - i + a_size).to_string());
            } else {
                l_neighbours.add(container.get(index - 1).to_string());
            }
        }

        return l_neighbours;
    }

    /**
     *
     * @param index
     * @return
     */
    public ArrayList get_r_local(int index) {

        ArrayList r_neighbours = new ArrayList<>();
        for (int i = 1; i <= neighbours; i++) {
            if (index + i >= a_size) {
                r_neighbours.add(container.get(index + i - a_size).to_string());
            } else {
                r_neighbours.add(container.get(index + i).to_string());
            }
        }
        return r_neighbours;
    }

    /**
     *
     */
    public void do_update() {

        LinkedList new_automata = new LinkedList<>();
        for (Cell automata1 : container) {
            ArrayList local = get_local(container.indexOf(automata1));
            String local_key = String.join("", local);
            new_automata.add(new Cell(rules.lookup_rule(local_key)));
        }
        container = new_automata;
    }

}
