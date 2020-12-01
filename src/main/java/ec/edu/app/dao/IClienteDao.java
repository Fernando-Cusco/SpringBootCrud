package ec.edu.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import ec.edu.app.models.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{
	
}
