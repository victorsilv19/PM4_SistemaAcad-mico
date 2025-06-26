package com.sistema.view;

import com.sistema.dao.AlunoDAO;
import com.sistema.dao.CursoDAO;
import com.sistema.dao.NotaDAO;
import com.sistema.model.Aluno;
import com.sistema.model.Nota;
import com.sistema.model.Professor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tela_Lancar_Nota extends JPanel {

    private JTextField campoBusca;
    private JTable tabelaAlunos;
    private DefaultTableModel tabelaModel;

    private JTextField campoNota;
    private JTextArea campoObservacao;
    private JButton btnBuscar;
    private JButton btnLancarNota;

    private Professor professor;
    private List<Aluno> alunosDoProfessor = new ArrayList<>();

    public Tela_Lancar_Nota(Professor professor) {
        this.professor = professor;

        setLayout(new BorderLayout());

        // Painel superior - Busca
        JPanel painelBusca = new JPanel(new FlowLayout());
        campoBusca = new JTextField(20);
        btnBuscar = new JButton("Buscar");

        painelBusca.add(new JLabel("Buscar Aluno:"));
        painelBusca.add(campoBusca);
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        // Tabela de alunos
        tabelaModel = new DefaultTableModel(new String[]{"ID", "Nome", "Matrícula", "Curso"}, 0);
        tabelaAlunos = new JTable(tabelaModel);
        JScrollPane scrollTabela = new JScrollPane(tabelaAlunos);
        add(scrollTabela, BorderLayout.CENTER);

        // Painel inferior - Lançar Nota
        JPanel painelNota = new JPanel(new GridLayout(3, 2, 10, 10));
        painelNota.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Lançar Nota", TitledBorder.LEFT, TitledBorder.TOP));

        campoNota = new JTextField();
        campoObservacao = new JTextArea(3, 20);
        JScrollPane scrollObs = new JScrollPane(campoObservacao);
        btnLancarNota = new JButton("Lançar Nota");

        painelNota.add(new JLabel("Nota:"));
        painelNota.add(campoNota);
        painelNota.add(new JLabel("Observações:"));
        painelNota.add(scrollObs);
        painelNota.add(new JLabel()); // espaço vazio
        painelNota.add(btnLancarNota);

        add(painelNota, BorderLayout.SOUTH);

        // Carrega todos os alunos vinculados ao professor
        carregarAlunosDoProfessor();

        // Botão buscar
        btnBuscar.addActionListener(e -> filtrarTabela(campoBusca.getText()));

        // Botão lançar nota
        btnLancarNota.addActionListener(e -> lancarNota());
    }

    private void carregarAlunosDoProfessor() {
        AlunoDAO alunoDAO = new AlunoDAO();
        // Temporário, só para testar
alunosDoProfessor = new AlunoDAO().listarTodos();
;

        atualizarTabela(alunosDoProfessor);
    }

    private void atualizarTabela(List<Aluno> lista) {
        tabelaModel.setRowCount(0);
        for (Aluno a : lista) {
            tabelaModel.addRow(new Object[]{a.getId(), a.getNome(), a.getMatricula(), a.getCurso()});
        }
    }

    private void filtrarTabela(String termo) {
        if (termo.isEmpty()) {
            atualizarTabela(alunosDoProfessor);
        } else {
            List<Aluno> filtrados = new ArrayList<>();
            for (Aluno a : alunosDoProfessor) {
                if (a.getNome().toLowerCase().contains(termo.toLowerCase()) ||
                    a.getMatricula().toLowerCase().contains(termo.toLowerCase())) {
                    filtrados.add(a);
                }
            }
            atualizarTabela(filtrados);
        }
    }

    private void lancarNota() {
        int linhaSelecionada = tabelaAlunos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno.");
            return;
        }

        try {
            int alunoId = (int) tabelaModel.getValueAt(linhaSelecionada, 0);
            String cursoNome = (String) tabelaModel.getValueAt(linhaSelecionada, 3);

            CursoDAO cursoDAO = new CursoDAO();
            int cursoId = cursoDAO.getCursoIdPorNome(cursoNome);

            double notaValor = Double.parseDouble(campoNota.getText());
            String observacao = campoObservacao.getText();

            Nota nota = new Nota();
            nota.setAlunoId(alunoId);
            nota.setCursoId(cursoId);
            nota.setProfessorId(professor.getId());
            nota.setNota(notaValor);
            nota.setObservacao(observacao);

            NotaDAO notaDAO = new NotaDAO();
            notaDAO.lancarNota(nota);

            JOptionPane.showMessageDialog(this, "Nota lançada com sucesso!");
            campoNota.setText("");
            campoObservacao.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nota inválida.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao lançar nota: " + ex.getMessage());
        }
    }
}
