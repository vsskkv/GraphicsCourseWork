package GraphicsLab;

/**
 * Encapsulates the concept of a 3D vector
 * 
 * @author Anthony Jones and Dan Cornford
 */
public class Vector
{
    /**
     * Constructs a Vector object from its x, y and z components
     * @param x The Vertex's x component
     * @param y The Vertex's y component
     * @param z The Vertex's z component
     */
    public Vector(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Constructs a Vector object from a vertex, effectively constructing
     * the vector from the origin (0,0,0) to the given vertex in 3D space
     * @param vertex the vertex from which to construct a new Vector object
     */
    public Vector(Vertex vertex)
    {
        this.x = vertex.getX();
        this.y = vertex.getY();
        this.z = vertex.getZ();
    }
    
    /**
     * @return the x value of the vector 
     */
    public final float getX()
    {   return x;
    }
    /**
     * @return the y value of the vector 
     */
    public final float getY()
    {   return y;
    }
    /**
     * @return the z value of the vector 
     */
    public final float getZ()
    {   return z;
    }
    
    /**
     * Normalises this vector, so that the vector is of unit length
     */
    public final void normalise()
    {
        //compute the length of the normal
        double length = Math.sqrt(x*x + y*y + z*z);

        // now divide each component by the length
        x /= length;
        y /= length;
        z /= length;
    }

	/** the x component of this vector */
    private float x;
    /** the y component of this vector */
    private float y;
    /** the z component of this vector */
    private float z;
}
