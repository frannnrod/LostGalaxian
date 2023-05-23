package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Destructor {
	double x;
	double y;
	double by;
	double bx;
	private double velocidad;
	double angulo;
	Image img1;
	Image bulletd;
	boolean colision;

	public Destructor() {
		// this.x = x;
		// this.y = y;
		Random gen = new Random();
		this.x = gen.nextDouble(800) + 1;
		this.y = gen.nextInt(-10, 20) + 1;
		img1 = Herramientas.cargarImagen("destructor.png");
		this.velocidad = 2;
		this.colision = false;

	}

	public void dibujarse(Entorno entorno) {
		if (entorno == null)
			return;
		if (this.y < 600)
			entorno.dibujarImagen(img1, this.x, this.y, -0.2, 0.16);
		else
			entorno.dibujarImagen(img1, this.x, this.y, -0.2, 0.16);

	}

	public double disparoDestructor() {
		return this.by;
	}

	public double disparoDestructorX() {
		return this.bx;
	}

	public void avanzar(AstroMegaShip astromegaship) {

		this.y += this.velocidad * Math.sin(this.angulo);
		this.x += this.velocidad * Math.cos(this.angulo);
		bx = this.x;
		if (this.x < 20) {
			this.x = 20;

			this.angulo = Math.PI + this.angulo;

		}
		if (this.x > 780) {
			this.x = 780;
			this.angulo = Math.PI + this.angulo;

		}
		if (this.y < 630) {
			this.y += this.velocidad * 0.3;
		}
		if (this.y > 630) {
			this.y = 0;
			if ((this.y > 424 && this.y < 555) && (this.x - astromegaship.x) > -30 && (this.x - astromegaship.x) < 30)
				;
			{
				this.colision = true;
			}

		}
	}
	


	public double getY() {
		return this.y;
	}

	public double getX() {
		return this.x;
	}

}
