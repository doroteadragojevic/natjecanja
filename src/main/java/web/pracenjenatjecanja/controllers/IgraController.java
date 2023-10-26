package web.pracenjenatjecanja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.pracenjenatjecanja.dto.IgraDTO;
import web.pracenjenatjecanja.service.IgraService;

@Controller
@RequestMapping("/igra")
public class IgraController {

    @Autowired
    IgraService igraService;

    @GetMapping("/{idTurnir}/{idIgra}")
    public String urediIgru(@PathVariable Long idIgra, @PathVariable Long idTurnir, Model model) {
        IgraDTO igra = igraService.getById(idIgra);
        model.addAttribute("igra", igra);
        model.addAttribute("idTurnir", idTurnir);
        return "rezultat";
    }

    @PostMapping("/{idTurnir}/{idIgra}")
    public String urediIgru(@ModelAttribute("igra") IgraDTO igra, @PathVariable Long idTurnir, @PathVariable Long idIgra, RedirectAttributes redirectAttributes) {
        igraService.update(igra, idTurnir);
        redirectAttributes.addAttribute("idTurnir", idTurnir);
        return "redirect:/turnir/{idTurnir}";
    }
}
