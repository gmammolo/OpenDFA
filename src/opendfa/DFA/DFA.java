//Ultima versione pdf: http://informatica.i-learn.unito.it/file.php/1001/esercizi_24_11_2014.pdf
package opendfa.DFA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Un oggetto della classe DFA rappresenta un automa a stati finiti
 * deterministico
 */
public class DFA {

    /**
     * Detects the client's operating system.
     */
    private final static String OS_NAME = System.getProperty("os.name").replaceAll("\\s", "");
    /**
     * Load the config.properties file.
     */
    private final static String CFG_PROP = "/home/terasud/NetBeansProjects/OpenDFA/config/config.properties";
    private final static Properties CONFIG_FILE = new Properties() {
        private final static long serialVersionUID = 1L;

        {
            try {
                load(new FileInputStream(CFG_PROP));
            } catch (IOException e) {
            }
        }
    };

    /**
     * The dir. where temporary files will be created.
     */
    private static final String OUTPUT_DIR = CONFIG_FILE.getProperty("outputDir");

    /**
     * The dir. where temporary files will be created.
     */
    private static final String TEMP_DIR = CONFIG_FILE.getProperty("tempDir");

    /**
     * Where is your dot program located? It will be called externally.
     */
    private static final String DOT = CONFIG_FILE.getProperty("dotFor" + OS_NAME);

    /**
     * The image size in dpi. 96 dpi is normal size. Higher values are 10%
     * higher each. Lower values 10% lower each.
     *
     * dpi patch by Peter Mueller
     */
    private final int[] dpiSizes = {46, 51, 57, 63, 70, 78, 86, 96, 106, 116, 128, 141, 155, 170, 187, 206, 226, 249};

    /**
     * Define the index in the image size array.
     */
    private int currentDpiPos = 7;

    /**
     * Increase the image size (dpi).
     */
    public void IncreaseDpi() {
        if (this.currentDpiPos < (this.dpiSizes.length - 1)) {
            ++this.currentDpiPos;
        }
    }

    /**
     * Decrease the image size (dpi).
     */
    public void decreaseDpi() {
        if (this.currentDpiPos > 0) {
            --this.currentDpiPos;
        }
    }

    public int getImageDpi() {
        return this.dpiSizes[this.currentDpiPos];
    }

    /**
     * Numero degli stati dell'automa. Ogni stato e` rappresentato da un numero
     * interno non negativo, lo stato con indice 0 e` lo stato iniziale.
     */
    private int numberOfStates;

    /**
     * Insieme degli stati finali dell'automa.
     */
    @SuppressWarnings("FieldMayBeFinal")
    private HashSet<Integer> finalStates;

    /**
     * Funzione di transizione dell'automa, rappresentata come una mappa da
     * mosse a stati di arrivo.
     */
    @SuppressWarnings("FieldMayBeFinal")
    private HashMap<Move, Integer> edges;

    /**
     * Crea un DFA con un dato numero di stati.
     *
     * @param n Il numero di stati dell'automa.
     */
    public DFA(int n) {
        numberOfStates = n;
        finalStates = new HashSet<>();
        edges = new HashMap<>();
    }

    /**
     * Aggiunge una transizione all' automa
     *
     * @param p stato di partenza della transizione
     * @param ch simbolo che etichetta la transizione
     * @param q stato di arrivo della transizione
     * @return <code>true</code> se lo stato di partenza e lo stato di arrivo
     * sono validi, <code>false</code> alltrimenti.
     */
    public boolean AddMove(int p, char ch, int q) {
        return SetMove(p, ch, q);
    }

    /**
     * Aggiunge uno stato all'automa.
     *
     * @return L'indice del nuovo stato creato
     */
    public int AddNewState() {
        return numberOfStates++;
    }

