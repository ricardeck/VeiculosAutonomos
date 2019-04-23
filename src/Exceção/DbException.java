package Exceção;


public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super("Problema na conexão com o banco de dados" + msg);
	}
	
	public DbException(String msg, Throwable cause){
	    super("Problema com o banco de dados" + msg, cause);
	}
}
