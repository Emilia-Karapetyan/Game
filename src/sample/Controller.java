package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import play.Main1;

import java.io.File;

public class Controller {
    boolean b = false;
    public static boolean b1 = false;
    MediaPlayer mediaPlayer;
    @FXML
    Button exit;
    @FXML
    Button play;
    @FXML
    RadioMenuItem music;
    @FXML
    RadioMenuItem sound;

    @FXML
    public void click() throws Exception {
        if (exit.isArmed()) {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        } else if (play.isArmed()) {
            Main1 main1 = new Main1();
            Stage stage = new Stage();
            main1.start(stage);
        }
    }

    @FXML
    public void startMusic() {
        if (music.isSelected()) {
            String path = "e.mp3";
            Media media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            b = true;
        }
        if (!music.isSelected() && b) {
            mediaPlayer.stop();
            b = false;
        }
    }

    @FXML
    public void startSound() {
        if (sound.isSelected()) {
            b1=true;
        }
        if (!sound.isSelected()){
            b1=false;
        }

    }


}
