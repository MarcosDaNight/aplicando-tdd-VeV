package junit5Test.processador_boletos;

import junit5Test.processador_boletos.dto.BoletoPagamento;
import main.processador_boletos.ProcessadorBoleto;
import main.processador_boletos.entity.Boleto;
import main.processador_boletos.entity.Fatura;
import main.processador_boletos.entity.Pagamento;
import main.processador_boletos.enums.FaturaStatusEnum;
import main.processador_boletos.enums.PaymentTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class AnaliseValoresLimiteTest {

    @DisplayName("Colocar processar fatura de forma correta")
    @ParameterizedTest
    @MethodSource(value = "makeAnaliseValoresLimiteTestCase")
    void testBoletoProcessorWithAnaliseValoresLimites(FaturaStatusEnum faturaStatus, Fatura fatura,
                                                      List<BoletoPagamento> boletoPagamentos) {

        List<Boleto> boletos = boletoPagamentos.stream().map(BoletoPagamento::boleto).toList();
        ProcessadorBoleto processadorBoleto = new ProcessadorBoleto(fatura, boletos);
        processadorBoleto.process();

        Assertions.assertEquals(fatura.getStatus(), faturaStatus);
        for (BoletoPagamento boletoPagamento : boletoPagamentos) {
            Assertions.assertEquals(boletoPagamento.boleto().getPayment(), boletoPagamento.pagamento());
        }
    }

    private static Stream<Arguments> makeAnaliseValoresLimiteTestCase() {
        Fatura fatura1 = new Fatura(LocalDate.now(), 1000, "Marcos da Night");
        Fatura fatura2 = new Fatura(LocalDate.now(), 800, "Marcos da Night");
        Fatura fatura3 = new Fatura(LocalDate.now(), 1500, "Marcos da Night");
        Fatura fatura4 = new Fatura(LocalDate.now(), 0, "Marcos da Night");

        FaturaStatusEnum faturaStatusPago = FaturaStatusEnum.PAID;
        FaturaStatusEnum faturaStatusNaoPago = FaturaStatusEnum.NOT_PAID;

        Boleto boleto1 = new Boleto("1", LocalDate.now(), 500);
        Boleto boleto2 = new Boleto("2", LocalDate.now(), 500);
        Boleto boleto3 = new Boleto("3", LocalDate.now(), 200);
        Boleto boleto4 = new Boleto("4", LocalDate.now(), 600);
        Boleto boleto5 = new Boleto("5", LocalDate.now(), 100);
        Boleto boleto6 = new Boleto("6", LocalDate.now(), 200);
        Boleto boleto7 = new Boleto("7", LocalDate.now(), 0);
        Boleto boleto8 = new Boleto("8", LocalDate.now(), 0);

        Pagamento pagamento1 = new Pagamento(boleto1.getValue(), boleto1.getDate(), PaymentTypeEnum.BOLETO);
        Pagamento pagamento2 = new Pagamento(boleto2.getValue(), boleto2.getDate(), PaymentTypeEnum.BOLETO);
        Pagamento pagamento4 = new Pagamento(boleto4.getValue(), boleto4.getDate(), PaymentTypeEnum.BOLETO);
        Pagamento pagamento5 = new Pagamento(boleto5.getValue(), boleto5.getDate(), PaymentTypeEnum.BOLETO);
        Pagamento pagamento6 = new Pagamento(boleto6.getValue(), boleto6.getDate(), PaymentTypeEnum.BOLETO);
        Pagamento pagamento7 = new Pagamento(boleto7.getValue(), boleto7.getDate(), PaymentTypeEnum.BOLETO);
        Pagamento pagamento8 = new Pagamento(boleto8.getValue(), boleto8.getDate(), PaymentTypeEnum.BOLETO);

        List<BoletoPagamento> boletoPagamentosTest1 = List.of(
                new BoletoPagamento(new Boleto(boleto1), pagamento1),
                new BoletoPagamento(new Boleto(boleto2), pagamento2)
        );

        List<BoletoPagamento> boletoPagamentosTest2 = List.of(
                new BoletoPagamento(new Boleto(boleto1), null),
                new BoletoPagamento(new Boleto(boleto3), null)
        );

        List<BoletoPagamento> boletoPagamentosTest3 = List.of(
                new BoletoPagamento(new Boleto(boleto1), pagamento1),
                new BoletoPagamento(new Boleto(boleto2), pagamento2),
                new BoletoPagamento(new Boleto(boleto4), pagamento4)
        );

        List<BoletoPagamento> boletoPagamentosTest4 = List.of(
                new BoletoPagamento(new Boleto(boleto5), pagamento5),
                new BoletoPagamento(new Boleto(boleto6), pagamento6)
        );

        List<BoletoPagamento> boletoPagamentosTest5 = List.of(
                new BoletoPagamento(new Boleto(boleto7), pagamento7),
                new BoletoPagamento(new Boleto(boleto8), pagamento8)
        );

        return Stream.of(
            Arguments.of(faturaStatusPago, fatura1, boletoPagamentosTest1),
            Arguments.of(faturaStatusNaoPago, fatura2, boletoPagamentosTest2),
            Arguments.of(faturaStatusPago, fatura3, boletoPagamentosTest3),
            Arguments.of(faturaStatusPago, fatura4, boletoPagamentosTest4),
            Arguments.of(faturaStatusPago, fatura4, boletoPagamentosTest5)
        );
    }

    @Test
    @DisplayName("Deve lançar uma exception quando boleto possui valores negativo")
    void test5() {
        Assertions.assertThrows(Exception.class, () -> new Boleto("1", LocalDate.now(), -100));
    }

    @Test
    @DisplayName("Deve lançar uma exception quando fatura possui valores negativo")
    void test6() {
        Boleto boleto1 = new Boleto("1", LocalDate.now(), 100);
        Boleto boleto2 = new Boleto("2", LocalDate.now(), 200);
        Assertions.assertThrows(Exception.class, () -> new Fatura(LocalDate.now(), 0, "Marcos da Night"));
    }
}
