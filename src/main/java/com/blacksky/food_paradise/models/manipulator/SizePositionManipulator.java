package com.blacksky.food_paradise.models.manipulator;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SizePositionManipulator {
    private AnchorPane gameRoot;

    public void setGameRoot(AnchorPane gameRoot) {
        this.gameRoot = gameRoot;
    }

    public double getGameScreenWidth() {
        return this.gameRoot.getWidth();
    }
    public double getGameScreenHeight() {
        return this.gameRoot.getHeight();
    }

    public double getIconWidth(ImageView imageView) {
        return imageView.getFitWidth();
    }
    public double getIconHeight(ImageView imageView) {
        return imageView.getFitHeight();
    }

    public double getPositionX(ImageView imageView) {
        return imageView.getLayoutX();
    }
    public double getPositionY(ImageView imageView) {
        return imageView.getLayoutY();
    }

}
