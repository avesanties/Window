package ave.avesanties.window.model.builders;

import ave.avesanties.window.model.AbstractDocument;

public interface FinalDocumentBuilder<T extends AbstractDocument> {
  T build();
}
