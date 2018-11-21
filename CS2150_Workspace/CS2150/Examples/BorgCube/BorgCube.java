/* BorgCube.java
 * A spinning textured and lit Borg Cube that glows with a pulsating green glow
 * 29/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(17,1,17) Rx(90) T(0,0,-20)] Backdrop
 *  |
 *  +-- [Rxy(cubeSpin/2,cubeSpin) S(3,3,3) T(0,0,-10)] Borg Cube
 */
package Examples.BorgCube;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This program demonstrates the use of OpenGL to render a spinning textured "Borg Cube"
 * from the science fiction series Star Trek. The sample includes a textured cube, some
 * simple spinning animation for the scene, plus animated material properties
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 *
 * <p>Ported to Java including elements from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class BorgCube extends GraphicsLab
{
    /** ids for nearest, linear and mipmapped textures for the starry back plane */
    private Texture starTextures;
    /** ids for nearest, linear and mipmapped textures for the Borg cube */
    private Texture cubeTextures;
    /** the borg cube's current angle of rotation */
    private float cubeSpin = 0.0f;

    public static void main(String args[])
    {   new BorgCube().run(WINDOWED,"Borg Cube",0.01f);
    }

    protected void initScene() throws Exception
    {
        // load the textures
        starTextures = loadTexture("Examples/BorgCube/textures/stars.bmp");
        cubeTextures = loadTexture("Examples/BorgCube/textures/borg.bmp");

        // global ambient light level
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        // the first light for the scene is white...
        float diffuse0[]  = { 0.8f,  0.8f, 0.8f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 0.0f, 5.0f, 0.0f, 1.0f};

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

        // use a flat shade model for the cube - the appearance of the cube
        // will be mostly determined by the borg texture anyway
        GL11.glShadeModel(GL11.GL_FLAT);
    }
    protected void checkSceneInput()
    {// empty
    }
    protected void updateScene()
    {
        // increase the cube's angle of rotation
        cubeSpin += 2.0f * getAnimationScale();
    }
    protected void renderScene()
    {
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
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,starTextures.getTextureID());
            
            // position, scale and draw the back plane
            GL11.glTranslatef(0.0f,0.0f,-20.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScaled(17.0f, 1.0f, 17.0f);
            drawUnitPlane();
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the borg cube
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the borg cube (specular exponent)
            float cubeFrontShininess  = 20.0f;
            // specular reflection of the front faces of the borg cube
            float cubeFrontSpecular[] = {1.0f, 1.0f, 1.0f, 1.0f};
            // diffuse reflection of the front faces of the borg cube
            float cubeFrontDiffuse[]  = {0.8f, 0.8f, 0.8f, 1.0f};
            
            // animate the emissive property of the borg cube so that it
            // pulsates as the cube rotates
            double emission = java.lang.Math.sin(cubeSpin / 45.0f);
            // emission must always be positive, and in the range 0.0 - 1.0
            emission = java.lang.Math.abs(emission);
            // scale the emission so that we get values between 0.0 and 0.01
            emission /= 10.0;
            // emissive property of the borg cube's front faces - a pulsating green glow!
            float cubeFrontEmissive[] = {0.0f, (float)emission, 0.0f, 1.0f};

            
            // set the material properties for the borg cube using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, cubeFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(cubeFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(cubeFrontDiffuse));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_EMISSION, FloatBuffer.wrap(cubeFrontEmissive));

            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,cubeTextures.getTextureID());

            // rotate, scale, position and draw the borg cube
            GL11.glTranslatef(0.0f, 0.0f, -10.0f);
            GL11.glScalef(3.0f, 3.0f, 3.0f);
            GL11.glRotatef(cubeSpin, 0.5f, 1.0f, 0.0f);
            drawUnitCube();

            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }
        GL11.glPopMatrix();
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
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v3.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v2.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v1.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v5.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v3.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v2.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v4.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v5.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v8.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v5.submit();
        }
        GL11.glEnd();
    }
}
