/* 
 * FileName: CS2150Coursework.java
 * Brief: This class create all the necessary function for the robot.
 * Author: Vikram Kainth Singh, Melika Taghyoon
 * Created: 22/11/2018
 */

package coursework.kainthvs_taghyoom_akhterm;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.*;

/**
 * Robot object is drawn
 * */
public class Robot {
	
	public Robot() {
	}
	/**
	 * @param text: Textures for the robots parts done in all the functions provided below
	 * The robot is drawn and all Textures are applied. Each method is public so it can be called and drawn separately
	 * */
	public void DrawRobotLegLeft(Texture text) {
	    	
	    	//draw left leg of robot 
	        Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
	        Vertex v2 = new Vertex(-0.5f,  0.5f,  0.5f);
	        Vertex v3 = new Vertex( 0.5f,  0.5f,  0.5f);
	        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
	        
	        Vertex v5 = new Vertex(-0.5f, -0.5f, -1.5f);
	        Vertex v6 = new Vertex(-0.5f,  0.5f, -1.5f);
	        Vertex v7 = new Vertex( 0.5f,  0.5f, -1.5f);
	        Vertex v8 = new Vertex( 0.5f, -0.5f, -1.5f);
	        
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
	    
	public void DrawRobotLegRight(Texture text) {
	    	
	    	//draw Right leg of robot 
	        Vertex v1 = new Vertex( 1.5f, -0.5f,  0.5f);
	        Vertex v2 = new Vertex( 1.5f,  0.5f,  0.5f);
	        Vertex v3 = new Vertex( 2.5f,  0.5f,  0.5f);
	        Vertex v4 = new Vertex( 2.5f, -0.5f,  0.5f);
	        
	        Vertex v5 = new Vertex( 1.5f, -0.5f, -1.5f);
	        Vertex v6 = new Vertex( 1.5f,  0.5f, -1.5f);
	        Vertex v7 = new Vertex( 2.5f,  0.5f, -1.5f);
	        Vertex v8 = new Vertex( 2.5f, -0.5f, -1.5f);
	        
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
	    
	public void DrawRobotBody(Texture robotFront) {
			
	        Vertex v1 = new Vertex(-0.5f,  0.5f,  0.5f);
	        Vertex v2 = new Vertex(-0.5f,  3.5f,  0.5f);
	        Vertex v3 = new Vertex( 2.5f,  3.5f,  0.5f);
	        Vertex v4 = new Vertex( 2.5f,  0.5f,  0.5f);
	        
	        Vertex v5 = new Vertex(-0.5f,  0.5f, -0.5f);
	        Vertex v6 = new Vertex(-0.5f,  3.5f, -0.5f);
	        Vertex v7 = new Vertex( 2.5f,  3.5f, -0.5f);
	        Vertex v8 = new Vertex( 2.5f,  0.5f, -0.5f);
	        
	        GL11.glBegin(GL11.GL_POLYGON);
	        {            
	            new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
	            GL11.glTexCoord2f(0.0f,0.0f);
	            v3.submit();
	            GL11.glTexCoord2f(1.0f,0.0f);
	            v2.submit();
	            GL11.glTexCoord2f(1.0f,1.0f);
	            v1.submit();
	            GL11.glTexCoord2f(0.0f,1.0f);
	            v4.submit();
	        }
	        GL11.glEnd();
	
	        // draw the left face:
	        GL11.glBegin(GL11.GL_POLYGON);
	        {
	        	
	            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
	            GL11.glTexCoord2f(0.0f,0.0f);
	        	v2.submit();
	        	GL11.glTexCoord2f(1.0f,0.0f);
	            v6.submit();
	            GL11.glTexCoord2f(1.0f,1.0f);
	            v5.submit();
	            GL11.glTexCoord2f(0.0f,1.0f);
	            v1.submit();
	            
	        }
	        GL11.glEnd();
	
	        // draw the right face:
	        GL11.glBegin(GL11.GL_POLYGON);
	        {
	            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
	            GL11.glTexCoord2f(0.0f,0.0f);
	            v7.submit();
	            GL11.glTexCoord2f(1.0f,0.0f);
	            v3.submit();
	            GL11.glTexCoord2f(1.0f,1.0f);
	            v4.submit();
	            GL11.glTexCoord2f(0.0f,1.0f);
	            v8.submit();
	        }
	        GL11.glEnd();
	
	        // draw the top face:
	        GL11.glBegin(GL11.GL_POLYGON);
	        {
	            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
	            GL11.glTexCoord2f(0.0f,0.0f);
	            v7.submit();
	            GL11.glTexCoord2f(1.0f,0.0f);
	            v6.submit();
	            GL11.glTexCoord2f(1.0f,1.0f);
	            v2.submit();
	            GL11.glTexCoord2f(0.0f,1.0f);
	            v3.submit();
	        }
	        GL11.glEnd();
	
	        // draw the bottom face:
	        GL11.glBegin(GL11.GL_POLYGON);
	        {
	            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
	            GL11.glTexCoord2f(0.0f,0.0f);
	            v4.submit();
	            GL11.glTexCoord2f(1.0f,0.0f);
	            v1.submit();
	            GL11.glTexCoord2f(1.0f,1.0f);
	            v5.submit();
	            GL11.glTexCoord2f(0.0f,1.0f);
	            v8.submit();
	        }
	        GL11.glEnd();
	
	        // draw the far face:
	        GL11.glBegin(GL11.GL_POLYGON);
	        {
	        	
	            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
	            GL11.glTexCoord2f(0.0f,0.0f);
	            v6.submit();
	            GL11.glTexCoord2f(1.0f,0.0f);
	            v7.submit();
	            GL11.glTexCoord2f(1.0f,1.0f);
	            v8.submit();
	            GL11.glTexCoord2f(0.0f,1.0f);
	            v5.submit();
	        }
	        GL11.glEnd();
	    }
	    
	public void DrawRobotArmLeft(Texture text) {
	    	Vertex v1 = new Vertex(-1.5f,  2.5f,  0.5f);
	        Vertex v2 = new Vertex(-1.5f,  3.5f,  0.5f);
	        Vertex v3 = new Vertex( -0.5f,  3.5f,  0.5f);
	        Vertex v4 = new Vertex( -0.5f,  2.5f,  0.5f);
	        
	        Vertex v5 = new Vertex(-1.5f,  2.5f, -1.5f);
	        Vertex v6 = new Vertex(-1.5f,  3.5f, -1.5f);
	        Vertex v7 = new Vertex(-0.5f,  3.5f, -1.5f);
	        Vertex v8 = new Vertex(-0.5f,  2.5f, -1.5f);
	        
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
	    
	public void DrawRobotArmRight(Texture text) {
	    	Vertex v1 = new Vertex( 2.5f,  2.5f,  0.5f);
	        Vertex v2 = new Vertex( 2.5f,  3.5f,  0.5f);
	        Vertex v3 = new Vertex( 3.5f,  3.5f,  0.5f);
	        Vertex v4 = new Vertex( 3.5f,  2.5f,  0.5f);
	        
	        Vertex v5 = new Vertex( 2.5f,  2.5f, -1.5f);
	        Vertex v6 = new Vertex( 2.5f,  3.5f, -1.5f);
	        Vertex v7 = new Vertex( 3.5f,  3.5f, -1.5f);
	        Vertex v8 = new Vertex( 3.5f,  2.5f, -1.5f);
	        
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
	    
	public void DrawRobotNeck() {
	    	Vertex v1 = new Vertex( 0.5f,  3.5f,  0.5f);
	        Vertex v2 = new Vertex( 0.5f,  4.0f,  0.5f);
	        Vertex v3 = new Vertex( 1.5f,  4.0f,  0.5f);
	        Vertex v4 = new Vertex( 1.5f,  3.5f,  0.5f);
	        
	        Vertex v5 = new Vertex( 0.5f,  3.5f, -0.5f);
	        Vertex v6 = new Vertex( 0.5f,  4.0f, -0.5f);
	        Vertex v7 = new Vertex( 1.5f,  4.0f, -0.5f);
	        Vertex v8 = new Vertex( 1.5f,  3.5f, -0.5f);
	        
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
	    
	public void DrawRobotHead(Texture text) {
	       	Vertex v1 = new Vertex( -0.5f,  4.0f,  0.5f);
	        Vertex v2 = new Vertex( -0.5f,  5.5f,  0.5f);
	        Vertex v3 = new Vertex( 2.5f,  5.5f,  0.5f);
	        Vertex v4 = new Vertex( 2.5f,  4.0f,  0.5f);
	        
	        Vertex v5 = new Vertex( -0.5f,  4.0f, -0.5f);
	        Vertex v6 = new Vertex( -0.5f,  5.5f, -0.5f);
	        Vertex v7 = new Vertex( 2.5f,  5.5f, -0.5f);
	        Vertex v8 = new Vertex( 2.5f,  4.0f, -0.5f);
	        
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
