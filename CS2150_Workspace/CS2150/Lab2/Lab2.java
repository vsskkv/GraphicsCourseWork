/* Lab2.java
 * A simple scene consisting of three stacked boxes
 * 24/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [T(0,-1,-6)] first cube
 *  |
 *  +-- [T(0, 0,-6)] second cube
 *  |
 *  +-- [T(0, 1,-6)] third cube
 */
package Lab2;
import org.lwjgl.opengl.GL11;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of OpenGL state changes to affect how objects are drawn
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 *
 * <p>Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class Lab2 extends GraphicsLab
{	
    public static void main(String args[])
    {   new Lab2().run(WINDOWED,"Lab 2 - Appearance",0.01f);
    }

    protected void initScene()
    {// empty
    }
    protected void checkSceneInput()
    {// empty
    }
    protected void updateScene()
    {// empty
    }
    protected void renderScene()
    {
        // position and draw the first cube
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(0.0f, -1.0f, -6.0f);
	        drawUnitCube(Colour.BLUE,Colour.CYAN,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();

        // position and draw the second cube
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(0.0f, 0.0f, -6.0f);
	        drawUnitCube(Colour.BLUE,Colour.CYAN,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();
        
        // position and draw the third cube
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(0.0f, 1.0f, -6.0f);
	        drawUnitCube(Colour.BLUE,Colour.CYAN,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();
    }
    protected void cleanupScene()
    {// empty
    }

    /**
     * Draws a unit cube using the given colours for its 6 faces
     * 
     * @param near   The colour for the cube's near face
     * @param far    The colour for the cube's far face
     * @param left   The colour for the cube's left face
     * @param right  The colour for the cube's right face
     * @param top    The colour for the cube's top face
     * @param bottom The colour for the cube's bottom face
     */
    private void drawUnitCube(Colour near, Colour far, Colour left, Colour right, Colour top, Colour bottom)
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v2 = new Vertex(-0.5f,  0.5f,  0.5f);
        Vertex v3 = new Vertex( 0.5f,  0.5f,  0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  0.5f, -0.5f);
        Vertex v7 = new Vertex( 0.5f,  0.5f, -0.5f);
        Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);

        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            near.submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            left.submit();

            v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            right.submit();

            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            top.submit();

            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            bottom.submit();

            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            far.submit();

            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
}
