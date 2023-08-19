package main.processador_boletos.entity;

import main.processador_boletos.enums.FaturaStatusEnum;

import java.time.LocalDate;
import java.util.Objects;

public class Fatura {

    private final LocalDate paymentDate;
    private final int value;
    private final String clientName;
    private FaturaStatusEnum faturaStatusEnum;

    public Fatura(LocalDate paymentDate, int value, String clientName) {

        this.paymentDate = paymentDate;
        this.value = value;
        this.clientName = clientName;
        this.faturaStatusEnum = FaturaStatusEnum.PENDING;
    }

    public FaturaStatusEnum getStatus() {
        return faturaStatusEnum;
    }

    public double getValue() {
        return value;
    }

    public void setStatus(FaturaStatusEnum faturaStatusEnum) {
        this.faturaStatusEnum = faturaStatusEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return value == fatura.value && Objects.equals(paymentDate, fatura.paymentDate) && Objects.equals(clientName, fatura.clientName) && faturaStatusEnum == fatura.faturaStatusEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentDate, value, clientName, faturaStatusEnum);
    }
}
