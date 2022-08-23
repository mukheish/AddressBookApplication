import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class  ConfirmBox {
    static boolean answer;
    public static boolean display(String title, String message){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label= new Label(message);
        label.setFont(javafx.scene.text.Font.font(15));
        Button YesB= new Button("Yes");
        YesB.setPrefWidth(80);

        Button NoB= new Button("No");
        NoB.setPrefWidth(80);
        YesB.setOnAction(event -> {
            answer=true;
            window.close();
        });
        NoB.setOnAction(event ->{
            answer=false;
            window.close();
        });
        VBox vBox= new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label,YesB,NoB);
        vBox.setStyle("-fx-background-color:#0cbace;");
        Scene scene =new Scene(vBox,350,150);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
