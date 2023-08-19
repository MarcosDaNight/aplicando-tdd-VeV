package main.processador_boletos.entity;

import main.processador_boletos.enums.PaymentTypeEnum;

import java.time.LocalDate;
import java.util.Objects;

public class Boleto {

    private final String code;
    private final LocalDate paymentDate;
    private final int value;

    public Boleto(String code, LocalDate paymentDate, int value) {
        this.code = code;
        this.paymentDate = paymentDate;
        this.value = value;
    }


    public Pagamento getPagamento() {
        return new Pagamento(value, paymentDate, PaymentTypeEnum.BOLETO);
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boleto boleto = (Boleto) o;
        return value == boleto.value && Objects.equals(code, boleto.code) && Objects.equals(paymentDate, boleto.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, paymentDate, value);
    }
}
