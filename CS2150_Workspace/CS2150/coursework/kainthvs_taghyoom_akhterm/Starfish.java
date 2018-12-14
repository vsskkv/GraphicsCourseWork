/** 
 * FileName: CS2150Coursework.java
 * Brief: This class create all the necessary function for the starfish.
 * Author: Vikram Kainth Singh, Melika Taghyoon
 * Created: 22/11/2018
 */

package coursework.kainthvs_taghyoom_akhterm;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This is a object called starfish
 * Here we have the object that will draw the starfish and the after apply textures, lighting and TRsformations to it.
 * */
public class Starfish {
	
	public Starfish() {
		
	}
	
	/**
	 * The starfish is drawn
	 * @param xTranslate: Translates the x of the object to anywhere where the call is called.
	 * @param yTranslate: Translates the y of the object to anywhere where the call is called.
	 * @param zTranslate: Translates the z of the object to anywhere where the call is called.
	 * 
	 * @param text: Apply the texture to the starfish
	 * 
	 * Lighting is applied here too to make the surface shiny and not reflect too much
	 * Object is drawn
	 * */
	public void Draw(float xTranslate, float yTranslate, float zTranslate, Texture text) {
        // position and scale of the object
        GL11.glTranslatef(xTranslate, yTranslate, zTranslate);
        GL11.glScalef(0.4f, 0.4f, 0.4f);
        GL11.glRotatef(80, 0.0f, 1.0f, 1.0f);
        
		// disable lighting calculations so that they don't affect
		// the appearance of the texture
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		//GL11.glDisable(GL11.GL_LIGHTING);
		// change the geometry colour to white so that the texture
		// is bright and details can be seen clearly
		Colour.WHITE.submit();
                
        // how shiny are the front faces of the house (specular exponent)
        float FishFrontShininess  = 20.0f;
        // specular reflection of the front faces of the house
        float FishFrontSpecular[] = {1.0f, 1.0f, 1.0f, 2.0f};
        // diffuse reflection of the front faces of the house
        float FishFrontDiffuse[]  = {0.6f, 0.2f, 0.2f, 1.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, FishFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(FishFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(FishFrontDiffuse));
        
        DrawStarFish(text);
        GL11.glPopAttrib();
	}

	/**
	 * @param text: Texture to be applied to the selected points
	 * 
	 * draws the points of the starfish from point v1 to v12
	 * then connects the vertex together and applys the texture to the points
	 * */
    private void DrawStarFish(Texture text) {
    	Vertex v1 = new Vertex(0.7f, -0.5f, 1.0f);
		Vertex v2 = new Vertex(1.2f, -0.5f, 1.2f);
		Vertex v3 = new Vertex(1.8f, -0.5f, 1.0f);
		Vertex v4 = new Vertex(1.7f, -0.5f, 1.5f);
		Vertex v5 = new Vertex(2.0f, -0.5f, 1.9f);
		Vertex v6 = new Vertex(1.5f, -0.5f, 2.0f);
		Vertex v7 = new Vertex(1.2f, -0.5f, 2.5f);
		Vertex v8 = new Vertex(1.0f, -0.5f, 2.0f);
		Vertex v9 = new Vertex(0.5f, -0.5f, 1.9f);
		Vertex v10 = new Vertex(0.9f, -0.5f, 1.5f);
		
		Vertex v11 = new Vertex(1.2f, -0.2f, 1.7f); 
		
		Vertex v12 = new Vertex(1.2f, -0.8f, 1.7f);

        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	
        	new Normal(v11.toVector(), v9.toVector(), v10.toVector(), v1.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v11.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v9.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v10.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v1.submit();
            
        }
        GL11.glEnd();	        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	new Normal(v11.toVector(), v7.toVector(), v8.toVector(), v9.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v11.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v7.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v8.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v9.submit();

        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {        
        	
        	new Normal(v11.toVector(), v5.toVector(), v6.toVector(), v7.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v11.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v5.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v6.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v7.submit();

        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	
        	new Normal(v11.toVector(), v3.toVector(), v4.toVector(), v5.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v11.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v3.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v4.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v5.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {   
        	
        	new Normal(v11.toVector(), v1.toVector(), v2.toVector(), v3.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v11.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v1.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v2.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v3.submit();
        }
        GL11.glEnd();
        
        //second half
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	
        	new Normal(v1.toVector(), v10.toVector(), v9.toVector(), v12.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v1.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v10.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v9.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v12.submit();
            
        }
        GL11.glEnd();	        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	new Normal(v11.toVector(), v9.toVector(), v8.toVector(), v7.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v12.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v9.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v8.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v7.submit();

        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {        
        	
        	new Normal(v12.toVector(), v7.toVector(), v6.toVector(), v5.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v12.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v7.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v6.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v5.submit();

        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	
        	new Normal(v12.toVector(), v5.toVector(), v4.toVector(), v3.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v12.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v5.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v4.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v3.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {   
        	
        	new Normal(v12.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v12.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v3.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v2.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v1.submit();
        }
        GL11.glEnd();

    }
}
