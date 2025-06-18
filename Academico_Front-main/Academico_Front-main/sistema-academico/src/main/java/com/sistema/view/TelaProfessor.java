package com.sistema.view;

import com.sistema.model.Professor;

import main.TelaLogin;

import javax.swing.*;

public class TelaProfessor extends JFrame {
	public TelaProfessor(Professor professor) {
	    setTitle("Área do Professor");
	    setSize(400, 300);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(null); // Cuidado com layout absoluto!

	    // Posicione os componentes manualmente (coordenadas x, y, largura, altura)
	    JLabel welcomeLabel = new JLabel("Olá, Prof. " + professor.getNome());
	    welcomeLabel.setBounds(50, 20, 300, 25);
	    add(welcomeLabel);
	    
	    JButton btnNotas = new JButton("Atribuir Notas");
	    btnNotas.setBounds(50, 60, 200, 30);
	    btnNotas.addActionListener(e -> {
	        new Tela_Lancar_Nota(professor.getId()).setVisible(true);
	    });
	    add(btnNotas);
	    
	    JButton btnChamada = new JButton("Fazer Chamada");
	    btnChamada.setBounds(50, 100, 200, 30);
	    add(btnChamada);
	    
	    JButton btnSair = new JButton("Sair");
	    btnSair.setBounds(50, 180, 200, 30);
	    btnSair.addActionListener(e -> {
	        dispose();
	        new TelaLogin();
	    });
	    add(btnSair);

	    setVisible(true); // Importante!
	}
}
