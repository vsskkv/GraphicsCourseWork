package Examples.AnimatedPerson;
import org.lwjgl.opengl.GL11;

/**
 * Defines a leg that may form part of a Person object
 * 
 * @author Anthony Jones and Dan Cornford
 */
public class Leg extends PersonPart
{
    private int side;

    private float upperLegLength;
    private float lowerLegLength;
    private float footLength;

    private float upperLegWidth;
    private float lowerLegWidth;
    private float footWidth;

    private float upperLegDepth;
    private float lowerLegDepth;
    private float footDepth;

    private float hipRadius;
    private float hipAngleUD 	= 0.0f;
    private float hipAngleLR 	= 0.0f;
    private float kneeRadius;
    private float kneeAngleUD  	= 0.0f;
    private float ankleRadius;

    /**
     * Constructs a Leg object
     * @param side which side of the Person this leg is attached to
     * @param unitLength the unit length of a Person as defined in the Person class 
     * @param unitWidth the unit width of a Person as defined in the Person class
     * @param unitDepth the unit depth of a Person as defined in the Person class
     */
    public Leg(int side, float unitLength, float unitWidth, float unitDepth)
    {
        this.side = side;
        
        upperLegLength = unitLength;
        lowerLegLength = 0.8f * unitLength;
        footLength     = 0.6f * lowerLegLength;

        upperLegWidth  = unitWidth / 3;
        lowerLegWidth  = unitWidth / 3.5f;
        footWidth      = lowerLegWidth;

        upperLegDepth  = unitDepth / 2;
        lowerLegDepth  = unitDepth / 2.5f;
        footDepth      = lowerLegDepth / 2;

        hipRadius      = (upperLegDepth+upperLegWidth)/4;
        kneeRadius     = (lowerLegDepth+lowerLegWidth)/4;
        ankleRadius    = (footDepth+footWidth)/4;
    }
    
    /**
     * @return the radius of the hip joint, which may be important for
     *          positioning parent objects in relation to the hip geometry
     */
    public float getHipRadius()
    {   return hipRadius;
    }
    /**
     * @return the current up/down angle of the hip joint 
     */
    public float getHipAngleUD()
    {   return hipAngleUD;
    }
    /**
     * @param hipAngleUD the new up/down angle of the hip joint
     */
    public void setHipAngleUD(float hipAngleUD)
    {   this.hipAngleUD = hipAngleUD;
    }
    /**
     * @return the current left/right angle of the hip joint
     */
    public float getHipAngleLR()
    {   return hipAngleLR;
    }
    /**
     * @param hipAngleLR the new left/right angle of the hip joint
     */
    public void setHipAngleLR(float hipAngleLR)
    {   this.hipAngleLR = hipAngleLR;
    }
    /**
     * @return the current up/down angle of the knee joint
     */
    public float getKneeAngleUD()
    {   return kneeAngleUD;
    }
    /**
     * @param kneeAngleUD the new up/down angle of the knee joint
     */
    public void setKneeAngleUD(float kneeAngleUD)
    {   this.kneeAngleUD = kneeAngleUD;
    }
    
    /**
     * draws this leg object
     */
    public void draw()
    {
        GL11.glPushMatrix();
        {
            // draw the hip
            drawJoint(hipRadius);
            // rotate about the hip and translate to upper leg
            GL11.glRotatef(hipAngleUD, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(hipAngleLR*(side==LEFT_SIDE?-1.0f:1.0f), 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(0.0f, (-upperLegLength-hipRadius)/2, 0.0f);
            drawUpperLeg();
            // translate to knee
            GL11.glTranslatef(0.0f, (-upperLegLength-hipRadius)/2, 0.0f);
            drawJoint(kneeRadius);
            // rotate about the knee and translate to lower leg
            GL11.glRotatef(kneeAngleUD, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(0.0f, (-lowerLegLength-kneeRadius)/2, 0.0f);
            drawLowerLeg();
            // translate to ankle
            GL11.glTranslatef(0.0f, (-lowerLegLength-kneeRadius)/2, 0.0f);
            drawJoint(ankleRadius);
            // translate to foot
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(0.0f, (-footLength)/2, 0.0f);
            drawFoot();
        }
        GL11.glPopMatrix();
    }
    
    private void drawUpperLeg()
    {   drawPart(upperLegWidth, upperLegLength, upperLegDepth);
    }
    private void drawLowerLeg()
    {   drawPart(lowerLegWidth, lowerLegLength, lowerLegDepth);
    }
    private void drawFoot()
    {   drawPart(footWidth, footLength, footDepth);
    }
}
