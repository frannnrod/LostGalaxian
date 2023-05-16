 package juego;


import java.awt.Color;
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
	Image loopFondo;
	Image vida;
	Image vidaMuerto;
	Image explosion;
	Image fin;
	boolean juego = true;
	boolean bala=false;
	boolean balaenemigo=false;
	public final char TECLA_ESC = 27;
	int navesDestruidas = 0;
	boolean [] vidas = {true,true,true};
	
	int vidasTotal = 4;
	boolean colisionDetectada = false;
	double fondoy = 300;
	double fondoy2= 900;
	double explosionx;
	double explosiony;
	boolean dioDisparo = false;
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		vida = Herramientas.cargarImagen("heart.png");
		vidaMuerto = Herramientas.cargarImagen("heart0.png");
		imgFondo = Herramientas.cargarImagen("fondo.png");
		loopFondo = Herramientas.cargarImagen("fondoloop.png");
		explosion = Herramientas.cargarImagen("explo.png");
		fin = Herramientas.cargarImagen("fin.gif");
		
		if (juego==true) 
		{
			astromegaship = new AstroMegaShip(400, 500);
			this.asteroid = new Asteroid[4]; 
			this.destructor = new Destructor[4];
			for(int i = 0; i<this.asteroid.length; i++) {
				this.asteroid[i] = new Asteroid();
			}
			for(int i = 0; i<this.destructor.length; i++) {
				this.destructor[i] = new Destructor();
			}
			
			// Inicializar lo que haga falta para el juego
			// ...
	
			// Inicia el juego!
		}
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		
		entorno.dibujarImagen(loopFondo, 400, fondoy2, 0);
		
		entorno.dibujarImagen(imgFondo, 400, fondoy, 0);


		for (int i = 0; i<vidas.length;i++) { //DIBUJAR LOS CORAZONES QUE REPRESENTAN LAS VIDAS
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
			//PANTALLA DE FIN UNA VEZ QUE PERDIMOS
			entorno.dibujarImagen(fin, 400, 300, 0);
			entorno.cambiarFont("8-bit Arcade Out", 90, Color.magenta);
			entorno.escribirTexto("PERDISTE",240,200);
			entorno.cambiarFont("8-bit Arcade In", 90, Color.white);
			entorno.escribirTexto("PERDISTE",240,200);
			entorno.cambiarFont("8-bit Arcade Out", 50, Color.magenta);
			entorno.escribirTexto("VOLVER A JUGAR",240,400);
			entorno.cambiarFont("8-bit Arcade In", 50, Color.white);
			entorno.escribirTexto("VOLVER A JUGAR",240,400);
			entorno.cambiarFont("8-bit Arcade Out", 40, Color.magenta);
			entorno.escribirTexto("APRIETE R",325,425);
			entorno.cambiarFont("8-bit Arcade In", 40, Color.white);
			entorno.escribirTexto("APRIETE R",325,425);
			entorno.cambiarFont("8-bit Arcade Out", 55, Color.magenta);
			entorno.escribirTexto("ESC PARA SALIR",230,570);
			entorno.cambiarFont("8-bit Arcade In", 55, Color.white);
			entorno.escribirTexto("ESC PARA SALIR",230,570);
			juego=false;
		}
		
		if (juego==false && entorno.estaPresionada('R')) { 
			//SI EL USUARIO DESEA VOLVER A JUGAR, VOLVEMOS A DIBUJAR LAS IMAGENES INICIALES Y REESTABLECEMOS LAS VARIABLES PRINCIPALES COMO SERIAN LAS VIDAS
			 vidas[0] = true;
			 vidas[1] = true;
			 vidas[2] = true;
			 vidasTotal = 4;
			juego=true;
			entorno.dibujarImagen(loopFondo, 400, fondoy2, 0);
			
			entorno.dibujarImagen(imgFondo, 400, fondoy, 0);
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
			astromegaship = new AstroMegaShip(400, 500);
			this.asteroid = new Asteroid[4]; 
			this.destructor = new Destructor[6];
			for(int i = 0; i<this.asteroid.length; i++) {
				this.asteroid[i] = new Asteroid();
			}
			for(int i = 0; i<this.destructor.length; i++) {
				this.destructor[i] = new Destructor();
			}
			
		}
		if (juego==false) { //EN CASO DE QUE PERDAMOS, DESAPARECEMOS LOS OBJETOS.
			astromegaship=null;
			asteroid=null;
			destructor=null;
		}

		if (!entorno.estaPresionada('P') && juego==true) 
		{
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
			fondoy2-=1;
			fondoy-=1;
			if (fondoy2==-300) {
				fondoy2=900;
			}
			if (fondoy2==300) {
				fondoy2=300;
			}
			if (fondoy==300) {
				fondoy=300;
			}
			if (fondoy==-300) {
				fondoy=900;
			}
			
			entorno.cambiarFont("SPACEMAN", 20, Color.white);
			entorno.escribirTexto("SCORE:" + navesDestruidas  ,astromegaship.x-60, 570);

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
						
						
						for (int j=0;j<asteroid.length;j++) { //LOGICA CUANDO HAY UNA COLISION ENTRE ASTEROIDE Y DESTRUCTOR
							if (asteroid[j] !=null) {
								if ((destructor[i].x - asteroid[j].x < 80 && destructor[i].x - asteroid[j].x >-80) 
										&& (destructor[i].y - asteroid[j].y < 80 && destructor[i].y - asteroid[j].y >-80) 
										) {
									
									destructor[i].angulo = Math.PI - destructor[i].angulo;
									
									asteroid[j].angulo = Math.PI - asteroid[j].angulo;
									asteroid[j].x+= 1*0.7;
									asteroid[j].y+= 1*0.7;
								}
							}
							
						}
						
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
								explosionx=destructor[i].x;
								explosiony=destructor[i].y;
								bala= false;
								destructor[i] = null;
								entorno.dibujarImagen(explosion,explosionx , explosiony, 0,0.2 );
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
