package GraphicsLab;
import org.lwjgl.opengl.GL11;

/**
 * Encapsulates the concept of a 3D normal
 * 
 * @author Anthony Jones  and Dan Cornford
 */
public class Normal
{
	/**
	 * Constructs a Normal object from its x, y and z components
	 * @param x The Normal's x component
	 * @param y The Normal's y component
	 * @param z The Normal's z component
	 */
	public Normal(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		normalise();
	}
	/**
	 * Constructs a normal vector by taking 3 co-planar points (not co-linear):
	 * Based on computing two vectors in the plane, then taking the vector cross product
	 * between these to compute the normal; to get the sign right the vectors must be
	 * passed in anti-clockwise order.
	 * @param vec1
	 * @param vec2
	 * @param vec3
	 */
	public Normal(Vector vec1, Vector vec2, Vector vec3)
	{
	    // we will hard-wire this  - it is not pretty or clever, but easily understood.
	    // in theory we should pass in an array of vectors and compute these terms in a loop!
	    x = (vec1.getY()-vec2.getY())*(vec1.getZ()+vec2.getZ()) + 
	        (vec2.getY()-vec3.getY())*(vec2.getZ()+vec3.getZ()) + 
	        (vec3.getY()-vec1.getY())*(vec3.getZ()+vec1.getZ());
	    y = (vec1.getZ()-vec2.getZ())*(vec1.getX()+vec2.getX()) + 
	        (vec2.getZ()-vec3.getZ())*(vec2.getX()+vec3.getX()) + 
	        (vec3.getZ()-vec1.getZ())*(vec3.getX()+vec1.getX());
	    z = (vec1.getX()-vec2.getX())*(vec1.getY()+vec2.getY()) + 
	        (vec2.getX()-vec3.getX())*(vec2.getY()+vec3.getY()) + 
	        (vec3.getX()-vec1.getX())*(vec3.getY()+vec1.getY());
	    // now normalise the result to have length 1
	    normalise();
	}
	/**
	 * Constructs a normal vector by taking 4 co-planar points (not co-linear):
	 * Based on computing two vectors in the plane, then taking the vector cross product
	 * between these to compute the normal; to get the sign right the vectors must be
	 * passed in anti-clockwise order.
	 * @param vec1
	 * @param vec2
	 * @param vec3
	 * @param vec4
	 */
    public Normal(Vector vec1, Vector vec2, Vector vec3, Vector vec4)
    {
        // we will hard-wire this - it is not pretty or clever, but easily understood.
        // in theory we should pass in an array of vectors and compute these terms in a loop!
        x = (vec1.getY()-vec2.getY())*(vec1.getZ()+vec2.getZ()) +
            (vec2.getY()-vec3.getY())*(vec2.getZ()+vec3.getZ()) +
            (vec3.getY()-vec4.getY())*(vec3.getZ()+vec4.getZ()) +
            (vec4.getY()-vec1.getY())*(vec4.getZ()+vec1.getZ());
        y = (vec1.getZ()-vec2.getZ())*(vec1.getX()+vec2.getX()) +
            (vec2.getZ()-vec3.getZ())*(vec2.getX()+vec3.getX()) +
            (vec3.getZ()-vec4.getZ())*(vec3.getX()+vec4.getX()) +
            (vec4.getZ()-vec1.getZ())*(vec4.getX()+vec1.getX());
        z = (vec1.getX()-vec2.getX())*(vec1.getY()+vec2.getY()) +
            (vec2.getX()-vec3.getX())*(vec2.getY()+vec3.getY()) +
            (vec3.getX()-vec4.getX())*(vec3.getY()+vec4.getY()) +
            (vec4.getX()-vec1.getX())*(vec4.getY()+vec1.getY());
        // now normalise the result to have length 1
        normalise();
    }
    
