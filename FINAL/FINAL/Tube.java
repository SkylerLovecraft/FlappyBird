//import java.util.Random;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

public class Tube extends Sprite
{
	Random number;
	boolean direction;
	//Bird bird;
	boolean kicked;
	static Image tubeDown, tubeUp;

	Tube()
	{
		number = new Random(System.currentTimeMillis());
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
	Tube(Tube that)
	{
		super.activator = that.activator;
		super.horizontalVelocity = that.horizontalVelocity;
		super.x = that.getX();
		super.y = that.getY();
		this.direction = that.direction;
		this.number = new Random(that.number);
		//this.bird = new Bird(that.bird);
		this.kicked = that.kicked;
	}
	Sprite clone(Model that)
	{
		//System.out.println("in model clone");
		return new Tube(this);
	}
	double getHorizontalVelocity()
	{
		return super.horizontalVelocity;
	}
	double getVerticalVelocity()
	{
		return super.verticalVelocity;
	}
	public void update()
	{
		//System.out.println(super.y);
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
			return this.tubeDown.getWidth(null);
		else if(!direction)
			return this.tubeUp.getWidth(null);
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
		//System.out.println("kicked");
		horizontalVelocity = HV;
	}
}











