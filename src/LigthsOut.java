import guiTools.Menu;
import guiTools.Tablero;

public class LigthsOut {

	
	private Menu menuPrincipal;						// Men� para gestionar el juego
	private int movimientos;						// Movimientos en un partida
	private Tablero tlo = new Tablero(5,5,true);	// Tablero de 5X5 descubierto
	
	public int getMovimientos() {return movimientos;	}

	public void setMovimientos(int movimientos) {	this.movimientos = movimientos;	}

	public static void main(String[] args) {

		LigthsOut lo = new LigthsOut();
		lo.nuevaPartida();
		System.out.println("\nAplicación terminada");
	}

	public void nuevaPartida() {
		
	// -------------- Inicialización de la partida
		String [] opcs = {"Nueva Partida","Tabla de Puntuaciones"};
		this.menuPrincipal = new Menu ();
		boolean finSesion = false;
		int result;
		menuPrincipal = new Menu(opcs);
		menuPrincipal.setTitulo("LIGHTS OUT!");
		while (!finSesion) {
			menuPrincipal.mostrarMenu();
			result = menuPrincipal.eligeOpcion();
			switch(result) {			
				case 1:	// iniciar partida
					this.playTheGame();
					break;
				case 2: // Creditos
					System.out.println("\n1 TBP \n");
					break;
				case 0: // salir aplicaci�n
					finSesion= true;
			} // switch
		} // while !finSesion
	} // JuegaPartida

	public void playTheGame() {
		

		boolean finJuego = false;		// Variable para determinar el fin de la partida
		
		this.movimientos = 0;
		tlo.limpiaTablero();
		setNivel();						// Elige un nivel y rellena el tablero
		while (!finJuego) {
			tlo.mostrarTablero();		// Pintar tablero
			tlo.leeMovimiento();		// Pedir movimiento al usuario
			this.procesaMovimiento();	// Activar/desactivar luces a partir del movimiento
			if (tlo.isEmpty()){			// Si el tablero esta apagado ...
				finJuego=true;			// he completado el nivel
			}
			this.movimientos++;			// actualizar numero de movimientos
		} // while !finJuego
		System.out.println("***********************************************\n"
				+          "Nivel terminado en : " + this.getMovimientos() + " movimientos !" +
		                 "\n***********************************************\n");
	} // PlayTheGame
	
	/**
	 * Inicializa el juego con un nivel predefinido
	 */
	public void setNivel() {
		
		int[][] nivel1 = {{2,0},{2,2},{2,4}};  // Solo almaceno las casillas encendidas
		
		for(int i=0;i<nivel1.length;i++) {
			tlo.marcarCelda(nivel1[i][0], nivel1[i][1], 'X'); // Activo las casillas
		}
	} // setNivel
	
	public void procesaMovimiento() {
		
		invierte(tlo.getFila(),tlo.getColumna());			// Celda elegida
			invierte(tlo.getFila()+1,tlo.getColumna());		// Celda Abajo
			invierte(tlo.getFila()-1,tlo.getColumna());		// Celda Arriba
			invierte(tlo.getFila(),tlo.getColumna()+1);		// Celda Derecha
			invierte(tlo.getFila(),tlo.getColumna()-1);		// Celda Izquierda

	}
	/**
	 * Invierte la celda elegida, si estaba encendida la apaga y viceversa.
	 * Importante , es preciso capturar la excepcción que se producirá 
	 * si alguna de las celdas adyacentes se encuentra fuera del tablero
	 * @param fila		Fila de la elección
	 * @param columna	columna de la elección
	 */
	public void invierte(int fila, int columna) {
		try {				
			char celda= tlo.leerCelda(fila,columna);
			if (celda ==tlo.getEmptyCell())
				celda = 'X';
			else
				celda = tlo.getEmptyCell();
			tlo.marcarCelda(fila, columna, celda);	
		}
	catch (Exception e) {
		// No hacer nada, es como si no se hubiese pedido su inversión
	} 
			
	}// invierte
	
} // Class
