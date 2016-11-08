package main.java.com.opendfa.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import main.java.com.opendfa.DFA.RangeChar;
import main.java.com.opendfa.DFAModel;

/**
 * Esempio2. Progettare un DFA che riconosca il linguaggio delle costanti
 * numeriche in virgola mobile. Tali esempi sono: 123; 123.5; .547; +75.4; -.7;
 * 6e3; e-3; -.7e2
 *
 * @author Giuseppe
 */
public class Esempio2 extends DFAModel {

    @Override
    protected int numState() {
        return 8;
    }

    @Override
    public void initializeDFA() {
        //start
        setMove(0, RangeChar.GetDigits(), 1);
        setMove(0, RangeChar.GetPlusMinus(), 2);
        setMove(0, '.', 3);

        //un numero puro (no punti): stato finale
        setMove(1, RangeChar.GetDigits(), 1);
        setMove(1, '.', 3);
        setMove(1, 'e', 4);

        //+,-
        setMove(2, RangeChar.GetDigits(), 1);
        setMove(2, '.', 3);

        //.
        setMove(3, RangeChar.GetDigits(), 5);

        //e
        setMove(4, RangeChar.GetPlusMinus(), 6);
        setMove(4, RangeChar.GetDigits(), 7);

        //n.n: stato finale
        setMove(5, RangeChar.GetDigits(), 5);
        setMove(5, 'e', 4);

        setMove(6, RangeChar.GetDigits(), 7);

        //nen: stato finale
        setMove(7, RangeChar.GetDigits(), 7);

        addFinalState(1);
        addFinalState(5);
        addFinalState(7);

    }

    public static void main(String[] args) throws Exception {
        Esempio2 es = new Esempio2();
        //crea il file java
        es.writeToJava("Esempio2_Code");
        //crea il file dot
        es.writeToDot("Esempio2_Dot");
        //crea il png
        es.toPng("Esempio2");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.out.println("Type the line:");
        String line = reader.readLine();
        if (line == null) {
            System.out.println("Input non Accettabile");
        } else {
            //verifica se la stringa inserita è accettata dal DFA
            System.out.println("Result: " + es.scan(line));
        }
    }

}
