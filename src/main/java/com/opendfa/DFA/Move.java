package main.java.com.opendfa.DFA;

import java.util.ArrayList;
import java.util.Objects;

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
    public Move(int start, Character[] ch) {
        this.start = start;
        this.alphabet = new ArrayList<>();
        for (char c : ch) {
            if (!this.alphabet.contains(c)) {
                this.alphabet.add(c);
            }
        }
        _regenerateLabel();
    }

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
            if (!this.alphabet.contains(c)) {
                this.alphabet.add(c);
            }
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
        this.alphabet = new ArrayList<>();
        for (char c : ch) {
            if (!this.alphabet.contains(c)) {
                this.alphabet.add(c);
            }
        }
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
        if (!this.alphabet.contains(ch)) {
            this.alphabet.add(ch);
        }

        _regenerateLabel();
    }

    /**
     * Confronta due mosse.
     *
     * @param o La mossa da confrontare a questa.
     * @return <code>true</code> se le due mosse sono uguali, ovvero hanno lo
     * stesso stato di partenza e gli stessi simboli, <code>false</code>
     * altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Move) {
            Move m = (Move) o;
            Boolean contain = m.alphabet.size() == alphabet.size() && start == m.start;
            if (contain) {
                for (char c : m.alphabet) {
                    if (!alphabet.contains(c)) {
                        return false;
                    }
                }
            }
            return contain ;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.start;
        hash = 67 * hash + Objects.hashCode(this.alphabet);
        return hash;
    }

    /**
     * Aggiunge caratteri all' arco.
     *
     * @param c carattere da aggiungere
     */
    public void addAlphabet(char c) {
        if (!this.alphabet.contains(c)) {
            this.alphabet.add(c);
        }
        _regenerateLabel();
    }

    /**
     * Aggiunge caratteri all' arco.
     *
     * @param ch caratteri da aggiungere
     */
    public void addAlphabet(char[] ch) {
        for (char c : ch) {
            if (!this.alphabet.contains(c)) {
                this.alphabet.add(c);
            }
        }
        _regenerateLabel();
    }

    /**
     * Aggiunge caratteri all' arco.
     *
     * @param ch caratteri da aggiungere
     */
    public void addAlphabet(ArrayList<Character> ch) {
        for (char c : ch) {
            if (!this.alphabet.contains(c)) {
                this.alphabet.add(c);
            }
        }
        _regenerateLabel();
    }

    /**
     * genera il label da visualizzare in modo stringato.
     */
    private void _regenerateLabel() {
        label = "";
        if (this.alphabet.size() <= 0) {
            return;
        } else if (alphabet.size() == 1) {
            label += alphabet.get(0);
            return;
        }

        int size = this.alphabet.size() - 1;
        int i;
        for (i = 0; i < size; i++) {
            if (alphabet.get(i) == alphabet.get(i + 1) - 1) {
                label += this.alphabet.get(i);
                label += "-";
                while (i < size && alphabet.get(i) == alphabet.get(i + 1) - 1) {
                    i++;
                }
                if (i != size) {
                    label += alphabet.get(i) + ", ";
                }
            } else {
                label += alphabet.get(i) + ", ";
            }

        }
        if (i >= size) {
            label += alphabet.get(size);
        }
    }

}
