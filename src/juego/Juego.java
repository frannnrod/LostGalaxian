package juego;


import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Asteroid[] asteroid;
	

	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		this.asteroid = new Asteroid[10]; 
		for(int i = 0; i<this.asteroid.length; i++) {
			this.asteroid[i] = new Asteroid();
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
		for (int i=0;i<asteroid.length;i++)
		{
			if (!asteroid[i].getAtrapada())
			{
				asteroid[i].dibujarse(entorno);
			}		
		}
		if (!entorno.estaPresionada('P'))
		{
			for (int i=0;i<asteroid.length;i++)
			{
				if (!asteroid[i].getAtrapada() && asteroid[i].getY() < 600)
				{
					asteroid[i].avanzar();
				}
			}
		}

		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
