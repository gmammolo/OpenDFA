//Ultima versione pdf: http://informatica.i-learn.unito.it/file.php/1001/esercizi_24_11_2014.pdf
package main.java.com.opendfa.NFA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;
import main.java.com.opendfa.DFA.DFA;
import main.java.com.opendfa.DFA.Move;

/**
 * Un oggetto della classe NFA rappresenta un automa a stati finiti non
 * deterministico con epsilon transizioni
 */
public class NFA {

    /**
     * Usiamo il carattere nullo per rappresentare una epsilon transizione
     */
    public static final char EPSILON = '\0';

    /**
     * Numero degli stati dell'automa. Ogni stato e` rappresentato da un numero
     * interno non negativo, lo stato con indice 0 e` lo stato iniziale.
     */
    private int _numberOfStates;

    /**
     * Insieme degli stati finali dell'automa.
     */
    private HashSet<Integer> _finalStates;

    /**
     * Funzione di transizione dell'automa, rappresentata come una mappa da
     * mosse a insiemi di stati di arrivo.
     */
    private HashMap<Move, HashSet<Integer>> _transitions;

    /**
     * Crea un NFA con un dato numero di stati.
     *
     * @param n Il numero di stati dell'automa.
     */
    public NFA(int n) {
        _numberOfStates = n;
        _finalStates = new HashSet<Integer>();
        _transitions = new HashMap<Move, HashSet<Integer>>();
    }

