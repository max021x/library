
public interface INterface {
  abstract void menu(int selector);
  abstract int chek_user_info(int num, String id, int pass);
  abstract void read_lib();
  abstract void run();
  abstract int intexeption();


  /**
   * adminINterface
   */ 
  public interface adminINterface {
    abstract void all_book();
    abstract void sett_book();
    abstract void read();
    abstract void update_csv();
    abstract void search_book();
    abstract void book_transfer(String status) ;
    abstract void make_user() ;
    abstract void admin_menu(int choice);

  }
  

}