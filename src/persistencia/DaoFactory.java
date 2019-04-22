package persistencia;

public class DaoFactory {
	
	public static VeiculoDAOIF createVeiculoDao() {
		return (VeiculoDAOIF) new VeiculoDaoJDBC();
	}
}
