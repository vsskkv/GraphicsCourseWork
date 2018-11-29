package coursework;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;

import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

public class Tree {
	
	public Tree() {
		
	}

	public void drawTree(float xTranslate, float yTranslate, float zTranslate) {
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
	    GL11.glTranslatef(xTranslate, yTranslate, zTranslate);
	    
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
}