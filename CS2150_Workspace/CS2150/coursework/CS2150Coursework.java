/* 
 * FileName: CS2150Coursework.java
 * Brief: This class places all the object on the screen together, as well as that it determines all
 * the movement on the screen, so depending on the user's input it does different movements.
 * Author: Vikram Kainth Singh, Mahamuda Akhter, Melika Taghyoon
 * Created: 22/11/2018
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(0,-1,-10) T(50,2,40)] Ground plane
 *  |
 *  |
 *  +-- [T(40,4,-100) R(90,1,0,0) S(300,200,90)] Sky plane
 *  |
 *  |
 *  +-- [T(2,4,-16,)] Planet
 *  |
 *  |
 *  +-- [T(4,6,-18)] Pp
 *  |
 *  |
 *  +--[R((360* update * 0.05),0,1,0) T(1,0,0)] Pp
 *  |
 *  |
 *  +--[T(0,-1,-11)] Tree
 *  |
 *  |+--[T(3,-1,-15)] Tree1
 *  |
 *  |
 *  +--[T(0,-0.1,-5)] Startfish
 *  |
 *  |
 *  +--[T(currentXPos, -0.5, currentZPos) R(roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotBody
 *  |
 *  |
 *  +--[T(currentXPos, -0.5, currentZPos) R(roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotLeftleg
 *  |
 *  |
 *  +--[T(currentXPos, -0.5, currentZPos) R(roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotRightleg
 *  |
 *  |
 *  +--[T(currentXPos, -0.5, currentZPos) R(roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotneck
 *  |
 *  |
 *  +--[T(currentXPos, currentValue, currentZPos) R(roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotLeftarm
 *  |
 *  |
 *  +--[T(currentXPos, currentValue, currentZPos) R(roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotRightarm
 *  |
 *  |
 *  +--[T(currentXPos, -0.5, currentZPos) R(headSpin + roationAngle, 0, 1, 0) S(0.07, 0.07, 0.07)] robotHead
 */
