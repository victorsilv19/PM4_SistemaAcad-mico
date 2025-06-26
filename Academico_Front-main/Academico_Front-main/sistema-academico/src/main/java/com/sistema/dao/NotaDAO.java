package com.sistema.dao;

import com.sistema.util.Conexao;
import com.sistema.model.Nota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    public void lancarNota(Nota nota) {
        String sql = "INSERT INTO \"Nota\" (aluno_id, professor_id, curso_id, nota, observacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nota.getAlunoId());
            stmt.setInt(2, nota.getProfessorId());
            stmt.setInt(3, nota.getCursoId());
            stmt.setDouble(4, nota.getNota());
            stmt.setString(5, nota.getObservacao());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Retorna as últimas 3 notas lançadas para o aluno
    public List<Nota> getUltimasNotasPorAluno(int alunoId) {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM \"Nota\" WHERE aluno_id = ? ORDER BY id DESC LIMIT 3";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setNota(rs.getDouble("nota"));
                nota.setObservacao(rs.getString("observacao"));
                nota.setCursoId(rs.getInt("curso_id"));
                notas.add(nota);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notas;
    }

    // Retorna todas as notas do aluno
    public List<Nota> getNotasPorAluno(int alunoId) {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM \"Nota\" WHERE aluno_id = ? ORDER BY id DESC";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setNota(rs.getDouble("nota"));
                nota.setObservacao(rs.getString("observacao"));
                nota.setCursoId(rs.getInt("curso_id"));
                notas.add(nota);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notas;
    }

    public List<Nota> getNotasLancadasHoje(int professorId) {
    List<Nota> notas = new ArrayList<>();
    String sql = "SELECT * FROM \"Nota\" WHERE professor_id = ? AND data_lancamento::date = CURRENT_DATE";

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, professorId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Nota nota = new Nota();
            nota.setAlunoId(rs.getInt("aluno_id"));
            nota.setCursoId(rs.getInt("curso_id"));
            nota.setNota(rs.getDouble("nota"));
            nota.setObservacao(rs.getString("observacao"));
            notas.add(nota);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return notas;
}

}
