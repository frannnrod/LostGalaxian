 package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Asteroid[] asteroid;
	Destructor[] destructor;
	private AstroMegaShip astromegaship;
	bulletDestructor [] bulletsdestructor;
	bulletAstroMegaShip bulletsastromegaship;
	Image imgFondo,loopFondo, vida, vidaMuerto, explosion, fin, fondoMenu;

	boolean juego = true;
	boolean bala, balaenemigo, colisionDetectada, dioDisparo, cargarMenu = false;

	public final char TECLA_ESC = 27;
	int navesDestruidas = 0;
	int navesDestruidas2 = 0;
	int cantAst = 0;
	int cantDest = 0;
	boolean [] vidas = {true,true,true};
	
	int vidasTotal = 4;
	double fondoy = 300;
	double fondoy2= 900;
	double explosionx;
	double explosiony;
	int temp = 0;
	int rondas = 0 ;
	int contMenu = 0;
	int random;
	double ultimodx;
	double ultimody;
	Random gen = new Random();
	
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
		fondoMenu = Herramientas.cargarImagen("fondoMenu.gif");
		
		if (juego==true) 
		{
			astromegaship = new AstroMegaShip(400, 500);
			bulletsastromegaship = null;
			this.asteroid = new Asteroid[6]; 
			this.destructor = new Destructor[5];
			this.bulletsdestructor = new bulletDestructor[5];
			
			for (int i = 0; i < this.destructor.length; i++) {

				this.destructor[i] = null;
			}

			for (int i = 0; i < this.asteroid.length; i++) {

				this.asteroid[i] = null;
			}

			for (int i = 0; i < this.bulletsdestructor.length; i++) {

				this.bulletsdestructor[i] = null;

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
		
	
//		if (!cargarMenu) {
//			iniciarMenu();
//		}
	//}
	/*public void iniciarMenu() {
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			cargarMenu = true;
			return;
		}
		else {
			Menu.principal(entorno,fondoMenu,contMenu);
			if (entorno.sePresiono(entorno.TECLA_DERECHA )&& contMenu<2) {
				contMenu++;
				}
			}
			if (entorno.sePresiono(entorno.TECLA_IZQUIERDA ) && contMenu>0) {
				contMenu--;
			}
		
	}*/
		
		
//	public void inicioJuego() {	
		temp++;
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
			 navesDestruidas = 0;
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
			// FONDO LOOP//
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
			//CIERRE LOOP//
			
			//ASTROMEGASHIP//
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
			astromegaship.dibujarse(entorno);
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)&& bala==false) {
				bala=true;
				bulletsastromegaship = new bulletAstroMegaShip(astromegaship.x,450);
			}
			
			if (bala) {
				if(bulletsastromegaship.by>0) {
					bulletsastromegaship.avanzarDisparo();
					bulletsastromegaship.dibujar(entorno);
				}
				else {
					bala=false;	
					bulletsastromegaship=null;
				}
			}
				
			entorno.cambiarFont("SPACEMAN", 20, Color.white);
			entorno.escribirTexto("SCORE:" + navesDestruidas  ,astromegaship.x-60, 570);
			//CIERRE ASTROMEGASHIP//
			
			//ASTEROID//
			if (temp % 60 == 0) {
				if (cantAst<4 && gen.nextInt(2) == 1) {
					for (int i=0;i<asteroid.length;i++) {
						if (this.asteroid[i] == null) {
							this.asteroid[i] = new Asteroid();	
							cantAst+=1;
							break;
					
							}
						}
					}
				else if ( cantDest<5 && gen.nextInt(2) == 0 ) {
					for (int i=0;i<destructor.length;i++){
						 
						if (this.destructor[i] == null) {
							this.destructor[i] = new Destructor();
							ultimodx = destructor[i].getX();
							ultimody = destructor[i].getY();
							cantDest+=1;
							if (this.destructor[i].x != ultimodx && this.destructor[i].x<400) {
								this.destructor[i].x -=100;
							}
							if (this.destructor[i].x != ultimodx && this.destructor[i].x>400) {
								this.destructor[i].x +=100;
							}
							if (this.destructor[i].y != ultimody) {
								this.destructor[i].y +=20;
							}
							break;
							}
						
						
						}
					}
			}
			
			for (int i=0;i<destructor.length;i++) {
				for (int k=0;k<destructor.length;k++) {
					if(destructor[i]!=null && destructor[k] !=null) {
						if ((destructor[i].x - destructor[k].x < 80 && destructor[i].x - destructor[k].x >-80) 
										&& (destructor[i].y - destructor[k].y < 80 && destructor[i].y - destructor[k].y >-80) ) {
							destructor[i].angulo = Math.PI - destructor[i].angulo;
							destructor[i].x+=0.3;
						
							 destructor[k].angulo = Math.PI -  destructor[k].angulo;
							if ( destructor[k].angulo<0){
								 destructor[k].x+= 0.3;
								}
							if ( destructor[k].angulo>0){
								 destructor[k].x-= 0.3; }
						}
					}
				}
			}
			for (int i=0;i<asteroid.length;i++) 
			{		
				if (asteroid[i]!=null)
				{
					asteroid[i].dibujarse(entorno);
					asteroid[i].avanzar(astromegaship);
					if (!colisionDetectada && asteroid[i].colision) {
						System.out.println("COLISION");
						vidasTotal--;
						vidas[vidasTotal - 1] = false;
						asteroid[i] = null;
						
					}    
				}		
			}
			
			//CIERRE ASTEROID
			
			//DESTRUCTOR
