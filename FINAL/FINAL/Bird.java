import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

public class Bird extends Sprite
{

	//Positive is down, negative is up. (relative to the bird)
	double ACELLERATION_FACTOR = 0.7;
	double FLAP_FACTOR = -8.0;
	int flapFrames = 0;
	int invincibilityFrames = 0;
	double energy;
	int score;
	//double super.verticalVelocity;
	
	static Image birdFall;
	static Image birdFlap1, birdFlap2, birdFlap3;
	static Image deadBird;
	Bird()
	{
		this.loadImages();

		//The x doesn't actually change, but if you want to move it around the screen just change this number as desired
		super.x = 10;
		super.y = 0;
		this.energy = 100.0;
		this.score = 0;
		//Cast?
		super.verticalVelocity = 0.0;
		super.image = deadBird;
	}

	Bird(Bird that)
	{
		//System.out.println("in bird copy");
		loadImages();
		super.x = that.getX();
		super.y = that.getY();
		this.energy = that.energy;
		this.score = that.score;
		super.verticalVelocity = that.getVerticalVelocity();
		this.flapFrames = that.flapFrames;
		this.score = that.score;
		super.image = deadBird;

	}
	Sprite clone(Model newModel)
	{
		return new Bird(this);
	}
	public void loadImages()
	{
		if(birdFall == null)
		{
			try{
			this.birdFall =
				ImageIO.read(new File("Bird/frame-1.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		}
		if(birdFlap1 == null)
		{
			try{
			this.birdFlap1 =
				ImageIO.read(new File("Bird/frame-2.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		}
		if(birdFlap2 == null)
		{
			try{
			this.birdFlap2 =
				ImageIO.read(new File("Bird/frame-3.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		}
		if(birdFlap3 == null)
		{
			try{
			this.birdFlap3 =
				ImageIO.read(new File("Bird/frame-4.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		}
		if(deadBird == null)
		{
			try{
			this.deadBird = 
				ImageIO.read(new File("Bird/dead.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	}
	public boolean isTube()
	{
		return false;
	}

	public boolean isBird()
	{
		return true;
	}

	public boolean isChuck()
	{
		return false;
	}
	public boolean isCollideable()
	{
		if(this.getInvincibility() == false)
			return true;
		else
			return false;
	}

	public void update()
	{
		if(this.energy > 0.0)
		{
			if(this.invincibilityFrames > 0)
				--invincibilityFrames;

			if(this.flapFrames > 0)
				--flapFrames;

			if(y <= 440)
			{
				if((super.y += super.verticalVelocity) < 475)
				{
					super.y += super.verticalVelocity;
					super.verticalVelocity += this.ACELLERATION_FACTOR;
				}
			}
		}
		//Keeps the bird from flying up forever 
		if(super.y < -10)
		{
			super.verticalVelocity = 0.0;
			super.y = -10;
		}

		//Keeps the bird from falling forever, unless it's dead, because we want
		//the hand to be able to pull it off the screen
		/*if(super.y > 440 && this.energy > 0.0)
		{
			super.verticalVelocity = 0.0;

			this.y = 440;
			this.energy(-5.0);
		}*/

		super.x = getX();
		super.y = getY();
		//System.out.println(+y);
	}

 	public void draw(Graphics g)
 	{
 		//System.out.println("In bird draw");
		if(this.getFrameCount() <= 1)
		{
			g.drawImage(this.birdFall, super.x, super.y, null);
		}
			//When a click occurs, the frame counter is set to 10 frames
			//AKA: the flap image will be cycle through the flap animation
			//     for 10 frames, then the counter will be 0 again
			//     causing the previous image to show (see above if statement).

		else if(this.getFrameCount() > 8 && this.getFrameCount() <= 10)
		{
			g.drawImage(this.birdFlap1, super.x, super.y, null);
		}

		else if(this.getFrameCount() > 6 && this.getFrameCount() <= 8)
		{
			g.drawImage(this.birdFlap2, super.x, super.y, null);
		}

		else if(this.getFrameCount() > 1 && this.getFrameCount() <= 6)
		{
			g.drawImage(this.birdFlap3, super.x, super.y, null);
		}
 	}
	public void flap()
	{
		//if(energy != 0)
		flapFrames = 10;
		super.verticalVelocity = 0.0;
		super.verticalVelocity += FLAP_FACTOR;
	}

	//Super self explanitory getters, used in the View paintComponent() stuff
	public int getFrameCount()
	{
		return flapFrames;
	}
	public double getVerticalVelocity()
	{
		return super.verticalVelocity;
	}
	public double getHorizontalVelocity()
	{
		return super.horizontalVelocity;
	}
	public int getX()
	{
		return super.x;
	}

	public int getY()
	{
		return super.y;
	}

	public int getWidth()
	{
		return deadBird.getWidth(null);
	}

	public int getHeight()
	{
		return deadBird.getHeight(null);
	}

	public void moveTheBird(int x)
	{
		//System.out.println("moving the bird");
		super.y += x;
		//System.out.println("y = " +y);
	}

	public double getEnergy()
	{
		return energy;
	}
	public void energy(double x)
	{
		if(energy != 0.0)
			energy += x;
	}
	public void score(int x)
	{
		if(energy != 0.0)
		{
			score += x;
			/*//System.out.println("Score: " +score);
			if(score % 5 ==  0 && energy <= 9 && energy >= 1)
				++energy;*/
			grantInvincibility(true);
		}
		//System.out.println(+score);
	}
	public int getScore()
	{
		return score;
	}
	public void verticalVelocity(double num)
	{
		super.verticalVelocity = num;
	}

	public void grantInvincibility(boolean b)
	{
		if(!b)
			energy(-20.0);
		invincibilityFrames = 20;
	}
	
	public boolean getInvincibility()
	{
		if(invincibilityFrames > 0)
			return true;
		return false;
	}

}