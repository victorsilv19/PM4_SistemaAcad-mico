package com.sistema.gui;

import com.sistema.dao.AdminDAO;
import com.sistema.dao.AlunoDAO;
import javax.swing.*;
import java.awt.event.*;
import com.sistema.dao.ProfessorDAO;
import com.sistema.model.Admin;
import com.sistema.model.Aluno;
import com.sistema.model.Professor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JTextField matriculaField;
    private JPasswordField senhaField;
    private JComboBox<String> tipoUsuarioBox;

    public TelaLogin() {
        setTitle("Login - Sistema Acadêmico");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaLabel.setBounds(30, 30, 100, 25);
        add(matriculaLabel);

        matriculaField = new JTextField();
        matriculaField.setBounds(130, 30, 150, 25);
        add(matriculaField); 

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(30, 70, 100, 25);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(130, 70, 150, 25);
        add(senhaField);

        JLabel tipoUsuarioLabel = new JLabel("Tipo:");
        tipoUsuarioLabel.setBounds(30, 110, 100, 25);
        add(tipoUsuarioLabel);

        tipoUsuarioBox = new JComboBox<>(new String[]{"Aluno", "Professor", "Admin"});
        tipoUsuarioBox.setBounds(130, 110, 150, 25);
        add(tipoUsuarioBox);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(140, 230, 120, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                String senha = new String(senhaField.getPassword());
                String tipo = (String) tipoUsuarioBox.getSelectedItem();

                if (tipo.equals("Aluno")) {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    Aluno aluno = alunoDAO.autenticar(matricula, senha);
                    if (aluno != null) {
                        new TelaAluno(aluno).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login de aluno inválido");
                    }
                } else if (tipo.equals("Professor")) {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    Professor professor = professorDAO.autenticar(matricula, senha);
                    if (professor != null) {
                        new TelaProfessor(professor).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login de professor inválido");
                    }
                } else if (tipo.equals("Admin")) {
                    AdminDAO adminDAO = new AdminDAO();
                    Admin admin = adminDAO.autenticar(matricula, senha);  // aqui pode mudar para "usuario" se preferir
                    if (admin != null) {
                        new TelaAdmin().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login de admin inválido");
                    }
                }

            }
        });

        
        tipoUsuarioBox.addActionListener(e ->{
        	String tipoSelecionado = (String) tipoUsuarioBox.getSelectedItem();
        	if ("Professor".equals(tipoSelecionado)) {
        		matriculaLabel.setText("Usuário:");
        	}else {
        		matriculaLabel.setText("Matrícula:");
        	}
        });
        
        
        tipoUsuarioBox.addActionListener(e -> {
            String tipoSelecionado = (String) tipoUsuarioBox.getSelectedItem();
            
            if ("Professor".equalsIgnoreCase(tipoSelecionado)) {
                matriculaLabel.setText("Nome:");
            } else if ("Admin".equalsIgnoreCase(tipoSelecionado)) {
                matriculaLabel.setText("Login Admin:");
            } else {
                matriculaLabel.setText("Matrícula:");
            }
        });

        
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
 