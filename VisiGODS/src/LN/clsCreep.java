package LN;

public class clsCreep extends clsIA
{

	public clsCreep(String nombre, int vida, int ataque, int velocidad, int armadura, int oro, int probabilidad,int ID) {
		super(nombre, vida, ataque, velocidad, armadura, oro, probabilidad,ID);
		// TODO Auto-generated constructor stub
	}
	
	public void daño(int ataque)
	{
		int Daño=ataque-this.getArmadura();
		if(Daño>0)
		{
		this.setVida(this.getVida()-Daño);
		}
	}

}
