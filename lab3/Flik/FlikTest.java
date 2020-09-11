import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest{
    @Test
    public void testFlik(){
        Integer A=1;
        Integer B=1;
        assertTrue(Flik.isSameNumber(A,B));
        A=255;
        B=255;
        assertTrue(Flik.isSameNumber(A,B));
    }
}