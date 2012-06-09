import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeadAnimal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeadAnimal extends Actor
{
private static final int START_VALUE = 10;
private int value = START_VALUE;
    /**
     * Act - do whatever the DeadAnimal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        value--;
        if (value <1)
            getWorld().removeObject(this);
    }    
}
