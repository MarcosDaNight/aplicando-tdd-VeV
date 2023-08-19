package tests.processador_boletos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ProcessadorBoletosTest {

    @Test
    @DisplayName("should pay fatura when fatura value is less than All Boletos' values")
    void shouldPayFaturaWhenFaturaValueIsLessThanBoletosValue() {
        Boleto boleto1 = new Boleto("1", LocalDate.now(), 500);
        Boleto boleto2 = new Boleto("2", LocalDate.now(), 400);
        Boleto boleto3 = new Boleto("1", LocalDate.now(), 600);
        Fatura fatura = new Fatura(LocalDate.now(), 1500, "Marcos da Night");
        ProcessadorBoleto processadorBoleto = new ProcessadorBoleto(fatura, List.of(boleto1, boleto2, boleto3));
        processadorBoleto.process();

        Assertions.assertEquals(fatura.getStatus(), FaturaStatusEnum.PAID);
        Assertions.assertEquals(boleto1.getPagamento(), new Pagamento(boleto1.getValue(), boleto1.getDate(), PaymentTypeEnum.BOLETO));
        Assertions.assertEquals(boleto2.getPagamento(), new Pagamento(boleto2.getValue(), boleto2.getDate(), PaymentTypeEnum.BOLETO));
        Assertions.assertEquals(boleto3.getPagamento(), new Pagamento(boleto3.getValue(), boleto3.getDate(), PaymentTypeEnum.BOLETO));
    }

}
