package web.pracenjenatjecanja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.pracenjenatjecanja.dao.Kolo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoloDTO {

    Set<IgraDTO> igre = new HashSet<>();

    public static KoloDTO toDto(Kolo k) {
        return new KoloDTO(k.getIgre().stream().map(IgraDTO::toDto).collect(Collectors.toSet()));
    }
}
