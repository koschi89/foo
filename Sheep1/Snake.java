import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Actor
{
    private Direction dir;
    private int full;
    private int digest;
    private boolean isDeadCheck = false;
    private static final int MAX_FULL = 300;        // Falls es den Wert überschreitet -> Fortpflanzung
    private static final int REDUCTION_FULL = 100;  // Wertabnahme für Fortpflanzung
    private static final int MIN_FULL = 80;          // Falls es den Wert unterschreitet -> Sterben 
    private static final int STAND_FULL = 200;       // Full Wert einer neuen Schlange
    private static final int EAT_SHEEP_FULL = 50;    // Wertzuwachs für das essen eines Schafes
    private static final int KIDS_FULL = 140;        // Full Wert eines Schlangenkindes
    private static final int DIGEST_COUNTER = 10;     // Anzahl Runden, die sich die Schlange nicht bewegen / nichts fressen kann
    private int SheepEaten=0;
    
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

    /**
     * Default Constructor.
     * 
     * You need to extend this to initialize the amount of food in the 
     * sheep's stomach when it is created.
     */
    public Snake ()
    {
        // By default set direction to 0-0, i.e., no motion.
        dir = new Direction(1,0);
        full = STAND_FULL;

        // Initially the sheep should have some fixed amount of food in its stomach.
    }
    /**
     * Act - do whatever the Snake wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Get a reference to the field that the snake is living on.
        Field theField = getWorld();
        if (isSnakeDigesting()){
             digest--;
         }
        else{
            // Move the snake to its new position.
            int oldX = getX();
            int oldY = getY();
            int newX = oldX + dir.rightSteps;
            int newY = oldY + dir.upSteps;
            full -=1 ;
        
        
            if(theField.hasBrickAt(newX, newY))
            {   
                bounceFromSolid(theField, oldX, oldY, newX, newY);}
            else{setLocation(newX, newY);}
                for(int i=-1; i<=1; i++){
                    for(int k=-1; k<=1; k++){
                        if(theField.hasSheepAt(newX+i, newY+k)){
                          
                                // Eat Sheep
                                theField.eatSheepAt(newX+i, newY+k); 
                                SheepEaten++;
                                full += EAT_SHEEP_FULL;
                                digest += DIGEST_COUNTER;
                }
            }
         }    
        
         full--;
        
         if (full>=MAX_FULL){
             fortPflanzung();
             full-=REDUCTION_FULL;
            }
            
         if (isDead()){
             die();
         }
       }
    }
      
       
    
     public Field getWorld()
    {
        return (Field)super.getWorld();
    }
    private void bounceFromSolid(Field theField, int oldX, int oldY, int newX, int newY)
    {
            
        if (dir.rightSteps == 0)
        {  
            // Sheep is moving only in the vertical direction
            // Flip its vertical direction of motion
            dir.upSteps *= -1;
        } else if (dir.upSteps ==0)
        {
            // Sheep is moving only in the horizontal direction
            // Flip its horzontal direction of motion
            dir.rightSteps *= -1;
        } else
        {
            // Sheep is moving along both the axes.
            
            
            // If there is no solid neighbour along the vertical direction of motion
            // The apparent wall is vertical
            // flip the horizontal direction of motion
            if (!theField.hasSolidAt(oldX, newY))
            {
                dir.rightSteps *= -1;
            }

            // If there is no solid neighbour along the horinzontal direction of motion 
            // The apparent wall is horizontal
            // flip the vertical direction of motion
            if (!theField.hasSolidAt(newX, oldY))
            {
                dir.upSteps *= -1;
            }

            // If there are solid neighbours along both the axes in the direction of motion
            // Flip both horizontal and vertical direction of motion
            if (theField.hasSolidAt(oldX, newY) && theField.hasSolidAt(newX, oldY))
            {
                dir.rightSteps *= -1;
                dir.upSteps *= -1;
            }
        }
    }
    public void fortPflanzung(){
        
        Field theWorld = getWorld();
         if (null == getOneObjectAtOffset(-1, 0, null) )
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    Snake s = new Snake();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX()-1, getY());
                }
                
                // Check if the top neighbouring location is empty.
                else if (null == getOneObjectAtOffset(0, -1, null))
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    Snake s = new Snake();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX(), getY()-1);
                }

                // Check if the bottom neighbouring location is empty.
                else if (null == getOneObjectAtOffset(0, 1, null))
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    Snake s = new Snake();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX()+1, getY());;
                }

                // Check if the right neighbouring location is empty.
                else if (null == getOneObjectAtOffset( 1, 0, null))
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    Snake s = new Snake();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX(), getY()+1);
                }
            }
    public void setFull(int a)
    {
        full = a;
    }
    public boolean isSnakeDigesting(){
        if (digest > 0)
            return true;
        else return false;
    }
    public void setDirection(boolean moveVertical, boolean moveHorizontal, boolean toTop, boolean toLeft)
    {
        // Step size is a single unit in a given direction.
        if (moveVertical) 
        {
            if (toTop)
            {
                dir.upSteps = -1;
            } else
            {
                dir.upSteps =  1;
            }
        }
        
        if (moveHorizontal) 
        {
            if (toLeft)
            {
                dir.rightSteps = -1;
            } else
            {
                dir.rightSteps =  1;
            }
        }
        
    }
    
    public boolean isDead(){
        
    if (full<MIN_FULL){
        isDeadCheck = true;
    }
    return isDeadCheck;
    }
    
    public void die(){
        Field theWorld = getWorld();
        DeadAnimal DeadAnimal = new DeadAnimal();
        theWorld.addObject(DeadAnimal, getX(), getY());
        getWorld().removeObject(this);
    }
}
