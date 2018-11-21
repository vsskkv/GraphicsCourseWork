/* AnimatedPerson.java
 * A simple scene consisting of an animated person performing various exercises
 * 31/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(10,1,10) T(0,-2.15,-10)] Ground plane
 *  |
 *  +-- [Ry(personSpinY) T(0,personOffsetY,-10)] Person
 *      |
 *      +-- [] Torso
 *          |
 *          +-- [T(0, 0.1+(torsoHeight+headHeight)/2, 0)] Head
 *          |
 *          +-- [T((-torsoWidth/2)-shoulderRadius, (torsoHeight-shoulderRadius)/2, 0)] Left arm
 *          |   |
 *          |   +-- [] Shoulder
 *          |       |
 *          |       +-- [ T(0, (-upperArmLength-shoulderRadius)/2, 0) Rz() R()
 *          |             Rz(shoulderAngleLR*(side==PersonPart.Side.LEFT?-1.0f:1.0f))
 *          |             Rx(shoulderAngleUD) ] Upper arm
 *          |           |
 *          |           +-- [T(0, (-upperArmLength-shoulderRadius)/2, 0)] Elbow
 *          |               |
 *          |               +-- [T(0, (-lowerArmLength-elbowRadius)/2, 0) Rx(elbowAngleUD)] Lower arm
 *          |                   |
 *          |                   +-- [T(0, (-lowerArmLength-elbowRadius)/2, 0)] Wrist
 *          |                       |
 *          |                       +-- [T(0, (-handLength-wristRadius)/2, 0)] Hand
 *          |
 *          +-- [T(( torsoWidth/2)+shoulderRadius, (torsoHeight-shoulderRadius)/2, 0)] Right arm
 *          |   |
 *          |   +-- ... as above for left arm
 *          |
 *          +-- [T(0, (-torsoHeight - waistHeight)/2, 0)] Waist
 *              |
 *              +-- [T((-waistWidth)/2 + hipRadius, (-waistHeight)/2 - hipRadius, 0)] Left leg
 *              |   |
 *              |   +-- [] Hip
 *              |       |
 *              |       +-- [T(0, (-upperLegLength-hipRadius)/2, 0)
 *              |            Rz(hipAngleLR*(side==PersonPart.Side.LEFT?-1.0f:1.0f))
 *              |            Rx(hipAngleUD) ] Upper leg
 *              |           |
 *              |           +-- [T(0, (-upperLegLength-hipRadius)/2, 0)] Knee
 *              |               |
 *              |               +-- [T(0, (-lowerLegLength-kneeRadius)/2, 0) Rz(kneeAngleUD)] Lower leg
 *              |                   |
 *              |                   +-- [T(0, (-lowerLegLength-kneeRadius)/2, 0)] Ankle
 *              |                       |
 *              |                       +-- [T(0, (-footLength)/2, 0) Rx(90)] Foot
 *              |
 *              +-- [T(( waistWidth)/2 - hipRadius, (-waistHeight)/2 - hipRadius, 0)] Right leg
 *                  |
 *                  +-- ... as above for left leg
 */

