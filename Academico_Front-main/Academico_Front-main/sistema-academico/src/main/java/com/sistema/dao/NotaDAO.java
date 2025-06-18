package com.sistema.dao;

import com.sistema.model.Nota;
import com.sistema.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}