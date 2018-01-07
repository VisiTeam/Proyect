package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import LN.User;
import LN.clsCreep;

public class PanelCombate extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	private User PJ;
	private clsCreep Creep;
	private JButton Boton1;
	private JButton Boton2;
	private JButton Boton3;

	public PanelCombate(User a, clsCreep b)
	{
		PJ=a;
		Creep=b;
		
		ActionListener ataque= new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						Creep.daño(PJ.getAtaque());
					}
				};
	}
}
