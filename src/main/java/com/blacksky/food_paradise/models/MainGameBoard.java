package com.blacksky.food_paradise.models;

import com.blacksky.food_paradise.Main;
import com.blacksky.food_paradise.models.manipulator.GameCharacterAnimationManipulator;
import com.blacksky.food_paradise.models.manipulator.SizePositionManipulator;
import com.blacksky.food_paradise.models.manipulator.ImageManipulator;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainGameBoard {
    private final double stageMinWidth =1280;
    private final double stageMinHeight =720;

    private final double GAME_CHARACTER_WIDTH_IN_1400 = 90;
    private final double GAME_CHARACTER_WIDTH_IN_2000 = 150;
    private double gameCharacterWidthHeight;

    private boolean isPlayerMoveLeftByKey;
    private boolean isPlayerMoveRightByKey;
    private boolean isGameEnd;
    private double gameScreenWidth;
    private double gameScreenHeight;
    private int score;
    private double gameChracterMovementSpeed = 30;
    private int gameItemMovementSpeed = 2;
    private final int gameItemAddSpeedFrom = 1500;
    private final int gameItemAddSpeedTo = 3000;
    private double animationYAxisDistance = 300;
    private double currentMouseCursorPosition = 0;
    private double tempCurrentMouseCursorPosition = 0;
    private ImageManipulator imageManipulator;
    private ImageView gameCharacter;
    private SizePositionManipulator sizePositionManipulator;
    private Stage gameRootStage;
    private AnchorPane gameRoot;
    private Scene gameRootScene;
    private AnimationTimer gameAnimationTimer;
    private GameCharacterAnimationManipulator gameCharacterAnimationManipulator;
    public MainGameBoard(boolean isMouseFeatureAdded,boolean isFullScreen) {
        imageManipulator = new ImageManipulator();
        sizePositionManipulator = new SizePositionManipulator();
        score = 0;
        gameRoot = new AnchorPane();
        gameRoot.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/stylesheets/game-styles.css")).toExternalForm());
        gameRootScene = new Scene(gameRoot,1280,720);
        gameRootScene.setCursor(Cursor.NONE);
        gameRootStage = new Stage();
        gameRootStage.setScene(gameRootScene);
        // Set to fullscreen mode if checkbox was selected...
        if ( isFullScreen ) {
            gameRootStage.setFullScreen(true);
            gameRootStage.setFullScreenExitHint("");
        }
        gameRootStage.setResizable(false);
        gameRootStage.setMinWidth(stageMinWidth);
        gameRootStage.setMinHeight(stageMinHeight);
        gameRootStage.setTitle("Food Paradise");
        // Add mouse movement feature if checkbox was selected...
        if ( isMouseFeatureAdded ) listenOnMouseEvent();
        listenOnKeyEvent();
    }

    // Can't put this method inside constructor because of priority...
    private void syncGameScreenWidthHeight() {
        sizePositionManipulator.setGameRoot(gameRoot);
        gameScreenWidth = sizePositionManipulator.getGameScreenWidth();
        gameScreenHeight = sizePositionManipulator.getGameScreenHeight()-5;
        if ( gameScreenWidth < 1400 ) {
            gameCharacterWidthHeight = GAME_CHARACTER_WIDTH_IN_1400;
        } else if ( gameScreenWidth > 2000 ) {
            gameCharacterWidthHeight = GAME_CHARACTER_WIDTH_IN_2000;
        }
    }

    private void moveCharacterPosition(ImageView icon, boolean isLeft, boolean isRight) {
        double destinationPosition = 0;
        if ( isLeft ) {
            destinationPosition = sizePositionManipulator.getPositionX(icon) - gameChracterMovementSpeed;
            if ( isMovableArea(destinationPosition, sizePositionManipulator.getIconWidth(icon))) {
                gameCharacterAnimationMonitor();
                icon.setLayoutX(destinationPosition);
            }
        } else if ( isRight ) {
            destinationPosition = sizePositionManipulator.getPositionX(icon) + gameChracterMovementSpeed;
            if ( isMovableArea(destinationPosition, sizePositionManipulator.getIconWidth(icon))) {
                gameCharacterAnimationMonitor();
                icon.setLayoutX(destinationPosition);
            }
        }
    }

    private void moveGameItemsPosition() {
        for ( int count=0 ; count<gameRoot.getChildren().size() ; count++ ) {
            if (gameRoot.getChildren().get(count) instanceof ImageView gameItem) {
                if ( imageManipulator.getGameItemImageViewList().contains(gameItem) ) {
                    if ( gameItem.getLayoutY() > sizePositionManipulator.getGameScreenHeight() ) {
                        gameRoot.getChildren().remove(gameItem);
                        System.out.println("removed game item...");
                        gameCharacterAnimationMonitor();
                    } else {
                        // This is the section of animating game character...
                        if ( isGameItemWithinSpecificAreaOfGameCharacter(gameItem) ) {
                            if ( !imageManipulator.isCatMouthAlreadyOpen(gameCharacter.getImage().getUrl()) ) {
                                gameCharacterAnimationManipulator.animateNormalEating(false);
                            }
                        }
                        // This is the section of animating game character...
                        gameItem.setLayoutY(gameItem.getLayoutY()+gameItemMovementSpeed);
                    }
                }
            }
        }
    }

    private boolean isMovableArea(double destinationPosition,double iconWidth) {
        return 0 < destinationPosition && (sizePositionManipulator.getGameScreenWidth()-iconWidth)>destinationPosition;
    }

    private void listenOnMouseEvent() {
        gameRootScene.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            currentMouseCursorPosition = event.getX();
        });
        gameRootScene.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            if ( event.getX() < sizePositionManipulator.getGameScreenWidth()-sizePositionManipulator.getIconWidth(gameCharacter) ) {
                gameCharacterAnimationMonitor();
                gameCharacter.setLayoutX(event.getX());
            }
        });
    }
    private void listenOnKeyEvent() {
        gameRootScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if ( event.getCode() == KeyCode.LEFT ) {
                isPlayerMoveLeftByKey=true;
            } else if ( event.getCode() == KeyCode.RIGHT ) {
                isPlayerMoveRightByKey=true;
            }
        });
        gameRootScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if ( event.getCode() == KeyCode.LEFT ) {
                isPlayerMoveLeftByKey=false;
            } else if ( event.getCode() == KeyCode.RIGHT ) {
                isPlayerMoveRightByKey=false;
            }
        });
    }

    // Can't put this method inside constructor because of priority...
    private void deployGameCharacterPosition() {
        gameCharacter = imageManipulator.createImageViewByMapName("catMouth1",gameCharacterWidthHeight,gameCharacterWidthHeight);
        gameCharacterAnimationManipulator = new GameCharacterAnimationManipulator(gameCharacter);
        gameRoot.getChildren().add(gameCharacter);
        if ( currentMouseCursorPosition == 0 ) {
            gameCharacter.setLayoutX(gameScreenWidth/2-(sizePositionManipulator.getIconWidth(gameCharacter)/2));
        } else {
            gameCharacter.setLayoutX(currentMouseCursorPosition);
        }
        gameCharacter.setLayoutY(gameScreenHeight- sizePositionManipulator.getIconHeight(gameCharacter));
    }

    private void deployGameItemPosition(ImageView gameItem) {
        // set left bound to half of game item width...
        Platform.runLater(() -> {
            try {
                gameRoot.getChildren().add(gameItem);
                double leftBound = Math.round((gameItem.getFitWidth()/2));
                double rightBound = Math.round(sizePositionManipulator.getGameScreenWidth()-((gameItem.getFitWidth()*2)-(gameItem.getFitWidth()/2)));
                double positionX = NumberGenerator.generate((int)leftBound,(int)rightBound);
                gameItem.setLayoutX(positionX);
                gameItem.setLayoutY(0);
            } catch (Exception e ) {
                System.out.println(e.getMessage());
            }
        });

    }

    private void startRandomGameItemAdderThread() {
        new Thread( () -> {
            try {
                int randomItemNo;
                while (!isGameEnd) {
                    randomItemNo = NumberGenerator.generate(0,imageManipulator.getGameItemImageViewList().size()-1);
                    deployGameItemPosition(imageManipulator.getGameItemImageViewList().get(randomItemNo));
                    Thread.sleep(NumberGenerator.generate(gameItemAddSpeedFrom,gameItemAddSpeedTo));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean isGameItemWithinSpecificAreaOfGameCharacter(ImageView gameItem) {
        return gameItem.getLayoutX() > gameCharacter.getLayoutX() && gameItem.getLayoutX() < gameCharacter.getLayoutX()+gameCharacter.getFitWidth() &&
                gameItem.getLayoutY() > (sizePositionManipulator.getGameScreenHeight()-animationYAxisDistance);
    }

//    private void animateGameCharacter(ImageView gameItem) {
//        if ( isGameItemWithinSpecificAreaOfGameCharacter(gameItem) ) {
//
//            if ( !imageManipulator.isCatMouthAlreadyOpen(gameCharacter.getImage().getUrl()) ) {
//                gameCharacterAnimationManipulator.animateNormalEating(false);
//            }
//
//        } else {
//            if ( imageManipulator.isCatMouthAlreadyOpen(gameCharacter.getImage().getUrl()) ) {
//                gameCharacterAnimationManipulator.animateNormalEating(true);
//            }
//
//        }
//    }

    private void gameCharacterAnimationMonitor() {
        ImageView tempGameItem=null;
        for ( int count=0 ; count<gameRoot.getChildren().size() ; count++ ) {
            if (gameRoot.getChildren().get(count) instanceof ImageView gameItem) {
                if ( imageManipulator.getGameItemImageViewList().contains(gameItem) ) {
//                    animateGameCharacter(gameItem);
                    if ( isGameItemWithinSpecificAreaOfGameCharacter(gameItem) ) {
                        tempGameItem = gameItem;
                    }
                }
            }
        }
        if ( tempGameItem!=null ) {
            if ( !imageManipulator.isCatMouthAlreadyOpen(gameCharacter.getImage().getUrl()) ) {
                gameCharacterAnimationManipulator.animateNormalEating(false);
            }
        } else {
            if ( imageManipulator.isCatMouthAlreadyOpen(gameCharacter.getImage().getUrl()) ) {
                gameCharacterAnimationManipulator.animateNormalEating(true);
            }
        }
    }

    private void initializeAnimationTimer() {
        gameAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ( isPlayerMoveLeftByKey ) {
                    moveCharacterPosition(gameCharacter,true,false);
                }
                if ( isPlayerMoveRightByKey ) {
                    moveCharacterPosition(gameCharacter,false,true);
                }

                moveGameItemsPosition();
            }
        };
        gameAnimationTimer.start();
    }

    public void startGame() {
        gameRootStage.show();
        syncGameScreenWidthHeight();
        deployGameCharacterPosition();
        initializeAnimationTimer();
        startRandomGameItemAdderThread();
    }
}
