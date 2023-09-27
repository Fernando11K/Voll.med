package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.fernando.DadosFernando;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fernando")
public class FernandoController {

    @PostMapping
    public ResponseEntity fernando(@RequestBody @Valid DadosFernando dados){

        var dataFormatada = dados.getDataFormatada();
        System.out.println(dataFormatada);
        return ResponseEntity.ok(dados);

    }

}
