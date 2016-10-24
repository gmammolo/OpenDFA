package opendfa.DFA;


/**
 * Un oggetto della classe <code>Move</code> rappresenta una mossa di
 * un automa a stati finiti, ovvero una coppia costituita da uno stato
 * di partenza e da un simbolo dell'alfabeto dell'automa.
 */
public class Move
{
    /** Lo stato di partenza. */
    public final int start;
    /** Il simbolo che etichetta la transizione. */
    public final char ch;

    /**
     * Crea una mossa con stato di partenza e simbolo dati.
     * @param start Lo stato di partenza.
     * @param ch Il simbolo che etichetta la transizione.
     */
    public Move(int start, char ch) {
	this.start = start;
	this.ch = ch;
    }

    /**
     * Confronta due mosse.
     * @param o La mossa da confrontare a questa.
     * @return <code>true</code> se le due mosse sono uguali, ovvero
     *         hanno lo stesso stato di partenza e lo stesso simbolo,
     *         <code>false</code> altrimenti.
     */
    public boolean equals(Object o) {
	if (o instanceof Move) {
	    Move m = (Move) o;
	    return (start == m.start && ch == m.ch);
	} else
	    return false;
    }

    /**
     * Calcola il valore hash della mossa.
     * @return Il valore hash.
     */
    public int hashCode() {
	return start ^ (int) ch;
    }
}

