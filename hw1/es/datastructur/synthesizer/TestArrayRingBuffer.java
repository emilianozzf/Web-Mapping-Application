package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());

        assertEquals(4, arb.capacity());
        assertEquals(0, arb.fillCount());

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        arb.enqueue(1.1);

        assertEquals(4, arb.capacity());
        assertEquals(4, arb.fillCount());
        assertFalse(arb.isEmpty());
        assertTrue(arb.isFull());

        assertEquals(9.3, arb.peek(), 0.01);
        assertEquals(9.3, arb.dequeue(), 0.01);
        assertEquals(4, arb.capacity());
        assertEquals(3, arb.fillCount());
    }
}
