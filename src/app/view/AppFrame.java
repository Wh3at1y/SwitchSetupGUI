package app.view;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.apple.eawt.*;

public class AppFrame extends JFrame
{
	private AppPanel panel;

	public AppFrame()
	{
		panel = new AppPanel();

		Application.getApplication().setDockIconImage(new ImageIcon(AppFrame.class.getResource("/resources/netLogo.png")).getImage());
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ImageRotator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Switch-Setup v.2");
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
