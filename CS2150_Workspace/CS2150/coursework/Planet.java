package coursework;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;

public class Planet {
	
	public void DrawPlanet(float xTranslate, float yTranslate, float zTranslate, Texture text, float size) {
	    // how shiny are the front faces of the moon (specular exponent)
	    float moonFrontShininess  = 1.0f;
	    // specular reflection of the front faces of the moon
	    float moonFrontSpecular[] = {1.0f, 0.0f, 0.0f, 1.0f};
	    // diffuse reflection of the front faces of the moon
	    float moonFrontDiffuse[]  = {1.0f, 1.0f, 1.0f, 1.0f};
	
	    // set the material properties for the sun using OpenGL
	    GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, moonFrontShininess);
	    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(moonFrontSpecular));
	    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(moonFrontDiffuse));
	
	    // position and draw the moon using a sphere quadric object
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D,text.getTextureID());

	   // enable the texture space S,T
	   GL11.glEnable(GL11.GL_TEXTURE_GEN_S); 
	   GL11.glEnable(GL11.GL_TEXTURE_GEN_T);            

/*	   GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	   GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST); */           

	   // Set The Texture Generation Mode For S To Sphere Mapping 
	   GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);           
	   GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
	   
	    GL11.glTranslatef(xTranslate, yTranslate, zTranslate);
	    new Sphere().draw(size,10,10);
	    
	    GL11.glDisable(GL11.GL_TEXTURE_2D);
	} 
	
    public void drawBody(Texture text, float size)
    {
        
	   
	    // position and draw the moon using a sphere quadric object
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D,text.getTextureID());

	   // enable the texture space S,T
	   GL11.glEnable(GL11.GL_TEXTURE_GEN_S); 
	   GL11.glEnable(GL11.GL_TEXTURE_GEN_T);            

/*	   GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	   GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST); */           

	   // Set The Texture Generation Mode For S To Sphere Mapping 
	   GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);           
	   GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
	   
        new Sphere().draw(size, 20, 20);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
}
