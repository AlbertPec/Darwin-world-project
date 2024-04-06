package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;
    public Vector2d(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        String r=String.format("(%d,%d)",this.x,this.y);
        return r;
    }
    public boolean precedes(Vector2d other){
        if(this.x<=other.x && this.y<=other.y)return true;
        else return false;
    }
    public boolean follows(Vector2d other){
        if(this.x>=other.x && this.y>=other.y)return true;
        else return false;
    }
    public Vector2d add(Vector2d other){
        Vector2d ret = new Vector2d(this.x+other.x,this.y+other.y);
        return ret;
    }
    public Vector2d subtract(Vector2d other){
        Vector2d ret = new Vector2d(this.x-other.x,this.y-other.y);
        return ret;
    }
    public Vector2d upperRight(Vector2d other){
        int new_x;
        int new_y;
        if(this.x>other.x)new_x=this.x;
        else new_x= other.x;
        if(this.y>other.y)new_y=this.y;
        else new_y= other.y;
        Vector2d ret = new Vector2d(new_x,new_y);
        return ret;
    }
    public Vector2d lowerLeft(Vector2d other){
        int new_x;
        int new_y;
        if(this.x<other.x)new_x=this.x;
        else new_x= other.x;
        if(this.y<other.y)new_y=this.y;
        else new_y= other.y;
        Vector2d ret = new Vector2d(new_x,new_y);
        return ret;
    }
    public Vector2d oposite(){
        Vector2d ret = new Vector2d(-this.x,-this.y);
        return ret;
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if(this.x==that.x && this.y==that.y)return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
