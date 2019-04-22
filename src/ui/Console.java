package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import entidades.Veiculo;
import persistencia.DaoFactory;
import persistencia.VeiculoDAOIF;
import sessao.AppFrete;
import sessao.AppFreteIF;

public class Console implements VeiculosAutonomosUIIF{
	
	private VeiculoDAOIF veiculoDao = (VeiculoDAOIF) DaoFactory.createVeiculoDao();
	private AppFreteIF app = new AppFrete(veiculoDao);
	private BufferedReader br;
	
	public Console() throws ClassNotFoundException, IOException, SQLException {
		this.br = new  BufferedReader(new
				InputStreamReader(System.in));
	}
	
	public void MensagemDeErro(String msg) {
		System.out.println(msg);
		menu();
	}
	
	@Override
	public void exibir() throws Exception {
		int option;
		do {
			this.menu();
			option = Integer.parseInt(this.br.readLine());
			switch(option) {
				case 1 : this.addVeiculo(); break;
				case 2 : this.buscaMelhorFrete(); break;
			}
		}while (option > 0);
	}
	
	private void menu() {
		System.out.println("*******************");
		System.out.println("1 - ADICIONAR VEICULO");
		System.out.println("2 - BUSCAR MELHOR FRETE");
		System.out.println("0 - SAIR");
	}

	private void buscaMelhorFrete() throws Exception {
		int latitudeOrigem;
		int longitudeOrigem;
		int latitudeDestino;
		int longitudeDestino;
		int pesoCarga;
		System.out.println("*******************");
		System.out.println("Informe a Latitude de Origem");
		latitudeOrigem = Integer.parseInt(this.br.readLine());
		System.out.println("Informe a Longitude de Origem");
		longitudeOrigem = Integer.parseInt(this.br.readLine());
		System.out.println("Informe a Latitude de Destino");
		latitudeDestino = Integer.parseInt(this.br.readLine());
		System.out.println("Informe a Longitude de Destino");
		longitudeDestino = Integer.parseInt(this.br.readLine());
		System.out.println("Informe o Peso da Carga");
		pesoCarga = Integer.parseInt(this.br.readLine());
		Veiculo veiculo = null;
		veiculo = this.app.melhorFrete(latitudeOrigem, longitudeOrigem, latitudeDestino, longitudeDestino, pesoCarga);
		System.out.println("Matricula: " + veiculo.getMatricula() + " - Valor do Frete: " +
		veiculo.CustoFrete(latitudeOrigem, longitudeOrigem, latitudeDestino, longitudeDestino, pesoCarga) );
		System.out.println("Deseja Confirmar o Frete? - SIM(1) - Não(2)");
		int option = Integer.parseInt(this.br.readLine());
		if(option==1) {
			app.setLocalizacao(veiculo.getMatricula(), latitudeDestino, longitudeDestino);
		}
	}
	
	private void addVeiculo() throws Exception {
		int capacidade;
		int latitude;
		int longitude;
		boolean tipo;
		System.out.println("*******************");
		System.out.println("Informe a Latitude");
		latitude = Integer.parseInt(this.br.readLine());
		System.out.println("Informe a Longitude");
		longitude = Integer.parseInt(this.br.readLine());
		System.out.println("Informe a Capacidade");
		capacidade = Integer.parseInt(this.br.readLine());
		System.out.println("Informe o Tipo: Terrestre(1) - Drone(2)");
		tipo = Integer.parseInt(this.br.readLine()) == 1 ? true : false;
		this.app.addVeiculo(capacidade, latitude, longitude, tipo);
	}
}
