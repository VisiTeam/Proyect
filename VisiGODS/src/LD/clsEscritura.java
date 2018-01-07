package LD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Comun.clsConstantes.enumFicheros;

public class clsEscritura
{
	private final String fichero_info = ".\\data\\info.rpg";
	private final String fichero_pj = ".\\data\\pj.rpg";
	
	ObjectOutputStream oos;
	AppendableObjectOutputStream Aoos;
	
	/**Clase encargada de seleccionar el fichero donde se guardaran los datos*/
	private String setFichero(enumFicheros fichero)
	{
		switch(fichero)
		{
		case FICHERO_INFO:
		{
			return fichero_info;
		}
		case FICHERO_PJ:
		{
			return fichero_pj;
		}
		}
		return null;
	}
		
	/**
	 * 
	 * @param fichero: 	enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a escribir.
	 * Método que debe crear un objectOutputStream o un AppendableObjectOutputStream para proceder
	 * a la escritura del fichero. Si el fichero existe,AppendableObjectOutputStream; de lo 
	 * contrario objectOutputStream
	 */
	public void ComenzarSave(enumFicheros fichero) 
	{
		String ruta=setFichero(fichero);
		File fic;
		
		fic=new File(ruta);
		
		
		if(fic.exists())
		{
			try
			{
				Aoos=new AppendableObjectOutputStream(new FileOutputStream(fic));
				Aoos.writeStreamHeader();
				oos= new ObjectOutputStream(new FileOutputStream(fic));
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				fic.createNewFile();
				oos= new ObjectOutputStream(new FileOutputStream(fic));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	
	}

	/**
	 * Método que debe cerrar el fichero en el que se ha escrito.
	 */
	public void TerminarSave() 
	{
		try 
		{
			oos.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param o: Objeto a guardar, que debe implementar la interfaz serializable.
	 * Método que guarda en el fichero indicado previamente el objeto recibido.
	 */
	public void Save(Serializable o)
	{
		try
		{
			oos.writeObject(o);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param fichero enumerado de la clase clsConstantes que indica el fichero que se
	 * 					va a borrar.
	 * Se borra el fichero (porque vamos a escribir datos modificados).
	 */
	public void ResetFile(enumFicheros fichero)
	{
		try 
		{
			oos.reset();
		} 
		catch (IOException e) 
		{

		}
		
	}

}