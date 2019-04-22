package sessao;

import entidades.Veiculo;
import persistencia.VeiculoDAOIF;

public interface AppFreteIF {
	public void addVeiculo(int capacidade, int latitude, int longitude, boolean tipo) throws Exception;
	public void setLocalizacao(int matricula, int latitude, int longitude);
	public void setVeiculoDAOIF(VeiculoDAOIF veiculoDAO);
	public Veiculo melhorFrete(int latitudeOrigem, int longitudeOrigem, int latitudeDestino, int longitudeDestino,
			int pesoCarga) throws Exception;
	public boolean ehInteiro(String s);
	public void atualizar(int matricula, int latitude, int longitude, int carga);
}
