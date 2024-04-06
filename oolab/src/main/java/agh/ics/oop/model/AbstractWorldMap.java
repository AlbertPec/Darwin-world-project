package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected UUID Id;
    public int day;
    protected Vector2d lL;
    protected Vector2d uR;
    protected Mutation mutate = new LittleCorection();
    protected GrassBehaviour grassBehaviour;
    int breedEnergy;
    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<WorldElement>(grassBehaviour.getGrasses().values());
    }
    @Override
    public UUID getId() {
        return Id;
    }

    @Override
    public Animal breed(Animal a1, Animal a2){
        List<Integer> newGenom = new ArrayList<>();

        int n = a1.getGenom().size();
        int sep = (int) (n *((float) a1.getEnergy() / (a1.getEnergy()+a2.getEnergy())));
        if (Math.round( Math.random())==0){
            newGenom.addAll(a1.getGenom().subList(0,sep));
            newGenom.addAll(a2.getGenom().subList(sep,n));
        }
        else{
            newGenom.addAll(a2.getGenom().subList(0,n-sep));
            newGenom.addAll(a1.getGenom().subList(n-sep,n));
        }
        a1.updateChildren();
        a2.updateChildren();
        a1.breed(breedEnergy);
        a2.breed(breedEnergy);
        Animal newBorn = new Animal(a1.getPosition(),mutate.mutate(new Genom(newGenom)),2*breedEnergy,a1,a2);
        return newBorn;
    }
    @Override
    public Vector2d getlL() {
        return lL;
    }
    @Override
    public Vector2d getuR() {
        return uR;
    }

    @Override
    public boolean eatGrass(Vector2d v) {
        if (grassBehaviour.containGrass(v)){
            Grass eaten = grassBehaviour.getGrass(v);
            grassBehaviour.removeGrass(eaten);
            return true;
        }
        return false;
    }
    @Override
    public void placeNewGrass() {
        grassBehaviour.placeNewGrasses();
    }
}