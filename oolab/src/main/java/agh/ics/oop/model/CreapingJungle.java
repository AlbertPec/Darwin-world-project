package agh.ics.oop.model;

import agh.ics.oop.model.Grass;
import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

// Roślinki najczęściej rosną obok siebie
public class CreapingJungle implements GrassBehaviour<Vector2d, Grass> {

    // Atrybuty
    private AbstractWorldMap map;
    private HashMap<Vector2d, Grass> grasses = new HashMap<Vector2d, Grass>();
    private ArrayList<Vector2d> possibleGrassesPositions = new ArrayList<>();
    private ArrayList<Vector2d> possibleNeighbouringPositions = new ArrayList<>();
    private final int reGrowth;
    private final int neighborhoodGrowthPossibility;

    // Konstruktor klasy
    CreapingJungle(AbstractWorldMap map, int neighborhoodGrowthPossibility, int reGrowth ,int amountOfGrass) {
        this.map = map;
        this.reGrowth = reGrowth;
        this.neighborhoodGrowthPossibility = neighborhoodGrowthPossibility;
        int width = map.getuR().getX();
        int height = map.getuR().getY();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                Vector2d newVector = new Vector2d(i, j);
                this.possibleGrassesPositions.add(newVector);
            }
        }
        Random random = new Random();
        for(int i = 0; i < amountOfGrass; i++) {
            Vector2d vector2d = this.possibleGrassesPositions.get(random.nextInt(possibleGrassesPositions.size()));
            Grass newGrass = new Grass(vector2d);
            this.grasses.put(vector2d, newGrass);
            generateNewLists();
        }
    }

    // Funkcje pomocnicze
    private ArrayList<Vector2d> generateNeighboursPositions(Vector2d vector2d) {
        ArrayList<Vector2d> neighbours = new ArrayList<>();
        neighbours.add(vector2d.add(new Vector2d(0, 1)));
        neighbours.add(vector2d.add(new Vector2d(1, 1)));
        neighbours.add(vector2d.add(new Vector2d(1, 0)));
        neighbours.add(vector2d.add(new Vector2d(1, -1)));
        neighbours.add(vector2d.add(new Vector2d(0, -1)));
        neighbours.add(vector2d.add(new Vector2d(-1, -1)));
        neighbours.add(vector2d.add(new Vector2d(-1, 0)));
        neighbours.add(vector2d.add(new Vector2d(-1, 1)));
        return neighbours;
    }
    private void generateNewLists() {
        ArrayList<Vector2d> possibleGrassesPositions = new ArrayList<>();
        for(int i = 0; i < this.map.getuR().getX(); i++) {
            for(int j = 0; j < this.map.getuR().getY(); j++) {
                Vector2d newVector = new Vector2d(i, j);
                possibleGrassesPositions.add(newVector);
            }
        }

        for(Vector2d vector2d : this.grasses.keySet()) {
            possibleGrassesPositions.remove(vector2d);
        }

        ArrayList<Vector2d> possibleNeighbouringPositions = new ArrayList<>();
        ArrayList<Vector2d> neighbours;
        for(Vector2d vector2d : this.grasses.keySet()) {
            neighbours = generateNeighboursPositions(vector2d);
            for(Vector2d neighbour : neighbours) {
                if(!this.possibleNeighbouringPositions.contains(neighbour)) {
                    this.possibleNeighbouringPositions.add(neighbour);
                }
                this.possibleGrassesPositions.remove(neighbour);
            }
        }
        this.possibleGrassesPositions = possibleGrassesPositions;
        this.possibleNeighbouringPositions = possibleNeighbouringPositions;
    }

    // Metoda removeGrass
    public void removeGrass(Grass grass) {
        this.grasses.remove(grass.getPosition());
        generateNewLists();
    }

    // Metoda placeNewGrasses
    public void placeNewGrasses() {
        Random random = new Random();
        for(int i = 0; i < this.reGrowth; i++) {
            if(i > (this.map.getuR().getY() - this.map.getlL().getY()) * (this.map.getuR().getX() - this.map.getlL().getX()) - this.grasses.size()) {
                break;
            }
            int temp = random.nextInt(100);
            if(!this.possibleNeighbouringPositions.isEmpty() && temp < this.neighborhoodGrowthPossibility) {
                Vector2d vector2d = this.possibleNeighbouringPositions.get(random.nextInt(this.possibleNeighbouringPositions.size()));
                Grass newGrass = new Grass(vector2d);
                this.grasses.put(vector2d, newGrass);
            } else {
                Vector2d vector2d = this.possibleGrassesPositions.get(random.nextInt(this.possibleGrassesPositions.size()));
                Grass newGrass = new Grass(vector2d);
                this.grasses.put(vector2d, newGrass);
            }
            generateNewLists();
        }
    }

    // Metoda getGrasses
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
