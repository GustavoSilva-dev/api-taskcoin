package taskcoin.api.records;

import taskcoin.api.classes.Tarefas;

import java.time.LocalDate;

public record DadosRetornoTarefas(
        Long id_tarefa,
        String nome_tarefa,
        String descricao_tarefa,
        statusTarefa status_tarefa,
        int valor_tarefa,
        LocalDate expiracao_tarefa,
        DadosRetornoFilho filho
) {
    public DadosRetornoTarefas(Tarefas tarefa) {
        this(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getValor(), tarefa.getExpiracao(), new DadosRetornoFilho(tarefa.getFilho()));
    }
}
