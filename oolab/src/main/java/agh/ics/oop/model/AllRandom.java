package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class AllRandom extends Mutation{
    @Override
    void changeGen(List<Integer> g, int i){
        Integer newG = (int) (Math.random() * 8);
        g.set(i,newG);
    }
}
