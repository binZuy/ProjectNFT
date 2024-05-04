package UI.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Functions.function1.blogNews;
import Functions.function1.twitterNews;
import UI.Model.Model;
import UI.View.TimeOptions;
import UI.View.NewsType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Function2Controller {
	//private LocalDate localDate;
	//private Functions.function1.detailTwitter detailTwitter;
	//private Functions.function1.detailBlog detailBlog;

	public Function2Controller() {
		//this.detailTwitter = new Functions.function1.detailTwitter();
		//this.detailBlog = new Functions.function1.detailBlog();

	}

	
	@FXML
	private DatePicker datePicker;
	  
	@FXML
    private ChoiceBox<NewsType> choiceSel;
	
	@FXML
    private ChoiceBox<TimeOptions> choiceTime;

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

	public void initialize() {
		// Initialization logic goes here
		choiceTime.setItems(FXCollections.observableArrayList(TimeOptions.DAY, TimeOptions.WEEK, TimeOptions.MONTH));
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
	                tblViewBlogResult.setItems(Model.getInstance().getViewFactory().
	                  detailBlog.getListBlog());
	                
	                /*tblViewBlogResult.getSelectionModel().selectedItemProperty().addListener(
	            			new ChangeListener<blogNews>() {
	            				@Override
	            				public void changed(ObservableValue<? extends blogNews> observable, blogNews oldValue,
	            						blogNews newValue) {
	            					if(newValue != null) {
	            					}
	            				}
	            			});*/
	                
	                
	                //FilteredList<blogNews> filteredList = new FilteredList<>(Model.getInstance().getViewFactory()
	              //  		.detailBlog.getListBlog(), p -> true);
	              //  tblViewBlogResult.setItems(filteredList);
	                
	                if(choiceTime.getValue()==TimeOptions.DAY) {
	            		dayPressedBlog();
	            	}
	            	else if( choiceTime.getValue()==TimeOptions.WEEK)
	            	{
	            		weekPressedBlog();
	            	}
	            	else if(choiceTime.getValue()==TimeOptions.MONTH) {
	            		monthPressedBlog();
	            	}
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
	                
	               /* tblViewTwitterResult.getSelectionModel().selectedItemProperty().addListener(
	            			new ChangeListener<twitterNews>() {
	            				@Override
	            				public void changed(ObservableValue<? extends twitterNews> observable, twitterNews oldValue,
	            						twitterNews newValue) {
	            					if(newValue != null) {
	            					}
	            				}
	            			});
	                */
	               // FilteredList<twitterNews> filteredList = new FilteredList<>(Model.getInstance().getViewFactory()
	                //		.detailTwitter.getListTwitter(), p -> true);
	                //tblViewTwitterResult.setItems(filteredList);
	                
	                if(choiceTime.getValue()==TimeOptions.DAY) {
	            		dayPressedTwitter();
	            	}
	            	else if( choiceTime.getValue()==TimeOptions.WEEK)
	            	{
	            		weekPressedTwitter();
	            	}
	            	else if(choiceTime.getValue()==TimeOptions.MONTH) {
	            		monthPressedTwitter();
	            	}
	            	tblViewTwitterResult.setRowFactory(tableView -> {
	            		TableRow<twitterNews> row = new TableRow<>();
	            	row.setOnMouseClicked(event -> {
	            	if (event.getClickCount() == 2 && (!row.isEmpty())) {
	                twitterNews obj = row.getItem();
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

	String getDatePattern() {
		LocalDate localDate = datePicker.getValue();
		String pattern = "yyyy-MM-dd";
		String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
		return datePattern.toString();
	}
	
	void dayPressedBlog() {
		//test case: 2023-12-18 
		String datePattern = getDatePattern();
		ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		listBlog = Model.getInstance().getViewFactory().detailBlog.getPostsForDay(
				Model.getInstance().getViewFactory().detailBlog.getListBlog(), datePattern.toString());
		FilteredList<blogNews> filteredListBlog = new FilteredList<>(listBlog, p -> true);
		tblViewBlogResult.setItems(filteredListBlog);
	 
		String mostpopular = Model.getInstance().getViewFactory().
				detailBlog.findMostPopularKeyword(filteredListBlog);
		createBlogFrame(mostpopular);
	}
	
	void dayPressedTwitter() {
		//test case: 2023-12-18 
		String datePattern = getDatePattern();
		
		ObservableList<twitterNews> listTwitter = FXCollections.observableArrayList();
		listTwitter = Model.getInstance().getViewFactory().detailTwitter.getPostsForDay(
				Model.getInstance().getViewFactory().detailTwitter.getListTwitter(), datePattern.toString());
		FilteredList<twitterNews> filteredListTwitter = new FilteredList<>(listTwitter, p -> true);
		tblViewTwitterResult.setItems(filteredListTwitter);
		String mostpopular = Model.getInstance().getViewFactory().
				detailTwitter.findMostPopularHashtag(filteredListTwitter);
		createTwitterFrame(mostpopular);
	}
	
	void weekPressedBlog() {
		//2023-12-15
		String datePattern = getDatePattern();
		ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		listBlog = Model.getInstance().getViewFactory().
				detailBlog.getPostsForWeek(Model.getInstance().getViewFactory().
						detailBlog.getListBlog(), datePattern);
		FilteredList<blogNews> filteredListBlog = new FilteredList<>(listBlog, p -> true);
		tblViewBlogResult.setItems(filteredListBlog);
		String mostpopular = Model.getInstance().getViewFactory().
				detailBlog.findMostPopularKeyword(filteredListBlog);
		createBlogFrame(mostpopular);
	}
	
	void weekPressedTwitter() {
		//2023-12-15
		String datePattern = getDatePattern();
		ObservableList<twitterNews> listTwitter = FXCollections.observableArrayList();
		listTwitter = Model.getInstance().getViewFactory().
				detailTwitter.getPostsForWeek(Model.getInstance().getViewFactory().
						detailTwitter.getListTwitter(), datePattern);
		FilteredList<twitterNews> filteredListTwitter = new FilteredList<>(listTwitter, p -> true);
		tblViewTwitterResult.setItems(filteredListTwitter);
		String mostpopular = Model.getInstance().getViewFactory().
				detailTwitter.findMostPopularHashtag(filteredListTwitter);
		createTwitterFrame(mostpopular);
	}

	
	void monthPressedBlog() {
		//2023-05-05
		String datePattern = getDatePattern();
		ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		listBlog = Model.getInstance().getViewFactory().
				detailBlog.getPostsForMonth(Model.getInstance().getViewFactory().
						detailBlog.getListBlog(), datePattern);
		FilteredList<blogNews> filteredListBlog = new FilteredList<>(listBlog, p -> true);
		tblViewBlogResult.setItems(filteredListBlog);
		String mostpopular = Model.getInstance().getViewFactory().
				detailBlog.findMostPopularKeyword(filteredListBlog);
		createBlogFrame(mostpopular);
	}
	
	void monthPressedTwitter() {
		//2023-12-01
		String datePattern = getDatePattern();
		ObservableList<twitterNews> listTwitter = FXCollections.observableArrayList();
		listTwitter = Model.getInstance().getViewFactory().
				detailTwitter.getPostsForMonth(Model.getInstance().getViewFactory().
						detailTwitter.getListTwitter(), datePattern);
		FilteredList<twitterNews> filteredListTwitter = new FilteredList<>(listTwitter, p -> true);
		tblViewTwitterResult.setItems(filteredListTwitter);
		String mostpopular = Model.getInstance().getViewFactory().
				detailTwitter.findMostPopularHashtag(filteredListTwitter);
		createTwitterFrame(mostpopular);
	}

	void createBlogFrame(String string) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Most popular Keyword");
		alert.setHeaderText(null);
		alert.setContentText("Keyword: " + string);

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.showAndWait();
	}
	
	void createTwitterFrame(String string) {
		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setTitle("Most popular Hashtag");
		alert2.setHeaderText(null);
		alert2.setContentText("Hashtag: " + string);

		Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
		stage2.showAndWait();
	}
	
}