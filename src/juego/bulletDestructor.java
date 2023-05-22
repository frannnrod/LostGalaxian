package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class bulletDestructor {
	double bx;
	double by;
	double angulo;
	Image bulletd;

	public bulletDestructor(double x, double y) {
		this.bulletd = Herramientas.cargarImagen("bulletd.png");
		this.bx = x;
		this.by = y;
		this.angulo = 0;
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(bulletd, bx, by, angulo, 0.2);
	}

	public void avanzar() {
		this.by += 5;
	}

	public double getX() {
		return this.bx;
	}

	public double getY() {
		return this.by;
	}
}