package ec.edu.app.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numeroElementos;
	private int paginaActual;
	private List<PageItem> paginas;
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.totalPaginas = page.getTotalPages();
		this.numeroElementos = page.getSize();
		this.paginaActual = page.getNumber() + 1;
		this.paginas = new ArrayList<PageItem>();
		
		int desde;
		int hasta;
		if(totalPaginas < numeroElementos) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if(paginaActual <= numeroElementos/2) {
				desde = 1;
				hasta = numeroElementos;
			} else if(paginaActual >= totalPaginas - numeroElementos/2) {
				desde = totalPaginas - numeroElementos + 1;
				hasta = numeroElementos;
			} else {
				desde = paginaActual - numeroElementos/2;
				hasta = numeroElementos;
			}
		}
		
		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}
		
	}
	
	public boolean esPrimera() {
		return page.isFirst();
	}
	
	public boolean esUltima() {
		return page.isLast();
	}
	
	public boolean siguiente() {
		return page.hasNext();
	}
	
	public boolean anterior() {
		return page.hasPrevious();
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Page<T> getPage() {
		return page;
	}
	public void setPage(Page<T> page) {
		this.page = page;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public int getNumeroElementos() {
		return numeroElementos;
	}
	public void setNumeroElementos(int numeroElementos) {
		this.numeroElementos = numeroElementos;
	}
	public int getPaginaActual() {
		return paginaActual;
	}
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	public List<PageItem> getPaginas() {
		return paginas;
	}
	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}
	
	
	 
	
	
}
