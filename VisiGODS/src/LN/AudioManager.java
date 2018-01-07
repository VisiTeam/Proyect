package LN;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {
	
	private AudioInputStream audioInputStream;
	private Clip clip;

	public AudioManager() 
	{
		audioInputStream=null;
		clip=null;
	}
	
	public void setAudio(String nombre)
	{
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(nombre));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
		{
			e.printStackTrace();
		}
	}
	public void IniciarAudio()
	{
		clip.start();
	}
	public void IniciarAudioInf()
	{
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void PararAudio()
	{
		clip.stop();
	}
	public void PonerAudioXVeces(int loops)
	{
		clip.start();
		clip.loop(loops);
	}
}
