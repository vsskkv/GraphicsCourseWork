/* Lab4.java
 * A simple scene consisting of a house
 * 24/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [Ry(35) T(0,-0.5,-5)] House
 *      |
 *      +-- [S(1,0.5,1) T(0,0.75,0)] Roof
 */
package Lab4;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of OpenGL lighting and materials to enhance the appearance of a 3D scene 
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
public class Lab4 extends GraphicsLab
{
    /** display list id for the house */
    private final int houseList = 1;
    /** display list id for the roof */
    private final int roofList  = 2;
    
    /** angle that the house is rotate to see the lighting better */
    private float houseRotationAngle= 35.0f;
    
    public static void main(String args[])
    {   new Lab4().run(WINDOWED,"Lab 4 - Lighting",0.01f);
    }
    
    protected void initScene()
    {
        // global ambient light level
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

        /*
        // the first light for the scene is white...
        float diffuse0[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above and behind the viewpoint
        float position0[] = { 0.0f, 10.0f, 5.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
  		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);
        */


        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        // prepare the display lists for later use
        GL11.glNewList(houseList,GL11.GL_COMPILE);
        {   drawUnitCube();
        }
        GL11.glEndList();
        GL11.glNewList(roofList,GL11.GL_COMPILE);
        {   drawUnitRoof();
        }
        GL11.glEndList();
    }
    protected void checkSceneInput()
    {
      if(Keyboard.isKeyDown(Keyboard.KEY_R))
      {   houseRotationAngle += 1.0f * getAnimationScale(); // Make the house go around if the R key is pressed
          if (houseRotationAngle > 360.0f) // Wrap the angle back around into 0-360 degrees.
          {  houseRotationAngle = 0.0f;
          }
      }
    }
    protected void updateScene()
    {// empty
    }
    protected void renderScene()
    {
        // position the house
        GL11.glTranslatef(0.0f, -0.5f, -5.0f);
        GL11.glRotatef(houseRotationAngle, 0.0f, 1.0f, 0.0f);

        // how shiny are the front faces of the house (specular exponent)
        float houseFrontShininess  = 2.0f;
        // specular reflection of the front faces of the house
        float houseFrontSpecular[] = {0.1f, 0.0f, 0.0f, 1.0f};
        // diffuse reflection of the front faces of the house
        float houseFrontDiffuse[]  = {0.6f, 0.2f, 0.2f, 1.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(houseFrontDiffuse));

        // draw the base of the house using its display list
        GL11.glCallList(houseList);
        
        // position and scale the house's roof relative to the base of the house
        GL11.glTranslatef(0.0f, 0.75f, 0.0f);
        GL11.glScalef(1.0f, 0.5f, 1.0f);
        
        // how shiny are the front faces of the roof (specular exponent)
        float roofFrontShininess = 60.0f;
        // specular reflection of the front faces of the roof
        float roofFrontSpecular[] = {0.2f, 0.2f, 0.2f, 1.0f};
        // diffuse reflection of the front faces of the roof
        float roofFrontDiffuse[] = {0.2f, 0.2f, 0.2f, 1.0f};
        
        // Set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, roofFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(roofFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(roofFrontDiffuse));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(roofFrontDiffuse));

        // draw the roof  using its display list
        GL11.glCallList(roofList);
    }
    protected void cleanupScene()
    {// empty
    }

    /**
     * Draws a roof geometry of unit length, width and height aligned along the x axis.
     * The roof uses the current OpenGL material settings for its appearance
     */
    private void drawUnitRoof()
    {
        Vertex v1 = new Vertex(-0.5f,-0.5f,-0.5f);
        Vertex v2 = new Vertex(-0.5f, 0.5f, 0.0f);
        Vertex v3 = new Vertex(-0.5f,-0.5f, 0.5f);
        Vertex v4 = new Vertex( 0.5f,-0.5f,-0.5f);
        Vertex v5 = new Vertex( 0.5f, 0.5f, 0.0f);
        Vertex v6 = new Vertex( 0.5f,-0.5f, 0.5f);
        
        // left gable
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
        }
        GL11.glEnd();
        
        // back slope
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v1.toVector(),v2.toVector(),v5.toVector(),v4.toVector()).submit();
            
            v1.submit();
            v2.submit();
            v5.submit();
            v4.submit();
        }
        GL11.glEnd();
        
        // front slope
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v5.toVector(),v2.toVector(),v3.toVector(),v6.toVector()).submit();
            
            v5.submit();
            v2.submit();
            v3.submit();
            v6.submit();
        }
        GL11.glEnd();
        
        // right gable
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v5.toVector(),v6.toVector(),v4.toVector()).submit();
            
            v5.submit();
            v6.submit();
            v4.submit();
        }
        GL11.glEnd();
    }
    /**
     * Draws a cube of unit length, width and height using the current OpenGL material settings
     */
    private void drawUnitCube()
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
            new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
            
            v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
            
            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
            
            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
            
            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
}
