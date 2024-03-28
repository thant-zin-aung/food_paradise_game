package com.blacksky.food_paradise.controllers;

import com.blacksky.food_paradise.models.AnimationStyle;
import com.blacksky.food_paradise.models.AudioPlayer;
import com.blacksky.food_paradise.models.MainGameBoard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainController {

    private boolean stillAnimateHomeScreenFoods = true;
    private final int Food_Icon_Scale_Speed = 600;

    private final AnimationStyle animationStyle = new AnimationStyle();
    private final AudioPlayer audioPlayer = new AudioPlayer();
    private MainGameBoard mainGameBoard;
    @FXML
    public AnchorPane homeRoot;
    @FXML
    public Button startGameButton;
    @FXML
    public HBox firstWrapper,secondWrapper,thirdWrapper;
    @FXML
    public CheckBox mouseMovementCheckBox,fullscreenCheckBox;

    public void initialize() {
        audioPlayer.play("backgroundSound",1,0.3,true);
        animateStartGameButton();
        animateHomeScreenFoodIcons();
    }

    private void animateHomeScreenFoodIcons() {
        Thread firstAndThirdWrapperThread = new Thread( () -> {
            try {
                while ( stillAnimateHomeScreenFoods ) {
                    for ( int count = 0 ; count < firstWrapper.getChildren().size() ; count++ ) {
                        animationStyle.playScaleEffect(firstWrapper.getChildren().get(count), Food_Icon_Scale_Speed,2,true,1,1,1.1,1.2);
                        Thread.sleep(Food_Icon_Scale_Speed / 2);
                    }
                    for ( int count = firstWrapper.getChildren().size()-1 ; count >= 0  ; count-- ) {
                        animationStyle.playScaleEffect(secondWrapper.getChildren().get(count), Food_Icon_Scale_Speed,2,true,1,1,1.1,1.2);
                        Thread.sleep(Food_Icon_Scale_Speed / 2);
                    }
                    for ( int count = 0 ; count < firstWrapper.getChildren().size() ; count++ ) {
                        animationStyle.playScaleEffect(thirdWrapper.getChildren().get(count), Food_Icon_Scale_Speed,2,true,1,1,1.1,1.2);
                        Thread.sleep(Food_Icon_Scale_Speed / 2);
                    }
                }
            } catch ( Exception e ) {
                System.out.println("Error in looping home screen foods...");
            }
        });

//        Thread secondWrapperThread = new Thread( () -> {
//            try {
//                while ( stillAnimateHomeScreenFoods ) {
//                    for ( int count = firstWrapper.getChildren().size()-1 ; count >= 0  ; count-- ) {
//                        animationStyle.playScaleEffect(secondWrapper.getChildren().get(count), Food_Icon_Scale_Speed,2,true,1,1,1.2,1.2);
//                        Thread.sleep(Food_Icon_Scale_Speed / 2);
//                    }
//                }
//            } catch ( Exception e ) {
//                e.printStackTrace();
//            }
//        });

        firstAndThirdWrapperThread.start();
//        secondWrapperThread.start();
    }
    private void animateStartGameButton() {
        animationStyle.playScaleEffect(startGameButton,500,-1,true,1,1,1.1,1.1);
    }
    private void closeHomeStage() {
        Stage homeStage = (Stage) homeRoot.getScene().getWindow();
        homeStage.close();
    }
    @FXML
    private void clickOnStartGameButton() {
        mainGameBoard = new MainGameBoard(mouseMovementCheckBox.isSelected(),fullscreenCheckBox.isSelected());
        stillAnimateHomeScreenFoods=false;
        closeHomeStage();
        mainGameBoard.startGame();
    }



}