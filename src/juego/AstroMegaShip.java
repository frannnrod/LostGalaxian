package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class AstroMegaShip {
	// Variables de instancia
		double x;
		double y;
		double angulo;
		Image img1;
		Image img2;
		boolean motor;
		
		public AstroMegaShip(int x, int y) 
		{
			this.x = x;
			this.y = y;
		    motor=false; 
			img1 = Herramientas.cargarImagen("nave.png");
			img2 = Herramientas.cargarImagen("nave2.png");
		}
		
		public void dibujarse(Entorno entorno)
		{
			//entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
		
			if (motor)
				entorno.dibujarImagen(img2, this.x, this.y, 0, 0.1);
			else
				entorno.dibujarImagen(img1, this.x, this.y, 0, 0.1);
		}


		
		public void moverDerecha() {
			this.x += Math.cos(this.angulo)*5;
			if(this.x > 810) {
				this.x=800;
			}
			if(this.x < -10) {
				this.x=0;
			}
		
		}
		public void moverIzquierda() {
			this.x -= Math.cos(this.angulo)*5;
			if(this.x > 810) {
				this.x=800;
			}
			if(this.x < -10) {
				this.x=0;
			}
		
		}

		public void prenderMotor() {
			// TODO Auto-generated method stub
			this.motor=true;
			}

		public void apagarMotor() {
			// TODO Auto-generated method stub
			this.motor=false;
			
		}

}
