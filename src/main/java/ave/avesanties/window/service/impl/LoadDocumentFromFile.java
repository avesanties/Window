package ave.avesanties.window.service.impl;

import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.service.Readable;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class LoadDocumentFromFile implements Readable {
  
  private Gson gson;
  
  public LoadDocumentFromFile(Gson gson) {
    this.gson = gson;
  }
  
  @Override
  public AbstractDocument read(File file) throws IOException {
    return gson.fromJson(new FileReader(file), AbstractDocument.class);
  }
 
}