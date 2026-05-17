package taskcoin.api.records;

import taskcoin.api.classes.Filhos;
import taskcoin.api.classes.Responsaveis;

public record DadosRetornoFilho(
        Long id,
        String nome,
        String email,
        int saldo,
        int tarefas_concluidas,
        DadosRetornoResponsavel responsavel,
        DadosRetornoNivel nivel
) {
    public DadosRetornoFilho(Filhos dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getSaldo(), dados.getTarefas_concluidas(), new DadosRetornoResponsavel(dados.getResponsavel()), new DadosRetornoNivel(dados.getNivel()));
    }
}
