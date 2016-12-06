/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa;

import java.util.ArrayList;
import java.util.Observable;
import main.java.com.opendfa.DFA.DFA;
import main.java.com.opendfa.DFA.RangeChar;

/**
 * Modello del DFA per utilizzare direttamente le api senza interfaccia grafica
 *
 * @author giuseppe
 */
public abstract class DFAModel extends Observable {

    protected DFA dfa;

    public DFAModel() {
        generateDFA();
    }

    public DFAModel(int numState) {
        generateDFA(numState);
    }

    /**
     * Inizializza un DFA. in questo metodo andranno messi i setMove e
     * addFinalState
     */
    protected abstract void initializeDFA();

    /**
     *
     * @return numero di stati presenti nel DFA
     */
    protected abstract int numState();

    /**
     * Genera il DFA. Richiama il metodo <code>initializeDFA</code> ed esegue il
     * suo contenuto
     */
    private void generateDFA() {
        dfa = new DFA(numState());
        initializeDFA();
    }

    /**
     * Genera il DFA. Richiama il metodo <code>initializeDFA</code> ed esegue il
     * suo contenuto
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
     * @return
     *
     */
    public String toDot(String name) {
        return dfa.toDOT(name);
    }

    /**
     * Crea un file con il Dot generato. lo salva nella output dir indicata dal
     * file di configurazione
     *
     * @param name nome del file
     */
    public void writeToDot(String name) {
        dfa.writeToDot(name);
    }

    /**
     * Crea un file con il Dot generato. lo salva nella output dir indicata dal
     * file di configurazione
     *
     * @param name nome del file
     * @param OutputDir cartella in cui sarà creato
     */
    public void writeToDot(String name, String OutputDir) {
        dfa.writeToDot(name, OutputDir);
    }

    /**
     * Genera il png del DFA (Richiede GraphViz installato).
     *
     * @param name node nel grafo
     * @return URL
     */
    public String toPng(String name) {
        return dfa.toPNG(name);
    }

    /**
     * Genera il png del DFA (Richiede GraphViz installato).
     *
     * @param name node nel grafo
     * @param outputDir URL della directory di output
     * @return URL
     */
    public String ToPng(String name, String outputDir) {
        return dfa.toPNG(name, outputDir);
    }

    /**
     * Stampa a video il codice del dfa
     *
     * @param s nome della classe java
     * @return
     */
    public String toJava(String s) {
        return dfa.toJava(s);
    }

    /**
     * Genera il file Java che accetta il linguaggio del dfa (con metodo scan).
     * viene salvato nella directory indicata nel file di configurazione
     *
     * @param name nome del file
     */
    public void writeToJava(String name) {
        dfa.writeToJava(name);
    }

    /**
     * Genera il file Java che accetta il linguaggio del dfa (con metodo scan).
     *
     * @param name nome del file
     * @param OutputDir directory in cui sarà
     */
    public void writeToJava(String name, String OutputDir) {
        dfa.writeToJava(name, OutputDir);
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
    
    /**
     * Minimizza il dfa 
     */
    public void minimize() {
        this.dfa =  dfa.minimize();
    }

}
