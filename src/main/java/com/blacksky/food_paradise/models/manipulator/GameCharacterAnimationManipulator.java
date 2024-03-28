package com.blacksky.food_paradise.models.manipulator;

import javafx.scene.image.ImageView;

public class GameCharacterAnimationManipulator {
    private final int animationDuration = 40;
    private boolean isStillAnimating;
    private final ImageView gameCharacter;
    private final ImageManipulator imageManipulator;
    public GameCharacterAnimationManipulator(ImageView gameCharacter) {
        imageManipulator = new ImageManipulator();
        this.gameCharacter = gameCharacter;
    }

    public void animateNormalEating(boolean setReverse) {
        Thread animateThread = new Thread ( () -> {
            try {
                isStillAnimating = true;
                if ( !setReverse ) {
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth1"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth2"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth3"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth4"));
                } else {
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth4"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth3"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth2"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth1"));
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            isStillAnimating = false;
        });
        if ( !isStillAnimating ) animateThread.start();
    }

    public void animateHappyEating(boolean setReverse) {
        Thread animateThread = new Thread ( () -> {
            try {
                isStillAnimating = true;
                if ( !setReverse ) {
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth5"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth6"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth7"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth4"));
                } else {
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth4"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth7"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth6"));
                    Thread.sleep(animationDuration);
                    gameCharacter.setImage(imageManipulator.createImageByMapName("catMouth5"));
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            isStillAnimating = false;
        });

        if (!isStillAnimating) animateThread.start();
    }
}
