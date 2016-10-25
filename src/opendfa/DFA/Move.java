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
        _regenerateLabel();
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
        _regenerateLabel();
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
        _regenerateLabel();
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
            return start == m.start && ( alphabet.equals(m.alphabet) ||  alphabet.contains(m.alphabet) );
        } else {
            return false;
        }
    }

    /**
     * Aggiunge caratteri all' arco
     *
     * @param ch
     */
    public void addAlphabet(char c) {
        this.alphabet.add(c);
        _regenerateLabel();
    }

    /**
     * Aggiunge caratteri all' arco
     *
     * @param ch
     */
    public void addAlphabet(char[] ch) {
        for (char c : ch) {
            this.alphabet.add(c);
        }
        _regenerateLabel();
    }

    /**
     * Aggiunge caratteri all' arco
     *
     * @param ch
     */
    public void addAlphabet(ArrayList<Character> ch) {
        this.alphabet.addAll(ch);
        _regenerateLabel();
    }

    /**
     * genera il label da visualizzare in modo stringato
     */
    private void _regenerateLabel() {
        label = "";
        if (this.alphabet.size() <= 0) {
            return;
        } else if (alphabet.size() == 1) {
            label += alphabet.get(0);
            return;
        }

        int size = this.alphabet.size()-1;
        int i;
        for (i = 0; i < size; i++) {
            if (alphabet.get(i) == alphabet.get(i + 1) - 1) {
                label += this.alphabet.get(i);
                label += "-";
                while (i < size  && alphabet.get(i) == alphabet.get(i + 1) - 1) {
                    i++;
                }
                if (i != size) {
                    label += alphabet.get(i)+", ";
                }
            } else {
                label += alphabet.get(i) + ", ";
            }

        }
        if (i >= size ) {
            label += alphabet.get(size );
        }
    }

}
