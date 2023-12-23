package ui.fxml;

import java.io.IOException;

import com.google.gson.JsonArray;

import Functions.Function1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Function1Controller implements HandleEvent{
	
	private Function1 fn1;
	
	public Function1Controller(Function1 fn1) {
		super();
		this.fn1 = fn1;
	}
	
	public Function1Controller() {
	
	}
	
	@FXML
    private TableView<?> tblViewResult;
	
	@FXML
    private TableColumn<?, ?> colHashtagKeyword;

	@FXML
    private TableColumn<?, ?> colDate;
	
	@FXML
    private TableColumn<?, ?> colContent;
	
	@FXML
    private TableColumn<?, ?> colTitle; 
	
	@FXML
    private RadioButton radiobtnBlog;
	
	@FXML
    private RadioButton radiobtnTweet;
	
	@FXML
    private MenuItem btnAbout;
	
    @FXML
    private TextArea tfSearchHashtag;

    @FXML
    private ToggleGroup choiceSearch;

    @FXML
    private Button btnGetUpdate;

    @FXML
    private Button btnFunction2;

    @FXML
    private Button btnFunction3;

    @FXML
    private Button btnFunction1;

    public void initialize() {
        // Initialization logic goes here
    }

	@Override
	public void btnAboutPressed(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/about.fxml"));
    	createStage(loader);
	}

	@Override
	public void btnGetUpdatePressed(ActionEvent event) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText(null);
        alert.setContentText("The news is updated successfully!");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.showAndWait();
	}

	@Override
	public void runFunction1(ActionEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void runFunction2(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/Function2.fxml"));
    	createStage(loader);
	}
	
	private void createStage(FXMLLoader loader) {
    	Scene scene = null;
		try {
			scene = new Scene(loader.load()); 
		} catch (IOException e) {
			e.printStackTrace();
	}
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.setTitle("Project OOP");
	stage.show();
	}
}