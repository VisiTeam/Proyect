package LN;

import java.util.ArrayList;
import java.util.Random;

public class clsCombate 
{
	int Probabilidad;
	int pCreep;
	final int MAX=100;
	public ArrayList<clsCreep> Creepys;
	public clsCreep Contrincante;
	
	public clsCombate(ArrayList<clsCreep> a) 
	{
		Probabilidad=0;
		Creepys=a;
	}
	public void resetProb()
	{
		Probabilidad=0;
	}
	public void aumentarProb()
	{
		Probabilidad=Probabilidad+((MAX-Probabilidad)/10);
	}
	public boolean IsCombat()
	{
		Random r=new Random();
		int p1=r.nextInt(MAX-Probabilidad);
		if(p1<10)
		{
			contrincante();
			return true;
		}
		else if(p1>70)
		{
			if(IsCombat()) return true;
		}
		return false;
	}
	private void contrincante()
	{
		pCreep=0;
		ArrayList<Integer> a=new ArrayList<Integer>();
		Random r=new Random();
		for(clsCreep cr: Creepys)
		{
			pCreep=cr.getProbabilidad();
			for(int i=0;i<pCreep;i++)
			{
				a.add(cr.getID());
			}
		}
		int p2=r.nextInt(a.size());
		for(clsCreep cr: Creepys)
		{
			if(cr.getID()==a.get(p2))
			{
				Contrincante=cr;
			}
		}
	}
	
	

}
