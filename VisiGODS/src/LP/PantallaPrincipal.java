package LP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import Comun.clsConstantes;
import Comun.clsConstantes.enumFicheros;
import LD.clsLectura;
import LN.AudioManager;
import LN.User;
import LN.clsCombate;
import LN.clsCreep;
import LP.PantallaPrincipal.MiHilo;
import LP.PantallaPrincipal.MovMapa;

public class PantallaPrincipal extends JFrame {
	
private static final long serialVersionUID = 1L;

private JPanel PPrincipal;
private KeyEvent event;
private ArrayList<Cuadrante> Cuad;
private final int MaxCuad=15;
private String[][] map;
private ArrayList<String[]> lineas;
private ArrayList<clsCreep> Creeps;
private int maxALTO_PANT=628, maxANCHO_PANT=806,ALTO_PANT=600, ANCHO_PANT=800, cont=0,s=10;
private boolean Funciona, Funciona2, posibilidad=false, bolPlay=false, bolOptions=false;
private MiHilo ElHilo;
private MovMapa OtroHilo;
private Cuadrante cuadran;
private AudioManager Musica;
private PJPrincipal pj;
private static Thread HiloCoche;
private static Thread HiloMapa;
private Point CuadActual=new Point(0,0);
private Point source=new Point(8,4);
private MouseListener BotonPlayOpt;
private WindowListener AjusteVentana;
private FocusListener FocusThings;
private User us;
private clsCombate c;

	

	
	public PantallaPrincipal(String nick) throws IOException 
	{
		ElHilo=new MiHilo();
		OtroHilo=new MovMapa();
		
		HiloCoche=new Thread(ElHilo);
		HiloMapa=new Thread(OtroHilo);
		
		setResizable(false);
		setSize(maxANCHO_PANT, maxALTO_PANT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		us=new User(nick);
		Musica=new AudioManager();
		Musica.setAudio(clsConstantes.Musica_1);
		Musica.IniciarAudioInf();
		
		PPrincipal=new JPanel();
		PPrincipal.setLayout(null);
		setContentPane(PPrincipal);


		JLabel Portada1 = new JLabel();
		Portada1.setSize(ANCHO_PANT, ALTO_PANT);
		Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Portada.png"));
		PPrincipal.add(Portada1);
		
		FocusThings=new FocusListener()	
		{
			@Override
			public void focusGained(FocusEvent arg0) 
			{}

			@Override
			public void focusLost(FocusEvent arg0) 
			{
				PPrincipal.requestFocus();
			}	
		};
		addFocusListener(FocusThings);
		
		AjusteVentana= new WindowListener()
		{
			@Override
			public void windowActivated(WindowEvent arg0) 
			{}

			@Override
			public void windowClosed(WindowEvent arg0) 
			{
				Musica.PararAudio();
				if(ElHilo!=null)ElHilo.stopit();
				if(OtroHilo!=null)OtroHilo.stopit();
			}

			@Override
			public void windowClosing(WindowEvent arg0) 
			{}

			@Override
			public void windowDeactivated(WindowEvent arg0) 
			{}

			@Override
			public void windowDeiconified(WindowEvent arg0) 
			{}

			@Override
			public void windowIconified(WindowEvent arg0) 
			{}

			@Override
			public void windowOpened(WindowEvent arg0) 
			{}
			
		};
		addWindowListener(AjusteVentana);
		
		BotonPlayOpt = new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Portada.png"));
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int x=e.getX();
			    int y=e.getY();
			
				if (x>258 && x<577 && y>260 && y<329)
				{
					bolPlay=true;
					Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Play.png"));
				}
				
				else if (x>258 && x<577 && y>360 && y<430)
				{
					bolOptions=true;
					Portada1.setIcon(new ImageIcon(".\\resources\\Menus\\Click.png"));
				}
				
			}
		
			public void mouseClicked(MouseEvent e) 
			{
				if (bolPlay==true)
				{
					PPrincipal.removeAll();
					PPrincipal.removeMouseListener(BotonPlayOpt);
					CargaJuego();
					PPrincipal.repaint();
					bolPlay=false;
				}
				
				if (bolOptions==true)
				{
					bolOptions=false;
				}
			}
			public void mouseEntered(MouseEvent e) 
			{}
			
			public void mouseExited(MouseEvent e) 
			{}
		};
		addMouseListener(BotonPlayOpt);
		
	}
	
	public void CargaJuego()
	{
		
		pj=new PJPrincipal();
		PPrincipal.add(pj.getPJ());
		PPrincipal.setComponentZOrder(pj.getPJ(), 0);
		removeMouseListener(BotonPlayOpt);
		Cuad=new ArrayList<Cuadrante>();
		cargadelmapa();
		cargaInfo();
		
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
							switch (e.getKeyCode()) {
							case KeyEvent.VK_UP: {
								pj.setMovSITUACION(clsConstantes.Moviendo_Arriba);
								Cuadrante.setMovimiento(true);
								break;
							}
							case KeyEvent.VK_DOWN: {
								pj.setMovSITUACION(clsConstantes.Moviendo_Abajo);
								Cuadrante.setMovimiento(true);
								break;
							}
							case KeyEvent.VK_LEFT: {
								pj.setMovSITUACION(clsConstantes.Moviendo_Izquierda);
								Cuadrante.setMovimiento(true);
								break;
							}
							case KeyEvent.VK_RIGHT: {
								pj.setMovSITUACION(clsConstantes.Moviendo_Derecha);
								Cuadrante.setMovimiento(true);
								break;
							}
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
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1){}
		
		HiloCoche.start();
		HiloMapa.start();
	}
	
	
	
	class MiHilo implements Runnable
	{

		@Override
		public void run()
		{	
			Funciona2=true;
			pj.setMovimiento(true);
			s=10;
			
			while(Funciona2)
			{
				if(pj.isMovimiento()==true)
				{
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)	
					{
						pj.IconLeft();
					}
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
					{
						pj.IconUp();
					}
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
					{
						pj.IconRight();
					}
					
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
					{
						pj.IconDown();
					}
				}
				try 
				{
					Thread.sleep(s);
				}catch (Exception e) {}
			}
		}
		public void stopit()
		{
			Funciona2=false;
		}
	}

	class MovMapa implements Runnable
	{
		
		@Override
		public void run()
		{	
			Funciona=true;
			SeleccionC();
			
			while(Funciona)
			{
				if(Cuadrante.isMovimiento()==true)
				{
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Izquierda)
					{
						comprobacion();
						
					
						if(cuadran.getPiezas()[source.x-1][source.y].isMovilidad())
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
								pj.getPJ().setIcon(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
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
								CuadActual.y=CuadActual.y+1;
								SeleccionC();
							}
							Cuadrante.setMovimiento(false);
							pj.setMovimiento(false);
							cont=0;
							pj.setContadorPos(0);
							pj.getPJ().setIcon(new ImageIcon(".\\resources\\PPrincipal\\MainL1.png"));
						}
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
					{
						comprobacion();
						if(cuadran.getPiezas()[source.x+1][source.y].isMovilidad())
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
								pj.getPJ().setIcon(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
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
								CuadActual.y=CuadActual.y-1;
								SeleccionC();
								
							}
							Cuadrante.setMovimiento(false);
							pj.setMovimiento(false);
							cont=0;
							pj.setContadorPos(0);
							pj.getPJ().setIcon(new ImageIcon(".\\resources\\PPrincipal\\MainR1.png"));
						}
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
					{
						comprobacion();
						
						if(cuadran.getPiezas()[source.x][source.y-1].isMovilidad())
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
								CuadActual.x=CuadActual.x+1;
								SeleccionC();
							}
							Cuadrante.setMovimiento(false);
							cont=0;
							pj.setContadorPos(0);
						}	
					}
					if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
					{
						comprobacion();
						if(cuadran.getPiezas()[source.x][source.y+1].isMovilidad())
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
								CuadActual.x=CuadActual.x-1;
								SeleccionC();
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
			}
		}
		public void stopit()
		{
			Funciona=false;
		}
	}
	
	
	private void cargadelmapa()
	{
		clsLectura a=new clsLectura();
		
		for(int NumCuadr=0;NumCuadr<=MaxCuad;NumCuadr++)
		{
			try 
			{
				map=a.LeertxtM(NumCuadr);
			} 	
			catch (IOException d){} 
			
			Cuadrante j=new Cuadrante(map);
			j.addpiezas();
			Cuad.add(j);
		}
		
		for(Cuadrante z:Cuad)
		{
			PPrincipal.add(z);
		}

	}
	private void cargaInfo()
	{
		clsLectura a=new clsLectura();
		clsCreep aux1;
		Creeps=new ArrayList<clsCreep>();
			try 
			{
				lineas=a.LeertxtI(enumFicheros.FICHERO_INFO);
			} 	
			catch (IOException d){} 
			for(String[] aux:lineas)
			{
				aux1=new clsCreep(aux[0], Integer.parseInt(aux[1]), Integer.parseInt(aux[2]), Integer.parseInt(aux[3]), Integer.parseInt(aux[4]), Integer.parseInt(aux[5]), Integer.parseInt(aux[6]), Integer.parseInt(aux[7]));
				Creeps.add(aux1);
			}
			c=new clsCombate(Creeps);
			

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
				CuadActual.y=CuadActual.y-1;
				SeleccionC();
			}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Derecha)
		{
		if(source.x+1==20)
		{
			source.x=-1;
			CuadActual.y=CuadActual.y+1;
			SeleccionC();
		}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Abajo)
		{
		if(source.y+1==15)
		{
			source.y=-1;
			CuadActual.x=CuadActual.x+1;
			SeleccionC();
		}
		}
		if(pj.getMovSITUACION()==clsConstantes.Moviendo_Arriba)
		{
		if(source.y-1==-1)
		{
			source.y=15;
			CuadActual.x=CuadActual.x-1;
			SeleccionC();
		}
		}
	}
	private void SeleccionC()
	{
		for(Cuadrante cc:Cuad)
		{
			if(CuadActual.y==cc.getNumCuadrante().x&&CuadActual.x==cc.getNumCuadrante().y)
			{
				cuadran=cc;
				return;
			}
		}
	}
	

}
