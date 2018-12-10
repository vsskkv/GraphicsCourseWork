package coursework;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

public class Starfish {
	
	
	
	public Starfish() {
		
	}
	
	public void Draw(float xTranslate, float yTranslate, float zTranslate, Texture text) {
        // position and scale of the object
        GL11.glTranslatef(xTranslate, yTranslate, zTranslate);
        //GL11.glScalef(1.0f, 1.0f, 1.0f);
        //GL11.glRotatef(0, 0.0f, 0.0f, 0.0f);
        
        // how shiny are the front faces of the house (specular exponent)
        float FishFrontShininess  = 1.0f;
        // specular reflection of the front faces of the house
        float FishFrontSpecular[] = {0.5f, 0.5f, 0.5f, 2.0f};
        // diffuse reflection of the front faces of the house
        float FishFrontDiffuse[]  = {0.6f, 0.2f, 0.2f, 5.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, FishFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(FishFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(FishFrontDiffuse));

        // draw the star fish
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,text.getTextureID());
        
        DrawStarFish();
        
        GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

    private void DrawStarFish() {
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
        	
        	new Normal(v1.toVector(), v10.toVector(), v9.toVector(), v11.toVector()).submit();
        	GL11.glTexCoord2f(0.0f, 1.0f);
            v1.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v10.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v9.submit();
            GL11.glTexCoord2f(1.0f,1.0f);
            v11.submit();
            
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
        
    }
}
