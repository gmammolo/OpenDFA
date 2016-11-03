/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

import main.java.com.opendfa.DFA.DFA;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
public class DFATest {

    public DFATest() {
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
     * Test of increaseDpi method, of class DFA.
     */
    @Test
    public void testIncreaseDpi() {
        System.out.println("IncreaseDpi");
        DFA instance = new DFA(0);
        int expResult = 106;
        instance.increaseDpi();
        assertEquals(expResult, instance.getImageDpi());
    }

    /**
     * Test of decreaseDpi method, of class DFA.
     */
    @Test
    public void testDecreaseDpi() {
        System.out.println("decreaseDpi");
        DFA instance = new DFA(0);
        int expResult = 86;
        instance.decreaseDpi();
        assertEquals(expResult, instance.getImageDpi());
    }

    /**
     * Test of getImageDpi method, of class DFA.
     */
    @Test
    public void testGetImageDpi() {
        System.out.println("getImageDpi");
        DFA instance = new DFA(0);
        int expResult = 96;
        int result = instance.getImageDpi();
        assertEquals(expResult, result);
    }

    /**
     * Test of addMove method, of class DFA.
     */
    @Test
    public void testAddMove() {
        System.out.println("AddMove");
        int p = 0;
        char ch = 'a';
        int q = 1;
        DFA instance = new DFA(2);
        boolean result = instance.addMove(p, ch, q);
        assertEquals(true, result);
        assertEquals(1, instance.move(p, ch));
        assertEquals(-1, instance.move(p, 'd'));
    }

    /**
     * Test of addNewState method, of class DFA.
     */
    @Test
    public void testAddNewState() {
        System.out.println("AddNewState");
        DFA instance = new DFA(2);
        int expResult = 3;
        int result = instance.addNewState();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMove method, of class DFA.
     */
    @Test
    public void testSetMove_4args() {
        System.out.println("SetMove");
        int p = 0;
        char start = 'a';
        char end = 'c';
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.setMove(p, start, end, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.move(p, 'a'));
        assertEquals(q, instance.move(p, 'b'));
        assertEquals(q, instance.move(p, 'c'));
        assertEquals(-1, instance.move(p, 'd'));
    }

    /**
     * Test of setMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args_1() {
        System.out.println("SetMove");
        int p = 0;
        char ch = 'a';
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.setMove(p, ch, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.move(p, 'a'));
        assertEquals(-1, instance.move(p, 'b'));
    }

    /**
     * Test of setMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args_2() {
        System.out.println("SetMove");
        int p = 0;
        char[] ch = {'a', 'b'};
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.setMove(p, ch, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.move(p, 'a'));
        assertEquals(q, instance.move(p, 'b'));
        assertEquals(-1, instance.move(p, 'c'));
    }

    /**
     * Test of setMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args_3() {
        System.out.println("SetMove");
        int p = 0;
        ArrayList<Character> ch = new ArrayList<>(Arrays.asList('a', 'b'));
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.setMove(p, ch, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.move(p, 'a'));
        assertEquals(q, instance.move(p, 'b'));
        assertEquals(-1, instance.move(p, 'c'));
    }

    /**
     * Test of addFinalState method, of class DFA.
     */
    @Test
    public void testAddFinalState() {
        System.out.println("AddFinalState");
        int p = 0;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.addFinalState(p);
        assertEquals(expResult, result);
        assertArrayEquals(new Integer[]{0}, instance.getFinalState());
    }

    /**
     * Test of addFinalState method, of class DFA.
     */
    @Test
    public void testAddFinalState2() {
        System.out.println("AddFinalState 2");
        DFA instance = new DFA(3);
        boolean expResult = true;
        boolean result = instance.addFinalState(1);
        assertEquals(expResult, result);
        result = instance.addFinalState(2);
        assertEquals(expResult, result);
        assertArrayEquals(new Integer[]{1, 2}, instance.getFinalState());
    }

    /**
     * Test of isValidState method, of class DFA.
     */
    @Test
    public void testValidState() {
        System.out.println("ValidState");
        DFA instance = new DFA(2);
        assertEquals(true, instance.isValidState(0));
        assertEquals(false, instance.isValidState(3));
        instance = new DFA(0);
        assertEquals(false, instance.isValidState(0));
        assertEquals(false, instance.isValidState(1));
    }

    /**
     * Test of isFinalState method, of class DFA.
     */
    @Test
    public void testIsFinalState() {
        System.out.println("IsFinalState");
        DFA instance = new DFA(3);
        instance.addFinalState(0);
        assertEquals(true, instance.isFinalState(0));
        assertEquals(false, instance.isFinalState(1));
    }

    /**
     * Test of getNumberOfStates method, of class DFA.
     */
    @Test
    public void testGetNumberOfStates() {
        System.out.println("GetNumberOfStates");
        DFA instance = new DFA(0);
        int expResult = 0;
        int result = instance.getNumberOfStates();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfStates method, of class DFA.
     */
    @Test
    public void testGetNumberOfStates2() {
        System.out.println("GetNumberOfStates 2");
        DFA instance = new DFA(3);
        int expResult = 3;
        int result = instance.getNumberOfStates();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllState method, of class DFA.
     */
    @Test
    public void testGetAllState() {
        System.out.println("GetAllState");
        DFA instance = new DFA(5);
        instance.addMove(0, 's', 1);
        instance.addMove(1, 'o', 2);
        instance.addMove(3, 's', 4);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4));
        HashSet<Integer> result = instance.getAllState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAlphabet method, of class DFA.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("GetAlphabet");
        DFA instance = new DFA(3);
        instance.addMove(0, '0', 1);
        instance.addMove(0, '1', 2);
        instance.addMove(1, 'a', 1);
        instance.addMove(1, '0', 0);
        HashSet<Character> expResult = new HashSet<>(Arrays.asList('0', '1', 'a'));
        HashSet<Character> result = instance.getAlphabet();
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class DFA.
     */
    @Test
    public void testMove_int_char() {
        System.out.println("Move");
        int p = 0;
        char ch = 'a';
        int expResult = 1;
        DFA instance = new DFA(2);
        instance.addMove(p, ch, expResult);
        int result = instance.move(p, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of scan method, of class DFA.
     */
    @Test
    public void testScan() {
        System.out.println("scan");
        String s = "sos";
        DFA instance = new DFA(4);
        instance.addMove(0, 's', 1);
        instance.addMove(1, 'o', 2);
        instance.addMove(2, 's', 3);
        instance.addFinalState(3);
        boolean expResult = true;
        boolean result = instance.scan(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of empty method, of class DFA.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        DFA instance = new DFA(3);
        instance.addFinalState(0);
        boolean expResult = true;
        boolean result = instance.empty();
        assertEquals(expResult, result);
    }

    /**
     * Test of equivalentTo method, of class DFA.
     */
    @Test
    public void testEquivalentTo() {
        System.out.println("equivalentTo");
        DFA dfa = new DFA(2);
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.equivalentTo(dfa);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFinalState method, of class DFA.
     */
    @Test
    public void testGetFinalState() {
        System.out.println("getFinalState");
        DFA instance = new DFA(3);
        instance.addFinalState(0);
        instance.addFinalState(2);
        Integer[] expResult = new Integer[]{0, 2};
        Integer[] result = instance.getFinalState();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getEdgeStringify method, of class DFA.
     */
    @Test
    public void testGetEdgeStringify() {
        System.out.println("getEdgeStringify");
        DFA instance = new DFA(3);
        instance.setMove(0, 'a', 1);
        instance.setMove(1, 'b', 2);
        ArrayList<String> expResult =new ArrayList<>(Arrays.asList("q0 =[a]=> q1", "q1 =[b]=> q2"));
        ArrayList<String>result = instance.getEdgeStringify();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    
    
    
        /**
     * Test of minimize method, of class DFA.
     */
    @Test
    public void testMinimize() {
        System.out.println("minimize");
        DFA instance = new DFA(7);
        instance.setMove(0, '0', 1);
        instance.setMove(0, '1', 3);
        instance.setMove(1, '0', 1);
        instance.setMove(1, '1', 2);
        instance.setMove(2, '0', 3);
        instance.setMove(2, '1', 4);
        instance.setMove(3, '0', 3);
        instance.setMove(3, '1', 4);
        instance.setMove(4, '0', 1);
        instance.setMove(4, '1', 2);
        instance.setMove(5, '0', 2);
        instance.setMove(5, '1', 6);
        instance.setMove(6, '0', 5);
        instance.setMove(6, '1', 4);
        instance.addFinalState(2);
        instance.addFinalState(4);
        DFA result = new DFA(3);
        result.setMove(0, '0', 1);
        result.setMove(0, '1', 1);
        result.setMove(1, '0', 1);
        result.setMove(1, '1', 2);
        result.setMove(2, '0', 1);
        result.setMove(2, '1', 2);
        result.addFinalState(2);
        assertEquals(true, result.equivalentTo(instance.minimize()));
    }

            /**
     * Test of minimize method, of class DFA.
     */
    @Test
    public void testMinimize2() {
        System.out.println("minimize");
        DFA result = new DFA(3);
        result.setMove(0, '0', 1);
        result.setMove(0, '1', 1);
        result.setMove(1, '0', 1);
        result.setMove(1, '1', 2);
        result.setMove(2, '0', 1);
        result.setMove(2, '1', 2);
        result.addFinalState(2);
        assertEquals(true, result.equivalentTo(result.minimize()));
    }


    /**
     * Test of equivalentTo method, of class DFA.
     */
    @Test
    public void testEquivalentoTo() {
        System.out.println("equivalentTo");
        DFA instance = new DFA(7);
        instance.setMove(0, '0', 1);
        instance.setMove(0, '1', 3);
        instance.setMove(1, '0', 1);
        instance.setMove(1, '1', 2);
        instance.setMove(2, '0', 3);
        instance.setMove(2, '1', 4);
        instance.setMove(3, '0', 3);
        instance.setMove(3, '1', 4);
        instance.setMove(4, '0', 1);
        instance.setMove(4, '1', 2);
        instance.setMove(5, '0', 2);
        instance.setMove(5, '1', 6);
        instance.setMove(6, '0', 5);
        instance.setMove(6, '1', 4);
        instance.addFinalState(2);
        instance.addFinalState(4);
        DFA result = new DFA(3);
        result.setMove(0, '0', 1);
        result.setMove(0, '1', 1);
        result.setMove(1, '0', 1);
        result.setMove(1, '1', 2);
        result.setMove(2, '0', 1);
        result.setMove(2, '1', 2);
        result.addFinalState(2);
        assertEquals(true, instance.equivalentTo(result));
    }

}
