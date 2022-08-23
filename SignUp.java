import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public  class SignUp {

    public Scene getScene(Main login){
        Label title= new Label("Sign Up !");
        title.setFont(Font.font(15));
        Label uLabel=  new Label("User Name");
        TextField uText =  new TextField();
        uText.setMaxWidth(200);
        Label notification= new Label();
        Label notification2= new Label();
        Label passLabel = new Label("Password");
        PasswordField pass= new PasswordField();
        pass.setMaxWidth(200);
        HBox hbox2=new HBox();
        hbox2.getChildren().addAll(uText,notification);
        hbox2.setSpacing(20);
        Label confirmPassLabel= new Label("Confirm Password");
        PasswordField confirmPass= new PasswordField();
        confirmPass.setMaxWidth(200);

        Label pincodeLabel= new Label("PIN ,for Password Resetting");
        PasswordField pincode = new PasswordField();
        pincode.setMaxWidth(200);
        HBox hBox3= new HBox();
        hBox3.setSpacing(150);
        hBox3.getChildren().addAll(pincodeLabel,notification2);

        uText.setOnMouseClicked(event -> {
            notification.setText("");
            notification2.setText("" );
            notification2.setText("");
        });
        confirmPass.setOnMouseClicked(event -> {
            confirmPassLabel.setText("Confirm Password");
            notification2.setText("" );
            notification2.setText("");
        });
        pass.setOnMouseClicked(event -> {
            confirmPassLabel.setText("Confirm Password");
            notification2.setText("" );
            notification2.setText("");
        });
        pincode.setOnMouseClicked(event -> {
            notification2.setText("" );
            notification2.setText("");
        });
        Button register = new Button("Register");
        register.setMinSize(100,30);
        register.setOnAction(event1 -> {
            // System.out.println("IN_>> "+uText.getText()+"  "+pass.getText()+"  "+confirmPass.getText()+"  "+pincode.getText());
            if(!uText.getText().isEmpty() && !pass.getText().isEmpty() && !confirmPass.getText().isEmpty() && !pincode.getText().isEmpty()
                    && pass.getText().equals(confirmPass.getText()) && !pass.getText().contains(" ")&& !uText.getText().contains(" ") && !pincode.getText().contains(" ")
                    ){

                if(getWrite(login,uText.getText(),pass.getText(),pincode.getText(),false))
                    login.getWindow().setScene(login.getScene());
                else{
                    notification.setText("USERNAME ALREADY EXIST !");
                }
            }
            if(!pass.getText().equals(confirmPass.getText())){
                confirmPassLabel.setText("PASSWORD DOESN'T MATCH");
            }

            if(pass.getText().contains(" ")||uText.getText().contains(" ") || pincode.getText().contains(" ")){
                notification2.setText("INPUT CAN'T HAVE SPACE" );
            }
            if(uText.getText().isEmpty() || pass.getText().isEmpty() || confirmPass.getText().isEmpty() || pincode.getText().isEmpty()
                    ){
                notification2.setText("SOMETHING IS MISSING");
            }
            //
        });

        Button goBack = new Button("Go Back");
        goBack.setOnAction(event -> {
            login.getWindow().setScene(login.getScene());
        });

        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.getChildren().addAll(goBack,register);
        hBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(30,30,30,30));
        vbox.setMargin(title,new Insets(20,0,10,170));
        vbox.setMargin(register,new Insets(0,0,10,270));

        vbox.getChildren().addAll(title,uLabel,hbox2,passLabel,pass,confirmPassLabel,confirmPass,hBox3,pincode,hBox);

        vbox.setStyle("-fx-background-color:#0cbace;");
        return  new Scene(vbox,500,450);
    }
    public boolean getWrite(Main login,String username,String password,String pincode,boolean update){
        boolean exist=true;
        String fileName ="credential.txt";
        boolean write=true;

        try {

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter contactWriter = new BufferedWriter(fileWriter);
            // System.out.println("Credential "+login.numberOfCredential);
            for(int i=0;i<login.getNumberOfCredential();i++){
                String currentLine=(login.getCredential()[i][0]+" "+login.getCredential()[i][1]+" "+login.getCredential()[i][2]+" ").toLowerCase();
                //   System.out.println("Current Line |"+ currentLine+"|");
                if((login.getCredential() [i][0]).equals(username.toLowerCase())){
                    if(update){
                        write=false;
                        contactWriter.append((username+" "+password+" "+pincode+" ").toLowerCase());
                    }
                    else{
                        //         System.out.println("already exist");
                        exist=  false;
                    }
                }
                else {

                    contactWriter.append(currentLine.toLowerCase());contactWriter.newLine();
                    //   System.out.println("Pass y "+currentLine);
                }
            }

            if (write){
                contactWriter.append((username.toLowerCase()+" "+password+" "+pincode+" ").toLowerCase());
                //System.out.println("ADD new "+username+" "+password+" "+pincode+" ");
            }
            contactWriter.newLine();
            contactWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");

        }
        return exist;
    }

}

