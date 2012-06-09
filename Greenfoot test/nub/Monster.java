import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monster extends Actor
{
    private Direction dir;
    private boolean isDeadCheck = false;
    private int life;
    private static final int LIFEPOINTS = 10;
    
    public static class Direction 
    { 
        int upSteps;    
        int rightSteps; 

        public Direction(int aUp, int aRight)
        {
            this.upSteps = aUp;
            this.rightSteps = aRight;
        }

    };
    public Monster(){
        dir = new Direction(0,1);
        life = LIFEPOINTS;
    }
        

    public void act() 
    {
        if (life <=0)
            die();
           
    }    
    public void die(){
        //Field theWorld = getWorld();
       //theWorld.addObject(DeadAnimal, getX(), getY());
        //getWorld().removeObject(this);
    }
}
