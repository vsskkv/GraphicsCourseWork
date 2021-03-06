package Designer;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of 
 * a new 3D object. Replace the content of the drawUnitShape() method with
 * your own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view:
 *   - TAB		switch between vertex, wireframe and full polygon modes
 *   - UP		move the shape away from the viewer
 *   - DOWN     move the shape closer to the viewer
 *   - X        rotate the camera around the x-axis (clockwise)
 *   - Y or C   rotate the camera around the y-axis (clockwise)
 *   - Z        rotate the camera around the z-axis (clockwise)
 *   - SHIFT    keep pressed when rotating to spin anti-clockwise
 *   - A 		Toggle colour (only if using submitNextColour() to specify colour)
 *   - SPACE	reset the view to its initial settings
 *  
 * @author Remi Barillec
 *
 */
public class ShapeDesigner extends AbstractDesigner {
	
	/** Main method **/
	public static void main(String args[])
    {   
		new ShapeDesigner().run( WINDOWED, "Designer", 0.01f);
    }
	
	/** Draw the shape **/
	    protected void drawUnitShape()
	    {
	    	DrawRobotLegLeft();
	    	DrawRobotLegRight();
	    	DrawRobotBody();
	    	DrawRobotArmLeft();
	    	DrawRobotArmRight();
	    	DrawRobotNeck(); 
	    	DrawRobotHead();
	    	
	    	//DrawStarFish(Colour.BLUE);
	    }


    
    private void DrawRobotLegLeft() {
    	
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
    
    private void DrawRobotLegRight() {
    	
    	//draw Right leg of robot 
        Vertex v9 = new Vertex( 1.5f, -0.5f,  0.5f);
        Vertex v10 = new Vertex( 1.5f,  0.5f,  0.5f);
        Vertex v11 = new Vertex( 2.5f,  0.5f,  0.5f);
        Vertex v12 = new Vertex( 2.5f, -0.5f,  0.5f);
        
        Vertex v13 = new Vertex( 1.5f, -0.5f, -1.5f);
        Vertex v14 = new Vertex( 1.5f,  0.5f, -1.5f);
        Vertex v15 = new Vertex( 2.5f,  0.5f, -1.5f);
        Vertex v16 = new Vertex( 2.5f, -0.5f, -1.5f);
        
        GL11.glBegin(GL11.GL_POLYGON);
        {        	
            new Normal(v11.toVector(),v10.toVector(),v9.toVector(),v12.toVector()).submit();
            
            v11.submit();
            v10.submit();
            v9.submit();
            v12.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	
            new Normal(v10.toVector(),v14.toVector(),v13.toVector(),v9.toVector()).submit();
            
        	v10.submit();
            v14.submit();
            v13.submit();
            v9.submit();
            
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v15.toVector(),v11.toVector(),v12.toVector(),v16.toVector()).submit();
            
            v15.submit();
            v11.submit();
            v12.submit();
            v16.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v15.toVector(),v14.toVector(),v10.toVector(),v11.toVector()).submit();
            
            v15.submit();
            v14.submit();
            v10.submit();
            v11.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v12.toVector(),v9.toVector(),v13.toVector(),v16.toVector()).submit();
            
            v12.submit();
            v9.submit();
            v13.submit();
            v16.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	
            new Normal(v14.toVector(),v15.toVector(),v16.toVector(),v13.toVector()).submit();
            
            v14.submit();
            v15.submit();
            v16.submit();
            v13.submit();
        }
        GL11.glEnd();
    }
    
    private void DrawRobotBody() {
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
    
    private void DrawRobotArmLeft() {
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
    
    private void DrawRobotArmRight() {
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
    
    private void DrawRobotNeck() {
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
    
    private void DrawRobotHead() {
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
            
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
    private void DrawStarFish(Colour bottom) {
    	/*Vertex v1 = new Vertex(0.7f, -0.5f, 1.0f);
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

		GL11.glBegin(GL11.GL_POLYGON);
		{	
		bottom.submit();
		
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();
			v9.submit();
			v10.submit();
			v1.submit();
			
		}
		GL11.glEnd();
		
        GL11.glBegin(GL11.GL_POLYGON);
        {            
            v11.submit();
            v1.submit();
            v2.submit();
            //v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {            
            v11.submit();
            v3.submit();
            v4.submit();
            //v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {            
            v11.submit();
            v5.submit();
            v6.submit();
            //v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {            
            v11.submit();
            v7.submit();
            v8.submit();
            //v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {            
            v11.submit();
            v9.submit();
            v10.submit();
            //v11.submit();
        }
        GL11.glEnd();
    }*/
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

		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			
			new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v4.toVector(), v5.toVector(), v6.toVector(), v7.toVector(), v8.toVector(), v9.toVector(), v10.toVector()).submit();
		
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();
			v9.submit();
			v10.submit();
			//v1.submit();
			
		}
		GL11.glEnd();
		
        GL11.glBegin(GL11.GL_POLYGON);
        {   
        	
        	new Normal(v11.toVector(), v1.toVector(), v2.toVector()).submit();
        	
            v11.submit();
            v1.submit();
            v2.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	
        	new Normal(v11.toVector(), v3.toVector(), v4.toVector()).submit();
        	
            v11.submit();
            v3.submit();
            v4.submit();
            v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {        
        	
        	new Normal(v11.toVector(), v5.toVector(), v6.toVector()).submit();
        	
            v11.submit();
            v5.submit();
            v6.submit();
            v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	new Normal(v11.toVector(), v7.toVector(), v8.toVector()).submit();
        	
            v11.submit();
            v7.submit();
            v8.submit();
            v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {     
        	
        	new Normal(v11.toVector(), v9.toVector(), v10.toVector()).submit();
            v11.submit();
            v9.submit();
            v10.submit();
            v11.submit();
        }
        GL11.glEnd();
    }
}