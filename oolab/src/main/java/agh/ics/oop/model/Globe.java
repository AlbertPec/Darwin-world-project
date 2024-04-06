package agh.ics.oop.model;


import java.util.*;

public class Globe extends AbstractWorldMap{
    public Globe(int width, int height, int growthNr, int start_grass, int breedingEnergy, int grassBehaviourType, Mutation mutation) {
        this.mutate=mutation;

        this.lL = new Vector2d(0, 0);
        this.uR = new Vector2d(width, height);
        this.Id = UUID.randomUUID();

        if(grassBehaviourType == 0) {
            this.grassBehaviour = new ForestedEquatorNormal(this, growthNr , start_grass);
        } else if(grassBehaviourType == 1) {
            this.grassBehaviour = new CreapingJungle(this,80,growthNr,start_grass);
        } else if(grassBehaviourType == 2) {
            this.grassBehaviour = new ForestedEquatorWithGradient(this, 80, growthNr , start_grass);
        }

        this.breedEnergy = breedingEnergy;
        this.day=0;

    }
    // gettery
    public int getWidth() {
        return this.uR.getX();
    }
    public int getHeight() {
        return this.uR.getY();
    }
    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<WorldElement>();
        elements.addAll(new ArrayList<WorldElement>(this.grassBehaviour.getGrasses().values()));
        return elements;
    }
    // helpery
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(position.getY() > this.uR.getY() - 1 || position.getY() < this.lL.getY());
    }
    // move
    @Override
    public void move(Animal animal, int direction) {
        animal.move(direction, this);
        Vector2d position = animal.getPosition();
        if(position.getX() >= this.uR.getX()) {
            animal.setPosition(new Vector2d(this.lL.getX(), animal.getPosition().getY()));
        }
        if(position.getX() < this.lL.getX()) {
            animal.setPosition(new Vector2d(this.uR.getX()-1, animal.getPosition().getY()));
        }
    }
}
