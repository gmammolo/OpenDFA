/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.GraphViz;

import static opendfa.GraphViz.GraphGUI.createDotGraph;


/**
 *
 * @author terasud
 */
public class tmpmain {
        public static void main(String[] args) throws Exception {
        String dotFormat="1->2;1->3;1->4;4->5;4->6;6->7;5->7;3->8;3->6;8->7;2->8;2->5;";
        createDotGraph(dotFormat, "DotGraph");
 }
}
