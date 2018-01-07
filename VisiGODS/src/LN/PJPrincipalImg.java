package LN;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PJPrincipalImg extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	private final int SIZE_PJ=100;
	private final int SIZE_PJ_JUEGO=50;
	
	public PJPrincipalImg(String src) 
	{
		setSize(SIZE_PJ, SIZE_PJ );
		try {
			setIcon( new ImageIcon(src));
		} catch (Exception e) {
			System.err.println( "No existe la imagen" );
		}
		setSize(SIZE_PJ, SIZE_PJ );
	}

	@Override
	protected void paintComponent(Graphics g) {
		Image img = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g; 
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
        g2.drawImage( img, 0, 0, SIZE_PJ_JUEGO, SIZE_PJ_JUEGO, null );
	}
}
