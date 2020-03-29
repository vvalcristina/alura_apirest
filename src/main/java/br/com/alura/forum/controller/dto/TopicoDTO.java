package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;


public class TopicoDTO {

    private Long id;
    private String tituto;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDTO(Topico topico){
        this.id = topico.getId();
        this.tituto = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return tituto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public static Page<TopicoDTO> converter(Page<Topico> topicos) {
        /*map: pega cada um dos registros que estava dentro do Page de topico
        e transforma em um Page de Topico DtO   */
        return topicos.map(TopicoDTO::new);

    }
}
