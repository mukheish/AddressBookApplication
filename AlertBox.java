import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
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
        vBox.setStyle("-fx-background-color:#0cbace;");
        Scene scene =new Scene(vBox,350,150);
        window.setScene(scene);
        window.showAndWait();
    }
}
