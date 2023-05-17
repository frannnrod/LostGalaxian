package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Asteroid {
	// Variables de instancia
		 double x, y;
		private double velocidad;
		 double angulo; 
		private int[] signo; 
		Image img1;
		public boolean colision;
		public Asteroid() 
		{
			Random gen = new Random();
			
			this.x = gen.nextDouble(800) + 1;
			this.y = gen.nextInt(20) + 1;
			img1 = Herramientas.cargarImagen("asteroid.png");	
			this.velocidad = 2;	
			this.signo = new int[2];
			this.signo[0] = -1;
			this.signo[1] = 1;
			this.angulo = Math.PI/2 + Math.PI/4 * signo[gen.nextInt(2)];
			this.colision=false;
			
		}

		public void dibujarse(Entorno entorno) 
		{
			
			if (this.y < 600)
			entorno.dibujarImagen(img1,this.x, this.y, this.angulo,0.7);
			else 
				entorno.dibujarImagen(img1,this.x, this.y, this.angulo,0.7);
		
		}

		public void avanzar(AstroMegaShip astromegaship) 
		{
			this.y+= this.velocidad*0.3 * Math.sin(this.angulo);
			this.x+= this.velocidad*0.7 * Math.cos(this.angulo);	
			if (this.x < 20 )
			   {
				this.x = 20; 
			    this.angulo = Math.PI - this.angulo;
			   }
			if (this.x > 780 )
			   {
				this.x = 780;
				this.angulo = Math.PI - this.angulo;
			   }
			if (this.y > 630) {
				this.y = 0;
			}
			if ((this.y > 424 && this.y <555) && (this.x - astromegaship.x) > -30 && (this.x - astromegaship.x) < 30) {
				this.colision=true;
				
			};
			

		
		}

		public double getY() 
		{
			return this.y;
		}

		public double getX() 
		{
			return this.x;
		}
}
