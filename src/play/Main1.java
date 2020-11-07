package play;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main1 extends Application implements Runnable {
    List<Matric> list = new ArrayList<>();
    int x = 0;
    int y = 0;
    int angle = 45;
    int speed = 5;
    int i = 0;
    Group root = new Group();
    Scene scene = new Scene(root, 600, 325);
    Label score;
    Arc wolf;
    //public static Circle circle = new Circle(Math.random() * 300, Math.random() * 300, 7, Color.RED);
    public static Circle circle = new Circle();
    Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {
        circle.setTranslateX((Math.random() * 300));
        circle.setTranslateY(Math.random() * 300);
        circle.setRadius(7);
        circle.setFill(Color.RED);

        primaryStage.setTitle("GAME");
        wolf = new Arc(16, 16, 16, 16, angle, 270);
        wolf.setType(ArcType.ROUND);

        Label count = new Label("Count");
        count.setPrefSize(50, 20);
        count.setLayoutX(500);
        count.setLayoutY(0);
        count.setFont(new Font("Cambria", 15));

        score = new Label();
        score.setText("0");
        score.setPrefSize(50, 20);
        score.setLayoutX(555);
        score.setLayoutY(0);

        scene.setFill(Color.valueOf("#ccccb3"));


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (change()) {
                    i++;
                    score.setText(String.valueOf(i));
                    circle.setTranslateX(Math.random() * (scene.getWidth() - 7));
                    circle.setTranslateY(Math.random() * (scene.getHeight() - 7));
                    add();
                    if (e()) {
                        String soundPath = "eat.wav";
                        Media media = new Media(new File(soundPath).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.play();
                    }
                }
                if (i > 15) {
                    speed = 10;
                }
                if (i == 20) {
                    JOptionPane.showMessageDialog(null, "You win", "Your score is" + score.getText(), JOptionPane.WARNING_MESSAGE);
                }
                if (gameOver()) {
                    JOptionPane.showMessageDialog(null, "Game Over", "Error", JOptionPane.WARNING_MESSAGE);
                    primaryStage.close();
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    wolf.setTranslateX(x += speed);
                    wolf.setStartAngle(45);
                    if (x >= scene.getWidth()) {
                        x = -32;
                    }
                }
                if (event.getCode() == KeyCode.LEFT) {
                    wolf.setTranslateX(x -= speed);
                    wolf.setStartAngle(225);
                    if (x <= -32) {
                        x = (int) scene.getWidth();
                    }
                }
                if (event.getCode() == KeyCode.UP) {
                    wolf.setTranslateY(y -= speed);
                    wolf.setStartAngle(135);
                    if (y <= -32) {
                        y = (int) scene.getHeight();
                    }

                }
                if (event.getCode() == KeyCode.DOWN) {
                    wolf.setStartAngle(-40);
                    wolf.setTranslateY(y += speed);
                    if (y >= scene.getHeight()) {
                        y = -32;
                    }
                }
            }
        });
        Main1 main1 = new Main1();
        Thread thread = new Thread(main1);
        thread.start();

        root.getChildren().addAll(wolf, circle, count, score);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

    }

    private void add() {
        int a = (int) (Math.random() * (scene.getWidth() - 7));
        int b = (int) (Math.random() * (scene.getHeight() - 7));
        boolean f = true;
        while (f) {
            Rectangle r = new Rectangle(a, b, 7, 7);
            r.setFill(Color.LIGHTGREEN);
            r.setStroke(Color.GREEN);
            r.setStrokeWidth(2);
            if ((r.getX() + 4.9 != wolf.getCenterX() && (r.getY() + 4.9 != wolf.getCenterY()) && r.getX() + 4.9 != circle.getTranslateX()) && r.getY() + 4.9 != circle.getTranslateY()) {
                Matric matric = new Matric(r.getX() + 4.9, r.getY() + 4.9);
                list.add(matric);
                root.getChildren().addAll(r);
                f = false;
            }
        }
    }

    public boolean e() {
        if (controller.b1) {
            return true;
        }
        return false;
    }


    public boolean gameOver() {
        for (Matric matric : list) {
            if (Math.abs(wolf.getTranslateX() - matric.getX1() + 16) <= 16 && Math.abs(wolf.getTranslateY() - matric.getY1() + 16) <= 16) {
                return true;
            }

        }
        return false;
    }

    public boolean change() {
        if (Math.abs(wolf.getTranslateX() + 16 - circle.getTranslateX()) < 17 && Math.abs(wolf.getTranslateY() + 16 - circle.getTranslateY()) < 17) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            circle.setTranslateX(Math.random() * (scene.getWidth() - 7));
            circle.setTranslateY(Math.random() * (scene.getHeight() - 7));
    }
}
