package LN;

public abstract class clsIA 
{
	private String nombre;
	private final int VidaTotal;
	private int vida;
	private int ataque;
	private int velocidad;
	private int armadura;
	private int oro;
	private int probabilidad;
	private int ID;
	public clsIA(String nombre, int vida, int ataque, int velocidad, int armadura, int oro, int probabilidad, int ID) 
	{
		super();
		VidaTotal=vida;
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.velocidad = velocidad;
		this.armadura = armadura;
		this.oro = oro;
		this.probabilidad = probabilidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getArmadura() {
		return armadura;
	}
	public void setArmadura(int armadura) {
		this.armadura = armadura;
	}
	public int getOro() {
		return oro;
	}
	public void setOro(int oro) {
		this.oro = oro;
	}
	public int getProbabilidad() {
		return probabilidad;
	}
	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	
	

}
