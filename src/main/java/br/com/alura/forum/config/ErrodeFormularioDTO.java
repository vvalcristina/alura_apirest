package br.com.alura.forum.config;

public class ErrodeFormularioDTO {

    private String campo;
    private String erro;

    public ErrodeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
