package Examples.AnimatedPerson;
import org.lwjgl.opengl.GL11;

import GraphicsLab.*;

/**
 * A cube of unit length, width and height
 * 
 * @author Anthony Jones and Dan Cornford
 */
public class UnitCube
{
    /**
     * draws this unit cube object, using the current OpenGL material settings for its appearance
     */
    public void draw()
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v2 = new Vertex(-0.5f,  0.5f,  0.5f);
        Vertex v3 = new Vertex( 0.5f,  0.5f,  0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  0.5f, -0.5f);
        Vertex v7 = new Vertex( 0.5f,  0.5f, -0.5f);
        Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);

        // draw the near face:
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
}
