package openjfx.ClockFX;
import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Clock {

	private StackPane root=new StackPane();
	private Circle circle=new Circle();
	private Line mili=new Line(),sec=new Line(),min=new Line(),hour=new Line();
	private Calendar date;

	private void drawLine(Line l,double x2,double y2,int thick) {
		
		l.setStartX(circle.getCenterX());l.setStartY(circle.getCenterX());
		l.setEndX(x2);l.setEndY(y2);l.setFill(Color.BLACK);l.setStrokeWidth(thick);
	}
	
	private void drawLines() {
	
		date=Calendar.getInstance();
		var deg=270+(date.get(Calendar.MILLISECOND)*0.36);
		drawLine(mili,Math.cos(Math.PI/180*deg)*circle.getRadius()*0.5,Math.sin(Math.PI/180*deg)*circle.getRadius()*0.5,2);
		deg=270+(date.get(Calendar.SECOND)*6);
		drawLine(sec,Math.cos(Math.PI/180*deg)*circle.getRadius()*0.9,Math.sin(Math.PI/180*deg)*circle.getRadius()*0.9,4);
		deg=270+(date.get(Calendar.MINUTE)*6);
		drawLine(min,Math.cos(Math.PI/180*deg)*circle.getRadius()*0.95,Math.sin(Math.PI/180*deg)*circle.getRadius()*0.95,5);
		deg=270+(date.get(Calendar.HOUR)%12)*30+date.get(Calendar.MINUTE)*0.5;
		drawLine(hour,Math.cos(Math.PI/180*deg)*circle.getRadius()*0.65,Math.sin(Math.PI/180*deg)*circle.getRadius()*0.65,6);
	}
	
	private void drawClock() {
	
		circle.setRadius(root.getHeight()*0.45);
		drawLines();
	}
	
	Clock(){
		
		circle.setFill(Color.DARKGREEN);
		root.getChildren().add(new Group(circle,mili,sec,min,hour));
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		root.heightProperty().addListener(e->drawClock());
		
		var timer=new Timeline(new KeyFrame(Duration.millis(10), e->drawClock()));
		timer.setCycleCount(Animation.INDEFINITE);timer.play();
	}
	
	public StackPane givClock() {
		return root;
	}
	
}
