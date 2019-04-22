package sessao;

import java.util.ArrayList;
import entidades.Veiculo;
import entidades.Drone;
import entidades.Terrestre;
import persistencia.DaoFactory;
import persistencia.VeiculoDAOIF;

public class AppFrete implements AppFreteIF{
	private VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
	
	public AppFrete(VeiculoDAOIF veiculoDao) {
		super();
		this.veiculoDao = veiculoDao;
	}

	@Override
	public void addVeiculo(int capacidade, int latitude, int longitude, boolean tipo) throws Exception {
		Veiculo veiculo;
		if(tipo){
			veiculo = new Terrestre(capacidade, latitude, longitude);
		}else {
			veiculo = new Drone(capacidade, latitude, longitude);
		}
		this.veiculoDao.insert(veiculo);
		
	}

	public void setVeiculoDAOIF(VeiculoDAOIF veiculoDAO){
		this.veiculoDao = veiculoDAO;
	}
	
	@Override
	public void setLocalizacao(int matricula, int latitude, int longitude) {
		Veiculo veiculo = veiculoDao.findByMatricula(matricula);
				veiculo.setLatitude(latitude);
				veiculo.setLongitude(longitude);
		veiculoDao.update(veiculo);
	}

	@Override
	public Veiculo melhorFrete(int latitudeOrigem, int longitudeOrigem, int latitudeDestino, int longitudeDestino, int pesoCarga) throws Exception {
		ArrayList<Veiculo> veiculos = this.findByCapacidade(pesoCarga);
		
		if(veiculos==null)
			return null;
		
		double melhorFrete = Double.MAX_VALUE;
		int matriculaMelhorFrete = 0;
		
		for (Veiculo v : veiculos) {
			double valor = v.CustoFrete(latitudeOrigem, longitudeOrigem, latitudeDestino, longitudeDestino, pesoCarga);
			if(valor < melhorFrete) {
				melhorFrete = valor;
				matriculaMelhorFrete = v.getMatricula();
			}
		}
			return veiculoDao.findByMatricula(matriculaMelhorFrete);
	}
	
	public boolean ehInteiro(String s) {
	    char[] c = s.toCharArray();
	    boolean d = true;
	    for ( int i = 0; i < c.length; i++ ) {
	        if ( !Character.isDigit( c[ i ] ) ) {
	            d = false;
	            break;
	        }
	    }
	    return d;
	}

	public ArrayList<Veiculo> findByCapacidade(int capacidade) throws Exception {
		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculos = veiculoDao.findByCapacidade(capacidade);
		return veiculos;
	}

	@Override
	public void atualizar(int matricula, int latitude, int longitude, int capacidade){
		
		    Veiculo veiculo = veiculoDao.findByMatricula(matricula);
					veiculo.setCapacidade(capacidade);
					veiculo.setLatitude(latitude);
					veiculo.setLongitude(longitude);
	
			this.veiculoDao.update(veiculo);
			
	}
		
}
