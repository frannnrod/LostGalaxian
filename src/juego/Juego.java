 package juego;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Asteroid[] asteroid;
	Destructor[] destructor;
	private AstroMegaShip astromegaship;
	Image imgFondo;
	Image vida;
	Image vidaMuerto;
	boolean bala=false;
	boolean balaenemigo=false;
	public final char TECLA_ESC = 27;
	int navesDestruidas = 0;
	boolean [] vidas = {true,true,true};
	boolean dioDisparo = false;
	int vidasTotal = 4;
	boolean colisionDetectada = false;

	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		vida = Herramientas.cargarImagen("heart.png");
		vidaMuerto = Herramientas.cargarImagen("heart0.png");
		imgFondo = Herramientas.cargarImagen("fondo.jpg");


		astromegaship = new AstroMegaShip(400, 500);
		this.asteroid = new Asteroid[6]; 
		this.destructor = new Destructor[6];
		for(int i = 0; i<this.asteroid.length; i++) {
			this.asteroid[i] = new Asteroid();
		}
		for(int i = 0; i<this.destructor.length; i++) {
			this.destructor[i] = new Destructor();
		}
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		
		
		entorno.dibujarImagen(imgFondo, 400, 300, 0);
		for (int i = 0; i<vidas.length;i++) {
			if (vidas[i]) {
				entorno.dibujarImagen(vida, 25 + (i * 50), 25, 0,0.15);
				if (dioDisparo) {
					System.out.println("estoy aca");
				 entorno.dibujarImagen(vidaMuerto, 25 + (i * 50), 25, 0,0.15);
				}
			}
			else {
				 entorno.dibujarImagen(vidaMuerto, 25 + (i * 50), 25, 0, 0.15);

			}
			
		}
		 
		if (vidas[0]== false) {
			System.out.println("PERDISTE PETE");
			System.exit(0);
		}
		entorno.cambiarFont("SPACEMAN", 20, Color.black);
		entorno.escribirTexto("SCORE:" + navesDestruidas  ,astromegaship.x-60, 570);
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			astromegaship.moverDerecha();
			astromegaship.prenderMotor();
		}
		else {
			astromegaship.apagarMotor();
			
	        }
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			astromegaship.moverIzquierda();
			astromegaship.prenderMotor();
		}
		else {
			astromegaship.apagarMotor();
			
	        }	
			
		for (int i=0;i<asteroid.length;i++)
		{
			if (asteroid[i]!=null && !asteroid[i].getAtrapada())
			{
				asteroid[i].dibujarse(entorno);
			}		
		}
		for (int i=0;i<destructor.length;i++)
		{
			
			if (destructor[i]!=null && !destructor[i].getAtrapada()  )
				
			{
			
				destructor[i].dibujarse(entorno);
			
				
			}		
		}
		if (!entorno.estaPresionada('P'))
		{
			for (int i=0;i<asteroid.length;i++)
			{
				{
					if (asteroid[i]!=null) {
						asteroid[i].avanzar(astromegaship);
						if (!colisionDetectada && asteroid[i].colision) {
							System.out.println("COLISION");
							 vidasTotal--;
							 vidas[vidasTotal - 1] = false;
							 asteroid[i] = null;
							
						}    
  					}
					
					
				}
				
				
			}
			
			
			dioDisparo = false;
			for (int i=0;i<destructor.length;i++)
			{
				{
					if (destructor[i]!=null) {
						destructor[i].avanzar(astromegaship);
						destructor[i].disparar(entorno);
						
						if ((destructor[i].disparoDestructor() - astromegaship.y < 20 && destructor[i].disparoDestructor() - astromegaship.y > -20) &&
								destructor[i].disparoDestructorX() - astromegaship.x < 50 && destructor[i].disparoDestructorX() - astromegaship.x > -50) {
							vidasTotal--;
							 vidas[vidasTotal - 1] = false;
							System.out.println("EL DESTRUCTOR LE DIO A MI NAVE");
							destructor[i].by = destructor[i].y;
							destructor[i].bx = destructor[i].x;
							//dioDisparo = true;
						}
						if (destructor[i].colision) {
						}
						//dioDisparo= false;
					}
					
				}
			}
		}

		
		astromegaship.dibujarse(entorno);
		if (entorno.sePresiono(entorno.TECLA_ESPACIO)&& bala==false) {
			bala=true;
			astromegaship.disparo(entorno);
		}
		if (bala) {
			if(astromegaship.by>0) {
				astromegaship.avanzarDisparo();
				astromegaship.disparo(entorno);
				
				for (int i= 0; i < destructor.length; i++) {
					if (destructor[i]!=null) {
						
						if ((astromegaship.posicionBalaY() - destructor[i].y < 33 && astromegaship.posicionBalaY() - destructor[i].y > -33) && 
								(astromegaship.posicionBalaX() - destructor[i].x < 100 && astromegaship.posicionBalaX() - destructor[i].x > -100 )) {
									
									navesDestruidas++;
									bala= false;
									destructor[i] = null;
									astromegaship.by=450;
									
								}
							}
					}
					
			}
			else {
				
				bala=false;
				astromegaship.by=450;
			}
		}
		if (entorno.sePresiono(TECLA_ESC)) {
			System.exit(0);
		}


		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