    /**
     * Aggiunge una serie di transizioni all' automa
     *
     * @param p Lo stato di partenza della transizione.
     * @param start Il simbolo di partenza che etichetta la transizione.
     * @param end Il simbolo di chiusura che etichetta la transizione.
     * @param q Lo stato di arrivo della transizione.
     * @return <code>true</code> se lo stato di partenza e lo stato di arrivo
     * sono validi, <code>false</code> altrimenti.
     */
    public boolean SetMove(int p, char start, char end, int q) {
        boolean check = true;
        if (start > end) {
            throw new IllegalArgumentException("start non può essere minore di end");
        }
        char ch = start;
        while (ch <= end) {
            check = SetMove(p, ch++, q);
        }
        return check;
    }

    /**
     * Aggiunge una transizione all'automa.
     *
     * @param p Lo stato di partenza della transizione.
     * @param ch Il simbolo che etichetta la transizione.
     * @param q Lo stato di arrivo della transizione.
     * @return <code>true</code> se lo stato di partenza e lo stato di arrivo
     * sono validi, <code>false</code> altrimenti.
     */
    public boolean SetMove(int p, char ch, int q) {
        ArrayList<Character> chlist = new ArrayList<>();
        chlist.add(ch);
        return SetMove(p, chlist, q);
    }

    /**
     * Aggiunge una transizione all'automa.
     *
     * @param p Lo stato di partenza della transizione.
     * @param ch Il simbolo che etichetta la transizione.
     * @param q Lo stato di arrivo della transizione.
     * @return <code>true</code> se lo stato di partenza e lo stato di arrivo
     * sono validi, <code>false</code> altrimenti.
     */
    public boolean SetMove(int p, char[] ch, int q) {
        ArrayList<Character> chlist = new ArrayList<>();
        for (Character c : ch) {
            chlist.add(c);
        }
        return SetMove(p, chlist, q);
    }

    /**
     * Aggiunge una transizione all'automa.
     *
     * @param p Lo stato di partenza della transizione.
     * @param ch Il simbolo che etichetta la transizione.
     * @param q Lo stato di arrivo della transizione.
     * @return <code>true</code> se lo stato di partenza e lo stato di arrivo
     * sono validi, <code>false</code> altrimenti.
     */
    public boolean SetMove(int p, ArrayList<Character> ch, int q) {
        if (!ValidState(p) || !ValidState(q)) {
            return false;
        }
        Move found = null;
        for (Move m : edges.keySet()) {
            if (m.start == p && edges.get(m)==q ) {
                found = m;
            }
        }
        if (found == null) {
            edges.put(new Move(p, ch), q);
        } else {
            edges.remove(found);
            found.AddAlphabet(ch);
            edges.put(found, q);
        }
        return true;
    }

