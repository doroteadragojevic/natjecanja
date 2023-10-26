package web.pracenjenatjecanja.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import web.pracenjenatjecanja.dao.Turnir;

import java.util.List;

public interface TurnirRepository extends JpaRepository<Turnir, Long> {
    List<Turnir> findByEmail(String email);
}
