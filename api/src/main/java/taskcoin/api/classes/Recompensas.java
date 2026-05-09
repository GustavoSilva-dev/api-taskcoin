package taskcoin.api.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taskcoin.api.records.DadosCriarRecompensa;
import taskcoin.api.records.statusRecompensa;

@Table(name = "recompensas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Recompensas {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_recompensa")
    private String nome;

    @Column(name = "valor_recompensa")
    private int valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_recompensa")
    private statusRecompensa status;

    @ManyToOne
    @JoinColumn(name = "id_filho")
    private Filhos filho;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsaveis responsavel;

    public Recompensas(DadosCriarRecompensa dados, Filhos filho, Responsaveis responsavel){
        this.nome = dados.nome_recompensa();
        this.valor = dados.valor_recompensa();
        this.status = dados.status_recompensa();
        this.filho = filho;
        this.responsavel = responsavel;
    }
}
