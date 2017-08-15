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
 
    private boolean on_off;

    Cell(boolean b) {
        on_off = b;
    }

    boolean get_value() {
        return on_off;
    }

    void switch_value() {
        on_off = !on_off;
    }
}