package Examples.AnimatedPerson;
import java.lang.Math;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of object oriented programming to define a fairly complex 'Person'
 * type, consisting of various connected body parts. The Person's Arm and Leg parts are further encapsulated
 * using Java classes. The Person's height, width and depth may be provided in order to produce a variety
 * of Person objects with varying appearance.
 * The rotational orientation of Arm and Leg objects in respect to their Parent owner may be accessed and
 * modified. The sample demonstrates how simple modifications can be made in order to produce a number of
 * simple animations
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * <li>Press the n key to reset the animation and tell the person to stand still
 * <li>Press the r key to tell the person to start running
 * <li>Press the j key to tell the person to perform star jumps
 * </ul>
 *
 * <p>Including elements from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class AnimatedPerson extends GraphicsLab
{
    /** the person object */
    private Person person = new Person(3.0f,0.6f,0.5f);
    
    /** all animations are a function of the animationDelta, which simply indicates how long
     * the animation has been running so far. Note that this is not a time value or frame count;
     * it merely allows the animations to advance forward smoothly */
    private float animationDelta = 0.0f;
    /** how high the person is from the ground */
    private float personOffsetY  = 0.0f;
    /** the current angle of the person's spin */
    private float personSpinY    = 0.0f;
    
    /** id indicating that no animation is to be used */
    private static final int NO_ANIMATION = 0;
    /** id indicating that running animation is to be used */
    private static final int RUNNING_ANIMATION = 1;
    /** id indicating that jumping animation is to be used */
    private static final int JUMPING_ANIMATION = 2;
    
    /** the animation currently in use */
    private int animation;
    
    public static void main(String args[])
    {   new AnimatedPerson().run(WINDOWED,"Animated Person",0.3f);
    }

    protected void initScene()
    {
        // global ambient light level
        float globalAmbient[]   = {0.8f,  0.8f,  0.8f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        // the first light for the scene is white...
        float diffuse0[]  = { 0.5f,  0.5f, 0.5f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);

    }
    protected void checkSceneInput()
    {
        // fairly easy to understand...
        // note that we reset the animation each time a choice is made so that
        // the jumping animation doesn't interfere with the running animation,
        // and vice versa
        if(Keyboard.isKeyDown(Keyboard.KEY_N))
        {
            resetAnimation();
            animation = NO_ANIMATION;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {
            resetAnimation();
            animation = RUNNING_ANIMATION;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_J))
        {   
            resetAnimation();
            animation = JUMPING_ANIMATION;
        }
    }
    protected void updateScene()
    {
        // spin the person and apply the current animation
        personSpinY += getAnimationScale()/2;
        
        if(animation == RUNNING_ANIMATION)
        {   updateRunningAnimation();
        }
        else if(animation == JUMPING_ANIMATION)
        {   updateJumpingAnimation();
        }
    }

    void resetAnimation()
    {
        // reset all variables that take part in the animations
        // to their initial values... which should all be zero
        final float zero = 0.0f;
        animationDelta = zero;
        personOffsetY = zero;
        
        person.getRightArm().setShoulderAngleUD(zero);
        person.getRightArm().setElbowAngleUD(zero);
        person.getRightArm().setShoulderAngleLR(zero);
        
        person.getLeftArm().setShoulderAngleUD(zero);
        person.getLeftArm().setElbowAngleUD(zero);
        person.getLeftArm().setShoulderAngleLR(zero);
        
        person.getRightLeg().setHipAngleUD(zero);
        person.getRightLeg().setKneeAngleUD(zero);
        person.getRightLeg().setHipAngleLR(zero);

        person.getLeftLeg().setHipAngleUD(zero);
        person.getLeftLeg().setKneeAngleUD(zero);
        person.getLeftLeg().setHipAngleLR(zero);
    }
    
    void updateRunningAnimation()
    {
        animationDelta += getAnimationScale();

        // use a sin function to get the gradual up and down motion required
        // 60 degrees is a nice angle for the arms and legs to reverse direction
        // at the height of their swing 
        double angle = Math.sin(animationDelta / 45) * 60;

        // fairly simple forward/backward swing for the arms and legs
        // note the use of abs to get only positive values...
        // your elbows and knees are only meant to bend one way!
        person.getRightArm().setShoulderAngleUD((float)angle);
        person.getRightArm().setElbowAngleUD((float)Math.abs(angle));
        person.getLeftArm().setShoulderAngleUD(-(float)angle);
        person.getLeftArm().setElbowAngleUD((float)Math.abs(angle));
        
        person.getRightLeg().setHipAngleUD(-(float)angle);
        person.getRightLeg().setKneeAngleUD(-(float)Math.abs(angle));
        person.getLeftLeg().setHipAngleUD((float)angle);
        person.getLeftLeg().setKneeAngleUD(-(float)Math.abs(angle));
        
        // small positive-only up and down bounce as the person runs
        // note that the time step is in synch with the swinging animations
        // (we use 45 degrees in both calls to sin) so that we get a bounce
        // for each swing of the legs (i.e. a bounce for each push on the
        // back foot
        personOffsetY = (float)Math.abs(Math.sin(animationDelta / 45)) * 0.2f;
    }
    void updateJumpingAnimation()
    {
        animationDelta += getAnimationScale();

        // use a sin function to get the gradual up and down motion required
        double angle = Math.sin(animationDelta / 90);
        
        // very simple left/right rotations for the shoulders and hips
        // 150 and 30 degrees are nice angles for the star jump 
        person.getRightArm().setShoulderAngleLR((float)Math.abs(angle)*150);
        person.getLeftArm().setShoulderAngleLR( (float)Math.abs(angle)*150);
        
        person.getRightLeg().setHipAngleLR((float)Math.abs(angle)*30);
        person.getLeftLeg().setHipAngleLR((float)Math.abs(angle)*30);
        
        // fairly large positive-only up and down jump
        // note that the time step is out of synch with the swinging animations
        // (we use 90 degrees above, and 45 degrees here) so that we get
        // two jumps for every complete up and down swing of the arms and legs
        // (i.e. the person lands when the arms/legs are closed AND open)
        personOffsetY = (float)Math.abs(Math.sin(animationDelta / 45));
    }
    
    protected void renderScene()
    {
        // draw the ground plane
        GL11.glPushMatrix();
        {
            // position, scale and draw the ground plane
            GL11.glTranslatef(0.0f,-2.15f,-10.0f);
            GL11.glScaled(10.0f, 1.0f, 10.0f);
            drawUnitPlane();
        }
        GL11.glPopMatrix();

        // position, rotate and draw the person
        GL11.glTranslatef(0.0f,personOffsetY,-10.0f);
        GL11.glRotatef(personSpinY, 0.0f, 1.0f, 0.0f);
        person.draw();
    }
    protected void cleanupScene()
    {// empty
    }
    
    /**
     * Draws a plane aligned with the X and Z axis, with its front face toward positive Y.
     *  The plane is of unit width and height, and uses the current OpenGL material settings
     *  for its appearance
     */
    private void drawUnitPlane()
    {
        Vertex v1 = new Vertex(-0.5f, 0.0f,-0.5f); // left,  back
        Vertex v2 = new Vertex( 0.5f, 0.0f,-0.5f); // right, back
        Vertex v3 = new Vertex( 0.5f, 0.0f, 0.5f); // right, front
        Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left,  front
        
        // draw the plane geometry. order the vertices so that the plane faces up
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
        }
        GL11.glEnd();
        
        // if the user is viewing an axis, then also draw this plane
        // using lines so that axis aligned planes can still be seen
        if(isViewingAxis())
        {
            // also disable textures when drawing as lines
            // so that the lines can be seen more clearly
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                v4.submit();
                v3.submit();
                v2.submit();
                v1.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
    }
}
