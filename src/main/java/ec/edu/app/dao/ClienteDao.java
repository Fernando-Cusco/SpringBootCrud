package ec.edu.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.core.net.server.Client;
import ec.edu.app.models.Cliente;

@Repository
public class ClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	
	
	public List<Cliente> listarClientes() {
		System.out.println("Listado de clientes");
		return em.createQuery("from Cliente").getResultList();
	}
	
	
	public void nuevoCliente(Cliente cliente) {
		System.out.println(cliente.toString()+ " metodo");
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		} else {
			em.persist(cliente);
		}
		
	}
	
	
	public Cliente buscarCliente(Long id) {
		return em.find(Cliente.class, id);
	}
	
	
	public void eliminarCliente(Long id) {
		Cliente cliente = buscarCliente(id);
		em.remove(cliente);
	}

}
