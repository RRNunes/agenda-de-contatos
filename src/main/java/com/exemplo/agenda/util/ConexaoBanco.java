package com.exemplo.agenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/agenda_de_contatos";
    private static final String USER = "agenda_user";
    private static final String PASSWORD = "sua_senha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void inicializarBanco() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Contato (" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(100), " +
                "telefone VARCHAR(20), " +
                "email VARCHAR(100));";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreateTable);
            System.out.println("Tabela 'Contato' criada ou já existente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

