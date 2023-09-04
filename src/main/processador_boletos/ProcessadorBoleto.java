package main.processador_boletos;

import main.processador_boletos.entity.Boleto;
import main.processador_boletos.entity.Fatura;
import main.processador_boletos.entity.Pagamento;
import main.processador_boletos.enums.FaturaStatusEnum;
import main.processador_boletos.enums.PaymentTypeEnum;

import java.util.List;

public class ProcessadorBoleto {


    private final Fatura fatura;
    private final List<Boleto> boletos;

    public ProcessadorBoleto(Fatura fatura, List<Boleto> boletos) {
        this.fatura = fatura;
        this.boletos = boletos;
    }

    public void process() {
        double allBoletosValue = boletos
                .stream()
                .map(Boleto::getValue)
                .reduce(0.0, Double::sum);

        if (allBoletosValue >= fatura.getValue()) {
            fatura.setStatus(FaturaStatusEnum.PAID);
            boletos.forEach(boleto -> boleto.createPayment(PaymentTypeEnum.BOLETO));
        } else {
            fatura.setStatus(FaturaStatusEnum.NOT_PAID);
        }
    }
}
