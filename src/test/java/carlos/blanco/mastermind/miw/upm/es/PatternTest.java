package carlos.blanco.mastermind.miw.upm.es;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class PatternTest {
    
    Combinations one;
    Combinations two;
    ArrayList<Combinations> tmp;
    
    @Before
    public void before() {
        one = Combinations.generateAPattern(9);
        two = Combinations.generateAPattern(7);
        tmp = new ArrayList<Combinations>();
        tmp.add(one);
        tmp.add(two);
    }

    @Test
    public void testArrayListRemove(){
        assertEquals(2,tmp.size());
        tmp.remove(tmp.indexOf(two));
        assertEquals(1,tmp.size());      
    }
    
    @Test
    public void testenerateAPattern(){
        assertEquals("(RED,RED,GREEN,YELLOW)",one.toString()); 
        assertEquals("(RED,RED,GREEN,GREEN)",two.toString()); 
    }
    
    

}
