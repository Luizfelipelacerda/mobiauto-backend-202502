package com.luizlacerda.mobiauto_backend_202502.Enum;

public enum Status {

    NOVO("NOVO"),

    EM_ATENDIMENTO("EM_ATENDIMENTO"),

    CONCLUIDO("CONCLUIDO");

    public final String label;

    private Status(String label) {
        this.label = label;
    }

}
