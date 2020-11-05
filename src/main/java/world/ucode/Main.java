package world.ucode;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws FileNotFoundException {
    ModelDatabase data = new ModelDatabase();
    ModelTimers tim = new ModelTimers();
    tim.init();
    tim.initAnim();
    String[] type = new String[6];
    type[0] = "цыпленок желтый с красным клювом";
    type[1] = "голубь";
    type[2] = "панда";
    type[3] = "цыпленок желтый с черным клювом";
    type[4] = "цыпленок белый с красным клювом";
    type[5] = "цыпленок белый с черным клювом";
    View view = new View();
    view.gameover(data, stage);
    view.gamePlay(tim, type, stage);
    view.newGame(tim, data, stage, type);
    view.loadGame( tim, type, data, stage);
    view.gameMenu(stage, view.newGam, view.lod);
    stage.setScene(view.gMenu);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
