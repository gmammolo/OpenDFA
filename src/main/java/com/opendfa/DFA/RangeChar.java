/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa.DFA;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Giuseppe
 */
public class RangeChar {

    protected final ArrayList<Character> charList;

    public RangeChar(char[] val) {
        charList = new ArrayList<>();
        RangeChar.this.addChar(val);

    }

    public RangeChar(ArrayList<Character> val) {
        this.charList = new ArrayList<>();
        RangeChar.this.addChar(val);

    }

    public RangeChar(char start, char end) {
        this.charList = new ArrayList<>();
        addChar(start, end);
    }

    /**
     * Aggiunge un set di caratteri
     *
     * @param ch
     */
    public void addChar(char[] ch) {
        for (char c : ch) {
            if (!this.charList.contains(c)) {
                this.charList.add(c);
            }
        }
    }

    public void addChar(ArrayList<Character> ch) {
        for (char c : ch) {
            if (!this.charList.contains(c)) {
                this.charList.add(c);
            }
        }
    }

    public void addChar(char start, char end) {
        while (start <= end) {
            if (!this.charList.contains(start)) {
                this.charList.add(start);
            }
            start++;
        }

    }

    /**
     * Restituisce la lista dei caratteri
     *
     * @return
     */
    public ArrayList<Character> getCharacters() {
        return charList;
    }

    /**
     * Restituisce [0-9]
     *
     * @return
     */
    public static RangeChar GetDigits() {
        return new RangeChar('0', '9');
    }

    /**
     * restituisce [+-]
     *
     * @return
     */
    public static RangeChar GetPlusMinus() {
        return new RangeChar(new char[]{'+', '-'});
    }

    /**
     * restituisce [A-Z]
     *
     * @return
     */
    public static RangeChar GetAlphabetUppercase() {
        return new RangeChar('A', 'Z');
    }

    /**
     * restituisce [a-z]
     *
     * @return
     */
    public static RangeChar GetAlphabetLovercase() {
        return new RangeChar('a', 'z');
    }

    /**
     * restituisce [A-Za-z]
     *
     * @return
     */
    public static RangeChar GetAlphabet() {
        RangeChar result = new RangeChar('a', 'z');
        result.addChar('A', 'Z');
        return result;
    }

    /**
     * restituisce [a-zA-Z0-9]
     *
     * @return
     */
    public static RangeChar GetFullAlphabet() {
        RangeChar result = new RangeChar('a', 'z');
        result.addChar('A', 'Z');
        result.addChar('0', '9');
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.charList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RangeChar other = (RangeChar) obj;
        if (!Objects.equals(this.charList, other.charList)) {
            return false;
        }
        return true;
    }
    
}
