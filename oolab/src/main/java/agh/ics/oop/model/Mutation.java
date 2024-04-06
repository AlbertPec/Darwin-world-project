package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Mutation {
    protected int minMutatuonN=0;
    protected int maxMutatuonNr=0;
    public Genom mutate(Genom genom){
        List<Integer> g = genom.getGenom();
        List<Integer> index = new ArrayList<>();
        for(Integer i=0;i<g.size();i++){
            index.add(i);
        }
        int k = (int) ((Math.random() * (maxMutatuonNr - minMutatuonN+1)) + minMutatuonN);
        int next;
        for(int i=0;i<k;i++){
            next=(int) (Math.random() * index.size());
            changeGen(g,index.get(next));
            index.remove(next);
        }
        return new Genom(g);
    }
    abstract void changeGen(List<Integer> g, int i);
    public void setMutationBoundries(int min,int max){
        this.minMutatuonN=min;
        this.maxMutatuonNr=max;
    }
}
