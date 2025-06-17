package com.sistema.gui;

import javax.swing.*;

public class TelaAdmin extends JFrame {

	public TelaAdmin() {
	    setTitle("Área Administrativa");
	    setSize(800, 600);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);

	    JTabbedPane abas = new JTabbedPane();

	    abas.add("Cadastrar Aluno", new TelaCadastroAluno());
	    abas.add("Cadastrar Professor", new TelaCadastroProfessor());
	    abas.add("Cadastrar Curso", new TelaCadastroCurso());
	    abas.add("Sair", new JPanel()); // Aba de sair

	    add(abas);

	    abas.addChangeListener(e -> {
	        if (abas.getSelectedIndex() == abas.indexOfTab("Sair")) {
	            int confirm = JOptionPane.showConfirmDialog(
	                this,
	                "Deseja realmente sair?",
	                "Confirmar saída",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (confirm == JOptionPane.YES_OPTION) {
	                dispose(); // Fecha a janela atual
	                new TelaLogin().setVisible(true); // Volta para a tela de login
	            } else {
	                abas.setSelectedIndex(0); // Volta para a primeira aba
	            }
	        }
	    });
	}

}
