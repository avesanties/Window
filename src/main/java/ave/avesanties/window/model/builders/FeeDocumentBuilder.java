package ave.avesanties.window.model.builders;

import java.math.BigDecimal;

public interface FeeDocumentBuilder {
  RateDocumentBuilder withFee(BigDecimal fee);
}
