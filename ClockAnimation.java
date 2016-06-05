//U10416003
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class ClockAnimation extends Application {
	@Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
        ClockPane clockPane = new ClockPane(); // Create a clock
        clockPane.start();
		
		//create the button
        Button btStart = new Button("Start"); 
        btStart.setOnAction(e -> clockPane.start()); //action to start
        Button btStop = new Button("Stop"); 
        btStop.setOnAction(e -> clockPane.stop()); //action to stop

        //create HBox
        HBox hBox = new HBox(btStart, btStop);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane(clockPane, null, null, hBox, null);
        
        // Create a place it in the stage
        primaryStage.setScene(new Scene(borderPane)); 
        primaryStage.setTitle("U10416003 ClockAnimation"); 
        primaryStage.show(); 
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	class ClockPane extends Pane {
		//int hour ,minute,second
		private int hour;
		private int minute;
		private int second;

		//set Clock pane width and height
		private double w = 250, h = 250;

		// Create a handler for animation
		private EventHandler<ActionEvent> eventHandler = e -> {
			setCurrentTime(); // Set a new clock time
		};

		// Create an animation for a running clock
		private Timeline animation = new Timeline(
			new KeyFrame(Duration.millis(1000), eventHandler));

			/** Construct a default clock with the current time*/
			public ClockPane() {
				setCurrentTime();
	
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.play(); // Start animation
		}

		/** Construct a clock with specified hour, minute, and second*/
		public ClockPane(int hour, int minute, int second) {
			this.hour = hour;
			this.minute = minute;
			this.second = second;
			paintClock();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play(); // Start animation
		}

		public void pause() {
			animation.pause();
		}
		//action start button
		public void start() {
			animation.play();
		}
		//action stop button
		public void stop() {
			animation.stop();
		}

		// Return hour 
		public int getHour() {
			return hour;
		}

		//Set a new hour
		public void setHour(int hour) {
			this.hour = hour;
			paintClock();
		}

		// Return minute
		public int getMinute() {
			return minute;
		}

		// Set the minute
		public void setMinute(int minute) {
			this.minute = minute;
			paintClock();
		}

		//Return second
		public int getSecond() {
			return second;
		}

		//Set the second 
		public void setSecond(int second) {
			this.second = second;
			paintClock();
		}

		// Return clock pane width 
		public double getW() {
			return w;
		}

		// Set clock pane  width
		public void setW(double w) {
			this.w = w;
			paintClock();
		}

		// Return clock pane's height 
		public double getH() {
			return h;
		}

		// Set clock pane's height
		public void setH(double h) {
			this.h = h;
			paintClock();
		}

		// Set the current time for the clock 
		public void setCurrentTime() {
			// Construct a calendar for the current date and time
			Calendar calendar = new GregorianCalendar();

			// Set current hour, minute and second
			this.hour = calendar.get(Calendar.HOUR_OF_DAY);
			this.minute = calendar.get(Calendar.MINUTE);
			this.second = calendar.get(Calendar.SECOND);

			paintClock(); // Repaint the clock
		}

		// Paint the clock 
		private void paintClock() {
			// Initialize clock parameters
			double clockRadius = Math.min(w, h) * 0.8 * 0.5;
			double centerX = w / 2;
			double centerY = h / 2;

			// Draw circle
			Circle circle = new Circle(centerX, centerY, clockRadius);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
			Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
			Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
			Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

			// Draw second hand
			double sLength = clockRadius * 0.8;
			double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
			double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
			Line sLine = new Line(centerX, centerY, secondX, secondY);
			sLine.setStroke(Color.BLUE);

			// Draw minute hand
			double mLength = clockRadius * 0.65;
			double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
			double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
			Line mLine = new Line(centerX, centerY, xMinute, minuteY);
			mLine.setStroke(Color.YELLOW);

			// Draw hour hand
			double hLength = clockRadius * 0.5;
			double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
			double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
			Line hLine = new Line(centerX, centerY, hourX, hourY);
			hLine.setStroke(Color.RED);

			getChildren().clear();
			getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
		}
	}
}



 
