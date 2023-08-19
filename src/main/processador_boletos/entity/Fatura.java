package main.processador_boletos.entity;

import main.processador_boletos.enums.FaturaStatusEnum;

import java.time.LocalDate;

public class Fatura {

    private final LocalDate paymentDate;
    private final int value;
    private final String clientName;

    public Fatura(LocalDate paymentDate, int value, String clientName) {

        this.paymentDate = paymentDate;
        this.value = value;
        this.clientName = clientName;
    }

    public FaturaStatusEnum getStatus() {
        return FaturaStatusEnum.PAID;
    }
}
