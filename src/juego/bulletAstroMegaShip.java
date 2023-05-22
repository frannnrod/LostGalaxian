package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class bulletAstroMegaShip {
	double bx;
	double by;
	double angulo;
	Image bullet;

	public bulletAstroMegaShip(double x, double y) {

		this.bx = x;
		this.by = y;
		this.bullet = Herramientas.cargarImagen("bullet.png");
		this.angulo = 0;

	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(bullet, bx, by, angulo, 0.2);

	}

	public void avanzarDisparo() {
		this.by -= 12;

	}

}
