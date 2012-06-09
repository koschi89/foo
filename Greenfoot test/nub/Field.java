 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a field of grass populated by a flock of sheep. 
 * You do not need to change this.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Field extends World
{

    // Width of the sheep-field in cell-uints.
    public static final int FLD_WID = 50;

    // Height of the sheep-field in cell-uints.
    public static final int FLD_HGH = 38;

 
    /**
     * Constructor.
     */
    public Field()
    {    
        // Create a new world with given width and height in cell-units. Set size of a cell size to 16x16 pixels.
        super(FLD_WID, FLD_HGH, 16); 

        // Add bricks to top 3 rows in the field.
        for (int i=0;i < 3 ;++i) {
            for (int j= 0; j < FLD_WID; ++j) {
                Brick bTemp = new Brick();
                addObject(bTemp, j,i);
            }
        }

        // Add bricks to the leftmost and rightmost columns of the field.
        for (int j=3; j < FLD_HGH; ++j) {
            Brick bTemp = new Brick();
            addObject(bTemp, 0, j);

            bTemp = new Brick();
            addObject(bTemp, FLD_WID-1,j);

        }
        
        // Add bricks to last row in the field.
        for (int j= 0; j < FLD_WID; ++j) {
            Brick bTemp = new Brick();
            addObject(bTemp, j,FLD_HGH-1);
        }
        
        int towerX = this.getWidth()/3;
        int towerY = this.getHeight()/2;
        
        PisaTower pt1 = new PisaTower();
        addObject(pt1, towerX, towerY);
        
        PisaTower pt2 = new PisaTower();
        addObject(pt2, 2*towerX, towerY);
        
        // Make counter

        // Perepare field as intialized using the visual interface for Greenfoot.
   //     prepare();
    }

     /**
     * This function checks if a given location in the field is empty or occupied.
     * Input :
     *  x - x-index in cell units for given location.
     *  y - y-index in cell units for given location.
     *  
     *  Output : A boolean flag indicating if given location is empty or occupied.
     *  true  - empty
     *  false - occupied
     */
    public boolean isEmpty(int x, int y)
    {
        java.util.List lTemp = getObjectsAt(x, y, null);

        return lTemp.isEmpty();
    }

    /**
     * This function checks if a given location has a brick  or not.
     * Input :
     *  x - x-index in cell units for given location.
     *  y - y-index in cell units for given location.
     *  
     *  Output : A boolean flag indicating if the presence/absence of a brick.
     *  true  - a brick present at given location.
     *  false - no brick present at given location.
     */
    public boolean hasBrickAt(int x, int y)
    {
        java.util.List lTemp = getObjectsAt(x, y, Brick.class);

        return !lTemp.isEmpty();
    }
    
}