    /**
     * Aggiunge uno stato finale.
     *
     * @param p Lo stato che si vuole aggiungere a quelli finali.
     * @return <code>true</code> se lo stato e` valido, <code>false</code>
     * altrimenti.
     */
    public boolean AddFinalState(int p) {
        if (ValidState(p)) {
            finalStates.add(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determina se uno stato e` valido oppure no.
     *
     * @param p Lo stato da controllare.
     * @return <code>true</code> se lo stato e` valido, <code>false</code>
     * altrimenti.
     * @see #numberOfStates
     */
    public boolean ValidState(int p) {
        return (p >= -1 && p < numberOfStates);
    }

    /**
     * Determina se uno stato e` finale oppure no.
     *
     * @param p Lo stato da controllare.
     * @return <code>true</code> se lo stato e` finale, <code>false</code>
     * altrimenti.
     * @see #finalStates
     */
    public boolean IsFinalState(int p) {
        return finalStates.contains(p);
    }

    /**
     * Restituisce il numero di stati dell'automa.
     *
     * @return Numero di stati.
     */
    public int GetNumberOfStates() {
        return numberOfStates;
    }

    public HashSet<Integer> GetAllState() {
        HashSet<Integer> result = new HashSet<>();
        for (Entry<Move, Integer> entry : edges.entrySet()) {
            result.add(entry.getKey().start);
            result.add(entry.getValue());
        }
        return result;

    }

    /**
     * Restituisce l'alfabeto dell'automa, ovvero l'insieme di simboli che
     * compaiono come etichette delle transizioni dell'automa.
     *
     * @return L'alfabeto dell'automa.
     */
    public HashSet<Character> GetAlphabet() {
        HashSet<Character> alphabet = new HashSet<Character>();
        for (Entry<Move, Integer> entry : edges.entrySet()) {
            for (Character ch : entry.getKey().alphabet) {
                alphabet.add(ch);
            }
        }
        return alphabet;
    }

    /**
     * Esegue una mossa dell'automa.
     *
     * @param p Stato di partenza prima della transizione.
     * @param ch Simbolo da riconoscere.
     * @return Stato di arrivo dopo la transizione, oppure <code>-1</code> se
     * l'automa non ha una transizione etichettata con <code>alphabet</code>
     * dallo stato <code>p</code>.
     */
    public int Move(int p, char ch) {
        Move move = new Move(p, ch);
        if (edges.containsKey(move)) {
            return edges.get(move);
        } else {
            return -1;
        }
    }

    /**
     * Esegue una mossa dell'automa.
     *
     * @param p Stato di partenza prima della transizione.
     * @param ch Simbolo da riconoscere.
     * @return Stato di arrivo dopo la transizione, oppure <code>-1</code> se
     * l'automa non ha una transizione etichettata con <code>alphabet</code>
     * dallo stato <code>p</code>.
     */
    public int Move(int p, char[] ch) {
        Move move = new Move(p, ch);
        if (edges.containsKey(move)) {
            return edges.get(move);
        } else {
            return -1;
        }
    }

    /**
     * Esegue una mossa dell'automa.
     *
     * @param p Stato di partenza prima della transizione.
     * @param ch Simbolo da riconoscere.
     * @return Stato di arrivo dopo la transizione, oppure <code>-1</code> se
     * l'automa non ha una transizione etichettata con <code>alphabet</code>
     * dallo stato <code>p</code>.
     */
    public int Move(int p, ArrayList<Character> ch) {
        Move move = new Move(p, ch);
        if (edges.containsKey(move)) {
            return edges.get(move);
        } else {
            return -1;
        }
    }

    /**
     * Verifica se una stringa e` riconosciuta dall'automa.
     *
     * @param s Stringa da riconoscere.
     * @return <code>true</code> se la stringa e` stata riconosciuta,
     * <code>false</code> altrimenti.
     */
    public boolean scan(String s) {
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            state = Move(state, ch);
        }
        return IsFinalState(state);

    }

    /**
     * Stampa una rappresentazione testuale dell'automa da visualizzare con
     * <a href="http://www.graphviz.org">GraphViz</a>.
     *
     * @param name Nome dell'automa.
     */
    public void toDOT(String name) {
        this.toDOT(name, OUTPUT_DIR);
    }

    /**
     * Stampa una rappresentazione testuale dell'automa da visualizzare con
     * <a href="http://www.graphviz.org">GraphViz</a>.
     *
     * @param name Nome dell'automa.
     */
    public void toDOT(String name, String OutputDir) {
// DA IMPLEMENTARE 2.5
        String out = "digraph " + name + "{\n";
        out += "rankdir=LR;\n";
        out += "node [shape = doublecircle];\n";
        for (Integer i : finalStates) {
            if (i == -1) {
                out += "qe;\n";
            } else {
                out += "q" + i + ";\n";
            }

        }
        out += "node [shape = circle];\n";
        for (Move m : edges.keySet()) {
            out += "q" + m.start + " -> q" + edges.get(m) + " [ label = \"" + m.label + "\" ];\n";
        }
        out += "}";
        System.out.println(out);
        this.writeToFile(OutputDir + name + ".dot", out);
    }

    public void toPNG(String Name) {
        this.toPNG(Name, OUTPUT_DIR);

    }

    public void toPNG(String Name, String OutputDir) {
        try {
            this.toDOT(Name, TEMP_DIR);
            String dotFIleUrl = TEMP_DIR + Name + ".dot";
            Runtime rt = Runtime.getRuntime();
            String[] args = {DOT, "-Tpng", "-Gdpi=" + dpiSizes[this.currentDpiPos], dotFIleUrl, "-o", OutputDir + Name + ".png"};
            Process p = rt.exec(args);
            p.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeToFile(String filename, String context) {
        try {
            PrintWriter out = new PrintWriter(filename);
            out.println(context);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Stampa una classe Java con un metodo <code>scan</code> che implementa
     * l'automa.
     *
     * @param name Nome della classe da generare.
     */
    public void toJava(String name) {
        this.toJava(name, OUTPUT_DIR);
    }

    /**
     * Stampa una classe Java con un metodo <code>scan</code> che implementa
     * l'automa.
     *
     * @param name Nome della classe da generare.
     */
    public void toJava(String name, String OutputDir) {
        String s = "public class " + name + " {\n"
                + "\tpublic static boolean scan(String s)\n"
                + "\t{\n"
                + "\t\tint state = 0;\n"
                + "\t\tint i = 0;\n"
                + "\t\twhile(state >= 0 && i < s.length()) {\n"
                + "\t\tfinal char ch = s.charAt(i++);\n"
                + "\t\tswitch(state) {\n";
        ArrayList<Move> entries = orderByInitialState();
        Integer OldState = -1;
        for (Move move : entries) {
            Integer end = edges.get(move);
            if (move.start != OldState) {
                if (OldState != -1) {
                    s += "\t\t\t\t else \n\t\t\t\t\t state = -1; \n\t\t\t break; \n";
                }
                s += "\t\t\t case " + move.start + ": \n";
                OldState = move.start;
                s += "\t\t\t\t if( ";
            } else {
                s += "\t\t\t\t else if( ";
            }
            if (move.alphabet.size() > 0) {

                for (int i = 0; i < move.alphabet.size() - 1; i++) {
                    s += " ch == " + move.alphabet.get(i) + " || ";
                }
                s += " ch == " + move.alphabet.get(move.alphabet.size() - 1);
                s += " ) \n";
                s += "\t\t\t\t\t state = " + end + ";\n";
            }

        }
        s += "\t\t\t\t else \n\t\t\t\t\t state = -1; \n\t\t\t break; \n";
        s += "\t\t\t}\n\t\t}\n";

        s += "\t\treturn ";
        Object[] arr = finalStates.toArray();
        for (int i = 0; i < finalStates.size() - 1; i++) {
            s += "state == " + arr[i] + " || ";
        }
        s += "state == " + String.valueOf(arr[arr.length - 1]) + ";\n\t\t}\n";
        s += "\tpublic static void main(String[] args)\n"
                + "\t{\n"
                + "\t\tSystem.out.println(scan(args[0]) ? \"OK\" : \"NOPE\");\n"
                + "\t}\n"
                + "}\n";

        System.out.println(s);
        this.writeToFile(OutputDir + name + "_code.java", s);
    }

    private ArrayList<Move> orderByInitialState() {
        ArrayList<Move> result = new ArrayList<>(edges.keySet());
        Collections.sort(result, new Comparator<Move>() {
            @Override
            public int compare(Move item1, Move item2) {
                return ((Integer) item1.start).compareTo(item2.start);
            }
        });
        return result;
    }

    /**
     * ************PROBLEMI DI RAGGIUNGIBILITA'*****************
     */
    protected HashSet<Integer> reach(Integer state) {
        if (!ValidState(state)) {
            return new HashSet<Integer>();
        }
        Boolean[] res = new Boolean[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            res[i] = (state == i);
        }
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < numberOfStates; i++) {
                for (Entry<Move, Integer> entry : edges.entrySet()) {
                    Move key = entry.getKey();
                    Integer value = entry.getValue();

                    if (res[key.start] && !res[value] && ValidState(value)) {
                        res[value] = check = true;
                    }

                }
            }
        }

        //genera hashset<integer>
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < numberOfStates; i++) {
            if (res[i]) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     *
     * @return boolean True se e solo se l’automa riconosce solo il linguaggio
     * vuoto
     */
    public boolean empty() {
        HashSet<Integer> l = reach(0);
        return l.isEmpty() || (l.size() == 1 && l.contains(0));

    }

    /**
     *
     */
    public HashSet<Integer> sink() {
        HashSet<Integer> result = new HashSet<Integer>();
        HashSet<Integer> AllStates = GetAllState();
        for (Integer state : AllStates) {
            HashSet<Integer> tree = reach(state);
            boolean check = false;
            for (Integer finalstate : finalStates) {
                if (tree.contains(finalstate)) {
                    check = true;
                }
            }
            if (!check) {
                result.add(state);
            }
        }
        return result;
    }

    public HashMap<Integer, String> samples(Integer state) {
        if (!ValidState(state)) {
            return null;
        }

        String[] res = new String[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            res[i] = (state == i) ? "" : null;
        }
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < numberOfStates; i++) {
                for (Entry<Move, Integer> entry : edges.entrySet()) {
                    Move key = entry.getKey();
                    Integer value = entry.getValue();

                    if (res[key.start] != null && ValidState(value) && (res[value] == null || !res[value].contains(String.valueOf(key.alphabet)))) {
                        check = true;
                        if (res[value] == null) {
                            res[value] = "";
                        }
                        res[value] += res[key.start] + String.valueOf(key.alphabet);
                    }

                }
            }
        }

        HashMap<Integer, String> result = new HashMap<>();
        for (int i = 0; i < res.length; i++) {
            if (IsFinalState(i) && res[i] != null) {
                result.put(i, res[i]);
            }

        }

        return result;

    }

    public DFA minimize() {
        //1-2
        Boolean[][] eq = new Boolean[numberOfStates][numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            for (int j = 0; j < numberOfStates; j++) {
                eq[i][j] = ((IsFinalState(i) && IsFinalState(j)) || (!IsFinalState(i) && !IsFinalState(j)));
            }
        }

        //3
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < numberOfStates; i++) {
                for (int j = 0; j < numberOfStates; j++) {
                    if (eq[i][j]) {
                        for (Entry<Move, Integer> entry : edges.entrySet()) {
                            Move key = entry.getKey();
                            //Integer value = entry.getValue();
                            if (ValidState(Move(i, key.alphabet)) && ValidState(Move(i, key.alphabet)) && Move(i, key.alphabet) >= 0 && Move(j, key.alphabet) >= 0 && !eq[Move(i, key.alphabet)][Move(j, key.alphabet)]) {
                                check = true;
                                eq[i][j] = false;
                            }
                        }
                    }
                }
            }
        }

        //5
        Integer[] m = new Integer[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            boolean find = false;
            for (int j = 0; j < numberOfStates && !find; j++) {
                if (eq[i][j]) {
                    find = true;
                    m[i] = j;
                }
            }

        }

        //6
        int k = -1, tmp_state = -1;
        for (int i = 0; i < m.length; i++) {
            if (m[i] != null && m[i] > tmp_state) {
                tmp_state = m[i];
                k = i;
            }
        }
        HashSet<Integer> sink = sink();
        DFA B = new DFA(k + 1);
        for (Entry<Move, Integer> entry : edges.entrySet()) {
            Move key = entry.getKey();
            Integer value = entry.getValue();
            if (m[key.start] != null && m[value] != null && !sink.contains(m[value])) {
                B.SetMove(m[key.start], key.alphabet, m[value]);
                if (IsFinalState(m[key.start]) && !B.IsFinalState(m[key.start])) {
                    B.AddFinalState(m[key.start]);
                }
            }
        }

        return B;
    }

    public boolean equivalentTo(DFA dfa) {
        DFA minimize = this.minimize();
        DFA minimize2 = dfa.minimize();

        return (minimize.numberOfStates == minimize2.numberOfStates && minimize.finalStates.equals(minimize2.finalStates) && minimize.edges.equals(minimize2.edges));
    }

    public Integer[] getFinalState() {
        return (Integer[]) finalStates.toArray();
    }

    public String[] getEdgeStringify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
