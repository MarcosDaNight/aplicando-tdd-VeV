package main.processador_boletos.entity;

import main.processador_boletos.enums.PaymentTypeEnum;

import java.time.LocalDate;
import java.util.Objects;

public class Pagamento {
    private final double value;
    private final LocalDate date;
    private final PaymentTypeEnum paymentType;

    public Pagamento(double value, LocalDate date, PaymentTypeEnum paymentType) {
        this.value = value;
        this.date = date;
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Double.compare(pagamento.value, value) == 0 && Objects.equals(date, pagamento.date) && paymentType == pagamento.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, date, paymentType);
    }
}
