package main;

import db.BD;
import gui.Ventana1;
import gui.Ventana2_1;
import gui.Ventana2_2;
import gui.Ventana3;

public class Main {
	public static void main(String[] arg) {

		BD.initBD("resources/db/bilbobnb.db");
		
		// Ventanas
		new Ventana1();
		//new Ventana2_1();
		//new Ventana2_2();
//		new Ventana3();
		
		// Base de datos
		BD.crearTablaUsuario();
		BD.crearTablaAlquiler();
		BD.crearTablaAlojamiento();
		
		
	}
}

