import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

public class Chuck extends Sprite
{
	//int x, y;
	//double verticalVelocity;
	//double horizontalVelocity;
	final double ACELLERATION_FACTOR = 0.4;

	boolean activated;
	Chuck()
	{
		if(super.image == null)
			{
				try{
				super.image = 
					ImageIO.read(new File("chuckImage.png"));}
					 catch(Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		super.x = -70;
		super.y = 250;
		super.verticalVelocity = -10;
		super.horizontalVelocity = 15;
		super.activator = false;
		}
	}

	public void update()
	{
			//System.out.println(+x);
		if(!super.activator)
		{
			super.x += super.horizontalVelocity;
			super.y += super.verticalVelocity;
			super.verticalVelocity += ACELLERATION_FACTOR;
		}
		if(super.activator)
		{
			super.x -= super.horizontalVelocity;
			super.y -= super.verticalVelocity;
		}
			super.x = getX();
			super.y = getY();

	}

	public void draw(Graphics g)
	{
		g.drawImage(super.image, x, y, null);
	}

	public boolean isTube()
	{
		return false;
	}
	public boolean isBird()
	{
		return false;
	}
	public boolean isChuck()
	{
		return true;
	}
	public boolean isCollideable()
	{
		return true;
	}
	public int getX()
	{
		return super.x;
	}
	public int getY()
	{
		return super.y;
	}
	public double getHorizontalVelocity()
	{
		return super.horizontalVelocity;
	}
	public int getWidth()
	{
		return super.image.getWidth(null);
	}
	public int getHeight()
	{
		return super.image.getHeight(null);
	}
	public void activate()
	{
		super.verticalVelocity = 0;
		super.horizontalVelocity = 0;
		//activated = true;
	}
}