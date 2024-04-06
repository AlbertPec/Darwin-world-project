package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;

public class Simulation implements  Runnable{
    List<Animal> animals =new ArrayList<>();
    WorldMap map;
    int genomSize;
    int plantEnergy;
    int fullEnergy;
    int day = 0;
    boolean active;
    int currentActiveGen;
    List<MapChangeListener> subscribers = new ArrayList<>();
    private final Object keyPressMonitor = new Object();



    public Simulation(WorldMap map, int start_animal,
                      int plantEnergy, int fullEnergy, int genomSize, int start_energy){
        this.fullEnergy = fullEnergy;
        this.plantEnergy = plantEnergy;
        this.genomSize=genomSize;
        this.map = map;
        this.active = true;
        this.currentActiveGen = (int) ((Math.random() * (genomSize)));
        int width = map.getuR().getX();
        int height = map.getuR().getY();
        for(int i=0;i< start_animal;i++){
            Animal placeAnimal = new Animal(new Vector2d((int) (Math.random() * width),((int) (Math.random() * height))),new Genom(genomSize),start_energy);
            this.animals.add(placeAnimal);
        }
    }

    public void run(){
        while (true){
            if (active){
                synchronized (this){
                    dailyRoutine(currentActiveGen);
                    currentActiveGen++;
                    if (currentActiveGen>=genomSize) currentActiveGen=0;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void dailyRoutine(int gn){
        this.mapChanged("");
        this.day++;
        for(Animal a:animals){
            if (a.getEnergy()<=0) a.setDateOfDeath(day);
        }

        animals.removeIf(a -> a.getEnergy()<=0);// usuniecie martwych zwierzat z listy

        Map<Vector2d,Conflict> conflicts = new HashMap<>();

        for(int k=0;k<animals.size();k++){//ruch i obrot kazdeg ozwierzaka
            Animal currentAnimal = animals.get(k);
            if ( currentAnimal.getEnergy()>0){
                map.move(currentAnimal, currentAnimal.getGen(gn));

                Vector2d pos = currentAnimal.getPosition();
                if (!conflicts.containsKey(pos)){
                    conflicts.put(pos,new Conflict(pos));
                }
                conflicts.get(pos).addAnimal(currentAnimal);
            }
        }
        for(Conflict c: conflicts.values()){
            List<Animal> chads= c.resolveConflict();
            if (map.eatGrass(c.getPosition())){
                chads.get(0).eat(plantEnergy);
            }
            if (chads.size()>1){
                if (chads.get(1).getEnergy()>fullEnergy){
                    Animal newBorn = map.breed(chads.get(0),chads.get(1));
                    animals.add(newBorn);
                }
            }
        }
        map.placeNewGrass();

    }
    public int getDay() {
        return this.day;
    }

    private void mapChanged(String notice) {
        for (MapChangeListener subscriber : subscribers) {
            subscriber.mapChanged(this, notice);
        }
    }
    public void registerSubscriber(MapChangeListener newSubscriber){
        subscribers.add(newSubscriber);
    }
    public void deregisterSubscriber(MapChangeListener delSubscriber){
        subscribers.remove(delSubscriber);
    }

    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>();
        elements.addAll(animals);
        elements.addAll(map.getElements());
        return elements;
    }
    public void switchActive(){
        this.active = !active;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    public int getCurrentActiveGen() {
        return currentActiveGen;
    }
}
