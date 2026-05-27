package taskcoin.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import taskcoin.api.classes.Filhos;

public interface FilhosRepository extends JpaRepository<Filhos, Long> {
    UserDetails findByEmail(String email);
}
