package GraphicsLab;

import org.lwjgl.BufferUtils;

/** Creates a FloatBuffer from an array of floats **/
public class FloatBuffer {
	
	public static java.nio.FloatBuffer fb;
	
	public static java.nio.FloatBuffer wrap(float[] buffer) {
		if (fb == null)
		{
			fb = BufferUtils.createFloatBuffer(buffer.length);
		}
		fb.put(buffer).flip();
		return fb;
	}
}
