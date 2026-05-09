package taskcoin.api.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResponsavel(
        @NotBlank
        String nome_pai,
        @NotBlank
        @Email
        String email_pai,
        @NotBlank
        String senha_pai
) {
}
