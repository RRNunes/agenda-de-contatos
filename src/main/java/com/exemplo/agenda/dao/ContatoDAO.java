package com.exemplo.agenda.dao;

import com.exemplo.agenda.model.Contato;
import com.exemplo.agenda.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void adicionarContato(Contato contato) throws SQLException {
        String sql = "INSERT INTO Contato (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<Contato> listarContatos() throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM Contato";

        try (Connection conn = ConexaoBanco.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contatos.add(contato);
            }
        }
        return contatos;
    }

    // Implementar métodos de atualizar e remover se necessário
}
