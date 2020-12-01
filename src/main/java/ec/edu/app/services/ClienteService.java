package ec.edu.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.app.dao.IClienteDao;
import ec.edu.app.models.Cliente;

@Service
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional(readOnly = false)
	public void nuevoCliente(Cliente cliente) {
		// TODO Auto-generated method stub
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
