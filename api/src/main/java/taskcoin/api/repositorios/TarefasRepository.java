package taskcoin.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import taskcoin.api.classes.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
}
