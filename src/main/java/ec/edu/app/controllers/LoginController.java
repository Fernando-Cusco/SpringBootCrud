package ec.edu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(Model model, Principal principal, RedirectAttributes flash) {
        if(principal != null) {
            flash.addFlashAttribute("info", "Ya haz iniciado sesión.");
            return "redirect:/";
        }
        model.addAttribute("titulo", "Inicio de Sesión");
        return "login";
    }

}
