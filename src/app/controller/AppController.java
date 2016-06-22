package app.controller;

import app.view.*;

public class AppController
{
	private AppFrame frame;
	
	public AppController()
	{
		frame = new AppFrame(this);
	}
}
