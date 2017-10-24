package LP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImagenRecorte extends javax.swing.JLabel {
    
	private static final long serialVersionUID = 1L;
	
	int x, y;

    public ImagenRecorte(JLabel jLabel1) {
        this.x = jLabel1.getWidth();
        this.y = jLabel1.getHeight();
        this.setSize(x, y);
    }

    @Override
    public void paintComponent(Graphics g) {
        BufferedImage imagen, recorte;
        try {
            File f = new File(".\\resources\\prueba1.png");
            imagen = ImageIO.read(f);   
            for(int x=0;x<112;x++)
            {
            	for(int y=0;y<96;y++)
            	{
            		 recorte = ((BufferedImage) imagen).getSubimage(x, y, 16, 16);
                     g.drawImage(recorte, x*2, y*2, this); 
                     y=y+15;
            	}
                x=x+15;
            }           
        } catch (IOException ex) { }
    }

}
