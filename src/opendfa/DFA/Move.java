package opendfa.DFA;

import java.util.ArrayList;

/**
 * Un oggetto della classe <code>Move</code> rappresenta una mossa di un automa
 * a stati finiti, ovvero una coppia costituita da uno stato di partenza e da un
 * simbolo dell'alfabeto dell'automa.
 */
public class Move {

    /**
     * Lo stato di partenza.
     */
    public final int start;
    /**
     * Il simbolo che etichetta la transizione.
     */
    public final ArrayList<Character> alphabet;
    public String label;

    /**
     * Crea una mossa con stato di partenza e simbolo dati.
     *
     * @param start Lo stato di partenza.
     * @param ch Il simbolo che etichetta la transizione.
     */
    public Move(int start, char[] ch) {
        this.start = start;
        this.alphabet = new ArrayList<>();
        for (char c : ch) {
            this.alphabet.add(c);
        }
        regenerateLabel();
    }

    /**
     * Crea una mossa con stato di partenza e simbolo dati.
     *
     * @param start Lo stato di partenza.
     * @param ch Il simbolo che etichetta la transizione.
     */
    public Move(int start, ArrayList<Character> ch) {
        this.start = start;
        this.alphabet = ch;
        regenerateLabel();
    }

    /**
     * Crea una mossa con stato di partenza e simbolo dati.
     *
     * @param start Lo stato di partenza.
     * @param ch Il simbolo che etichetta la transizione.
     */
    public Move(int start, char ch) {
        this.start = start;
        this.alphabet = new ArrayList<>();
        this.alphabet.add(ch);
        regenerateLabel();
    }

    /**
     * Confronta due mosse.
     *
     * @param o La mossa da confrontare a questa.
     * @return <code>true</code> se le due mosse sono uguali, ovvero hanno lo
     * stesso stato di partenza e lo stesso simbolo, <code>false</code>
     * altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Move) {
            Move m = (Move) o;
            return start == m.start && alphabet.contains(m.alphabet);
        } else {
            return false;
        }
    }

    public void AddAlphabet(char c) {
        this.alphabet.add(c);
        regenerateLabel();
    }

    public void AddAlphabet(char[] ch) {
        for (char c : ch) {
            this.alphabet.add(c);
        }
        regenerateLabel();
    }

    public void AddAlphabet(ArrayList<Character> ch) {
        this.alphabet.addAll(ch);
        regenerateLabel();
    }

    private void regenerateLabel() {
        //TODO: da abbellire
        this.label = this.alphabet.toString();
    }

}
