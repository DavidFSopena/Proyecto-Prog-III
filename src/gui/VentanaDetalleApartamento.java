package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
        lblTitulo.setFont(Funciones.Letra.negrita(28));
        lblTitulo.setForeground(Color.BLACK);
        
        JLabel lblBarrio = new JLabel(barrio, SwingConstants.RIGHT);
        lblBarrio.setFont(Funciones.Letra.normal(16));
        lblBarrio.setForeground(Color.BLACK);
        
        cabecera.add(lblTitulo, BorderLayout.WEST);
        cabecera.add(lblBarrio, BorderLayout.EAST);
        
        raiz.add(cabecera, BorderLayout.NORTH);
        
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
        
        pDetalles.add(crearLabel("ID:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(id, Funciones.Letra.negrita(16)));
        
        pDetalles.add(crearLabel("Barrio:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(barrio, Funciones.Letra.negrita(16)));

        pDetalles.add(crearLabel("Capacidad:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(capacidad + " personas", Funciones.Letra.negrita(16)));
        
        pDetalles.add(crearLabel("Rating:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(String.format("%.1f / 5   %s", rating, Funciones.estrellas(rating)), Funciones.Letra.negrita(16)));
        
        margen.gridx = 0;
        margen.gridy = 0;
        pcentro.add(pDetalles, margen);
        
        JPanel pPrecio = new JPanel();
        pPrecio.setLayout(new BoxLayout(pPrecio, BoxLayout.Y_AXIS));
        pPrecio.setBackground(new Color(247, 247, 247));
        pPrecio.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)),new EmptyBorder(25, 25, 25, 25)));
    
        JLabel lblDesde = new JLabel("Precio por noche", SwingConstants.CENTER);
        lblDesde.setFont(Funciones.Letra.normal(14));
        lblDesde.setForeground(Funciones.Colores.Gris);
        
        JLabel lblPrecio = new JLabel(String.format("%.2f €", precioNoche), SwingConstants.CENTER);
        lblPrecio.setFont(Funciones.Letra.negrita(30));
        lblPrecio.setForeground(Color.BLACK);
        
        pPrecio.add(lblDesde);
        pPrecio.add(Box.createVerticalStrut(10));
        pPrecio.add(lblPrecio);
        
        JPanel pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        pSur.setPreferredSize(new Dimension(0, 60));
        pSur.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20, 0, 0, 0),BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220,220,220))));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(Funciones.Letra.negrita(14));
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setForeground(Color.BLACK);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createLineBorder(Funciones.Colores.Gris));
        btnCerrar.addActionListener(e -> dispose());
        
        pSur.add(btnCerrar);
        raiz.add(pSur, BorderLayout.SOUTH);        
    }
    
    private JLabel crearLabel(String texto, Font f) {
        JLabel lbl = new JLabel(texto, SwingConstants.RIGHT);
        lbl.setFont(f);
        lbl.setForeground(Color.DARK_GRAY);
        return lbl;
    }

    private JLabel crearBold(String texto, Font f) {
        JLabel lbl = new JLabel(texto, SwingConstants.LEFT);
        lbl.setFont(f);
        lbl.setForeground(Color.BLACK);
        return lbl;
    }

}