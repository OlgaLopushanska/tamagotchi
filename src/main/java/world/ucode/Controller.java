package world.ucode;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Controller {
  boolean priznak = false;

  public void gameov(Button menu, ModelDatabase data, Stage stage, Scene gMenu) {
    menu.setOnAction(value -> {
      stage.setScene(gMenu);
    });
  }

  public boolean  guessGame(Button choice, TextField text2, int y, Button gou, Stage stage, Scene gamePlay, ModelTimers tim) {
   choice.setOnAction(value -> {
      try {
        int r = Integer.parseInt(text2.getText().trim());
        if (r > y)
          text2.setText("Твое число слишком большое. Попробуй еще");
        else if (r < y)
          text2.setText("Твое число слишком малое. Попробуй еще");
        else if (r == y) {
          text2.setText("Молодец! Угадал!");
          priznak = true;
        }
      }
      catch(NumberFormatException nfe) {
        text2.setText("Выбери еще раз ЧИСЛО");
      }
    });
    gou.setOnAction(value -> {
      text2.setText("");
      tim.helt.play();
      stage.setScene(gamePlay);
    });
    return priznak;
  }

  public void newgame(MenuItem menuItem1, MenuItem menuItem2, MenuItem menuItem3, ModelTimers tim, Button change1,
                      Button change2, Button plays, Stage stage, String[] type, Scene gamePlay, ModelDatabase data) {
    menuItem1.setOnAction(event -> {
      tim.animal = 1;
    });
    menuItem2.setOnAction(event -> {
      tim.animal = 2;
    });
    menuItem3.setOnAction(event -> {
      tim.animal = 3;
    });
    change1.setOnAction(value -> {
      switch (tim.animal) {
        case 1:
          tim.animal = 5;
          break;
        case 4:
          tim.animal = 6;
          break;
        case 5:
          tim.animal = 1;
          break;
        case 6:
          tim.animal = 4;
          break;
      }
    });
    change2.setOnAction(value -> {
      switch (tim.animal) {
        case 1:
          tim.animal = 4;
          break;
        case 4:
          tim.animal = 1;
          break;
        case 5:
          tim.animal = 6;
          break;
        case 6:
          tim.animal = 5;
          break;
      }
    });
    plays.setOnAction(value -> {
      if(tim.animal == 0)
        tim.animal = 1;
      try {
        tim.animals = tim.makeImage(tim.animal, tim.anim);
      }
      catch(Exception e) {
        System.out.println("не нашли картинку");
      }
      tim.nam.setText("имя " + tim.zver);
      tim.typ.setText("тип " + type[tim.animal -1]);
      tim.hunger = 99;
      tim.health = 100;
      tim.thirst = 99;
      tim.happiness = 99;
      tim.cleanliness = 99;
      stage.setScene(gamePlay);
      tim.helt.play();
      tim.ptr.play();
    });
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent event) {
        try {
          data.writeS(tim.zver, tim.animal, tim.happiness, tim.health, tim.hunger, tim.thirst, tim.cleanliness);
        }
        catch (SQLException ex){
          System.out.println("исключение базы данных");
        }
      }
    });
  }

  public void gameMenu(Button newGame, Stage stage, Scene newGam, Button load, Scene lod) {
    newGame.setOnAction(value -> {
      stage.setScene(newGam);
    });
    load.setOnAction(value -> {
      stage.setScene(lod);
    });
  }

 public void loadGame(Button come, ModelTimers tim, TextField tex, Stage stage, Scene gamePlay, Scene newGam, String[] type,
                      ModelDatabase data) {
   come.setOnAction(value -> {
     int[] values = new int[6];
     try {
       values = data.read(tex.getText());
       if (values == null)
         tex.setText("Такого питомца пока нет");
     } catch (ClassNotFoundException cl) {
       System.out.println("не нашел класс");
     } catch (SQLException ex) {
       System.out.println("ожибка скл");
     }
     if (values != null) {
       tim.nam.setText("имя " + tex.getText());
       tim.zver = tex.getText();
       tim.typ.setText("тип " + type[values[0] - 1]);
       tim.animal = values[0];
       tim.happiness = values[1];
       tim.health = values[2];
       tim.hunger = values[3];
       tim.thirst = values[4];
       tim.cleanliness = values[5];
       try {
         tim.animals = tim.makeImage(tim.animal, tim.anim);
       } catch (Exception e) {
         System.out.println("не нашли картинку");
       }
       stage.setScene(gamePlay);
       tim.helt.play();

     } else
       stage.setScene(newGam);
   });
 }

 public void gamePlay(Button medicine, ModelTimers tim, Button food, Group gr, Button drink, Button dance, Button clean){
   medicine.setOnAction(value -> {
     tim.tabs.setStyle("visibility: visible;");
     tim.ptr.pause();
     tim.tabs.setImage(tim.some[0]);
     Path pathy = new Path();
     pathy.getElements().add(new MoveTo(medicine.getBoundsInParent().getCenterX(), medicine.getBoundsInParent().getCenterY()));
     pathy.getElements().add(new LineTo(tim.chicken.getBoundsInParent().getCenterX(), tim.chicken.getBoundsInParent().getCenterY()));
     pathy.setStyle("visibility: hidden;");
     tim.ptrtab.setDuration(Duration.seconds(1));
     tim.ptrtab.setPath(pathy);
     tim.ptrtab.setNode((ImageView) tim.tabs);
     tim.ptrtab.setCycleCount(1);
     tim.ptrtab.setAutoReverse(false);
     tim.ptrtab.play();
     gr.getChildren().add(pathy);
     tim.health += 25;
   });
   tim.tabs.setY(1200);
   food.setOnAction(value -> {
     tim.wormy.setStyle("visibility: visible;");
     tim.ptr.pause();
     tim.wormy.setImage(tim.some[1]);
     Path pathy = new Path();
     pathy.getElements().add(new MoveTo(food.getBoundsInParent().getCenterX(), food.getBoundsInParent().getCenterY()));
     pathy.getElements().add(new LineTo(tim.chicken.getBoundsInParent().getCenterX(), tim.chicken.getBoundsInParent().getCenterY()));
     pathy.setStyle("visibility: hidden;");
     tim.ptrwo.setDuration(Duration.seconds(1));
     tim.ptrwo.setPath(pathy);
     tim.ptrwo.setNode((ImageView) tim.wormy);
     tim.ptrwo.setCycleCount(1);
     tim.ptrwo.setAutoReverse(false);
     tim.ptrwo.play();
     gr.getChildren().add(pathy);
     tim.hunger += 25;
   });
   tim.wormy.setX(1200);
   drink.setOnAction(value -> {
     tim.drinky.setStyle("visibility: visible;");
     tim.ptr.pause();
     tim.drinky.setImage(tim.some[2]);
     Path pathy = new Path();
     pathy.getElements().add(new MoveTo(drink.getBoundsInParent().getCenterX(), drink.getBoundsInParent().getCenterY()));
     pathy.getElements().add(new LineTo(tim.chicken.getBoundsInParent().getCenterX(), tim.chicken.getBoundsInParent().getCenterY()));
     pathy.setStyle("visibility: hidden;");
     tim.ptrdr.setDuration(Duration.seconds(1));
     tim.ptrdr.setPath(pathy);
     tim.ptrdr.setNode((ImageView) tim.drinky);
     tim.ptrdr.setCycleCount(1);
     tim.ptrdr.setAutoReverse(false);
     tim.ptrdr.play();
     gr.getChildren().add(pathy);
     tim.thirst += 25;
   });
   tim.drinky.setX(1200);
   dance.setOnAction(value -> {
     tim.ptr.pause();
     tim.dance();
     tim.tt.setCycleCount(36);
     tim.tt.setAutoReverse(false);
     tim.tt.play();
     tim.happiness += 25;
   });
   clean.setOnAction(value -> {
     tim.scoopy.setStyle("visibility: visible;");
     tim.ptr.pause();
     tim.scoopy.setImage(tim.some[4]);
     Path pathy = new Path();
     pathy.getElements().add(new MoveTo(clean.getBoundsInParent().getCenterX(), clean.getBoundsInParent().getCenterY()));
     pathy.getElements().add(new LineTo(tim.shit.getBoundsInParent().getCenterX(), tim.shit.getBoundsInParent().getCenterY()));
     pathy.setStyle("visibility: hidden;");
     tim.ptrso.setDuration(Duration.seconds(1));
     tim.ptrso.setPath(pathy);
     tim.ptrso.setNode((ImageView) tim.scoopy);
     tim.ptrso.setCycleCount(1);
     tim.ptrso.setAutoReverse(false);
     tim.ptrso.play();
     gr.getChildren().add(pathy);
     tim.cleanliness += 18;
   });
  }
}
