package UI.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import UI.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ClientController implements Initializable {
	public BorderPane client_parent;

	public ClientController() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) 
				-> {switch(newVal) {
				case FUNCTION1-> client_parent.setCenter(Model.getInstance().getViewFactory().getFunction1View());
				case FUNCTION2-> client_parent.setCenter(Model.getInstance().getViewFactory().getFunction2View());
				case UPDATE -> Model.getInstance().getViewFactory().showUpdateView();
				default -> client_parent.setCenter(Model.getInstance().getViewFactory().getMainView());
				}
				});
	}

}
