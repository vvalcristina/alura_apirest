package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Resposta;

import java.time.LocalDateTime;

public class RespostaDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataDeCriacao;
    private String nomeAutor;

    public RespostaDTO(Resposta resposta){
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataDeCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

    //DTO não precisa de setter

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataDeCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
