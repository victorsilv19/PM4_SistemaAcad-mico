package com.sistema.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.sistema.dao.NotaDAO;
import com.sistema.model.Aluno;
import com.sistema.model.Nota;
import main.TelaSelecaoLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaAluno extends JFrame {

    private Aluno aluno;

    public TelaAluno(Aluno aluno) {
        this.aluno = aluno;

        // Tema escuro FlatDarkLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Área do Aluno");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane abas = new JTabbedPane(JTabbedPane.LEFT);

        abas.addTab("Home", criarAbaHome(aluno));
        abas.addTab("Ver Notas", criarAbaVerNotas(aluno));
        abas.addTab("Ver Presenças", criarAbaPresencas());
        abas.addTab("Sair", criarAbaSair());

        add(abas);

        // Ação de sair
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

    private JPanel criarAbaHome(Aluno aluno) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel saudacao = new JLabel("Bem-vindo, " + aluno.getNome(), SwingConstants.CENTER);
        saudacao.setFont(new Font("Segoe UI", Font.BOLD, 18));

        NotaDAO notaDAO = new NotaDAO();
        List<Nota> ultimasNotas = notaDAO.getUltimasNotasPorAluno(aluno.getId());

        StringBuilder texto = new StringBuilder();
        texto.append("Curso: ").append(aluno.getCurso()).append("\n\nÚltimas notas:\n");

        if (ultimasNotas.isEmpty()) {
            texto.append("Nenhuma nota cadastrada.");
        } else {
            for (Nota nota : ultimasNotas) {
                texto.append("- Nota: ").append(nota.getNota())
                      .append(" | Obs: ").append(nota.getObservacao() == null ? "" : nota.getObservacao())
                      .append("\n");
            }
        }

        JTextArea areaResumo = new JTextArea(texto.toString());
        areaResumo.setEditable(false);
        areaResumo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        painel.add(saudacao, BorderLayout.NORTH);
        painel.add(new JScrollPane(areaResumo), BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarAbaVerNotas(Aluno aluno) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String[] colunas = {"Curso", "Nota", "Observação"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);

        NotaDAO dao = new NotaDAO();
        List<Nota> notas = dao.getNotasPorAluno(aluno.getId()); // método que retorna TODAS as notas

        for (Nota nota : notas) {
            String curso = getCursoNomePorId(nota.getCursoId());
            model.addRow(new Object[]{curso, nota.getNota(), nota.getObservacao()});
        }

        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarAbaPresencas() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));
        painel.add(new JLabel("Presenças do aluno (em construção)", SwingConstants.CENTER), BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarAbaSair() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(new EmptyBorder(50, 50, 50, 50));
        painel.add(new JLabel("Clique na aba para sair.", SwingConstants.CENTER), BorderLayout.CENTER);
        return painel;
    }

    private String getCursoNomePorId(int cursoId) {
        switch (cursoId) {
            case 1: return "ADS";
            case 2: return "Português";
            case 3: return "Matemática";
            default: return "Desconhecido";
        }
    }
}
