package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Funciones {

		public class Colores {
			public static final Color Turquesa = new Color(96, 198, 194);
			public static final Color Coral = new Color(255,102,102);
			public static final Color Gris = new Color(230, 230, 230);
		}
		
		public static class Letra {

		    public static Font normal(int size) {
		        return new Font("Segoe UI", Font.PLAIN, size);
		    }

		    public static Font negrita(int size) {
		        return new Font("Segoe UI", Font.BOLD, size);
		    }

		    public static Font inclinada(int size) {
		        return new Font("Segoe UI", Font.ITALIC, size);
		    }
		}
		
		public static void botonBonito(JButton boton, Color Coral) {
			boton.setForeground(Coral);
			boton.setBackground(Color.WHITE);
			boton.setFocusPainted(false);
			boton.setFont(new Font("Segoe UI", Font.BOLD, 16));

			boton.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					boton.setForeground(Coral);
					boton.setBackground(Color.WHITE);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					boton.setForeground(Color.WHITE);
					boton.setBackground(Coral);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});

		}
}
