package view;

import java.util.List;
import java.util.Scanner;

import model.Tarefa;

public class TarefaView {
	private Scanner scanner;

	public TarefaView() {
		scanner = new Scanner(System.in);
	}
	
	public void exibirTarefas(List<Tarefa> tarefa) {
		System.out.println("------lista de tarefas------");
		System.out.println("");
		for (Tarefa tf : tarefa) {
			System.out.println("Id:"+tf.getId_Tarefa()+"\nTitulo:"+ tf.getTitulo() +"\nDescricao: " + tf.getDescricao() + "\nStatus: " + tf.getStatus().name());
			System.out.println("---------------------------");
		}
	}

	public int menu() {
		System.out.println("-----menu--------");
		System.out.println("1. Adiciona tarefa");
		System.out.println("2. Atualiza tarefa");
		System.out.println("3. Finaliza tarefa");
		System.out.println("4. Lista tarefa");
		System.out.println("5. Exclui tarefa");
		System.out.println("0. Sair");
		System.out.print("Escolha uma opção: ");
		return scanner.nextInt();
	}
	
	public void saida() {
		System.err.println("Saindo...");
	}
	
	public String titulo() {
		System.out.println("titulo: ");
		scanner.nextLine();
		return scanner.nextLine();
	}
	
	public String descricao() {
		System.out.println("descricao: ");
		return scanner.nextLine();
	}
	
	public int id() {
		System.out.println("ID da Tarefa:");
		return scanner.nextInt();
	}
	

}
