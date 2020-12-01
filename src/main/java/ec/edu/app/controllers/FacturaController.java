package ec.edu.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.app.models.Cliente;
import ec.edu.app.models.Factura;
import ec.edu.app.models.Producto;
import ec.edu.app.services.IClienteService;

@Controller
@RequestMapping(value = "/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(value = "/form/{clienteId}")
	public String crear(@PathVariable Long clienteId, Model model, RedirectAttributes flash, SessionStatus status) {
		Cliente cliente = clienteService.buscarCliente(clienteId);
		if(cliente == null ) {
			flash.addFlashAttribute("danger", "No existe el cliente");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.addAttribute("titulo", "Crear factura");
		model.addAttribute("factura", factura);
		status.setComplete();
		return "factura/form";
	}
	
	@GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		
		return clienteService.findByNombreLikeIgnoreCase(term);
	}
	
	
}
