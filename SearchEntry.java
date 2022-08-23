import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SearchEntry {
    private String fileName;
    private int count=0, index=0;
    private String[][] contact= new String[1000][12];
    private String selected="";
    private MainMenu menu;

    public TextField getSearchT() {
        return searchT;
    }

    public void setSearchT(TextField searchT) {
        this.searchT = searchT;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String[][] getContact() {
        return contact;
    }

    public void setContact(String[][] contact) {
        this.contact = contact;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIIndex() {
        return index;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TextField searchT= new TextField();
    public VBox getLeft(MainMenu temp){
        menu=temp;
        searchT.setPromptText("search by name");
        searchT.setPromptText("search by name");
        searchT.setStyle("-fx-font: 15px Tahoma; -fx-stroke: black; -fx-stroke-width: 1");
        searchT.setAlignment(Pos.CENTER);
        searchT.setOnKeyReleased(event -> {
            //System.out.println(searchT.getText());
            handleSearch(searchT.getText());
        });
        fileName=System.getProperty("user.dir")+"\\"+menu.getLogin().getUsername()+".txt";
        //menu.getSearchEntry().getLoad();
        getLoad();
        ObservableList<String> names = FXCollections.observableArrayList();

        for(int i=0;i<count;i++){
            names.add(contact[i][0]+" "+contact[i][1]+" "+contact[i][2]+"\n"+contact[i][10]);
        }
        ListView<String>listView = new ListView<>(names);
        listView.setStyle("-fx-font-size:15;");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println(new_val);
                    selected=new_val;
                    getIndex();
                    getLoad();
                    menu.getBorderPane().setRight(getRight());

                });
        VBox vbox= new VBox();
        vbox.getChildren().addAll(searchT,listView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        vbox.setMinWidth(200);
        vbox.setMinHeight(300);
        vbox.setPadding(new Insets(30,30,30,30));
        vbox.setStyle("-fx-background-color:  #0cbace;-fx-border-color:white;-fx-border-width: 4;");
        return vbox;
    }





    public int getIndex(){
        int i;
        for( i=0;i<count;i++){
            if((contact[i][0]+" "+contact[i][1]+" "+contact[i][2]+"\n"+contact[i][10]).equals(selected)){
                menu.getSearchEntry().setIndex(i);
                System.out.println("Pressed Index--- "+ menu.getSearchEntry().getIIndex()+"  "+i);
                break;
            }
        }
        return i;
    }

    public StackPane getRight(){
        StackPane stackPane=new StackPane();
        stackPane.setMinWidth(400);
        stackPane.setMinHeight(300);
        stackPane.setStyle("-fx-border-color: green;-fx-border-width: 2;-fx-border-style: solid;");
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(30,30,30,30));

        GridPane details= new GridPane();
        Label nameL= new Label("Name \n"+contact[index][0]+" "+contact[index][1]+" "+contact[index][2]);
        nameL.setMinHeight(40);
        Label emailL= new Label("Email Address \n"+contact[index][3]);
        emailL.setMinHeight(40);
        Label countryL= new Label("Country \n"+contact[index][4]);
        countryL.setMinHeight(40);
        Label cityL= new Label("City \n"+contact[index][5]);
        cityL.setMinHeight(40);
        Label zipL= new Label("Zip \n"+contact[index][6]);
        zipL.setMinHeight(40);
        Label birthdayL= new Label("Birthday  \n"+contact[index][7]+" - "+contact[index][8]+" - "+contact[index][9]);
        birthdayL.setMinHeight(40);
        Label phoneL= new Label("Phone No \n"+contact[index][10]);
        phoneL.setMinHeight(40);
        Label addressL= new Label("Address \n"+contact[index][11]);
        addressL.setMinHeight(40);
        details.add(nameL,0,3);
        nameL.setFont(javafx.scene.text.Font.font(15));
        details.add(emailL,0,5);
        emailL.setFont(javafx.scene.text.Font.font(15));
        details.add(countryL,0,7);
        countryL.setFont(javafx.scene.text.Font.font(15));
        details.add(cityL,1,7);
        cityL.setFont(javafx.scene.text.Font.font(15));
        details.add(zipL,0,9);
        zipL.setFont(javafx.scene.text.Font.font(15));
        details.add(birthdayL,0,11);
        birthdayL.setFont(javafx.scene.text.Font.font(15));
        details.add(phoneL,1,9);
        phoneL.setFont(javafx.scene.text.Font.font(15));
        details.add(addressL,0,13);
        addressL.setFont(javafx.scene.text.Font.font(15));
        details.setAlignment(Pos.CENTER_LEFT);
        details.setHgap(20);
        details.setVgap(10);
        details.setMinWidth(150);
        stackPane.getChildren().add(details);
        stackPane.setStyle("-fx-background-color:  white;-fx-border-color:#0cbace;-fx-border-width: 4;");
        return stackPane;
    }




    public void getLoad(){

        String line = null;
        count=0;
        System.out.println("Reader called");
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                for(int i=0,j=0;i<line.length();i++){
                    String temp="";

                    if(line.charAt(i)=='|'){
                        temp=line.substring(0,i);
                        line=line.substring(i+1);
                        i=0;
                        contact[count][j]=temp;
                        j++;

                    }
                }
                count++;
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

    }
    public void handleSearch(String query){
        ObservableList<String> names = FXCollections.observableArrayList();


        for(int i=0;i<count;i++){
            String match=contact[i][0]+" "+contact[i][1]+" "+contact[i][2]+"\n"+contact[i][10];
            //System.out.println("Match "+match+" Queary -->>>"+query);

            if(match.toLowerCase().contains(query.toLowerCase())){
                names.add(match);
            }
        }
       // System.out.println("________________________________________");
        ListView<String>listView = new ListView<>(names);
        listView.setStyle("-fx-font-size:15;");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println(new_val);
                    selected=new_val;
                    getIndex();
                    getLoad();
                    menu.getBorderPane().setRight(getRight());

                });
        VBox vbox= new VBox();
        vbox.getChildren().addAll(searchT,listView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        vbox.setMinWidth(200);
        vbox.setMinHeight(300);
        vbox.setPadding(new Insets(30,30,30,30));
        menu.getBorderPane().setLeft(vbox);
    }

}
