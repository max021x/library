import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Users implements INterface {

  private Admin admin = new Admin();
  private User user = new User();
  private Text_Writer text = new Text_Writer();
  private Setting setting = new Setting();
  private Scanner scanner = new Scanner(System.in);

  @Override
  public int chek_user_info(int num, String id, int pass) {

    if (num == 1 && id.equals(admin.username) && pass == admin.password) {
      text.write(true, id);
      return 1;
    } else if (num == 2 && id.equals(user.defultUser_name) && pass == user.defultUser_pass) {
      setting.setter(id);
      return 0;
    } else if (num == 2) {
      for (String i : admin.users_info.keySet()) {
        if (id.equals(i) && pass == admin.users_info.get(id)) {
          text.write(true, id);
          setting.setter(id);
          return 0;
        }
      }
    }
    return -1;
  }

  public void read_lib() {
    setting.cls();
    setting.paly_music();
    admin.read();
  }

  public void run() {
    System.out.println();
    System.out.println("logging chose 1/2/3 ?");
    System.out.println("1 Admin");
    System.out.println("2 User");
    System.out.println("3 exit");
    System.out.print("chose ");

    int choice = intexeption();
    scanner.nextLine();
    String username = "";
    int password = 0;
    switch (choice) {
      case 1:
        System.out.print("username  ");
        username = scanner.nextLine();
        System.out.print("password  ");
        password = intexeption();
        setting.cls();
        menu(chek_user_info(choice, username.toLowerCase(), password));

        break;
      case 2:
        System.out.print("username  ");
        username = scanner.nextLine();
        System.out.print("password  ");
        password = intexeption();
        setting.cls();
        menu(chek_user_info(choice, username.toLowerCase(), password));

        break;
      case 3:
        System.out.println("good bye");
        admin.update_csv();
        System.exit(0);
        break;
      default:
        setting.cls();
        System.out.print(setting.RED_TEXT + setting.ANSI_BOLD + "CHOSE A NUMBER FROM LIST" + setting.RESET);
        run();
        break;
    }

  }

  @Override
  public void menu(int selector) {
    int choice = 0;
    switch (selector) {
      case 1:
        setting._menu_admin();
        scanner.nextLine();
        choice = intexeption();
        admin.admin_menu(choice);
        menu(selector);
        break;
      case 0:
        setting._menu_user();
        scanner.nextLine();

        choice = intexeption();
        user.user_menu(choice);
        menu(selector);
        break;
      default:
        System.out.print(setting.RED_TEXT+setting.ANSI_BOLD+"\nsomthing went wrong  [incorect uername or password ]"+setting.RESET);
        run();
        break;
    }
  }

  @Override
  public int intexeption() {
    boolean succes = false;
    int choice = 0;
    while (!succes) {
      try {
        choice = scanner.nextInt();
        succes = true;
      } catch (Exception e) {
        System.out.println("Enter a num (int)");
        scanner.nextLine();
      }
    }
    return choice;
  }

  /**
   * Admin
   */
  private class Admin extends Book implements INterface.adminINterface {
    private HashMap<String, Integer> users_info = new HashMap<>();
    final String username = "max";
    final int password = 778;

    public HashMap<String, Integer> getUsers_info() {
      return users_info;
    }

    public void setUsers_info(String users_name, int users_pass) {
      this.users_info.put(users_name, users_pass);
    }

    public void all_book() {
      setting.cls();
      System.out.println(getter());
    }

    public void sett_book() {
      String answer = "y";
      while (answer.equals("y")) {
        System.out.print("imdb ");

        int imdb = intexeption(); // get imdb

        System.out.print("name ");
        String name = scanner.nextLine(); // get name

        System.out.print("athour ");
        String athour = scanner.nextLine(); // get athour

        System.out.print("status (t/f) ");
        String status = scanner.nextLine(); // get status

        while (status.equals("t") == false && status.equals("f") == false) {
          System.out.println("only t/f");
          status = scanner.nextLine(); // get status if and only if y/n
        }
        setter(imdb, name, athour, status, ""); // set book
        System.out.print("add another ? \n");
        answer = scanner.nextLine();
        while (answer.equals("y") == false && answer.equals("n") == false) {
          System.out.println("only y/n");
          answer = scanner.nextLine();
        }

      }
      setting.cls();
    }

    public void read() {
      String path = "src\\data_base\\book.csv";
      try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        List<List<String>> oop = new ArrayList<>();
        while ((line = br.readLine()) != null) {
          List<String> val = Arrays.asList(line.split(":"));
          oop.add(val);
        }
        String[] arr;
        for (List<String> list : oop) {
          for (String ufo : list) {
            arr = ufo.split(",", -1);
            if (arr.length == 5) {
              setter(Integer.valueOf(arr[0]), arr[1], arr[2], arr[3], arr[4]);
            }
          }

        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    public void update_csv() {
      File file = new File("src\\data_base\\book.csv");
      try (PrintWriter pw = new PrintWriter(file)) {
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

      write_books();

    }

    public void search_book() {
      System.out.print("search by item "); // update later
      String item = scanner.nextLine();
      search(item, true);
    }

    public void book_transfer(String status) {
      System.out.print("Enter item ");
      String item = scanner.nextLine();
      int imdb = search(item, false);
      String active = setting.getter();
      bypass_book(imdb, status, active);
    }

    public void make_user() {
      String answer = "y";
      while (answer.equals("y")) {

        System.out.println("Enter user's name > ");
        String name = scanner.nextLine();
        System.out.println("Enter user's pass > ");
        int pass = intexeption();
        setUsers_info(name, pass);
        System.out.println(getUsers_info() + "\n");
        System.out.print("add another? ");
        answer = scanner.nextLine();
        while (answer.equals("y") == false && answer.equals("n") == false) {
          System.out.println("only y/n");
          answer = scanner.nextLine();
        }
      }
      setting.cls();
    }

    public void admin_menu(int choice) {

      switch (choice) {
        case 1:
          sett_book();
          break;
        case 2:
          make_user();
          break;
        case 3:
          search_book();
          break;
        case 4:
          all_book();
          break;
        case 5:
          text.write(false, username);
          setting.cls();
          run();
        case 6:
          update_csv();
          text.write(false, username);
          System.out.println("Good bye");
          System.exit(0);
          break;
        default:
          System.out.println("chose a number from list");
          int test = scanner.nextInt();
          admin_menu(test);
          break;
      }

    }

  }

  /**
   * user
   */
  private class User extends Admin {
    final String defultUser_name = "max";
    final int defultUser_pass = 778;

    public void user_menu(int choice) {
      switch (choice) {
        case 1:
          String status1 = "f";
          admin.book_transfer(status1);
          break;
        case 2:
          String status2 = "t";
          admin.book_transfer(status2);
          break;
        case 3:
          admin.search_book();
          break;
        case 4:
          text.write(false, setting.getter());
          setting.cls();
          run();
          break;
        case 5:
          admin.update_csv();
          text.write(false, setting.getter());
          System.out.println("Good bye");
          System.exit(0);
          break;

        default:
          System.out.println("chose a number from list");
          int test = scanner.nextInt();
          admin_menu(test);
          break;
      }
    }
  }
}
