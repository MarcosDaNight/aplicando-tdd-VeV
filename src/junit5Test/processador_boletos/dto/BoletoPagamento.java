package junit5Test.processador_boletos.dto;

import main.processador_boletos.entity.Boleto;
import main.processador_boletos.entity.Pagamento;

public record BoletoPagamento(Boleto boleto, Pagamento pagamento) {
}
