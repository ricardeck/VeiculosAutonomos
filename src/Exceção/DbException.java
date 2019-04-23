package Exce��o;


public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super("Problema na conex�o com o banco de dados" + msg);
	}
	
	public DbException(String msg, Throwable cause){
	    super("Problema com o banco de dados" + msg, cause);
	}
}
