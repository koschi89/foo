import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CarrionBeetle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CarrionBeetle extends Actor
{
    private Direction dir;
    private float full;
    private boolean isDeadCheck = false;
    private boolean isEating = false;
    private static final float MAX_FULL = 20;        // Falls es den Wert überschreitet -> Fortpflanzung
    private static final float REDUCTION_FULL = 10;  // Wertabnahme für Fortpflanzung
    private static final float MIN_FULL = 0;          // Falls es den Wert unterschreitet -> Sterben 
    private static final float STAND_FULL = 10;       // Full Wert eines neuen Käfers
    private static final float KIDS_FULL = 10;        // Full Wert eines Käferkindes
    
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
     * Bug's stomach when it is created.
     */
    public CarrionBeetle ()
    {
        // By default set direction to 0-0, i.e., no motion.
        dir = new Direction(1,0);
        full = STAND_FULL;

        // Initially the Bug should have some fixed amount of food in its stomach.
    }
    /**
     * Act - do whatever the Bug wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Get a reference to the field that the Bug is living on.
        Field theField = getWorld();

            // Move the Bug to its new position.
            int oldX = getX();
            int oldY = getY();
            int newX = oldX + dir.rightSteps;
            int newY = oldY + dir.upSteps;
            
        
        if (!isEating){
            if(theField.hasBrickAt(newX, newY))
            {   
                bounceFromSolid(theField, oldX, oldY, newX, newY);}
            else{setLocation(newX, newY);}
                for(int i=-1; i<=1|| isEating; i++){
                    for(int k=-1; k<=1 || isEating; k++){
                        Eat(i,k,newX,newY);
                }
            }
        full-=0.05;
        }

         if (full>=MAX_FULL){
             fortPflanzung();
             full-=REDUCTION_FULL;
            }
            
         if (isDead()){
             die();
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
            // Bug is moving only in the vertical direction
            // Flip its vertical direction of motion
            dir.upSteps *= -1;
        } else if (dir.upSteps ==0)
        {
            // Bug is moving only in the horizontal direction
            // Flip its horzontal direction of motion
            dir.rightSteps *= -1;
        } else
        {
            // Bug is moving along both the axes.
            
            
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
                    CarrionBeetle s = new CarrionBeetle();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX()-1, getY());
                }
                
                // Check if the top neighbouring location is empty.
                else if (null == getOneObjectAtOffset(0, -1, null))
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    CarrionBeetle s = new CarrionBeetle();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX(), getY()-1);
                }

                // Check if the bottom neighbouring location is empty.
                else if (null == getOneObjectAtOffset(0, 1, null))
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    CarrionBeetle s = new CarrionBeetle();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX()+1, getY());;
                }

                // Check if the right neighbouring location is empty.
                else if (null == getOneObjectAtOffset( 1, 0, null))
                {
                    // It is empty.
                    // Add a new grass patch to it.
                    CarrionBeetle s = new CarrionBeetle();
                    s.setDirection(true, true, true, true);
                    s.setFull(KIDS_FULL);
                    theWorld.addObject(s, getX(), getY()+1);
                }
            }
    public void setFull(float a)
    {
        full = a;
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
        getWorld().removeObject(this);
    }
    public void Eat(int i, int k, int X, int Y){
        Field theField = getWorld();
        if(theField.hasDeadAnimalAt(X+i, Y+k)){
           isEating = true;               
           // Eat Dead Animal
           full++;
        }
        else isEating = false;
        
    }
}


