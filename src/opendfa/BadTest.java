/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa;

import opendfa.DFA.*;


/**
 *
 * @author Giuseppe
 */
public class BadTest extends DFAModel{

    @Override
    protected void InitializeDFA() {
        SetMove(0, '1', 0);
        SetMove(0, '0', 1);
        
        SetMove(1, '1', 0);
        SetMove(1, '0', 2);
        
        SetMove(2, '1', 0);
        SetMove(2, '0', 3);
        
        SetMove(3, new char[]{'0','1'}, 3);
        
        AddFinalState(3);
    }

    @Override
    protected int NumState() {
       return 4;
    }
    
    public static void main(String[] args) throws Exception {
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader reader = new BufferedReader(inputStreamReader);
        //System.out.println("Type the line:");
        //String line = reader.readLine();

        //toJava("Es1_3");
       // new BadTest().toJava("Test_Code");
        //new BadTest().toDotAlternative("Test_Dot");
        new BadTest().toPng("Test");
    }
    
}
