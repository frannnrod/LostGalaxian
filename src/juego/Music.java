package juego;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
public class Music {
	public static void reproducirAudio(String rutaArchivo) {
	    try {
	        // Cargar el archivo de audio
	        File audioIntro = new File(rutaArchivo);
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioIntro);

	        // Crear el clip de audio
	        Clip clip = AudioSystem.getClip();

	        // Abrir el archivo de audio en el clip
	        clip.open(audioInputStream);

	        // Reproducir el audio
	        clip.start();

	        // Esperar hasta que se termine de reproducir
	        while (!clip.isRunning())
	            Thread.sleep(10);
	        while (clip.isRunning())
	            Thread.sleep(10);

	        // Cerrar el clip y el stream de audio
	        clip.close();
	        audioInputStream.close();
	    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}
