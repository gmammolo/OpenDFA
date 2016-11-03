/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

import main.java.com.opendfa.DFA.RangeChar;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author terasud
 */
public class RangeCharTest {

    public RangeCharTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addChar method, of class RangeChar.
     */
    @Test
    public void testAddChar_charArr() {
        System.out.println("addChar");
        char[] ch = {'d'};
        RangeChar instance = new RangeChar('a', 'c');
        instance.addChar(ch);
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        result.add('b');
        result.add('c');
        result.add('d');
        assertEquals(result, instance.getCharacters());
    }

    /**
     * Test of addChar method, of class RangeChar.
     */
    @Test
    public void testAddChar_ArrayList() {
        System.out.println("addChar");
        ArrayList<Character> ch = new ArrayList<>();
        ch.add('e');
        ch.add('f');
        RangeChar instance = new RangeChar('a', 'c');
        instance.addChar(ch);
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        result.add('b');
        result.add('c');
        result.add('e');
        result.add('f');
        assertEquals(result, instance.getCharacters());
    }

    /**
     * Test of addChar method, of class RangeChar.
     */
    @Test
    public void testAddChar_char_char() {
        System.out.println("addChar");
        char start = 'd';
        char end = 'f';
        RangeChar instance = new RangeChar('a', 'b');
        instance.addChar(start, end);
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        result.add('b');
        result.add('d');
        result.add('e');
        result.add('f');
        assertEquals(result, instance.getCharacters());
    }

    /**
     * Test of getCharacters method, of class RangeChar.
     */
    @Test
    public void testGetCharacters() {
        System.out.println("getCharacters");
        RangeChar instance = new RangeChar('a', 'c');
        ArrayList<Character> expResult = new ArrayList<>(Arrays.asList('a','b','c'));
        ArrayList<Character> result = instance.getCharacters();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetDigits method, of class RangeChar.
     */
    @Test
    public void testGetDigits() {
        System.out.println("GetDigits");
        ArrayList<Character> expResult = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        RangeChar result = RangeChar.GetDigits();
        assertEquals(expResult, result.getCharacters());
    }

    /**
     * Test of GetPlusMinus method, of class RangeChar.
     */
    @Test
    public void testGetPlusMinus() {
        System.out.println("GetPlusMinus");
        ArrayList<Character> expResult = new ArrayList<>(Arrays.asList('+', '-'));
        RangeChar result = RangeChar.GetPlusMinus();
        assertEquals(expResult, result.getCharacters());
    }

    /**
     * Test of GetAlphabetUppercase method, of class RangeChar.
     */
    @Test
    public void testGetAlphabetUppercase() {
        System.out.println("GetAlphabetUppercase");
        RangeChar expResult = new RangeChar('A','Z');
        RangeChar result = RangeChar.GetAlphabetUppercase();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetAlphabetLovercase method, of class RangeChar.
     */
    @Test
    public void testGetAlphabetLovercase() {
        System.out.println("GetAlphabetLovercase");
        RangeChar expResult = new RangeChar('a','z');
        RangeChar result = RangeChar.GetAlphabetLovercase();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetAlphabet method, of class RangeChar.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("GetAlphabet");
        RangeChar expResult = new RangeChar('a','z');
        expResult.addChar('A','Z');
        RangeChar result = RangeChar.GetAlphabet();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetFullAlphabet method, of class RangeChar.
     */
    @Test
    public void testGetFullAlphabet() {
        System.out.println("GetFullAlphabet");
        RangeChar expResult = new RangeChar('a','z');
        expResult.addChar('A','Z');
        expResult.addChar('0','9');
        RangeChar result = RangeChar.GetFullAlphabet();
        assertEquals(expResult, result);
    }

}
