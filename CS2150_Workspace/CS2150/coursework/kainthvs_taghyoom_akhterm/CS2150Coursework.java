/** 
 * FileName: CS2150Coursework.java
 * Brief: This is a program that was created for the CS2150 Coursework.
 *        This is a game scene for a robot moving on a planet where the robot is collecting stars.
 *        There are planets here and stars also the robot is on a planet where other planets are visible.
 * Author: Vikram Singh Kainth, Melika Taghyoon, Mahamuda Akhter.
 * Created: 15/11/2018.
 * Course: Computer Graphics CS2150.
 * Year: 2nd.
 * Statement: This is our own work.
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
package coursework.kainthvs_taghyoom_akhterm;

import org.lwjgl.opengl.GL11;
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
 */
public class CS2150Coursework extends GraphicsLab {
	
	/** Final values for permate positions for the robot */ 
	private static final float ROBOT_X = 0.0f;
	private static final float ROBOT_Z = -2.0f;
	private static final float STAR_R = 0.0f;

	/** display list id for the unit plane, robot, stars */
	private static final int planeList = 1;
	private static final int robotList = 2;
	private static final int starList = 3;

	/** ids for nearest, linear and mipmapped textures for the ground plane */
	private Texture groundTextures;
	/**
	 * ids for nearest, linear and mipmapped textures for the night time back
	 * background plane = {@link https://fstoppers.com/news/japan-landed-space-rovers-aste0roid-and-first-pictures-are-here-292344}
	 */
	private Texture backGroundTexture;
	private Texture robotFrontTexture;
	private Texture robotBackTexture;
	private Texture robotCrack;
	private Texture robotCrack1;
	private Texture robotEyes;
	
	/** Textures for the planets */ 
	private Texture plantTexture;
	private Texture planet2Texture;
	private Texture planet3Texture;
	
	/** Textures for StarGem */
	private Texture blueGem;
	private Texture pinkGem;
	private Texture yellowGem;

	/** creations of robot instance */
	private Robot robot1 = new Robot();

	/** values for robot movement */
	private float update = 0.0f;
	private float topValue = -0.5f;
	private float bottomValue = -0.58f;
	private float currentValue = -0.5f;
	private boolean reached = false;
	private boolean doLoop = false;

	/** Robot head rotation and boolean value to tell it to do so or not */
	private boolean headRoation = false;
	private float headSpin = 0.0f;

	/** movement of robot variables */ 
	private float robotUp = -6.0f;
	private float currentZPos = -2.0f;
	private float currentXPos = 0.0f;
	private float roationAngle = 10.0f;
	private boolean headRotationFunction = true;
	
	/** Set movement for the player controllers */
	private boolean temp1 = false;
	private boolean temp2 = false;
	private boolean temp3 = false;
	
	/** set position for gems starts for player */
	private float starR1 = 1.0f;
	private float starR2 = 1.0f;
	private float starR3 = 1.0f;

	/**main method to run the program */
	public static void main(String args[]) {
		new CS2150Coursework().run(WINDOWED, "Coursework-Submission", 0.01f);
	}
	
