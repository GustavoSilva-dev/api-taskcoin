package taskcoin.api.records;

import taskcoin.api.classes.Recompensas;

public record DadosRetornoRecompensa(
    Long id_recompensa,
    String nome_recompensa,
    int valor_recompensa,
    DadosRetornoFilho filho
) {
    public DadosRetornoRecompensa(Recompensas recompensa){
        this(recompensa.getId(), recompensa.getNome(), recompensa.getValor(), new DadosRetornoFilho(recompensa.getFilho()));
    }
}
