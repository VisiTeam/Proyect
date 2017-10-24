package LP;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PJPrincipal extends JLabel
{
	private static final long serialVersionUID = 1L;
	private boolean movimiento;
	private String MovSITUACION;
	private String IntentoMovSITUACION;
	private ImageIcon a;
	private Icon icono;
	private Point p;
	public PJPrincipal() 
	{
		movimiento=false;
		MovSITUACION="";
		IntentoMovSITUACION="STOP";
		p=new Point(100,100);
		setSize(100,100);
		setLocation(400,175);
		a=new ImageIcon(".\\resources\\PPrincipal\\MainF1.png"); 	
	}

	
	public String getIntentoMovSITUACION() {
		return IntentoMovSITUACION;
	}


	public void setIntentoMovSITUACION(String intentoMovSITUACION) {
		IntentoMovSITUACION = intentoMovSITUACION;
	}


	public boolean isMovimiento() {
		return movimiento;
	}


	public void setMovimiento(boolean movimiento) {
		this.movimiento = movimiento;
	}


	public String getMovSITUACION() {
		return MovSITUACION;
	}


	public void setMovSITUACION(String movSITUACION) {
		MovSITUACION = movSITUACION;
	}


	public void paintComponent(Graphics g)
	{
		icono = new ImageIcon(a.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		setIcon(icono);
        super.paintComponent(g);

	}
	
	public void setA(ImageIcon m)
	{
		a=m;
		
	}
	
	public void moveUp()
	{
		p=this.getLocation();

		p=new Point((p.x),(p.y-1));
		this.setLocation(p);
		
	}
	
	public void moveDown()
	{
		p=this.getLocation();
		
		p=new Point((p.x),(p.y+1));
		this.setLocation(p);
			

	}
	public void moveRight()
	{
		p=this.getLocation();
		
		p=new Point((p.x+1),(p.y));
		this.setLocation(p);
		
	}
	public void moveLeft()
	{
		p=this.getLocation();
		
		p=new Point((p.x-1),(p.y));
		this.setLocation(p);
	}
}