	/**
	 * @throws Exception if texture isn't found or error occurs here.
	 * This Function here is abl'e to set the textures, set the global lighting and set up the draw-list.
	 * - The global lighting is set to white and at the positions [T(1.0, 10.0, 1.0)]
	 * */
	protected void initScene() throws Exception {
		// load the textures
		groundTextures = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/Grass01.bmp");
		backGroundTexture = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/SpaceBackground.jpg");
		
		// Planet textures
		plantTexture = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/Planets/planet1.bmp");
		planet2Texture = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/Planets/planet2.bmp");
		planet3Texture = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/Planets/planet3.png");

		// Robot Textures
		robotFrontTexture = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/RobotTextures/robot.png");
		robotBackTexture = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/RobotTextures/robotBack.png");
		robotCrack = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/RobotTextures/robotCrack.png");
		robotCrack1 = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/RobotTextures/robotCrackDark.png");
		robotEyes = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/RobotTextures/roboteyes.png");
		
		// starGem Texture
		blueGem = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/StarGem/blueGem.png");
		pinkGem = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/StarGem/pinkGem.png");
		yellowGem = loadTexture("coursework/kainthvs_taghyoom_akhterm/textures/StarGem/yellowGem.png");

		// global ambient light level
		float globalAmbient[] = { 0.4f, 0.4f, 0.4f, 1f };
		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		// the first light for the scene is white...
		float diffuse0[] = { 0.6f, 0.6f, 0.6f, 1.0f };
		// ...with a dim ambient contribution...
		float ambient0[] = { 0.1f, 0.1f, 0.1f, 1.0f };
		// ...and is positioned above and behind the viewpoint 
		float position0[] = { 1.0f, 10.0f, 1.0f, 1.0f };

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
	
	/**
	 * Keyboard keys are set here:
	 * - W: robot travels up the map
	 * - A: robot travels in the left direction by turning the robot left and then moving
	 * - S: robot travels down the map
	 * - D: robot travels right by first rotating the robot then moving it
	 * 
	 * - P: Plays the scene where the robot will collect the stars.
	 * 
	 * - Space_Bar: resets the robot position
	 * 
	 * - T: prints the robots Coordinates; X, Z
	 * */
	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W) && currentZPos >= robotUp) {
			currentZPos -= 0.001f;
			currentXPos -= 0.0001f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && currentZPos <= ROBOT_Z) {
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
		// Testing printing the robots X and Z
		if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
			System.out.println("X: " + currentXPos + " Z: " + currentZPos);
			
		}
	}

	/**
	 * Camera position is set here 
	 * {@link GraphicsLab.GraphicsLab.java}, i set the super camera position to be further away to get more of the scene in the screen 
	 * */
	protected void setSceneCamera() {
		// use the default projection settings
		super.setSceneCamera();
	}
	
	/**
	 * Scene is updated in real-time at runtime
	 * First the planet update is performed, ,this ;is where the planets rotation is performed. 
	 * Then the head rotations is performed
	 * The robot going forward and back is ran here
	 * The robots set movement for the P key is ran here
	 * */
	protected void updateScene() {
		// Planets moon is rotating around it is set here  to increase
		update += +1.0f * getAnimationScale();
		
		// if the head rotation is set to true then the function will run
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
		
		// Here the robot can move back and fourth
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
		
		// For the set program for robot to collect stars 
		if(doLoop == true) {
			temp1 = true;
			if (temp1 == true) {
				if(currentXPos >= -0.3f && currentZPos >= -4.9f) {
					currentZPos -= 0.001f;
					currentXPos -= 0.0001f;
				
					if(currentXPos <= -0.1f && currentZPos <= -3.6f) {
						starR1 = STAR_R;
					}
					if(currentXPos <= -0.26f && currentZPos <= -4.3f) {
						starR2 = STAR_R;
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
						starR3 = STAR_R;
						temp3 = true;
						temp2 = false;
					}
				}
			}
			if(temp3 == true) {
				if(!(currentXPos >= ROBOT_X && currentZPos >= ROBOT_Z)) {
					currentZPos += 0.001f;
					currentXPos -= 0.001f;
					System.exit(0);
				}
			}
		}
	}

	/**
	 * Draws the scene that will be displayed on the screen
	 * First draws the Ground plane and then the background plane
	 * Then calls the methods to run the Drawing of the trees, Stars and the Planets.
	 * Last i draw the robot here with the textures  and lighting
	 * */
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
		
		// Runs the Draw Planet function where all the objects are with the texture and lighting
		drawPlanets();
		// Runs the Draw Trees function where all the objects are with the texture and lighting
		DrawTrees();
		// Runs the Draw Stars function where all the objects are with the texture and lighting
		drawStars();
		
		// Robot
		// Body
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotFrontTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			// draw the base of the robot body t
			robot1.DrawRobotBody(robotFrontTexture);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		//Left leg
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotCrack1.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			robot1.DrawRobotLegLeft(robotCrack1);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		// Right leg
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			robot1.DrawRobotLegRight(robotCrack1);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		// Neck
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotBackTexture.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			robot1.DrawRobotNeck();

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		// Left arm
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotCrack.getTextureID());

			GL11.glTranslatef(currentXPos, currentValue, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			robot1.DrawRobotArmLeft(robotCrack);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		// Right arm
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotCrack.getTextureID());

			GL11.glTranslatef(currentXPos, currentValue, currentZPos);
			GL11.glRotatef(roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			robot1.DrawRobotArmRight(robotCrack);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		// Head
		GL11.glPushMatrix();
		{
			RobotLighting();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, robotEyes.getTextureID());

			GL11.glTranslatef(currentXPos, -0.5f, currentZPos);
			GL11.glRotatef(headSpin + roationAngle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);

			robot1.DrawRobotHead(robotEyes);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
		
	}
	
	/**
	 * cleans up the scene not needed as no cleaning had to be done
	 * */
	protected void cleanupScene() {
		// empty
	}
	
	/**
	 *Resets the robot position. 
	 * */
	private void resetAnimations() {
		currentXPos = ROBOT_X;
		currentZPos = ROBOT_Z;
		
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
	
	/**
	 * Lighting for robot, here the shininess is set high so the robot looks really shiny and clean
	 * This is called for each part of the robot to apply the same effect across it
	 * */
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

	/**
	 * Draws the trees for the map
	 * All the tress are drawn here and ran in the renderScene()
	 * */
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
		// draw the tree 11
		GL11.glPushMatrix();
		{
			Tree tree1 = new Tree();
			tree1.drawTree(-3.0f, -1.0f,-9.0f);
		}
		GL11.glPopMatrix();
	}
	
	/**
	 * Draws the star Gems on the map with the textures also being inputed here
	 * The Function is called in renderScene()
	 * */
	private void drawStars() {
		// star 1
		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(-0.5f * starR1, -1.0f * starR1, -5.0f * starR1, blueGem);
		}
		GL11.glPopMatrix();
		
		// Star 2
		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(-0.5f * starR2, -1.0f * starR2, -7.0f * starR2, pinkGem);
		}
		GL11.glPopMatrix();
		
		// Star 3
		GL11.glPushMatrix();
		{
			Starfish starfish = new Starfish();
			starfish.Draw(1.0f * starR3, -1.0f * starR3, -5.0f * starR3, yellowGem);
		}
		GL11.glPopMatrix();
	}
	
	/**
	 * Draws the planets with the moons orbiting them.
	 * This function is called in the renderScene()
	 * The lighting and textures are done here
	 * */
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
			GL11.glDisable(GL11.GL_LIGHTING);
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
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit(); 
			
			// the planet 1
			GL11.glTranslatef(-5.0f, 5.0f, -10.0f);
			GL11.glRotatef((-360.0f * -update  * -0.06f), 0.0f, 1.0f, 0.0f);
			Planet pp = new Planet();
			pp.drawBody(planet3Texture, 0.6f);

			GL11.glRotatef((360.0f * update  * 0.05f), 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(2.0f, 0.0f, 0.0f);
			pp.drawBody(planet2Texture, 0.3f);
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
			pp.drawBody(planet3Texture, 0.6f);
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
			pp.drawBody(planet2Texture, 0.3f);
			
			GL11.glRotatef((360.0f * update * 0.02f), 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.5f, 0.0f, 0.0f);
			pp.drawBody(planet3Texture, 0.1f);
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
			pp.drawBody(planet3Texture, 0.8f);
			GL11.glPopAttrib();
		} // restore origin
		GL11.glPopMatrix();
	}

}