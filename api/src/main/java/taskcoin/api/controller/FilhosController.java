package taskcoin.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import taskcoin.api.classes.Filhos;
import taskcoin.api.records.DadosAlterarFilho;
import taskcoin.api.records.DadosRetornoFilho;
import taskcoin.api.records.DadosCadastroFilho;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.NiveisRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;
import taskcoin.api.services.FilhosNivelService;

@RestController
@RequestMapping("/filhos")
public class FilhosController {

    @Autowired
    private NiveisRepository repositoryNivel;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ResponsaveisRepository repositoryPai;

    @Autowired
    private FilhosRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarFilhos(@RequestBody @Valid DadosCadastroFilho dados){
        var nivel = repositoryNivel.getReferenceById(1L);
        var responsavel = repositoryPai.getReferenceById(dados.id_responsavel());
        var filho = new Filhos(dados, encoder, responsavel, nivel);
        repository.save(filho);

        return ResponseEntity.ok().body(new DadosRetornoFilho(filho));
    }

    @GetMapping
    public Page<DadosRetornoFilho> ListarFilhos(@PageableDefault(size=10, sort="nome") Pageable paginacao){
        return repository.findAll(paginacao).map(DadosRetornoFilho::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity EditarFilhos(@RequestBody @Valid DadosAlterarFilho dados){
        var filho = repository.getReferenceById(dados.id_filho());
        filho.alterarFilho(dados);
        repository.save(filho);

        return ResponseEntity.ok(new DadosRetornoFilho(filho));
    }

    @GetMapping("/detalhe-filho")
    public ResponseEntity DetalharFilho(Authentication authentication){
        var usuario = (Filhos) repository.findByEmail(authentication.getName());
        return ResponseEntity.ok(new DadosRetornoFilho(usuario));
    }
}
