package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import db.BD;

public class VentanaEliminarApartamento extends JDialog {

    private JLabel lblImagen;
    private String idAlojamiento;
    private Thread hiloFotos;
    private volatile boolean seguirMostrando = true;
    private ImageIcon[] imagenes;

    private int usuarioID;
    private Runnable onDeleted;

    public VentanaEliminarApartamento(
            Window padre,
            int usuarioID,
            String id,
            String titulo,
            String barrio,
            int capacidad,
            double precioNoche,
            double rating,
            Runnable onDeleted) {

        super(padre, "Apartamento " + id, ModalityType.APPLICATION_MODAL);
        this.usuarioID = usuarioID;
        this.idAlojamiento = id;
        this.onDeleted = onDeleted;

        setSize(1000, 600);
        setLocationRelativeTo(padre);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(25, 25));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        panel.setBackground(Color.WHITE);
        setContentPane(panel);

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setBorder(new EmptyBorder(10, 10, 20, 10));
        cabecera.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.LEFT);
        lblTitulo.setFont(Funciones.Letra.negrita(28));
        lblTitulo.setForeground(Color.BLACK);

        JLabel lblBarrio = new JLabel(barrio, SwingConstants.RIGHT);
        lblBarrio.setFont(Funciones.Letra.normal(16));
        lblBarrio.setForeground(Color.BLACK);

        cabecera.add(lblTitulo, BorderLayout.WEST);
        cabecera.add(lblBarrio, BorderLayout.EAST);

        panel.add(cabecera, BorderLayout.NORTH);

        JPanel pCentro = new JPanel(new BorderLayout(30, 0));
        pCentro.setBackground(Color.WHITE);
        panel.add(pCentro, BorderLayout.CENTER);

        JPanel pImagen = new JPanel(new BorderLayout());
        pImagen.setBackground(new Color(245, 245, 245));
        pImagen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(10, 10, 10, 10)
        ));
        pImagen.setPreferredSize(new Dimension(420, 0));

        lblImagen = new JLabel("Cargando fotos...", SwingConstants.CENTER);
        lblImagen.setFont(Funciones.Letra.normal(14));
        lblImagen.setForeground(Color.DARK_GRAY);

        pImagen.add(lblImagen, BorderLayout.CENTER);
        pCentro.add(pImagen, BorderLayout.WEST);

        JPanel pDerecha = new JPanel();
        pDerecha.setBackground(Color.WHITE);
        pDerecha.setLayout(new BoxLayout(pDerecha, BoxLayout.Y_AXIS));
        pCentro.add(pDerecha, BorderLayout.CENTER);

        JPanel pDetalles = new JPanel(new GridLayout(0, 2, 15, 18));
        pDetalles.setOpaque(false);

        pDetalles.add(crearLabel("ID:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(id, Funciones.Letra.negrita(16)));

        pDetalles.add(crearLabel("Barrio:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(barrio, Funciones.Letra.negrita(16)));

        pDetalles.add(crearLabel("Capacidad:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(capacidad + " personas", Funciones.Letra.negrita(16)));

        pDetalles.add(crearLabel("Rating:", Funciones.Letra.normal(16)));
        pDetalles.add(crearBold(
                String.format("%.1f / 5   %s", rating, Funciones.estrellas(rating)),
                Funciones.Letra.negrita(16))
        );

        pDerecha.add(pDetalles);
        pDerecha.add(Box.createVerticalStrut(25));

        JPanel pAccion = new JPanel();
        pAccion.setLayout(new BoxLayout(pAccion, BoxLayout.Y_AXIS));
        pAccion.setBackground(new Color(247, 247, 247));
        pAccion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JButton btnEliminar = new JButton("Eliminar apartamento");
        btnEliminar.setFont(Funciones.Letra.negrita(16));
        btnEliminar.setBackground(Color.BLACK);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);

        btnEliminar.addActionListener(e -> eliminarApartamento());

        JLabel lblInfo = new JLabel("¿Quieres quitar este apartamento?", SwingConstants.CENTER);
        lblInfo.setFont(Funciones.Letra.normal(16));
        lblInfo.setForeground(Color.BLACK);

        pAccion.add(lblInfo);
        pAccion.add(Box.createVerticalStrut(15));
        pAccion.add(btnEliminar);

        pDerecha.add(pAccion);
        pDerecha.add(Box.createVerticalGlue());

        JPanel pSur = new JPanel();
        pSur.setBackground(Color.WHITE);
        pSur.setPreferredSize(new Dimension(0, 60));
        pSur.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 0, 0, 0),
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220))
        ));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(Funciones.Letra.negrita(14));
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setForeground(Color.BLACK);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createLineBorder(Funciones.Colores.Gris));
        btnCerrar.addActionListener(e -> dispose());

        pSur.add(btnCerrar);
        panel.add(pSur, BorderLayout.SOUTH);

        cargarImagenesAlojamiento(idAlojamiento);
        iniciarHiloFotos();
    }

    private void eliminarApartamento() {
        int resp = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar este apartamento de tus alojamientos?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (resp != JOptionPane.YES_OPTION) return;

        boolean ok = BD.eliminarAlquiler(usuarioID, idAlojamiento); 

        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Apartamento eliminado correctamente de tus alojamientos.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            if (onDeleted != null) onDeleted.run();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No ha sido posible eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarImagenesAlojamiento(String id) {
        imagenes = new ImageIcon[3];
        for (int i=0; i<3; i++) {
            String ruta = "/images/" + id + "_" + (i+1) + ".jpg";
            java.net.URL url = getClass().getResource(ruta);
            if (url != null) {
                ImageIcon original = new ImageIcon(url);
                Image imgEscalada = original.getImage().getScaledInstance(380, 260, Image.SCALE_SMOOTH);
                imagenes[i] = new ImageIcon(imgEscalada);
            } else {
                imagenes[i] = null;
            }
        }
    }

    private void iniciarHiloFotos() {
        boolean hayAlguna = false;
        for (ImageIcon ic : imagenes) {
            if (ic != null) { hayAlguna = true; break; }
        }
        if (!hayAlguna) { lblImagen.setText("No hay imágenes disponibles"); return; }

        hiloFotos = new Thread(() -> {
            int indice = 0;
            while (seguirMostrando) {
                if (imagenes[indice] == null) {
                    indice = (indice + 1) % imagenes.length;
                    continue;
                }
                final int idx = indice;
                SwingUtilities.invokeLater(() -> {
                    lblImagen.setText(null);
                    lblImagen.setIcon(imagenes[idx]);
                });
                try { Thread.sleep(2500); } catch (InterruptedException e) { break; }
                indice = (indice + 1) % imagenes.length;
            }
        });
        hiloFotos.start();
    }

    @Override
    public void dispose() {
        seguirMostrando = false;
        if (hiloFotos != null && hiloFotos.isAlive()) hiloFotos.interrupt();
        super.dispose();
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

