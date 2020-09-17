import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayDequeTest{
    @Test
    public void testArrayDeque(){
        ArrayDeque<Integer> testDeque= new ArrayDeque<>();
        assertTrue(testDeque.isEmpty());
        assertNull(testDeque.get(0));
        assertEquals(testDeque.size(),0);
    }
    @Test
    public void testaddFirst(){
        ArrayDeque<Integer> testDeque= new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            testDeque.addFirst(i);
        }
        assertEquals((int)testDeque.get(8),0);
        assertEquals((int)testDeque.get(0),8);
        assertNull(testDeque.get(9));
        assertEquals(testDeque.size(),9);
    }
    @Test
    public void testaddLast(){
        ArrayDeque<Integer> testDeque= new ArrayDeque<>();
        for (int i = 0; i < 17; i++) {
            testDeque.addLast(i);
        }
        assertEquals((int)testDeque.get(16),16);
        assertEquals((int)testDeque.get(0),0);
        assertNull(testDeque.get(17));
        assertEquals(testDeque.size(),17);
    }
    @Test
    public void testremoveFirst(){
        ArrayDeque<Integer> testDeque= new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            testDeque.addLast(i);
        }
        for (int i = 0; i < 4; i++) {
            assertEquals((int)testDeque.removeFirst(),i);
        }
        assertNull(testDeque.get(0));
        assertTrue(testDeque.isEmpty());
    }
    @Test
    public void testremoveLast(){
        ArrayDeque<Integer> testDeque= new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            testDeque.addFirst(i);
        }
        for (int i = 0; i < 4; i++) {
            assertEquals((int)testDeque.removeLast(),i);
        }
        assertNull(testDeque.get(0));
        assertTrue(testDeque.isEmpty());
    }
}