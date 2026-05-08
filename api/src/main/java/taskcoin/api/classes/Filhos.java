package taskcoin.api.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import taskcoin.api.records.DadosCadastroFilho;
import taskcoin.api.repositorios.FilhosRepository;

import java.util.Collection;
import java.util.List;

@Table(name = "filhos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Filhos implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_filho")
    private String nome;

    @Column(name = "email_filho")
    private String email;

    @Column(name = "senha_filho")
    private String senha;

    @Column(name = "saldo_pontos")
    private int saldo;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsaveis responsavel;

    public Filhos(DadosCadastroFilho dados, PasswordEncoder encoder, Responsaveis responsavel){
        this.nome = dados.nome_filho();
        this.email = dados.email_filho();
        this.senha = encoder.encode(dados.email_filho());
        this.saldo = 0;
        this.responsavel = responsavel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
