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
	bulletDestructor[] bulletsdestructor;
	bulletAstroMegaShip bulletsastromegaship;
	Image imgFondo, loopFondo, vida, vidaMuerto, explosion, fin, fondoMenu, win, intro, hpboss1, hpboss2, hpboss3, hpboss4, hpboss5;
	Music musica,musicaJuego, sonidoDisparo, menuMusica, musicaPerder ,
			musicaGanar, musicaOleadaSuperada;
	Boss boss;
	boolean juego = true;
	boolean bala, balaenemigo, colisionDetectada, dioDisparo, cargarMenu, facil, medio, dificil, ganar, introMostrar,
			musicPerder, musicWin, pausa, jefe = false;
	public final char TECLA_ESC = 27;
	int navesDestruidas, navesDestruidas2, cantAst, cantAstDest, cantDest, disparosaBoss, temp, contMenu, contInvocar,
			navesaDestruir, temp2, temp3m, temp4m, temp5, conteasteregg, levelUp, curar = 0;
	boolean[] vidas = { true, true, true };
	int vidasTotal = 4;
	double fondoy = 300;
	double fondoy2 = 900;
	int cantOleadas = 1;
	double explosionx, explosiony, ultimodx, ultimody;
	int random;
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
		fin = Herramientas.cargarImagen("fin2.gif");
		fondoMenu = Herramientas.cargarImagen("fondoMenu.gif");
		win = Herramientas.cargarImagen("win.gif");
		intro = Herramientas.cargarImagen("intro.gif");
		hpboss1 = Herramientas.cargarImagen("vidaBoss1.png");
		hpboss2 = Herramientas.cargarImagen("vidaBoss2.png");
		hpboss3 = Herramientas.cargarImagen("vidaBoss3.png");
		hpboss4 = Herramientas.cargarImagen("vidaBoss4.png");
		hpboss5 = Herramientas.cargarImagen("vidaBoss5.png");
		musicaJuego = new Music("/musicaJuego.wav");
		sonidoDisparo = new Music("/disparo.wav");
		menuMusica = new Music("/menu.wav");
		musicaPerder = new Music("/lose.wav");
		musicaGanar = new Music("/win.wav");
		musicaOleadaSuperada = new Music("/oleadaSuperada.wav");

		if (juego == true) {

			astromegaship = new AstroMegaShip(400, 500);
			bulletsastromegaship = null;
			this.asteroid = new Asteroid[5];
			this.destructor = new Destructor[6];
			this.bulletsdestructor = new bulletDestructor[6];

			this.boss = null;
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
		// MENU//
		if (!cargarMenu) {
			iniciarMenu();
			 menuMusica.reproducirMusica();
		} else if (cargarMenu) {
			temp2++;
			if (temp2 % 1100 > 0 && introMostrar == true) {
				 menuMusica.pausarMusica();
				entorno.dibujarImagen(intro, 400, 300, 0, 2);
				if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					introMostrar = false;
				}

			} else {
				introMostrar = false;
				inicioJuego();
			}
		}
	}

	public void iniciarMenu() {
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			 menuMusica.pararMusica();
			cargarMenu = true;
			introMostrar = true;
			return;
		} else {
			Menu.principal(entorno, fondoMenu, contMenu, conteasteregg);
			if (entorno.sePresiono(entorno.TECLA_DERECHA) && contMenu < 2) {
				contMenu++;
			}
			if (entorno.sePresiono(entorno.TECLA_DERECHA) && conteasteregg < 7) {
				conteasteregg++;
				System.out.println(conteasteregg);
			}
		}
		if (entorno.sePresiono(entorno.TECLA_IZQUIERDA) && contMenu > 0) {
			contMenu--;
		}
		// CIERRE MENU//

		// DIFICULTAD//
		if (contMenu == 2) {
			dificil = true;
			medio = false;
			facil = false;
			navesaDestruir = 45;

		} else if (contMenu == 1) {
			dificil = false;
			medio = true;
			facil = false;
			navesaDestruir = 30;
		} else if (contMenu == 0) {
			dificil = false;
			medio = false;
			facil = true;
			navesaDestruir = 15;
		}
		// CIERRE DIFICULTAD
	}

	// SI EL USUARIO DESEA VOLVER A JUGAR, VOLVEMOS A DIBUJAR LAS IMAGENES INICIALES
	// Y REESTABLECEMOS LAS VARIABLES PRINCIPALES COMO SERIAN LAS VIDAS
	public void reset() {
		juego = true;
		astromegaship = new AstroMegaShip(400, 500);
		this.asteroid = new Asteroid[6];
		this.destructor = new Destructor[6];
		this.bulletsdestructor = new bulletDestructor[6];
		this.boss = null;
		jefe = false;
		contInvocar = 0;
		disparosaBoss = 0;
		temp = 0;
		temp2 = 0;
		temp3m = 0;
		temp4m = 0;
		cantAst = 0;
		cantDest = 0;
		for (int i = 0; i < vidas.length; i++) {
			if (vidas[i]) {
				entorno.dibujarImagen(vida, 25 + (i * 50), 25, 0, 0.15);
				if (dioDisparo) {
					entorno.dibujarImagen(vidaMuerto, 25 + (i * 50), 25, 0, 0.15);
				}
			} else {
				entorno.dibujarImagen(vidaMuerto, 25 + (i * 50), 25, 0, 0.15);
			}
		}
	}

	public void inicioJuego() {
		if (juego) {
			menuMusica.pausarMusica();
			musicaPerder.pausarMusica();
			musicaJuego.reproducirMusica();
		 
		}
		temp++; // Temporizador que se usa para marcar las salidas de los asteroides y naves
		entorno.dibujarImagen(loopFondo, 400, fondoy2, 0);

		entorno.dibujarImagen(imgFondo, 400, fondoy, 0);

		for (int i = 0; i < vidas.length; i++) { // DIBUJAR LOS CORAZONES QUE REPRESENTAN LAS VIDAS
			if (vidas[i]) {
				entorno.dibujarImagen(vida, 25 + (i * 50), 25, 0, 0.15);
				if (dioDisparo) {
					System.out.println("estoy aca");
					entorno.dibujarImagen(vidaMuerto, 25 + (i * 50), 25, 0, 0.15);
				}
			} else {
				entorno.dibujarImagen(vidaMuerto, 25 + (i * 50), 25, 0, 0.15);

			}

		}
		// PERDER//
		if (vidas[0] == false) {
			musicaJuego.pausarMusica();
			temp3m++;
			if (temp3m % 200 > 0 && musicPerder == false) {
				 musicaPerder.reproducirMusica();
			} else {
				musicPerder = true;
				 musicaPerder.pausarMusica();
			}
			// PANTALLA DE FIN UNA VEZ QUE PERDIMOS
			entorno.dibujarImagen(fin, 400, 300, 0, 1.5);
			entorno.cambiarFont("8-bit Arcade Out", 90, Color.black);
			entorno.escribirTexto("PERDISTE", 240, 200);
			entorno.cambiarFont("8-bit Arcade In", 90, Color.cyan);
			entorno.escribirTexto("PERDISTE", 240, 200);
			entorno.cambiarFont("8-bit Arcade Out", 70, Color.black);
			entorno.escribirTexto("VOLVER A JUGAR", 180, 300);
			entorno.cambiarFont("8-bit Arcade In", 70, Color.white);
			entorno.escribirTexto("VOLVER A JUGAR", 180, 300);
			entorno.cambiarFont("8-bit Arcade Out", 50, Color.black);
			entorno.escribirTexto("APRIETE R", 305, 350);
			entorno.cambiarFont("8-bit Arcade In", 50, Color.cyan);
			entorno.escribirTexto("APRIETE R", 305, 350);
			entorno.cambiarFont("8-bit Arcade Out", 55, Color.black);
			entorno.escribirTexto("ESC PARA SALIR", 230, 570);
			entorno.cambiarFont("8-bit Arcade In", 55, Color.white);
			entorno.escribirTexto("ESC PARA SALIR", 230, 570);
			juego = false;
		}
		// CIERRE PERDER//

		// GANAR JUEGO//

		if (juego == false && ganar == true) {
			musicaJuego.pausarMusica();
			temp4m++;
			if (temp4m % 300 > 0 && musicWin == false) {
				 musicaGanar.reproducirMusica();
			} else {
				musicWin = true;
				 musicaGanar.pararMusica();
				 
			}
			entorno.dibujarImagen(win, 400, 300, 0, 1.4);
			entorno.cambiarFont("8-bit Arcade Out", 70, Color.black);
			entorno.escribirTexto("FELICIDADES ESCAPASTE", 60, 145);
			entorno.cambiarFont("8-bit Arcade In", 70, Color.white);
			entorno.escribirTexto("FELICIDADES ESCAPASTE", 60, 145);
			entorno.cambiarFont("8-bit Arcade Out", 50, Color.black);
			entorno.escribirTexto("APRIETE ESC PARA SALIR", 150, 550);
			entorno.cambiarFont("8-bit Arcade In", 50, Color.white);
			entorno.escribirTexto("APRIETE ESC PARA SALIR", 150, 550);
		}

		// REINICIAR JUEGO//
		if (juego == false && entorno.estaPresionada('R')) {
			reset();
			navesDestruidas = 0;
			vidas[0] = true;
			vidas[1] = true;
			vidas[2] = true;
			vidasTotal = 4;
			cantOleadas = 1;
			inicioJuego();
		}
		// CIERRE VOLVER A JUGAR//
		// JUEGO TERMINADO O PERDIDO
		if (juego == false) {
			astromegaship = null;
			asteroid = null;
			destructor = null;
			bulletsdestructor = null;
			boss = null;
		}
		// INICIO JUEGO
		if (!entorno.estaPresionada('P') && juego == true && pausa == false) {
			// FONDO LOOP//
			fondoy2 -= 1;
			fondoy -= 1;
			if (fondoy2 == -300) {
				fondoy2 = 900;
			}
			if (fondoy2 == 300) {
				fondoy2 = 300;
			}
			if (fondoy == 300) {
				fondoy = 300;
			}
			if (fondoy == -300) {
				fondoy = 900;
			}
			// CIERRE LOOP//

			// ASTROMEGASHIP//
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				astromegaship.moverDerecha();
				astromegaship.prenderMotor();
			} else {
				astromegaship.apagarMotor();

			}
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				astromegaship.moverIzquierda();
				astromegaship.prenderMotor();
			} else {
				astromegaship.apagarMotor();

			}
			astromegaship.dibujarse(entorno);
			if (entorno.sePresiono(entorno.TECLA_ESPACIO) && bala == false) {
				bala = true;
				bulletsastromegaship = new bulletAstroMegaShip(astromegaship.x, 450);
				 sonidoDisparo.reproducirFX();
			}

			if (bala) {
				if (bulletsastromegaship.by > 0) {
					bulletsastromegaship.avanzarDisparo();
					bulletsastromegaship.dibujar(entorno);
				} else {
					bala = false;
					bulletsastromegaship = null;
				}
			}

			entorno.cambiarFont("SPACEMAN", 20, Color.white);
			entorno.escribirTexto("SCORE:" + navesDestruidas, astromegaship.x - 60, 570);
			// CIERRE ASTROMEGASHIP//

			// RESPAWN//
			if (navesDestruidas2 == 3) {
				navesDestruidas2 = 0;
				cantDest = -3;
			}
			if (cantAstDest == 2) {
				cantAstDest = 0;
				cantAst -= 2;
			}

			// RESPAWN//

			// ASTEROID//
			if (temp % 40 == 0) {
				if (cantAst < 5 && gen.nextInt(2) == 1) {
					for (int i = 0; i < asteroid.length; i++) {
						if (this.asteroid[i] == null) {
							this.asteroid[i] = new Asteroid();
							cantAst += 1;
							break;

						}
					}
				} else if (cantDest < 6 && gen.nextInt(2) == 0) {
					for (int i = 0; i < destructor.length; i++) {

						if (this.destructor[i] == null) {
							this.destructor[i] = new Destructor();
							cantDest += 1;
							break;
						}

					}
				}
			}

			for (int i = 0; i < destructor.length; i++) {
				for (int k = 0; k < destructor.length; k++) {
					if (destructor[i] != null && destructor[k] != null) {
						if ((destructor[i].x - destructor[k].x < 40 && destructor[i].x - destructor[k].x > -40)
								&& (destructor[i].y - destructor[k].y < 40
										&& destructor[i].y - destructor[k].y > -40)) 
						{
							destructor[i].angulo = Math.PI - destructor[i].angulo;
							destructor[i].x += 0.3;
							destructor[k].angulo = Math.PI - destructor[k].angulo;
							if (destructor[k].angulo < 0) {
								destructor[k].x += 0.3;
							}
							if (destructor[k].angulo > 0) {
								destructor[k].x -= 0.3;
							}
						}
					}
				}
			}
			for (int i = 0; i < asteroid.length; i++) {
				if (asteroid[i] != null) {
					asteroid[i].dibujarse(entorno);
					asteroid[i].avanzar(astromegaship);
					if (!colisionDetectada && asteroid[i].colision) {
						System.out.println("COLISION");
						cantAstDest++;
						vidasTotal--;
						if (vidasTotal > 0) {
							vidas[vidasTotal - 1] = false;
						}
						asteroid[i] = null;

					}
					if (bala && asteroid[i]!=null) {
						if ((bulletsastromegaship.by - asteroid[i].y < 33
								&& bulletsastromegaship.by - asteroid[i].y > -33)
								&& (bulletsastromegaship.bx - asteroid[i].x < 40
										&& bulletsastromegaship.bx - asteroid[i].x > -40)) {
							bala = false;
							astromegaship.by = 450;
						}
					}

				}
			}

			// CIERRE ASTEROID

			// DESTRUCTOR
			for (int i = 0; i < destructor.length; i++) {
				if (destructor[i] != null) {
					destructor[i].avanzar(astromegaship);
					destructor[i].dibujarse(entorno);
					if ((astromegaship.y - destructor[i].y < 33 && astromegaship.y - destructor[i].y > -33)
							&& (astromegaship.x - destructor[i].x < 100 && astromegaship.x - destructor[i].x > -100)) {
						vidasTotal--;
						if (vidasTotal > 0) {
							vidas[vidasTotal - 1] = false;
						}
					}
					if (bala) {
						if ((bulletsastromegaship.by - destructor[i].y < 33
								&& bulletsastromegaship.by - destructor[i].y > -33)
								&& (bulletsastromegaship.bx - destructor[i].x < 40
										&& bulletsastromegaship.bx - destructor[i].x > -40)) {
							navesDestruidas++;
							navesDestruidas2++;
							explosionx = destructor[i].x;
							explosiony = destructor[i].y;
							bala = false;
							destructor[i] = null;
							entorno.dibujarImagen(explosion, explosionx, explosiony, 0, 0.25);
							astromegaship.by = 450;
						}

					}
				}

			}

			for (int i = 0; i < bulletsdestructor.length; i++) {
				if (this.destructor[i] != null && bulletsdestructor[i] == null) {
					if (astromegaship.x - destructor[i].x < 5) {
						bulletsdestructor[i] = new bulletDestructor(destructor[i].x, destructor[i].y);
					}
				}
				if (bulletsdestructor[i] != null) {
					bulletsdestructor[i].avanzar();
					bulletsdestructor[i].dibujar(entorno);
					if ((bulletsdestructor[i].by - astromegaship.y < 20
							&& bulletsdestructor[i].by - astromegaship.y > -20)
							&& bulletsdestructor[i].bx - astromegaship.x < 50
							&& bulletsdestructor[i].bx - astromegaship.x > -50) {
						vidasTotal--;
						if (vidasTotal > 0) {
							vidas[vidasTotal - 1] = false;
						}
						bulletsdestructor[i] = null;
						System.out.println("EL DESTRUCTOR LE DIO A MI NAVE");
					}
					if (bulletsdestructor[i] != null && bulletsdestructor[i].by > 600) {
						bulletsdestructor[i] = null;
					}
				}

			}

			// CIERRE DESTRUCTOR//

			// LOGICA CUANDO HAY UNA COLISION ENTRE ASTEROIDE Y DESTRUCTOR//
			for (int i = 0; i < destructor.length; i++) {
				for (int j = 0; j < asteroid.length; j++) {
					if (asteroid[j] != null && destructor[i] != null) {
						if ((destructor[i].x - asteroid[j].x < 80 && destructor[i].x - asteroid[j].x > -80)
								&& (destructor[i].y - asteroid[j].y < 80 && destructor[i].y - asteroid[j].y > -80)) {
							destructor[i].angulo = Math.PI + destructor[i].angulo;
							destructor[i].x += 0.3;
							destructor[i].y += 2.3;
							asteroid[j].angulo = Math.PI - asteroid[j].angulo;
							asteroid[j].x += 0.3;
							if (asteroid[j].angulo < 0) {
								asteroid[j].x += 0.3;
							}
							if (asteroid[j].angulo > 0) {
								asteroid[j].x -= 0.3;
							}
						}
					}

				}
			}
			// CONFIGURANDO BOSS//
			if (navesDestruidas == navesaDestruir && dificil) {
				jefe = true;
				for (int i = 0; i < this.destructor.length; i++) {
					this.destructor[i] = null;
				}
				if (contInvocar == 0) {
					this.boss = new Boss(400, 100); // Para que se invoque solo una vez.
					boss.easteregg(conteasteregg);
					contInvocar++;
				}

			}

			if (boss != null) {
				this.boss.avanzar(astromegaship);
				this.boss.dibujarse(entorno);
				if (bala) {
					if ((bulletsastromegaship.by - this.boss.y < 33 && bulletsastromegaship.by - this.boss.y > -33)
							&& (bulletsastromegaship.bx - this.boss.x < 100
									&& bulletsastromegaship.bx - this.boss.x > -100)) {
						disparosaBoss++;
						explosionx = this.boss.x;
						explosiony = this.boss.y;
						bala = false;
					}
				}
				if (disparosaBoss<=3) {
					entorno.dibujarImagen(hpboss5, 400, 30, 0,0.2);
				}
				else if (disparosaBoss>=3 && disparosaBoss<=6) {
					entorno.dibujarImagen(hpboss4, 400, 30, 0,0.2);
				}
				else if (disparosaBoss>=6 && disparosaBoss<=9) {
					entorno.dibujarImagen(hpboss3, 400, 30, 0,0.2);
				}
				else if (disparosaBoss>=9 && disparosaBoss<=12) {
					entorno.dibujarImagen(hpboss2, 400, 30, 0,0.2);
				}
				else if (disparosaBoss>=12 && disparosaBoss<=15) {
					entorno.dibujarImagen(hpboss1, 400, 30, 0,0.2);
				}
				
				if (disparosaBoss == 15) {
					boss = null;
					jefe = false;
					entorno.dibujarImagen(explosion, explosionx, explosiony, 0, 0.2);
					astromegaship.by = 450;
				}
			}

			for (int i = 0; i < bulletsdestructor.length; i++) {
				if (this.boss != null && bulletsdestructor[i] == null) {
					if (jefe) {
						bulletsdestructor[i] = new bulletDestructor(this.boss.x, this.boss.y);
					}
					if (bulletsdestructor[i] != null) {
						bulletsdestructor[i].avanzar();
						bulletsdestructor[i].dibujar(entorno);
					}
					if ((bulletsdestructor[i].by - astromegaship.y < 20
							&& bulletsdestructor[i].by - astromegaship.y > -20)
							&& bulletsdestructor[i].bx - astromegaship.x < 50
							&& bulletsdestructor[i].bx - astromegaship.x > -50) {
						vidasTotal--;
						bulletsdestructor[i] = null;
						vidas[vidasTotal - 1] = false;

					}
				}
				if (bulletsdestructor[i] != null && bulletsdestructor[i].by > 600) {

					bulletsdestructor[i] = null;
				}
			}
			// CIERRE BOSS//
			if (navesaDestruir == navesDestruidas && jefe == false) {
				juego = false;
				ganar = true;
			}
			//CURACION PARA SUPERACION DE OLEADAS//
			if (medio || dificil) {
				if (juego == true && cantOleadas%2 == 0 && curar == 0) {
					if (vidas[2]==false && vidas[1]==true) {
						vidas[2]=true;
						vidasTotal++;
						curar+=1;
					}
					else if (vidas[1]==false && vidas[0]==true && curar == 0) {
						vidas[1]=true;
						vidasTotal++;
						curar+=1;
					}
					
				}
			}
			//CIERRE CURACION//
		}

		// PASAR OLEADA//
		if (navesDestruidas == 5 * cantOleadas) {
			temp5++;
			levelUp+=1;
			if (navesaDestruir != navesDestruidas && temp5 % 100 > 0) {
				if (levelUp == 1) {
					musicaOleadaSuperada.reproducirFX();
					curar = 0;
				}
				pausa = true;
				entorno.cambiarFont("8-bit Arcade Out", 90, Color.black);
				entorno.escribirTexto("OLEADA SUPERADA", 70, 300);
				entorno.cambiarFont("8-bit Arcade In", 90, Color.cyan);
				entorno.escribirTexto("OLEADA SUPERADA", 70, 300);
			} else {
				reset();
				pausa = false;
				levelUp = 0;
				cantOleadas += 1;
			}

		}

		// CIERRE JUEGO
		if (entorno.sePresiono(TECLA_ESC)) {
			System.exit(0);
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
