package carlos.blanco.mastermind.miw.upm.es;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class PatternTest {
    
    Pattern one;
    Pattern two;
    ArrayList<Pattern> tmp;
    
    @Before
    public void before() {
        one = Pattern.generateAPattern(9);
        two = Pattern.generateAPattern(7);
        tmp = new ArrayList<Pattern>();
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
