package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @Pattern(
                regexp = "^[0-9]{11}$",
                message = "CPF deve conter exatamente 11 dígitos numéricos"
        )
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco
) {

}
