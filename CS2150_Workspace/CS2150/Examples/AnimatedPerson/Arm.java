package Examples.AnimatedPerson;
import org.lwjgl.opengl.GL11;

/**
 * Defines an arm that may form part of a Person object
 * 
 * @author Anthony Jones and Dan Cornford
 */
public class Arm extends PersonPart
{
    private int side;

    private float upperArmLength;
    private float lowerArmLength;
    private float handLength;

    private float upperArmWidth;
    private float lowerArmWidth;
    private float handWidth;

    private float upperArmDepth;
    private float lowerArmDepth;
    private float handDepth;

    private float shoulderRadius;
    private float shoulderAngleUD = 0.0f;
    private float shoulderAngleLR = 0.0f;
    private float elbowRadius;
    private float elbowAngleUD    = 0.0f;
    private float wristRadius;
    
    /**
     * Constructs an Arm object
     * @param side which side of the Person this arm is attached to
     * @param unitLength the unit length of a Person as defined in the Person class 
     * @param unitWidth the unit width of a Person as defined in the Person class
     * @param unitDepth the unit depth of a Person as defined in the Person class
     */
    public Arm(int side, float unitLength, float unitWidth, float unitDepth)
    {
        this.side = side;
        
        upperArmLength = 0.8f * unitLength;
        lowerArmLength = 0.8f * unitLength;
        handLength     = 0.6f * lowerArmLength;

        upperArmWidth  = unitWidth / 5;
        lowerArmWidth  = unitWidth / 5.5f;
        handWidth      = lowerArmWidth / 2;

        upperArmDepth  = unitDepth / 2;
        lowerArmDepth  = unitDepth / 2.5f;
        handDepth      = 0.8f * lowerArmDepth;

        shoulderRadius = (upperArmDepth+upperArmWidth)/4;
        elbowRadius    = (lowerArmDepth+lowerArmWidth)/4;
        wristRadius    = (handDepth+handWidth)/4;
    }
    
    /**
     * @return the radius of the shoulder joint, which may be important for
     *          positioning parent objects in relation to the shoulder geometry
     */
    public float getShoulderRadius()
    {   return shoulderRadius;
    }
    /**
     * @return the current up/down angle of the shoulder joint
     */
    public float getShoulderAngleUD()
    {   return shoulderAngleUD;
    }
    /**
     * @param shoulderAngleUD the new up/down angle of the shoulder joint
     */
    public void setShoulderAngleUD(float shoulderAngleUD)
    {   this.shoulderAngleUD = shoulderAngleUD;
    }
    /**
     * @return the current left/right angle of the shoulder joint
     */
    public float getShoulderAngleLR()
    {   return shoulderAngleLR;
    }
    /**
     * @param shoulderAngleLR the new left/right angle of the shoulder joint
     */
    public void setShoulderAngleLR(float shoulderAngleLR)
    {   this.shoulderAngleLR = shoulderAngleLR;
    }
    /**
     * @return the current up/down angle of the elbow joint
     */
    public float getElbowAngleUD()
    {   return elbowAngleUD;
    }
    /**
     * @param elbowAngleUD the new up/down angle of the elbow joint
     */
    public void setElbowAngleUD(float elbowAngleUD)
    {   this.elbowAngleUD = elbowAngleUD;
    }
    
    /**
     * draws this arm object
     */
    public void draw()
    {
        GL11.glPushMatrix();
        {
            // draw the shoulder
            drawJoint(shoulderRadius);
            // rotate about the shoulder and translate to upper arm
            GL11.glRotatef(shoulderAngleUD, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(shoulderAngleLR*(side==LEFT_SIDE?-1.0f:1.0f), 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(0.0f, (-upperArmLength-shoulderRadius)/2, 0.0f);
            drawUpperArm();
            // translate to elbow
            GL11.glTranslatef(0.0f, (-upperArmLength-shoulderRadius)/2, 0.0f);
            drawJoint(elbowRadius);
            // rotate about the elbow and translate to lower arm
            GL11.glRotatef(elbowAngleUD, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(0.0f, (-lowerArmLength-elbowRadius)/2, 0.0f);
            drawLowerArm();
            // translate to wrist
            GL11.glTranslatef(0.0f, (-lowerArmLength-elbowRadius)/2, 0.0f);
            drawJoint(wristRadius);
            // translate to hand
            GL11.glTranslatef(0.0f, (-handLength-wristRadius)/2, 0.0f);
            drawHand();
        }
        GL11.glPopMatrix();
    }
    
    private void drawUpperArm()
    {   drawPart(upperArmWidth, upperArmLength, upperArmDepth);
    }
    private void drawLowerArm()
    {   drawPart(lowerArmWidth, lowerArmLength, lowerArmDepth);
    }
    private void drawHand()
    {   drawPart(handWidth, handLength, handDepth);
    }
}
