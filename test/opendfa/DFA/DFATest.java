/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

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
     * Test of IncreaseDpi method, of class DFA.
     */
    @Test
    public void testIncreaseDpi() {
        System.out.println("IncreaseDpi");
        DFA instance = new DFA(0);
        int expResult = 106;
        instance.IncreaseDpi();
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
     * Test of AddMove method, of class DFA.
     */
    @Test
    public void testAddMove() {
        System.out.println("AddMove");
        int p = 0;
        char ch = 'a';
        int q = 1;
        DFA instance = new DFA(2);
        boolean result = instance.AddMove(p, ch, q);
        assertEquals(true, result);
        assertEquals(1, instance.Move(p, ch));
        assertEquals(-1, instance.Move(p, 'd'));
    }

    /**
     * Test of AddNewState method, of class DFA.
     */
    @Test
    public void testAddNewState() {
        System.out.println("AddNewState");
        DFA instance = new DFA(2);
        int expResult = 3;
        int result = instance.AddNewState();
        assertEquals(expResult, result);
    }

    /**
     * Test of SetMove method, of class DFA.
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
        boolean result = instance.SetMove(p, start, end, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.Move(p, 'a'));
        assertEquals(q, instance.Move(p, 'b'));
        assertEquals(q, instance.Move(p, 'c'));
        assertEquals(-1, instance.Move(p, 'd'));
    }

    /**
     * Test of SetMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args_1() {
        System.out.println("SetMove");
        int p = 0;
        char ch = 'a';
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.SetMove(p, ch, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.Move(p, 'a'));
        assertEquals(-1, instance.Move(p, 'b'));
    }

    /**
     * Test of SetMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args_2() {
        System.out.println("SetMove");
        int p = 0;
        char[] ch = {'a', 'b'};
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.SetMove(p, ch, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.Move(p, 'a'));
        assertEquals(q, instance.Move(p, 'b'));
        assertEquals(-1, instance.Move(p, 'c'));
    }

    /**
     * Test of SetMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args_3() {
        System.out.println("SetMove");
        int p = 0;
        ArrayList<Character> ch = new ArrayList<>(Arrays.asList('a', 'b'));
        int q = 1;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.SetMove(p, ch, q);
        assertEquals(expResult, result);
        assertEquals(q, instance.Move(p, 'a'));
        assertEquals(q, instance.Move(p, 'b'));
        assertEquals(-1, instance.Move(p, 'c'));
    }

    /**
     * Test of AddFinalState method, of class DFA.
     */
    @Test
    public void testAddFinalState() {
        System.out.println("AddFinalState");
        int p = 0;
        DFA instance = new DFA(2);
        boolean expResult = true;
        boolean result = instance.AddFinalState(p);
        assertEquals(expResult, result);
        assertArrayEquals(new Integer[]{0}, instance.getFinalState());
    }

    /**
     * Test of AddFinalState method, of class DFA.
     */
    @Test
    public void testAddFinalState2() {
        System.out.println("AddFinalState 2");
        DFA instance = new DFA(3);
        boolean expResult = true;
        boolean result = instance.AddFinalState(1);
        assertEquals(expResult, result);
        result = instance.AddFinalState(2);
        assertEquals(expResult, result);
        assertArrayEquals(new Integer[]{1, 2}, instance.getFinalState());
    }

    /**
     * Test of ValidState method, of class DFA.
     */
    @Test
    public void testValidState() {
        System.out.println("ValidState");
        DFA instance = new DFA(2);
        assertEquals(true, instance.ValidState(0));
        assertEquals(false, instance.ValidState(3));
        instance = new DFA(0);
        assertEquals(true, instance.ValidState(0));
        assertEquals(false, instance.ValidState(1));
    }

    /**
     * Test of IsFinalState method, of class DFA.
     */
    @Test
    public void testIsFinalState() {
        System.out.println("IsFinalState");
        DFA instance = new DFA(3);
        instance.AddFinalState(0);
        assertEquals(true, instance.IsFinalState(0));
        assertEquals(false, instance.IsFinalState(1));
    }

    /**
     * Test of GetNumberOfStates method, of class DFA.
     */
    @Test
    public void testGetNumberOfStates() {
        System.out.println("GetNumberOfStates");
        DFA instance = new DFA(0);
        int expResult = 0;
        int result = instance.GetNumberOfStates();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetNumberOfStates method, of class DFA.
     */
    @Test
    public void testGetNumberOfStates2() {
        System.out.println("GetNumberOfStates 2");
        DFA instance = new DFA(3);
        int expResult = 3;
        int result = instance.GetNumberOfStates();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetAllState method, of class DFA.
     */
    @Test
    public void testGetAllState() {
        System.out.println("GetAllState");
        DFA instance = new DFA(4);
        instance.AddMove(0, 's', 1);
        instance.AddMove(1, 'o', 2);
        instance.AddMove(3, 's', 4);
        HashSet<Integer> expResult = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4));
        HashSet<Integer> result = instance.GetAllState();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetAlphabet method, of class DFA.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("GetAlphabet");
        DFA instance = new DFA(3);
        instance.AddMove(0, '0', 1);
        instance.AddMove(0, '1', 2);
        instance.AddMove(1, 'a', 1);
        instance.AddMove(1, '0', 0);
        HashSet<Character> expResult = new HashSet<>(Arrays.asList('0', '1', 'a'));
        HashSet<Character> result = instance.GetAlphabet();
        assertEquals(expResult, result);
    }

    /**
     * Test of Move method, of class DFA.
     */
    @Test
    public void testMove_int_char() {
        System.out.println("Move");
        int p = 0;
        char ch = 'a';
        int expResult = 1;
        DFA instance = new DFA(2);
        instance.AddMove(p, ch, expResult);
        int result = instance.Move(p, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of Move method, of class DFA.
     */
    @Test
    public void testMove_int_charArr() {
        System.out.println("Move");
        int p = 0;
        char[] ch = new char[]{'a', 'b'};
        DFA instance = new DFA(2);
        int expResult = 1;
        for (char c : ch) {
            instance.AddMove(p, c, expResult);
        }
        int result = instance.Move(p, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of Move method, of class DFA.
     */
    @Test
    public void testMove_int_ArrayList() {
        System.out.println("Move");
        int p = 0;
        ArrayList<Character> ch = new ArrayList<>(Arrays.asList('a', 'b'));
        DFA instance = new DFA(2);
        int expResult = 1;
        for (char c : ch) {
            instance.AddMove(p, c, expResult);
        }
        int result = instance.Move(p, ch);
        assertEquals(expResult, result);
    }

    /**
     * Test of scan method, of class DFA.
     */
    @Test
    public void testScan() {
        System.out.println("scan");
        String s = "sos";
        DFA instance = new DFA(3);
        instance.AddMove(0, 's', 1);
        instance.AddMove(1, 'o', 2);
        instance.AddMove(2, 's', 3);
        instance.AddFinalState(3);
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
        instance.AddFinalState(0);
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
        DFA instance = new DFA(2);
        instance.AddFinalState(0);
        instance.AddFinalState(2);
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
        DFA instance = new DFA(2);
        instance.SetMove(0, 'a', 1);
        instance.SetMove(1, 'b', 2);
        String[] expResult = new String[]{"0 => 1 [a]", "1 =>b [b]"};
        String[] result = instance.getEdgeStringify();
        assertArrayEquals(expResult, result);
    }

}
