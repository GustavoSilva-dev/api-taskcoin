package taskcoin.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import taskcoin.api.classes.Filhos;

public interface FilhosRepository extends JpaRepository<Filhos, Long> {
}
