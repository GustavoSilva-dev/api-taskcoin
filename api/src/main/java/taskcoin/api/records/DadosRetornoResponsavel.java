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
        this(responsavel.getId(), responsavel.getNome(), responsavel.getEmail(),
                responsavel.getFilhos() == null ? List.of() : responsavel.getFilhos().stream().map(DadosRetornoFilho::new).toList());
    }
}
