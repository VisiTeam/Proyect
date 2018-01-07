package LP;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class PiezaMapa extends JLabel
{
	private static final long serialVersionUID = 1L;
	private final int SIZE_PIEZA=50;
	private boolean movilidad;

	public PiezaMapa(ImageIcon c) 
	{
		setSize(SIZE_PIEZA, SIZE_PIEZA);
		setIcon(c);
		movilidad=true;
	}

	public boolean isMovilidad() {
		return movilidad;
	}

	public void setMovilidad(boolean b) {
		this.movilidad=b;
		
	}
	
	protected void paintComponent(Graphics g) {
		Image img = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g; 
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
        g2.drawImage( img, 0, 0, SIZE_PIEZA, SIZE_PIEZA, null );
	}
}
