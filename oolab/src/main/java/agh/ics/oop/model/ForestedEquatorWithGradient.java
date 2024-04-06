package agh.ics.oop.model;

import agh.ics.oop.model.Grass;
import agh.ics.oop.model.Vector2d;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ForestedEquatorWithGradient implements GrassBehaviour<Vector2d, Grass> {
    // Atrybuty
    private AbstractWorldMap map;
    private HashMap<Vector2d, Grass> grasses = new HashMap<Vector2d, Grass>();
    private ArrayList<Vector2d> fullGradientList = new ArrayList<Vector2d>(); // Lista od której będziemy odejmować zajęte pola aby tworzyć liste prawdopodobieństwa
    private ArrayList<Vector2d> possibleGrassesPositions; // Lista prawdopodobieństwa tworzona za pomocą fullGradientList
    private final int grassesGrowth; // Stała ilość ile traw odrośnie za każdym użyciem metody placeNewGrasses()
    private final int densification; //

    // Konstruktor klasy
    ForestedEquatorWithGradient(AbstractWorldMap map, int densification, int grassesGrowth, int amountOfGrass) {
        this.map = map;
        this.grassesGrowth = grassesGrowth;
        this.densification = densification;
        int left = this.map.getlL().getX();
        int right = this.map.getuR().getX();
        int top = this.map.getuR().getY();
        int bottom = this.map.getlL().getY();
        int width = right - left;
        int height = top - bottom;


        // Generacja fullGradientList
        for(int i = 0; i <= height/2; i++) {
            for(int j = 0; j < width; j++) {
                for(int k = 0; k < (100 + (this.densification * i))/10; k++) {
                    Vector2d newVector = new Vector2d(left + j, top - i);
                    this.fullGradientList.add(newVector);
                }
            }
            for(int j = 0; j < width; j++) {
                for(int k = 0; k < (100 + (this.densification * i))/10; k++) {
                    Vector2d newVector = new Vector2d(j, bottom + i);
                    this.fullGradientList.add(newVector);
                }
            }
        }

        this.possibleGrassesPositions = new ArrayList<>(this.fullGradientList);

        Random random = new Random();
        for(int i = 0; i < amountOfGrass; i++) {
            Vector2d vector2d = this.possibleGrassesPositions.get(random.nextInt(this.possibleGrassesPositions.size()));
            Grass newGrass = new Grass(vector2d);
            this.grasses.put(vector2d, newGrass);
            while(this.possibleGrassesPositions.contains(newGrass.getPosition())) {
                this.possibleGrassesPositions.remove(newGrass.getPosition());
            }
        }
    }

    // Metoda pomocnicza
    public void generateNewList() {
        this.possibleGrassesPositions = new ArrayList<>(this.fullGradientList);
        for(Vector2d vec : this.grasses.keySet()) {
            while(this.possibleGrassesPositions.contains(vec)) {
                this.possibleGrassesPositions.remove(vec);
            }
        }
    }

    // Usuwanie i dodawanie
    public void removeGrass(Grass grass) {
        this.grasses.remove(grass.getPosition());
        generateNewList();
    }
    public void placeNewGrasses() {
        Random random = new Random();
        for(int i = 0; i < this.grassesGrowth; i++) {
            Vector2d vector2d = this.possibleGrassesPositions.get(random.nextInt(this.possibleGrassesPositions.size()));
            Grass newGrass = new Grass(vector2d);
            this.grasses.put(vector2d, newGrass);
            while(this.possibleGrassesPositions.contains(vector2d)) {
                this.possibleGrassesPositions.remove(vector2d);
            }
        }
    }

    // Overrides
    @Override
    public HashMap<Vector2d, Grass> getGrasses() {
        return this.grasses;
    }
    @Override
    public boolean containGrass(Vector2d v) {
        return grasses.containsKey(v);
    }
    @Override
    public Grass getGrass(Vector2d v) {
        return grasses.get(v);
    }
}