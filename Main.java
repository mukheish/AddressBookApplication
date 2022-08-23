import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main extends Application {



    private String username="";
    private int numberOfCredential=0;
    private String[][] credential= new String[5][3];
    private Stage window = new Stage();

    public SignUp getSignUpO() {
        return signUpO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Scene getScene(){

        Label title = new Label("User Login");
        title.setFont(Font.font(30));

        Label uLabel= new Label("User Name");
        TextField uText= new TextField();


        Label pLabel=  new Label("Password");
        PasswordField pText= new PasswordField();

        uText.setOnMouseClicked(event1 -> {
            pLabel.setText("Password");
            pLabel.setTextFill(Color.BLACK);
        });
        pText.setOnMouseClicked(event1 -> {
            pLabel.setText("Password");
            pLabel.setTextFill(Color.BLACK);
        });
        Button signIn= new Button("Sign In");
        signIn.setPrefWidth(370);
        signIn.setOnAction(event -> {
            if(getLoad(uText.getText(),pText.getText(),false)) {

                username=uText.getText();
                window.close();
                new MainMenu(this).getMenu();
            }
            else{
                pLabel.setText(" Wrong user name or password ");
                pLabel.setTextFill(Color.RED);
            }

        });
        Image iWhat= new Image("what.jpg");
        ImageView ivWhat= new ImageView(iWhat);
        ivWhat.setSmooth(true);
        ivWhat.setCache(true);
        ivWhat.setPreserveRatio(true);
        ivWhat.setFitWidth(20);

        Image logo= new Image("Contacts-icon-300x300.png");
        ImageView ivLogo= new ImageView(logo);
        ivLogo.setSmooth(true);
        ivLogo.setCache(true);
        ivLogo.setPreserveRatio(true);
        ivLogo.setFitWidth(150);

        HBox hbox= new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.TOP_RIGHT);
        Button forgotPass = new Button("Forgot Password");
        forgotPass.setOnAction(e->{
            window.setScene(new PasswordReset().getScene(this));
        });
        hbox.getChildren().addAll(forgotPass,ivWhat);

        Button signUp= new Button("Sign Up");
        signUp.setPrefWidth(105);
        signUp.setOnAction(e->{
            getLoad("","",false);
            window.setScene(signUpO.getScene(this));
        });

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10,30,30,30));
        vBox.getChildren().addAll(uLabel,uText,pLabel,pText,signIn,hbox,signUp);
        vBox.setMargin(signUp,new Insets(0,0,0,15));
        vBox.setMargin(hbox, new Insets(0,0,0,30));
        vBox.setAlignment(Pos.TOP_CENTER);

        HBox hBox1= new HBox();
        hBox1.getChildren().addAll(ivLogo,vBox);
        hBox1.setAlignment(Pos.CENTER_LEFT);

        VBox finish = new VBox();
        finish.setSpacing(20);
        finish.setPadding(new Insets(30,30,30,30));
        finish.getChildren().addAll(title,hBox1);
        finish.setAlignment(Pos.CENTER);
        finish.setStyle("-fx-background-color:#0cbace;");
        return new Scene(finish,700,650);
    }

    public boolean getLoad(String user,String pass,boolean forgot){

        String fileName = "credential.txt";
        String line = null;
        System.out.println("credential called");
        numberOfCredential=0;
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                for(int i=0,j=0;i<line.length();i++){
                    String temp="";

                    if(line.charAt(i)==' '){
                        temp=line.substring(0,i);
                        line=line.substring(i+1);
                        i=0;
                        //System.out.println("Temp "+ temp);
                        credential[numberOfCredential][j]=temp;
                        j++;

                    }
                }
                if(forgot && credential[numberOfCredential][2].equals(pass)){
                    return  true;
                }
                else if(credential[numberOfCredential][0].equals(user) && credential[numberOfCredential][1].equals(pass))
                    return true;
                numberOfCredential++;


            }
            System.out.println("Login number of cd "+numberOfCredential);
            for(int i=0;i<numberOfCredential;i++){
                System.out.println(credential[i][0]+"  "+credential[i][1]+"  "+credential[i][2]);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
        System.out.println("credential called passed");
        return false;

    }
    public void setSignUpO(SignUp signUpO) {
        this.signUpO = signUpO;
    }

    public int getNumberOfCredential() {
        return numberOfCredential;
    }

    public void setNumberOfCredential(int numberOfCredential) {
        this.numberOfCredential = numberOfCredential;
    }

    public String[][] getCredential() {
        return credential;
    }

    public void setCredential(String[][] credential) {
        this.credential = credential;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    SignUp signUpO= new SignUp();


    public static void main(String [] args){
        launch(args);
    }
    public void start(Stage primary){
        window= primary;
        window.setScene(getScene());
        window.setResizable(false);
        window.setTitle("                                                           MY ADDRESS BOOK");
        window.show();
    }
}
