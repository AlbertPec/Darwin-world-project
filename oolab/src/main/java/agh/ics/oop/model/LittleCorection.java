package agh.ics.oop.model;

import java.util.List;

public class LittleCorection extends Mutation{
    @Override
    void changeGen(List<Integer> g, int i) {
        Integer old = g.get(i);
        if(Math.round( Math.random())==1){
            if(old==7) g.set(i,0);
            else g.set(i,old+1);
        }
        else {
            if (old==0) g.set(i,7);
            else g.set(i,old-1);
        }
    }
}
