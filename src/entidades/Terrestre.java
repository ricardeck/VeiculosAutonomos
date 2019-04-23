package entidades;

public class Terrestre extends Veiculo{
	public static final double PrecoKM = 1;
	public static final double ValorMinimo = 30;
	
	@Override
	public double CustoFrete(int latitudeOrigem, int longitudeOrigem, int latitudeDestino, int longitudeDestino,
			int pesoCarga) {
		pesoCarga = pesoCarga <= 0 ? 1 : pesoCarga;
		double distancia = Math.abs(latitude - latitudeOrigem);
		distancia += Math.abs(longitude - longitudeOrigem);
		distancia += Math.abs(latitudeOrigem - latitudeDestino);
		distancia += Math.abs(longitudeOrigem - longitudeDestino);
		double custo = distancia*pesoCarga*PrecoKM;
		custo = custo < ValorMinimo ? ValorMinimo : custo;
		return custo;
	}

	public Terrestre() {
		super();
	}
	
	public Terrestre(int capacidade, int latitude, int longitude) {
		super(capacidade, latitude, longitude);
	}
}
