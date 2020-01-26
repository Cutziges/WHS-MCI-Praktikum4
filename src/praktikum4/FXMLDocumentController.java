
package praktikum4;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarah Grugiel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane scene;
    @FXML
    private MenuBar menubar;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem saveas;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem add;
    @FXML
    private MenuItem change;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem list;
    @FXML
    private MenuItem details;
    @FXML
    private MenuItem info;
    
    private final Desktop desktop = Desktop.getDesktop();
    
    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Anpassung der Menübar-Breite an die Fenstergröße
        menubar.prefWidthProperty().bind(scene.widthProperty());
        
        // Methode zur Erstellung eines Weinfensters
        add.setOnAction((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Wein.fxml"));
                
                Scene scene = new Scene(fxmlLoader.load(), 600, 800);
                Stage stage = new Stage();
                stage.setTitle("Wein aufnehmen");
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, 
                        "Erstellung des Fensters fehlgeschlagen", e);
            }
        });
        
        // FileChooser
        FileChooser fileChooser = new FileChooser();
        
        
        // Methode zum Öffnen des FileChoosers
        open.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) scene.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            
            if (file != null) {
                openFile(file);
                List<File> files = Arrays.asList(file);
            }
        });
    }    

    /**
     * Methode zum Schließen des Fensters.
     * @param event Exit
     */
    @FXML
    private void exitAction(ActionEvent event) {
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Methode zum Öffnen einer Datei.
     * @param file Datei
     */
    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            System.out.print("Fehler beim Öffnen der Datei.");
            //e.printStackTrace();
        }
    }
}
