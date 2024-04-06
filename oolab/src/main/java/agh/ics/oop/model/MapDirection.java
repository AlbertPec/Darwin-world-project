package agh.ics.oop.model;

import java.util.Map;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST
    ;

    public String toString() {
        return switch (this) {
            case NORTH -> "Polnoc";
            case NORTHWEST -> "Polnocny zachod";
            case SOUTH -> "Poludnie";
            case SOUTHEAST -> "Poudniowy wschod";
            case EAST -> "Wschod";
            case SOUTHWEST -> "Poudniowy zachod";
            case WEST -> "Zachod";
            case NORTHEAST -> "Pounocny wschod";
        };
    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case NORTHEAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTHWEST -> new Vector2d(-1,1);
        };
    }

    public MapDirection rotate(int r){
        int a = this.ordinal() + r;
        if (a > 7) a-=8;
        return getValue(a);
    }
    private MapDirection getValue(int i){
        return switch (i){
            case 0 -> MapDirection.NORTH;
            case 1 -> MapDirection.NORTHEAST;
            case 2 -> MapDirection.EAST;
            case 3 -> MapDirection.SOUTHEAST;
            case 4 -> MapDirection.SOUTH;
            case 5 -> MapDirection.SOUTHWEST;
            case 6 -> MapDirection.WEST;
            case 7 -> MapDirection.NORTHWEST;
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

}
