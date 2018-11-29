package coursework;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import GraphicsLab.FloatBuffer;

public class Planet {
	
	public void DrawPlanet(float xTranslate, float yTranslate, float zTranslate) {
	    // how shiny are the front faces of the moon (specular exponent)
	    float moonFrontShininess  = 2.0f;
	    // specular reflection of the front faces of the moon
	    float moonFrontSpecular[] = {0.6f, 0.6f, 0.5f, 1.5f};
	    // diffuse reflection of the front faces of the moon
	    float moonFrontDiffuse[]  = {0.6f, 10.0f, 0.6f, 1.0f};
	
	    // set the material properties for the sun using OpenGL
	    GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, moonFrontShininess);
	    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(moonFrontSpecular));
	    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(moonFrontDiffuse));
	
	    // position and draw the moon using a sphere quadric object
	    GL11.glTranslatef(xTranslate, yTranslate, zTranslate);
	    new Sphere().draw(0.5f,10,10);
	} 
}
