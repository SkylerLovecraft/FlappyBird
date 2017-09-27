//import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;
//CHANGELOG

class Model
{
	int ACTION_NOTHING = 0;
	int ACTION_FLAP = 1;
	int ACTION_CHUCK = 2;
	LinkedList<Sprite> sprites;
	Bird bird;
	Hand hand;
	//Chuck chuck;
	int frames;
	//Tube tube;
	Background background;
	int k = 2;
	int d = 10;

	Model()
	{
		sprites = new LinkedList<Sprite>();
		//chuck = new Chuck();
		//tube = new Tube();
		bird = new Bird();
		background = new Background();
		frames = 0;
		sprites.add(bird);
		hand = new Hand(bird);
	}
	Model(Model that)
	{
		this.bird = new Bird(that.bird);
		this.hand = new Hand(that.hand);
		this.frames = that.frames;
		this.background = new Background(that.background);
		this.sprites = new LinkedList<Sprite>();
		Iterator<Sprite> it = that.sprites.iterator();
		int numItems = 0;
		while(it.hasNext())
		{
			++numItems;
			Sprite s = it.next();
			//System.out.println("num items in LinkedList: " +numItems);
			this.sprites.add(s.clone(that));
		}
	}
	double evaluateAction(int action, int depth)
	{

	// Evaluate the state
	if(bird.energy <= 0)
		return 0;
	if(depth >= d)
	{
		//System.out.println("returning bird.energy");
		return bird.energy;
	}

	// Simulate the action
	Model copy = new Model(this); // uses the copy constructor
	//System.out.println("after the copy");
	copy.doAction(action);
	//System.out.println("after do action");
	copy.update(); // advance simulated time
	//System.out.println("After update");
	// Recurse
	if(depth % k != 0)
	   return copy.evaluateAction(ACTION_NOTHING, depth + 1);
	else {
	   double nothing  = copy.evaluateAction(ACTION_NOTHING, depth + 1);
	   double flapping = copy.evaluateAction(ACTION_FLAP, depth + 1);
	   double chuck    = copy.evaluateAction(ACTION_CHUCK, depth + 1);
	   double best = 0.0;
	   if(nothing > flapping)
	   		best = nothing;
	   else if(nothing == flapping)
	   {
	   		best = nothing;
	   }
	   else if(nothing < flapping)
	   {
	   		System.out.println("flapping is greater than nothing");
	   		best = flapping;
	   }
	   else if(best < chuck)
	   {
	   		best = chuck;
	   }
	   return best;
	}
}
void doAction(int i)
{
	///System.out.println("in model do action");
	if(i == 0)
		return;
	if(i == 1)
		onClick();
	if(i == 2)
		onRightClick();
}


void update()
{
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			//System.out.println("iterating through the sprites");
			Sprite s = it.next(); 
			s.update();
		if(this.bird.getY() > 440 && this.bird.getEnergy() > 0.0)
		{
			this.bird.verticalVelocity(0.0);
			this.bird.y = 440;
			this.bird.energy(-5.0);
		}
			if(bird != s && s.isCollideable() && s.isChuck() == false)
			{
				if(bird.doesCollideWith(s) && bird.getInvincibility() == false)
				{
					bird.grantInvincibility(false);
				}
				else if(bird.getEnergy() > 0.0)
				{
					if(bird.getEnergy() < 100.0)
					bird.energy(0.1);
				}
			}
			if(bird != s && bird.isCollideable() && s.isCollideable())
			{
				if(bird.scoreCheck(s))
					bird.score(1);
			}

			if(s.x < -80 || s.y > 700)
				it.remove();
			//if(s.isChuck() )
			//System.out.println("Bird energy: " +bird.energy);
		}

		
		it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			if(s.isChuck())
			{
				Iterator<Sprite> it2 = sprites.iterator();
				while(it2.hasNext())
				{
					Sprite x = it2.next();
					if(s.doesCollideWith(x) && x.isTube())
					{
						//System.out.println("Collided");
						s.activator = true;
						x.activator = true;
					}
				}
			}
		}

		hand.update();
		background.update();
		//chuck.update();

		++frames;
		if(frames > 30)
		{
			frames = 0; 
			Tube newTube = new Tube();
			sprites.add(newTube);
		}
	}
	
	public void onClick()
	{
		System.out.println("The bird should flap now");
		bird.flap();
	}

	public void onRightClick()
	{
		bird.energy(-10.0);
		Chuck newChuck = new Chuck();
		//chuck = newChuck;
		sprites.add(newChuck);
	}
}