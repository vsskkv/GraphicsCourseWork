/* Lab1.java
 * A simple scene consisting of three boxes
 * 23/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [T(0,-1,-6)] first cube
 *  |
 *  +-- [T(3,-1,-10)] second cube
 *  |
 *  +-- [T(-2,2,-8)] third cube
 */
package Lab1;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of 3D translations to position objects within OpenGL's 3D environment
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
public class Lab1 extends GraphicsLab
{
    public static void main(String args[])
    {   new Lab1().run(WINDOWED,"Lab 1 - Spatial Awareness",0.01f);
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
	        GL11.glTranslatef(0.0f, -1.0f, -2.0f);
	        drawUnitCube(Colour.BLUE,Colour.BLUE,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();

        // position and draw the second cube
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(3.0f, -1.0f, 1.0f);
	        drawUnitCube(Colour.BLUE,Colour.BLUE,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();
        
        // position and draw the third cube
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(-2.0f, 1.2f, -1.0f);
	        drawUnitCube(Colour.BLUE,Colour.BLUE,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();
    }
    /**
     * Set the values for the sample's viewpoint and projection settings. This will override the 
     * default settings in the GraphicsLab base class.
     */
    protected void setSceneCamera()
    {
    	// call the default behaviour defined in GraphicsLab. This will set a default 
    	// perspective projection and default camera settings ready 
    	// for some custom camera positioning below...  
    	super.setSceneCamera();
        
        // set the actual viewpoint using the gluLookAt command. This specifies the 
    	// viewer's (x,y,z) position, the point the viewer is looking 
    	// at (x,y,z) and the view-up direction (x,y,z), typically (0,1,0) - 
    	// i.e. the y-axis defines the up direction
        GLU.gluLookAt(0.0f, 0.0f, 10.0f,   // viewer location        
        		      0.0f, 0.0f, 0.0f,    // view point loc.
        		      0.0f, 1.0f, 0.0f);   // view-up vector
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
        near.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        left.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        right.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        top.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
       	bottom.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        far.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
}
