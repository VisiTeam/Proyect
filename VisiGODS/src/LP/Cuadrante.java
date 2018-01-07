package LP;

import java.awt.Point;

import javax.swing.JPanel;

import Comun.clsConstantes;
import LN.clsArrayC;

public class Cuadrante extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static boolean movimiento;
	public static int cont;
	private Point numCuadrante;
	private PiezaMapa[][] Piezas;
	private Point pActual;
	
	public Cuadrante(String[][] a)
	{
		setLayout(null);
		movimiento=false;
		Piezas=clsArrayC.ConversorJLabel(a);
		if(a[0][15].equals("X"))
		{
			numCuadrante=PointMar();
			cont++;
		}
		else
		{
		cont=0;
		numCuadrante=new Point(Integer.parseInt(a[0][15]),Integer.parseInt(a[1][15]));
		}
		setBounds(1000*numCuadrante.x,750*numCuadrante.y,1000,750);
		
	}
	public void addpiezas()
	{
		for(int c=0;c<15;c++)
		{
			for(int d=0;d<20;d++)
			{
				this.add(Piezas[d][c]);
			}
		}
	}
	
	public Point getNumCuadrante() 
	{
		return numCuadrante;
	}
	public void setNumCuadrante(Point numCuadrante) 
	{
		this.numCuadrante = numCuadrante;
	}
	public PiezaMapa[][] getPiezas() 
	{
		return Piezas;
	}
	public void setPiezas(PiezaMapa[][] piezas) 
	{
		Piezas = piezas;
	}

	public static boolean isMovimiento() 
	{
		return movimiento;
	}

	public static void setMovimiento(boolean movimiento) 
	{
		Cuadrante.movimiento = movimiento;
	}
	public Point getpInit() 
	{
		return pActual;
	}

	public void setpInit(Point pInit) 
	{
		this.pActual = pInit;
	}

	public void moveUp()
	{
		pActual=this.getLocation();

		pActual=new Point((pActual.x),(pActual.y-1));
		this.setLocation(pActual);
		
	}
	
	public void moveDown()
	{
		pActual=this.getLocation();
		
		pActual=new Point((pActual.x),(pActual.y+1));
		this.setLocation(pActual);
			

	}
	public void moveRight()
	{
		pActual=this.getLocation();
		
		pActual=new Point((pActual.x+1),(pActual.y));
		this.setLocation(pActual);
		
	}
	public void moveLeft()
	{
		pActual=this.getLocation();
		
		pActual=new Point((pActual.x-1),(pActual.y));
		this.setLocation(pActual);
	}
	
	public Point PointMar()
	{
		switch(cont)
		{
		case 0:
			return clsConstantes.Point1;
		case 1:
			return clsConstantes.Point2;
		case 2:
			return clsConstantes.Point3;
		case 3:
			return clsConstantes.Point4;
		case 4:
			return clsConstantes.Point5;
		case 5:
			return clsConstantes.Point6;
		case 6:
			return clsConstantes.Point7;
		case 7:
			return clsConstantes.Point8;
		case 8:
			return clsConstantes.Point9;
		case 9:
			return clsConstantes.Point10;
		case 10:
			return clsConstantes.Point11;
		case 11:
			return clsConstantes.Point12;
			
			
		}
		return null;
	}

}
