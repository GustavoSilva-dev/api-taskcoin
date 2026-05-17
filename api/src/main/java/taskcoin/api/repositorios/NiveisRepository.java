package taskcoin.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import taskcoin.api.classes.Niveis;

public interface NiveisRepository extends JpaRepository<Niveis, Long> {
}
