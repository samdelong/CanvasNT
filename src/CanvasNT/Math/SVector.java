package CanvasNT.Math;

public class SVector{
    float x;
    float y;
    public SVector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float mag(){

        return (float) Math.sqrt((x*x + y*y));

    }

    public void setMag(float nm){
        float ratio = Math.abs(nm/mag());
        x *= ratio;
        y *= ratio;
    }

}