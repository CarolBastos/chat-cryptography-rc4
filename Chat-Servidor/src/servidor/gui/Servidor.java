/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Servidor.java
 *
 * Created on May 1, 2011, 3:44:06 PM
 */
package servidor.gui;

import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Servidor extends javax.swing.JFrame {

    ArrayList<DataOutputStream> clientes;
    Servidor servidor;

    public void sendToAll(String host, String msg) throws IOException {
        for (DataOutputStream cliente : clientes) {
            cliente.writeUTF(host);
            cliente.writeUTF(msg);
        }
    }

    public void ligar() throws IOException {
        int porta = 22222;
        clientes = new ArrayList<DataOutputStream>();

        ServerSocket servidor = new ServerSocket(porta);
        Conectar conectar = new Conectar(servidor, clientes, this.servidor);
        conectar.start();


    }

    public Servidor() {
        initComponents();
        Font font = new Font("Arial", Font.BOLD, 14);
        tf_conexoes.setFont(font);
        bt_desligar.setEnabled(false);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_desligar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_conexoes = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bt_ligar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_desligar.setFont(new java.awt.Font("Arial", 0, 18));
        bt_desligar.setText("             Desligar          ");
        bt_desligar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_desligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_desligarActionPerformed(evt);
            }
        });

        tf_conexoes.setColumns(20);
        tf_conexoes.setRows(5);
        jScrollPane1.setViewportView(tf_conexoes);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel1.setText("Servidor");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18));
        jLabel2.setText("Chat com Criptografia - Algoritmo RC4");

        bt_ligar.setFont(new java.awt.Font("Arial", 0, 18));
        bt_ligar.setText("              Ligar             ");
        bt_ligar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_ligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ligarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel3.setText("Histórico");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(bt_ligar)
                            .addGap(18, 18, 18)
                            .addComponent(bt_desligar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_desligar)
                    .addComponent(bt_ligar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_ligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ligarActionPerformed
        tf_conexoes.append("Aguardando Conexões...\n");
        bt_ligar.setEnabled(false);
        bt_desligar.setEnabled(true);
        try {
            ligar();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_ligarActionPerformed

    private void bt_desligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_desligarActionPerformed
        bt_desligar.setEnabled(false);
        bt_ligar.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_bt_desligarActionPerformed

    public void run(Servidor servidor) {
        this.servidor = servidor;
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_desligar;
    private javax.swing.JButton bt_ligar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea tf_conexoes;
    // End of variables declaration//GEN-END:variables
}

class Conectar extends Thread {
    Connection gerenciador;
    ServerSocket servidor;
    ArrayList<DataOutputStream> clientes;
    Servidor server;
    public Conectar(ServerSocket servidor,ArrayList<DataOutputStream> clientes, Servidor server) {
        this.servidor = servidor;
        this.clientes = clientes;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket cliente = servidor.accept();
                DataOutputStream outCliente = new DataOutputStream(
                        cliente.getOutputStream());
                DataInputStream inCliente = new DataInputStream(
                        cliente.getInputStream());
                clientes.add(outCliente);
                server.tf_conexoes.append("Cliente " + cliente.getInetAddress().getHostAddress()
                        + " está CONECTADO !\n");
                gerenciador = new Connection(server, inCliente, cliente.getInetAddress().getHostAddress());
                gerenciador.start();
            } catch (IOException ex) {
                Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}

class Connection extends Thread {

    DataInputStream cliente;
    Servidor servidor;
    String host;

    public Connection(Servidor servidorr, DataInputStream cliente, String host) {
        servidor = servidorr;
        this.cliente = cliente;
        this.host = host;
    }

    @Override
    public void run() {
        String msg;
        while (true) {
            try {
                msg = cliente.readUTF();
                servidor.sendToAll(host, msg);
                servidor.tf_conexoes.append("MSG: " + msg + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
