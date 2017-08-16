
/**
 * The Cell class holds information and behaviour for each cell in the automaton.
 *
 * @author Chris
 */
public class Cell {

    /**
     * This holds the value of the cell, on or off, represented by an integer.
     */
    private final int on_off;

    /**
     * Constructor sets the passed variable, num, as the initial starting value
     * of the cell.
     *
     * @param num
     */
    public Cell(int num) {
        on_off = num;
    }

    /**
     * Getter to return the current value of the cell
     *
     * @return on_off
     */
    public int get_value() {
        return on_off;
    }

    /**
     * Gets a string representation of the cell's value for printing
     *
     * @return on_off cast to a string.
     */
    public String to_string() {
        return String.valueOf(on_off);
    }
}
