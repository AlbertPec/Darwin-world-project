package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1));
    Genom genom = new Genom(list);
    Globe globe = new Globe(10, 10,10, 10, 1, 1, new AllRandom());

}