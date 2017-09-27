import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/*CHANGELOG
	-the infinite while loop is no longer infinite and is controlled by the 
	 bool in the hand class "offScreen"
*/
public class Game extends JFrame
{
	Model model;
	View view;
	Controller controller;
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("Bird which flaps");
		this.setSize(500, 500);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run()
	{
		while(model.hand.offScreen == false)
		{
			model.update();
			System.out.println(model.bird.getEnergy());
			view.repaint(); // Indirectly calls View.paintComponent
			//Model copy = new Model(this.model);
			controller.update();
			// Go to sleep for 33 ms, (about 30 fps, 3.01 something)
			try
			{
				Thread.sleep(33);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	public void endGame()
	{
		System.exit(0);
	}
	public static void main(String[] args)
	{
		//System.out.println("In main");
		Game g = new Game();
		g.run();
		g.endGame();
	}
}
