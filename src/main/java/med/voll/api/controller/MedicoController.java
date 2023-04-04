package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("medicos")
public class MedicoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicoController.class);

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        LOGGER.info("Cadastrando novo medico: " + dados.nome() + ". CRM: " + dados.crm());
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        LOGGER.info("Listando médicos");
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedico dados) {
        LOGGER.info("Atualizando dados cadastrais do medico: " + dados.nome() + ". ID: " + dados.id());
        var medico = repository.getReferenceById(id);
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        LOGGER.info("Excluindo médico: " + medico.getNome() + ". CRM: " + medico.getCrm());
        medico.excluir();
    }


}
