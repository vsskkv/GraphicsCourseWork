package Examples.AnimatedPerson;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

/**
 * Provides the base class for the various parts of a Person,
 * including some common/helper functionality
 * 
 * @author Anthony Jones and Dan Cornford
 */
public abstract class PersonPart
{
	/** indicates a part on the right side of a Person */
	public static final int RIGHT_SIDE = 0;
	/** indicates a part on the left side of a Person */
	public static final int LEFT_SIDE  = 1;
	
    /**
     * Helper method for drawing parts as scaled unit cubes
     * @param width the width (x extent) of the part
     * @param length the length (y extent) of the part
     * @param depth the depth (z extent) of the part
     */
    protected final void drawPart(float width, float length, float depth)
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(width,length,depth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
    /**
     * Helper method for drawing joints as sphere objects
     * @param size the radius of the sphere
     */
    protected final void drawJoint(float size)
    {
        GL11.glPushMatrix();
        {   new Sphere().draw(size,10,10);
        }
        GL11.glPopMatrix();
    }
}
