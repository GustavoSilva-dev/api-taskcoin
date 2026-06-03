package taskcoin.backend.records;

import taskcoin.backend.classes.Filhos;

import java.util.List;

public record DadosRetornoFilho(
        Long id,
        String nome,
        String email,
        int saldo,
        int tarefas_concluidas,
        DadosRetornoNivel nivel,
        List<DadosRetornoTarefas> tarefas,
        List<DadosRetornoRecompensa> recompensas,
        DadosRetornoResponsavelAssociado responsavel
) {
    public DadosRetornoFilho(Filhos dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getSaldo(), dados.getTarefas_concluidas(), new DadosRetornoNivel(dados.getNivel()),
                dados.getTarefas() == null ? List.of() : dados.getTarefas().stream().map(DadosRetornoTarefas::new).toList(), dados.getRecompensas() == null ? List.of() : dados.getRecompensas().stream().map(DadosRetornoRecompensa::new).toList(), new DadosRetornoResponsavelAssociado(dados.getResponsavel()));
    }
}
