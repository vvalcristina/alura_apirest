package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesdoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //Parâmetro obrigatorio: @RequestParam
    //Quando se usa paginação o retorna Page
    @GetMapping
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
                                 @RequestParam int pagina, int quantidade){

        Pageable paginacao = PageRequest.of(pagina, quantidade);

        if(nomeCurso == null){
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDTO.converter(topicos);
        }else{
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico= form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{Id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesdoTopicoDTO> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            return ResponseEntity.ok(new DetalhesdoTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm form ){
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}