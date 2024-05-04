package UI.View;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Functions.function1.JsonProcessor;
import Functions.function1.blogNews;
import Functions.function1.twitterNews;
import UI.Controller.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
	private final ObjectProperty<TimeOptions> timeSelectedItem;
	private NewsType newsType;
	//Client View
	private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem; // Create a flag when change screen
	private AnchorPane clientView;
	private AnchorPane mainView;
	private AnchorPane function1View;
	private AnchorPane function2View;
	
	public Functions.function1.detailTwitter detailTwitter;
	public Functions.function1.detailBlog detailBlog;

	
	public ViewFactory() {
		this.timeSelectedItem = new SimpleObjectProperty<>();
		this.newsType = NewsType.BLOG;
		this.detailBlog= new Functions.function1.detailBlog();
		this.detailTwitter = new Functions.function1.detailTwitter();
		this.clientSelectedMenuItem = new SimpleObjectProperty<>();
	}
	
	public NewsType getNewsType () {
		return this.newsType;
	}
	
	public void setNewsType(NewsType newsType) {
		this.newsType= newsType;
	}
	
	public ObjectProperty<TimeOptions> getTimeSelectedItem(){
		return timeSelectedItem;
	}
	 
	
	public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
		return clientSelectedMenuItem;
	}
	
	public AnchorPane getClientView() {
		if( clientView == null) { // Save resource for reload
			try {
				clientView = new FXMLLoader(getClass().getResource("/UI/Resource/Client.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clientView;
	}
	
	public void showClientView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/Client.fxml"));
		ClientController clientController = new ClientController();
		loader.setController(clientController);
		createStage(loader);
	}
	
	public AnchorPane getMainView() { 
		if( mainView == null) {
			try {
				mainView = new FXMLLoader(getClass().getResource("/UI/Resource/Main.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mainView;
	}
	
	public void showMainView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/Main.fxml"));
		createStage(loader);
	}
	
	public AnchorPane getFunction1View() {
		if( function1View == null) { 
			try {
				function1View = new FXMLLoader(getClass().getResource("/UI/Resource/Function1.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return function1View;
	}
	
	public void showFunction1View() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/Function1.fxml"));
		createStage(loader);
	}

	
	public void showUpdateView() {
		String twitterFile = "src/data/outputData/TwitterData/twitter.json";
		String blogFile = "src/data/outputData/blogData/nftically.json";
		String blog2File = "src/data/outputData/blogData/nonFungible.json";
		String blog3File = "src/data/outputData/blogData/nftnewstoday.json";
		String blog4File = "src/data/outputData/blogData/airnft.json";

		String twitterJsonString = null;
		String blogJsonString = null;
		String blog2JsonString = null;
		String blog3JsonString = null;
		String blog4JsonString = null;

		try {
			twitterJsonString = JsonProcessor.readJsonFile(twitterFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			blogJsonString = JsonProcessor.readJsonFile(blogFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			blog2JsonString = JsonProcessor.readJsonFile(blog2File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blog3JsonString = JsonProcessor.readJsonFile(blog3File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			blog4JsonString = JsonProcessor.readJsonFile(blog4File);

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (this.detailTwitter != null) {
			this.detailTwitter.loadData(twitterJsonString);
		} else {
			System.err.println("Error: detailTwitter is null");
		}
		if (this.detailBlog != null) {
			this.detailBlog.loadData(blogJsonString);
			this.detailBlog.loadData(blog2JsonString);
			this.detailBlog.loadData(blog3JsonString);
			this.detailBlog.loadData(blog4JsonString);

		} else {
			System.err.println("Error: detailBlog is null");
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notice");
		alert.setHeaderText(null);
		alert.setContentText("NFTs news can be accessed now!");

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.showAndWait();
	}
	
	public AnchorPane getFunction2View() {
		if( function2View == null) {
			try {
				function2View = new FXMLLoader(getClass().getResource("/UI/Resource/Function2.fxml")).load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return function2View;
	}
	
	
	public void showFunction2View() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/Function2.fxml"));
		createStage(loader);
	}
	
	private void createStage(FXMLLoader loader) {
		Scene scene = null;
		try {
			scene = new Scene(loader.load());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Project NFT");
		stage.show();
	}
	
	
	public void createBlogView(blogNews entity) throws IOException {
		JPanel blogPanel = new JPanel();
		blogPanel.setLayout(new BoxLayout(blogPanel, BoxLayout.Y_AXIS));
		blogPanel.setPreferredSize(new Dimension(600, 600)); // Set a fixed size for the JPanel
		// Add components to the blog view panel
		JLabel titleLabel = new JLabel("Title: " + entity.getTitle());
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel timeLabel = new JLabel("Time: " + entity.getTime());
		timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel keywordsLabel = new JLabel("Keywords: " + String.join(", ", entity.getKeywords()));
		keywordsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		// Create a JTextArea for the content
		JTextArea contentArea = new JTextArea(entity.getContent());
		contentArea.setEditable(false);
		contentArea.setLineWrap(true); // Enable text wrapping
		contentArea.setWrapStyleWord(true); // Wrap at word boundaries

		// Wrap the content area in a JScrollPane for scrolling
		JScrollPane contentScrollPane = new JScrollPane(contentArea);

		JLabel urlLabel = new JLabel("URL: " + entity.getUrl());
		urlLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		blogPanel.add(titleLabel);
		blogPanel.add(timeLabel);
		blogPanel.add(keywordsLabel);
		blogPanel.add(contentScrollPane); // Add the JScrollPane instead of the JTextArea directly
		blogPanel.add(urlLabel);

		// Show the JOptionPane with the custom blog view panel
		JOptionPane.showMessageDialog(null, blogPanel, "Blog View", JOptionPane.PLAIN_MESSAGE);
	}

	public void createTwitterView(twitterNews entity) throws IOException {
		JPanel twitterPanel = new JPanel();
		twitterPanel.setLayout(new BoxLayout(twitterPanel, BoxLayout.Y_AXIS));
		twitterPanel.setPreferredSize(new Dimension(500, 500));

		JLabel authorLabel = new JLabel("Author: " + entity.getAuthor());
		authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel hashtagsLabel = new JLabel("Hashtags: " + String.join(", ", entity.getHashtags()));
		hashtagsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextArea contentArea = new JTextArea(entity.getContent());
		contentArea.setEditable(false);
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setPreferredSize(new Dimension(450,200));

		JLabel timeLabel = new JLabel("Time: " + entity.getTime().formatted(DateTimeFormatter.ISO_DATE_TIME));
		timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		twitterPanel.add(authorLabel);
		twitterPanel.add(hashtagsLabel);
		twitterPanel.add(contentArea);
		twitterPanel.add(timeLabel);

		JOptionPane.showMessageDialog(null, twitterPanel, "Twitter View", JOptionPane.PLAIN_MESSAGE);
	}
	
	
	public void closeStage(Stage stage) {
		stage.close();
	}
}
