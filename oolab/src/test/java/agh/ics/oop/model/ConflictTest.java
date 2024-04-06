package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConflictTest {
    @Test
    void basicTest(){
        Animal sowoniedzwiedz = new Animal(new Vector2d(0,0),new Genom(4),20,null,null);
        Animal ryjowka = new Animal(new Vector2d(0,0),new Genom(4),10,null,null);
        Animal kuna = new Animal(new Vector2d(0,0),new Genom(4),5,null,null);
        Conflict konflikt = new Conflict(new Vector2d(0,0));

        konflikt.addAnimal(sowoniedzwiedz);
        konflikt.addAnimal(ryjowka);
        konflikt.addAnimal(kuna);

        assertEquals(sowoniedzwiedz,konflikt.resolveConflict().get(0));
    }

}