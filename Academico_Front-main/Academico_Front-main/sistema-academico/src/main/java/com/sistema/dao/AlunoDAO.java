package com.sistema.dao;

import com.sistema.model.Aluno;
import com.sistema.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // Adiciona um novo aluno no banco
    public void adicionarAluno(Aluno aluno) {
    	String sql = "INSERT INTO \"Aluno\" (nome, loginInput, curso, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getSenha());  
            stmt.executeUpdate();

            System.out.println("Aluno adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os alunos
    public void listarAlunos() {
        String sql = "SELECT * FROM \"Aluno\"";

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
    
    public List<Aluno> buscarPorTermo(String termo) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM \"Aluno\" WHERE nome ILIKE ? OR loginInput ILIKE ? OR curso ILIKE ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String likeTerm = "%" + termo + "%";
            stmt.setString(1, likeTerm);
            stmt.setString(2, likeTerm);
            stmt.setString(3, likeTerm);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("loginInput"));
                aluno.setCurso(rs.getString("curso"));
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public List<Aluno> listarTodos() {
    List<Aluno> lista = new ArrayList<>();
    String sql = "SELECT * FROM \"Aluno\"";

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Aluno a = new Aluno();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setMatricula(rs.getString("loginInput"));
            a.setCurso(rs.getString("curso"));
            lista.add(a);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

    public int contarAlunos() {
    String sql = "SELECT COUNT(*) FROM \"Aluno\"";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) return rs.getInt(1);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public String getUltimoAlunoCadastrado() {
    String sql = "SELECT nome FROM \"Aluno\" ORDER BY id DESC LIMIT 1";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) return rs.getString("nome");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "Nenhum aluno cadastrado";
}

public List<Aluno> listarAlunosPorProfessor(int professorId) {
    List<Aluno> alunos = new ArrayList<>();

    String sqlCursos = "SELECT nome FROM \"Curso\" WHERE id IN (SELECT curso_id FROM \"ProfessorCurso\" WHERE professor_id = ?)";

    List<String> nomesCursos = new ArrayList<>();

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmtCursos = conn.prepareStatement(sqlCursos)) {

        stmtCursos.setInt(1, professorId);
        ResultSet rsCursos = stmtCursos.executeQuery();

        while (rsCursos.next()) {
            nomesCursos.add(rsCursos.getString("nome"));
        }

        if (nomesCursos.isEmpty()) {
            // Professor não tem cursos, retorna lista vazia
            return alunos;
        }

        // Montar string para IN (?, ?, ...)
        StringBuilder inClause = new StringBuilder();
        for (int i = 0; i < nomesCursos.size(); i++) {
            inClause.append("?");
            if (i < nomesCursos.size() - 1) {
                inClause.append(",");
            }
        }

        String sqlAlunos = "SELECT * FROM \"Aluno\" WHERE curso IN (" + inClause.toString() + ")";

        try (PreparedStatement stmtAlunos = conn.prepareStatement(sqlAlunos)) {
            for (int i = 0; i < nomesCursos.size(); i++) {
                stmtAlunos.setString(i + 1, nomesCursos.get(i));
            }

            ResultSet rsAlunos = stmtAlunos.executeQuery();

            while (rsAlunos.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rsAlunos.getInt("id"));
                aluno.setNome(rsAlunos.getString("nome"));
                aluno.setMatricula(rsAlunos.getString("loginInput"));
                aluno.setCurso(rsAlunos.getString("curso"));
                alunos.add(aluno);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return alunos;
}



}





    

