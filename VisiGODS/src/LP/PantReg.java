package LP;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PantReg extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static PantallaPrincipal MiPantalla;
	static PantReg PR;
	JLabel Nombre;
	JTextField nombretxt;
	JButton ACCEPT;
	JButton CANCEL;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					PR=new PantReg();
					PR.setVisible(true);
					
					
				}catch (Exception e) 
				{
					e.printStackTrace();	
				}
			}
		});
	}
	public PantReg()
	{
		setTitle("Logging Name");
		setResizable(false);
		setSize(250, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		this.setContentPane(panel);
		
		Nombre=new JLabel("Nombre:");
		this.getContentPane().add(Nombre);
		nombretxt=new JTextField();
		nombretxt.setColumns(15);
		this.getContentPane().add(nombretxt);
		
		ACCEPT=new JButton("Entrar");
		ACCEPT.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try {
							MiPantalla = new PantallaPrincipal(nombretxt.getText());
						} catch (IOException e) 
						{
							e.printStackTrace();
						}
						MiPantalla.setVisible(true);
						MiPantalla.requestFocus();
						PR.dispose();
						
					}
			
				});
		CANCEL=new JButton("Salir");
		CANCEL.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						PR.dispose();
						
					}
			
				});
		this.getContentPane().add(ACCEPT);
		this.getContentPane().add(CANCEL);
	}

}
