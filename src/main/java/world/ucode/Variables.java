package world.ucode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Variables {
  int happiness = 99;
  Text happinessy = new Text("счастье ");
  int hunger = 99;
  Text hungery = new Text("голод ");
  int health = 100;
  Text healthy = new Text("здоровье ");
  int thirst = 99;
  Text thirsty = new Text("жажда ");
  int cleanliness = 99;
  Text cleanlinessy = new Text("чистота ");
  int animal = 0;
  String zver = "";
  Image[] animals = new Image[13];
  Text nam = new Text();
  Text typ = new Text();
  ImageView chicken = new ImageView();
  ImageView tabs = new ImageView();
  ImageView wormy = new ImageView();
  ImageView drinky = new ImageView();
  ImageView shit = new ImageView();
  ImageView scoopy = new ImageView();
  Image [] some = new Image[5];
  String[] anim = new String[6];

  public void initAnim() {
    anim[0] = "желтый";
    anim[1] = "голубь";
    anim[2] = "панда";
    anim[3] = "желтыйЧерн";
    anim[4] = "белый";
    anim[5] = "белыйЧерн";
  }

  public Image[] init() {
    try {
    some[0] = new Image("/таблетка.png");
    some[1] = new Image("/червяк.png");
    some[2] = new Image("/вода.png");
    some[3] = new Image("/какашка.png");
    some[4] = new Image("/совок.png");
  } catch (Exception e) {
    System.out.println("Бросает что не находит");
  }
  return  some;
}
  public Image[] makeImage(int animal, String [] anim) {
    Image[] animals = new Image[13];
    for (int a = 0; a < 13; a++) {
      animals[a] = new Image("/" + anim[animal - 1] + (a + 1) + ".png");
    }
    return animals;
  }
}
