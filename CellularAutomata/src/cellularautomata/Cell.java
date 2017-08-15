/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellularautomata;

/**
 *
 * @author Chris
 */
class Cell {

    private Integer on_off;

    Cell(int num) {
        on_off = num;
    }

    public int get_value() {
        return on_off;
    }

    public void set_value(int num) {
        on_off = num;
    }

    public void switch_value() {
        if (on_off == 1) {
            on_off = 0;
        } else {
            on_off = 1;
        }
    }

    public String to_string() {
        return on_off.toString();
    }
}
