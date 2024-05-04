package UI.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import UI.Model.Model;
import UI.View.ClientMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ClientMenuController implements Initializable {

	@FXML
    private Button btnMainClient;
	
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnFunction2;

    @FXML
    private Button btnFunction3;

    @FXML
    private Button btnFunction1;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Initialization logic goes here
    	addListeners();
    }
    	
    private void addListeners() {    	
    	btnMainClient.setOnAction(event -> onMain());
    	btnFunction1.setOnAction(event -> onFunction1());
    	btnUpdate.setOnAction(event -> onUpdate());
    	btnFunction2.setOnAction(event -> onFunction2());
	}
    
    public void onMain() {
    	Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.MAIN)	;
    }
    
    public void onFunction1() {
    	Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.FUNCTION1)	;
    }
    
    public void onFunction2() {
    	Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.FUNCTION2)	;
    }
    
    public void onUpdate() {
    	Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.UPDATE);
    }
}

