package web.pracenjenatjecanja.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Igra {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @ManyToOne
    private Igrac igrac1;

    @NonNull
    @ManyToOne
    private Igrac igrac2;

    private Rezultat rezultat = Rezultat.NIJE_ODIGRANO;

    public Igra(@NonNull Igrac igrac, @NonNull Igrac igrac1) {
        this.igrac1 = igrac;
        this.igrac2 = igrac1;
    }

    @Override
    public boolean equals(Object o) {
        Igra igra2 = (Igra) o;

        return (this.igrac1 == igra2.igrac1 && this.igrac2 == igra2.igrac2) || (this.igrac1 == igra2.igrac2 && this.igrac2 == igra2.igrac1);
    }

    public Igra() {
    }


}
