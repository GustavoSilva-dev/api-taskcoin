package taskcoin.api.records;

import taskcoin.api.classes.Responsaveis;

import java.util.List;

public record DadosRetornoResponsavel(
        Long id,
        String nome,
        String email,
        List<DadosRetornoFilho> filhos
) {
    public DadosRetornoResponsavel(Responsaveis responsavel){
        this(responsavel.getId(), responsavel.getNome_pai(), responsavel.getEmail_pai(), responsavel.getFilhos().stream().map(DadosRetornoFilho::new).toList());
    }
}
