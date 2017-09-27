import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;
//CHANGELOG

class Model
{
	LinkedList<Sprite> sprites;
	Bird bird;
	Hand hand;
	//Chuck chuck;
	int frames;
	//Tube tube;
	Background background;

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

	void update()
	{
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			//System.out.println("iterating through the sprites");
			Sprite s = it.next(); 
			s.update();
			if(bird != s && s.isCollideable() && s.isChuck() == false)
			{
				if(bird.doesCollideWith(s) && bird.getInvincibility() == false)
				{
					bird.grantInvincibility(false);
				}
			}
			if(bird != s && bird.isCollideable() && s.isCollideable())
			{
				if(bird.scoreCheck(s))
					bird.score(1);
			}

			if(s.x < -80 || s.y > 700 || s.x > 700 || s.y < -100)
				it.remove();
			//if(s.isChuck() )
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
						System.out.println("Collided");
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
		bird.flap();
	}

	public void onRightClick()
	{
		bird.energy(-1);
		Chuck newChuck = new Chuck();
		//chuck = newChuck;
		sprites.add(newChuck);
	}
}