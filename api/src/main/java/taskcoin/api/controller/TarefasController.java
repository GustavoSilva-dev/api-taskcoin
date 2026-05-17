package taskcoin.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskcoin.api.classes.Tarefas;
import taskcoin.api.records.DadosAlterarTarefas;
import taskcoin.api.records.DadosCriarTarefa;
import taskcoin.api.records.DadosRetornoStatusTarefa;
import taskcoin.api.records.statusTarefa;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;
import taskcoin.api.repositorios.TarefasRepository;
import taskcoin.api.services.FilhosNivelService;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private FilhosNivelService nivelService;

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

    @PutMapping
    @Transactional
    public ResponseEntity AlterarTarefas(@RequestBody @Valid DadosAlterarTarefas dados){
        var tarefa = repository.getReferenceById(dados.id_tarefa());
        tarefa.atualizarTarefas(dados);

        if(tarefa.getStatus() == statusTarefa.CONCLUIDA){
            var filhoId = tarefa.getFilho().getId();
            var filhoTarefas = tarefa.getFilho().getTarefas_concluidas();
            nivelService.verificarNivel(filhoId, filhoTarefas);
        }

        return ResponseEntity.ok().body(new DadosRetornoStatusTarefa(tarefa));
    }
}
