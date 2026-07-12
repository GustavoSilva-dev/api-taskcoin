package taskcoin.backend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import taskcoin.backend.classes.Filhos;
import taskcoin.backend.classes.Responsaveis;
import taskcoin.backend.records.DadosCadastroFilho;
import taskcoin.backend.records.DadosCadastroResponsavel;
import taskcoin.backend.records.DadosRetornoResponsavel;
import taskcoin.backend.repositorios.ResponsaveisRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class ResponsaveisControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroResponsavel> dadosCadastroResponsavelJson;

    @Autowired
    private JacksonTester<DadosRetornoResponsavel> dadosRetornoResponsavelJson;

    @MockitoBean
    private ResponsaveisRepository repository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Código 400 por falta de informações")
    void cadastrarResponsavel_cod400() throws Exception {
        var response = mvc.perform(post("/responsaveis")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retornar código 201 por cadastro completo")
    void cadastrarResponsavel_cod201() throws Exception {
        var dadosCadastro = new DadosCadastroResponsavel("Teste", "teste@gmail.com", "teste123");
        var responsavelTeste = new Responsaveis(dadosCadastro, passwordEncoder);

        when(passwordEncoder.encode(any())).thenReturn("criptografia_teste");
        when(repository.save(any())).thenReturn(responsavelTeste);

        var response = mvc.perform(post("/responsaveis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroResponsavelJson.write(dadosCadastro).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


}