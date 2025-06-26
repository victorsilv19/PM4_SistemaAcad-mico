package com.sistema.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.sistema.dao.CursoDAO;
import com.sistema.dao.NotaDAO;
import com.sistema.model.Nota;
import com.sistema.model.Professor;
import main.TelaSelecaoLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaProfessor extends JFrame {

    private Professor professor;

    public TelaProfessor(Professor professor) {
        this.professor = professor;

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Área do Professor");
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane(JTabbedPane.LEFT);

        abas.addTab("Home", criarAbaHome());
        abas.addTab("Lançar Nota", new Tela_Lancar_Nota(professor));
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

        StringBuilder texto = new StringBuilder();
        texto.append("Disciplinas atribuídas:\n");

        CursoDAO cursoDAO = new CursoDAO();
        List<String> disciplinas = cursoDAO.getDisciplinasPorProfessor(professor.getId());

        if (disciplinas.isEmpty()) {
            texto.append("- Nenhuma disciplina atribuída.\n");
        } else {
            for (String nome : disciplinas) {
                texto.append("- ").append(nome).append("\n");
            }
        }

        texto.append("\nNotas lançadas hoje:\n");

        NotaDAO notaDAO = new NotaDAO();
        List<Nota> notasHoje = notaDAO.getNotasLancadasHoje(professor.getId());

        if (notasHoje.isEmpty()) {
            texto.append("- Nenhuma nota lançada hoje.\n");
        } else {
            for (Nota n : notasHoje) {
                texto.append("- Aluno ID ").append(n.getAlunoId())
                      .append(" | Curso: ").append(getCursoNomePorId(n.getCursoId()))
                      .append(" | Nota: ").append(n.getNota())
                      .append("\n");
            }
        }

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

    private String getCursoNomePorId(int id) {
        switch (id) {
            case 1: return "ADS";
            case 2: return "Português";
            case 3: return "Matemática";
            default: return "Desconhecido";
        }
    }
}
