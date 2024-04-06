package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void equalTest(){
        Vector2d A = new Vector2d(2,2);
        Vector2d B = new Vector2d(2,2);
        Vector2d C = new Vector2d(0,0);

        assertTrue(A.equals(B));
        assertFalse(A.equals(C));
    }
    @Test
    void toStringTest(){
        Vector2d A = new Vector2d(2,2);
        assertEquals("(2,2)",A.toString());
    }

    @Test
    void precedesTest(){
        Vector2d A = new Vector2d(0,1);
        Vector2d B = new Vector2d(3,3);
        Vector2d C = new Vector2d(0,2);
        Vector2d D = new Vector2d(-1,-2);
        assertTrue(A.precedes(B));
        assertTrue(A.precedes(C));
        assertTrue(A.precedes(A));
        assertFalse(A.precedes(D));
    }

    @Test
    void followsTest(){
        Vector2d A = new Vector2d(3,3);
        Vector2d B = new Vector2d(0,1);
        Vector2d C = new Vector2d(3,2);
        Vector2d D = new Vector2d(4,7);
        assertTrue(A.follows(B));
        assertTrue(A.follows(C));
        assertTrue(A.follows(A));
        assertFalse(A.follows(D));
    }

    @Test
    void upperRightTest(){
        Vector2d A = new Vector2d(-1,4);
        Vector2d B = new Vector2d(7,-2);
        Vector2d C = new Vector2d(7,4);

        assertEquals(C,A.upperRight(B));
    }

    @Test
    void lowerLEftTest(){
        Vector2d A = new Vector2d(-1,4);
        Vector2d B = new Vector2d(7,-2);
        Vector2d C = new Vector2d(-1,-2);

        assertEquals(C,A.lowerLeft(B));
    }

    @Test
    void addTest(){
        Vector2d A = new Vector2d(-1,4);
        Vector2d B = new Vector2d(3,-2);
        Vector2d C = new Vector2d(2,2);

        assertEquals(C,A.add(B));
    }

    @Test
    void subtractTest(){
        Vector2d A = new Vector2d(-1,4);
        Vector2d B = new Vector2d(3,-2);
        Vector2d C = new Vector2d(-4,6);

        assertEquals(C,A.subtract(B));
    }

    @Test
    void oppositeTest(){
        Vector2d A = new Vector2d(-4,7);
        Vector2d B = new Vector2d(4,-7);

        assertEquals(B,A.oposite());
    }
}