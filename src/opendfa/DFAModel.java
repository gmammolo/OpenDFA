/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa;

import java.util.ArrayList;
import opendfa.DFA.DFA;
import opendfa.DFA.RangeChar;

/**
 * Modello del DFA per utilizzare direttamente le api senza interfaccia grafica
 *
 * @author giuseppe
 */
public abstract class DFAModel {

    protected DFA dfa;

    public DFAModel() {
        generateDFA();
    }

    public DFAModel(int numState) {
        generateDFA(numState);
    }

    /**
     * Inizializza un DFA: in questo metodo andranno messi i setMove e
     * addFinalState
     */
    protected abstract void initializeDFA();

    /**
     *
     * @return numero di stati presenti nel DFA
     */
    protected abstract int numState();

    /**
     * Genera il DFA
     */
    private void generateDFA() {
        dfa = new DFA(numState());
        initializeDFA();
    }

    /**
     * Genera il DFA
     */
    private void generateDFA(Integer numState) {
        dfa = new DFA(numState);
        initializeDFA();
    }

    /**
     * Imposta una move da uno stato p ad uno stato q dovuta a un carattere
     * speciale
     *
     * @see <code>RangeChar</code> per maggiori informazioni
     * @param p stato iniziale
     * @param g carattere
     * @param q stato finale
     */
    protected void setMove(Integer p, RangeChar g, Integer q) {
        dfa.setMove(p, g.getCharacters(), q);
    }

    /**
     * Imposta una move da uno stato p ad uno stato q dovuta a un carattere ch
     *
     * @param p stato iniziale
     * @param ch carattere
     * @param q stato finale
     */
    protected void setMove(Integer p, char ch, Integer q) {
        dfa.setMove(p, ch, q);
    }

    /**
     * Imposta una move da uno stato p ad uno stato q per un range di valori
     *
     * @param p stato iniziale
     * @param start inizio del range di valori
     * @param end fine del range di valori
     * @param q stato finale
     * @deprecated
     * @see
     * <code>protected void setMove(Integer p, RangeChar g, Integer q)</code>
     */
    protected void setMove(Integer p, char start, char end, Integer q) {
        dfa.setMove(p, start, end, q);
    }

    /**
     *
     * @param p stato iniziale
     * @param ch array di char contente la serie di caratteri che permettono il
     * movimento
     * @param q stato finale
     * @deprecated
     * @see
     * <code>protected void setMove(Integer p, RangeChar g, Integer q)</code>
     */
    protected void setMove(Integer p, char[] ch, Integer q) {
        dfa.setMove(p, ch, q);
    }

    /**
     *
     * @param p stato iniziale
     * @param ch array di char contente la serie di caratteri che permettono il
     * movimento
     * @param q stato finale
     * @deprecated
     * @see
     * <code>protected void setMove(Integer p, RangeChar g, Integer q)</code>
     */
    protected void setMove(Integer p, ArrayList<Character> ch, Integer q) {
        dfa.setMove(p, ch, q);
    }

    /**
     * Aggiunge un nuovo stato finale al DFA
     *
     * @param p stato finale
     */
    protected void addFinalState(Integer p) {
        dfa.addFinalState(p);
    }

    /**
     * Stampa il dot del DFA. l'output URL è definito in config.properties
     *
     * @param name node nel grafo
     *
     */
    public void toDot(String name) {
        dfa.toDOT(name);
    }

    /**
     * Stampa il dot del DFA.
     *
     * @param name node nel grafo
     * @param outputDir URL della directory di output
     */
    public void toDot(String name, String outputDir) {
        dfa.toDOT(name, outputDir);
    }

    /**
     * Genera il png del DFA (Richiede GraphViz installato).
     *
     * @param name node nel grafo
     */
    public void toPng(String name) {
        dfa.toPNG(name);
    }

    /**
     * Genera il png del DFA (Richiede GraphViz installato).
     *
     * @param name node nel grafo
     * @param outputDir URL della directory di output
     */
    public void ToPng(String name, String outputDir) {
        dfa.toPNG(name, outputDir);
    }

    /**
     * Stampa a video il codice del dfa
     *
     * @param s nome della classe java
     */
    public void toJava(String s) {
        dfa.toJava(s);
    }

    /**
     * Stampa a video il codice del dfa
     *
     * @param s nome della classe java
     * @param outputDir URL della directory di output
     *
     */
    public void toJava(String s, String outputDir) {
        dfa.toJava(s, outputDir);
    }

    /**
     * Verifica se una stringa e` riconosciuta dall'automa.
     *
     * @param line Stringa da riconoscere.
     * @return <code>true</code> se la stringa e` stata riconosciuta,
     * <code>false</code> altrimenti.
     */
    public boolean scan(String line) {
        return dfa.scan(line);
    }


}
