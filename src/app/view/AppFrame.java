package app.view;
import javax.swing.JFrame;

public class AppFrame extends JFrame
{
	private AppPanel panel;

	public AppFrame()
	{
		panel = new AppPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle("Switch-Setup v.1");
		this.setSize(800, 600);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	public AppPanel getPanel()
	{
		return panel;
	}
	
	public AppFrame getFrame()
	{
		return this;
	}
}
