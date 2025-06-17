package com.sistema.dao;

import com.sistema.model.Aluno;
import com.sistema.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

    // Adiciona um novo aluno no banco
    public void adicionarAluno(Aluno aluno) {
    	String sql = "INSERT INTO \"Aluno\" (nome, loginInput, curso, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getSenha());  // Assumindo que Aluno tem senha
            stmt.executeUpdate();

            System.out.println("Aluno adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os alunos
    public void listarAlunos() {
        String sql = "SELECT * FROM aluno";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Nome: " + rs.getString("nome") +
                    ", Matricula: " + rs.getString("loginInput") +
                    ", Curso: " + rs.getString("curso")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Autentica o aluno usando matrícula e senha
    public Aluno autenticar(String loginInput, String senha) {
    	String sql = "SELECT * FROM \"Aluno\" WHERE loginInput = ? AND senha = ?";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loginInput);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setMatricula(rs.getString("loginInput"));
                    aluno.setCurso(rs.getString("curso"));
                    aluno.setSenha(rs.getString("senha")); // caso queira manter a senha no objeto

                    return aluno;  // Aluno autenticado com sucesso
                } else {
                    return null;  // Não encontrou usuário
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
