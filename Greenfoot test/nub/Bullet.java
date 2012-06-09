import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{   
    
    float force;
    
    public Bullet(float force, int rotation){
        this.force = force;
        this.setRotation(rotation);
    }
    
    public float getForce(){
        return force;
    }
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (this.getIntersectingObjects(Brick.class).size() > 0){
            getWorld().removeObject(this);
        }
        this.move(10);
    }    
}
