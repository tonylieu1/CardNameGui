package Game;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.sun.scenario.effect.Effect;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CardNameGUI extends Application {
	ArrayList<Image> deck = new ArrayList<>();
	ImageView deckView = new ImageView();
	ImageView firstCard = new ImageView();
	ImageView secondCard = new ImageView();
	
	public static void main(String[] args) {
	CardNameGUI Cgg = new CardNameGUI();

	launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoadCardsImages("C:\\Users\\tl02965\\git\\CardNameGui\\src\\Cards");
		
		//this is where it places the images
		HBox hb = new HBox(20);
		Random NumRam = new Random();
		
		//this is where it get the images from the array
	for(int i = 0; i < 2; i++) {
		deckView.setImage(deck.get(NumRam.nextInt(50)));
		firstCard.setImage(deck.get(NumRam.nextInt(50)));
		firstCard.setRotate(0);
		
		
		}

		
		hb.getChildren().addAll(deckView,firstCard, createButtons());
		
		
		//create the stages for everything to happen on
		Scene sc = new Scene(hb);
		primaryStage.setScene(sc);
		primaryStage.show();
		
	}
	public boolean LoadCardsImages(String location) {
		Path CardImages = Paths.get(location);
	
		if(!Files.isDirectory(CardImages)) {
			System.out.println("this is not the Files your looking for!");
			return false;
		}
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(CardImages)) {
		for(Path entry: stream) {
			System.out.println(entry.toUri());
			deck.add(new Image(entry.toUri().toString()));
				
		}} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
		
		
	}
	public Pane createButtons() {
		//set the place for tehe button
		HBox hb = new HBox(30);
		
		// create button
		Button forward = new Button("Forward");
		forward.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int index = deck.indexOf(deckView.getImage());
				deckView.setImage(deck.get(index+1));
				event.consume();
			}
			
		});
		
		//this move the cards back
		Button Back = new Button("Backwards");
		Back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int index = deck.indexOf(deckView.getImage());
				deckView.setImage(deck.get(index-1));
				event.consume();
						
				
			}
			
		});
		
		hb.getChildren().addAll(forward, Back);
		return hb;
		
		
	}

}
