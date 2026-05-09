package taskcoin.api.classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import taskcoin.api.records.DadosCriarTarefa;
import taskcoin.api.records.statusTarefa;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Table(name = "tarefas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Tarefas {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_tarefa")
    private String nome;

    @Column(name = "valor_tarefa")
    private int valor;

    @Column(name = "descricao_tarefa")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_tarefa")
    private statusTarefa status;

    @Column(name = "expiracao_tarefa")
    private LocalDate expiracao;

    @ManyToOne
    @JoinColumn(name = "id_filho")
    private Filhos filho;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsaveis responsavel;

    public Tarefas(DadosCriarTarefa dados, Filhos filho, Responsaveis responsavel){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nome = dados.nome_tarefa();
        this.valor = dados.valor_tarefa();
        this.descricao = dados.descricao_tarefa();
        this.expiracao = dados.expiracao_tarefa();
        this.status = dados.status_tarefa();
        this.filho = filho;
        this.responsavel = responsavel;
    }
}
