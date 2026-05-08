package taskcoin.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskcoin.api.classes.Filhos;
import taskcoin.api.records.DadosRetornoFilho;
import taskcoin.api.records.DadosCadastroFilho;
import taskcoin.api.repositorios.FilhosRepository;
import taskcoin.api.repositorios.ResponsaveisRepository;

@RestController
@RequestMapping("/filhos")
public class FilhosController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ResponsaveisRepository repositoryPai;

    @Autowired
    private FilhosRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarFilhos(@RequestBody @Valid DadosCadastroFilho dados){
        var responsavel = repositoryPai.getReferenceById(dados.id_responsavel());
        var filho = new Filhos(dados, encoder, responsavel);
        repository.save(filho);

        return ResponseEntity.ok().body(new DadosRetornoFilho(filho));
    }
}
