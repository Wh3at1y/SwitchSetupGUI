package app.view;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;

public class AppFrame extends JFrame
{
	private AppPanel panel;

	public AppFrame()
	{
		panel = new AppPanel();

		String OS = System.getProperty("os.name").toLowerCase();
		//System.out.println(OS);
		if(OS.contains("mac"))
			com.apple.eawt.Application.getApplication().setDockIconImage( new ImageIcon(getClass().getResource( "/resources/netIcon.png" )).getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Switch-Setup v.2");
		this.setSize(new Dimension(800, 600));
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