//			if (cantDest<5) {
//				for (int i=0;i<destructor.length;i++){
//					 random= gen.nextInt(100);
//					if (this.destructor[i] == null && random==1 ) {
//						this.destructor[i] = new Destructor();	
//						cantDest+=1;
//						}	
//					}
//			}
					for (int i=0;i<destructor.length;i++) {
						if (destructor[i]!=null) {
							destructor[i].avanzar(astromegaship);
							destructor[i].dibujarse(entorno);
							if ((astromegaship.y - destructor[i].y < 33 && astromegaship.y - destructor[i].y > -33) && 
								(astromegaship.x - destructor[i].x < 100 && astromegaship.x - destructor[i].x > -100 )) 
								{
								vidasTotal--;
								}
							if (bala) {
										if ((bulletsastromegaship.by - destructor[i].y < 33 && bulletsastromegaship.by - destructor[i].y > -33) &&
											(bulletsastromegaship.bx - destructor[i].x < 100 && bulletsastromegaship.bx - destructor[i].x > -100 )) {
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
				for (int i = 0; i < bulletsdestructor.length; i++) {
					if (this.destructor[i] != null && bulletsdestructor[i] == null) {
						if (astromegaship.x-destructor[i].x<5) {
							bulletsdestructor[i] = new bulletDestructor(destructor[i].x, destructor[i].y);
						}

					}
					
					if (bulletsdestructor[i] != null) {
	
						bulletsdestructor[i].avanzar();	
						bulletsdestructor[i].dibujar(entorno);
					}
					if (bulletsdestructor[i] != null) {
						if ((bulletsdestructor[i].by- astromegaship.y < 20 && bulletsdestructor[i].by - astromegaship.y > -20) &&
								bulletsdestructor[i].bx - astromegaship.x < 50 && bulletsdestructor[i].bx - astromegaship.x > -50) {
							vidasTotal--;
							bulletsdestructor[i]=null;
							vidas[vidasTotal - 1] = false;
							System.out.println("EL DESTRUCTOR LE DIO A MI NAVE");
						}
					}
					if (bulletsdestructor[i] != null) {
						
						if (bulletsdestructor[i].by > 600) {
							bulletsdestructor[i] = null;
						}
					}

						}


				
				//CIERRE DESTRUCTOR//
					for (int i=0;i<destructor.length;i++) {	
						for (int j=0;j<asteroid.length;j++) { //LOGICA CUANDO HAY UNA COLISION ENTRE ASTEROIDE Y DESTRUCTOR
							if (asteroid[j] !=null && destructor[i]!=null) {
								if ((destructor[i].x - asteroid[j].x < 80 && destructor[i].x - asteroid[j].x >-80) 
										&& (destructor[i].y - asteroid[j].y < 80 && destructor[i].y - asteroid[j].y >-80) 
										) 
									{
										destructor[i].angulo = Math.PI +destructor[i].angulo;
										destructor[i].x+=0.3;
										destructor[i].y+=2.3;
										asteroid[j].angulo = Math.PI + asteroid[j].angulo;
										asteroid[j].x+=0.3;
										if (asteroid[j].angulo<0){
											asteroid[j].x+= 0.3;
											}
										if (asteroid[j].angulo>0){
											asteroid[j].x-= 0.3;
											}
								}
							}
							
						}
				}
					
//								if (navesDestruidas2 == 4) { //UNA VEZ QUE MUEREN LOS DESTRUCTORES APARECEN MAS (¿SISTEMAS DE RONDAS?)
//									
//										navesDestruidas2=0;
//										this.destructor = new Destructor[4];
//										
//										for(int i1 = 0; i1<this.destructor.length; i1++) {
//											this.destructor[i1] = new Destructor();
//											}
//									}

		
		}
	//CIERRE JUEGO
	if (entorno.sePresiono(TECLA_ESC)) {
			System.exit(0);
		}
		
	
		
	
}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
