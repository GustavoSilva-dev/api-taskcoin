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
import taskcoin.api.classes.Responsaveis;
import taskcoin.api.records.DadosCadastroResponsavel;
import taskcoin.api.records.DadosRetornoFilho;
import taskcoin.api.records.DadosRetornoResponsavel;
import taskcoin.api.repositorios.ResponsaveisRepository;

@RestController
@RequestMapping("/responsaveis")
public class ResponsaveisController {

    @Autowired
    private ResponsaveisRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarResponsavel(@RequestBody @Valid DadosCadastroResponsavel dados){
        var responsavel = new Responsaveis(dados, encoder);
        repository.save(responsavel);

        return ResponseEntity.ok().body(new DadosRetornoResponsavel(responsavel));
    }

    @GetMapping
    public Page<DadosRetornoResponsavel> ListarResponsaveis(@PageableDefault(size=10) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosRetornoResponsavel::new);
    }

    @GetMapping("/detalhe-responsavel")
    public ResponseEntity DetalharResponsavel(Authentication authentication){
        var usuario = (Responsaveis) repository.findByEmail(authentication.getName());
        return ResponseEntity.ok(new DadosRetornoResponsavel(usuario));
    }
}
