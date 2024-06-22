/*
Desarrollador: ÁLVARO DE LA ORDEN
Programador: ÁLVARO DE LA ORDEN
 */
package gui;

import java.io.File;
import java.util.ArrayList;

public class AdvertenciaSalir extends javax.swing.JDialog {

    public  final int OPCION_SALIR = 1;
    public  final int OPCION_CANCELAR = 0;
    
    private int opcionSeleccionada = OPCION_CANCELAR;

    public AdvertenciaSalir(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Salir");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImagenAdvertencia = new javax.swing.JPanel();
        labelImagenAdvertencia = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        botonSi = new javax.swing.JButton();
        botonNo = new javax.swing.JButton();
        labelTexto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelImagenAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloAdvertencia.png"))); // NOI18N

        javax.swing.GroupLayout panelImagenAdvertenciaLayout = new javax.swing.GroupLayout(panelImagenAdvertencia);
        panelImagenAdvertencia.setLayout(panelImagenAdvertenciaLayout);
        panelImagenAdvertenciaLayout.setHorizontalGroup(
            panelImagenAdvertenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImagenAdvertencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImagenAdvertenciaLayout.setVerticalGroup(
            panelImagenAdvertenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImagenAdvertencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonSi.setText("Sí");
        botonSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiActionPerformed(evt);
            }
        });
        panelBotones.add(botonSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, -1, -1));

        botonNo.setText("No");
        botonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNoActionPerformed(evt);
            }
        });
        panelBotones.add(botonNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 6, -1, -1));

        labelTexto.setText("¿Está seguro de que desea salir?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(panelImagenAdvertencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(labelTexto)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImagenAdvertencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void botonSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiActionPerformed
        opcionSeleccionada = OPCION_SALIR; // Marcar que el usuario eligió salir
        this.dispose();
         
    }//GEN-LAST:event_botonSiActionPerformed
    private void botonNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNoActionPerformed
        opcionSeleccionada = OPCION_CANCELAR; // Marcar que el usuario eligió cancelar
        this.dispose(); // Cerrar el JDialog
    }//GEN-LAST:event_botonNoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonNo;
    private javax.swing.JButton botonSi;
    private javax.swing.JLabel labelImagenAdvertencia;
    private javax.swing.JLabel labelTexto;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelImagenAdvertencia;
    // End of variables declaration//GEN-END:variables
    
    public int getOpcionSeleccionada() {
        return opcionSeleccionada;
    }
}
