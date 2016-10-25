/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opendfa.DFA;

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
        DFA instance = null;
        instance.IncreaseDpi();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decreaseDpi method, of class DFA.
     */
    @Test
    public void testDecreaseDpi() {
        System.out.println("decreaseDpi");
        DFA instance = null;
        instance.decreaseDpi();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImageDpi method, of class DFA.
     */
    @Test
    public void testGetImageDpi() {
        System.out.println("getImageDpi");
        DFA instance = null;
        int expResult = 0;
        int result = instance.getImageDpi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddMove method, of class DFA.
     */
    @Test
    public void testAddMove() {
        System.out.println("AddMove");
        int p = 0;
        char ch = ' ';
        int q = 0;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.AddMove(p, ch, q);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddNewState method, of class DFA.
     */
    @Test
    public void testAddNewState() {
        System.out.println("AddNewState");
        DFA instance = null;
        int expResult = 0;
        int result = instance.AddNewState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetMove method, of class DFA.
     */
    @Test
    public void testSetMove_4args() {
        System.out.println("SetMove");
        int p = 0;
        char start = ' ';
        char end = ' ';
        int q = 0;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.SetMove(p, start, end, q);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetMove method, of class DFA.
     */
    @Test
    public void testSetMove_3args() {
        System.out.println("SetMove");
        int p = 0;
        char ch = ' ';
        int q = 0;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.SetMove(p, ch, q);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddFinalState method, of class DFA.
     */
    @Test
    public void testAddFinalState() {
        System.out.println("AddFinalState");
        int p = 0;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.AddFinalState(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ValidState method, of class DFA.
     */
    @Test
    public void testValidState() {
        System.out.println("ValidState");
        int p = 0;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.ValidState(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IsFinalState method, of class DFA.
     */
    @Test
    public void testIsFinalState() {
        System.out.println("IsFinalState");
        int p = 0;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.IsFinalState(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetNumberOfStates method, of class DFA.
     */
    @Test
    public void testGetNumberOfStates() {
        System.out.println("GetNumberOfStates");
        DFA instance = null;
        int expResult = 0;
        int result = instance.GetNumberOfStates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetAllState method, of class DFA.
     */
    @Test
    public void testGetAllState() {
        System.out.println("GetAllState");
        DFA instance = null;
        HashSet<Integer> expResult = null;
        HashSet<Integer> result = instance.GetAllState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetAlphabet method, of class DFA.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("GetAlphabet");
        DFA instance = null;
        HashSet<Character> expResult = null;
        HashSet<Character> result = instance.GetAlphabet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Move method, of class DFA.
     */
    @Test
    public void testMove() {
        System.out.println("Move");
        int p = 0;
        char ch = ' ';
        DFA instance = null;
        int expResult = 0;
        int result = instance.Move(p, ch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scan method, of class DFA.
     */
    @Test
    public void testScan() {
        System.out.println("scan");
        String s = "";
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.scan(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDOT method, of class DFA.
     */
    @Test
    public void testToDOT_String() {
        System.out.println("toDOT");
        String name = "";
        DFA instance = null;
        instance.toDOT(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDOT method, of class DFA.
     */
    @Test
    public void testToDOT_String_String() {
        System.out.println("toDOT");
        String name = "";
        String OutputDir = "";
        DFA instance = null;
        instance.toDOT(name, OutputDir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDOTAlternative method, of class DFA.
     */
    @Test
    public void testToDOTAlternative_String() {
        System.out.println("toDOTAlternative");
        String name = "";
        DFA instance = null;
        instance.toDOTAlternative(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDOTAlternative method, of class DFA.
     */
    @Test
    public void testToDOTAlternative_String_String() {
        System.out.println("toDOTAlternative");
        String name = "";
        String OutputDir = "";
        DFA instance = null;
        instance.toDOTAlternative(name, OutputDir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toPNG method, of class DFA.
     */
    @Test
    public void testToPNG_String() {
        System.out.println("toPNG");
        String Name = "";
        DFA instance = null;
        instance.toPNG(Name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toPNG method, of class DFA.
     */
    @Test
    public void testToPNG_String_String() {
        System.out.println("toPNG");
        String Name = "";
        String OutputDir = "";
        DFA instance = null;
        instance.toPNG(Name, OutputDir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toJava method, of class DFA.
     */
    @Test
    public void testToJava_String() {
        System.out.println("toJava");
        String name = "";
        DFA instance = null;
        instance.toJava(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toJava method, of class DFA.
     */
    @Test
    public void testToJava_String_String() {
        System.out.println("toJava");
        String name = "";
        String OutputDir = "";
        DFA instance = null;
        instance.toJava(name, OutputDir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reach method, of class DFA.
     */
    @Test
    public void testReach() {
        System.out.println("reach");
        Integer state = null;
        DFA instance = null;
        HashSet<Integer> expResult = null;
        HashSet<Integer> result = instance.reach(state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of empty method, of class DFA.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sink method, of class DFA.
     */
    @Test
    public void testSink() {
        System.out.println("sink");
        DFA instance = null;
        HashSet<Integer> expResult = null;
        HashSet<Integer> result = instance.sink();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of samples method, of class DFA.
     */
    @Test
    public void testSamples() {
        System.out.println("samples");
        Integer state = null;
        DFA instance = null;
        HashMap<Integer, String> expResult = null;
        HashMap<Integer, String> result = instance.samples(state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minimize method, of class DFA.
     */
    @Test
    public void testMinimize() {
        System.out.println("minimize");
        DFA instance = null;
        DFA expResult = null;
        DFA result = instance.minimize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equivalentTo method, of class DFA.
     */
    @Test
    public void testEquivalentTo() {
        System.out.println("equivalentTo");
        DFA dfa = null;
        DFA instance = null;
        boolean expResult = false;
        boolean result = instance.equivalentTo(dfa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalState method, of class DFA.
     */
    @Test
    public void testGetFinalState() {
        System.out.println("getFinalState");
        DFA instance = null;
        Integer[] expResult = null;
        Integer[] result = instance.getFinalState();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEdgeStringify method, of class DFA.
     */
    @Test
    public void testGetEdgeStringify() {
        System.out.println("getEdgeStringify");
        DFA instance = null;
        String[] expResult = null;
        String[] result = instance.getEdgeStringify();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
