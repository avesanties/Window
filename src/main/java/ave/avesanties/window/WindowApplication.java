package ave.avesanties.window;

import java.awt.EventQueue;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import ave.avesanties.window.controller.Presenter;
import ave.avesanties.window.controller.MainForm;

@SpringBootApplication
public class WindowApplication {

  public static void main(String[] args) {
    var ctx = new SpringApplicationBuilder(WindowApplication.class).headless(false).run(args);

    var controller = new MainForm(ctx.getBean("presenter", Presenter.class));

    EventQueue.invokeLater(() -> {
      controller.initPanels();
    });
  }

}
