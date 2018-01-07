package LN;

import LP.PJPrincipal;

public class User 
{
	private String nick;
	private int money;
	private int lvl;
	private int vida;
	private int velocidad;
	private int ataque;
	private int defensa;
	private PJPrincipal pjLinked;

	public User(String nomb) 
	{
		nick=nomb;
		money=0;
		lvl=1;
	}

	public String getNick() {
		return nick;
	}

	public int getMoney() {
		return money;
	}

	public int getLvl() {
		return lvl;
	}

	public PJPrincipal getPjLinked() {
		return pjLinked;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public void setPjLinked(PJPrincipal pjLinked) {
		this.pjLinked = pjLinked;
	}
	
	

	
	
	
}
