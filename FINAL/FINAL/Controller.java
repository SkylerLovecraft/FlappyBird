import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Controller implements ActionListener, MouseListener
{
	View view;
	Model model;
	static final int ACTION_NOTHING = 0;
	static final int ACTION_FLAP = 1;
	static final int ACTION_CHUCK = 2;
	Controller(Model m)
	{
		model = m;
	}

	public void update()
	{
	//System.out.println("here1");
	double score_nothing = model.evaluateAction(ACTION_NOTHING, 0);
	//System.out.println("here");
	double score_flap = model.evaluateAction(ACTION_FLAP, 0);
	double score_chuck = model.evaluateAction(ACTION_CHUCK, 0);
	
	System.out.println("score_nothing: " +score_nothing);
	System.out.println("score_flap: " +score_flap);
	//System.out.println("score_chuck: " +score_chuck);
	// Do the best one
	if(score_chuck > score_flap && score_chuck > score_nothing)
		do_action(ACTION_CHUCK);
	else if(score_flap > score_nothing)
	{
		System.out.println("doing fucking flap");
		do_action(ACTION_FLAP);
	}
	else {
	   do_action(ACTION_NOTHING);
	   if(score_nothing == 0)
		   System.out.println("Found no way to survive!");
	}

	}
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("Hey! I said never push that button!");
		view.removeButton();
	}
	public void do_action(int i)
	{
		System.out.println("in do action");
		if(i == 0)
		{
			System.out.println("doing nothing");
			return;
		}
		if(i == 1)
		{
			System.out.println("Flapping");
			model.onClick();
		}
		if(i == 2)
		{
			model.onRightClick();
		}
	}
	void setView(View v)
	{
		view = v;
		view.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e)
	{
		//g.run();
		if(e.getButton() != MouseEvent.BUTTON3)
			model.onClick();
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			model.onRightClick();
			System.out.println("uwu");
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered (MouseEvent e) {    }
	public void mouseExited  (MouseEvent e) {    }
	public void mouseClicked (MouseEvent e) {    }

}
