package web.pracenjenatjecanja.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.pracenjenatjecanja.dao.Igra;
import web.pracenjenatjecanja.dao.Rezultat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IgraDTO {

    @NotNull(message = "ID ne smije biti null.")
    Long idIgra;
    IgracDTO igrac1;
    IgracDTO igrac2;
    Rezultat rezultat;

    public static IgraDTO toDto(Igra igra) {
        return new IgraDTO(igra.getId(), IgracDTO.toDto(igra.getIgrac1()), IgracDTO.toDto(igra.getIgrac2()), igra.getRezultat());
    }
}
