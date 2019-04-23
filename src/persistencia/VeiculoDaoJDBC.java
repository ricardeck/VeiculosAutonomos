package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import Exceção.DbException;
import entidades.Drone;
import entidades.Terrestre;
import entidades.Veiculo;

public class VeiculoDaoJDBC implements VeiculoDAOIF {
	private static Connection conn = null ;
	
	public VeiculoDaoJDBC() {
		VeiculoDaoJDBC.conn = VeiculoDaoJDBC.getConnection();
	}
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	@Override
	public ArrayList<Veiculo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM veiculo");
			rs = st.executeQuery();

			ArrayList<Veiculo> list = new ArrayList<Veiculo>();

			while (rs.next()) {
				Veiculo obj = rs.getBoolean("tipo") ? new Terrestre(): new Drone();
				obj.setMatricula(rs.getInt("matricula"));
				obj.setCapacidade(rs.getInt("capacidade"));
				obj.setLatitude(rs.getInt("latitude"));
				obj.setLongitude(rs.getInt("longitude"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			VeiculoDaoJDBC.closeStatement(st);
			VeiculoDaoJDBC.closeResultSet(rs);
		}
	}
	
	@Override
	public void insert(Veiculo obj) {
		PreparedStatement st = null;
		try {
			boolean tipo = obj.getClass().toString().equals("class entidades.Terrestre");
			
			st = conn.prepareStatement(
				"INSERT INTO veiculo " +
				"(capacidade, latitude, longitude, tipo) " +
				"VALUES " +
				"(?,?,?,?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getCapacidade());
			st.setInt(2, obj.getLatitude());
			st.setInt(3, obj.getLongitude());
			st.setBoolean(4, tipo);

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int matricula = rs.getInt(1);
					obj.setMatricula(matricula);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			VeiculoDaoJDBC.closeStatement(st);
		}
	}

	@Override
	public ArrayList<Veiculo> findByCapacidade(int capacidade) throws NullPointerException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM veiculo WHERE capacidade >=" + capacidade);
			rs = st.executeQuery();

			ArrayList<Veiculo> list = new ArrayList<Veiculo>();

			while (rs.next()) {
				Veiculo obj = rs.getBoolean("tipo") ? new Terrestre(): new Drone();
				obj.setMatricula(rs.getInt("matricula"));
				obj.setCapacidade(rs.getInt("capacidade"));
				obj.setLatitude(rs.getInt("latitude"));
				obj.setLongitude(rs.getInt("longitude"));
				list.add(obj);
			}
			if(list.isEmpty())
				return null;
						
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			VeiculoDaoJDBC.closeStatement(st);
			VeiculoDaoJDBC.closeResultSet(rs);
		}
	}

	@Override
	public Veiculo findByMatricula(int matricula) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM veiculo WHERE matricula =" + matricula);
			rs = st.executeQuery();

			Veiculo veiculo = null;

			if (rs.next()) {
				Veiculo obj = rs.getBoolean("tipo") ? new Terrestre(): new Drone();
				obj.setMatricula(rs.getInt("matricula"));
				obj.setCapacidade(rs.getInt("capacidade"));
				obj.setLatitude(rs.getInt("latitude"));
				obj.setLongitude(rs.getInt("longitude"));
				veiculo = obj;
			}
			if(veiculo==null)
				return null;
			return veiculo;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			VeiculoDaoJDBC.closeStatement(st);
			VeiculoDaoJDBC.closeResultSet(rs);
		}
	}

	@Override
	public void update(Veiculo veiculo) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE veiculo SET latitude = ?, longitude = ?, capacidade = ? WHERE matricula = ?");

			st.setInt(1, veiculo.getLatitude());
			st.setInt(2, veiculo.getLongitude());
			st.setInt(3, veiculo.getCapacidade());
			st.setInt(4, veiculo.getMatricula());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			VeiculoDaoJDBC.closeStatement(st);
		}
		
	}

}
