package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassBehaviourTest {
    Genom genom = new Genom(new ArrayList<>(Arrays.asList(1)));

    @Test
    public void testForestedEquatorNormal() {
        // Sprawdzanie dla konkretnej wartości
        int n = 9;
        // Test
        Globe globe = new Globe(50, 50, 10, 10, 1, 0, new LittleCorection());
        for(int i = 0; i < n; i++) {
            globe.grassBehaviour.placeNewGrasses();
        }
        assertEquals(globe.grassBehaviour.getGrasses().size(), 10 + 10 * n);
    }
    @Test
    public void testCreapingJungle() {
        // Sprawdzanie dla konkretnej wartości
        int n = 5;
        Globe globe = new Globe(10, 10, 10, 10, 1, 1, new LittleCorection());
        for(int i = 0; i < n; i++) {
            globe.grassBehaviour.placeNewGrasses();
        }
        System.out.println(globe.grassBehaviour.getGrasses().size());
        assertEquals(globe.grassBehaviour.getGrasses().size(), 10 + 10 * n);
    }
    @Test
    public void testForestedEquatorWithGradient() {
        // Sprawdzanie dla konkretnej wartości
        int n = 9;
        // Test
        Globe globe = new Globe(50, 50, 10, 10, 1, 2, new LittleCorection());
        for(int i = 0; i < n; i++) {
            globe.grassBehaviour.placeNewGrasses();
        }
        assertEquals(globe.grassBehaviour.getGrasses().size(), 10 + 10 * n);
    }
}