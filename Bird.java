import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

public class Bird extends Sprite
{
	Model m;

	//Positive is down, negative is up. (relative to the bird)
	double ACELLERATION_FACTOR = 0.7;
	double FLAP_FACTOR = -8.0;
	int flapFrames = 0;
	int invincibilityFrames = 0;
	final int energy;
	//int x;
	//int y;
	int score;
	//double super.verticalVelocity;
	
	Image birdFall;
	Image birdFlap1, birdFlap2, birdFlap3;
	Image deadBird;
	Bird()
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

		//The x doesn't actually change, but if you want to move it around the screen just change this number as desired
		super.x = 10;
		super.y = 0;
		energy = 10;
		score = 0;
		//Cast?
		super.verticalVelocity = 0.0;
		super.image = this.deadBird;
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
		if(this.energy != 0)
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
		if(super.y > 440 && this.energy != 0)
		{
			super.verticalVelocity = 0.0;

			//420 just looked nice, anything between 420 - 450 is a decent enough restraint
			this.y = 440;
		}
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
		return birdFall.getWidth(null);
	}

	public int getHeight()
	{
		return birdFall.getHeight(null);
	}

	public void moveTheBird(int x)
	{
		//System.out.println("moving the bird");
		super.y += x;
		//System.out.println("y = " +y);
	}

	public int getEnergy()
	{
		return energy;
	}
	public void energy(int x)
	{
		if(energy != 0)
			energy += x;
	}
	public void score(int x)
	{
		if(energy != 0)
		{
			score += x;
			//System.out.println("Score: " +score);
			if(score % 5 ==  0 && energy <= 9 && energy >= 1)
				++energy;
			grantInvincibility(true);
		}
		System.out.println(+score);
	}
	public int getScore()
	{
		return score;
	}

	public void grantInvincibility(boolean b)
	{
		if(!b)
			energy(-1);
		invincibilityFrames = 20;
	}
	
	public boolean getInvincibility()
	{
		if(invincibilityFrames > 0)
			return true;
		return false;
	}

}