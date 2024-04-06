package agh.ics.oop.model;

import agh.ics.oop.Simulation;

public interface MapChangeListener {
    void mapChanged(Simulation simulation, String message);
}
