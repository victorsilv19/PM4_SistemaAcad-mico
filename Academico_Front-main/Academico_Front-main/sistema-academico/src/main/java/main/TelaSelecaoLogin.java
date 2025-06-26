package main;

import javax.swing.*;
import java.awt.*;

import com.sistema.view.TelaLoginAdmin;
import com.sistema.view.TelaLoginAluno;
import com.sistema.view.TelaLoginProfessor;
import com.sistema.util.Tema;

public class TelaSelecaoLogin extends JFrame {

    public TelaSelecaoLogin() {
        // Aplica o tema escuro antes de criar qualquer componente
        Tema.aplicarTemaDark();

        setTitle("Selecione o tipo de usuário");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Define o fundo para combinar com o tema escuro
        getContentPane().setBackground(UIManager.getColor("Panel.background"));

        JButton btnAluno = new JButton("Login Aluno");
        JButton btnProfessor = new JButton("Login Professor");
        JButton btnAdmin = new JButton("Login Admin");

        btnAluno.setBounds(70, 20, 150, 40);
        btnProfessor.setBounds(70, 80, 150, 40);
        btnAdmin.setBounds(70, 140, 150, 40);

        add(btnAluno);
        add(btnProfessor);
        add(btnAdmin);

        btnAluno.addActionListener(e -> {
            new TelaLoginAluno().setVisible(true);
            dispose();
        });

        btnProfessor.addActionListener(e -> {
            new TelaLoginProfessor().setVisible(true);
            dispose();
        });

        btnAdmin.addActionListener(e -> {
            new TelaLoginAdmin().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        new TelaSelecaoLogin().setVisible(true);
    }
}
