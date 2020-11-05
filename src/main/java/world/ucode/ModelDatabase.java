package world.ucode;

import java.sql.*;

public class ModelDatabase {

  public static Connection conn;
  public static Statement statmt;
  public static ResultSet resSet;

  public static void conn() throws ClassNotFoundException, SQLException {
    conn = null;
    Class.forName("org.sqlite.JDBC");
    conn = DriverManager.getConnection("jdbc:sqlite:src/Pitomets.db");
    System.out.println("подключение к базе данных:: ок");
  }

  public static void create() throws ClassNotFoundException, SQLException {
    statmt = conn.createStatement();
    statmt.execute("CREATE TABLE if not exists pitoms (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
        " type INT, happiness INT, health INT, hunger INT, thrist INT, cleanliness INT);");
  }

  public static void writeS(String name, int type, int happiness, int health, int hunger, int thrist, int cleanliness) throws SQLException {
   statmt.execute("DELETE FROM pitoms WHERE name='" + name + "';");
   statmt.execute("INSERT INTO  pitoms (name,type,happiness,health,hunger,thrist,cleanliness) " +
        "VALUES ('" + name + "'," + type + "," +happiness + "," +health + "," + hunger + "," + thrist + "," + cleanliness + ");");
  }

  public static int[] read(String name) throws ClassNotFoundException, SQLException {
    int[] values =  new int[6];

    resSet = statmt.executeQuery("SELECT * FROM 'pitoms' WHERE name = '" + name + "';");
    while(resSet.next()) {
      values[0] = resSet.getInt("type");
      values[1] = resSet.getInt("happiness");
      values[2] = resSet.getInt("health");
      values[3] = resSet.getInt("hunger");
      values[4] = resSet.getInt("thrist");
      values[5] = resSet.getInt("cleanliness");
      return values;
    }
    return null;
  }

//  public static void closeDB() throws ClassNotFoundException, SQLException {
//    conn.close();
//    statmt.close();
//    resSet.close();
//    System.out.println("Соединения закрыты");
//  }
}