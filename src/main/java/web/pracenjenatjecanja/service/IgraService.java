package web.pracenjenatjecanja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.pracenjenatjecanja.dto.IgraDTO;
import web.pracenjenatjecanja.dao.Igra;
import web.pracenjenatjecanja.dao.Igrac;
import web.pracenjenatjecanja.dao.Rezultat;
import web.pracenjenatjecanja.dao.Turnir;
import web.pracenjenatjecanja.repos.IgraRepository;
import web.pracenjenatjecanja.repos.IgracRepository;
import web.pracenjenatjecanja.repos.TurnirRepository;

@Service
public class IgraService {

    @Autowired
    IgraRepository igraRepository;

    @Autowired
    IgracRepository igracRepository;

    @Autowired
    TurnirRepository turnirRepository;

    public IgraDTO getById(Long idIgra) {

        return IgraDTO.toDto(igraRepository.findById(idIgra).get());

    }

    public void update(IgraDTO igra, Long idTurnir) {

        Turnir turnir = turnirRepository.findById(idTurnir).get();

        Double pobjeda = turnir.getPobjeda();
        Double remi = turnir.getRemi();
        Double poraz = turnir.getPoraz();

        Igra igraDao = igraRepository.findById(igra.getIdIgra()).get();
        Igrac igrac1 = igracRepository.findById(igra.getIgrac1().getId()).get();
        Igrac igrac2 = igracRepository.findById(igra.getIgrac2().getId()).get();
        if (igraDao.getRezultat() != Rezultat.NIJE_ODIGRANO) {
            switch (igraDao.getRezultat()) {
                case POBJEDA -> {
                    igrac1.setBodovi(igrac1.getBodovi() - pobjeda);
                    igrac2.setBodovi(igrac2.getBodovi() - poraz);
                }
                case PORAZ -> {
                    igrac1.setBodovi(igrac1.getBodovi() - poraz);
                    igrac2.setBodovi(igrac2.getBodovi() - pobjeda);
                }
                case REMI -> {
                    igrac1.setBodovi(igrac1.getBodovi() - remi);
                    igrac2.setBodovi(igrac2.getBodovi() - remi);
                }
            }
        }
        switch (igra.getRezultat()) {
            case POBJEDA -> {
                igrac1.setBodovi(igrac1.getBodovi() + pobjeda);
                igrac2.setBodovi(igrac2.getBodovi() + poraz);
            }
            case PORAZ -> {
                igrac1.setBodovi(igrac1.getBodovi() + poraz);
                igrac2.setBodovi(igrac2.getBodovi() + pobjeda);
            }
            case REMI -> {
                igrac1.setBodovi(igrac1.getBodovi() + remi);
                igrac2.setBodovi(igrac2.getBodovi() + remi);
            }
        }
        igracRepository.save(igrac1);
        igracRepository.save(igrac2);
        igraDao.setRezultat(igra.getRezultat());
        igraRepository.save(igraDao);

    }
}
