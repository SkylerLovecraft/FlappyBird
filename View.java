import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collection;

class View extends JPanel
{
	JButton b1;
	Image energy0, energy1, energy2, energy3, energy4, energy5, energy6, energy7, energy8, energy9, energy10;
	Image handOpen, handClosed;
	Image background;
	Model model;

	int birdEnergy;
	boolean dead = false;
	//For testing the energy sprites
	//int i = 0;

	View(Controller c, Model m)
	{

		model = m;
		birdEnergy = model.bird.getEnergy();
		try
		{
			this.background = 
				ImageIO.read(new File("Background/background.png"));
			this.energy0 = 
				ImageIO.read(new File("EnergySprites/energy_0.png"));
			this.energy1 = 
				ImageIO.read(new File("EnergySprites/energy_1.png"));
			this.energy2 = 
				ImageIO.read(new File("EnergySprites/energy_2.png"));
			this.energy3 = 
				ImageIO.read(new File("EnergySprites/energy_3.png"));
			this.energy4 = 
				ImageIO.read(new File("EnergySprites/energy_4.png"));
			this.energy5 = 
				ImageIO.read(new File("EnergySprites/energy_5.png"));
			this.energy6 = 
				ImageIO.read(new File("EnergySprites/energy_6.png"));
			this.energy7 = 
				ImageIO.read(new File("EnergySprites/energy_7.png"));
			this.energy8 = 
				ImageIO.read(new File("EnergySprites/energy_8.png"));
			this.energy9 = 
				ImageIO.read(new File("EnergySprites/energy_9.png"));
			this.energy10 = 
				ImageIO.read(new File("EnergySprites/energy_10.png"));

			this.handOpen = 
				ImageIO.read(new File("Hand/hand1.png"));
			this.handClosed = 
				ImageIO.read(new File("Hand/hand2.png"));

		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

		c.setView(this);
	}

	//Note Model model -> Bird bird.getFrameCount
	public void paintComponent(Graphics g)
	{
		//g.setColor(new Color(100, 255, 255));
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//If not clicked or recently clicked the frame counter will be stuck at 0

		//The background image stuff is super lame but it works pretty decently
		//unless of course the player plays for too long, since it's just a really
		//long scrolling picture (like 500 x 5000)
		g.drawImage(this.background, model.background.background_x, 0, null);
		//Draw the corresponding tube and check it for a collision

		//Iterator<Tube> it = tubes.iterator();
		Iterator<Sprite> it = model.sprites.iterator();
		while(it.hasNext())
		{
   			Sprite s = it.next();
   			//System.out.println("s.draw(g)");
 			s.draw(g);
   		}
		
		birdEnergy = model.bird.getEnergy();
		if(birdEnergy == 0)
			g.drawImage(this.energy0, 300, 0, null);
		if(birdEnergy == 1)
			g.drawImage(this.energy1, 300, 0, null);
		if(birdEnergy == 2)
			g.drawImage(this.energy2, 300, 0, null);
		if(birdEnergy == 3)
			g.drawImage(this.energy3, 300, 0, null);
		if(birdEnergy == 4)
			g.drawImage(this.energy4, 300, 0, null);
		if(birdEnergy == 5)
			g.drawImage(this.energy5, 300, 0, null);
		if(birdEnergy == 6)
			g.drawImage(this.energy6, 300, 0, null);
		if(birdEnergy == 7)
			g.drawImage(this.energy7, 300, 0, null);
		if(birdEnergy == 8)
			g.drawImage(this.energy8, 300, 0, null);
		if(birdEnergy == 9)
			g.drawImage(this.energy9, 300, 0, null);
		if(birdEnergy == 10)
			g.drawImage(this.energy10, 300, 0, null);
		if(birdEnergy == 0)
			dead = true;
		//System.out.println(dead);

		//model.chuck.draw(g);
		if(dead)
		{
			model.hand.update();
			//handOpen, handClosed
			g.drawImage(model.bird.deadBird, model.bird.x, model.bird.y, null);
			if(model.hand.reachingUp())
			{
				g.drawImage(this.handOpen, model.hand.x, model.hand.y, null);
			}
			else if(model.hand.pullingDown())
			{
				g.drawImage(this.handClosed, model.hand.x, model.hand.y, null);
			}
		}
	}
}
