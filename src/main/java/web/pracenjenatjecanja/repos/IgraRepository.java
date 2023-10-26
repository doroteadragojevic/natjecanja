package web.pracenjenatjecanja.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import web.pracenjenatjecanja.dao.Igra;

public interface IgraRepository extends JpaRepository<Igra, Long> {
}
