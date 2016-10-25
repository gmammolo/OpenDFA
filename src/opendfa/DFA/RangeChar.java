/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

/**
 *
 * @author Giuseppe
 */
public class RangeChar {
    public char start;
    public char end;
    public char val[];
    
    public RangeChar(char[] val)
    {
        this.val = val;
    }
    public RangeChar(char start, char end) {
        this.start = start;
        this.end = end;
        val= new char[0];
    }
    
    public static RangeChar getDigits() {
        return new RangeChar('0', '9');
    }
    
    public static RangeChar getPlusMinus()
    {
        return new RangeChar(new char[]{'+', '-'});
    }
    
    public static RangeChar getAlphabetUppercase() 
    {
        return new RangeChar('A','Z');
    }
    
        public static RangeChar getAlphabetLovercase() 
    {
        return new RangeChar('a','z');
    }
            
}
