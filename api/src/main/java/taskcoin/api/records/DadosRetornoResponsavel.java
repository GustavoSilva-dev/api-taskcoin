package taskcoin.api.records;

import taskcoin.api.classes.Responsaveis;

public record DadosRetornoResponsavel(
        Long id,
        String nome,
        String email
) {
    public DadosRetornoResponsavel(Responsaveis responsavel){
        this(responsavel.getId(), responsavel.getNome_pai(), responsavel.getEmail_pai());
    }
}
