package LP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import Comun.clsConstantes;
import LD.clsLectura;

public class PantallaPrincipal extends JFrame {
	
private static final long serialVersionUID = 1L;

private static PantallaPrincipal MiPantalla;
private JPanel PPrincipal;
private KeyEvent event;
private ArrayList<Cuadrante> Cuad;
private final int MaxCuad=3;
private String[][] map;
private int cont=0,cont2=0,s=10,cuadrante=0;
private boolean Funciona, Funciona2, posibilidad=false;
private MiHilo ElHilo;
private MovMapa OtroHilo;
private PJPrincipal pj;
private static Thread HiloCoche;
private static Thread HiloMapa;
private Point source=new Point(8,4);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiPantalla = new PantallaPrincipal();
					MiPantalla.setVisible(true);
					MiPantalla.requestFocus();
					
					MiPantalla.ElHilo=MiPantalla.new MiHilo();
					HiloCoche=new Thread(MiPantalla.ElHilo);
					HiloCoche.start();
					
					MiPantalla.OtroHilo=MiPantalla.new MovMapa();
					HiloMapa=new Thread(MiPantalla.OtroHilo);
					HiloMapa.start();

				}catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}
	
	public PantallaPrincipal() throws IOException 
	{
		setSize(815, 475);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		PPrincipal=new JPanel();
		PPrincipal.setLayout(null);
		setContentPane(PPrincipal);

		pj=new PJPrincipal();
		PPrincipal.add(pj);
		PPrincipal.setComponentZOrder(pj, 0);
		Cuad=new ArrayList<Cuadrante>();
		cargadelmapa();
		
		addKeyListener(new KeyListener()
				{
					@Override
					public void keyPressed(KeyEvent e)
					{
						event=null;
						posibilidad=false;
						
						if(cont==1)event=e;
						
						if(Cuadrante.isMovimiento()==false)
						{
							pj.setMovimiento(true);
							cont=1;
							
							if(KeyEvent.VK_RIGHT==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Derecha);
								Cuadrante.setMovimiento(true);
							}

							if(KeyEvent.VK_DOWN==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Abajo);
								Cuadrante.setMovimiento(true);
							}

							if(KeyEvent.VK_LEFT==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Izquierda);
								Cuadrante.setMovimiento(true);
							}

							if(KeyEvent.VK_UP==e.getKeyCode())
							{
								pj.setMovSITUACION(clsConstantes.Moviendo_Arriba);
								Cuadrante.setMovimiento(true);
							}
						}
					}
					@Override
					public void keyReleased(KeyEvent arg0) 
					{
						posibilidad=true;
					}

					@Override
					public void keyTyped(KeyEvent arg0) {}
			
				});
	}
	private class MiHilo implements Runnable
	{

		@Override
		public void run()
		{	
			PPrincipal.repaint();
			Funciona2=true;
			pj.setMovimiento(true);
			while(Funciona2)
				
			{
				if(pj.isMovimiento()==true)
				{
				s=10;
				if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)	
				{
					
					if(cont2<90)
					{cont2++;
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
					}
					else if(cont2<120)
					{cont2++;
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainL2.png"));
					}
					else
					{
					cont2=0;	
					}
				
				}
				if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
				{
					if(cont2<90)
					{cont2++;
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
					}
					else if(cont2<120)
					{cont2++;
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainR2.png"));
					}
					else
					{
					cont2=0;	
					}
				}
				if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
				{
					
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainB1.png"));
					
				}
				if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
				{
					if(cont2!=300)
					{cont2++;
					
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainF1.png"));
					
						
					}
					else
					{cont2=0;
						pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainF2.png"));
						s=150;
					}
					
				}
				pj.repaint();
				}
				try 
				{
					Thread.sleep(s);
				}catch (Exception e) {}
			}
		}
	}

	private class MovMapa implements Runnable
	{
		
		@Override
		public void run()
		{	
			Funciona=true;
			while(Funciona)
			{
				if(Cuadrante.isMovimiento()==true)
				{
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)
					{
						comprobacion();
					
						if(Cuad.get(cuadrante).getPiezas()[source.x-1][source.y].isMovilidad())
						{	
							source.setLocation(source.x-1, source.y);
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveRight();
								}
								

								if(event!=null)IntentoMovimiento();
								try 
								{
									Thread.sleep(6);
								}catch (Exception e) {}
							}
							
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
								Cuadrante.setMovimiento(false);
								pj.setMovimiento(false);
								cont=0;
								pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
								}
							
							}
							
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.x==20)
							{
								source.x=0;
								if(cuadrante==0)cuadrante=1;
								else
								{
								cuadrante=3;
								}
							}
							Cuadrante.setMovimiento(false);
							pj.setMovimiento(false);
							cont=0;
							cont2=0;
							pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
						}
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
					{
						comprobacion();
						if(Cuad.get(cuadrante).getPiezas()[source.x+1][source.y].isMovilidad())
						{
							source.setLocation(source.x+1, source.y);
					
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveLeft();
								}
								if(event!=null)IntentoMovimiento();
								try 
								{
									Thread.sleep(6);
								}catch (Exception e) {}
							}
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
								Cuadrante.setMovimiento(false);
								pj.setMovimiento(false);
								cont=0;
								pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
								}
							}
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.x==-1)
							{
								source.x=19;
								if(cuadrante==1)cuadrante=0;
								else
								{
								cuadrante=2;
								}
							}
							Cuadrante.setMovimiento(false);
							pj.setMovimiento(false);
							cont=0;
							cont2=0;
							pj.setA(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
						}
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
					{
						comprobacion();
						
						if(Cuad.get(cuadrante).getPiezas()[source.x][source.y-1].isMovilidad())
						{

							source.setLocation(source.x, source.y-1);
					
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveDown();
								}
								if(event!=null)IntentoMovimiento();
								try 
								{
									Thread.sleep(6);
								}catch (Exception e) {}
							}
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
									Cuadrante.setMovimiento(false);
									cont=0;
								}
							}
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.y==15)
							{
								source.y=0;
								if(cuadrante==0)cuadrante=2;
								else
								{
								cuadrante=3;
								}
							}
							Cuadrante.setMovimiento(false);
							cont=0;
							cont2=0;
						}	
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
					{
						comprobacion();
						if(Cuad.get(cuadrante).getPiezas()[source.x][source.y+1].isMovilidad())
						{
							source.setLocation(source.x, source.y+1);
							for(int j=0;j<50;j++)
							{
								for(Cuadrante z:Cuad)
								{
								z.moveUp();
								}
								if(event!=null)IntentoMovimiento();
						
								try 
								{
									Thread.sleep(6);
								}
								
								catch (Exception e) {}
							}
							if(pj.getIntentoMovSITUACION()=="STOP")
							{
								if(posibilidad)
								{
								Cuadrante.setMovimiento(false);
								cont=0;
								}
							}
							else
							{
								pj.setMovSITUACION(pj.getIntentoMovSITUACION());
								pj.setIntentoMovSITUACION("STOP");
								event=null;
							}
						}
						else
						{
							if(source.y==-1)
							{
								source.y=14;
								if(cuadrante==3)cuadrante=1;
								else
								{
								cuadrante=0;
								}
							}
							Cuadrante.setMovimiento(false);
							cont=0;
						}
					}
				}
				try 
				{
					Thread.sleep(8);
				}catch (Exception e) {}
				MiPantalla.requestFocus();
			}
		}
	}
	
	public class RunnablePJPrincipal implements Runnable {
		@Override
		public void run() {
			while(true)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (Exception e) {}
			}

		}
	};
	
	private void cargadelmapa()
	{
		clsLectura a=new clsLectura();
		
		
		for(int NumCuadr=0;NumCuadr<=MaxCuad;NumCuadr++)
		{
			try 
			{
				map=a.LeerMapa(NumCuadr);
			} 	
			catch (FileNotFoundException e) {} 
			catch (IOException e) {}
			int[] abc=new int[2];
			abc[0]=Integer.parseInt(map[0][15]);
			abc[1]=Integer.parseInt(map[1][15]);
			Cuadrante j=new Cuadrante(map,abc);
			j.addpiezas();
			Cuad.add(j);
		
		}
		for(Cuadrante z:Cuad)
		{
			PPrincipal.add(z);
		}

	}
	private void IntentoMovimiento()
	{
		if(KeyEvent.VK_RIGHT==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Derecha);
		}

		if(KeyEvent.VK_DOWN==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Abajo);
		}

		if(KeyEvent.VK_LEFT==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Izquierda);
		}

		if(KeyEvent.VK_UP==event.getKeyCode())
		{
			pj.setIntentoMovSITUACION(clsConstantes.Moviendo_Arriba);
		}
	}
	private void comprobacion()
	{
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)
		{
			if(source.x-1==-1)
			{
				source.x=20;
				if(cuadrante-10>=0)cuadrante=cuadrante-1;
				else
				{
				cuadrante=2;
				}
			}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
		{
		if(source.x+1==20)
		{
			source.x=-1;
			if(cuadrante==0)cuadrante=1;
			else
			{
			cuadrante=3;
			}
		}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
		{
		if(source.y+1==15)
		{
			source.y=-1;
			if(cuadrante==0)cuadrante=2;
			else
			{
			cuadrante=3;
			}
		}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
		{
		if(source.y-1==-1)
		{
			source.y=15;
			if(cuadrante==3)cuadrante=1;
			else
			{
			cuadrante=0;
			}
		}
		}
	}
	

}
