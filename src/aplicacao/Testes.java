package aplicacao;
import java.util.ArrayList;
import java.util.Scanner;
import entidades.Drone;
import entidades.Terrestre;
import entidades.Veiculo;
import persistencia.DaoFactory;
import persistencia.VeiculoDAOIF;
import sessao.AppFrete;

public class Testes {

	public static void main(String[] args) throws Exception {


//		System.out.println("Testando melhorFrete");
//		VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
//		AppFrete app = new AppFrete(veiculoDao);
//		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
//		System.out.println("\n-------------------------------\n");
//		System.out.println(app.melhorFrete(8, 6, 10, 10, 6));
//
//
//		
//		System.out.println("Testando findAll");
//		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
//		Scanner sc = new Scanner(System.in);
//		VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
//		veiculos = veiculoDao.findAll();
//		for (Veiculo v : veiculos) {
//			System.out.println(v.toString() +" - " + v.CustoFrete(8, 6, 10, 10, 6) + " - " + v.getClass().toString());
//		}
//		
//		
//		
//		System.out.println("Testando findByMatricula");
//		VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
//		System.out.println(veiculoDao.findByMatricula(2).toString());
//
//		
//		
//		System.out.println("Testando findByCapacidade");
//		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
//		Scanner sc = new Scanner(System.in);
//		VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
//		veiculos = veiculoDao.findByCapacidade(13);
//		for (Veiculo v : veiculos) {
//			System.out.println(v.toString() +" - " + v.CustoFrete(8, 6, 10, 10, 6) + " - " + v.getClass().toString());
//		}
//		
//		
//		
//		System.out.println("Testando insert");
//		Scanner sc = new Scanner(System.in);
//		VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
//		Veiculo veiculo = new Terrestre(15,1,1);
//		veiculoDao.insert(veiculo);
//		System.out.println("Inserted! New id: " + veiculo.getMatricula());
//		Veiculo veiculo2 = new Drone(12,2,3);
//		veiculoDao.insert(veiculo2);
//		System.out.println("Inserted! New id: " + veiculo2.getMatricula());
//		sc.close();
		
//		System.out.println("Testando update");
//		VeiculoDAOIF veiculoDao = DaoFactory.createVeiculoDao();
//		Veiculo veiculo = veiculoDao.findByMatricula(3);
//		veiculo.setLatitude(8);
//		veiculo.setLongitude(7);
//		veiculoDao.update(veiculo);
//		System.out.println("Update id: " + veiculo.getMatricula());
		
	}
}
