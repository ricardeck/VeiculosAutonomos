package persistencia;

import java.util.ArrayList;
import entidades.Veiculo;

public interface VeiculoDAOIF {
	public void insert(Veiculo atividade) throws Exception;
	public ArrayList<Veiculo> findAll() throws Exception;
	public ArrayList<Veiculo> findByCapacidade(int capacidade) throws Exception;
	public Veiculo findByMatricula(int matricula);
	public void update(Veiculo veiculo);

}
