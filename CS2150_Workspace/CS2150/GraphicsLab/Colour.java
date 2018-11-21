package GraphicsLab;
import org.lwjgl.opengl.GL11;

/**
 * Encapsulates the concept of a colour consisting of red, green and blue components
 *
 * @author Anthony Jones and Dan Cornford
 */
public class Colour
{
	// some commonly used colours
	public static final Colour RED   = new Colour(1.0f,0.0f,0.0f);
	public static final Colour GREEN = new Colour(0.0f,1.0f,0.0f);
	public static final Colour BLUE  = new Colour(0.0f,0.0f,1.0f);
	
	public static final Colour YELLOW  = new Colour(1.0f,1.0f,0.0f);
	public static final Colour PINK    = new Colour(1.0f,0.0f,1.0f);
	public static final Colour CYAN    = new Colour(0.0f,1.0f,1.0f);
	
	public static final Colour BLACK = new Colour(0.0f,0.0f,0.0f);
	public static final Colour WHITE = new Colour(1.0f,1.0f,1.0f);
	
	//public static final Colour ORANGE = new Colour(238,154,0);
	
	/**
	 * Constructs a Colour object from its RGB components with a float in the range 0-1.
	 * @param red The Colour's red component 
	 * @param green The Colour's green component
	 * @param blue The Colour's blue component
	 */
	public Colour(float red, float green, float blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	/**
	 * Constructs a Colour object from its RGB components as integers
	 * @param red The Colour's red component (int 0-255)
	 * @param green The Colour's green component (int 0-255)
	 * @param blue The Colour's blue component (int 0-255)
	 */
	public Colour(int red, int green, int blue)
	{
		this.red = ((float) red)/255.0f;
		this.green = ((float) green)/255.0f;
		this.blue = ((float) blue)/255.0f;
	}
	
	/**
	 * Submits this Colour to OpenGL using an immediate mode call
	 */
	public final void submit()
	{	GL11.glColor3f(red, green, blue);
	}

	/** the red component of this colour */
	private float red;
	/** the green component of this colour */
	private float green;
	/** the blue component of this colour */
	private float blue;
}
