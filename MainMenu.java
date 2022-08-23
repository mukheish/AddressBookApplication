import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends ConfirmBox {

    private Main login;
    private Stage menu;
    private Scene scene;
    private BorderPane borderPane;
    private SearchEntry searchEntry= new SearchEntry();

    MainMenu(Main login){
        this.login=login;
    }


    public AddEntry getAddEntry() {
        return addEntry;
    }

    public void setAddEntry(AddEntry addEntry) {
        this.addEntry = addEntry;
    }

    public SearchEntry getSearchEntry() {
        return searchEntry;
    }

    public void setSearchEntry(SearchEntry searchEntry) {
        this.searchEntry = searchEntry;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setMenu(Stage menu) {
        this.menu = menu;
    }

    public Stage getMennu() {
        return menu;
    }
    public Main getLogin() {
        return login;
    }

    public void setLogin(Main login) {
        this.login = login;
    }

    public AddEntry addEntry= new AddEntry();
    public void getMenu(){
        menu=new Stage();
        borderPane = new BorderPane();
        borderPane.setLeft(searchEntry.getLeft(this));
        borderPane.setRight(searchEntry.getRight() );

        Button logout= new Button("  Logout  ");
        logout.setOnAction(event -> {
            menu.close();
            Main main= new Main();
            main.getWindow().setScene(main.getScene());
            main.getWindow().setResizable(false);
            main.getWindow().show();
        });


        Button add= new Button("  ADD NEW  ");
        add.setOnAction(event -> {
            menu.setScene(addEntry.getScene(this,false));
        });
        Button update= new Button("  UPDATE   ");
        update.setOnAction(event -> {
            searchEntry.getLoad();
            System.out.println("CHALAINDEX --- "+searchEntry.getIndex());
            borderPane.setLeft(searchEntry.getLeft(this));
            menu.setScene(addEntry.getScene(this,true));

        });
        Button delete= new Button("  DELETE ");
        delete.setOnAction(event -> {
            System.out.println("delete pressed");
            searchEntry.getLoad();
            boolean confirmation=display("Confirmation ", "Are you sure you wanna delete? ");
            System.out.println("confirmation*********> "+confirmation);
            if(confirmation){
                addEntry.setFileName(System.getProperty("user.dir")+"\\"+login.getUsername()+".txt");
                addEntry.update(this,false);
                searchEntry.getLoad();
                borderPane.setLeft(searchEntry.getLeft(this));
            }
        });
        Label welcome= new Label("WELCOME BACK "+login.getUsername().toUpperCase()+"                       ");
        welcome.setFont(Font.font(15));
        HBox hBox= new HBox();
        hBox.setMinHeight(100);
        hBox.setMinWidth(600);
        hBox.setSpacing(25);
        hBox.setPadding(new Insets(30,30,30,30));
        hBox.setStyle("-fx-border-color: white;-fx-border-style: solid;-fx-border-width: 2;");
        hBox.getChildren().addAll(welcome,logout,delete,update,add);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        borderPane.setTop(hBox);
        borderPane.setAlignment(borderPane,Pos.TOP_LEFT);
        borderPane.setPadding(new Insets(30,30,30,30));
        borderPane.setStyle("-fx-background-color:#0cbace;");
        scene= new Scene(borderPane,900,700);
        menu.setScene(scene );
        menu.setResizable(false);
        menu.setTitle("                                                                                   MY ADDRESS BOOK ");
        menu.show();
    }

}
