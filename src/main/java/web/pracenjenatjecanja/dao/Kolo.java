package web.pracenjenatjecanja.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Kolo {

    @Id
    @GeneratedValue
    Long id;

    @NonNull
    Long turnirId;

    @NonNull
    @OneToMany
    Set<Igra> igre = new HashSet<>();

    public Kolo() {
    }

    public Kolo(@NonNull Long turnirId) {
        this.turnirId = turnirId;
    }
}
