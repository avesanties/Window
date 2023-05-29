package ave.avesanties.window.service;

import ave.avesanties.window.model.AbstractDocument;
import java.io.File;
import java.io.IOException;

public interface Readable  {
  AbstractDocument read (File file) throws IOException;
}
