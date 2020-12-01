package ec.edu.app.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import ec.edu.app.models.Cliente;
import ec.edu.app.services.IClienteService;
import ec.edu.app.utils.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private final static String UPLOADS = "uploads";
	
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		log.info("Path Foto: "+pathFoto);
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathFoto.toUri());
			if(!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen: "+pathFoto.toString());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+recurso.getFilename()+"\"")
				.body(recurso);
	}
	
	@GetMapping(value = "/ver/{id}")
	public String verImagen(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = clienteService.buscarCliente(id);
		if(cliente == null) {
			flash.addFlashAttribute("danger", "El cliente no existe.");
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Detalle Cliente");
		model.addAttribute("cliente", cliente);
		return "ver";
	}
	
	@GetMapping(value = "listar")
	 public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageable);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	 }
	
	
	@GetMapping(value = "form")
	public String nuevoClienteView(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("boton", "Crear Cliente");
		model.addAttribute("titulo", "Nuevo Cliente");
		return "form";
	}
	
	@PostMapping(value = "form")
	public String nuevoCliente(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto ,RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo Cliente");
			model.addAttribute("boton", "Crear Cliente");
			return "form";
		}
		if(cliente.getId() != null && cliente.getId() > 0) {
			
			if(!foto.isEmpty()) {
				if(cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
					Path rootPath = Paths.get(UPLOADS).resolve(cliente.getFoto()).toAbsolutePath();
					File f  = rootPath.toFile();
					if(f.exists() && f.canRead()) {
						f.delete();
					}
				}
				String uniqueName = UUID.randomUUID().toString() + "_"+foto.getOriginalFilename();
				Path path = Paths.get(UPLOADS).resolve(uniqueName);
				Path absolutePath = path.toAbsolutePath();
				log.info("Editar - Ruta relativa imagen: "+ path);
				log.info("Editar - Ruta obsoluta imagen: "+ absolutePath);
				try {
					Files.copy(foto.getInputStream(), absolutePath);
					flash.addFlashAttribute("info", "Foto cargada correctamente: "+uniqueName);
					cliente.setFoto(uniqueName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			clienteService.nuevoCliente(cliente);
			flash.addFlashAttribute("success", "Cliente editado exitosamente.");
			return "redirect:/listar";
			
		}
		if(!foto.isEmpty()) {
			
			String uniqueName = UUID.randomUUID().toString() + "_"+foto.getOriginalFilename();
			Path path = Paths.get("uploads").resolve(uniqueName);
			Path absolutePath = path.toAbsolutePath();
			log.info("Editar - Ruta relativa imagen: "+ path);
			log.info("Editar - Ruta obsoluta imagen: "+ absolutePath);
			try {
				Files.copy(foto.getInputStream(), absolutePath);
				flash.addFlashAttribute("info", "Foto cargada correctamente: "+uniqueName);
				cliente.setFoto(uniqueName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		clienteService.nuevoCliente(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", "Cliente creado con exito");
		return "redirect:listar"; 
	}
	
	@GetMapping(value = "form/{id}")
	public String buscarCliente(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.buscarCliente(id);
			if(cliente == null) {
				flash.addFlashAttribute("danger", "El cliente no existe.");
				return "redirect:/listar";
			}
			model.addAttribute("titulo", "Editar Cliente");
			model.addAttribute("boton", "Editar Cliente");
			model.addAttribute("cliente", cliente);
			
		} else {
			flash.addFlashAttribute("danger", "Error el cliente no existe.");
			return "redirect:/listar";
		}
		
		return "form";
	}
	
	@GetMapping(value = "eliminar/{id}")
	public String eliminarCliente(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
		if(id > 0) {
			Cliente cliente = clienteService.buscarCliente(id);
			clienteService.eliminarCliente(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
			Path rootPath = Paths.get(UPLOADS).resolve(cliente.getFoto()).toAbsolutePath();
			File foto  = rootPath.toFile();
			if(foto.exists() && foto.canRead()) {
				if(foto.delete()) {
					flash.addFlashAttribute("info", "Foto "+cliente.getFoto()+ " eliminada con exito");
				}
			}
		}
		return "redirect:/listar";
	}
	
}
