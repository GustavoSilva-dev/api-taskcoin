package taskcoin.api.records;

import taskcoin.api.classes.Niveis;

public record DadosRetornoNivel(
        Long nivel,
        String titulo_nivel
) {
    public DadosRetornoNivel(Niveis dados){
        this(dados.getNivel(), dados.getTitulo_nivel());
    }
}
