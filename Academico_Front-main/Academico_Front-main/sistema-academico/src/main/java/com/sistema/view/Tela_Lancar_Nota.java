package com.sistema.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sistema.dao.AlunoDAO;
import com.sistema.dao.NotaDAO;
import com.sistema.model.Aluno;
import com.sistema.model.Nota;

public class Tela_Lancar_Nota extends JFrame {

    private JTextField campoBusca;
    private JButton btnBuscar;
    private JTable tabelaAlunos;
    private DefaultTableModel tabelaModel;

    private JTextField campoNota;
    private JTextArea campoObservacao;
    private JButton btnLancarNota;

    private int professorId;

    public Tela_Lancar_Nota(int professorId) {
        this.professorId = professorId;
        setLayout(new BorderLayout());
        
        

        // Painel de busca
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

        // Painel inferior para lançar nota
        JPanel painelInferior = new JPanel(new GridLayout(3, 2, 10, 10));
        painelInferior.setBorder(BorderFactory.createTitledBorder("Lançar Nota"));

        campoNota = new JTextField();
        campoObservacao = new JTextArea(3, 20);
        JScrollPane scrollObs = new JScrollPane(campoObservacao);
        btnLancarNota = new JButton("Lançar Nota");

        painelInferior.add(new JLabel("Nota:"));
        painelInferior.add(campoNota);
        painelInferior.add(new JLabel("Observações:"));
        painelInferior.add(scrollObs);
        painelInferior.add(new JLabel()); // Espaço vazio
        painelInferior.add(btnLancarNota);

        add(painelInferior, BorderLayout.SOUTH);

        // Ação do botão buscar
        btnBuscar.addActionListener(e -> atualizarTabela(campoBusca.getText()));

        // Ação do botão lançar nota
        btnLancarNota.addActionListener(e -> lancarNota());
    }

    private void atualizarTabela(String termo) {
        tabelaModel.setRowCount(0); // limpa a tabela
        AlunoDAO dao = new AlunoDAO();
        List<Aluno> alunos = dao.buscarPorTermo(termo);

        for (Aluno a : alunos) {
            tabelaModel.addRow(new Object[]{a.getId(), a.getNome(), a.getMatricula(), a.getCurso()});
        }
    }

    private void lancarNota() {
        int linhaSelecionada = tabelaAlunos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela.");
            return;
        }

        try {
            int alunoId = (int) tabelaModel.getValueAt(linhaSelecionada, 0);
            String cursoNome = (String) tabelaModel.getValueAt(linhaSelecionada, 3); // nome do curso
            int cursoId = obterCursoIdPeloNome(cursoNome); // precisa existir

            double valorNota = Double.parseDouble(campoNota.getText());
            String observacao = campoObservacao.getText();

            Nota nota = new Nota();
            nota.setAlunoId(alunoId);
            nota.setCursoId(cursoId);
            nota.setProfessorId(professorId);
            nota.setNota(valorNota);
            nota.setObservacao(observacao);

            NotaDAO dao = new NotaDAO();
            dao.lancarNota(nota);

            JOptionPane.showMessageDialog(this, "Nota lançada com sucesso!");
            campoNota.setText("");
            campoObservacao.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor válido para a nota.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao lançar nota: " + e.getMessage());
        }
    }

    private int obterCursoIdPeloNome(String nomeCurso) {
        if (nomeCurso.equalsIgnoreCase("ADS")) return 1;
        if (nomeCurso.equalsIgnoreCase("Português")) return 2;
        // ...
        throw new RuntimeException("Curso não encontrado: " + nomeCurso);
    }
}
