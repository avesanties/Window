package ave.avesanties.window.service;

import ave.avesanties.window.model.AbstractDocument;
import java.io.File;
import java.io.IOException;

public interface Writable {
  void write (AbstractDocument doc, File file) throws IOException;
}
