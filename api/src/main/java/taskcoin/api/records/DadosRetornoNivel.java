package taskcoin.api.records;

import taskcoin.api.classes.Niveis;

public record DadosRetornoNivel(
        Long nivel,
        String titulo_nivel,
        int tarefas_requeridas
) {
    public DadosRetornoNivel(Niveis dados){
        this(dados.getNivel(), dados.getTitulo_nivel(), dados.getTarefas_requeridas_nivel());
    }
}
