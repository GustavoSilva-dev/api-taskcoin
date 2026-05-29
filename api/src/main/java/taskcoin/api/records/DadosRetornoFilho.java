package taskcoin.api.records;

import taskcoin.api.classes.Filhos;
import taskcoin.api.classes.Responsaveis;

import java.util.List;

public record DadosRetornoFilho(
        Long id,
        String nome,
        String email,
        int saldo,
        int tarefas_concluidas,
        DadosRetornoNivel nivel,
        List<DadosRetornoTarefas> tarefas
) {
    public DadosRetornoFilho(Filhos dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getSaldo(), dados.getTarefas_concluidas(), new DadosRetornoNivel(dados.getNivel()),
                dados.getTarefas() == null ? List.of() : dados.getTarefas().stream().map(DadosRetornoTarefas::new).toList());
    }
}
