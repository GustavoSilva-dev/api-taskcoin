package taskcoin.api.records;

import taskcoin.api.classes.Tarefas;

public record DadosRetornoStatusTarefa(
        Long id,
        String nome,
        statusTarefa status,
        int valor
) {
    public DadosRetornoStatusTarefa(Tarefas tarefa){
        this(tarefa.getId(), tarefa.getNome(), tarefa.getStatus(), tarefa.getValor());
    }
}
