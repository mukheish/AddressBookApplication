import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class AddEntry extends AlertBox{
    private ComboBox<String> countryC;
    private boolean allFilled=true;
    private String[] contact =new  String[12];
    private MainMenu login;
    private String fileName;

    public ComboBox<String> getCountryC() {
        return countryC;
    } //combobox is for dragdown options

    public void setCountryC(ComboBox<String> countryC) {
        this.countryC = countryC;
    }

    public boolean isAllFilled() {
        return allFilled;
    }

    public void setAllFilled(boolean allFilled) {
        this.allFilled = allFilled;
    }

    public String[] getContact() {
        return contact;
    }

    public void setContact(String[] contact) {
        this.contact = contact;
    }

    public MainMenu getLogin() {
        return login;
    }

    public void setLogin(MainMenu login) {
        this.login = login;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //overriding
    public static void display(String title, String message){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(200);

        Label label= new Label(message);
        label.setFont(Font.font(15));
        Button button= new Button("   OK   ");
        button.setOnAction(event -> {
            window.close();
        });
        VBox vBox= new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, button);
        vBox.setStyle("-fx-background-color:Red");
        Scene scene =new Scene(vBox,350,150);
        window.setScene(scene);
        window.showAndWait();
    }
    public Scene getScene(MainMenu menu,boolean editable) {
        login=menu;
        fileName =System.getProperty("user.dir")+"\\"+login.getLogin().getUsername()+".txt";
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(30);
        gridPane.setVgap(15);

        RadioButton mr = new RadioButton("Mr.");
        RadioButton mrs = new RadioButton("Mrs.");

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.setSpacing(20);
        hBox.setMaxWidth(150);
        hBox.getChildren().addAll(mr, mrs);
        mr.setOnAction(event1 -> {
            if(mr.isSelected()) {
                contact[0]=mr.getText();
                hBox.setStyle("-fx-border-color:#48e3f4");
            }
        });

        mrs.setOnAction(event1 -> {
            if(mrs.isSelected()) {
                contact[0]=mr.getText();
                hBox.setStyle("-fx-border-color:black");
            }
        });

        ToggleGroup tg= new ToggleGroup();
        mr.setToggleGroup(tg);

        mrs.setToggleGroup(tg);

        GridPane.setConstraints(hBox, 0, 0);

        Label firstName = new Label("First Name");
        TextField firstNameT = new TextField();
        GridPane.setConstraints(firstName, 0, 1);
        GridPane.setConstraints(firstNameT, 0, 2);

        firstNameT.setOnMouseClicked(event1 -> {
            allFilled=true;
            firstNameT.setStyle("-fx-border-color:black");
        });

        Label lastName = new Label("Last Name");
        GridPane.setConstraints(lastName, 1, 1);
        TextField lastNameT = new TextField();
        GridPane.setConstraints(lastNameT, 1, 2);
        lastNameT.setOnMouseClicked(event1 -> {
            allFilled=true;
            lastNameT.setStyle("-fx-border-color:black");
        });

        Label email = new Label("Email");
        GridPane.setConstraints(email, 0, 3);
        TextField emailT = new TextField();
        GridPane.setConstraints(emailT, 0, 4);
        emailT.setMinWidth(250);
        emailT.setText("@");
        emailT.setAlignment(Pos.CENTER);
        emailT.setOnMouseClicked(event1 -> {
            allFilled=true;
            emailT.setStyle("-fx-border-color:black");
        });
        Label country = new Label("Country");
        countryC = new ComboBox<>();
        getAllCountries();
        countryC.setMaxWidth(220);
        countryC.setPromptText("Choose Country");
        countryC.setOnAction(event1 -> {
            countryC.setStyle("-fx-border-color:black");
            allFilled=true;
        });

        GridPane.setConstraints(country, 0, 5);
        GridPane.setConstraints(countryC, 0, 6);

        Label city = new Label("City");
        TextField cityT = new TextField();
        GridPane.setConstraints(city, 1, 5);
        GridPane.setConstraints(cityT, 1, 6);
        cityT.setOnMouseClicked(event1 -> {
            allFilled=true;
            cityT.setStyle("-fx-border-color:black");
        });

        Label zipcode = new Label("Zip Code");
        TextField zipcodeT = new TextField();
        GridPane.setConstraints(zipcode, 2, 5);
        GridPane.setConstraints(zipcodeT, 2, 6);
        zipcodeT.setOnMouseClicked(event1 -> {
            allFilled=true;
            zipcodeT.setStyle("-fx-border-color:black");
        });

        Label dob = new Label("Date Of Birth");
        ComboBox<String> day = new ComboBox<>(); //combobox is for dragdown  options
        day.setPromptText("Day");
        for (int i = 1; i < 31; i++)
            day.getItems().add(""+i+"");
        day.setMaxHeight(80);
        day.setOnAction(event1 -> {
            day.setStyle("-fx-border-color: black");
            allFilled=true;
        });
        ComboBox<String> month = new ComboBox<>();
        month.setPromptText("Month");
        month.getItems().addAll(
            "January","February","March","April","May ","June  ","July  ","August ","September "," October","November ","December "
        );
        month.setMaxHeight(150);
        month.setOnAction(event1 -> {
            month.setStyle("-fx-border-color: black");
            allFilled=true;
        });
        ComboBox<String> year = new ComboBox<>();
        year.setPromptText("Year");
        for (int i = 1900; i < 2017; i++)
            year.getItems().add(""+i+"");
        year.setMaxHeight(150);
        year.setOnAction(event1 -> {
            allFilled=true;
            year.setStyle("-fx-border-color: black");
        });
        HBox dobV = new HBox();
        dobV.setSpacing(20);
        dobV.setAlignment(Pos.TOP_LEFT);
        dobV.getChildren().addAll(day, month, year);
        GridPane.setConstraints(dob,0,7);
        GridPane.setConstraints(dobV, 0, 8);


        Label phoneNo = new Label("Phone Number");
        TextField phoneNoT = new TextField();
        GridPane.setConstraints(phoneNo, 0, 9);
        GridPane.setConstraints(phoneNoT, 0, 10);
        phoneNoT.setOnMouseClicked(event1 -> {
            allFilled=true;
            phoneNoT.setStyle("-fx-border-color: black");
        });

        Label address = new Label("Home Address");
        TextField addressT = new TextField();
        GridPane.setConstraints(address, 0, 11);
        GridPane.setConstraints(addressT, 0, 12);
        addressT.setMinSize(0, 60);
        addressT.setOnMouseClicked(event1 -> {
            addressT.setStyle("-fx-border-color: black");
            allFilled=true;
        });


        Button save = new Button("Save");
        save.setMinSize(120,40);
        gridPane.setHalignment(save,HPos.LEFT);
        GridPane.setConstraints(save, 2, 13);
        Button cancel= new Button(" Cancel ");
        gridPane.setHalignment(cancel,HPos.RIGHT);
        cancel.setMinSize(120,40);
        GridPane.setConstraints(cancel, 1, 13);
        gridPane.getChildren().addAll(cancel,hBox, firstName, firstNameT, lastName, lastNameT, email, emailT, country, countryC, city, cityT, zipcode, zipcodeT, dob, dobV, phoneNo, phoneNoT, address, addressT, save);
        cancel.setOnAction(event1 -> menu.getMennu().setScene(menu.getScene()));

        save.setOnAction(event -> {
            if(!mr.isSelected() && !mrs.isSelected() ){
                allFilled=false;
                hBox.setStyle("-fx-border-color:Red");
            }

            if(!firstNameT.getText().isEmpty())
                contact[1]=firstNameT.getText();
            else {
                firstNameT.setStyle("-fx-border-color:Red");
                allFilled = false;
            }
            if(!lastNameT.getText().isEmpty())
                contact[2]=lastNameT.getText();
            else {
                lastNameT.setStyle("-fx-border-color:Red");
                allFilled=false;
            }
            if(emailT.getText().isEmpty() || !emailT.getText().contains("@")){
                allFilled=false;
                emailT.setStyle("-fx-border-color:Red");
            }
            else {
                contact[3]=emailT.getText();
            }
            if(countryC.getSelectionModel().getSelectedIndex()!=-1)
                contact[4]=countryC.getValue();
            else{
                allFilled=false;
                countryC.setStyle("-fx-border-color:Red");
            }

            if(!cityT.getText().isEmpty())
                contact[5]=cityT.getText();
            else{
                allFilled=false;
                cityT.setStyle("-fx-border-color:Red");
            }

            if(!zipcodeT.getText().isEmpty())
                contact[6]=zipcodeT.getText();
            else{
                allFilled=false;
                zipcodeT.setStyle("-fx-border-color:Red");
            }
            if(day.getSelectionModel().getSelectedIndex()!=-1)
                contact[7]=day.getValue();
            else{
                allFilled=false;
                day.setStyle("-fx-border-color:Red");
            }

            if(month.getSelectionModel().getSelectedIndex()!=-1)
                contact[8]=month.getValue();
            else{
                allFilled=false;
                month.setStyle("-fx-border-color:Red");
            }

            if(year.getSelectionModel().getSelectedIndex()!=-1)
                contact[9]=year.getValue();
            else{
                allFilled=false;
                year.setStyle("-fx-border-color:Red");
            }

            if(!phoneNoT.getText().isEmpty())
                contact[10]=phoneNoT.getText();
            else{
                allFilled=false;
                phoneNoT.setStyle("-fx-border-color:Red");
            }

            if(!addressT.getText().isEmpty())
                contact[11]=addressT.getText();
            else{
                allFilled=false;
                addressT.setStyle("-fx-border-color:Red");
            }
            if(allFilled){
                if(editable)
                    update(menu,true);
                else
                    menu.addEntry.update(menu);
                menu.getBorderPane().setLeft(new SearchEntry().getLeft(menu));
                menu.getMennu().setScene(menu.getScene());
            }

        });
        if(editable){
            menu.getSearchEntry().getLoad();
            int index= menu.getSearchEntry().getIIndex();
            System.out.println("Index "+index);
            String contact[][]= menu.getSearchEntry().getContact();
            if(contact[index][0].equals("Mr."))
                mr.fire();
            else
                mrs.fire();
            firstNameT.setText(contact[index][1]);
            lastNameT.setText(contact[index][2]);
            emailT.setText(contact[index][3]);
            countryC.setValue(contact[index][4]);
            cityT.setText(contact[index][5]);
            zipcodeT.setText(contact[index][6]);
            day.setValue(contact[index][7]);
            month.setValue(contact[index][8]);
            year.setValue(contact[index][9]);
            phoneNoT.setText(contact[index][10]);
            addressT.setText(contact[index][11]);

        }
        gridPane.setStyle("-fx-background-color:#0cbace;");
        return new Scene(gridPane, 900, 750);
    }

    public void getAllCountries() {
            String[] countries = new String[Locale.getISOCountries().length];
            String[] countryCodes = Locale.getISOCountries();
            for (int i = 0; i < countryCodes.length; i++) {
                Locale obj = new Locale("", countryCodes[i]);
                countries[i] = obj.getDisplayCountry();
                countryC.getItems().add(countries[i]);
            }
    }
    /*
    Overloading
     */
    public void update(MainMenu menu){
        System.out.println("Writer -- "+fileName);
        boolean exist=false;

        try {

            String tobeOut="";
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter contactWriter = new BufferedWriter(fileWriter);
            for(int i=0;i<12;i++){
                tobeOut+=(contact[i]+"|");
            }
            for(int i=0;i<menu.getSearchEntry().getCount();i++){
                String currentLine="";
                for(int j=0;j<12;j++){
                    currentLine+=menu.getSearchEntry().getContact()[i][j]+"|";
                }

                if(menu.getSearchEntry().getContact()[i][10].equals(contact[10])){
                    exist=true;
                }
                else {

                    contactWriter.append(currentLine);contactWriter.newLine();
                }
            }
            if(!exist){

                contactWriter.append(tobeOut);
                contactWriter.newLine();
                super.display("Message ","You Have added a contact successfully! ");
            }
            else {
                display("Message ","Phone number already exist ! ");
            }
            System.out.println("\n");
            contactWriter.close();
            menu.getSearchEntry().getLoad();
            menu.getBorderPane().setLeft(menu.getSearchEntry().getLeft(menu));
            menu.getBorderPane().setRight(menu.getSearchEntry().getRight());
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");

        }

    }

    public void update(MainMenu menu,boolean update){
        System.out.println("Writer -- "+fileName);

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter contactWriter = new BufferedWriter(fileWriter);
            String tobeOut="";
            for(int i=0;i<12;i++){
                tobeOut+=(contact[i]+"|");
            }
            String temp="";
            System.out.println("Current Index **** "+menu.getSearchEntry().getIIndex());

            for(int i=0;i<12;i++)
                temp+=menu.getSearchEntry().getContact()[menu.getSearchEntry().getIIndex()][i]+"|";



            for(int i=0;i<menu.getSearchEntry().getCount();i++){
                String currentLine="";
                for(int j=0;j<12;j++){
                    currentLine+=menu.getSearchEntry().getContact()[i][j]+"|";
                }
                System.out.println("CHECK "+"\nTobe ->> "+tobeOut+"\nCUrrent  ->> "+currentLine+"\nSELECTED ->> "+temp);


                if(currentLine.equals(temp)){
                    if(update){
                        contactWriter.append(tobeOut);contactWriter.newLine();
                        System.out.println("Matched "+ tobeOut);
                    }
                }
                else {

                    contactWriter.append(currentLine);contactWriter.newLine();
                    System.out.println("Pass by "+ currentLine);
                }
            }
            contactWriter.close();
            menu.getSearchEntry().getLoad();
            menu.getBorderPane().setLeft(menu.getSearchEntry().getLeft(menu));
            menu.getBorderPane().setRight(menu.getSearchEntry().getRight());
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");

        }

    }


}