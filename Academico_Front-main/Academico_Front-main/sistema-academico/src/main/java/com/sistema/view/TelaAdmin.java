package com.sistema.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.sistema.dao.AlunoDAO;
import com.sistema.dao.CursoDAO;
import com.sistema.dao.ProfessorDAO;
import main.TelaSelecaoLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaAdmin extends JFrame {

    public TelaAdmin() {
        // Aplica tema escuro FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Área Administrativa");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane(JTabbedPane.LEFT);

        abas.addTab("Home", criarAbaHome());
        abas.addTab("Cadastrar Aluno", new TelaCadastroAluno());
        abas.addTab("Cadastrar Professor", new TelaCadastroProfessor());
        abas.addTab("Cadastrar Curso", new TelaCadastroCurso());
        abas.addTab("Sair", criarAbaSair());

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
                    dispose();
                    new TelaSelecaoLogin().setVisible(true);
                } else {
                    abas.setSelectedIndex(0);
                }
            }
        });
    }

    private JPanel criarAbaHome() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        CursoDAO cursoDAO = new CursoDAO();

        StringBuilder texto = new StringBuilder();

        texto.append("Total de alunos: ").append(alunoDAO.contarAlunos()).append("\n");
        texto.append("Total de professores: ").append(professorDAO.contarProfessores()).append("\n");
        texto.append("Total de cursos: ").append(cursoDAO.contarCursos()).append("\n\n");

        texto.append("Último aluno cadastrado: ").append(alunoDAO.getUltimoAlunoCadastrado()).append("\n");

        area.setText(texto.toString());
        painel.add(new JScrollPane(area), BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarAbaSair() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(50, 50, 50, 50));
        painel.add(new JLabel("Clique na aba para sair.", SwingConstants.CENTER), BorderLayout.CENTER);
        return painel;
    }
}
