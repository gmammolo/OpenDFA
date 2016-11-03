/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

import main.java.com.opendfa.DFA.Move;
import java.util.ArrayList;
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
public class MoveTest {
    
    public MoveTest() {
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
     * Test of equals method, of class Move.
     */
    @Test
    public void testEqualsSingleChar() {
        System.out.println("equals single char");
        Object o =new  Move(1, 'c');
        Move instance = new Move(1, 'c');
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

        @Test
    public void testEqualsArrayChar() {
        System.out.println("equals array char");
        Object o =new  Move(1, new char[]{'a', 'b'} );
        Move instance = new Move(1, new char[]{'a', 'b'});
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

            @Test
    public void testEqualsArrayListChar() {
        System.out.println("equals ArrayList char");
        ArrayList<Character> cr = new ArrayList<>();
        cr.add('a'); cr.add('b');
        Object o =new  Move(1, cr );
        Move instance = new Move(1, cr);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addAlphabet method, of class Move.
     */
    @Test
    public void testAddAlphabet_char() {
        System.out.println("addAlphabet");
        char c = 'b';
        Move instance = new Move(1,'a');
        instance.addAlphabet(c);
        assertEquals(true, instance.equals(new Move(1,new char[]{'a' , 'b'})));
        
    }

    /**
     * Test of addAlphabet method, of class Move.
     */
    @Test
    public void testAddAlphabet_charArr() {
        System.out.println("addAlphabet");
        char[] ch = {'b','c'};
        Move instance = new Move(1, 'a');
        instance.addAlphabet(ch);
        assertEquals(new Move(1,new char[]{'a' , 'b', 'c'}), instance);
    }
    
        /**
     * Test of addAlphabet method, of class Move.
     */
    @Test
    public void testAddAlphabet_charArr2() {
        System.out.println("addAlphabet");
        char[] ch = {'a','b'};
        Move instance = new Move(1, new char[] { '0', '1'});
        instance.addAlphabet(ch);
        assertEquals(new Move(1,new char[]{'0', '1', 'a' , 'b'}), instance);
    }

    /**
     * Test of addAlphabet method, of class Move.
     */
    @Test
    public void testAddAlphabet_ArrayList() {
        System.out.println("addAlphabet");
        ArrayList<Character> ch = new ArrayList<>();
        ch.add('b'); ch.add('c');
        Move instance = new Move(1, 'a');
        instance.addAlphabet(ch);
        assertEquals(new Move(1,new char[]{'a' , 'b', 'c'}), instance);
    }

    /**
     * Test of equals method, of class Move.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new Move(0,'a');
        Move instance = new Move(0,'a');
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Move.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object o = new Move(0, new char[]{'a', 'b'});
        Move instance = new Move(0,new char[]{'a', 'b'});
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
        
       /**
     * Test of equals method, of class Move.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");
        Object o = new Move(0, new char[]{'b', 'a'});
        Move instance = new Move(0,new char[]{'a', 'b'});
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
        
        /**
     * Test of equals method, of class Move.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals");
        Object o = new Move(0, new char[]{'a'});
        Move instance = new Move(0,new char[]{'a', 'b'});
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
}
