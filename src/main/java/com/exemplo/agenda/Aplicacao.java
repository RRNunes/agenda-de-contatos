package com.exemplo.agenda;

import com.exemplo.agenda.dao.ContatoDAO;
import com.exemplo.agenda.model.Contato;
import com.exemplo.agenda.util.ConexaoBanco;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        ConexaoBanco.inicializarBanco();
        ContatoDAO contatoDAO = new ContatoDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Listar contatos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                if (opcao == 1) {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    contatoDAO.adicionarContato(new Contato(nome, telefone, email));
                    System.out.println("Contato adicionado com sucesso.");
                } else if (opcao == 2) {
                    List<Contato> contatos = contatoDAO.listarContatos();
                    contatos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
                } else if (opcao == 3) {
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
