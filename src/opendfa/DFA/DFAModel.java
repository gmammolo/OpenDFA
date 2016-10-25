/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

/**
 *
 * @author giuseppe
 */
public abstract class DFAModel {

    protected DFA dfa;

    public DFAModel() {
        GenerateDFA();
    }

    /**
     * Inizializza un DFA: in questo metodo andranno messi i SetMove e
     * AddFinalState
     */
    protected abstract void InitializeDFA();

    /**
     *
     * @return numero di stati presenti nel DFA
     */
    protected abstract int NumState();

    /**
     * Genera il DFA
     */
    private void GenerateDFA() {
        dfa = new DFA(NumState());
        InitializeDFA();
    }

    protected void SetMove(Integer p, Gen g, Integer q) {
        if (g.val.length > 0) {
            SetMove(p, g.val, q);
        } else {
            SetMove(p, g.start, g.end, q);
        }
    }

    /**
     * Imposta una move da uno stato p ad uno stato q dovuta a un carattere ch
     *
     * @param p stato iniziale
     * @param ch carattere
     * @param q stato finale
     */
    protected void SetMove(Integer p, char ch, Integer q) {
        dfa.setMove(p, ch, q);
    }

    /**
     * Imposta una move da uno stato p ad uno stato q per un range di valori
     *
     * @param p stato iniziale
     * @param start inizio del range di valori
     * @param end fine del range di valori
     * @param q stato finale
     */
    protected void SetMove(Integer p, char start, char end, Integer q) {
        dfa.setMove(p, start, end, q);
    }

    /**
     *
     * @param p stato iniziale
     * @param ch array di char contente la serie di caratteri che permettono il
     * movimento
     * @param q stato finale
     */
    protected void SetMove(Integer p, char[] ch, Integer q) {
        for (Character a : ch) {
            SetMove(p, a, q);
        }
    }

    /**
     * Aggiunge un nuovo stato finale al DFA
     *
     * @param p stato finale
     */
    protected void AddFinalState(Integer p) {
        dfa.addFinalState(p);
    }

    /**
     * Stampa a video il dot del DFA
     *
     * @param s
     */
    public void toDot(String name) {
        dfa.toDOT(name);
    }

    public void toDot(String name, String outputDir) {
        dfa.toDOT(name, outputDir);
    }

    public void toPng(String name) {
        dfa.toPNG(name);
    }

    public void toPng(String name, String outputDir) {
        dfa.toPNG(name, outputDir);
    }

    public void toDotAlternative(String name) {
        dfa.toDOTAlternative(name);
    }

    public void toDotAlternative(String name, String OutputDir) {
        dfa.toDOTAlternative(name, OutputDir);
    }

    /**
     * Stampa a video il codice del dfa
     *
     * @param s nome della classe java
     */
    public void toJava(String s) {
        dfa.toJava(s);
    }

    public boolean Scan(String line) {
        return dfa.scan(line);
    }
    

}
