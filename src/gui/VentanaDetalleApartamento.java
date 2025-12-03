package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaDetalleApartamento extends JDialog {

    public VentanaDetalleApartamento(
            Window padre,
            String id,
            String titulo,
            String barrio,
            int capacidad,
            double precioNoche,
            double rating) {

        super(padre, "Apartamento " + id, ModalityType.APPLICATION_MODAL);

        setSize(820, 470);
        setLocationRelativeTo(padre);
        setResizable(false);

        JPanel raiz = new JPanel(new BorderLayout(25, 25));
        raiz.setBorder(new EmptyBorder(25, 25, 25, 25));
        raiz.setBackground(Color.WHITE);
        setContentPane(raiz);
        
        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setBorder(new EmptyBorder(25, 25, 25, 25));
        cabecera.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.LEFT);
        lblTitulo.setFont(Funciones.Letra.normal(28));
        lblTitulo.setForeground(Color.BLACK);
        
        JLabel lblBarrio = new JLabel(barrio, SwingConstants.RIGHT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(Color.BLACK);
        
        cabecera.add(lblTitulo, BorderLayout.WEST);
        cabecera.add(lblBarrio, BorderLayout.EAST);
        
        raiz.add(cabecera, BorderLayout.NORTH);
        
        JLabel lineaFina = new JLabel();
        lineaFina.setOpaque(true);
        lineaFina.setBackground(Funciones.Colores.Gris);
        lineaFina.setPreferredSize(new Dimension(820, 1));
        raiz.add(lineaFina, BorderLayout.AFTER_LAST_LINE);
        
        JPanel pcentro = new JPanel(new GridLayout());
        pcentro.setBackground(Color.WHITE);
        raiz.add(pcentro, BorderLayout.CENTER);
        
        GridBagConstraints margen = new GridBagConstraints();
        margen.insets = new Insets(10, 20, 10, 20);
        margen.fill = GridBagConstraints.BOTH;
        margen.weightx = 1;
        margen.weighty = 1;
        
        JPanel pDetalles = new JPanel(new GridLayout(0, 2, 15, 18));
        pDetalles.setOpaque(false);
        
        
    }
}