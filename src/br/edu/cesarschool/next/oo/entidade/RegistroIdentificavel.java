package br.edu.cesarschool.next.oo.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class RegistroIdentificavel implements Serializable {

    private LocalDateTime dataHoraCriacao;

    public int obterTempoDeCriacao(){
        return (int) ChronoUnit.DAYS.between(dataHoraCriacao, LocalDateTime.now());
    }

    public abstract String obterChave();

    private static final long serialVersionUID = 1L;

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }


}
