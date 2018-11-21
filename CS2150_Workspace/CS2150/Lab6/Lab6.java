/* Lab6.java
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
 *  +-- [T(4,currentSunMoonY,-19)] Moon
 *  |
 *  +-- [T(2.0,-0.5,-10)] Bee hive
 *  |
 *  +-- [T(0,-1,-12)] Tree
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [Ry(35) S(2,2,2) T(-2.5,0,-10)] House
 *      |
 *      +-- [S(1,0.5,1) T(0,0.75,0)] Roof
 */
package Lab6;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of user input and various types of animation
 * to add a dynamic aspect to a 3D scene
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * <li>Press L to lower the sun
 * <li>Press R to raise the sun
 * </ul>
 *
 * <p>Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class Lab6 extends GraphicsLab
{
    /** display list id for the house */
    private final int houseList = 1;
    /** display list id for the roof */
    private final int roofList  = 2;
    /** display list id for the unit plane */
    private final int planeList = 3;
    /** display list id for the bee hive */
    private final int hiveList  = 4;
    
    /** the sun/moon's current Y offset from the scene origin */
    private float currentSunMoonY = 7.0f;
    /** the sun/moon's highest possible Y offset */
    private final float highestSunMoonY = currentSunMoonY;
    /** the sun/moon's lowest possible Y offset */
    private final float lowestSunMoonY  = -2.0f;
    /** is the sun/moon rising? (false = the sun/moon is falling) */
    private boolean risingSunMoon = true;

    /** ids for nearest, linear and mipmapped textures for the ground plane */
    private Texture groundTextures;
    /** ids for nearest, linear and mipmapped textures for the daytime back (sky) plane */
    //private Texture skyDayTextures;
    /** ids for nearest, linear and mipmapped textures for the night time back (sky) plane */
    private Texture skyNightTextures;

    public static void main(String args[])
    {   new Lab6().run(WINDOWED,"Lab 6 - Animation",0.01f);
    }

    protected void initScene() throws Exception
    {
        // load the textures
        groundTextures = loadTexture("Lab6/textures/grass.bmp");
        //skyDayTextures = loadTexture("Lab6/textures/daySky.bmp");
        skyNightTextures = loadTexture("Lab6/textures/nightSky.bmp");

        // global ambient light level
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        // the first light for the scene is soft blue...
        float diffuse0[]  = { 0.2f,  0.2f, 0.4f, 1.0f};
        // ...with a very dim ambient contribution...
        float ambient0[]  = { 0.05f,  0.05f, 0.05f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f};

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

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
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        GL11.glNewList(hiveList, GL11.GL_COMPILE);
        {   drawUnitPyramid();
        }
        GL11.glEndList();
    }
    protected void checkSceneInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {   risingSunMoon = true;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_L))
        {   risingSunMoon = false;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {   resetAnimations();
        }
    }
    protected void updateScene()
    {
        // if the sun/moon is rising, and it isn't at its highest,
        // then increment the sun/moon's Y offset
        if(risingSunMoon && currentSunMoonY < highestSunMoonY)
        {   currentSunMoonY += 1.0f * getAnimationScale();
        }
        // else if the sun/moon is falling, and it isn't at its lowest,
        // then decrement the sun/moon's Y offset
        else if(!risingSunMoon && currentSunMoonY > lowestSunMoonY)
        {   currentSunMoonY -= 1.0f * getAnimationScale();
        }
    }
    protected void renderScene()
    {
        // draw the ground plane
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(0.0f,-1.0f,-10.0f);
            GL11.glScalef(25.0f, 1.0f, 20.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the back plane
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,skyNightTextures.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(0.0f,4.0f,-20.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(25.0f, 1.0f, 10.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the moon
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the moon (specular exponent)
            float moonFrontShininess  = 2.0f;
            // specular reflection of the front faces of the moon
            float moonFrontSpecular[] = {0.6f, 0.6f, 0.6f, 1.0f};
            // diffuse reflection of the front faces of the moon
            float moonFrontDiffuse[]  = {0.6f, 0.6f, 0.6f, 1.0f};

            // set the material properties for the sun using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, moonFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(moonFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(moonFrontDiffuse));

            // position and draw the moon using a sphere quadric object
            GL11.glTranslatef(4.0f, currentSunMoonY, -19.0f);
            new Sphere().draw(0.5f,10,10);
        }
        GL11.glPopMatrix();

        // draw the bee hive
        GL11.glPushMatrix();
        {
            // position, scale and draw the bee hive
            GL11.glTranslatef(2.5f,-0.5f,-10.0f);

            // how shiny are the front faces of the bee hive (specular exponent)
            float hiveFrontShininess  = 2.0f;
            // specular reflection of the front faces of the bee hive
            float hiveFrontSpecular[] = {0.0f, 0.0f, 0.0f, 1.0f};
            // diffuse reflection of the front faces of the bee hive
            float hiveFrontDiffuse[]  = {0.8f, 0.8f, 0.0f, 1.0f};
            
            // set the material properties for the bee hive using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, hiveFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(hiveFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(hiveFrontDiffuse));

            // draw the bee hive using its display list
            GL11.glCallList(hiveList);
        }
        GL11.glPopMatrix();
        
        // draw the tree
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the trunk (specular exponent)
            float trunkFrontShininess  = 20.0f;
            // specular reflection of the front faces of the trunk
            float trunkFrontSpecular[] = {0.2f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the trunk
            float trunkFrontDiffuse[]  = {0.38f, 0.29f, 0.07f, 1.0f};
            
            // set the material properties for the trunk using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

            // position the tree
            GL11.glTranslatef(0.0f, -1.0f, -12.0f);
            
            // draw the trunk using a cylinder quadric object. Surround the draw call with a
            // push/pop matrix pair, as the cylinder will originally be aligned with the Z axis
            // and will have to be rotated to align it along the Y axis
            GL11.glPushMatrix();
            {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.25f, 0.25f, 1.5f, 10, 10);
            }
            GL11.glPopMatrix();

            // how shiny are the front faces of the leafy head of the tree (specular exponent)
            float headFrontShininess  = 20.0f;
            // specular reflection of the front faces of the head
            float headFrontSpecular[] = {0.1f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the head
            float headFrontDiffuse[]  = {0.0f, 0.5f, 0.0f, 1.0f};
            
            // set the material properties for the head using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

            // position and draw the leafy head using a sphere quadric object
            GL11.glTranslatef(0.0f, 2.0f, 0.0f);
            new Sphere().draw(0.8f, 10, 10);
        }
        GL11.glPopMatrix();
        
        // draw the house and its roof
        GL11.glPushMatrix();
        {
            // position and scale the house
	        GL11.glTranslatef(-2.5f, 0.0f, -10.0f);
	        GL11.glScalef(2.0f, 2.0f, 2.0f);
	        // rotate the house a little so that we can see more of it
	        GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);
	        
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

	        // draw the base of the house using its display list
	        GL11.glCallList(houseList);
	        
	        // position and scale the house's roof relative to the base of the house
	        GL11.glTranslatef(0.0f, 0.75f, 0.0f);
	        GL11.glScalef(1.0f, 0.5f, 1.0f);
	        
	        // how shiny are the front faces of the roof (specular exponent)
	        float roofFrontShininess = 2.0f;
	        // specular reflection of the front faces of the roof
	        float roofFrontSpecular[] = {0.1f, 0.1f, 0.1f, 1.0f};
	        // diffuse reflection of the front faces of the roof
	        float roofFrontDiffuse[] = {0.6f, 0.4f, 0.2f, 1.0f};
	        
            // Set the material properties for the house using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, roofFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(roofFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(roofFrontDiffuse));

            // draw the roof using its display list
            GL11.glCallList(roofList);
        }
        GL11.glPopMatrix();
    }
    
    protected void cleanupScene()
    {// empty
    }
    
    private void resetAnimations()
    {
        // reset all attributes that are modified by user controls or animations
        currentSunMoonY = highestSunMoonY;
        risingSunMoon = true;
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
    /**
     * Draws a pyramid of unit length, width and height. The pyramid uses the current OpenGL
     * material settings for its appearance
     */
    private void drawUnitPyramid()
    {
        Vertex v1 = new Vertex( 0.0f,  0.5f,  0.0f);
        Vertex v2 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v3 = new Vertex( 0.5f, -0.5f, -0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f,  0.5f);
        
        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v3.toVector(),v4.toVector(),v5.toVector()).submit();
            
            v2.submit();
            v3.submit();
            v4.submit();
            v5.submit();
        }
        GL11.glEnd();
        // draw the near face:
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v1.toVector(),v5.toVector(),v4.toVector()).submit();
            
            v1.submit();
            v5.submit();
            v4.submit();
        }
        GL11.glEnd();
        // draw the right face:
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v1.toVector(),v4.toVector(),v3.toVector()).submit();
            
            v1.submit();
            v4.submit();
            v3.submit();
        }
        GL11.glEnd();
        // draw the far face:
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v1.toVector(),v3.toVector(),v2.toVector()).submit();
            
            v1.submit();
            v3.submit();
            v2.submit();
        }
        GL11.glEnd();
        // draw the left face:
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v1.toVector(),v2.toVector(),v5.toVector()).submit();
            
            v1.submit();
            v2.submit();
            v5.submit();
        }
        GL11.glEnd();
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
