package com.sistema.gui;

import com.sistema.dao.CursoDAO;
import com.sistema.model.Curso;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroCurso extends JPanel {

    private JTextField nomeField;
    private JTextField codigoField;
    private JButton cadastrarButton;

    public TelaCadastroCurso() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Cadastro de Curso"));

        JLabel nomeLabel = new JLabel("Nome do Curso:");
        nomeField = new JTextField();

        JLabel codigoLabel = new JLabel("Código do Curso:");
        codigoField = new JTextField();

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setForeground(new Color(0, 0, 0));
        cadastrarButton.setBackground(new Color(255, 255, 255));

        add(nomeLabel);
        add(nomeField);
        add(codigoLabel);
        add(codigoField);
        add(new JLabel());
        add(cadastrarButton);

        cadastrarButton.addActionListener(e -> cadastrarCurso());
    }

    private void cadastrarCurso() {
        String nome = nomeField.getText();
        String codigo = codigoField.getText();

        if (nome.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setCodigo(codigo);

        CursoDAO dao = new CursoDAO();
        dao.adicionarCurso(curso);

        JOptionPane.showMessageDialog(this, "Curso cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        nomeField.setText("");
        codigoField.setText("");
    }
}
