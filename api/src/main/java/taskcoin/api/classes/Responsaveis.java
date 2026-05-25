package taskcoin.api.classes;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import taskcoin.api.records.DadosCadastroResponsavel;

import java.util.Collection;
import java.util.List;

@Table(name = "responsaveis")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Responsaveis implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_pai")
    private String nome_pai;

    @Column(name = "email_pai")
    private String email_pai;

    @Column(name = "senha_pai")
    private String senha_pai;

    @OneToMany(mappedBy = "responsavel")
    private List<Filhos> filhos;

    public Responsaveis(DadosCadastroResponsavel dados, PasswordEncoder encoder){
        this.email_pai = dados.email_pai();
        this.nome_pai = dados.nome_pai();
        this.senha_pai = encoder.encode(dados.senha_pai());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return senha_pai;
    }

    @Override
    public String getUsername() {
        return email_pai;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
