package taskcoin.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;

@Service
@Primary
public class AuthService implements UserDetailsService {

    @Autowired
    private ResponsaveisRepository responsaveisRepository;

    @Autowired
    private FilhosRepository filhosRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var responsavel = responsaveisRepository.findByEmail(email);
        if (responsavel != null) return responsavel;

        var filho = filhosRepository.findByEmail(email);
        if (filho != null) return filho;

        throw new UsernameNotFoundException("Usuário não encontrado: " + email);
    }
}
