import java.io.FileWriter;
import java.util.*;

public class Book {
  private HashMap<Integer, HashMap> main = new HashMap<>();
  private HashMap<String, String> data = new HashMap<>();
  private String name;
  private String athour;
  private String status;
  private int imdb;
  private String reciver;
  private Setting setting = new Setting();
  Scanner scanner = new Scanner(System.in);

  public HashMap<Integer, HashMap> getter() {
    return main;
  }

  public void setter(int imdb, String name, String athour, String status, String reciver) {
    this.name = name;
    this.athour = athour;
    this.imdb = imdb;
    this.status = status;
    this.reciver = reciver;
    this.data = hashmaker(this.name, this.athour, this.status, this.reciver);
    this.main.put(this.imdb, this.data);
  }

  public HashMap<String, String> hashmaker(String name, String athour, String status, String reciver) {
    HashMap<String, String> data = new HashMap<>();
    data.put("name", name);
    data.put("athour", athour);
    data.put("status", status);
    data.put("reciver", reciver);
    return data;
  }

  public void cls() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public int search(String item, boolean readonly) {
    int num = 0;
    try {
      Double.parseDouble(item);
      num = Integer.parseInt(item);
    } catch (NumberFormatException e) {

    }
    for (Integer i : this.main.keySet()) {
      if (readonly == false) {
        if (i == num) {
          return i;
        } else if (this.main.get(i).get("name").equals(item)) {
          return i;
        } else if (this.main.get(i).get("athour").equals(item)) {
          return i;
        }
      } else {
        if (i == num) {
          setting.cls();
          System.out.println(i + "= " + this.main.get(i));
        } else if (this.main.get(i).get("name").equals(item)) {
          setting.cls();
          System.out.println(i + "= " + this.main.get(i));
        } else if (this.main.get(i).get("athour").equals(item)) {
          setting.cls();
          System.out.println(i + "= " + this.main.get(i));
        }
        else{
          setting.cls();
          System.out.println("not found");
        }
      }
    }
    return 0;
  }

  public void bypass_book(int imdb, String status, String active) {
    if (imdb != 0) {

      if (this.main.get(imdb).get("status").equals(status)) {
        if (status.equals("f")) {
          setting.cls();
          System.out.println("sorry you can't take this book [status:false]");
        } else {
          System.out.println("somthing went wrong [status:true]");
        }
      } else {

        if (this.main.get(imdb).get("reciver").equals(active) || this.main.get(imdb).get("reciver").equals("")) {

          this.main.get(imdb).replace("status", status);
          if (status.equals("f")) {
            this.main.get(imdb).replace("reciver", active);
            setting.cls();
            System.out.println("mission accomplished >>");
          } else {
            this.main.get(imdb).replace("reciver", "");
            setting.cls();
            System.out.println("mission accomplished >>");  

          }

        } else if (this.main.get(imdb).get("reciver").equals(active) && this.main.get(imdb).get("status").equals("f")) {
          setting.cls();
          System.out.println("sorry This book is not available in the library");
        }

        else {
          System.out.println("somthing went wrong");
        }

      }
    } else {
      setting.cls();
      System.out.println("not found");
    }
  }

  public void write_books() {
    try {
      FileWriter csv_file = new FileWriter("src\\data_base\\book.csv", true);
      String name;
      String athor;
      String status;
      String reciver;
      int imdb;
      for (Integer i : this.main.keySet()) {
        imdb = i;
        name = this.main.get(i).get("name").toString();
        athor = this.main.get(i).get("athour").toString();
        status = this.main.get(i).get("status").toString();
        reciver = this.main.get(i).get("reciver").toString();
        csv_file.write(imdb + "," + name + "," + athour + "," + status + "," + reciver + ":\n");
      }
      csv_file.close();
    } catch (Exception e) {
      // TODO: handle exception
    }

  }

}
