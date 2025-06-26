package com.sistema.dao;

import com.sistema.model.Curso;
import com.sistema.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void adicionarCurso(Curso curso) {
        String sql = "INSERT INTO \"Curso\" (nome, codigo) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM \"Curso\"";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setCodigo(rs.getString("codigo"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public List<String> getDisciplinasPorProfessor(int professorId) {
    List<String> disciplinas = new ArrayList<>();
    String sql = "SELECT nome FROM \"Curso\" WHERE id IN (SELECT curso_id FROM \"Nota\" WHERE professor_id = ?)";

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, professorId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            disciplinas.add(rs.getString("nome"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return disciplinas;
}


    public int contarCursos() {
    String sql = "SELECT COUNT(*) FROM \"Curso\"";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) return rs.getInt(1);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

    public int getCursoIdPorNome(String nomeCurso) {
    String sql = "SELECT id FROM \"Curso\" WHERE LOWER(nome) = LOWER(?)";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nomeCurso);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    throw new RuntimeException("Curso não encontrado: " + nomeCurso);
}


}
