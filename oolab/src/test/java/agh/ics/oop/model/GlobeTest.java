package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobeTest {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1));
    Genom genom = new Genom(list);
    Globe globe = new Globe(10, 10,10, 10, 1, 0, new LittleCorection());

    @Test
    public void testInterior() {
        Vector2d vector2d = new Vector2d(0, 0);
        Animal animal = new Animal(vector2d, genom , 1, null, null);
        globe.move(animal, 1);
        assertEquals(new Vector2d(1, 1), animal.getPosition());
    }
    @Test
    public void testLowerBorder() {
        Vector2d vector2d = new Vector2d(globe.lL.getX(), globe.lL.getY());
        Animal animal = new Animal(vector2d, genom , 1, null, null);
        globe.move(animal, 4);
        assertEquals(globe.lL, animal.getPosition());
    }
    @Test
    public void testUpperBorder() {
        Vector2d vector2d = new Vector2d(globe.uR.getX(), globe.uR.getY());
        Animal animal = new Animal(vector2d, genom , 1, null, null);
        globe.move(animal, 0);
        assertEquals(globe.uR, animal.getPosition());
    }
    @Test
    public void testCircumnavigation() {
        Vector2d uR = new Vector2d(globe.uR.getX(), globe.uR.getY());
        Vector2d uL = new Vector2d(globe.lL.getX(), globe.uR.getY());
        Vector2d lL = new Vector2d(globe.lL.getX(), globe.lL.getY());
        Vector2d lR = new Vector2d(globe.uR.getX(), globe.lL.getY());
        Animal first = new Animal(uR, genom , 1, null, null);
        Animal second = new Animal(lL, genom , 1, null, null);
        globe.move(first, 2);
        globe.move(second, 6);
        assertEquals(first.getPosition(), uL);
        assertEquals(second.getPosition(), lR);
    }
}
