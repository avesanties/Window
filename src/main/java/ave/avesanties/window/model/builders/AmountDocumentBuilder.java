package ave.avesanties.window.model.builders;

import java.math.BigDecimal;

public interface AmountDocumentBuilder {
  RateDocumentBuilder withAmount(BigDecimal amount);
}
