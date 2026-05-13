package taskcoin.api.records;

import taskcoin.api.classes.Filhos;
import taskcoin.api.classes.Responsaveis;

public record DadosRetornoFilho(
        Long id,
        String nome,
        String email,
        int saldo,
        DadosRetornoResponsavel responsavel
) {
    public DadosRetornoFilho(Filhos dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getSaldo(), new DadosRetornoResponsavel(dados.getResponsavel()));
    }
}
