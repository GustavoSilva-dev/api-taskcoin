package taskcoin.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskcoin.api.classes.Tarefas;
import taskcoin.api.records.DadosCriarTarefa;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;
import taskcoin.api.repositorios.TarefasRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefasRepository repository;

    @Autowired
    private FilhosRepository repositoryFilhos;

    @Autowired
    private ResponsaveisRepository repositoryResponsaveis;

    @PostMapping
    @Transactional
    public ResponseEntity CriarTarefas(@RequestBody @Valid DadosCriarTarefa dados){
        var filho = repositoryFilhos.getReferenceById(dados.id_filho());
        var responsavel = repositoryResponsaveis.getReferenceById(dados.id_responsavel());
        var tarefa = new Tarefas(dados, filho, responsavel);
        repository.save(tarefa);

        return ResponseEntity.ok().body(tarefa);
    }
}
