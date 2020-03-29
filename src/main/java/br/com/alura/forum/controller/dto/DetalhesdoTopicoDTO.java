package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//DTO devolve strings, data, enum
public class DetalhesdoTopicoDTO {

    private Long id;
    private String title;
    private String mensagem;
    private LocalDateTime dataDeCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDTO> respostas;

    public DetalhesdoTopicoDTO(Topico topico)
    {
        this.id = topico.getId();
        this.mensagem = topico.getMensagem();
        this.dataDeCriacao = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }
}
