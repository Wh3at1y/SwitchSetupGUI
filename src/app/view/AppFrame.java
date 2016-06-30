package app.view;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AppFrame extends JFrame
{
	private AppPanel panel;
	
	public AppFrame()
	{
		panel = new AppPanel();
		try{
		setIconImage(ImageIO.read(AppFrame.class.getResourceAsStream("/resources/netLogo.gif")));
    }catch(Exception e){System.out.println(e);}
		
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

