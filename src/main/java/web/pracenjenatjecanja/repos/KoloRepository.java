package web.pracenjenatjecanja.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import web.pracenjenatjecanja.dao.Kolo;

import java.util.List;

public interface KoloRepository extends JpaRepository<Kolo, Long> {
    List<Kolo> findAllByTurnirId(Long turnirId);
}
