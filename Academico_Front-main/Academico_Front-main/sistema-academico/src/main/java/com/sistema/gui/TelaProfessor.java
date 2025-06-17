package com.sistema.gui;

import com.sistema.model.Professor;

import javax.swing.*;

public class TelaProfessor extends JFrame {

    public TelaProfessor(Professor professor) {
        setTitle("Área do Professor");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Olá, Prof. " + professor.getNome());
        welcomeLabel.setBounds(50, 50, 200, 25);
        add(welcomeLabel);
        
        JButton btnNotas = new JButton("Atribuir Notas");
        btnNotas.setBounds(100, 70, 200, 30);
        add(btnNotas);
        
        JButton btnChamada = new JButton("Fazer Chamada");
        btnChamada.setBounds(100, 110, 200, 30);
        add(btnChamada);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(100, 180, 200, 30);
        add(btnSair);

        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin(); // volta pra tela de login
        });

        setVisible(true);
    }
}
