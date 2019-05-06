package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 400, 250, Color.WHITE);

    GridPane gridpane = new GridPane();
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(10);
    gridpane.setVgap(10);
    ColumnConstraints column1 = new ColumnConstraints(150, 150,
        Double.MAX_VALUE);
    ColumnConstraints column2 = new ColumnConstraints(50);
    ColumnConstraints column3 = new ColumnConstraints(150, 150,
        Double.MAX_VALUE);
    column1.setHgrow(Priority.ALWAYS);
    column3.setHgrow(Priority.ALWAYS);
    gridpane.getColumnConstraints().addAll(column1, column2, column3);

    Label LabelCandidatos = new Label("Candidates");
    GridPane.setHalignment(LabelCandidatos, HPos.CENTER);
    gridpane.add(LabelCandidatos, 0, 0);

    Label LabelSelected = new Label("selected");
    gridpane.add(LabelSelected, 2, 0);
    GridPane.setHalignment(LabelSelected, HPos.CENTER);

    // Candidates
    final ObservableList<String> candidates = FXCollections
        .observableArrayList("Pepe", "Jesus", "Conri", "Lola", "Pepa");
    final ListView<String> candidatesListView = new ListView<>(candidates);
    gridpane.add(candidatesListView, 0, 1);

    final ObservableList<String> selected = FXCollections.observableArrayList();
    final ListView<String> LaLista = new ListView<>(selected);
    gridpane.add(LaLista, 2, 1);

    Button MoverDerecha = new Button(" >> ");
    MoverDerecha.setOnAction((ActionEvent event) -> {
      String potential = candidatesListView.getSelectionModel()
          .getSelectedItem();
      if (potential != null) {
        candidatesListView.getSelectionModel().clearSelection();
        candidates.remove(potential);
        selected.add(potential);
      }else {
    	  Stage stage = (Stage) MoverDerecha.getScene().getWindow();
    	    stage.close();
    	}
        	
      
    });

    Button Eliminar_MoverOtraVez = new Button(" << ");
    Eliminar_MoverOtraVez.setOnAction((ActionEvent event) -> {
      String s = LaLista.getSelectionModel().getSelectedItem();
      if (s != null) {
        LaLista.getSelectionModel().clearSelection();
        selected.remove(s);
        candidates.add(s);

      }
    });
    VBox vbox = new VBox(5);
    vbox.getChildren().addAll(MoverDerecha, Eliminar_MoverOtraVez);

    gridpane.add(vbox, 1, 1);
    root.setCenter(gridpane);

    GridPane.setVgrow(root, Priority.ALWAYS);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}