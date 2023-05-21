	package juego;

	import java.awt.Image;
	import java.util.Random;

	import entorno.Entorno;
	import entorno.Herramientas;

public class Boss {
	double x;
	double y;
	double by;
	double bx;
	private double velocidad;
	double angulo; 
	Image img1;
	Image bulletd;
	boolean colision;

	public Boss(double x, double y) 
		{
			this.x = x;
			this.y = y;
			img1 = Herramientas.cargarImagen("finalBoss.png");
			this.velocidad = 1.5;
			
		}

		public void dibujarse(Entorno entorno) 
		{
			if (entorno== null) return;
			if (this.y < 600)
			entorno.dibujarImagen(img1,this.x, this.y, 0, 0.5);
			else 
				entorno.dibujarImagen(img1,this.x, this.y, 0, 0.5);
		
		}
		
		public double disparoDestructor() {
			return this.by;
		}
		public double disparoDestructorX() {
			return this.bx;
		}
		public void easteregg (int cont) {
			if(cont==7) {
				img1 = Herramientas.cargarImagen("boss.png");
			}
		}

		public void avanzar(AstroMegaShip astromegaship) 
		{
			this.x+= this.velocidad * Math.cos(this.angulo);
			bx = this.x;
			if (this.x < 20 )
			   {
				this.x = 20;
			
			    this.angulo = Math.PI + this.angulo;

			   }
			if (this.x > 780 )
			   {
				this.x = 780;
				this.angulo = Math.PI + this.angulo;
				
			   }
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

