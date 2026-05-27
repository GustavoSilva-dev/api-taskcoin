package taskcoin.api.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
        String email,
        String senha
) {
}
