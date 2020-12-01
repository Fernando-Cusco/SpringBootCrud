package ec.edu.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.app.dao.IClienteDao;
import ec.edu.app.dao.IProductoDao;
import ec.edu.app.models.Cliente;
import ec.edu.app.models.Producto;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	
	public List<Producto> findByNombreLikeIgnoreCase(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}
	
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional(readOnly = false)
	public void nuevoCliente(Cliente cliente) {
		clienteDao.save(cliente);
		
	}

	@Transactional(readOnly = true)
	public Cliente buscarCliente(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = false)
	public void eliminarCliente(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	
}
