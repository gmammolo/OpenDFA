/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class Support_Transitions {

    private ArrayList<Transition> transition;
    
    
    public Support_Transitions(){
        transition = new ArrayList();
    }
    
    
    public HashSet<Integer> InitialState()
    {
        HashSet<Integer> state = new HashSet<Integer>();
        for(Transition tr : transition)
        {
            state.add(tr.start);
        }
        return state;
    }
    
    
    public Boolean[] reach(int start, int numberOfStates)
    {
        Boolean[] res = new Boolean[numberOfStates];
        for(int i=0; i< numberOfStates;i++)
        {
           res[i] = (start == i);
        }
        for(Transition tr: transition)
        {
            if(tr.start==start)
            {
                res[tr.end] = true;
            }   
        }
        return res;
    }
    
    
    /**
     * Indica una transizione 
     * @param start stato di partenza
     * @param c ccarattere
     * @return stato d arrivo
     */
    public Integer GetTransition(Integer start, char c)
    {
        for(Transition tr: transition)
        {
            if(tr.start==start && tr.label.contains(c))
                return tr.end;
                
        }
        return -1;
    }
    
    public void AddTransiction(int start, char ch, int end)
    {
        Transition ts=new Transition(start, end,ch); 
        if(transition.contains(ts))
        {
           ((Transition)transition.get(transition.indexOf(ts))).AddLabel(ch);
        }
        else
        {
            transition.add(ts);
        }
    }
    
    
    
    
    /**
     * Restituisce il contenuto delle transizioni in formato Dot
     * @return 
     */
    public String toDot()
    {
        String s="";
        for(Object o : transition)
        {
            if (o instanceof Transition) {
                Transition m = (Transition) o;
            
                s+=m.toDot();
            }
        }
        return s;
    }
    
    
    
    /**
     * Restituisce il contenuto delle transizioni in formato java
     * @return 
     */
    public String toJava()
    {
        String s = "";
        if(transition.size()>0)
        {
            //sorting
            Collections.sort(transition, new Comparator<Transition >() {
                @Override
                public int compare(Transition t, Transition t1) {
                    return (t.start.compareTo(t1.start));
                }
            });
            
            int oldstate = ((Transition)transition.get(0)).start;
            s+="\t\t\tcase "+oldstate+":\n";
            s+="\t\t\t\t"+((Transition)transition.get(0)).toJava();
            for(int i=1; i<transition.size();i++)
            {
                Transition tr= ((Transition)transition.get(i));
                if(tr.start != oldstate)
                {
                    s +="\t\t\t\telse \n \t\t\t\t\tstate = -1; \n \t\t\t\tbreak;\n";
                    oldstate = tr.start;
                    s+="\t\t\tcase "+oldstate+":\n\t\t\t\t"+tr.toJava();
                }
                else
                    s+="\t\t\t\telse "+tr.toJava();
            }
            s +="\t\t\t\telse \n \t\t\t\t\tstate = -1; \n \t\t\t\tbreak;\n";
        }
        
        return s;
    }
    
    /**
     *
     * @author giuseppe
     */
    public class Transition {
        /** Lo stato di partenza. */
        final Integer start;
        /** Lo stato di arrivo. */
        final Integer end;
        public ArrayList<Character> label;

//        /**
//         * Crea uno stato di transizione dal punto start al punto end
//         * @param start
//         * @param end 
//         */
//        public Transition(int start, int end)
//        {
//            this.start = start;
//            this.end = end;
//            this.label= "";
//        }

        /**
        * Crea uno stato di transizione dal punto start al punto end
        * @param start
        * @param end 
        */
        public Transition(int start, int end, char ch)
        {
            this.start = start;
            this.end = end;
            this.label = new ArrayList<Character>();
            this.label.add(ch);
        }
        

         /**
         * Confronta due transizioni.
         * @param o La mossa da confrontare a questa.
         * @return <code>true</code> se le due mosse sono uguali, ovvero
         *         hanno lo stesso stato di partenza e lo stesso simbolo,
         *         <code>false</code> altrimenti.
         */
        public boolean equals(Object o) {
            if (o instanceof Transition) {
                Transition m = (Transition) o;
                return (start == m.start && end == m.end);
            } else
                return false;
        }

        public void AddLabel(char c){
            this.label.add(c);
        }
        
        /**
         * Riscrive il label con la sintassi che verrà stampata
         * es. 1-9,a ,d
         * @return La stringa trascritta
         */
        private String Trascrivi(){
            String text="";
            for(int i=0; i<label.size();i++)
            {
                text+=label.get(i);
                int j=i+1;
                if( j<label.size())
                {
                    if(label.get(j)==label.get(i)+1)
                    {
                        text+="-";
                        while(j<label.size() && label.get(j)==label.get(i+1))
                        {
                            j++;i++;
                        }
                        text+=label.get(i);
                    }
                    else
                    {
                      text+=",";
                    }
                }
            }
             return  text;   
        }
        
        public String toDot()
        {
            
            Collections.sort(label);
            String s =Trascrivi();
            return "q"+this.start+" -> q"+end+" [ label = \""+s+"\" ] \n";
        }
        
        
        public String toJava()
        {
            String s="if(";
            for(int i=0; i<label.size()-1;i++)
            {
                s+="ch == '"+label.get(i)+"' || ";
            }
            s+="ch == '"+label.get(label.size()-1)+"' ) \n"+
               "\t\t\t\t\tstate = "+end+";\n";
            return s;
        }

    }
}