package ave.avesanties.window.service.impl;

import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.model.Invoice;
import ave.avesanties.window.model.Payment;
import ave.avesanties.window.model.PaymentApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

  @Bean
  Gson gson() {
    GsonBuilder gb = new GsonBuilder();
    gb.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
    gb.registerTypeAdapter(AbstractDocument.class, new DocAdapter());
    gb.setPrettyPrinting();
    return gb.create();
  }

  private class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(src.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {

      return LocalDate.parse(json.getAsString());
    }
  }

  private class DocAdapter implements JsonDeserializer<AbstractDocument> {

    private static final Map<String, Class> CLASSES = Map.of("Платёжка", Payment.class, "Накладная",
        Invoice.class, "Заявка на оплату", PaymentApplication.class);

    @Override
    public AbstractDocument deserialize(JsonElement json, Type typeOfT,
        JsonDeserializationContext context) throws JsonParseException {

      String type = json.getAsJsonObject().get("type").getAsString();
      if (CLASSES.containsKey(type)) {
        return context.deserialize(json, CLASSES.get(type));
      } else {
        throw new IllegalArgumentException("No supported class found for: " + type);
      }
    }
  }
}
