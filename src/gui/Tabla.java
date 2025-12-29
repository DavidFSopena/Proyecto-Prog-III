package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Tabla {

    public static void aplicar(JTable tabla) {

        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(Funciones.Letra.normal(14));
        tabla.setRowHeight(32);

        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(true);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setIntercellSpacing(new Dimension(1, 1));

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setSelectionBackground(new Color(225, 240, 240));
        tabla.setSelectionForeground(Color.BLACK);

        JTableHeader h = tabla.getTableHeader();
        h.setFont(Funciones.Letra.negrita(14));
        h.setBackground(new Color(245, 245, 245));
        h.setForeground(new Color(50, 50, 50));
        h.setPreferredSize(new Dimension(h.getPreferredSize().width, 38));
        h.setReorderingAllowed(false);

        tabla.setDefaultRenderer(Object.class, new Renderer());
        tabla.setDefaultRenderer(Number.class, new Renderer());
    }

    private static class Renderer extends DefaultTableCellRenderer {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Color zebra1 = Color.WHITE;
        private final Color zebra2 = new Color(247, 249, 252);

        Renderer() {
            setBorder(new EmptyBorder(0, 12, 0, 12));
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        	Point mouse = table.getMousePosition();
            boolean hover = false;

            if (mouse != null) {
                int filaHover = table.rowAtPoint(mouse);
                int colHover = table.columnAtPoint(mouse);
                hover = (filaHover == row && colHover == column);
            }

            if (column == 3 && hover) {
                int cap = 0;
                try {
                    cap = Integer.parseInt(value.toString());
                } catch (Exception e) {}

                JProgressBar pb = new JProgressBar(0, 5);
                pb.setValue(cap);
                pb.setStringPainted(true);
                pb.setString(cap + " personas");

                pb.setBackground(isSelected ? new Color(225, 240, 240) : ((row % 2 == 0) ? zebra1 : zebra2));
                pb.setForeground(new Color(60, 160, 140));
                pb.setBorder(new EmptyBorder(6, 12, 6, 12));

                return pb;
            }
        	
            JLabel l = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            l.setFont(Funciones.Letra.normal(14));
            l.setForeground(Color.BLACK);

            if (isSelected) {
                l.setBackground(new Color(225, 240, 240));
            } else if (row % 2 == 0) {
                l.setBackground(zebra1);
            } else {
                l.setBackground(zebra2);
            }

            if (column == 0 || column == 2 || column == 3 || column == 4 || column == 5) {
                l.setHorizontalAlignment(JLabel.CENTER);
            } else {
                l.setHorizontalAlignment(JLabel.LEFT);
            }

            if (value != null && column == 4) {
                try {
                    double p = Double.parseDouble(value.toString());
                    l.setText(String.format("%.2f €", p));
                } catch (Exception e) {}
            }

            if (value != null && column == 5) {
                try {
                    double r = Double.parseDouble(value.toString());
                    l.setText(String.format("%.1f  %s", r, Funciones.estrellas(r)));

                    if (hover) {
                        if (r < 2) {
                            l.setForeground(new Color(200, 40, 40));
                        } else if (r < 3.5) {
                            l.setForeground(new Color(230, 140, 0));
                        } else {
                            l.setForeground(new Color(40, 160, 80));
                        }
                    } else {
                        l.setForeground(new Color(80, 80, 80));
                    }
                } catch (Exception e) {}
            }

            return l;
        }
    }
}