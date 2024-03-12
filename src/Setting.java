import java.io.File;
import javax.sound.sampled.*;

public class Setting {
  private String active;
  final String RESET = "\u001B[0m";
  final String RED_TEXT = "\u001B[31m";
  final String GREEN_TEXT = "\u001B[32m";
  final String YELLOW_TEXT = "\u001B[33m";
  final String BLACK_BG = "\u001B[40m";
  final String WHITE_BG = "\u001B[47m";
  final String ANSI_BOLD = "\u001B[1m";
  final String ANSI_BLINK = "\u001B[5m";
  final String ANSI_UNDERLINE = "\u001B[4m";
  final String ANSI_CYAN = "\u001B[36m";
  final String ANSI_PURPLE = "\u001B[35m";

  public void _menu_admin() {
    System.out.println();
    System.out
        .println(this.BLACK_BG + this.RED_TEXT + this.ANSI_BOLD + this.ANSI_BLINK + this.ANSI_UNDERLINE
            + "ADMIN Menu " + this.RESET);
    System.out.println(this.YELLOW_TEXT + "1) Add Books");
    System.out.println("2) Add Users ");
    System.out.println("3) search the Book ");
    System.out.println("4) Library");
    System.out.println("5) logout ");
    System.out.println("6) Exit ");
    System.out.print("chose : " + this.RESET);

  }

  public void _menu_user() {
    System.out.println();
    System.out.println(this.BLACK_BG + this.ANSI_CYAN + this.ANSI_BOLD + this.ANSI_UNDERLINE + this.ANSI_BLINK
        + "User Menu " + this.RESET);
    System.out.println(this.ANSI_PURPLE + "1) Borrow Book ");
    System.out.println("2) Return Book ");
    System.out.println("3) Finde your book ");
    System.out.println("4) logout");
    System.out.println("5) Exit");
    // System.out.println("see libray"); add it later  
    System.out.print("chose : " + this.RESET);

  }

  public String getter() {
    return active;
  }

  public void setter(String active) {
    this.active = active;
  }

  public void paly_music() {
    try {
      File file = new File("src/data_base/Ghostrifter-Official-Purple-Dream.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
      Clip clip = AudioSystem.getClip();
      clip.open(audioStream);
      clip.loop(clip.LOOP_CONTINUOUSLY);
      clip.start();
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  public void cls() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
