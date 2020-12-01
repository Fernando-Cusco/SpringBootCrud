package ec.edu.app.dao;

import org.springframework.data.repository.CrudRepository;

import ec.edu.app.models.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
	
	
}
