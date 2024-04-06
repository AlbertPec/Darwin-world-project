package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Statitics implements MapChangeListener {
    private double averageEnergy;
    private double averageLifeSpan;
    private double averageChildren;
    private int grassNr;
    private int animalNr;
    private int occupiedArea;
    private Set<Animal> animalHistory;
    public Statitics() {
        this.animalHistory = new HashSet<>();
        this.grassNr=0;
        this.animalNr=0;
        this.occupiedArea=0;
    }

    @Override
    public void mapChanged(Simulation simulation, String message) {
        List<Animal> animals = simulation.getAnimals();
        List<WorldElement> elements = simulation.getElements();
        animalHistory.addAll(animals);
        this.animalNr = animals.size();
        this.grassNr = elements.size();

        Set<Vector2d> pos = new HashSet<>();
        for(Animal a:animals){
            pos.add(a.getPosition());
        }
        for(WorldElement e:elements){
            pos.add(e.getPosition());
        }
        this.occupiedArea = pos.size();
        this.average();;
    }

    private void average(){
        double il = 0;
        double ic = 0;
        double ie = 0;
        double avl = 0;
        double avc = 0;
        double ave = 0;

        for (Animal a:animalHistory){
            if (a.getEnergy()<=0){
                il++;
                avl+=a.getAge();
            }
            else {
                ic++;
                avc+=a.getChildren();
                ie++;
                ave+=a.getEnergy();
            }
        }
        this.averageLifeSpan = avl/il;
        this.averageChildren = avc/ic;
        this.averageEnergy = ave/ie;
    }

    public double getAverageChildren() {
        return averageChildren;
    }

    public double getAverageEnergy() {
        return averageEnergy;
    }

    public double getAverageLifeSpan() {
        return averageLifeSpan;
    }

    public int getAnimalNr() {
        return animalNr;
    }

    public int getGrassNr() {
        return grassNr;
    }

    public int getOccupiedArea() {
        return occupiedArea;
    }
}