    public Normal(Vector vec1, Vector vec2, Vector vec3, Vector vec4, Vector vec5, Vector vec6, Vector vec7, Vector vec8, Vector vec9, Vector vec10) {
    	x = (vec1.getY()-vec2.getY())*(vec1.getZ()+vec2.getZ()) +
            (vec2.getY()-vec3.getY())*(vec2.getZ()+vec3.getZ()) +
            (vec3.getY()-vec4.getY())*(vec3.getZ()+vec4.getZ()) +
            (vec4.getY()-vec5.getY())*(vec4.getZ()+vec5.getZ()) +
            (vec5.getY()-vec6.getY())*(vec5.getZ()+vec6.getZ()) +
            (vec6.getY()-vec7.getY())*(vec6.getZ()+vec7.getZ()) +
            (vec7.getY()-vec8.getY())*(vec7.getZ()+vec8.getZ()) +
            (vec8.getY()-vec9.getY())*(vec8.getZ()+vec9.getZ()) +
            (vec9.getY()-vec10.getY())*(vec9.getZ()+vec10.getZ())+
            (vec10.getY()-vec1.getY())*(vec10.getZ()+vec1.getZ());
    	
    	y = (vec1.getZ()-vec2.getZ())*(vec1.getX()+vec2.getX()) +
    	    (vec2.getZ()-vec3.getZ())*(vec2.getX()+vec3.getX()) +
    	    (vec3.getZ()-vec4.getZ())*(vec3.getX()+vec4.getX()) +
    	    (vec4.getZ()-vec5.getZ())*(vec4.getX()+vec5.getX()) +
    	    (vec5.getZ()-vec6.getZ())*(vec5.getX()+vec6.getX()) +
    	    (vec6.getZ()-vec7.getZ())*(vec6.getX()+vec7.getX()) +
    	    (vec7.getZ()-vec8.getZ())*(vec7.getX()+vec8.getX()) +
    	    (vec8.getZ()-vec9.getZ())*(vec8.getX()+vec9.getX()) +
    	    (vec9.getZ()-vec10.getZ())*(vec9.getX()+vec10.getX()) +
    	    (vec10.getZ()-vec1.getZ())*(vec10.getX()+vec1.getX());
    	
        z = (vec1.getX()-vec2.getX())*(vec1.getY()+vec2.getY()) +
            (vec2.getX()-vec3.getX())*(vec2.getY()+vec3.getY()) +
            (vec3.getX()-vec4.getX())*(vec3.getY()+vec4.getY()) +
            (vec4.getX()-vec5.getX())*(vec4.getY()+vec5.getY()) +
            (vec5.getX()-vec6.getX())*(vec5.getY()+vec6.getY()) +
            (vec6.getX()-vec7.getX())*(vec6.getY()+vec7.getY()) +
            (vec7.getX()-vec8.getX())*(vec7.getY()+vec8.getY()) +
            (vec8.getX()-vec9.getX())*(vec8.getY()+vec9.getY()) +
            (vec9.getX()-vec10.getX())*(vec9.getY()+vec10.getY()) +
            (vec10.getX()-vec1.getX())*(vec10.getY()+vec1.getY());
    }
	
    /**
     * @return the x value of the normal 
     */
    public final float getX()
    {   return x;
    }
    /**
     * @return the y value of the normal 
     */
    public final float getY()
    {   return y;
    }
    /**
     * @return the z value of the normal 
     */
    public final float getZ()
    {   return z;
    }
    
	/**
	 * Submits this Normal to OpenGL using an immediate mode call
	 */
	public final void submit()
	{	GL11.glNormal3f(x,y,z);
	}
	
	/**
	 * Normalises this normal, so that the normal is of unit length
	 */
	private void normalise()
	{
		//compute the length of the normal
        double length = Math.sqrt(x*x + y*y + z*z);

        // now divide each component by the length
        x /= length;
        y /= length;
        z /= length;
	}
	
    /** the x component of this normal */
    private float x;
    /** the y component of this normal */
    private float y;
    /** the z component of this normal */
    private float z;
}
