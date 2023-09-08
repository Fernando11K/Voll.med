package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import med.voll.api.paciente.DadosCadastroPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
    repository.save(new Medico(dados));

    }
//    @GetMapping
//    public Page<DadosListagemMedico> listar(Pageable paginacao){
//        return repository.findAll(paginacao).map(DadosListagemMedico::new);
//    }
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        repository.deleteById(id);
//
//    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

    }
//    @PostMapping("/lote")
//    @Transactional
//    public void cadastrarEmLote(@RequestBody List<DadosCadastroMedico> dados){
//        dados.stream()
//                .collect(Collectors.groupingBy(DadosCadastroMedico::especialidade))
//                .forEach((key, value) -> {
//                    String resultado = key +":" +value;
//                    System.out.println(resultado);
//                });
//
//    }

    @PostMapping("/lote")
    @Transactional
    public void cadastrarEmLote(@RequestBody @Valid List<DadosCadastroMedico> dados){
        dados.forEach(medico ->{
            repository.save(new Medico(medico));
        });

    }
}
