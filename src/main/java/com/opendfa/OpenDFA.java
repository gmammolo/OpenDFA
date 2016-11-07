/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.java.com.opendfa.DFA.Edge;
import main.java.com.opendfa.DFA.Move;

/**
 *
 * @author terasud
 */
public class OpenDFA extends DFAModel {

    @Override
    protected void initializeDFA() {
        setMove(0, 'a', 1);
        setMove(1, 'b', 2);

        addFinalState(2);
    }

    @Override
    protected int numState() {
        return dfa.getNumberOfStates();
    }

    public OpenDFA(int numState) {
        super(numState);

    }

    public ArrayList<String> getEdgesStringify() {
        return this.dfa.getEdgeStringify();
    }

    public Edge getEdgeAt(int index) {
        return this.dfa.getEdgeAt(index);
    }

    Integer[] getFinalStates() {
        return this.dfa.getFinalState();
    }

    /**
     *
     * @param start
     * @param c
     * @param end
     */
    @Override
    public void setMove(Integer start, char c, Integer end) {
        super.setMove(start, c, end);
        setChanged();
        notifyObservers();
    }

    @Override
    public void setMove(Integer start, char charAt, char charAt0, Integer end) {
        super.setMove(start, charAt, charAt0, end);
        setChanged();
        notifyObservers();
    }

    @Override
    public void setMove(Integer start, ArrayList<Character> list, Integer end) {
        super.setMove(start, list, end);
        setChanged();
        notifyObservers();
    }

    public boolean isValidState(Integer end) {
        return dfa.isValidState(end);
    }

    boolean isFinalState(int i) {
        return dfa.isFinalState(i);
    }

    public void addState(int state) {
        if (state == numState()) {
            dfa.addNewState();
            setChanged();
            notifyObservers();
        }

    }

    public int move(int start, char c) {
        return dfa.move(start, c);
    }

    public int move(Move m) {
        return dfa.move(m.start, m.alphabet.get(0));
    }

    @Override
    public void addFinalState(Integer p) {
        super.addFinalState(p); //To change body of generated methods, choose Tools | Templates.
        setChanged();
        notifyObservers();
    }

    public void remove(Integer start, ArrayList<Character> alphabet) {
        dfa.remove(start, alphabet);
    }

    /**
     * Crea un'immagine del grafo nella cartella temporanea, per la
     * visualizzazione
     *
     * @param name
     * @return
     */
    public String toPngTemp(String name) {
        return dfa.toPNGTemp(name);
    }

    void saveFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("state;finalState");
            HashSet<Integer> allState = dfa.getAllState();
            writer.println(allState.size());
            for (Integer state : allState) {
                writer.println(state + ";" + isFinalState(state));
            }

            writer.println("start;end;character");
            ArrayList<Edge> allEdges = this.getEdges();
            writer.println(allEdges.size());
            for (Edge e : allEdges) {
                writer.println(e.start + ";" + e.end + ";" + e.alphabet.toString());
            }

            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenDFA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Edge> getEdges() {
        return dfa.getEdges();
    }

    public static OpenDFA loadFile(File file) {
        OpenDFA dfa = new OpenDFA(0);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            in.readLine(); //testo inutile
            Integer n = Integer.parseInt(in.readLine());
            dfa = new OpenDFA(n);
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                ArrayList<String> list = new ArrayList<>(Arrays.asList(line.split(";")));
                dfa.addState(Integer.parseInt(list.get(0)));
                if (Boolean.getBoolean(list.get(1))) {
                    dfa.addFinalState(Integer.parseInt(list.get(0)));
                }
            }

            in.readLine(); //testo inutile
            n = Integer.parseInt(in.readLine());
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                ArrayList<String> list = new ArrayList<>(Arrays.asList(line.split(";")));
                ArrayList<Character> chars = new ArrayList<>();
                for (char c : line.substring(5, line.length() - 1).toCharArray()) {
                    if (c != ',' && c != ' ') {
                        chars.add(c);
                    }
                }
                dfa.setMove(Integer.parseInt(list.get(0)), chars, Integer.parseInt(list.get(1)));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenDFA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpenDFA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(OpenDFA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dfa;
    }

    void writeFile(File file, String text) {
            try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(text);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenDFA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ToPng(String name, File file) {
        dfa.toPNG(name, file);
    }

}
