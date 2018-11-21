package GraphicsLab;
import org.lwjgl.opengl.GL11;

/**
 * Encapsulates the concept of a 3D vertex
 * 
 * @author Anthony Jones and Dan Cornford
 */
public class Vertex
{
	/**
	 * Constructs a Vertex object from its x, y and z components
	 * @param x The Vertex's x component
	 * @param y The Vertex's y component
	 * @param z The Vertex's z component
	 */
	public Vertex(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
    /**
     * @return the x value of the vertex 
     */
    public final float getX()
    {   return x;
    }
    /**
     * @return the y value of the vertex 
     */
    public final float getY()
    {   return y;
    }
    /**
     * @return the z value of the vertex 
     */
    public final float getZ()
    {   return z;
    }
	
	/**
	 * Submits this Vertex to OpenGL using an immediate mode call
	 */
	public final void submit()
	{	GL11.glVertex3f(x,y,z);
	}
	/**
	 * @return this vertex as a Vector object
	 */
	public final Vector toVector()
	{   return new Vector(this);
	}

	/** the x component of this vertex */
	private float x;
	/** the y component of this vertex */
	private float y;
	/** the z component of this vertex */
	private float z;
}
