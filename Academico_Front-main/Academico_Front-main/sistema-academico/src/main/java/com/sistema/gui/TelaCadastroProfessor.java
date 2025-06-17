package com.sistema.gui;

import com.sistema.dao.ProfessorDAO;
import com.sistema.model.Professor;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroProfessor extends JPanel {

    private JTextField nomeField;
    private JTextField loginField;
    private JTextField disciplinaField;
    private JPasswordField senhaField;
    private JButton cadastrarButton;

    public TelaCadastroProfessor() {
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Cadastro de Professor"));

        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();

        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField();

        JLabel disciplinaLabel = new JLabel("Disciplina:");
        disciplinaField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        cadastrarButton = new JButton("Cadastrar");

        add(nomeLabel); add(nomeField);
        add(loginLabel); add(loginField); // NOVO
        add(disciplinaLabel); add(disciplinaField);
        add(senhaLabel); add(senhaField);
        add(new JLabel()); add(cadastrarButton);


        cadastrarButton.addActionListener(e -> cadastrarProfessor());
    }

    private void cadastrarProfessor() {
    	String nome = nomeField.getText();
    	String login = loginField.getText(); // NOVO
    	String disciplina = disciplinaField.getText();
    	String senha = new String(senhaField.getPassword());

    	if (nome.isEmpty() || login.isEmpty() || disciplina.isEmpty() || senha.isEmpty()) {
    	    JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
    	    return;
    	}

    	Professor professor = new Professor();
    	professor.setNome(nome);
    	professor.setLoginInput(login); // NOVO
    	professor.setDisciplina(disciplina);
    	professor.setSenha(senha);

    	ProfessorDAO dao = new ProfessorDAO();
    	dao.adicionarProfessor(professor);

    	JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    	nomeField.setText("");
    	loginField.setText(""); // NOVO
    	disciplinaField.setText("");
    	senhaField.setText("");

    }
}
