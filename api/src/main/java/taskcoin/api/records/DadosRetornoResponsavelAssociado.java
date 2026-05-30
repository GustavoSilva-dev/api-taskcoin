package taskcoin.api.records;

import taskcoin.api.classes.Responsaveis;

public record DadosRetornoResponsavelAssociado(
        String nome,
        String email
) {
    public DadosRetornoResponsavelAssociado(Responsaveis responsavel){
        this(responsavel.getNome(), responsavel.getEmail());
    }
}
