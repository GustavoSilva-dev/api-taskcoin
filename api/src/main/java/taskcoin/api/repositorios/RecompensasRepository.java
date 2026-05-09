package taskcoin.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import taskcoin.api.classes.Recompensas;

public interface RecompensasRepository extends JpaRepository<Recompensas, Long> {
}
