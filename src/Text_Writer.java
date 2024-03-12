import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Text_Writer {
  final String enter_path = "src\\data_base\\enter.txt";
  final String exit_path = "src\\data_base\\exit.txt";
  final String book_base = "src\\data_base\\book.csv";

  public void write(boolean user_status , String id) {
    File path;
    if(user_status == true)
    path = new File(this.enter_path);
    else
    path = new File(this.exit_path);
    try {
      PrintWriter time_writer = new PrintWriter(new FileWriter(path , true));
      time_writer.append(time()+"  "+id+"\n");
      time_writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private String time(){
      LocalDateTime time = LocalDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm:ss");
      String formated_time = time.format(format);
      return formated_time;
  }

  protected void csv_writer(int imdb , String name , String athour , String status ){
    try {
      FileWriter csv = new FileWriter(this.book_base , true);
      csv.write(imdb+","+name+","+athour+","+status+":\n");
      csv.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }


}
