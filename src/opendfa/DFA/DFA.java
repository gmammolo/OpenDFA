//Ultima versione pdf: http://informatica.i-learn.unito.it/file.php/1001/esercizi_24_11_2014.pdf
package opendfa.DFA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
    private final int[] _dpiSizes = {46, 51, 57, 63, 70, 78, 86, 96, 106, 116, 128, 141, 155, 170, 187, 206, 226, 249};

    /**
     * Define the index in the image size array.
     */
    private int _currentDpiPos = 7;

    /**
     * Increase the image size (dpi).
     */
    public void increaseDpi() {
        if (this._currentDpiPos < (this._dpiSizes.length - 1)) {
            ++this._currentDpiPos;
        }
    }

    /**
     * Decrease the image size (dpi).
     */
    public void decreaseDpi() {
        if (this._currentDpiPos > 0) {
            --this._currentDpiPos;
        }
    }

    public int getImageDpi() {
        return this._dpiSizes[this._currentDpiPos];
    }

    /**
     * Numero degli stati dell'automa. Ogni stato e` rappresentato da un numero
     * interno non negativo, lo stato con indice 0 e` lo stato iniziale.
     */
    private int _numberOfStates;

    /**
     * Insieme degli stati finali dell'automa.
     */
    private final HashSet<Integer> _finalStates;

    /**
     * Funzione di transizione dell'automa, rappresentata come una mappa da
     * mosse a stati di arrivo.
     */
    private final HashMap<Move, Integer> _transitions;

    /**
     * Crea un DFA con un dato numero di stati.
     *
     * @param n Il numero di stati dell'automa.
     */
    public DFA(int n) {
        _numberOfStates = n;
        _finalStates = new HashSet<>();
        _transitions = new HashMap<>();
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
    public boolean addMove(int p, char ch, int q) {
        return setMove(p, ch, q);
    }

    /**
     * Aggiunge uno stato all'automa.
     *
     * @return L'indice del nuovo stato creato
     */
    public int addNewState() {
        return ++_numberOfStates;
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
    public boolean setMove(int p, char start, char end, int q) {
        boolean check = true;
        if (start > end) {
            throw new IllegalArgumentException("start non può essere minore di end");
        }
        char ch = start;
        while (ch <= end) {
            check = setMove(p, ch++, q);
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
    public boolean setMove(int p, char ch, int q) {
        ArrayList<Character> chlist = new ArrayList<>();
        chlist.add(ch);
        return setMove(p, chlist, q);
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
    public boolean setMove(int p, char[] ch, int q) {
        ArrayList<Character> chlist = new ArrayList<>();
        for (Character c : ch) {
            chlist.add(c);
        }
        return setMove(p, chlist, q);
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
    public boolean setMove(int p, ArrayList<Character> ch, int q) {
        if (!isValidState(p) || !isValidState(q)) {
            return false;
        }
        Move found = null;
        for (Move m : _transitions.keySet()) {
            if (m.start == p && _transitions.get(m) == q) {
                found = m;
            }
        }
        if (found == null) {
            _transitions.put(new Move(p, ch), q);
        } else {
            _transitions.remove(found);
            found.addAlphabet(ch);
            _transitions.put(found, q);
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
    public boolean addFinalState(int p) {
        if (isValidState(p)) {
            _finalStates.add(p);
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
     * @see #_numberOfStates
     */
    public boolean isValidState(int p) {
        return (p >= -1 && p < _numberOfStates);
    }

    /**
     * Determina se uno stato e` finale oppure no.
     *
     * @param p Lo stato da controllare.
     * @return <code>true</code> se lo stato e` finale, <code>false</code>
     * altrimenti.
     * @see #_finalStates
     */
    public boolean isFinalState(int p) {
        return _finalStates.contains(p);
    }

    /**
     * Restituisce il numero di stati dell'automa.
     *
     * @return Numero di stati.
     */
    public int getNumberOfStates() {
        return _numberOfStates;
    }

    public HashSet<Integer> getAllState() {
        HashSet<Integer> result = new HashSet<>();
        for (Entry<Move, Integer> entry : _transitions.entrySet()) {
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
    public HashSet<Character> getAlphabet() {
        HashSet<Character> alphabet = new HashSet<>();
        for (Entry<Move, Integer> entry : _transitions.entrySet()) {
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
    public int move(int p, char ch) {
        for (Entry<Move, Integer> entry : _transitions.entrySet()) {
            if (entry.getKey().alphabet.contains(ch)) {
                return entry.getValue();
            }

        }
        return -1;
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
            state = move(state, ch);
        }
        return isFinalState(state);

    }

    /**
     * Stampa una rappresentazione testuale dell'automa da visualizzare con
     * <a href="http://www.graphviz.org">GraphViz</a>.
     *
     * @param name Nome dell'automa.
     * @return
     */
    public String toDOT(String name) {
        return this.toDOT(name, OUTPUT_DIR);
    }

    /**
     * Stampa una rappresentazione testuale dell'automa da visualizzare con
     * <a href="http://www.graphviz.org">GraphViz</a>.
     *
     * @param name Nome dell'automa.
     * @param OutputDir
     * @return
     */
    public String toDOT(String name, String OutputDir) {
// DA IMPLEMENTARE 2.5
        String out = "digraph " + name + "{\n";
        out += "rankdir=LR;\n";
        out += "node [shape = doublecircle];\n";
        for (Integer i : _finalStates) {
            if (i == -1) {
                out += "qe;\n";
            } else {
                out += "q" + i + ";\n";
            }

        }
        out += "node [shape = circle];\n";
        for (Move m : _transitions.keySet()) {
            out += "q" + m.start + " -> q" + _transitions.get(m) + " [ label = \"" + m.label + "\" ];\n";
        }
        out += "}";
        return out;
        //this._writeToFile(OutputDir + name + ".dot", out);
    }

    public void toPNG(String Name) {
        this.toPNG(Name, OUTPUT_DIR);

    }

    public void toPNG(String Name, String OutputDir) {
        try {
            this.toDOT(Name, TEMP_DIR);
            String dotFIleUrl = TEMP_DIR + Name + ".dot";
            Runtime rt = Runtime.getRuntime();
            String[] args = {DOT, "-Tpng", "-Gdpi=" + _dpiSizes[this._currentDpiPos], dotFIleUrl, "-o", OutputDir + Name + ".png"};
            Process p = rt.exec(args);
            p.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void _writeToFile(String filename, String context) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(context);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Stampa una classe Java con un metodo <code>scan</code> che implementa
     * l'automa.
     *
     * @param name Nome della classe da generare.
     * @return
     */
    public String toJava(String name) {
        return this.toJava(name, OUTPUT_DIR);
    }

    /**
     * Stampa una classe Java con un metodo <code>scan</code> che implementa
     * l'automa.
     *
     * @param name Nome della classe da generare.
     * @param OutputDir
     * @return
     */
    public String toJava(String name, String OutputDir) {
        String s = "public class " + name + " {\n"
                + "\tpublic static boolean scan(String s)\n"
                + "\t{\n"
                + "\t\tint state = 0;\n"
                + "\t\tint i = 0;\n"
                + "\t\twhile(state >= 0 && i < s.length()) {\n"
                + "\t\tfinal char ch = s.charAt(i++);\n"
                + "\t\tswitch(state) {\n";
        ArrayList<Move> entries = _orderByInitialState();
        Integer OldState = -1;
        for (Move move : entries) {
            Integer end = _transitions.get(move);
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
        Object[] arr = _finalStates.toArray();
        if (arr.length > 0) {
            s += "state == " + arr[0];
        }
        for (int i = 1; i < arr.length - 1; i++) {
            s += " ||  state == " + arr[i];
        }
        s += ";\n\t\t}\n";
        s += "\tpublic static void main(String[] args)\n"
                + "\t{\n"
                + "\t\tSystem.out.println(scan(args[0]) ? \"OK\" : \"NOPE\");\n"
                + "\t}\n"
                + "}\n";

        return s;
        //this._writeToFile(OutputDir + name + "_code.java", s);
    }

    /**
     * Riordina le Mosse per stato iniziale
     *
     * @return La lista delle mosse ordinate
     */
    private ArrayList<Move> _orderByInitialState() {
        ArrayList<Move> result = new ArrayList<>(_transitions.keySet());
        Collections.sort(result, (Move item1, Move item2) -> {
            return ((Integer) item1.start).compareTo(item2.start);
        });
        return result;
    }

    /**
     * ************PROBLEMI DI RAGGIUNGIBILITA'
     *
     *****************
     * @param state
     * @return
     */
    protected HashSet<Integer> _reach(Integer state) {
        if (!isValidState(state)) {
            return new HashSet<>();
        }
        Boolean[] res = new Boolean[_numberOfStates];
        for (int i = 0; i < _numberOfStates; i++) {
            res[i] = (state == i);
        }
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < _numberOfStates; i++) {
                for (Entry<Move, Integer> entry : _transitions.entrySet()) {
                    Move key = entry.getKey();
                    Integer value = entry.getValue();

                    if (res[key.start] && !res[value] && isValidState(value)) {
                        res[value] = check = true;
                    }

                }
            }
        }

        //genera hashset<integer>
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < _numberOfStates; i++) {
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
        HashSet<Integer> l = _reach(0);
        return l.isEmpty() || (l.size() == 1 && l.contains(0));

    }

    /**
     *
     * @return
     */
    public HashSet<Integer> sink() {
        HashSet<Integer> result = new HashSet<>();
        HashSet<Integer> AllStates = getAllState();
        for (Integer state : AllStates) {
            HashSet<Integer> tree = _reach(state);
            boolean check = false;
            for (Integer finalstate : _finalStates) {
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
        if (!isValidState(state)) {
            return null;
        }

        String[] res = new String[_numberOfStates];
        for (int i = 0; i < _numberOfStates; i++) {
            res[i] = (state == i) ? "" : null;
        }
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < _numberOfStates; i++) {
                for (Entry<Move, Integer> entry : _transitions.entrySet()) {
                    Move key = entry.getKey();
                    Integer value = entry.getValue();

                    if (res[key.start] != null && isValidState(value) && (res[value] == null || !res[value].contains(String.valueOf(key.alphabet)))) {
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
            if (isFinalState(i) && res[i] != null) {
                result.put(i, res[i]);
            }

        }

        return result;

    }

    public DFA minimize() {

        //1-2
        Boolean[][] eq = new Boolean[_numberOfStates][_numberOfStates];
        for (int i = 0; i < _numberOfStates; i++) {
            for (int j = 0; j < _numberOfStates; j++) {
                eq[i][j] = ((isFinalState(i) && isFinalState(j)) || (!isFinalState(i) && !isFinalState(j)));
            }
        }

        //3
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < _numberOfStates; i++) {
                for (int j = 0; j < _numberOfStates; j++) {
                    if (eq[i][j]) {
                        for (Entry<Move, Integer> entry : _transitions.entrySet()) {
                            Move key = entry.getKey();
                            for (Character c : key.alphabet) {
                                if (isValidState(move(i, c))
                                        && isValidState(move(j, c))
                                        && move(i, c) >= 0
                                        && move(j, c) >= 0
                                        && !eq[move(i, c)][move(j, c)]) {
                                    check = true;
                                    eq[i][j] = false;
                                }
                            }

                        }
                    }
                }
            }
        }

        //5
        Integer[] m = new Integer[_numberOfStates];
        for (int i = 0; i < _numberOfStates; i++) {
            boolean find = false;
            for (int j = 0; j < _numberOfStates && !find; j++) {
                if (eq[i][j]) {
                    find = true;
                    m[i] = j;
                }
            }

        }

        //6
        HashSet<Integer> reach = _reach(0);

        int k = -1, tmp_state = -1;
        for (int i = 0; i < m.length; i++) {
            if (m[i] != null && m[i] > tmp_state && reach.contains(i)) {
                tmp_state = m[i];
                k = i;
            }
        }
        HashSet<Integer> sink = sink();

        DFA B = new DFA(k + 1);
        for (Entry<Move, Integer> entry : _transitions.entrySet()) {
            Move key = entry.getKey();
            Integer value = entry.getValue();
            if (m[key.start] != null && m[value] != null && !sink.contains(m[value]) && reach.contains(m[value])) {
                B.setMove(m[key.start], key.alphabet, m[value]);
                if (isFinalState(m[key.start]) && !B.isFinalState(m[key.start])) {
                    B.addFinalState(m[key.start]);
                }
            }
        }

        return B;
    }

    public boolean equivalentTo(DFA dfa) {
        DFA minimize = this.minimize();
        DFA minimize2 = dfa.minimize();
        return (minimize._numberOfStates == minimize2._numberOfStates && _checkFinalStatesEquals(minimize._finalStates, minimize2._finalStates) && _checkTransitionsEquals(minimize._transitions, minimize2._transitions));
    }

    private boolean _checkFinalStatesEquals(HashSet<Integer> a, HashSet<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (Integer i : a) {
            if (!b.contains(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean _checkTransitionsEquals(HashMap<Move, Integer> a, HashMap<Move, Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (Entry<Move, Integer> entry : a.entrySet()) {
            if (!b.containsKey(entry.getKey()) && !b.containsValue(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public Integer[] getFinalState() {
        return _finalStates.toArray(new Integer[0]);
    }

    public ArrayList<String> getEdgeStringify() {
        ArrayList<Move> moves = this._orderByInitialState();
        ArrayList<String> result = new ArrayList<>();
        for (Move move : moves) {
            result.add("q" + move.start + " =[" + move.label + "]=> q" + _transitions.get(move));
        }
        return result;
    }

    /**
     * Metodo ausiliario per la GUI: restituisce il move nella posizione passata per permetterne la modifica
     * @param index
     * @return 
     */
    public Edge getEdgeAt(int index) {
        ArrayList<Move> moves = this._orderByInitialState();
        if (index > -1 && index < moves.size()) {
            Move m = moves.get(index);
            return new Edge(m.start, move(m.start, m.alphabet.get(0)), m.alphabet, m.label);
        }
        return null;
    }

    public void remove(Integer start, ArrayList<Character> alphabet) {
         Move remove = null;
        for (Move m : _transitions.keySet()) {
            if (m.start == start && m.alphabet.contains(alphabet.get(0))) {
                remove= m;
            }
        }
        _transitions.remove(remove);
    }

}
