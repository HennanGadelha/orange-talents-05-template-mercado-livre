package com.mercadolivre.config.validacao;

public class ErrosDtoRequest {

    private String camopo;
    private String erro;

    public ErrosDtoRequest(String camopo, String erro) {
        this.camopo = camopo;
        this.erro = erro;
    }

    public String getCamopo() {
        return camopo;
    }

    public String getErro() {
        return erro;
    }
}
