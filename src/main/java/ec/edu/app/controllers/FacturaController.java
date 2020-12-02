package ec.edu.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import ec.edu.app.models.Cliente;
import ec.edu.app.models.Detalle;
import ec.edu.app.models.Factura;
import ec.edu.app.models.Producto;
import ec.edu.app.services.IClienteService;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping(value = "/factura")
@SessionAttributes("factura")
public class FacturaController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IClienteService clienteService;


	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Factura factura = clienteService.findFacturaById(id);
		if(factura == null) {
			flash.addFlashAttribute("danger", "La factura no existe");
			return "redirect:/listar";
		}
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Detalle Factura ".concat(factura.getDescripcion()));
		return "factura/ver";
	}
	
	@GetMapping(value = "/form/{clienteId}")
	public String crear(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {
		Cliente cliente = clienteService.buscarCliente(clienteId);
		if(cliente == null ) {
			flash.addFlashAttribute("danger", "No existe el cliente");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.addAttribute("titulo", "Crear factura");
		model.addAttribute("factura", factura);
		
		return "factura/form";
	}
	
	@GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		
		return clienteService.findByNombreLikeIgnoreCase(term);
	}
	
	@PostMapping(value = "/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "detalle_id[]", required = false) Long[] detalleId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status
			) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear factura");
			return "factura/form";
		}
		if (detalleId == null || detalleId.length == 0 ) {
			flash.addFlashAttribute("info", "La factura no tiene productos");
			model.addAttribute("titulo", "Crear factura");
			return "factura/form";
		}
		for (int i = 0; i < detalleId.length; i++) {
			Producto producto = clienteService.findProductoById(detalleId[i]);
			Detalle detalle = new Detalle();
			detalle.setCantidad(cantidad[i]);
			detalle.setProducto(producto);
			factura.agregarDetalle(detalle);
			log.info("Id: "+detalleId[i].toString()+" Cantidad: "+cantidad[i].toString());
		}
		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Factura creada con exito para el cliente: "+factura.getCliente().getNombres()+" "+factura.getCliente().getApellidos());
		return "redirect:/ver/"+factura.getCliente().getId();
	}
	
	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		Factura factura = clienteService.findFacturaById(id);
		if(factura != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura eliminada con exito.");
			return "redirect:/ver/"+factura.getCliente().getId();
		}
		
		flash.addFlashAttribute("warning", "Factura no existe.");
		return "redirect:/listar";
	}
	
	
}
