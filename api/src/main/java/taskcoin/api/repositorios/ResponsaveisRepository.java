package taskcoin.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import taskcoin.api.classes.Responsaveis;

public interface ResponsaveisRepository extends JpaRepository<Responsaveis, Long> {
     UserDetails findByEmail(String email);
}
