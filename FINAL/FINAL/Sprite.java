import java.awt.Graphics;
import java.awt.Image;

abstract class Sprite
{

	int x, y;
	boolean activator;
	double horizontalVelocity;
	double verticalVelocity;
	Image image;

	abstract void update();

	//The draw method should draw the sprite. 
	abstract void draw(Graphics g);
	abstract boolean isTube();
	abstract boolean isBird();
	abstract boolean isChuck();
	abstract boolean isCollideable(); //Would be pretty nice too
	abstract int getX();
	abstract int getY();
	abstract int getWidth();
	abstract int getHeight();
	abstract double getVerticalVelocity();
	abstract double getHorizontalVelocity();
	abstract Sprite clone(Model newModel);
	void setX(int num)
	{
		x = num;
	}
	void setY(int num)
	{
		y = num;
	}

	boolean doesCollideWith(Sprite otherSprite)
	{
		//System.out.println("in Sprite: " +this.x);
		if(this.x + this.getWidth() < otherSprite.x)
			return false;
		else if(this.x > otherSprite.x + otherSprite.getWidth())
			return false;
		else if(this.y + this.getHeight() < otherSprite.y)
			return false;
		else if(this.y > otherSprite.y + otherSprite.getHeight())
			return false;
		return true;
	}

	boolean scoreCheck(Sprite otherSprite)
	{
		if(otherSprite.x < 20)
		{
			if(this.x > otherSprite.x + otherSprite.getWidth() && this.y > otherSprite.y + 
				otherSprite.getHeight())
				return true;
			else if(this.x > otherSprite.x + otherSprite.getWidth() && this.y < otherSprite.y)
				return true;
		}
			return false;
	}
	

}