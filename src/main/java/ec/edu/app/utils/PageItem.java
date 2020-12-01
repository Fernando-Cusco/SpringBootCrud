package ec.edu.app.utils;

public class PageItem {

	private int numeroPagina;
	private boolean actual;
	public PageItem(int numeroPaginas, boolean actual) {
		
		super();
		this.numeroPagina = numeroPaginas;
		this.actual = actual;
	}
	public int getNumeroPagina() {
		return numeroPagina;
	}
	public boolean isActual() {
		return actual;
	}
	
	
	
}
