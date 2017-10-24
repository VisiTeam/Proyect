package LP;

import javax.swing.ImageIcon;

public class Soldier1 extends PiezaMapa 
{
	
	private static final long serialVersionUID = 1L;
	private static ImageIcon p;
	public Soldier1() 
	{
		super(p);
		p=new ImageIcon("resources/Soldier1.png") ;
		setIcon(p);
		setLocation(0,250);
		setSize(50,55);

	}

}
