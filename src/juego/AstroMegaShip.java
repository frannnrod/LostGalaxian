package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class AstroMegaShip {
	// Variables de instancia
		double x;
		double y;
		double bx;
		double by;
		double angulo;
		Image img1;
		Image img2;
		Image bullet;
		boolean motor;
		boolean disparar;
		
		public AstroMegaShip(int x, int y) 
		{
			this.x = x;
			this.y = y;
		    motor=false; 
			img1 = Herramientas.cargarImagen("nave.png");
			img2 = Herramientas.cargarImagen("nave2.png");
			bullet = Herramientas.cargarImagen("bullet.png");
			bx=x;
			by=y;
			disparar=false;
		}
		
		public void dibujarse(Entorno entorno)
		{
			//entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
		
			if (motor)
				entorno.dibujarImagen(img2, this.x, this.y, 0, 0.1);
			else
				entorno.dibujarImagen(img1, this.x, this.y, 0, 0.1);
		}
		public void disparo(Entorno entorno) {
			entorno.dibujarImagen(bullet, this.x, this.by, 0,0.2);
			this.disparar=true;
			System.out.println("disparo");
		}
		public void avanzarDisparo() {
				this.by-=17;				
		}
		public void frenardisparo() {
			if (this.by<0){
			disparar=false;
			}
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
