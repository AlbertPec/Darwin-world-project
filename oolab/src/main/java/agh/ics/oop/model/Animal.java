package agh.ics.oop.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animal implements WorldElement, Comparable<Animal>{
    private Genom genom;
    private int energy;
    private final Animal father;
    private final Animal mother;
    private int offspring;
    private int children;
    private Vector2d position;
    private MapDirection orientation;
    private int age;
    private int eatenGrass;
    private Integer dateOfDeath;
    public Animal(Vector2d nw_position,Genom genom,int energy,Animal mother, Animal father){
        this.father=father;
        this.mother=mother;
        this.genom=genom;
        this.position=nw_position;
        this.orientation=MapDirection.NORTH;
        this.energy=energy;
        this.children=0;
        this.offspring=0;
        this.eatenGrass=0;
        this.dateOfDeath=null;
    }
    public Animal(Vector2d nw_position,Genom genom,int energy){
        this(nw_position,genom,energy,null,null);

    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public String toString() {
        if (this.energy<=0)return "X";
        return switch (this.orientation){
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            case NORTHEAST -> "NE";
            case SOUTHWEST -> "SW";
            case SOUTHEAST -> "SE";
            case NORTHWEST -> "NW";
        };
    }

    public boolean isAt(Vector2d ch_position){
        return ch_position.equals(this.position);
    }

    public void move(int direction,MoveValidator possibile){
        this.energy--;
        this.age++;
        this.orientation=this.orientation.rotate(direction);
        Vector2d newPos = this.position.add(this.orientation.toUnitVector());
        if (possibile.canMoveTo(newPos)){
            this.position=newPos;
        }
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getEnergy() {
        return energy;
    }
    private void update_offspring(){
        this.offspring++;
        if (this.father!=null){
            this.father.update_offspring();
        }
        if (this.mother!=null){
            this.mother.update_offspring();
        }
    }

    public void updateChildren(){
        this.children++;
        this.update_offspring();
    }

    @Override
    public int compareTo(Animal o) {
        int result = Integer.compare(this.energy,o.energy);
        if ( result == 0 ){
            result = Integer.compare(this.age,o.age);
            if ( result == 0){
                result = Integer.compare(this.children,o.children);
                if ( result == 0){
                    return 1;
                }
            }
        }
        return result;
    }

    public void eat(int plantEnergy){
        this.eatenGrass++;
        this.energy+=plantEnergy;
    }
    public void breed(int breedingEnergy){
        this.energy-=breedingEnergy;
    }
    public int getGen(int i) {
        return genom.getGenom().get(i);
    }
    public List<Integer> getGenom() {
        return genom.getGenom();
    }
    public int getEatenGrass() {
        return eatenGrass;
    }
    public int getAge() {
        return age;
    }

    public int getChildren() {
        return children;
    }
    public int getOffspring() {
        return offspring;
    }

    public void setDateOfDeath(Integer dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    public Integer getDateOfDeath() {
        return dateOfDeath;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    public Color getColor(int fullEnergy) {
        if (this.energy >= fullEnergy)return Color.rgb(0,255,0);
        if (this.energy >= fullEnergy*0.9)return Color.rgb(51,255,0);
        if (this.energy >= fullEnergy*0.8)return Color.rgb(102,255,0);
        if (this.energy >= fullEnergy*0.7)return Color.rgb(153,255,0);
        if (this.energy >= fullEnergy*0.6)return Color.rgb(204,255,0);
        if (this.energy >= fullEnergy*0.5)return Color.rgb(255,255,0);
        if (this.energy >= fullEnergy*0.4)return Color.rgb(255,204,0);
        if (this.energy >= fullEnergy*0.3)return Color.rgb(255,153,0);
        if (this.energy >= fullEnergy*0.2)return Color.rgb(255,102,0);
        if (this.energy >= fullEnergy*0.1)return Color.rgb(255,51,0);
        return Color.rgb(255,0,0);
    }
}
