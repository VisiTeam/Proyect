package LD;

import java.io.*;
import java.util.ArrayList;

import Comun.clsConstantes.enumFicheros;
import LN.clsArrayC;

/** Clase encargada de leer y cargar un mapa creado por un usuario, se valera de un metodo filereader para convertir un archivo .map (.txt)
 *  en un array de chars o ints*/

public class clsLectura
{
	private final String fichero_info = ".\\data\\info.txt";
	private final String fichero_pj = ".\\data\\Ranura1\\pj.txt";
	private final String Info_cuad0 = ".\\data\\Mapa\\Cuad0.txt";
	private final String Info_cuad1 = ".\\data\\Mapa\\Cuad1.txt";
	private final String Info_cuad2 = ".\\data\\Mapa\\Cuad2.txt";
	private final String Info_cuad3 = ".\\data\\Mapa\\Cuad3.txt";
	private final String Info_cuad4 = ".\\data\\Mapa\\Cuad4.txt";

	private ObjectInputStream ois;
	/**Clase encargada de seleccionar el fichero donde se leeran los datos*/
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
	
	private String setCuadrante(int ranura)
	{
		switch(ranura)
		{
			case 0:
			{
				return Info_cuad0;
			}
			case 1:
			{
				return Info_cuad1;
			}
			case 2:
			{
				return Info_cuad2;
			}
			case 3:
			{
				return Info_cuad3;
			}
			default:
			{
				return Info_cuad4;				
			}
		}
	}
	/**
	 * 
	 * @param fichero: enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a leer. 
	 * @throws IOException: excepción lanzada en caso de que se dé un error de lectura/escritura en fichero.
	 * Método que crea un objectInputStream para la lectura del fichero indicado previamente.
	 */
	public void ComenzarRead(enumFicheros fichero) throws IOException 
	{
		String ruta=setFichero(fichero);
		File fic;

		fic=new File(ruta);

		if(!fic.exists())
		{
			fic.createNewFile();
		}
		ois=new ObjectInputStream(new FileInputStream(fic));
	}

	/**
	 * 
	 * @return ArrayList<Serializable>: Devuelve un arraylist con los datos leídos, en el tipo
	 * Serializable.
	 */
	public ArrayList<Serializable> Read()
	{
		ArrayList<Serializable> lista = new  ArrayList<Serializable>();
		Serializable a;

		try 
		{
			while((a=(Serializable)ois.readObject())!=null)
			{
				lista.add(a);
			}
		}
		catch (ClassNotFoundException | IOException e){} 

		return lista;
	}
	/**
	 * Método que cierra el fichero del que se ha leído.
	 */
	public void TerminarRead()
	{
		try {
			ois.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public String[][] LeertxtM(int fich) throws IOException, FileNotFoundException
	{
		final int tamx=20;
		final int tamy=16;
		boolean comprobacion=true;
		String[][] mapa;
		String text;
		FileReader fr;
		BufferedReader br;
		File fic;
		
		mapa= new String[tamx][tamy];
		String ruta=this.setCuadrante(fich);
		fic= new File(ruta);
		
		try
		{ 
			fr= new FileReader(ruta);
			br= new BufferedReader(fr);
			
			for(int i=0 ; i<tamy ; i++)
			{
				text=br.readLine();
				
				if(text != null && !text.isEmpty())
				{
					comprobacion=false;
					clsArrayC.ConvertirFormatoMapa(mapa, text, i);
				}
				if(comprobacion==true)
				{
					mapa=null;
				}
			}
		}
		catch(IOException f)
		{
			fic.createNewFile();
			throw f;
		}
	
		br.close();
		return mapa;
	}
	public ArrayList<String[]> LeertxtI(enumFicheros ficheroInfo) throws IOException, FileNotFoundException
	{
		final int tamx=7;
		ArrayList<String[]> a=new ArrayList<String[]>();
		String[] line;
		String text;
		FileReader fr;
		BufferedReader br;
		File fic;
		
		line= new String[tamx];
		String ruta=this.setFichero(ficheroInfo);
		fic= new File(ruta);
		
		try
		{ 
			fr= new FileReader(ruta);
			br= new BufferedReader(fr);
			
			while((text=br.readLine()) != null)
			{
					line=clsArrayC.ConvertirFormatoInfo( text);
					a.add(line);
			}
		}
		catch(IOException f)
		{
			fic.createNewFile();
			throw f;
		}
	
		br.close();
		return a;
	}
	public void ResetFile (enumFicheros fichero)
	{
		try 
		{
			ois.reset();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
}