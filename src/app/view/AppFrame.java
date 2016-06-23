package app.view;

import javax.swing.JFrame;
import app.controller.AppController;

public class AppFrame extends JFrame
{
	private AppPanel panel;
	
	public AppFrame(AppController controller)
	{
		panel = new AppPanel(controller);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Switch-Setup v.1");
		this.setSize(600, 500);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public AppPanel getPanel()
	{
		return panel;
	}
}

