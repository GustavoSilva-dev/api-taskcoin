package taskcoin.api.records;

import taskcoin.api.classes.Filhos;

public record DadosRetornoFilho(
        Long id,
        String nome,
        String email,
        int saldo
) {
    public DadosRetornoFilho(Filhos dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getSaldo());
    }
}
