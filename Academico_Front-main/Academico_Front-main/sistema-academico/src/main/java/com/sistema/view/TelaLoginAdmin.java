package com.sistema.view;

import com.sistema.dao.AdminDAO;
import com.sistema.model.Admin;

import javax.swing.*;

public class TelaLoginAdmin extends JFrame {
    public TelaLoginAdmin() {

        com.sistema.util.Tema.aplicarTemaDark();

        setTitle("Login Admin");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setBounds(30, 30, 100, 25);
        add(usuarioLabel);

        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(130, 30, 150, 25);
        add(usuarioField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(30, 70, 100, 25);
        add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(130, 70, 150, 25);
        add(senhaField);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(110, 120, 100, 25);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.autenticar(usuario, senha);
            if (admin != null) {
                new TelaAdmin().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login de admin inválido");
            }
        });
    }
}