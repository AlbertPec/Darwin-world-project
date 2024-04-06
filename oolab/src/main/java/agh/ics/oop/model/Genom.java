package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Genom {
    final private List<Integer> genom;

    public Genom(List<Integer> genom) {
        this.genom = genom;
    }
    public Genom(int n){
        List<Integer> newGenom = new ArrayList<>();
        for (int i=0;i<n;i++){
            Integer newG = (int) (Math.random() * 8);
            newGenom.add(newG);
        }
        this.genom=newGenom;
    }
    public List<Integer> getGenom() {
        return genom;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0;i<genom.size();i++){
            s = s + genom.get(i) +"-";
        }
        return s;
    }
}
