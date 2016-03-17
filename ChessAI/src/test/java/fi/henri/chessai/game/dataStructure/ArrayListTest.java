/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.dataStructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author KavioElain
 */
public class ArrayListTest {

    private ArrayList<Integer> list;
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    @Before
    public void setUp() {
        this.list = new ArrayList<Integer>();
        this.a = 0;
        this.b = 1;
        this.c = 2;
        this.d = 3;
        this.e = 4;
    }

    @Test
    public void addOne() {
        list.add(a);
        assertEquals(1, list.size());
    }

    @Test
    public void addTestIncrease() {
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        assertEquals(5, list.size());
    }

    @Test
    public void getTest() {
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        for (int i = 0; i < 5; i++) {
            int x = i;
            int y = list.get(i);
            assertEquals(x, y);
        }
    }

    @Test
    public void clearTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.clear();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(3, list.size());
    }
    
    @Test
    public void addAllTest() {
        ArrayList<Integer> test = new ArrayList<Integer>();
        
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        
        for (int i = 10; i < 15; i++) {
            test.add(i);
        }
        
        list.addAll(test);
        
        for (int i = 0; i < 15; i++) {
            int y = list.get(i);
            int x = i;
            assertEquals(x, y);
        }
    }
    
    @Test
    public void removePrecisionTest() {
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        
        list.remove(3);
        
        int test1 = list.get(0);
        int test2 = list.get(1);
        
        assertEquals(null, list.get(3));
    }
    
    @Test
    public void containsTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertTrue(list.contains(8));
        assertTrue(!list.contains(10));
        
    }
    
    @Test
    public void clearingTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        
        assertEquals(10, list.size());
        
        list.clear();
        
        assertTrue(list.isEmpty());
        assertEquals(2, list.getArrayLenght());
    }
}
