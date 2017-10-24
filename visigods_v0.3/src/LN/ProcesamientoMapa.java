package LN;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class ProcesamientoMapa {
	public static String[][] CasillasSpecial(JLabel[][] MapaPosicion)
	{
		final int tamx=18;
		final int tamy=14;
		BufferedImage imagencomparada;
		String[][] SpecialCasillas= new String[tamx][tamy];

		for(int y=0; tamy>y;y++)
		{
			for(int x=0; tamx>x;x++)
			{
				imagencomparada = new BufferedImage(MapaPosicion[x][y].getWidth(), MapaPosicion[x][y].getHeight(), BufferedImage.TYPE_INT_ARGB);
				MapaPosicion[x][y].paint(imagencomparada.getGraphics());
				
				int w1 = imagencomparada.getWidth(null);
				int h1 = imagencomparada.getHeight(null);
				int[] rgbs1 = new int[w1*h1];
				imagencomparada.getRGB(0, 0, w1, h1, rgbs1, 0, w1);		
			}
		}
		return SpecialCasillas;
	}
}
