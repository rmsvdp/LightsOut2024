import guiTools.Menu;
import guiTools.Tablero;

public class LigthsOut {

	
	private Menu menuPrincipal;						// Men� para gestionar el juego
	private int movimientos;						// Movimientos en un partida
	private Tablero tlo = new Tablero(5,5,true);	// Tablero de 5X5 descubierto
	
	public int getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}

	public static void main(String[] args) {

		LigthsOut lo = new LigthsOut();
		lo.nuevaPartida();
		System.out.println("\nAplicación terminada");
	}

	public void nuevaPartida() {
		
	// -------------- Inicializaci�n de la partida

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
		

		boolean finJuego = false;
		
		this.movimientos = 0;
		tlo.limpiaTablero();
		setNivel();
		while (!finJuego) {
			
			tlo.mostrarTablero();
			tlo.leeMovimiento();
			this.procesa();
			if (tlo.isEmpty()){
				finJuego=true;
			}
			else this.movimientos++;
			
			
		} // while !finJuego
		System.out.println("Nivel terminado en :" + this.getMovimientos() + "!" +
		"\n***********************************************\n");
	} // PlayTheGame
	
	/**
	 * Inicializa el juego con un nivel predefinido
	 */
	public void setNivel() {
		
		int[][] nivel1 = {{1,1},{0,4},{3,3},{3,4}};
		
		for(int i=0;i<nivel1.length;i++) {
			
			tlo.marcarCelda(nivel1[i][0], nivel1[i][1], 'X');
		}
		
	} // setNivel
	
	public void procesa() {
		
		invierte(tlo.getFila(),tlo.getColumna());
		invierte(tlo.getFila()+1,tlo.getColumna());
		invierte(tlo.getFila()-1,tlo.getColumna());
		invierte(tlo.getFila(),tlo.getColumna()+1);
		invierte(tlo.getFila(),tlo.getColumna()-1);
		
	}
	
	public void invierte(int fila, int columna) {
		// TODO implementar la inversión con tryCatch
		/*
		try {
			char celda;
			if (tlo.leeCelda(fila,columna)==tlo.getemptyCell)
				celda = 'X';
			else tlo.marcarCelda(fila, columna, tlo.getemptyCell());
		} catch (ArrayIndexOutOfBoundsException e){
				// no hacemos nada
		}
		*/
	}
	
}
