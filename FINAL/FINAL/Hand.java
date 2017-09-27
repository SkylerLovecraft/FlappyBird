class Hand
{
	Bird b;
	int x; 
	int y;
	int dest_x;
	int dest_y;
	boolean status;
	boolean offScreen;
	Hand(Bird bird)
	{
		status = true; //aka the hand is moving up
		offScreen = false;
		b = bird;
		y = 510;
		x = 510;
	}
	Hand(Hand that)
	{
		this.b = new Bird(that.b);
		this.x = that.x;
		this.y = that.y;
		this.dest_y = that.dest_y;
		this.dest_x = that.dest_x;
		this.status = that.status;
		this.offScreen = that.offScreen;
	}
	public void update()
	{
		/*if(b.getEnergy() <= 0)
			grab();
			*/
		if(b.energy <= 0.0)
		{
			//System.out.println("detected the birds energy is less than 0.0 (in hand)");
			if(status)
			{
				if(x != b.x)
				{
					if(x - b.x < 5)
					{
						x = b.y;
					}
					else
						x -= 5;
				}

				if(y != b.y)
				{
					if(y - b.y < 5)
					{
						System.out.println("y - b.y: " + (y - b.y));
						y = b.y;
					}
					else
					{
						System.out.println("this.y: " +this.y);
						y -= 5;
					}
				}
			}

			if(x == b.x && y == b.y)
			{
				status = false;
			}
			if(!status)
			{
				System.out.println("moving th e brid");
				b.moveTheBird(5);
				y += 5;
				b.update();
			}
		}
		if(b.y > 550)
		{
			offScreen = true;
		}
	}

	//idea
	/*public void grab()
	{
		dest_x = b.x;
		dest_y = b.y;
		return;
	}
	*/
	public boolean reachingUp()
	{
		return status;
	}

	public boolean pullingDown()
	{
		return !status;
	}
}