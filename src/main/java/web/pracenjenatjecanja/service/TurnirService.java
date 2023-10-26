package web.pracenjenatjecanja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.pracenjenatjecanja.dto.TurnirDTO;
import web.pracenjenatjecanja.dao.Igra;
import web.pracenjenatjecanja.dao.Igrac;
import web.pracenjenatjecanja.dao.Kolo;
import web.pracenjenatjecanja.dao.Turnir;
import web.pracenjenatjecanja.repos.IgraRepository;
import web.pracenjenatjecanja.repos.IgracRepository;
import web.pracenjenatjecanja.repos.KoloRepository;
import web.pracenjenatjecanja.repos.TurnirRepository;

import java.util.*;

@Service
public class TurnirService {

    @Autowired
    TurnirRepository turnirRepository;

    @Autowired
    IgracRepository igracRepository;

    @Autowired
    IgraRepository igraRepository;

    @Autowired
    KoloRepository koloRepository;

    public TurnirDTO noviTurnir(TurnirDTO turnir, String email) {
        Set<String> igraciString = new HashSet<>(Arrays.asList(turnir.getIgracistring().split(";")));
        if (igraciString.size() > 8 || igraciString.size() < 4)
            throw new IllegalArgumentException("Broj igraca mora biti izmedu 4 i 8");
        List<String> rezultati = Arrays.asList(turnir.getRezultati().split("/"));
        Double pobjeda = Double.parseDouble(rezultati.get(0));
        Double remi = Double.parseDouble(rezultati.get(1));
        Double poraz = Double.parseDouble(rezultati.get(2));
        Turnir noviTurnir = new Turnir(turnir.getNaziv(), pobjeda, remi, poraz, email);

        Set<Igrac> igraci = new HashSet<>();
        igraciString.forEach(igrac -> {
            igraci.add(igracRepository.save(new Igrac(igrac)));
        });
        noviTurnir.setIgraci(igraci);
        turnirRepository.save(noviTurnir);
        noviTurnir.setIgre(generirajKola(igraci.stream().toList(), noviTurnir.getId()));
        noviTurnir = turnirRepository.save(noviTurnir);
        noviTurnir.setPoveznica(noviTurnir.getId().toString());
        return TurnirDTO.toDto(turnirRepository.save(noviTurnir));

    }


    private Set<Kolo> generirajKola(List<Igrac> igraci, Long turnirId) {

        Set<Kolo> kola = new HashSet<>();

        int brojKola = igraci.size() % 2 == 0 ? igraci.size() - 1 : igraci.size();

        for (int i = 0; i < brojKola; i++) {
            List<List<Igrac>> kolo = kirkmanPairing(igraci, igraci.size(), i + 1);
            Set<Igra> igreUKolu = new HashSet<>();
            for (int j = 0; j < kolo.size(); j++) {
                if ((kolo.get(j).get(0).getNaziv() != "bye" && kolo.get(j).get(1).getNaziv() != "bye")) {
                    Igra igra = igraRepository.save(new Igra(kolo.get(j).get(0), kolo.get(j).get(1)));
                    igreUKolu.add(igra);
                }
            }
            Kolo k = new Kolo(turnirId);
            k.setIgre(igreUKolu);
            koloRepository.save(k);
            kola.add(k);
        }

        return kola;
    }

    public static List<List<Igrac>> kirkmanPairing(List<Igrac> igraci, int n, int rd) {
        List<List<Integer>> pair = new ArrayList<>();

        List<Igrac> kopiranaLista = new ArrayList<>(igraci);
        int[] idex = {rd, rd};

        // 2n - 1 spariranje je isto kao 2n spariranja
        if (n % 2 != 0) {
            n = n + 1;
            kopiranaLista.add(new Igrac("bye"));
        }

        for (int i = 0; i < n / 2; i++) {
            // Dodati posljednjeg (bye)
            if (idex[0] == idex[1]) {
                List<Integer> byePair = new ArrayList<>();
                byePair.add(idex[0]);
                byePair.add(n);
                pair.add(byePair);
            } else {
                List<Integer> currentPair = new ArrayList<>();
                currentPair.add(idex[0]);
                currentPair.add(idex[1]);
                pair.add(currentPair);
            }

            // Pomicanje prema gore u antidiagonali
            idex[0] = idex[0] - 1;
            idex[1] = idex[1] + 1;

            // Zaokrenuti, ako je potrebno
            if (idex[0] < 1) {
                idex[0] = n - 1;
            }

            if (idex[1] > n - 1) {
                idex[1] = 1;
            }
        }

        List<List<Igrac>> result = new ArrayList<>();
        for (List<Integer> p : pair) {
            result.add(List.of(kopiranaLista.get(p.get(0) - 1), kopiranaLista.get(p.get(1) - 1)));
        }
        System.out.println(result.toString());


        return result;
    }


    public List<TurnirDTO> dohvatiTurnireZaKorisnika(String email) {

        return turnirRepository.findByEmail(email).stream().map(turnir -> TurnirDTO.toDto(turnir)).toList();

    }

    public TurnirDTO dohvatiTurnirById(Long idTurnir) {

        Turnir turnir = turnirRepository.findById(idTurnir).get();

        return TurnirDTO.toDto(turnir
        );

    }
}
