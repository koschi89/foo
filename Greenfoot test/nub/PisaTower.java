 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PisaTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PisaTower extends Actor
{
    /**
     * Act - do whatever the PisaTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.getRandomNumber(3)==0) {
            getWorld().addObject(new Bullet(1, Greenfoot.getRandomNumber(359)), getX(), getY());
        }
    }    
}
