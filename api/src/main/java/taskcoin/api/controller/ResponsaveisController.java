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
import taskcoin.api.classes.Responsaveis;
import taskcoin.api.records.DadosCadastroResponsavel;
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

}
