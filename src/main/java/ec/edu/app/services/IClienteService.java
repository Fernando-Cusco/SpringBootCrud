package ec.edu.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ec.edu.app.models.Cliente;
import ec.edu.app.models.Factura;
import ec.edu.app.models.Producto;

public interface IClienteService {

	public List<Cliente> listarClientes();
	public Page<Cliente> findAll(Pageable pageable);
	
	public void nuevoCliente(Cliente cliente);
	
	public Cliente buscarCliente(Long id);
	
	public void eliminarCliente(Long id);
	
	public List<Producto> findByNombre(String term);
	public List<Producto> findByNombreLikeIgnoreCase(String term);
	
	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
	
	public Factura findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	
}
