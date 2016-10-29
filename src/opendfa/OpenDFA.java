/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa;

import java.util.ArrayList;

/**
 *
 * @author terasud
 */
public class OpenDFA extends DFAModel {

    private int numState;

    @Override
    protected void initializeDFA() {
        setMove(0, 'a', 1);
        setMove(1, 'b', 2);

        addFinalState(2);
    }

    @Override
    protected int numState() {
        return this.numState;
    }

    public OpenDFA(int numState) {
        super(numState);
        this.numState = numState;

    }

    public ArrayList<String> getEdge() {
        return this.dfa.getEdgeStringify();
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
    }

    
    @Override
    public void setMove(Integer start, char charAt, char charAt0, Integer end) {
        super.setMove(start, charAt, charAt0,end);
    }

    @Override
    public void setMove(Integer start, ArrayList<Character> list, Integer end) {
       super.setMove(start, list, end);
    }
    
    public boolean isValidState(Integer end) {
        return dfa.isValidState(end);
    }

}
