/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegisterView.java
 *
 * Created on 2011-02-11, 00:06:23
 */

package messenger.views;

import javax.swing.JOptionPane;

import messenger.controller.MainAppController;
import messenger.controller.UserController;

public class RegisterView extends javax.swing.JFrame {

	private MainAppController controller;

	/**
	 * Creates new form RegisterView
	 * 
	 * @param userController
	 */
	public RegisterView(MainAppController mainAppController) {
		initComponents();
		controller = mainAppController;
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		nameField = new javax.swing.JTextField();
		passwordField = new javax.swing.JPasswordField();
		registerButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Nazwa użytkownika");

		jLabel3.setText("Has�o");

		registerButton.setText("Rejestruj");
		registerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Anuluj");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel3))
																.addGap(12, 12,
																		12)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						passwordField)
																				.addComponent(
																						nameField,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						104,
																						Short.MAX_VALUE)))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		registerButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		77,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cancelButton,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)))
								.addContainerGap(23, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														nameField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(
														passwordField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(registerButton)
												.addComponent(cancelButton))
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_registerButtonActionPerformed
		String name = nameField.getText();
		char[] pass = passwordField.getPassword();
		String password = UserController.arrayToString(pass);
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Wype�nij wszystkie pola!",
					"B��d", JOptionPane.ERROR_MESSAGE);
			return;
		}
		controller.register(name, password);
	}// GEN-LAST:event_registerButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cancelButtonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// new RegisterView().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField nameField;
	private javax.swing.JPasswordField passwordField;
	private javax.swing.JButton registerButton;
	// End of variables declaration//GEN-END:variables

}
