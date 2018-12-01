/* 
 * FileName: CS2150Coursework.java
 * Brief:
 * Author:
 * Created: 22/11/2018
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(20,1,20) T(0,-1,-10)] Ground plane
 *  |
 *  |
 *  +-- [T(4,currentSunMoonY,-19)] Planet
 *  |
 *  |
 *  +-- [T(0,-1,-12)] Tree
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 */
package coursework;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import java.util.Date;

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
public class CS2150Coursework extends GraphicsLab
{
    /** display list id for the house */
    private final int houseList = 1;
    /** display list id for the unit plane */
    private final int planeList = 2;
    /** display list for robot */ 
    private final int DRobot = 3;

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
    /** ids for nearest, linear and mipmapped textures for the night time back background plane 
     * {@link https://fstoppers.com/news/japan-landed-space-rovers-aste0roid-and-first-pictures-are-here-292344}
     * */
    private Texture backGroundTexture;
    
    private Texture starFishTexture;
    
    private Texture plantTexture;
    
    private Texture BrickTexture;
    
    // how shiny are the front faces of the house (specular exponent)
    private float RobotFrontShininess  = 2.0f;
    // specular reflection of the front faces of the house
    private float RobotFrontSpecular[] = {0.1f, 0.0f, 0.0f, 1.0f};
    // diffuse reflection of the front faces of the house
    private float RobotFrontDiffuse[]  = {0.6f, 0.2f, 0.2f, 1.0f};
    

    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"Coursework-Submission",0.01f);
    }

    protected void initScene() throws Exception
    {
        // load the textures
        groundTextures = loadTexture("coursework/textures/Grass01.bmp");
        backGroundTexture = loadTexture("coursework/textures/space.bmp");
        starFishTexture = loadTexture("coursework/textures/1.bmp");
        plantTexture = loadTexture("coursework/textures/planet1.bmp");
        BrickTexture = loadTexture("coursework/textures/brick.bmp");

        // global ambient light level
        float globalAmbient[]   = {0.7f,  0.7f,  0.7f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        // the first light for the scene is soft blue...
        float diffuse0[]  = { 0.2f,  0.2f, 0.4f, 1.0f};
        // ...with a very dim ambient contribution...
        float ambient0[]  = { 0.05f,  0.05f, 0.05f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 5.0f, 5.0f, 0.0f, 1.0f};

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
        
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        //Robot parts
        GL11.glNewList(DRobot, GL11.GL_COMPILE);
        {
        	Robot robot = new Robot();
        	robot.DrawRobot();
        }
        GL11.glEndList();
    }
    protected void checkSceneInput()
    {
/*        if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {   risingSunMoon = true;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_L))
        {   risingSunMoon = false;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {   resetAnimations();
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_V ))
        {   RotationAngle += 1.0f * getAnimationScale(); // Make the house go around if the R key is pressed
            if (RotationAngle > 360.0f) // Wrap the angle back around into 0-360 degrees.
            {  
            	RotationAngle = 0.0f;
            }
        }*/
    }
    protected void updateScene()
    {
/*        // if the sun/moon is rising, and it isn't at its highest,
        // then increment the sun/moon's Y offset
        if(risingSunMoon && currentSunMoonY < highestSunMoonY)
        {   currentSunMoonY += 1.0f * getAnimationScale();
        }
        // else if the sun/moon is falling, and it isn't at its lowest,
        // then decrement the sun/moon's Y offset
        else if(!risingSunMoon && currentSunMoonY > lowestSunMoonY)
        {   currentSunMoonY -= 1.0f * getAnimationScale();
        }*/
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
            GL11.glScalef(50.0f, 2.0f, 40.0f);
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
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,backGroundTexture.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(40.0f,4.0f,-100.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(300.0f, 200.0f, 90.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the back Right plane
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
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,backGroundTexture.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(200.0f, 200.0f, 20.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the planet 1
        GL11.glPushMatrix();
        {
        	Planet planet = new Planet();
        	planet.DrawPlanet(4.0f, 7.0f, -19.0f, plantTexture);
        }
        GL11.glPopMatrix();
        
        // draw the planet 2
        GL11.glPushMatrix();
        {
        	Planet planet = new Planet();
        	planet.DrawPlanet(2.0f, 4.0f, -16.0f, plantTexture);
        }
        GL11.glPopMatrix();
        
        // draw the tree 1
        GL11.glPushMatrix();
        {
			 Tree tree = new Tree();
			 tree.drawTree(0.0f, -1.0f, -11.0f);
        }
        GL11.glPopMatrix();
        
        // draw the tree2
        GL11.glPushMatrix();
        {
			 Tree tree1 = new Tree();
			 tree1.drawTree(3.0f, -1.0f, -15.0f);
        }
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        {
			 Starfish starfish = new Starfish();
			 starfish.Draw(2.0f, 1.0f, -5.0f, starFishTexture);
        }
        GL11.glPopMatrix();
        
        // draw the house
        GL11.glPushMatrix();
        {

	        // how shiny are the front faces of the house (specular exponent)
	        float houseFrontShininess  = 2.0f;
	        // specular reflection of the front faces of the house
	        float houseFrontSpecular[] = {1.0f, 0.0f, 0.0f, 1.0f};
	        // diffuse reflection of the front faces of the house
	        float houseFrontDiffuse[]  = {0.6f, 0.2f, 0.2f, 1.0f};
	        
	        // set the material properties for the house using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));

	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glBindTexture(GL11.GL_TEXTURE_2D,BrickTexture.getTextureID());
	        
            // position and scale of the object
	        GL11.glTranslatef(4.0f, -0.3f, -15.0f);
	        GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);
	        GL11.glScalef(1.0f, 1.0f, 1.0f);
	       
	        // draw the base of the house using its display list
	        GL11.glCallList(houseList);
	        
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
        }
        GL11.glPopMatrix();
        
        //Robot parts that make up the Robot
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0.0f, -0.5f, -2.0f);
            GL11.glRotatef(32.0f, 0.0f, 1.0f, .0f);
            GL11.glScalef(0.2f, 0.2f, 0.2f);
	        
	        // set the material properties for the robot using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, RobotFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(RobotFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(RobotFrontDiffuse));

	        // draw the base of the robot using its display list
	        GL11.glCallList(DRobot);
	        

        }
        GL11.glPopMatrix();
    }
    
    protected void cleanupScene()
    {// empty
    }
    
    
    private void resetAnimations()
    {
/*        // reset all attributes that are modified by user controls or animations
        currentSunMoonY = highestSunMoonY;
        risingSunMoon = true;*/
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
    
    private void drawUnitCube()
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v2 = new Vertex(-0.5f,  0.5f,  0.5f);
        Vertex v3 = new Vertex( 1.5f,  0.5f,  0.5f);
        Vertex v4 = new Vertex( 1.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  0.5f, -0.5f);
        Vertex v7 = new Vertex( 1.5f,  0.5f, -0.5f);
        Vertex v8 = new Vertex( 1.5f, -0.5f, -0.5f);


        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
            
            GL11.glTexCoord2f(4.0f,4.0f);
            v3.submit();
            GL11.glTexCoord2f(0.0f,4.0f);
            v2.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v1.submit();
            GL11.glTexCoord2f(4.0f,0.0f);
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(4.0f,4.0f);
            v2.submit();
            GL11.glTexCoord2f(0.0f,4.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v5.submit();
            GL11.glTexCoord2f(4.0f,0.0f);
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
            
            GL11.glTexCoord2f(4.0f,4.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,4.0f);
            v3.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            GL11.glTexCoord2f(4.0f,0.0f);
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
            
            GL11.glTexCoord2f(4.0f,4.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,4.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v2.submit();
            GL11.glTexCoord2f(4.0f,0.0f);
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
            
            GL11.glTexCoord2f(4.0f,4.0f);
            v4.submit();
            GL11.glTexCoord2f(0.0f,4.0f);
            v1.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v5.submit();
            GL11.glTexCoord2f(4.0f,0.0f);
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            
            GL11.glTexCoord2f(4.0f,4.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,4.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v8.submit();
            GL11.glTexCoord2f(4.0f,0.0f);
            v5.submit();
        }
        GL11.glEnd();
    }
    
    
}