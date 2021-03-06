/* Lab5.java
 * A simple scene consisting of a lit house and a textured ground plane
 * 27/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(20,1,20) T(0,-1,-10)] Ground plane
 *  |
 *  +-- [S(20,1,10) Rx(90) T(0,4,-20)] Sky plane
 *  |
 *  +-- [T(4,7,-19)] Sun
 *  |
 *  +-- [Ry(35) S(2,2,2) T(-2.5,0,-10)] House
 *      |
 *      +-- [S(1,0.5,1) T(0,0.75,0)] Roof
 */
package Lab5;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;
import Lab4.Lab4;

/**
 * This sample demonstrates the use of OpenGL quadrics and textures to enhance both the
 * content and appearance of a 3D scene 
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
public class Lab5 extends GraphicsLab
{    

    private final int starList  = 1;
    
    /** angle that the house is rotate to see the lighting better */
    private float houseRotationAngle= 35.0f;
    
    public static void main(String args[])
    {   new Lab5().run(WINDOWED,"Lab 5 - Lighting",0.01f);
    }
    
    protected void initScene()
    {
        // global ambient light level
        //float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1f};
        // set the global ambient lighting
        //GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

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
        //GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        //GL11.glEnable(GL11.GL_NORMALIZE);
        
        GL11.glNewList(starList,GL11.GL_COMPILE);
        {   DrawStarFish();
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
        // position and scale the house's roof relative to the base of the house
      //  GL11.glTranslatef(0.0f, 0.75f, 0.0f);
        GL11.glScalef(1.0f, 0.5f, 1.0f);
        GL11.glTranslated(0f, 0f, 0f);
        
        // how shiny are the front faces of the roof (specular exponent)
        //float roofFrontShininess = 60.0f;
        // specular reflection of the front faces of the roof
        //float roofFrontSpecular[] = {0.2f, 0.2f, 0.2f, 1.0f};
        // diffuse reflection of the front faces of the roof
        //float roofFrontDiffuse[] = {0.2f, 0.2f, 0.2f, 1.0f};
        
        // Set the material properties for the house using OpenGL
        //GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, roofFrontShininess);
        //GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(roofFrontSpecular));
        //GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(roofFrontDiffuse));
        //GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(roofFrontDiffuse));

        // draw the roof  using its display list
        GL11.glCallList(starList);
    }
    protected void cleanupScene()
    {// empty
    }

    private void DrawStarFish() {
    	Vertex v1 = new Vertex(0f, -0.5f, 0.5f);
		Vertex v2 = new Vertex(0.5f, 0f, 0.5f);
		Vertex v3 = new Vertex(0f, 0.5f, 0.5f);
		Vertex v4 = new Vertex(-0.5f, 0f, 0.5f);
		
		Vertex v5 = new Vertex(0f, -0.5f, -0.5f);
		Vertex v6 = new Vertex(0.5f, 0f, -0.5f);
		Vertex v7 = new Vertex(0f, 0.5f, -0.5f);
		Vertex v8 = new Vertex(-0.5f, 0f, -0.5f);

		GL11.glBegin(GL11.GL_POLYGON);
		{
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
			
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();
			
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v2.submit();
			v6.submit();
			v7.submit();
			v3.submit();
			
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v1.submit();
			v5.submit();
			v6.submit();
			v2.submit();
			
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v1.submit();
			v5.submit();
			v6.submit();
			v2.submit();
			
		}
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			v8.submit();
			v7.submit();
			v3.submit();
			v4.submit();
			
		}
		GL11.glEnd();
    }
}
