package entidades;

public class Drone extends Veiculo{
	public static final double PrecoKM = 1.25;
	public static final double ValorMinimo = 40;
	
	public Drone(int capacidade, int latitude, int longitude) {
		super(capacidade, latitude, longitude);
	}
	
	public Drone() {
		super();
	}

	@Override
	public double CustoFrete(int latitudeOrigem, int longitudeOrigem, int latitudeDestino, int longitudeDestino,
			int pesoCarga) {
		pesoCarga = pesoCarga <= 0 ? 1 : pesoCarga;
		double diferencaLatitude = Math.abs(latitude - latitudeOrigem);
		double diferencaLongitude = Math.abs(longitude - longitudeOrigem);
		diferencaLatitude += Math.abs(latitudeOrigem - latitudeDestino);
		diferencaLongitude += Math.abs(longitudeOrigem - longitudeDestino);
		double custo = Math.sqrt(Math.pow(diferencaLatitude,2) + Math.pow(diferencaLongitude,2));
		custo *= pesoCarga*PrecoKM;
		custo = custo<ValorMinimo ? ValorMinimo : custo;
		return custo;
	}
}
