package web.pracenjenatjecanja.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import web.pracenjenatjecanja.dao.Igrac;
import web.pracenjenatjecanja.dao.Turnir;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Data
public class TurnirDTO {

    private Long id;

    @NotNull(message = "Naziv turnira ne smije biti null.")
    private String naziv;

    private String poveznica;

    private String igracistring;

    private List<Igrac> igraci = new ArrayList<>();

    private String rezultati;

    private Double pobjeda;

    private Double remi;

    private Double poraz;

    private String email;

    public TurnirDTO(Long id, @NonNull String naziv, String poveznica, Set<Igrac> igraci, Double pobjeda, Double remi, Double poraz, String email) {
        this.id = id;
        this.naziv = naziv;
        this.poveznica = poveznica;
        this.igraci = igraci.stream().sorted(Comparator.comparing(Igrac::getBodovi, Comparator.reverseOrder())).toList();
        this.pobjeda = pobjeda;
        this.remi = remi;
        this.poraz = poraz;
        this.email = email;
    }

    public TurnirDTO() {

    }

    public static TurnirDTO toDto(Turnir turnir) {
        return new TurnirDTO(turnir.getId(), turnir.getNaziv(), turnir.getPoveznica(), turnir.getIgraci(), turnir.getPobjeda(), turnir.getRemi(), turnir.getPoraz(), turnir.getEmail());
    }

}
