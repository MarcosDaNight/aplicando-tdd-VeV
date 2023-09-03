package functionalTests.processador_boletos;

import main.processador_boletos.ProcessadorBoleto;
import main.processador_boletos.entity.Boleto;
import main.processador_boletos.entity.Fatura;
import main.processador_boletos.entity.Pagamento;
import main.processador_boletos.enums.FaturaStatusEnum;
import main.processador_boletos.enums.PaymentTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TabelaDecisaoTest {

    @Test
    @DisplayName("Deve colocar Fatura marcada como PAGA e dois pagamentos do tipo BOLETO criados.")
    void test1() {
        Boleto boleto1 = new Boleto("1", LocalDate.now(), 1200);
        Boleto boleto2 = new Boleto("2", LocalDate.now(), 500);
        Fatura fatura = new Fatura(LocalDate.now(), 1500, "Marcos da Night");
        ProcessadorBoleto processadorBoleto = new ProcessadorBoleto(fatura, List.of(boleto1, boleto2));
        processadorBoleto.process();

        Assertions.assertEquals(fatura.getStatus(), FaturaStatusEnum.PAID);
        Assertions.assertEquals(boleto1.getPayment(), new Pagamento(boleto1.getValue(), boleto1.getDate(), PaymentTypeEnum.BOLETO));
        Assertions.assertEquals(boleto2.getPayment(), new Pagamento(boleto2.getValue(), boleto2.getDate(), PaymentTypeEnum.BOLETO));
    }

    @Test
    @DisplayName("Deve colocar Fatura marcada como PAGA e três pagamentos do tipo BOLETO criados.")
    void test2() {
        Boleto boleto1 = new Boleto("1", LocalDate.now(), 500);
        Boleto boleto2 = new Boleto("2", LocalDate.now(), 400);
        Boleto boleto3 = new Boleto("2", LocalDate.now(), 600);
        Fatura fatura = new Fatura(LocalDate.now(), 1500, "Marcos da Night");
        ProcessadorBoleto processadorBoleto = new ProcessadorBoleto(fatura, List.of(boleto1, boleto2, boleto3));
        processadorBoleto.process();

        Assertions.assertEquals(fatura.getStatus(), FaturaStatusEnum.PAID);
        Assertions.assertEquals(boleto1.getPayment(), new Pagamento(boleto1.getValue(), boleto1.getDate(), PaymentTypeEnum.BOLETO));
        Assertions.assertEquals(boleto2.getPayment(), new Pagamento(boleto2.getValue(), boleto2.getDate(), PaymentTypeEnum.BOLETO));
        Assertions.assertEquals(boleto3.getPayment(), new Pagamento(boleto3.getValue(), boleto3.getDate(), PaymentTypeEnum.BOLETO));
    }

    @Test
    @DisplayName("Deve colocar Fatura marcada como NÃO PAGA e nenhuma pagamento do tipo BOLETO criado.")
    void test3() {
        Boleto boleto1 = new Boleto("1", LocalDate.now(), 100);
        Boleto boleto2 = new Boleto("2", LocalDate.now(), 200);
        Fatura fatura = new Fatura(LocalDate.now(), 500, "Marcos da Night");
        ProcessadorBoleto processadorBoleto = new ProcessadorBoleto(fatura, List.of(boleto1, boleto2));
        processadorBoleto.process();

        Assertions.assertEquals(fatura.getStatus(), FaturaStatusEnum.NOT_PAID);
        Assertions.assertNull(boleto1.getPayment());
        Assertions.assertNull(boleto2.getPayment());
    }

}
