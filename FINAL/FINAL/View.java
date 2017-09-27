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
	static Image handOpen, handClosed;
	Image background;
	Model model;

	double birdEnergy;
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

	void removeButton()
	{
		this.remove(b1);
		this.repaint();
	}
	//Note Model model -> Bird bird.getFrameCount
	public void paintComponent(Graphics g)
	{
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
		if(birdEnergy >= 0.0 && birdEnergy <= 10.0)
			g.drawImage(this.energy0, 300, 0, null);
		if(birdEnergy > 10.0 && birdEnergy <= 20.0)
			g.drawImage(this.energy1, 300, 0, null);
		if(birdEnergy > 20.0 && birdEnergy <= 30.0)
			g.drawImage(this.energy2, 300, 0, null);
		if(birdEnergy > 30.0 && birdEnergy <= 40.0)
			g.drawImage(this.energy3, 300, 0, null);
		if(birdEnergy > 40.0 && birdEnergy <= 50.0)
			g.drawImage(this.energy4, 300, 0, null);
		if(birdEnergy > 50.0 && birdEnergy <= 60.0)
			g.drawImage(this.energy5, 300, 0, null);
		if(birdEnergy > 60.0 && birdEnergy <= 70.0)
			g.drawImage(this.energy6, 300, 0, null);
		if(birdEnergy > 70.0 && birdEnergy <= 80.0)
			g.drawImage(this.energy7, 300, 0, null);
		if(birdEnergy > 80.0 && birdEnergy <= 90.0)
			g.drawImage(this.energy8, 300, 0, null);
		if(birdEnergy > 90.0 && birdEnergy < 100.0)
			g.drawImage(this.energy9, 300, 0, null);
		if(birdEnergy == 100.0)
			g.drawImage(this.energy10, 300, 0, null);
		if(birdEnergy <= 0.0)
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
				System.out.println("drawing the hand");
				g.drawImage(this.handOpen, model.hand.x, model.hand.y, null);
			}
			else if(model.hand.pullingDown())
			{
				g.drawImage(this.handClosed, model.hand.x, model.hand.y, null);
			}
		}
	}
}
