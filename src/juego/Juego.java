package juego;


import java.awt.Image;

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
	

	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		imgFondo = Herramientas.cargarImagen("fondo.jpg");
		astromegaship = new AstroMegaShip(400, 500);
		this.asteroid = new Asteroid[6]; 
		this.destructor = new Destructor[7];
		this.asteroid = new Asteroid[2]; 
		for(int i = 0; i<this.asteroid.length; i++) {
			this.asteroid[i] = new Asteroid();
		}
		for(int i = 0; i<this.asteroid.length; i++) {
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
		entorno.dibujarImagen(imgFondo, 400, 305, 0,1.5);
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
			if (!asteroid[i].getAtrapada())
			{
				asteroid[i].dibujarse(entorno);
			}		
		}
		for (int i=0;i<destructor.length;i++)
		{
			if (!destructor[i].getAtrapada())
			{
				destructor[i].dibujarse(entorno);
			}		
		}
		if (!entorno.estaPresionada('P'))
		{
			for (int i=0;i<asteroid.length;i++)
			{
				{
					asteroid[i].avanzar(astromegaship);
				}
			}
			for (int i=0;i<destructor.length;i++)
			{
				{
					destructor[i].avanzar(astromegaship);
				}
			}
		}
		astromegaship.dibujarse(entorno);

		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
