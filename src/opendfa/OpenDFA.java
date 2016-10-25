/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa;

import opendfa.DFA.DFAModel;
import opendfa.DFA.Gen;

/**
 *
 * @author terasud
 */
public class OpenDFA extends DFAModel{

    private int numstate;
        
    @Override
    protected void InitializeDFA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected int NumState() {
        return this.numstate;
    }
    
    public OpenDFA() {
        numstate=0;
    }
    
    protected Boolean checkState(Integer newState) {
         if(newState<-1) {
            throw new IllegalArgumentException("Non possono esistere stati negativi");
        }
         if(newState > this.NumState()) {
             this.numstate = newState;
         } 
         return true;
    }


    @Override
    protected void SetMove(Integer p, Gen g, Integer q)
    {
        checkState(p);
        checkState(q);
        super.SetMove(p, g, q);
    }
    
    
    
    /**
     * Imposta una move da uno stato p ad uno stato q dovuta a un carattere ch
     * @param p stato iniziale  
     * @param ch carattere  
     * @param q stato finale
     */
    @Override
    protected void SetMove(Integer p, char ch, Integer q)
    {
        checkState(p);
        checkState(q);
        super.SetMove(p, ch, q);
    }
    
    /**
     * Imposta una move da uno stato p ad uno stato q per  un range di valori
     * @param p stato iniziale
     * @param start inizio del range di valori
     * @param end fine del range di valori
     * @param q stato finale
     */
    @Override
    protected void SetMove(Integer p, char start, char end, Integer q)
    {
        checkState(p);
        checkState(q);
        super.SetMove(p, start,end, q);
    }
        
    /**
     * 
     * @param p stato iniziale 
     * @param ch array di char contente la serie di caratteri che permettono il movimento
     * @param q stato finale
     */
    @Override
    protected void SetMove(Integer p, char[] ch, Integer q)
    {
        for(Character a :ch) 
        {
            SetMove(p, a, q);
        }
    }
    
    /**
     * Aggiunge un nuovo stato finale al DFA
     * @param p stato finale
     */
    @Override
    protected void AddFinalState(Integer p)
    {
        checkState(p);
        super.AddFinalState(p);
    }
    
    
    public Integer[] getFinalState() {
      return this.dfa.getFinalState();  
    }
    
    public String[] getEdge() {
        return this.dfa.getEdgeStringify();
    } 
}
