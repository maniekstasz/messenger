/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConverstationView.java
 *
 * Created on 2011-02-11, 00:38:53
 */

package messenger.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import messenger.controller.UserController;
import messenger.model.User;


public class ConversationView extends javax.swing.JFrame {

	private UserController controller;
    private String conversationContent = "";
	private User interlocutor;

	/** Creates new form ConverstationView 
	 * @param userController */
    public ConversationView(UserController userController, User interlocutor) {
        initComponents();
        controller = userController;
        this.setLocationRelativeTo(null);
        this.interlocutor = interlocutor;
    }

    private class ExitAction extends WindowAdapter implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                    setVisible(false);
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                    setVisible(false);
            }
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        conversationPane = new javax.swing.JEditorPane();
        sendButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        conversationPane.setContentType("text/html");
        conversationPane.setEditable(false);
        scrollPane.setViewportView(conversationPane);

        sendButton.setText("Wy�lij");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        messageArea.setFont(new java.awt.Font("Tahoma", 0, 10));
        messageArea.setColumns(20);
        messageArea.setRows(5);
        jScrollPane3.setViewportView(messageArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	String message = messageArea.getText();
    	messageArea.setText("");
    	controller.sendMessage(message, interlocutor);
        addMessage(message, controller.getUser().getUsername());
    }                                          

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ConverstationView().setVisible(true);
            }
        });
    }
    
    public void addMessage(String message, String senderName) {
    	conversationContent += "<p style=\"font-weight: bold; margin: 0px;\">"
                + senderName + "</p>";
    	conversationContent += "<p style=\"margin: 0px;\">" + message + "</p>";
    	conversationContent += "<hr style=\"margin-bottom: 3px;\">";
        conversationPane.setText(conversationContent);
    }
    
    // Variables declaration - do not modify
    private javax.swing.JEditorPane conversationPane;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton sendButton;
    // End of variables declaration

}


