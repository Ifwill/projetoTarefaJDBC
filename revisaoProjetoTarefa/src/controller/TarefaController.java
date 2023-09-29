package controller;

import java.io.ObjectInputFilter.Status;

import dao.TarefaDAO;
import model.Tarefa;
import view.TarefaView;

public class TarefaController {
	private TarefaView tarefaView;
	private TarefaDAO tarefaDAO;

	public TarefaController() {
		tarefaView = new TarefaView();
		tarefaDAO = new TarefaDAO();
	}

	public void start() {
		int op = 0;
		do {

			switch (op = tarefaView.menu()) {
			case 1:
				addTarefa();
				break;
			case 2:
				
				break;
			case 3:
				concluido();
				break;
			case 4:
				exibir();
				break;

			}
		} while (op != 0);
		tarefaView.saida();
	}

	public void addTarefa() {
		String titulo = tarefaView.titulo();
		String descricao = tarefaView.descricao();
		Tarefa tarefa = new Tarefa(titulo, descricao, Tarefa.Status.A);
		tarefaDAO.addTarefa(tarefa);
	}

	public void exibir() {
		tarefaView.exibirTarefas(tarefaDAO.exibir());
	}

	public void concluido() {
		exibir();
		int id = tarefaView.id();
		tarefaDAO.finalizar(id);
	}


}
