/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa;

import opendfa.GraphViz.GraphGUI;

/**
 *
 * @author terasud
 */
public class OpenDFA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //String dotFormat = "1->2;1->3;1->4;4->5;4->6;6->7;5->7;3->8;3->6;8->7;2->8;2->5;";
        //createDotGraph(dotFormat, "DotGraph");
        new GraphGUI().createPngGraph(null, null);
    }

}
