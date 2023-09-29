package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tarefa;
import model.Tarefa.Status;

public class TarefaDAO {
	private String url = "jdbc:mysql://localhost:3306/projeto_revisao";
	private String usuario = "root";
	private String senha = "aluno";
	private Connection connection;
	private ArrayList<Tarefa> listaDeTarefas;

	public TarefaDAO() {
		listaDeTarefas = new ArrayList<Tarefa>();
	}
	
	public void addTarefa(Tarefa tarefa) {
		if (abreConexao()) {
			try (Connection c = DriverManager.getConnection(url, usuario, senha)) {
				String sql = "INSERT INTO tarefa(titulo,descricao,status) VALUES(?,?,?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, tarefa.getTitulo());
				ps.setString(2, tarefa.getDescricao());
				ps.setString(3, tarefa.getStatus().name());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Tarefa> exibir() {
		ArrayList<Tarefa> tarefa = new ArrayList<>();
		if (abreConexao()) {
			try {
				String sql = "SELECT * FROM tarefa;";
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id_tarefa");
					String titulo = rs.getString("titulo");
					String descricao = rs.getString("descricao");
					Status status = Status.valueOf(rs.getString("status"));
					Tarefa lista = new Tarefa(id, titulo, descricao, status);
					tarefa.add(lista);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tarefa;
	}

	public boolean abreConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, usuario, senha);

			if (connection != null) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void finalizar(int id) {
		Status status = Tarefa.Status.C;
		for (Tarefa i : exibir())
			if (i.getId_Tarefa() == id)
				if (abreConexao()) {
					try {
						String sql = "UPDATE tarefa SET status = ? WHERE id_tarefa = ?;";

						PreparedStatement ps = connection.prepareStatement(sql);

						ps.setString(1, status.name());
						ps.setInt(2, id);
						ps.execute();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	}

}
