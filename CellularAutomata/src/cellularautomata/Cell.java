
/**
 *
 * @author Chris
 */
public class Cell {

    /**
     *
     */
    private Integer on_off;

    /**
     *
     * @param num
     */
    Cell(int num) {
        on_off = num;
    }

    /**
     *
     * @return
     */
    public int get_value() {
        return on_off;
    }

    /**
     *
     * @param num
     */
    public void set_value(int num) {
        on_off = num;
    }

    /**
     *
     */
    public void switch_value() {
        if (on_off == 1) {
            on_off = 0;
        } else {
            on_off = 1;
        }
    }

    /**
     *
     * @return
     */
    public String to_string() {
        return on_off.toString();
    }
}
