package world.ucode;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ModelTimers extends Variables {
  protected boolean kapriz = false;
  protected boolean kapriz2 = false;
  PathTransition ptr = new PathTransition();
  PathTransition ptrtab = new PathTransition();
  PathTransition ptrdr = new PathTransition();
  PathTransition ptrwo = new PathTransition();
  PathTransition ptrso = new PathTransition();
  Timeline tt;
  Timeline helt;
  Timeline tl;

public void dance() {
  tt = new Timeline(new KeyFrame(Duration.seconds(0.25),
      new EventHandler<ActionEvent>() {
        int im = 0;

        @Override
        public void handle(ActionEvent actionEvent) {
          ptr.pause();
          if (ptr != null && ptrtab != null) {
            im = (im + 1 > 4) ? 1 : im + 1;
            if (im == 1)
              chicken.setImage(animals[8]);
            if (im == 2)
              chicken.setImage(animals[9]);
            if (im == 3)
              chicken.setImage(animals[10]);
            if (im == 4)
              chicken.setImage(animals[11]);
          }
        }
      }));
}

public void move() {
  tl = new Timeline(new KeyFrame(Duration.seconds(0.1),
      new EventHandler<ActionEvent>() {
        int im = 0;

        @Override
        public void handle(ActionEvent actionEvent) {
          if (ptrtab != null) {
            if (ptrtab.getStatus() == Animation.Status.STOPPED)
              tabs.setStyle("visibility: hidden;");
          }
          if (ptrwo != null) {
            if (ptrwo.getStatus() == Animation.Status.STOPPED)
              wormy.setStyle("visibility: hidden;");
          }
          if (ptrdr != null) {
            if (ptrdr.getStatus() == Animation.Status.STOPPED)
              drinky.setStyle("visibility: hidden;");
          }
          if (ptrso != null) {
            if (ptrso.getStatus() == Animation.Status.RUNNING)
              kapriz = true;
            if (ptrso.getStatus() == Animation.Status.STOPPED) {
              scoopy.setStyle("visibility: hidden;");
              if (kapriz == true) {
                shit.setStyle("visibility: hidden;");
                kapriz = false;
                kapriz2 = false;
              }
            }
          }
          if (ptr != null && ptrtab != null) {
            if (ptr.getCurrentRate() == 1.0 && ptr.getStatus() == Animation.Status.RUNNING && tt.getStatus() != Animation.Status.RUNNING) {
              im = (im + 1 > 4) ? 1 : im + 1;
              if (im == 1)
                chicken.setImage(animals[0]);
              if (im == 2)
                chicken.setImage(animals[1]);
              if (im == 3)
                chicken.setImage(animals[2]);
              if (im == 4)
                chicken.setImage(animals[3]);
            }

            if (ptr.getCurrentRate() == -1.0 && ptr.getStatus() == Animation.Status.RUNNING && tt.getStatus() != Animation.Status.RUNNING) {
              im = (im + 1 > 4) ? 1 : im + 1;
              if (im == 1)
                chicken.setImage(animals[4]);
              if (im == 2)
                chicken.setImage(animals[5]);
              if (im == 3)
                chicken.setImage(animals[6]);
              if (im == 4)
                chicken.setImage(animals[7]);
            }
            if (ptr.getStatus() == Animation.Status.PAUSED && tt.getStatus() != Animation.Status.RUNNING) {
              chicken.setImage(animals[12]);
            }
            if (ptr.getStatus() == Animation.Status.PAUSED && ptrtab.getStatus() == Animation.Status.STOPPED &&
                ptrwo.getStatus() == Animation.Status.STOPPED && ptrdr.getStatus() == Animation.Status.STOPPED &&
                ptrso.getStatus() == Animation.Status.STOPPED && tt.getStatus() == Animation.Status.STOPPED)
              ptr.play();
          }
        }
      }));
}
  //ПОКАЗАТЕЛИ
  public void showIt(Stage stage, Scene sceneOver) {
    helt = new Timeline(new KeyFrame(Duration.seconds(0.4),
        new EventHandler<ActionEvent>() {
          int hap = 0;
          int hu = 0;
          int hel = 0;
          int thr = 0;
          int cl = 0;

          @Override
          public void handle(ActionEvent actionEvent) {
            if (ptr != null) {
              hap++;
              hu++;
              hel++;
              thr++;
              cl++;
              happiness = happiness > 99 ? 99 : happiness ;
              hunger = hunger > 99 ? 99 : hunger ;
              health = health > 99 ? 99 : health;
              thirst = thirst > 99 ? 99 : thirst;
              cleanliness = cleanliness > 99 ? 99 : cleanliness;
              if (thr == 3) {
                thirst--;
                thirsty.setText("жажда " + thirst);
                thr = 0;
              }
              if (cl == 2) {
                cleanliness--;
                cleanlinessy.setText("чистота " + cleanliness);
                cl = 0;
              }
              if (hu == 2) {
                hunger--;
                hungery.setText("голод " + hunger);
                hu = 0;
              }
              if (hel == 3) {
                health--;
                healthy.setText("здоровье " + health);
                hel = 0;
              }
              if (hap == 4) {
                happiness--;
                happinessy.setText("cчастье: " + happiness);
                hap = 0;
              }
              if (cleanliness % 20 == 0 && !kapriz2) {
                shit.setImage(some[3]);
                kapriz2 = true;
                shit.setStyle("visibility: visible;");
                shit.setX(chicken.getBoundsInParent().getCenterX());
                shit.setY(chicken.getBoundsInParent().getCenterY());
              }
              if (thirst == 20)
                health -= 10;
              if (hunger == 20)
                health -= 10;
              if (happiness == 20)
                health -= 10;
              if (thirst <= 0) {
                thirst = 0;
                health -= 15;
              }
              if (hunger <= 0) {
                hunger = 0;
                health -= 15;
              }
              if (health <= 0) {
                health = 0;
                tt.stop();
                ptr.stop();
                ptrtab.stop();
                ptrwo.stop();
                ptrdr.stop();
                ptrso.stop();
                helt.stop();
                stage.setScene(sceneOver);
              }
              if (cleanliness <= 0) {
                cleanliness = 0;
                health -= 15;
              }
              if (happiness <= 0) {
                happiness = 0;
                health -= 15;
              }
            }
          }
        }));
  }
}