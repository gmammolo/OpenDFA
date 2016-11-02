/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.GUI;

import javax.swing.JLabel;

/**
 *
 * @author terasud
 */
public class JLabelNumerated extends JLabel {

    private int num;

    public JLabelNumerated(String name, int num) {
        super(name);
        this.num = num;
    }
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
}
