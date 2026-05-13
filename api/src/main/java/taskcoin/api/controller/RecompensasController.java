package taskcoin.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskcoin.api.classes.Filhos;
import taskcoin.api.classes.Recompensas;
import taskcoin.api.classes.Responsaveis;
import taskcoin.api.records.DadosAlterarRecompensa;
import taskcoin.api.records.DadosCriarRecompensa;
import taskcoin.api.records.DadosRetornoStatusRecompensa;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.RecompensasRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;

@RestController
@RequestMapping("/recompensas")
public class RecompensasController {

    @Autowired
    public RecompensasRepository repository;

    @Autowired
    public FilhosRepository repositoryFilho;

    @Autowired
    public ResponsaveisRepository repositoryResponsavel;

    @PostMapping
    @Transactional
    public ResponseEntity CriarRecompensas(@RequestBody @Valid DadosCriarRecompensa dados){
        var filho = repositoryFilho.getReferenceById(dados.id_filho());
        var responsavel = repositoryResponsavel.getReferenceById(dados.id_responsavel());
        var recompensa = new Recompensas(dados, filho, responsavel);
        repository.save(recompensa);

        return ResponseEntity.ok().body(recompensa);
    }

    @PutMapping
    @Transactional
    public ResponseEntity AlterarTarefas(@RequestBody @Valid DadosAlterarRecompensa dados){
        var recompensa = repository.getReferenceById(dados.id_recompensa());
        recompensa.alterarRecompensa(dados);

        return ResponseEntity.ok().body(new DadosRetornoStatusRecompensa(recompensa));
    }
}
