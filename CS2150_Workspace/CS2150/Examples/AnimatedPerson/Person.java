package Examples.AnimatedPerson;
import org.lwjgl.opengl.GL11;

/**
 * Provides an abstraction of a Person, including arm and leg abstractions that may be accessed and
 * manipulated separately. The person is constructed using relative size relationships that are built
 * from initial height, width and depth constructor parameters, so that a variety of person objects
 * may be constructed quickly and easily without having to set lots of variables
 * 
 * @author Anthony Jones and Dan Cornford
 */
public class Person
{
    /** The person's height */
    private float height;
    /** The person's width */
    private float width;
    /** The person's depth */
    private float depth;
    
    /** The person's unit height, according to trial and error */
    private float unitHeight;
    /** The height of the person's head */
    private float headHeight;
    /** The height of the person's torso */
    private float torsoHeight;
    /** The height of the person's waist */
    private float waistHeight;

    /** The person's unit width, according to trial and error */
    private float unitWidth;
    /** The width of the person's head */
    private float headWidth;
    /** The width of the person's torso */
    private float torsoWidth;
    /** The width of the person's waist */
    private float waistWidth;

    /** The person's unit depth, according to trial and error  */
    private float unitDepth;
    /** The depth of the person's head */
    private float headDepth;
    /** The depth of the person's torso */
    private float torsoDepth;
    /** The depth of the person's waist */
    private float waistDepth;

    /** The person's left arm */
    private Arm leftArm;
    /** The person's right arm */
    private Arm rightArm;
    /** The person's left leg */
    private Leg leftLeg;
    /** The person's right leg */ 
    private Leg rightLeg;

    /**
     * Constructs a person of a given height, using default values for the width and depth
     * @param height the person's height
     */
    public Person(float height)
    {   init(height,height*0.6f,height*0.5f);
    }
    /**
     * Constructs a person of a given height, width and depth
     * @param height the person's height
     * @param width the person's width
     * @param depth the person's depth
     */
    public Person(float height, float width, float depth)
    {   init(height,width,depth);
    }
    // helper method to set the person's attributes
    private void init(float height, float width, float depth)
    {
        this.height = height;
        this.width  = width;
        this.depth  = depth;
        
        unitHeight  = height / 5;
        headHeight  = 1 * unitHeight;
        torsoHeight = 1.8f * unitHeight;
        waistHeight = 0.3f * unitHeight;

        unitWidth   = width;
        headWidth   = unitWidth / 2;
        torsoWidth  = 0.9f * unitWidth;
        waistWidth  = 0.85f * unitWidth;

        unitDepth   = depth;
        headDepth   = unitDepth / 1.5f;
        torsoDepth  = unitDepth;
        waistDepth  = unitDepth;
        
        leftArm     = new Arm(PersonPart.LEFT_SIDE, unitHeight,unitWidth,unitDepth);
        rightArm    = new Arm(PersonPart.RIGHT_SIDE,unitHeight,unitWidth,unitDepth);
        
        leftLeg     = new Leg(PersonPart.LEFT_SIDE, unitHeight,unitWidth,unitDepth);
        rightLeg    = new Leg(PersonPart.RIGHT_SIDE,unitHeight,unitWidth,unitDepth);
    }
    
    /**
     * @return the person's height
     */
    public float getHeight()
    {   return height;
    }
    /**
     * @return the person's width
     */
    public float getWidth()
    {   return width;
    }
    /**
     * @return the person's depth
     */
    public float getDepth()
    {   return depth;
    }

    /**
     * @return the person's left arm object
     */
    public Arm getLeftArm()
    {   return leftArm;
    }
    /**
     * @return the person's right arm object
     */
    public Arm getRightArm()
    {   return rightArm;
    }
    /**
     * @return the person's left leg object
     */
    public Leg getLeftLeg()
    {   return leftLeg;
    }
    /**
     * @return the person's right leg object
     */
    public Leg getRightLeg()
    {   return rightLeg;
    }

    /**
     * draws this person object
     */
    public void draw()
    {
        // draw the torso
        drawTorso();
        
        // draw the head
        GL11.glPushMatrix();
        {
            // translate up to the head
            GL11.glTranslatef(0.0f, 0.1f + (torsoHeight + headHeight)/2, 0.0f);
            drawHead();
        }// pop back to torso origin
        GL11.glPopMatrix();
        
        // draw the left arm
        GL11.glPushMatrix();
        {
            // translate left and up to left shoulder and draw left arm
            GL11.glTranslatef((-torsoWidth/2)-leftArm.getShoulderRadius(), (torsoHeight-leftArm.getShoulderRadius())/2, 0.0f);
            leftArm.draw();
        }// pop back to torso origin
        GL11.glPopMatrix();

        // draw the right arm
        GL11.glPushMatrix();
        {
            // translate right and up to right shoulder and draw right arm
            GL11.glTranslatef((torsoWidth/2)+rightArm.getShoulderRadius(), (torsoHeight-rightArm.getShoulderRadius())/2, 0.0f);
            rightArm.draw();
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        {
            // translate down to the waist
            GL11.glTranslatef(0.0f, (-torsoHeight - waistHeight)/2, 0.0f);
            drawWaist();
            
            // draw the left leg
            GL11.glPushMatrix();
            {
                // translate left and down to the left hip and draw left leg
                GL11.glTranslatef((-waistWidth)/2 + leftLeg.getHipRadius(), (-waistHeight)/2 - leftLeg.getHipRadius(), 0.0f);
                leftLeg.draw();
            }// pop back to waist origin
            GL11.glPopMatrix();
            
            // draw the right leg
            GL11.glPushMatrix();
            {
                // translate right and down to the right hip and draw right leg
                GL11.glTranslatef((waistWidth)/2 - leftLeg.getHipRadius(), (-waistHeight)/2 - leftLeg.getHipRadius(), 0.0f);
                rightLeg.draw();

            }// pop back to waist origin
            GL11.glPopMatrix();

        }// pop back to torso origin
        GL11.glPopMatrix();
    }

    // the following are helper functions that simply draw the person's head, torso and waist
    // as scaled unit cubes. The transform and draw calls are surrounded by push and pop pairs
    // to isolate their matrix manipulations from subsequent application behaviour
    private void drawHead()
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(headWidth,headHeight,headDepth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
    private void drawTorso()
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(torsoWidth,torsoHeight,torsoDepth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
    private void drawWaist()
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(waistWidth,waistHeight,waistDepth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
}
