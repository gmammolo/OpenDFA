/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa.DFA;

import java.util.ArrayList;


/**
 * Classe di supporto. Pensata per essere di sola lettura e permettere la lettura pulita di una transizione di un DFA
 * (Edge in quanto ricorda l'arco di un grafo)
 * @author terasud
 */
public class Edge {
    /**
     * Stato di partenza.
     */
    public final Integer start;
    /**
     * Stato di arrivo.
     */
    public final Integer end;
    /**
     * label grafico per visualizzare i valori ben formattati. il dfa contiene il parametro label che deve 
     * essere copiato qui.
     */
    public final String label;
    /**
     * lista dei caratteri dell'arco.
     */
    public final ArrayList<Character> alphabet;
   
    
   public Edge(Integer start, Integer end, ArrayList<Character> character, String label) {
       this.start = start;
       this.end = end;
       this.alphabet = character;
       this.label = label;
   }
    
}
