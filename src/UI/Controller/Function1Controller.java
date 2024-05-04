package UI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Functions.function1.blogNews;
import Functions.function1.twitterNews;
import UI.Model.Model;
import UI.View.NewsType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Function1Controller implements Initializable {
	
	@FXML
    private ChoiceBox<NewsType> choiceSel;

    @FXML
    private TextField search_HK;

    @FXML
    private Button search_btn;
 
    @FXML
	private TableColumn<blogNews, String> colTime1;

	@FXML
	private TableColumn<twitterNews, String> colTime;

	@FXML
	private TableColumn<twitterNews, List<String>> colHashtags;

	@FXML
	private TableColumn<twitterNews, String> colAuthor;

	@FXML
	private TableColumn<blogNews, String> colTitle;

	@FXML
	private TableView<blogNews> tblViewBlogResult;

	@FXML
	private TableColumn<blogNews, String> colContent1;

	@FXML
	private TableView<twitterNews> tblViewTwitterResult;

	@FXML
	private TableColumn<blogNews, String> colUrl1;

	@FXML
	private TableColumn<twitterNews, String> colUrl;

	@FXML
	private TableColumn<twitterNews, String> colContent;

	@FXML
	private TableColumn<blogNews, List<String>> colKeywords;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		choiceSel.setItems(FXCollections.observableArrayList(NewsType.BLOG, NewsType.TWITTER));
		choiceSel.setValue(Model.getInstance().getViewFactory().getNewsType());
		choiceSel.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
	        	Model.getInstance().getViewFactory().setNewsType(choiceSel.getValue());
	            if (Model.getInstance().getViewFactory().getNewsType().equals(NewsType.BLOG)) {
	                // Update the TableView for BlogNews
	           	 tblViewTwitterResult.setVisible(false);
	           	 tblViewBlogResult.setVisible(true);
	           	 colTitle.setCellValueFactory(
	                		new PropertyValueFactory<blogNews,String>("title"));
	                colTime1.setCellValueFactory(
	                		new PropertyValueFactory<blogNews,String>("time"));
	                colKeywords.setCellValueFactory(
	                		new PropertyValueFactory<>("keywords"));
	                colContent1.setCellValueFactory(
	                		new PropertyValueFactory<blogNews,String>("content"));
	                colUrl1.setCellValueFactory(
	                		new PropertyValueFactory<blogNews,String>("url"));
	                tblViewBlogResult.setItems(Model.getInstance().getViewFactory().detailBlog.getListBlog());
	                
	                tblViewBlogResult.getSelectionModel().selectedItemProperty().addListener(
	            			new ChangeListener<blogNews>() {
	            				@Override
	            				public void changed(ObservableValue<? extends blogNews> observable, blogNews oldValue,
	            						blogNews newValue) {
	            					if(newValue != null) {
	            					}
	            				}
	            			});
	                
	                FilteredList<blogNews> filteredList = new FilteredList<>(Model.getInstance().getViewFactory()
	                		.detailBlog.getListBlog(), p -> true);
	                tblViewBlogResult.setItems(filteredList);
	            	search_HK.textProperty().addListener(new ChangeListener<String>() {
	            		@Override
	            		public void changed(ObservableValue<? extends String> 
	            		observable, String oldValue, String newValue) {
	            			filteredList.setPredicate(news -> {
	                            if (newValue == null || newValue.trim().isEmpty()) {
	                                return true;
	                            }
	                                String lowerCaseFilter = newValue.toLowerCase();
	                                return news.getKeywords().stream().anyMatch(tag -> tag.contains(lowerCaseFilter));
	                                
	            			});
	            		}
	            	});
	            	
	            	tblViewBlogResult.setRowFactory(tableView -> {
	                   TableRow<blogNews> row = new TableRow<>();
	                   row.setOnMouseClicked(event -> {
	                       if (event.getClickCount() == 2 && (!row.isEmpty())) {
	                           blogNews entity = row.getItem();
	                           try {
									Model.getInstance().getViewFactory().createBlogView(entity);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                       }
	                   });
	                   return row;
	               });
	            }
	            else if (Model.getInstance().getViewFactory().getNewsType().equals(NewsType.TWITTER)) {
	                // Update the TableView for TwitterNews
	           	 tblViewBlogResult.setVisible(false);
	           	 tblViewTwitterResult.setVisible(true);
	           	 colAuthor.setCellValueFactory(
	                		new PropertyValueFactory<twitterNews,String>("author"));
	                colTime.setCellValueFactory(
	                		new PropertyValueFactory<twitterNews,String>("time"));
	                colHashtags.setCellValueFactory(
	                		new PropertyValueFactory<>("hashtags"));
	                colContent.setCellValueFactory(
	                		new PropertyValueFactory<twitterNews,String>("content"));
	                tblViewTwitterResult.setItems(Model.getInstance().getViewFactory()
	                		.detailTwitter.getListTwitter());
	                
	                tblViewTwitterResult.getSelectionModel().selectedItemProperty().addListener(
	            			new ChangeListener<twitterNews>() {
	            				@Override
	            				public void changed(ObservableValue<? extends twitterNews> observable, twitterNews oldValue,
	            						twitterNews newValue) {
	            					if(newValue != null) {
	            					}
	            				}
	            			});
	                
	                FilteredList<twitterNews> filteredList = new FilteredList<>(Model.getInstance().getViewFactory()
	                		.detailTwitter.getListTwitter(), p -> true);
	                tblViewTwitterResult.setItems(filteredList);
	            	search_HK.textProperty().addListener(new ChangeListener<String>() {
	            		@Override
	            		public void changed(ObservableValue<? extends String> 
	            		observable, String oldValue, String newValue) {
	            			filteredList.setPredicate(news -> {
	                            if (newValue == null || newValue.trim().isEmpty()) {
	                                return true;
	                            }
	                                String lowerCaseFilter = newValue.toLowerCase();
	                                return news.getHashtags().stream().anyMatch(tag -> tag.contains(lowerCaseFilter));
	                                });
	            			}
	            		});
	            	tblViewTwitterResult.setRowFactory(tableView -> {
	            		TableRow<twitterNews> row = new TableRow<>();
	            	row.setOnMouseClicked(event -> {
	            	if (event.getClickCount() == 2 && (!row.isEmpty())) {
	                twitterNews obj = row.getItem();
	                //System.out.println(obj.getAuthor() +"\n"+ obj.getTime()+"\n" +obj.getContent()+"\n" + obj.getHashtags());
	                try {
	                	Model.getInstance().getViewFactory().createTwitterView(obj);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	}
	            	});
	            	return row;
	            	});
	            }
	        }
	    });
	}

}
