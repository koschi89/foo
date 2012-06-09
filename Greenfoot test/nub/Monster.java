import greenfoot.*;
import java.util.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private float life;
    private static final int LIFEPOINTS = 10;
    private int count;
    
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
        
        life = LIFEPOINTS;
        
    }
        

    public void act() 
    {
        
        count ++;
        java.util.List bullets = getNeighbours(10, true, Bullet.class);
        
        for (Object bullet : bullets){
            life -= ((Bullet)bullet).getForce();
        }
        
        ArrayList<Path> pathArray=((Field)getWorld()).pathArray;
        
        if (life <=0 || (count == pathArray.size())){
            die();
            return;
        }
        
       Path path = pathArray.get(count);
       this.setLocation(path.getX(), path.getY());
     
    }    
    public void die(){
       getWorld().removeObject(this);
    }
}
