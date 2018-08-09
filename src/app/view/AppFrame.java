package app.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AppFrame extends JFrame
{
	private AppPanel panel;

	public AppFrame()
	{
		panel = new AppPanel();


		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.contains("mac"))
			com.apple.eawt.Application.getApplication().setDockIconImage( new ImageIcon(getClass().getResource( "/resources/netIcon.png" )).getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true); //set to true if uncommenting other code in class
		this.setTitle("Switch-Setup v.3.9.1");
		this.setSize(new Dimension(800, 600));
		this.setMinimumSize(this.getSize());
		this.setContentPane(panel);
		this.setVisible(true);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension d=AppFrame.this.getSize();
				Dimension minD=AppFrame.this.getMinimumSize();
				if(d.width<minD.width)
					d.width=minD.width;
				if(d.height<minD.height)
					d.height=minD.height;
				AppFrame.this.setSize(d);
			}

			{}});

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
