import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();
        menuBar(root);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void menuBar(Pane parent)
    {
        MenuBar menubar = new MenuBar();
        parent.getChildren().add(menubar);

        Menu toolsMenu = new Menu("Tools");
        Menu helpMenu = new Menu("Help");
        Menu aboutMenu = new Menu("About");

        menubar.getMenus().addAll(toolsMenu,helpMenu,aboutMenu);
    }
}
