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
	private double diametro;
	private double velocidad;
	private double angulo; 
	private boolean atrapada;
	private int[] signo; 
	Image img1;
	Image bulletd;
	boolean colision;

	public Destructor() 
	{
		//this.x = x;
		//this.y = y;
		this.diametro = 20;
		Random gen = new Random();
		
		this.x = gen.nextInt(800) + 1;
		this.y = gen.nextInt(100) + 1;
		img1 = Herramientas.cargarImagen("destructor.png");
		bulletd = Herramientas.cargarImagen("bulletd.png");
		by=y;
		bx =x;
		this.velocidad = gen.nextInt(3) + 1;
		
		this.signo = new int[2];
		this.signo[0] = -1;
		this.signo[1] = 1;

		this.angulo = Math.PI/2 + Math.PI/4 * signo[gen.nextInt(2)];
//		System.out.println(this.x+" "+this.y+" "+this.velocidad+" "+  this.angulo);
//		System.exit(0);
		this.atrapada=false;
		this.colision=false;
		
	}

	public void dibujarse(Entorno entorno) 
	{
		if (entorno== null) return;
		if (this.y < 600)
		entorno.dibujarImagen(img1,this.x, this.y, this.angulo,0.2);
		else 
			entorno.dibujarImagen(img1,this.x, this.y, this.angulo,0.2);
	
	}
	public void disparar(Entorno entorno) {
		entorno.dibujarImagen(bulletd, this.bx, this.by, 0,0.2);
		this.by+=5;
		if(this.by>600) {
			this.by=this.y;
		}
	}
	public double disparoDestructor() {
		return this.by;
	}
	public double disparoDestructorX() {
		return this.bx;
	}

	public void avanzar(AstroMegaShip astromegaship) 
	{
		this.y+= this.velocidad*0.3 * Math.sin(this.angulo);
		this.x+= this.velocidad*0.7 * Math.cos(this.angulo);
		bx = this.x;
//		this.y+= this.velocidad * Math.sin(this.angulo);
//		this.x+= this.velocidad * Math.cos(this.angulo);
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
			if ((this.y > 424 && this.y <555) && (this.x - astromegaship.x) > -30 && (this.x - astromegaship.x) < 30);{
				this.colision=true;
				System.out.println("colision");
			}
		
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

	public boolean getAtrapada() 
	{
		return this.atrapada;
	}

	public void setAtrapada(boolean atrapada) {
		this.atrapada = atrapada;
	}

	

}
