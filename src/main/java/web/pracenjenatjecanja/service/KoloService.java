package web.pracenjenatjecanja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.pracenjenatjecanja.dto.KoloDTO;
import web.pracenjenatjecanja.repos.KoloRepository;

import java.util.List;

@Service
public class KoloService {

    @Autowired
    KoloRepository koloRepository;


    public List<KoloDTO> getKoloByTurnirId(Long turnirId) {

        return koloRepository.findAllByTurnirId(turnirId).stream().map(kolo -> KoloDTO.toDto(kolo)).toList();

    }
}
