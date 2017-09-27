import java.util.Random;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
//Relevant information: 
//http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadLocalRandom.html#nextInt-int-int-

//CHANGELOG
/*
	1/31/17:
	-Modified the constructor and generate() to work with a boolean value, determining 
	which values to generate for either the downward(b = true) or upward(b = false) tubes.
	-Commented out the setY() and getY() methods, might use or refer to it later for something
	-Added a getDirection() method, needed for the View update() to determine which image to use
*/

public class Tube extends Sprite
{
	Random number = new Random();
	boolean direction;
	Bird bird;
	boolean kicked;
	Image tubeDown, tubeUp;
	//direction = true for the downward facing tube, false for upward facing
	Tube()
	{
		if(tubeDown == null)
		{
			try{
			this.tubeDown = 
				ImageIO.read(new File("tube_down.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		}
		if(tubeUp == null)
		{
			try{
			this.tubeUp =
				ImageIO.read(new File("tube_up.png"));}
				 catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		}
		super.activator = false;
		super.horizontalVelocity = 15;
		super.x = 510;
		this.direction = generateBool();
		generate(direction);

	}

	public void update()
	{
		if(!super.activator)
		{
			x -= 10;
		}
		if(super.activator)
		{
			super.x += super.horizontalVelocity;
		}
		super.x = getX();
		super.y = getY();
	}

	public void draw(Graphics g)
	{
	   	if(getDirection())
	   	{
	   		g.drawImage(tubeDown, x, y, null);
	   	}
	   	else if(!getDirection())
	   	{
	   		g.drawImage(tubeUp, x, y, null);
	   	}
	}
	public boolean isBird()
	{
		return false;
	}
	public boolean isTube()
	{
		return true;
	}
	public boolean isChuck()
	{
		return false;
	}
	//Tube dimensions are 55 x 400
	public void generate(boolean b)
	{
		if(b)
			super.y = number.nextInt(321) - 360; //Generates a number between -380 and -60 //Perhaps needs more refinement!
		if(!b)
			super.y = number.nextInt(361) + 120;   //Generates a number between 100 and 460 (for the upward tube)
	}

	public boolean generateBool()
	{
		if(number.nextInt(2) == 1)
			return true;
		else
			return false;
	}

	public boolean getDirection()
	{
		return direction;
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
		if(direction)
			return tubeDown.getWidth(null);
		else if(!direction)
			return tubeUp.getWidth(null);
		else
			return -1;
	}

	public int getHeight()
	{
		if(direction)
			return tubeDown.getHeight(null);
		else if(!direction)
			return tubeUp.getHeight(null);
		else
			return -1;
	}

	public boolean isCollideable()
	{
		return true;
	}
	public void kicked(double HV)
	{
		System.out.println("kicked");
		horizontalVelocity = HV;
	}
}











