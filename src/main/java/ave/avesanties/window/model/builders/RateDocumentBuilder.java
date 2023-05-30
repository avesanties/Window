package ave.avesanties.window.model.builders;

import java.math.BigDecimal;

public interface RateDocumentBuilder {
  CurrencyDocumentBuilder withRate(BigDecimal rate);
}
