import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Controller implements ActionListener, MouseListener
{
	View view;
	Model model;
	
	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
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
