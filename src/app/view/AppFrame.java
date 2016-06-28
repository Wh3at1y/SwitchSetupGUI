package app.view;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AppFrame extends JFrame
{
	private AppPanel panel;
	
	public AppFrame()
	{
		panel = new AppPanel();
		
		URL iconURL = getClass().getResource("/resources/netLogo.gif");
		// iconURL is null when not found
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		
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

