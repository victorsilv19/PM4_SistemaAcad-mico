package com.sistema.dao;

import com.sistema.model.Professor;
import com.sistema.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    public Professor autenticar(String loginInput, String senha) {
    	String sql = "SELECT * FROM \"Professor\" WHERE loginInput = ? AND senha = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loginInput);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("loginInput")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return null;
    }
    
    public void adicionarProfessor(Professor professor) {
    	String sql = "INSERT INTO \"Professor\" (nome, loginInput, disciplina, senha) VALUES (?, ?, ?, ?)";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getLoginInput());
            stmt.setString(3, professor.getDisciplina());
            stmt.setString(4, professor.getSenha());  // aqui pega direto do objeto

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
