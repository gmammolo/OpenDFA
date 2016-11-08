package main.java.com.opendfa.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import main.java.com.opendfa.DFAModel;


/**
 * Esempio. Creare un automa finito deterministico che riconosca stringhe composte da 0 e 1  con 3 zeri consegutivi
 * @author Giuseppe
 */
public class Esempio1 extends DFAModel {

    @Override
    protected void initializeDFA() {
        setMove(0, '1', 0);
        setMove(0, '0', 1);

        setMove(1, '1', 0);
        setMove(1, '0', 2);

        setMove(2, '1', 0);
        setMove(2, '0', 3);

        setMove(3, new char[]{'0', '1'}, 3);

        addFinalState(3);
    }

    @Override
    protected int numState() {
        return 4;
    }

    public static void main(String[] args) throws Exception {
        Esempio1 es = new Esempio1();
        //crea il file java
        es.writeToJava("Esempio1_Code");
        //crea il file dot
        es.writeToDot("Esempio1_Dot");
        //crea il png
        es.toPng("Esempio1");
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
