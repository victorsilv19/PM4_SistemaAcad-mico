package com.sistema.view;

import com.sistema.dao.AlunoDAO;
import com.sistema.model.Aluno;

import javax.swing.*;

public class TelaLoginAluno extends JFrame {
    public TelaLoginAluno() {

        com.sistema.util.Tema.aplicarTemaDark();

        setTitle("Login Aluno");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaLabel.setBounds(30, 30, 100, 25);
        add(matriculaLabel);

        JTextField matriculaField = new JTextField();
        matriculaField.setBounds(130, 30, 150, 25);
        add(matriculaField);

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
            String matricula = matriculaField.getText();
            String senha = new String(senhaField.getPassword());
            AlunoDAO alunoDAO = new AlunoDAO();
            Aluno aluno = alunoDAO.autenticar(matricula, senha);
            if (aluno != null) {
                new TelaAluno(aluno).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login de aluno inválido");
            }
        });
    }
}