package entidades;

public abstract class Veiculo{
	protected int matricula;
	protected int capacidade;
	protected int latitude;
	protected int longitude;
	
	public Veiculo() {
	}
	
	public Veiculo(int capacidade, int latitude, int longitude) {
		this.capacidade = capacidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public abstract double CustoFrete(int latitudeOrigem, int longitudeOrigem,
			int latitudeDestino, int longitudeDestino, int pesoCarga );

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public int getCapacidade() {
		return capacidade;
	}
	
	public int getLatitude() {
		return latitude;
	}
	
	public int getLongitude() {
		return longitude;
	}
	
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		String tipo = this.getClass().toString().equalsIgnoreCase("class entidades.Terrestre") ? "Terrestre" : "Drone";
		return " Matricula "+ matricula + " :"+ tipo + " - Capacidade " + capacidade + " / Latitude " + latitude
				+ " / Longitude " + longitude;
	}
}
