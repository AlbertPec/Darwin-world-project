package agh.ics.oop.model;

import java.util.List;
import java.util.UUID;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {
    List<WorldElement> getElements();
    void move(Animal animal, int gen);
    UUID getId();
    Animal breed(Animal a1, Animal a2);
    Vector2d getuR();
    Vector2d getlL();
    boolean eatGrass(Vector2d v);
    void placeNewGrass();
}