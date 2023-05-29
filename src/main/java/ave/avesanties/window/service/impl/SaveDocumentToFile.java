package ave.avesanties.window.service.impl;

import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.service.Writable;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import org.springframework.stereotype.Service;

@Service
public class SaveDocumentToFile implements Writable {

  private Gson gson;

  public SaveDocumentToFile(Gson gson) {
    this.gson = gson;
  }

  @Override
  public void write(AbstractDocument doc, File file) throws IOException {
    String json = gson.toJson(doc);
    Files.writeString(file.toPath(), json, StandardOpenOption.CREATE);
  }
}
