package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Funciones {

	public class Colores {
		public static final Color Turquesa = new Color(96, 198, 194);
		public static final Color Coral = new Color(255, 102, 102);
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

	public static String estrellas(double rating) {
		int r = (int) Math.round(rating);
		StringBuilder sb = new StringBuilder();
		final String ESTRELLA_LLENA = "*";
		final String ESTRELLA_VACIA = ".";
		for (int i = 0; i < 5; i++) {
			if (i < r) {
				sb.append(ESTRELLA_LLENA);
			} else {
				sb.append(ESTRELLA_VACIA);
			}
		}
		return sb.toString();
	}

	public static JPanel ratingPanel(double rating, int sizePx, Color fondo) {

		int llenas = (int) Math.round(rating);
		if (llenas < 0)
			llenas = 0;
		if (llenas > 5)
			llenas = 5;
		ImageIcon amarilla = new ImageIcon("resources/images/estrella_amarilla.png");
		ImageIcon gris = new ImageIcon("resources/images/estrella_gris.png");
		amarilla = new ImageIcon(amarilla.getImage().getScaledInstance(sizePx, sizePx, Image.SCALE_SMOOTH));
		gris = new ImageIcon(gris.getImage().getScaledInstance(sizePx, sizePx, Image.SCALE_SMOOTH));

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		p.setOpaque(true);
		p.setBackground(fondo);

		for (int i = 0; i < 5; i++) {
			JLabel estrella = new JLabel();
			if (i < llenas) {
	            estrella.setIcon(amarilla);
	        } else {
	            estrella.setIcon(gris);
	        }
			p.add(estrella);
		}

		return p;
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

	public static void aplicarIcono(JFrame ventana) {
		ImageIcon image = new ImageIcon("resources/images/ImagenTrans.png");
		ventana.setIconImage(image.getImage());
	}

	public static JLabel crearReloj() {

		JLabel lbl = new JLabel("", SwingConstants.RIGHT);

		lbl.setFont(Letra.negrita(14));
		lbl.setForeground(Color.BLACK);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Timer t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lbl.setText(sdf.format(new Date()));

			}
		});

		t.setInitialDelay(0);
		t.start();

		return lbl;
	}

}