package coursework;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 *  <p>
 * Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis,
 * respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down
 * cursor keys to increase or decrease the viewpoint's distance from the scene
 * origin
 * <li>Press W to make the robot go up
 * <li>Press S to make the robot go down
 * <li>Press A to make the robot turn left
 * <li>Press D to make the robot turn right
 * <li>Press P to play the animation
 * <li>Press Space to reset animation
 * </ul>
 *
 * <p>
 * Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class CS2150Coursework extends GraphicsLab {
	private final float RobotX = 0.0f;
	private final float RobotZ = -2.0f;
	private final float StarR = 0.0f;

	/** display list id for the unit plane, robot, stars */
	private final int planeList = 1;
	private final int robotList = 2;
	private final int starList = 3;

	/** ids for nearest, linear and mipmapped textures for the ground plane */
	private Texture groundTextures;
	/**
	 * ids for nearest, linear and mipmapped textures for the night time back
	 * background plane
	 * {@link https://fstoppers.com/news/japan-landed-space-rovers-aste0roid-and-first-pictures-are-here-292344}
	 */
	private Texture backGroundTexture;
	private Texture starFishTexture;
	private Texture controlTexture;
	private Texture robotFrontTexture;
	private Texture robotBackTexture;
	private Texture starTexture;
	
	// Textures for the planets 
	private Texture plantTexture;
	private Texture planet2Texture;
	private Texture planet3Texture;
	private Texture planet4Texture;
	private Texture planet5Texture;
	private Texture planet6Texture;
	private Texture planet7Texture;
	private Texture planet8Texture;

	//creations of robot instance
	private Robot robot1 = new Robot();

	//values for robot movement
	private float update = 0.0f;
	private float topValue = -0.5f;
	private float bottomValue = -0.58f;
	private float currentValue = -0.5f;
	private boolean reached = false;
	private boolean doLoop = false;

	// Robot head roatation
	private boolean headRoation = false;
	private float headSpin = 0.0f;

	//movement of robot variables 
	private float robotUp = -6.0f;
	private float currentZPos = -2.0f;
	private float currentXPos = 0.0f;
	private float roationAngle = 10.0f;
	private boolean headRotationFunction = true;
	
	// Set movement for the player controllers
	private boolean temp1 = false;
	private boolean temp2 = false;
	private boolean temp3 = false;
	
	// set position for gems starts for player
	private float starR1 = 1.0f;
	private float starR2 = 1.0f;
	private float starR3 = 1.0f;

	public static void main(String args[]) {
		new CS2150Coursework().run(WINDOWED, "Coursework-Submission", 0.01f);
	}

	protected void initScene() throws Exception {
		// load the textures
		groundTextures = loadTexture("coursework/textures/Grass01.bmp");
		backGroundTexture = loadTexture("coursework/textures/SpaceBackground.jpg");
		starFishTexture = loadTexture("coursework/textures/1.bmp");
		
		// Planet textures
		plantTexture = loadTexture("coursework/textures/Planets/planet1.bmp");
		planet2Texture = loadTexture("coursework/textures/Planets/planet2.bmp");
		planet3Texture = loadTexture("coursework/textures/Planets/planet3.png");
		planet4Texture = loadTexture("coursework/textures/Planets/planet4.jpg");
		planet5Texture = loadTexture("coursework/textures/Planets/planet5.jpg");
		planet6Texture = loadTexture("coursework/textures/Planets/planet6.jpg");
		planet7Texture = loadTexture("coursework/textures/Planets/planet7.jpg");
		planet8Texture = loadTexture("coursework/textures/Planets/planet8.jpg");

		// Robot Textures
		robotFrontTexture = loadTexture("coursework/textures/RobotTextures/robot.png");
		robotBackTexture = loadTexture("coursework/textures/RobotTextures/robotBack.png");

		// global ambient light level
		float globalAmbient[] = { 0.2f, 0.2f, 0.2f, 1f };
		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		// the first light for the scene is white...
		float diffuse0[] = { 0.6f, 0.6f, 0.6f, 1.0f };
		// ...with a dim ambient contribution...
		float ambient0[] = { 0.1f, 0.1f, 0.1f, 1.0f };
		// ...and is positioned above and behind the viewpoint 
		float position0[] = { 0.0f, 10.0f, 1.0f, 1.0f };

		// supply OpenGL with the properties for the first light
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);

		// prepare the display lists for later us

		GL11.glNewList(planeList, GL11.GL_COMPILE);
		{
			drawUnitPlane();
		}
		GL11.glNewList(robotList, GL11.GL_COMPILE);
		{
			drawUnitPlane();
		}
		GL11.glNewList(starList, GL11.GL_COMPILE);
		{
			drawUnitPlane();
		}
		GL11.glEndList();
	}
	
	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W) && currentZPos >= robotUp) {
			currentZPos -= 0.001f;
			currentXPos -= 0.0001f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && currentZPos <= RobotZ) {
			currentZPos += 0.001f;
			currentXPos += 0.0001f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if (roationAngle <= 90.0f) {
				roationAngle += 0.1f;
			}
			if (roationAngle >= 87.0f && currentXPos >= -3.0f) {
				currentXPos -= 0.0001f;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) ) {
			if(roationAngle >= -90.0f) {
				roationAngle -= 0.1f;
			} 
			if (roationAngle <= -87.0f && currentXPos <= 6.0f) {
				currentXPos += 0.0001f;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			resetAnimations();
			headRotationFunction = false;
			doLoop = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			resetAnimations();
		}
		//testing
		if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
			System.out.println("X: " + currentXPos + " Z: " + currentZPos);
			
		}
	}

	protected void setSceneCamera() {
		// use the default projection settings
		super.setSceneCamera();
	
	}

	protected void updateScene() {
		
		update += +1.0f * getAnimationScale();
		if(headRotationFunction == true) {
			if (headRoation == false) {
				headSpin += 1.0f * getAnimationScale();
				if (headSpin >= 40.0f) {
					headRoation = true;
				}
			}
			if (headRoation == true) {
				headSpin -= 1.0f * getAnimationScale();
				if (headSpin <= -40.0f) {
					headRoation = false;
				}
			}
		}
		if (reached == false) {
			currentValue = currentValue - 0.0001f;
			if (currentValue <= bottomValue) {
				reached = true;
			}
		}
		if (reached == true) {
			currentValue = currentValue + 0.0001f;
			if (currentValue >= topValue) {
				reached = false;
			}
		}
		if(doLoop == true) {
			temp1 = true;
			if (temp1 == true) {
				if(currentXPos >= -0.3f && currentZPos >= -4.9f) {
					currentZPos -= 0.001f;
					currentXPos -= 0.0001f;
				
					if(currentXPos <= -0.1f && currentZPos <= -3.6f) {
						starR1 = StarR;
					}
					if(currentXPos <= -0.26f && currentZPos <= -4.3f) {
						starR2 = StarR;
						temp2 = true;
						temp1 = false;
					}
				}
			}
			if(temp2 == true) {
				if(currentXPos <= 2.0f) {
					currentXPos += 0.001f;
					if(roationAngle != -180.0f) {
						roationAngle -= 1.0f;
					}
					if(currentXPos >= 1.8f) {
						starR3 = StarR;
						temp3 = true;
						temp2 = false;
					}
				}
			}
			if(temp3 == true) {
				if(!(currentXPos >= RobotX && currentZPos >= RobotZ)) {
					currentZPos += 0.001f;
					currentXPos -= 0.001f;
				}
			}
		}
	}

	protected void renderScene() {
		// draw the ground plane
		GL11.glPushMatrix();
		{
			// disable lighting calculations so that they don't affect
			// the appearance of the texture
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit();
			// enable texturing and bind an appropriate texture
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextures.getTextureID());

			// position, scale and draw the ground plane using its display list
			GL11.glTranslatef(0.0f, -1.0f, -10.0f);
			GL11.glScalef(50.0f, 2.0f, 40.0f);
			GL11.glCallList(planeList);
			
			GL11.glTranslatef(0.0f, -1.0f, -10.0f);
			// GL11.glScalef(50.0f, 2.0f, 40.0f);
			GL11.glCallList(robotList);

			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		// draw the back plane
		GL11.glPushMatrix();
		{
			// disable lighting calculations so that they don't affect
			// the appearance of the texture
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit();
			// enable texturing and bind an appropriate texture
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, backGroundTexture.getTextureID());

			// position, scale and draw the back plane using its display list
			GL11.glTranslatef(20.0f, 4.0f, -90.0f);
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(600.0f, 100.0f, 210.0f);
			GL11.glCallList(planeList);

			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		
		// draw the planet 2
		GL11.glPushMatrix();
		{
			Planet planet = new Planet();

			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit();

			planet.DrawPlanet(2.0f, 4.0f, -16.0f, planet2Texture, 0.7f);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		// first planet with moon rotating
		GL11.glPushMatrix();
		{
			// the planet 1
			GL11.glTranslatef(4.0f, 6.0f, -18.0f);
			// draw the Earth
			Planet pp = new Planet();
			pp.drawBody(plantTexture, 0.6f);

			// rotate the Moon around the Earth
			GL11.glRotatef((360.0f * update * 0.05f), 0.0f, 1.0f, 0.0f);
			// the Moon is .5 units from the Earth
			GL11.glTranslatef(1.0f, 0.0f, 0.0f);
			// draw the moon
			pp.drawBody(planet3Texture, 0.3f);

		} // restore origin
		GL11.glPopMatrix();

		// draw the tree 1
		GL11.glPushMatrix();
		{

			Tree tree = new Tree();
			tree.drawTree(0.0f, -1.0f, -11.0f);
		}
		GL11.glPopMatrix();

		// draw the tree2
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(3.0f, -1.0f, -15.0f);
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(0.0f, -0.1f, -5.0f, starFishTexture);
		}
		GL11.glPopMatrix();

		// draw the house
		GL11.glPushMatrix();
		{

			// how shiny are the front faces of the house (specular exponent)
			float houseFrontShininess = 2.0f;
			// specular reflection of the front faces of the house
			float houseFrontSpecular[] = { 1.0f, 0.0f, 0.0f, 1.0f };
			// diffuse reflection of the front faces of the house
			float houseFrontDiffuse[] = { 0.6f, 0.2f, 0.2f, 1.0f };

			// set the material properties for the house using OpenGL
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));

			/*
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, BrickTexture.getTextureID());

			// position and scale of the object
			//GL11.glTranslatef(4.0f, -0.3f, -15.0f);
			//GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);
			//GL11.glScalef(1.0f, 1.0f, 1.0f);

			// draw the base of the house using its display list
			GL11.glCallList(houseList);
			*/
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		drawPlanets();
		
		DrawTrees();
		
		drawStars();
		
		// robot
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			// draw the base of the robot body t
			robot1.DrawRobotBody(robotFrontTexture, robotBackTexture, robotBackTexture, robotBackTexture,
					robotBackTexture, robotBackTexture);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			robot1.DrawRobotLegLeft();

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			robot1.DrawRobotLegRight();

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			robot1.DrawRobotNeck();

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, currentValue, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			robot1.DrawRobotArmLeft();

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, currentValue, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			robot1.DrawRobotArmRight();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(headSpin + roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.07f, 0.07f, 0.07f);

			robot1.DrawRobotHead();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		
	}

	protected void cleanupScene() {
		// empty
	}

	private void resetAnimations() {
		currentXPos = RobotX;
		currentZPos = RobotZ;
		
		headRoation = true;
		headRotationFunction = false;
	}

	/**
	 * Draws a plane aligned with the X and Z axis, with its front face toward
	 * positive Y. The plane is of unit width and height, and uses the current
	 * OpenGL material settings for its appearance
	 */
	private void drawUnitPlane() {
		Vertex v1 = new Vertex(-0.5f, 0.0f, -0.5f); // left, back
		Vertex v2 = new Vertex(0.5f, 0.0f, -0.5f); // right, back
		Vertex v3 = new Vertex(0.5f, 0.0f, 0.5f); // right, front
		Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left, front

		// draw the plane geometry. order the vertices so that the plane faces up
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();

			GL11.glTexCoord2f(0.0f, 0.0f);
			v4.submit();

			GL11.glTexCoord2f(1.0f, 0.0f);
			v3.submit();

			GL11.glTexCoord2f(1.0f, 1.0f);
			v2.submit();

			GL11.glTexCoord2f(0.0f, 1.0f);
			v1.submit();
		}
		GL11.glEnd();

		// if the user is viewing an axis, then also draw this plane
		// using lines so that axis aligned planes can still be seen
		if (isViewingAxis()) {
			// also disable textures when drawing as lines
			// so that the lines can be seen more clearly
			GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				v4.submit();
				v3.submit();
				v2.submit();
				v1.submit();
			}
			GL11.glEnd();
			GL11.glPopAttrib();
		}
	}

	private void RobotLighting() {
		// how shiny are the front faces of the robot (specular exponent)
		float RobotFrontShininess = 1.0f;
		// specular reflection of the front faces of the house
		float RobotFrontSpecular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		// diffuse reflection of the front faces of the house
		float RobotFrontDiffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };

		// set the material properties for the house using OpenGL
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, RobotFrontShininess);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(RobotFrontSpecular));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(RobotFrontDiffuse));
	}
	
	private void DrawTrees() {
		// draw the tree 1
		GL11.glPushMatrix();
		{
			Tree tree = new Tree();
			tree.drawTree(0.0f, -1.0f, -11.0f);
		}
		GL11.glPopMatrix();
		// draw the tree2
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(3.0f, -1.0f, -15.0f);
		}
		GL11.glPopMatrix();
		// draw the tree3
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(4.0f, -1.0f, -14.0f);
		}
		GL11.glPopMatrix();
		// draw the tree4
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(5.0f, -1.0f, -10.0f);
		}
		GL11.glPopMatrix();
		// draw the tree 5
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(6.5f, -1.0f, -11.0f);
		}
		GL11.glPopMatrix();
		// draw the tree6
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(7.0f, -1.0f, -9.0f);
		}
		GL11.glPopMatrix();
		// draw the tree7
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(8.0f, -1.0f, -11.0f);
		}
		GL11.glPopMatrix();
		// draw the tree8
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(8.0f, -1.0f, -6.0f);
		}
		GL11.glPopMatrix();
		// draw the tree9
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(8.0f, -1.0f, -10.0f);
		}
		GL11.glPopMatrix();
		// draw the tree 10
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(7.0f, -1.0f, -15.0f);
		}
		GL11.glPopMatrix();
	}
	
	private void drawStars() {
		// star 1
		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(-0.5f * starR1, -1.0f * starR1, -5.0f * starR1, starFishTexture);
		}
		GL11.glPopMatrix();
		
		// Star 2
		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(-0.5f * starR2, -1.0f * starR2, -7.0f * starR2, starFishTexture);
		}
		GL11.glPopMatrix();
		
		// Star 3
		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(1.0f * starR3, -1.0f * starR3, -5.0f * starR3, starFishTexture);
		}
		GL11.glPopMatrix();
	}
	
	private void drawPlanets() {
		/*
		 * make lighting white
		 * Call the Object planet 
		 * Translate it and position it 
		 * Apply Texture
		 * */
		// first planet with moon rotating
		GL11.glPushMatrix();
		{
		    // how shiny are the front faces of the moon (specular exponent)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			//GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit(); 
			
			// the planet 1
			GL11.glTranslatef(3.0f, 4.0f, -18.0f);
			GL11.glRotatef((360.0f * update  * 0.02f), 0.0f, 1.0f, 0.0f);
			Planet pp = new Planet();
			pp.drawBody(planet2Texture, 0.6f);

			GL11.glRotatef((360.0f * update * 0.05f), 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(1.0f, 0.0f, 0.0f);
			pp.drawBody(planet3Texture, 0.3f);
			GL11.glPopAttrib();
		} // restore origin
		GL11.glPopMatrix();
		// 3rd planet with moon rotating
		GL11.glPushMatrix();
		{
		    // how shiny are the front faces of the moon (specular exponent)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			//GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit(); 
			
			// the planet 1
			GL11.glTranslatef(-5.0f, 5.0f, -10.0f);
			GL11.glRotatef((-360.0f * -update  * -0.06f), 0.0f, 1.0f, 0.0f);
			Planet pp = new Planet();
			pp.drawBody(planet4Texture, 0.6f);

			GL11.glRotatef((360.0f * update  * 0.05f), 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(2.0f, 0.0f, 0.0f);
			pp.drawBody(planet5Texture, 0.3f);
			GL11.glPopAttrib();
		} // restore origin
		GL11.glPopMatrix();
		// 3rd Planet
		GL11.glPushMatrix();
		{
		    // how shiny are the front faces of the moon (specular exponent)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit(); 
			
			// the planet 1
			GL11.glTranslatef(-2.0f, 7.0f, -10.0f);
			GL11.glRotatef((360.0f * update  * 0.03f), 0.0f, 1.0f, 0.0f);
			Planet pp = new Planet();
			pp.drawBody(planet6Texture, 0.6f);
			GL11.glPopAttrib();
		} // restore origin
		GL11.glPopMatrix();
		// 4th planet
		GL11.glPushMatrix();
		{
		    // how shiny are the front faces of the moon (specular exponent)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit(); 
			
			// the planet 1
			GL11.glTranslatef(-1.0f, 6.0f, -10.0f);
			GL11.glRotatef((360.0f * update  * 0.006f), 0.0f, 1.0f, 0.0f);
			Planet pp = new Planet();
			pp.drawBody(plantTexture, 0.6f);
			
			GL11.glRotatef((360.0f * update  * 0.02f), 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(3.0f, 0.0f, 0.0f);
			pp.drawBody(planet5Texture, 0.3f);
			
			GL11.glRotatef((360.0f * update * 0.02f), 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.5f, 0.0f, 0.0f);
			pp.drawBody(planet6Texture, 0.1f);
			GL11.glPopAttrib();
		} // restore origin
		GL11.glPopMatrix();
		// Planet 5
		GL11.glPushMatrix();
		{
		    // how shiny are the front faces of the moon (specular exponent)
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit(); 
			// the planet 1
			GL11.glTranslatef(-1.0f, 5.0f, -15.0f);
			GL11.glRotatef((360.0f * update  * 0.02f), 0.0f, 1.0f, 0.0f);
			Planet pp = new Planet();
			pp.drawBody(planet8Texture, 0.8f);
			GL11.glPopAttrib();
		} // restore origin
		GL11.glPopMatrix();
	}

}