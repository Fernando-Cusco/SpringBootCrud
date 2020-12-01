package ec.edu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,Model model, Principal principal, RedirectAttributes flash) {
        if(principal != null) {
            flash.addFlashAttribute("info", "Ya haz iniciado sesión.");
            return "redirect:/";
        }
        if(error != null) {
            model.addAttribute("danger", "Error al iniciar sesión, verifica las credenciales");
        }

        if(logout != null) {
            model.addAttribute("success", "Haz cerrado sesión con exito");
        }
        model.addAttribute("titulo", "Inicio de Sesión");
        return "login";
    }


}
