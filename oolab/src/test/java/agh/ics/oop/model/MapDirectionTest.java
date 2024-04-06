package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void rotateTest(){
        int a = 2;
        int b = 4;
        int c = 7;
        MapDirection wzrot = MapDirection.NORTH;

        assertEquals(MapDirection.EAST,wzrot.rotate(a));
        assertEquals(MapDirection.WEST,wzrot.rotate(a+b));
        assertEquals(MapDirection.SOUTHWEST,wzrot.rotate(a+b+c));
    }
}