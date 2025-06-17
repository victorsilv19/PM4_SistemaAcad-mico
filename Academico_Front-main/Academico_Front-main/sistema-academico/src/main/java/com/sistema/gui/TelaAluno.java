package com.sistema.gui;

import com.sistema.model.Aluno;

import javax.swing.*;
import java.awt.event.*;

public class TelaAluno extends JFrame {
    private Aluno aluno;

    public TelaAluno(Aluno aluno) {
        this.aluno = aluno;

        setTitle("Área do Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel saudacao = new JLabel("Bem-vindo, " + aluno.getNome());
        saudacao.setBounds(20, 20, 300, 25);
        add(saudacao);

        JButton btnNotas = new JButton("Ver Notas");
        btnNotas.setBounds(100, 70, 200, 30);
        add(btnNotas);

        JButton btnPresencas = new JButton("Ver Presenças");
        btnPresencas.setBounds(100, 110, 200, 30);
        add(btnPresencas);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(100, 180, 200, 30);
        add(btnSair);

        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin(); // volta pra tela de login
        });

        // Aqui você pode adicionar eventos nos botões para abrir novas telas ou mostrar dados

        setVisible(true);
    }
}
