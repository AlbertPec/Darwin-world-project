package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conflict {
    private Vector2d position;
    private List<Animal> conflictedAnimals;
    public Conflict(Vector2d position){
        this.position = position;
        this.conflictedAnimals= new ArrayList<>();
    }
    public void addAnimal(Animal animal){
        this.conflictedAnimals.add(animal);
    }
    public List<Animal> resolveConflict(){
        int n = conflictedAnimals.size();
        if ( n == 1) return conflictedAnimals;
        Collections.sort(conflictedAnimals);
        return List.of(conflictedAnimals.get(n-1),conflictedAnimals.get(n-2));
    }

    public Vector2d getPosition() {
        return position;
    }
}
