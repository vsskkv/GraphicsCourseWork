/* 
 * FileName: CS2150Coursework.java
 * Brief: This class create all the necessary function for the planets.
 * Author: Vikram Singh Kainth Melika Taghyoon
 * Created: 22/11/2018
 */

package coursework.kainthvs_taghyoom_akhterm;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;

public class Planet {
	
	/**
	 * The starfish is drawn
	 * @param xTranslate: Translates the x of the object to anywhere where the call is called.
	 * @param yTranslate: Translates the y of the object to anywhere where the call is called.
	 * @param zTranslate: Translates the z of the object to anywhere where the call is called.
	 * 
	 * @param size: size of planet to be drawn, using the diameter
	 * 
	 * @param text: Apply the texture to the Planet
	 * 
	 * Lighting is applied here too to make the surface shiny and not reflect too much
	 * Object is drawn
	 * */
	public void DrawPlanet(float xTranslate, float yTranslate, float zTranslate, Texture text, float size) {         

	   // Set The Texture Generation Mode For S To Sphere Mapping 
	   GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);           
	   GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
	   
	    GL11.glTranslatef(xTranslate, yTranslate, zTranslate);
	    new Sphere().draw(size,10,10);
	    GL11.glDisable(GL11.GL_TEXTURE_2D);
	} 
	
	/**
	 * @param text: apply texture to planet
	 * @param size: size of planet that needs to be drawn
	 * 
	 * Lighting is applied to the planet to make it glow and look right and bright.
	 * */
    public void drawBody(Texture text, float size)
    {   
	    // position and draw the moon using a sphere quadric object
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D,text.getTextureID());
	    
        // how shiny are the front faces of the house (specular exponent)
        float PlanetFrontShininess  = 40.0f;
        // specular reflection of the front faces of the house
        float PlanetFrontSpecular[] = {1.0f, 1.0f, 1.0f, 2.0f};
        // diffuse reflection of the front faces of the house
        float PlanetFrontDiffuse[]  = {1.0f, 1.0f, 1.0f, 1.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, PlanetFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(PlanetFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(PlanetFrontDiffuse));

	   // enable the texture space S,T
	   GL11.glEnable(GL11.GL_TEXTURE_GEN_S); 
	   GL11.glEnable(GL11.GL_TEXTURE_GEN_T);         

	   // Set The Texture Generation Mode For S To Sphere Mapping 
	   GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);           
	   GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
	   
        new Sphere().draw(size, 20, 20);
        GL11.glPopAttrib();
        GL11.glDisable(GL11.GL_TEXTURE_2D);

    }
}
