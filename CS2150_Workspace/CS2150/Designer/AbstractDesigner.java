package Designer;

import java.util.Date;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector4f;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import GraphicsLab.GraphicsLab;
import GraphicsLab.Vertex;

/**
 * Base class for shape designer
 * 
 * @author Remi Barillec
 *
 */
public abstract class AbstractDesigner extends GraphicsLab {
	
	private float dist;
	private float rotx, roty, rotz;
	
	private static final int VM_POINT = 0;
	private static final int VM_WIRE = 1;
	private static final int VM_FULL = 2;
	private int viewMode;
	private static final Colour colours[] = {
		Colour.BLUE, Colour.CYAN, Colour.GREEN, 
		Colour.PINK, Colour.RED, Colour.WHITE, Colour.YELLOW };
	private static final String colourNames[] = {
		"BLUE", "CYAN", "GREEN", "PINK", "RED", "WHITE", "YELLOW" };
	private static int colourIndex = 0;
	private boolean useColours;

	// Display lists for the shape (white or colour)
	private final int shapeListWhite = 1;
	private final int shapeListColour = 2;
	
	// Time at which a key was last pressed - this is needed to avoid multiple
	// key press events
	public long keyLastPressed = 0L;     
	public static final long keyWait = 120L;
	
	public AbstractDesigner() {
		useColours = false;
		setViewingAxisDistance(5f);
		resetCamera();
	}
	
	protected abstract void drawUnitShape();
	
	/** Submit the next colour in the array **/
	protected void submitNextColour() {
		if (useColours) {
			// Submit current Colour
			System.out.println("Face " + (colourIndex+1) + ": " + colourNames[colourIndex]);
			colours[colourIndex].submit();
	
			// Increment index
			colourIndex = (colourIndex+1) % colours.length;
		} else {
			Colour.WHITE.submit();
		}
	}
	
	/** Reset the camera **/
	public void resetCamera() {
		viewMode = VM_WIRE;
		dist = 10;
		rotx = 0;
		roty = 0;
		rotz = 0;
	}
	
	/** Create display lists **/
	protected void initScene()
    {
		GL11.glPointSize(3);
		
		// Display list for the shape (white)
		GL11.glNewList(shapeListWhite, GL11.GL_COMPILE);
        {
        	useColours = false;
        	Colour.WHITE.submit();
        	drawUnitShape();
        }
        GL11.glEndList();
        
        // Display list for the shape (colour)
        GL11.glNewList(shapeListColour, GL11.GL_COMPILE);
        {
        	useColours = true;
        	Colour.WHITE.submit();     	
        	drawUnitShape();
        }
        GL11.glEndList();
        
        useColours = false;

    }
	
	/** Handle input **/
    protected void checkSceneInput()
    {
    	long now = (new Date()).getTime();

    	setViewingAxis(false);  // Don't let GraphicsLab draw the axes (we'll draw them ourselves)
    	
    	float delta =  1f; //(float) Math.PI/180f;
    	
    	if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
    		delta = -delta;
    	}
    	
    	// Rotate the camera around the x axis
    	if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
    		resetCamera();
    	}
    	
    	// Rotate the camera around the x axis
		if(Keyboard.isKeyDown(Keyboard.KEY_X)) {
			rotx += delta * getAnimationScale();
		}
		
		// Rotate the camera around the y axis
		if(Keyboard.isKeyDown(Keyboard.KEY_Y) || Keyboard.isKeyDown(Keyboard.KEY_C)) {
			roty += delta * getAnimationScale();
		}

		// Rotate the camera around the z axis
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			rotz += delta * getAnimationScale();
		}
		
		// Move towards the object
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			dist += 0.1 * getAnimationScale();
			if (dist > 20f) dist = 20f; 
		}

		// Move away from the object
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			dist -= 0.1 * getAnimationScale();
			if (dist< 0.5f) dist = 0.5f; 
		}
    	
    	// Prevent multiple toggle when key pressed for too long
    	if (now - keyLastPressed < keyWait) {
    		return;
    	} else {
    		keyLastPressed = now;
    	}
		    	
    	// Toggle point / lines / full drawing mode
    	if(Keyboard.isKeyDown(Keyboard.KEY_TAB))
        {
    		// Rotate through view mode options
    		viewMode = (viewMode+1) % 3;
        }
    	
    	// Toggle colour
    	if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
    		useColours = !useColours;
        }
    }
    
    protected float[] matFromQuat(Quaternion q) {
    	q.normalise();
    	
    	float x = q.getX();
    	float y = q.getY();
    	float z = q.getZ();
    	float w = q.getW();
    	
    	float[] mat = { 
    		1.0f - 2.0f * (y * y + z * z),
    		2.0f * (x * y + z * w),
			2.0f * (x * z - y * w),
			0.0f,
			
			2.0f * (x * y - z * w),
			1.0f - 2.0f * (x * x + z * z),
			2.0f * (z * y + x * w),
			0.0f,
			
			2.0f * (x * z + y * w),
			2.0f * (y * z - x * w),
			1.0f - 2.0f * (x * x + y * y),
			0.0f,
			
			0f, 0f, 0f, 1f
    	};
    			
        return mat;
    }
    
    protected float[] computeRotMatrix() {
    	Quaternion roll  = new Quaternion();
    	Quaternion pitch = new Quaternion();
    	Quaternion yaw   = new Quaternion();
    	Quaternion yp = new Quaternion();
    	Quaternion mv = new Quaternion();
    	
    	roll.setFromAxisAngle(new Vector4f(1, 0, 0, rotx));
    	pitch.setFromAxisAngle(new Vector4f(0, 1, 0, roty));
    	yaw.setFromAxisAngle(new Vector4f(0, 0, 1, rotz));
    	
    	Quaternion.mul(pitch, yaw, yp);
    	Quaternion.mul(roll, yp, mv);
    	
    	return matFromQuat( mv );
    }
    
    /** Update camera position and drawing mode **/
    protected void updateScene()
    {
    	// Reset colour index
    	colourIndex = 0;
    	
    	switch( viewMode ) {
		case VM_POINT:
			GL11.glDisable(GL11.GL_CULL_FACE);   // No back face culling
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_POINT);
			break;
		case VM_WIRE:
			GL11.glDisable(GL11.GL_CULL_FACE);   // No back face culling
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			break;
		case VM_FULL:
			GL11.glEnable(GL11.GL_CULL_FACE);   // No back face culling
			GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
			break;
		}

    }
    
    /** Draw the scene **/
    protected void renderScene()
    {
    	GL11.glLoadIdentity();
    	
        // Position the camera outside the shape
		GLU.gluLookAt(0, 0, dist,     /* Camera position */
				      0,0,0,          /* Target */
				      0, 1, 0         /* Up vector */ 
				      );
    	
    	GL11.glPushMatrix(); 
    	{
    		GL11.glRotatef(rotx, 1, 0, 0);
		
    		GL11.glPushMatrix(); 
    		{
    			GL11.glRotatef(roty, 0, 1, 0);
    			
    			GL11.glPushMatrix();
    			{
    				GL11.glRotatef(rotz, 0, 0, 1);
		
    				drawAxisLines();
		
					if (useColours) {
						GL11.glCallList(shapeListColour);
					} else {
						GL11.glCallList(shapeListWhite);
					}
			
    			} 
    			GL11.glPopMatrix();
    		}
    		GL11.glPopMatrix();
    	}
    	GL11.glPopMatrix();
    }
    
    protected void cleanupScene()
    {// empty
    }

}

