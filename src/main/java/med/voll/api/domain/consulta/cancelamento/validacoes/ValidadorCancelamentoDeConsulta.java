package med.voll.api.domain.consulta.cancelamento.validacoes;

import med.voll.api.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
