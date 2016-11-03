/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.NFA;

import main.java.com.opendfa.NFA.NFA;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import main.java.com.opendfa.DFA.DFA;
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
public class NFATest {

    public NFATest() {
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
     * Test of newState method, of class NFA.
     */
    @Test
    public void testNewState() {
        System.out.println("newState");
        NFA instance = new NFA(0);
        int expResult = 1;
        int result = instance.newState();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFinalState method, of class NFA.
     */
    @Test
    public void testAddFinalState() {
        System.out.println("addFinalState");
        NFA instance = new NFA(4);
        assertEquals(true, instance.addFinalState(0));
        assertEquals(true, instance.addFinalState(1));
    }

    /**
     * Test of isValidState method, of class NFA.
     */
    @Test
    public void testValidState() {
        System.out.println("validState");
        NFA instance = new NFA(1);
        assertEquals(true, instance.isValidState(0));
        assertEquals(false, instance.isValidState(1));
        assertEquals(false, instance.isValidState(2));
    }

    /**
     * Test of isFinalState method, of class NFA.
     */
    @Test
    public void testFinalState() {
        System.out.println("finalState");
        NFA instance = new NFA(2);
        instance.addFinalState(0);
        assertEquals(true, instance.isFinalState(0));
        assertEquals(false, instance.isFinalState(1));
        instance.addFinalState(1);
        assertEquals(true, instance.isFinalState(0));
        assertEquals(true, instance.isFinalState(1));
    }

    /**
     * Test of getNumberOfStates method, of class NFA.
     */
    @Test
    public void testNumberOfStates() {
        System.out.println("numberOfStates");
        NFA instance = new NFA(3);
        assertEquals(3, instance.getNumberOfStates());

    }

    /**
     * Test of addMove method, of class NFA.
     */
    @Test
    public void testAddMove() {
        System.out.println("addMove");
        int p = 0;
        char ch = 'c';
        NFA instance = new NFA(3);
        assertEquals(true, instance.addMove(p, ch, 1));
        assertEquals(new HashSet<>(Arrays.asList(1)), instance.move(p, ch));
        assertEquals(true, instance.addMove(p, ch, 2));
        assertEquals(new HashSet<>(Arrays.asList(1, 2)), instance.move(p, ch));
    }

    /**
     * Test of move method, of class NFA.
     */
    @Test
    public void testMove_int_char() {
        System.out.println("move");
        int p = 0;
        char ch = 'a';
        NFA instance = new NFA(2);
        instance.addMove(p, 'a', 1);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(1));
        HashSet<Integer> result = instance.move(p, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class NFA.
     */
    @Test
    public void testMove_int_char2() {
        System.out.println("move");
        int p = 0;
        char ch = 'a';
        NFA instance = new NFA(2);
        instance.addMove(p, 'a', 1);
        instance.addMove(p, 'a', 0);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(0, 1));
        HashSet<Integer> result = instance.move(p, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class NFA.
     */
    @Test
    public void testMove_HashSet_char() {
        System.out.println("move");
        HashSet<Integer> s = new HashSet<>(Arrays.asList(0, 1));
        char ch = 'a';
        NFA instance = new NFA(4);
        instance.addMove(0, 'a', 1);
        instance.addMove(1, 'a', 2);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(1, 2));
        HashSet<Integer> result = instance.move(s, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class NFA.
     */
    @Test
    public void testMove_HashSet_char2() {
        System.out.println("move");
        HashSet<Integer> s = new HashSet<>(Arrays.asList(0, 1));
        char ch = 'a';
        NFA instance = new NFA(4);
        instance.addMove(0, 'a', 1);
        instance.addMove(1, 'b', 2);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(1));
        HashSet<Integer> result = instance.move(s, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of epsilonClosure method, of class NFA.
     */
    @Test
    public void testEpsilonClosure_HashSet() {
        System.out.println("epsilonClosure");
        HashSet<Integer> s = new HashSet<>(Arrays.asList(0, 1));
        NFA instance = new NFA(3);
        instance.addMove(0, NFA.EPSILON, 1);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(0, 1));
        HashSet<Integer> result = instance.epsilonClosure(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of epsilonClosure method, of class NFA.
     */
    @Test
    public void testEpsilonClosure_int() {
        System.out.println("epsilonClosure");
        int p = 0;
        NFA instance = new NFA(3);
        instance.addMove(0, NFA.EPSILON, 1);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(0, 1));
        HashSet<Integer> result = instance.epsilonClosure(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidState method, of class NFA.
     */
    @Test
    public void testIsValidState() {
        System.out.println("isValidState");
        NFA instance = new NFA(2);
        assertEquals(true, instance.isValidState(0));
        assertEquals(true, instance.isValidState(1));
        assertEquals(false, instance.isValidState(2));
        assertEquals(false, instance.isValidState(3));
    }

    /**
     * Test of isFinalState method, of class NFA.
     */
    @Test
    public void testIsFinalState() {
        System.out.println("isFinalState");
        NFA instance = new NFA(2);
        instance.addFinalState(0);
        assertEquals(true, instance.isFinalState(0));
        assertEquals(false, instance.isFinalState(1));
        instance.addFinalState(1);
        assertEquals(true, instance.isFinalState(1));
    }

    /**
     * Test of getNumberOfStates method, of class NFA.
     */
    @Test
    public void testGetNumberOfStates() {
        System.out.println("getNumberOfStates");
        NFA instance = new NFA(0);
        int expResult = 0;
        int result = instance.getNumberOfStates();
        assertEquals(expResult, result);
    }

    /**
     * Test of addMove method, of class NFA.
     */
    @Test
    public void testAddMove_3args_1() {
        System.out.println("addMove");
        int p = 0;
        char ch = 'a';
        int q = 1;
        NFA instance = new NFA(2);
        boolean expResult = true;
        boolean result = instance.addMove(p, ch, q);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMove method, of class NFA.
     */
    @Test
    public void testAddMove_3args_2() {
        System.out.println("addMove");
        int p = 0;
        Character[] ch = new Character[]{'a', 'b'};
        int q = 0;
        NFA instance = new NFA(1);
        boolean expResult = true;
        boolean result = instance.addMove(p, ch, q);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMove method, of class NFA.
     */
    @Test
    public void testAddMove_3args_3() {
        System.out.println("addMove");
        int p = 0;
        ArrayList<Character> ch = new ArrayList<>(Arrays.asList('a', 'b'));
        int q = 0;
        NFA instance = new NFA(1);
        boolean expResult = true;
        boolean result = instance.addMove(p, ch, q);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAlphabet method, of class NFA.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("getAlphabet");
        NFA instance = new NFA(2);
        instance.addMove(0, 'a', 0);
        instance.addMove(0, new Character[]{'a', 'b'}, 1);
        HashSet<Character> expResult = new HashSet<>(Arrays.asList('a', 'b'));
        HashSet<Character> result = instance.getAlphabet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEquivalentDFA method, of class NFA.
     */
    @Test
    public void testGetEquivalentDFA() {
        System.out.println("getEquivalentDFA");
        NFA instance = new NFA(4);
        instance.addMove(0, 'a', 1);
        instance.addMove(0, 'b', 2);
        instance.addMove(1, 'a', 2);
        instance.addMove(1, 'b', 3);
        instance.addMove(2, new Character[]{'a', 'b'}, 2);
        instance.addMove(3, new Character[]{'a', 'b'}, 0);
        instance.addFinalState(3);
        DFA expResult = new DFA(4);
        expResult.addMove(1, 'a', 0);
        expResult.addMove(1, 'b', 3);
        expResult.addMove(3, 'a', 0);
        expResult.addMove(3, 'b', 0);
        expResult.addMove(0, 'a', 1);
        expResult.addMove(0, 'a', 0);
        expResult.addMove(0, 'b', 0);
        expResult.addFinalState(3);
        DFA result = instance.getEquivalentDFA();
        assertEquals(true, result.equivalentTo(expResult));
    }
    
        /**
     * Test of getEquivalentDFA method, of class NFA.
     */
    @Test
    public void testGetEquivalentDFA2() {
        System.out.println("getEquivalentDFA");
        NFA instance = new NFA(4);
        instance.addMove(0, 'a', 1);
        instance.addMove(0, 'b', 2);
        instance.addMove(1, 'a', 2);
        instance.addMove(1, 'a', 3);
        instance.addMove(2, new Character[]{'a', 'b'}, 2);
        instance.addMove(3, new Character[]{'a', 'b'}, 0);
        instance.addFinalState(3);
        DFA expResult = new DFA(7);
        expResult.addMove(1, 'a', 3);
        expResult.addMove(6, 'b', 2);
        expResult.addMove(1, 'b', 4);
        expResult.addMove(2, 'a', 2);
        expResult.addMove(2, 'b', 2);
        expResult.addMove(3, 'a', 5);
        expResult.addMove(3, 'b', 5);
        expResult.addMove(4, 'a', 4);
        expResult.addMove(4, 'b', 4);
        expResult.addMove(5, 'a', 6);
        expResult.addMove(0, 'a', 1);
        expResult.addMove(5, 'b', 2);
        expResult.addMove(0, 'b', 2);
        expResult.addMove(6, 'a', 3);
        expResult.addFinalState(3);
        DFA result = instance.getEquivalentDFA();
        assertEquals(true, result.equivalentTo(expResult));
    }


}
