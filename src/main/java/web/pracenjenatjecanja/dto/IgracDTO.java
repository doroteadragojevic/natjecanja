package web.pracenjenatjecanja.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.pracenjenatjecanja.dao.Igrac;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IgracDTO {

    private Long id;

    @NotNull(message = "Naziv igraca ne smije biti null.")
    private String naziv;

    private Double bodovi = 0.;

    public static IgracDTO toDto(Igrac igrac) {
        return new IgracDTO(igrac.getId(), igrac.getNaziv(), igrac.getBodovi());
    }
}
