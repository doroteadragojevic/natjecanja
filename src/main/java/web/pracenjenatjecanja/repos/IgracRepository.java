package web.pracenjenatjecanja.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import web.pracenjenatjecanja.dao.Igrac;

public interface IgracRepository extends JpaRepository<Igrac, Long> {
}
