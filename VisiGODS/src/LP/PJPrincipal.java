package LP;

import javax.swing.ImageIcon;

import LN.PJPrincipalImg;

import static Comun.clsConstantes.*;

public class PJPrincipal
{
	private PJPrincipalImg PJ;
	private boolean movimiento; 
	private int ContadorPos=0;
	private String MovSITUACION;
	private String IntentoMovSITUACION;
	private ImageIcon a,b;
	public PJPrincipal() 
	{
		movimiento=false;
		MovSITUACION="";
		IntentoMovSITUACION="STOP";
			
		PJ=new PJPrincipalImg(".\\resources\\PPrincipal\\MainF1.png");
		PJ.setLocation(400,200);
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


	public int getContadorPos() {
		return ContadorPos;
	}


	public void setContadorPos(int contadorPos) {
		ContadorPos = contadorPos;
	}

	public PJPrincipalImg getPJ() {
		return PJ;
	}


	public void setPJ(PJPrincipalImg pJ) {
		PJ = pJ;
	}


	public void IconUp()
	{
		if((b=new ImageIcon(U1)).equals(a)==false)PJ.setIcon(b);
	}
	public void IconDown()
	{
		if(ContadorPos<280)
		{
			if((b=new ImageIcon(D1)).equals(a)==false)PJ.setIcon(b);
			ContadorPos++;
		}
		else if(ContadorPos<300)
		{
			if((b=new ImageIcon(D2)).equals(a)==false)PJ.setIcon(b);
			ContadorPos++;
		}
		else
		{
			ContadorPos=0;
		}	

	}
	public void IconRight()
	{
		if(ContadorPos<90)
		{
			if((b=new ImageIcon(R1)).equals(a)==false)PJ.setIcon(b);
		}
		else if(ContadorPos<120)
		{
			if((b=new ImageIcon(R2)).equals(a)==false)PJ.setIcon(b);
		}
		
		if(ContadorPos<120)
		{
			ContadorPos++;
		}
		else
		{
			ContadorPos=0;	
		}
		
		
	}
	public void IconLeft()
	
	{
		if(ContadorPos<90)
		{
			if((b=new ImageIcon(L1)).equals(a)==false)PJ.setIcon(b);
		}
		else if(ContadorPos<120)
		{
			if((b=new ImageIcon(L2)).equals(a)==false)PJ.setIcon(b);
		}
		if(ContadorPos<120)
		{
			ContadorPos++;
		}
		else
		{
			ContadorPos=0;	
		}
	}
}