    /**
     * Aggiunge uno stato all'automa.
     *
     * @return L'indice del nuovo stato creato
     */
    public int newState() {
        return ++_numberOfStates;
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
        return (p >= 0 && p < _numberOfStates);
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

    /**
     * Aggiunge una transizione all'automa.
     *
     * @param p Lo stato di partenza della transizione.
     * @param ch Il simbolo che etichetta la transizione.
     * @param q Lo stato di arrivo della transizione.
     * @return <code>true</code> se lo stato di partenza e lo stato di arrivo
     * sono validi, <code>false</code> altrimenti.
     */
    public boolean addMove(int p, char ch, int q) {
        if (!isValidState(p) || !isValidState(q)) {
            return false;
        }
        Move move = null;
        for (Entry<Move, HashSet<Integer>> entry : _transitions.entrySet()) {
            if (entry.getKey().start == p && (entry.getValue().contains(q) || entry.getKey().alphabet.contains(ch))) {
                move = entry.getKey();

            }
        }
        if (move == null) {
            HashSet<Integer> arrivo = new HashSet<>();
            arrivo.add(q);
            _transitions.put(new Move(p, ch), arrivo);
        } else {
            HashSet<Integer> value = _transitions.remove(move);
            value.add(q);
            move.addAlphabet(ch);
            _transitions.put(move, value);
        }
        return true;
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
    public boolean addMove(int p, Character[] ch, int q) {
        boolean result = true;
        for (char c : ch) {
            result = addMove(p, c, q) && result;
        }
        return result;
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
    public boolean addMove(int p, ArrayList<Character> ch, int q) {
        return addMove(p, ch.toArray(new Character[0]), q);
    }

    /**
     * Determina se c'e` uno stato finale in un insieme di stati.
     *
     * @param s L'insieme di stati da controllare.
     * @return <code>true</code> se c'e` uno stato finale in <code>s</code>,
     * <code>false</code> altrimenti.
     * @see #_finalStates
     */
    private boolean _findAFinalState(HashSet<Integer> s) {
        if (s.size() <= 0) {
            return false;
        }
        for (Integer p : s) {
            if (isFinalState(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Restituisce l'alfabeto dell'automa, ovvero l'insieme di simboli che
     * compaiono come etichette delle transizioni dell'automa. Notare che
     * <code>EPSILON</code> non e` un simbolo.
     *
     * @return L'alfabeto dell'automa.
     */
    public HashSet<Character> getAlphabet() {
        HashSet<Character> alphabet = new HashSet<Character>();
        for (Move m : _transitions.keySet()) {
            if (!m.alphabet.contains(NFA.EPSILON)) {
                alphabet.addAll(m.alphabet);
            }
        }
        return alphabet;
    }

    /**
     * Esegue una mossa dell'automa.
     *
     * @param p Stato di partenza prima della transizione.
     * @param ch Simbolo da riconoscere.
     * @return Insieme di stati di arrivo dopo la transizione. Questo insieme
     * puo` essere vuoto.
     */
    public HashSet<Integer> move(int p, char ch) {
        Move move = new Move(p, ch);
        for (Entry<Move, HashSet<Integer>> entry : _transitions.entrySet()) {
            if (entry.getKey().start == p && entry.getKey().alphabet.contains(ch)) {
                return entry.getValue();
            }
        }
        return new HashSet<>();

    }

    /**
     * Esegue una mossa dell'automa.
     *
     * @param s Insieme di stati di partenza prima della transizione.
     * @param ch Simbolo da riconoscere.
     * @return Insieme di stati di arrivo dopo la transizione. Questo insieme
     * puo` essere vuoto.
     */
    public HashSet<Integer> move(HashSet<Integer> s, char ch) {
        HashSet<Integer> endstate = new HashSet<>();
        for (Integer p : s) {
            for (Entry<Move, HashSet<Integer>> entry : _transitions.entrySet()) {
                if (entry.getKey().start == p && entry.getKey().alphabet.contains(ch)) {
                    for (Integer end : entry.getValue()) {
                        endstate.add(end);
                    }
                }
            }

        }
        return endstate;

    }

    /**
     * Calcola la epsilon chiusura di un insieme di stati dell'automa.
     *
     * @param s Insieme di stati di cui calcolare l'epsilon chiusura.
     * @return Insieme di stati raggiungibili da quelli contenuti in
     * <code>s</code> per mezzo di zero o piu` epsilon transizioni.
     */
    public HashSet<Integer> epsilonClosure(HashSet<Integer> s) {
        boolean[] r = new boolean[_numberOfStates];
        for (int i = 0; i < r.length; i++) {
            r[i] = s.contains(i);
        }
        boolean modificato;
        do {
            modificato = false;
            for (int i = 0; i < r.length; i++) {
                if (r[i]) {
                    for (Move m : _transitions.keySet()) {
                        if (m.start == i && m.alphabet.contains(NFA.EPSILON)) {
                            for (int j : _transitions.get(m)) {
                                if (!r[j]) {
                                    r[j] = modificato = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (modificato);
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < r.length; i++) {
            if (r[i]) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Calcola la epsilon chiusura di uno stato dell'automa. E` un caso
     * specifico del metodo precedente.
     *
     * @param p Insieme di cui calcolare l'epsilon chiusura.
     * @return Insieme di stati raggiungibili da <code>p</code> per mezzo di
     * zero o piu` epsilon transizioni.
     * @see #epsilonClosure
     */
    public HashSet<Integer> epsilonClosure(int p) {
        HashSet a = new HashSet<>();
        a.add(p);
        return epsilonClosure(a);

    }

    /**
     * Calcola l'automa a stati finiti deterministico equivalente.
     *
     * @return DFA equivalente.
     */
    public DFA getEquivalentDFA() {
        // la costruzione del DFA utilizza due tabelle hash per tenere
// traccia della corrispondenza (biunivoca) tra insiemi di
// stati del NFA e stati del DFA
        HashMap<HashSet<Integer>, Integer> indexOfSet
                = new HashMap<HashSet<Integer>, Integer>(); // NFA -> DFA
        HashMap<Integer, HashSet<Integer>> setOfIndex
                = new HashMap<Integer, HashSet<Integer>>(); // DFA -> NFA
        DFA dfa = new DFA(0); // il DFA
        Stack<Integer> newStates = new Stack<Integer>(); // nuovi stati del DFA
        HashSet<Character> alphabet = getAlphabet();
        int q0 = dfa.addNewState() - 1; // stato iniziale del DFA
        indexOfSet.put(epsilonClosure(0), q0); // stati dell'NFA corrisp. a q0
        setOfIndex.put(q0, epsilonClosure(0));
        newStates.push(q0); // nuovo stato da esplorare
        while (!newStates.empty()) { // finche' ci sono nuovi stati da visitare
            final int p = newStates.pop(); // ne considero uno e lo visito
            final HashSet<Integer> pset = setOfIndex.get(p); // stati del NFA corrisp.
            for (char ch : alphabet) { // considero tutte le possibili transizioni
                HashSet<Integer> tmp = move(pset, ch);
                HashSet<Integer> qset = epsilonClosure(tmp);
                if (indexOfSet.containsKey(qset)) { // se qset non e` nuovo...
                    final int q = indexOfSet.get(qset); // recupero il suo indice
                    dfa.setMove(p, ch, q); // aggiungo la transizione
                } else { // se invece qset e` nuovo
                    final int q = dfa.addNewState() - 1; // creo lo stato nel DFA
                    indexOfSet.put(qset, q); // aggiorno la corrispondenza
                    setOfIndex.put(q, qset);
                    newStates.push(q); // q e` da visitare
                    dfa.setMove(p, ch, q); // aggiungo la transizione
                }

            }
        }
// stabilisco gli stati finali del DFA
        for (int p = 0; p < setOfIndex.size(); p++) {
            if (_findAFinalState(setOfIndex.get(p))) {
                dfa.addFinalState(p);
            }
        }
        return dfa;

    }

    public void toDot(String name) {
        String text = "digraph " + name + " {\n"
                + "rankdir=LR;\n"
                + "node [shape = doublecircle];\n";
        for (Integer c : _finalStates) {
            text += " q" + c + "; ";
        }
        text += "\n node [shape = circle];\n";
        for (Entry<Move, HashSet<Integer>> entry : _transitions.entrySet()) {
            for (Integer end : entry.getValue()) {
                text += "q" + entry.getKey().start + " -> q" + end + "[label = \"" + entry.getKey().alphabet + "\" ] \n";
            }
        }

        System.out.println(text);
    }

    public int append(NFA a) {
        final int n = _numberOfStates;
        _numberOfStates += a.getNumberOfStates();
        for (Move m : a._transitions.keySet()) {
            for (int q : a._transitions.get(m)) {

                addMove(n + m.start, m.alphabet, n + q);
            }
        }
        return n;
    }

    private void _toDOT(String name) {
        String out = "digraph " + name + "{\n";
        out += "rankdir=LR;\n";
        out += "node [shape = doublecircle];\n";
        for (Integer i : _finalStates) {
            out += "q" + i + ";\n";
        }
        out += "node [shape = circle];\n";
        for (Move m : _transitions.keySet()) {
            for (int i : _transitions.get(m)) {
                out += "q" + m.start + " -> q" + i + " [ label = \"" + (!m.alphabet.contains(NFA.EPSILON) ? m.alphabet : "ɛ") + "\" ];\n";
            }
        }
        out += "}";
        System.out.println(out);
    }

}
