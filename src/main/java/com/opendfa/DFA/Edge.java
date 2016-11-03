/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa.DFA;

import java.util.ArrayList;


/**
 *Un metodo pulito per passare una transizione 
 * @author terasud
 */
public class Edge {
    public final Integer start;
    public final Integer end;
    public final String label;
    public final ArrayList<Character> alphabet;
   
    
   public Edge(Integer start, Integer end, ArrayList<Character> character, String label) {
       this.start = start;
       this.end = end;
       this.alphabet = character;
       this.label = label;
   }
    
}
