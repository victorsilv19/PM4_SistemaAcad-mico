package com.sistema.gui;

import com.sistema.dao.AlunoDAO;
import com.sistema.model.Aluno;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroAluno extends JPanel {

    private JTextField nomeField;
    private JTextField matriculaField;
    private JTextField cursoField;
    private JPasswordField senhaField;
    private JButton cadastrarButton;

    public TelaCadastroAluno() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Cadastro de Aluno"));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaField = new JTextField();

        JLabel cursoLabel = new JLabel("Curso:");
        cursoField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        cadastrarButton = new JButton("Cadastrar");

        // Adiciona os componentes ao painel
        add(nomeLabel);
        add(nomeField);
        add(matriculaLabel);
        add(matriculaField);
        add(cursoLabel);
        add(cursoField);
        add(senhaLabel);
        add(senhaField);
        add(new JLabel()); // espaço vazio
        add(cadastrarButton);

        // Ação do botão de cadastro
        cadastrarButton.addActionListener(e -> cadastrarAluno());
    }

    private void cadastrarAluno() {
        String nome = nomeField.getText();
        String matricula = matriculaField.getText();
        String curso = cursoField.getText();
        String senha = new String(senhaField.getPassword());

        if (nome.isEmpty() || matricula.isEmpty() || curso.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setCurso(curso);
        aluno.setSenha(senha);

        AlunoDAO dao = new AlunoDAO();
        dao.adicionarAluno(aluno);

        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Limpa os campos
        nomeField.setText("");
        matriculaField.setText("");
        cursoField.setText("");
        senhaField.setText("");
    }
}
