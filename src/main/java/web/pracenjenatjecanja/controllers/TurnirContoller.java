package web.pracenjenatjecanja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.pracenjenatjecanja.dto.KoloDTO;
import web.pracenjenatjecanja.dto.TurnirDTO;
import web.pracenjenatjecanja.service.KoloService;
import web.pracenjenatjecanja.service.TurnirService;

import java.util.List;

@Controller
@RequestMapping("/turnir")
public class TurnirContoller {

    @Autowired
    TurnirService turnirService;

    @Autowired
    KoloService koloService;

    @GetMapping("/korisnik")
    public String login(Model model, Authentication authentication) {
        List<TurnirDTO> turniri = turnirService.dohvatiTurnireZaKorisnika(((DefaultOidcUser) authentication.getPrincipal()).getAttribute("email"));
        model.addAttribute("turniri", turniri);
        return "index";
    }

    @GetMapping("")
    public String obrazacZaKreiranje(Model model) {
        model.addAttribute("turnir", new TurnirDTO());
        return "noviturnir";
    }

    @PostMapping("/novi")
    public String noviTurnir(Authentication authentication, @ModelAttribute("turnir") TurnirDTO turnir) {
        String email = ((DefaultOidcUser) authentication.getPrincipal()).getAttribute("email");
        try {
            turnirService.noviTurnir(turnir, email);
            return "redirect:/turnir/korisnik";
        } catch (IllegalArgumentException e) {
            return "errorpage";
        }
    }

    @GetMapping("/{idTurnir}")
    public String urediTurnir(@PathVariable Long idTurnir, Model model) {
        TurnirDTO turnir = turnirService.dohvatiTurnirById(idTurnir);
        model.addAttribute("turnir", turnir);

        List<KoloDTO> kola = koloService.getKoloByTurnirId(turnir.getId());
        model.addAttribute("kola", kola);

        return "turnir";
    }

    @GetMapping("/guest/{idTurnir}")
    public String guestTurnir(@PathVariable Long idTurnir, Model model) {
        TurnirDTO turnir = turnirService.dohvatiTurnirById(idTurnir);
        model.addAttribute("turnir", turnir);
        List<KoloDTO> kola = koloService.getKoloByTurnirId(turnir.getId());
        model.addAttribute("kola", kola);
        return "guestturnir";
    }
}
