package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Vector;

public interface GrassBehaviour<P, T> {
    void removeGrass(T element);
    void placeNewGrasses();
    HashMap<P, T> getGrasses();
    boolean containGrass(Vector2d v);
    Grass getGrass(Vector2d v);
}