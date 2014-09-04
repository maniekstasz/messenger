/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainView.java
 *
 * Created on 2011-02-11, 00:24:57
 */

package messenger.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;

import messenger.controller.UserController;


public class MainView extends javax.swing.JFrame {
	
	UserController controller;
    /** Creates new form MainView */
    public MainView(UserController controller) {
    	super("Jadu Messenger");
        initComponents();
        this.controller = controller;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width-270,screenSize.height-450);
        
        
    }
    
    private class ExitAction extends WindowAdapter{

            @Override
            public void windowClosing(WindowEvent e) {
                    controller.close();
            }
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        contactList = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        rozmowaItem = new javax.swing.JMenuItem();
        szukajItem = new javax.swing.JMenuItem();
        usunItem = new javax.swing.JMenuItem();
        wyjdzItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(contactList);
        this.addWindowListener(new ExitAction());
        jMenu1.setText("Akcje");
        contactList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                	controller.showConversationFrame();
                }
            }
        });
        rozmowaItem.setText("Rozmowa");
        rozmowaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rozmowaItemActionPerformed(evt);
            }
        });
        jMenu1.add(rozmowaItem);
        

        szukajItem.setText("Znajdź kontakt");
        szukajItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                szukajItemActionPerformed(evt);
            }
        });
        jMenu1.add(szukajItem);

        usunItem.setText("Usuń kontakt");
        usunItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunItemActionPerformed(evt);
            }
        });
        jMenu1.add(usunItem);

        wyjdzItem.setText("Zakończ");
        wyjdzItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyjdzItemActionPerformed(evt);
            }
        });
        jMenu1.add(wyjdzItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Pomoc");


        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );

        pack();
    }

    private void rozmowaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rozmowaItemActionPerformed
    	controller.showConversationFrame();
    }//GEN-LAST:event_rozmowaItemActionPerformed
    private void szukajItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_szukajItemActionPerformed
    	controller.showSearchFrame();
    }//GEN-LAST:event_szukajItemActionPerformed

    private void usunItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunItemActionPerformed
        controller.deleteContact();
    }//GEN-LAST:event_usunItemActionPerformed

    private void wyjdzItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyjdzItemActionPerformed
    	controller.logout();
    }//GEN-LAST:event_wyjdzItemActionPerformed


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JList contactList;
    private javax.swing.JMenuItem helpItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem rozmowaItem;
    private javax.swing.JMenuItem informacjeItem;
    private javax.swing.JMenuItem szukajItem;
    private javax.swing.JMenuItem usunItem;
    private javax.swing.JMenuItem wyjdzItem;
    // End of variables declaration//GEN-END:variables

}
