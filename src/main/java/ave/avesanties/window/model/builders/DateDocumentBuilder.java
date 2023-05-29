package ave.avesanties.window.model.builders;

import java.time.LocalDate;

public interface DateDocumentBuilder {
  UsernameDocumentBuilder withDate(LocalDate date);
}
