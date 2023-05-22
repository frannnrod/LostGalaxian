package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Menu {
	int cantOleadas;

	public static boolean principal(Entorno e, Image fondo, int cont, int cont2) {
		e.dibujarImagen(fondo, 400, 300, 0);
		e.cambiarFont("8-bit Arcade Out", 90, Color.black);
		e.escribirTexto("Lost Galaxian", 130, 200);
		e.cambiarFont("8-bit Arcade In", 90, Color.magenta);
		e.escribirTexto("Lost Galaxian", 130, 200);

		e.cambiarFont("8-bit Arcade Out", 65, Color.black);
		e.escribirTexto("Seleccione la dificultad", 60, 300);
		e.cambiarFont("8-bit Arcade In", 65, Color.magenta);
		e.escribirTexto("Seleccione la dificultad", 60, 300);

		e.cambiarFont("8-bit Arcade Out", 50, Color.black);
		e.escribirTexto("Enter para comenzar", 180, 460);
		e.cambiarFont("8-bit Arcade In", 50, Color.magenta);
		e.escribirTexto("Enter para comenzar", 180, 460);

		if (cont == 0) {

			// FACIL
			e.cambiarFont("8-bit Arcade Out", 60, Color.white);
			e.escribirTexto("FACIL", 120, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("FACIL", 120, 400);
			// MEDIO
			e.cambiarFont("8-bit Arcade Out", 60, Color.black);
			e.escribirTexto("MEDIO", 320, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("MEDIO", 320, 400);
			// DIFICIL
			e.cambiarFont("8-bit Arcade Out", 60, Color.black);
			e.escribirTexto("DIFICIL", 520, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("DIFICIL", 520, 400);
		}

		if (cont == 1) {
			// FACIL
			e.cambiarFont("8-bit Arcade Out", 60, Color.black);
			e.escribirTexto("FACIL", 120, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("FACIL", 120, 400);
			// MEDIO
			e.cambiarFont("8-bit Arcade Out", 60, Color.white);
			e.escribirTexto("MEDIO", 320, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("MEDIO", 320, 400);
			// DIFICIL
			e.cambiarFont("8-bit Arcade Out", 60, Color.black);
			e.escribirTexto("DIFICIL", 520, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("DIFICIL", 520, 400);
		}

		if (cont == 2) {
			// FACIL
			e.cambiarFont("8-bit Arcade Out", 60, Color.black);
			e.escribirTexto("FACIL", 120, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("FACIL", 120, 400);
			// MEDIO
			e.cambiarFont("8-bit Arcade Out", 60, Color.black);
			e.escribirTexto("MEDIO", 320, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("MEDIO", 320, 400);
			// DIFICIL
			e.cambiarFont("8-bit Arcade Out", 60, Color.white);
			e.escribirTexto("DIFICIL", 520, 400);
			e.cambiarFont("8-bit Arcade In", 60, Color.magenta);
			e.escribirTexto("DIFICIL", 520, 400);
		}

		if (e.estaPresionada(e.TECLA_ENTER)) {
			return false;
		}
		return true;
	}

	public void crearOleadas(int cont) {
		if (cont == 0) {
			cantOleadas = 3;
		}
		if (cont == 1) {
			cantOleadas = 6;
		}
		if (cont == 2) {
			cantOleadas = 10;
		}
	}

}