/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.henri.chessai.game.dataStructure;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manhenri
 */
public class HashMapTest {

    private HashMap<String, Integer> map;
    private String[] list;

    @Before
    public void setUp() {
        this.map = new HashMap<>();
        this.list = new String[]{"tall", "key", "zebra", "#themanninen", "Ten", "Singing in the rain", "sunny", "president", "bodyguard"};
    }
    
    @Test
    public void addGetTest() {
        for (int i = 0; i < 2; i++) {
            map.put(list[i], i);
        }

        for (int i = 0; i < 2; i++) {
            assertTrue(new Integer(i).equals(map.get(list[i])));
        }
    }
    
    
    @Test
    public void addOneAndGetOn() {
        map.put(list[1], 100);
        assertTrue(map.get(list[1]).equals(new Integer(100)));
    }
    
    @Test
    public void addGetmore() {
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }

        for (int i = 0; i < list.length; i++) {
            assertTrue(new Integer(i).equals(map.get(list[i])));
        }
    }
    
    @Test
    public void clearTest() {
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }
        map.clear();
        for (int i = 0; i < list.length; i++) {
            assertTrue(null == map.get(list[i]));
        }
    }   
    @Test
    public void containsKeyTest() {
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }

        for (int i = 0; i < list.length; i++) {
            assertTrue(map.containsKey(list[i]));
        }

        assertTrue(!map.containsKey("DekekekakTsadsaegf'sog+9owj243h35geih324t2+24jtwkdsnvfåäihapigräannvwrigt+oifgehdng90e3g3y30sphgaöiurigbepghaäjbtåugaebpåuapöibaög     rhåg herg håue h9ou herg eoiuh oho hå oeh oh o "
                + " er ohe oår hgåoe hrågoeh åoiher h epih oåieh eårh gåe hråoehr oe hråoi eåo0 hoå ih oåih åehoåge her "
                + " erro ghero gheo heoår hergou heour hoer håg hoåer hgoerg hoåerh oåterh o"));
    }
    
    @Test
    public void removeTest() {
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }
        
        map.remove("zebra");
        
        assertTrue(map.get("zebra") == null);
        assertTrue(map.get("bodyguard").equals(new Integer(8)));
    }
    
    @Test
    public void replaceValue() {
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }
        
        map.put("zebra", 100);
        assertTrue(map.get("zebra").equals(new Integer(100)));
    }
    
    @Test
    public void containsKeyTesting() {
        for (int i = 0; i < list.length; i++) {
            map.put(list[i], i);
        }
        char[] board = {2, 5, 0, 3, 5, 12, 14, 20, 0, 14, 12, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0, 2, 5, 6, 21, 11, 5, 7, 2, 5, 6, 21, 2, 5, 6, 21, 11, 5, 7, 2, 5, 6, 2, 5, 0, 3, 5, 12, 14, 20, 0, 14, 12, 0, 0, 0};
        
        String key = new String(board);
        map.put(key, 100);
        assertTrue(map.containsKey(key));
        assertTrue(map.containsKey("zebra"));
        assertTrue(map.containsKey("Ten"));
        assertTrue(!map.containsKey("Tan"));
        assertTrue(!map.containsKey("tul"));
        
        char[] test = {2, 5, 0, 3, 5, 12, 14, 20, 0, 14, 12, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 0, 0 ,0 ,0 ,0 ,0, 2, 5, 6, 21, 11, 5, 7, 2, 5, 6, 21, 2, 5, 6, 21, 11, 5, 7, 2, 5, 6, 2, 5, 0, 3, 5, 12, 14, 20, 0, 14, 12, 0, 0, 0};
        String t = new String(test);
        
        assertTrue(key.equals(t));
    }
}
