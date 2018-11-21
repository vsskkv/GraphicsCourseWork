/* SolarSystem.java
 * An animated Solar System
 * 29/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [] Sun
 *      |
 *      +-- [T(5,0,0) Ry(360*timeday/365)] Earth
 *      |   |
 *      |   +-- [T(5,0,0) Ry(360*timeday*0.5)] Moon
 *      |
 *      +-- [T(8,0,0) Ry(360*timeday/467)] Mars
 *          |
 *          +-- [T(0.5,0,0) R(360*timeday*0.9)] Mars' first moon
 *          |
 *          +-- [T(0.7,0,0) Ry(150 + 360*timeday/2)] Mars' second moon
 */
package Examples.SolarSystem;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of OpenGL 3D rendering and animation by drawing
 * a simplified solar system that includes orbiting planets and moons
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 *
 * <p>Originally written by Dan Cornford; ported to Java including elements from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class SolarSystem extends GraphicsLab
{
    /** the time in the solar year on Earth */
    private float timeday = 0.0f;

    public static void main(String args[])
    {   new SolarSystem().run(WINDOWED,"Solar System",0.01f);
    }

    protected void initScene()
    {// empty
    }
    protected void checkSceneInput()
    {// empty
    }
    protected void setSceneCamera()
    {
        // use the default projection settings
        super.setSceneCamera();

        // position the camera up and back a little
        GLU.gluLookAt(0.0f,3.0f,20.0f, 0.0f,0.0f,0.0f, 0.0f,1.0f,0.0f);
    }
    protected void updateScene()
    {
        // increment the time variable by 0.01 Earth days
        timeday += + 1.0f * getAnimationScale();
    }
    protected void renderScene()
    {
        // the colour of the moons in the scene
        final Colour grey = new Colour(0.7f,0.7f,0.7f);
        
        // draw the Sun at the scene origin (i.e. in the middle of the scene)
        drawBody(Colour.YELLOW, 0.7f);
        
        // store the current model view matrix (Sun origin)
        GL11.glPushMatrix();
        {
            // rotate the Earth around the Sun
            GL11.glRotatef((360.0f*timeday/365.0f),0.0f,1.0f,0.0f);
            // the Earth is 5 units from the Sun
            GL11.glTranslatef(5.0f,0.0f,0.0f);
            // draw the Earth
            drawBody(Colour.BLUE, 0.3f);
            
            // rotate the Moon around the Earth
            GL11.glRotatef((360.0f*timeday*0.5f),0.0f,1.0f,0.0f);
            // the Moon is .5 units from the Earth
            GL11.glTranslatef(0.5f,0.0f,0.0f);
            // draw the moon
            drawBody(grey, 0.1f);

        }// restore the sun origin
        GL11.glPopMatrix();

        // rotate Mars around the Sun
        GL11.glRotatef((360.0f*timeday/467.0f),0.0f,1.0f,0.0f);
        // Mars is 8 units from the Sun
        GL11.glTranslatef(8.0f,0.0f,0.0f);
        // draw Mars
        drawBody(Colour.RED, 0.26f);
        
        // store the current model view matrix (Mars origin)
        GL11.glPushMatrix();
        {
            // rotate the first moon around Mars, translate and draw it
            GL11.glRotatef((360.0f*timeday*0.9f),0.0f,1.0f,0.0f);
            GL11.glTranslatef(0.5f,0.0f,0.0f);
            drawBody(grey, 0.03f);
            
        }// restore the Mars origin
        GL11.glPopMatrix();
        
        // rotate the second moon around Mars, translate and draw it
        GL11.glRotatef((150 + 360.0f*timeday/2.0f),0.0f,1.0f,0.0f);
        GL11.glTranslatef(0.7f,0.0f,0.0f);
        drawBody(grey, 0.06f);
    }
    protected void cleanupScene()
    {// empty
    }
    
    /** draws a coloured sphere representing a star, planet or moon */
    private void drawBody(Colour colour, float size)
    {
        colour.submit();
        new Sphere().draw(size, 20, 20);
    }
}
