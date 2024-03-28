package com.blacksky.food_paradise.models.manipulator;

import com.blacksky.food_paradise.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

public class ImageManipulator {
    private final double IMAGE_WIDTH = 50;
    private final double IMAGE_HEIGHT = 50;
    private Map<String,String> imageMap;
    private ObservableList<ImageView> gameItemImageViewList;

    public ImageManipulator() {
        imageMap = new HashMap<>();
        gameItemImageViewList = FXCollections.observableArrayList();
        mapImagesWithPath();


    }

    private String convertCatMouthsImagePath(String imageName) {
        return "/images/cat_mouths/"+imageName+".jpg";
    }
    private String convertFoodsImagePath(String imageName) {
        String imagePath = "/images/foods/"+imageName+".png";
        addGameItemToList(imagePath);
        return imagePath;
    }
    private String convertNonFoodsImagePath(String imageName) {
        String imagePath = "/images/not_foods/"+imageName+".png";
        addGameItemToList(imagePath);
        return imagePath;
    }
    private String convertOthersImagePath(String imageName) {
        return "/images/not_foods/"+imageName+".png";
    }

    private void mapImagesWithPath() {
        imageMap.put("catMouth1",convertCatMouthsImagePath("cat_mouth_1"));
        imageMap.put("catMouth2",convertCatMouthsImagePath("cat_mouth_2"));
        imageMap.put("catMouth3",convertCatMouthsImagePath("cat_mouth_3"));
        imageMap.put("catMouth4",convertCatMouthsImagePath("cat_mouth_4"));
        imageMap.put("catMouth5",convertCatMouthsImagePath("cat_mouth_5"));
        imageMap.put("catMouth6",convertCatMouthsImagePath("cat_mouth_6"));
        imageMap.put("catMouth7",convertCatMouthsImagePath("cat_mouth_7"));
        imageMap.put("apple",convertFoodsImagePath("apple"));
        imageMap.put("bananas",convertFoodsImagePath("bananas"));
        imageMap.put("basket",convertFoodsImagePath("basket"));
        imageMap.put("beef",convertFoodsImagePath("beef"));
        imageMap.put("berry",convertFoodsImagePath("berry"));
        imageMap.put("bread",convertFoodsImagePath("bread"));
        imageMap.put("bubbleTea",convertFoodsImagePath("bubble-tea"));
        imageMap.put("cake",convertFoodsImagePath("cake"));
        imageMap.put("chocolate",convertFoodsImagePath("chocolate"));
        imageMap.put("coffee",convertFoodsImagePath("coffee"));
        imageMap.put("cola",convertFoodsImagePath("cola"));
        imageMap.put("cookies",convertFoodsImagePath("cookies"));
        imageMap.put("dimsum",convertFoodsImagePath("dimsum"));
        imageMap.put("donut",convertFoodsImagePath("donut"));
        imageMap.put("dragonFruit",convertFoodsImagePath("dragon-fruit"));
        imageMap.put("drink",convertFoodsImagePath("drink"));
        imageMap.put("durian",convertFoodsImagePath("durian"));
        imageMap.put("eggs",convertFoodsImagePath("eggs"));
        imageMap.put("fish",convertFoodsImagePath("fish"));
        imageMap.put("friedRice",convertFoodsImagePath("fried-rice"));
        imageMap.put("fruit",convertFoodsImagePath("fruit"));
        imageMap.put("fruitJuice",convertFoodsImagePath("fruit-juice"));
        imageMap.put("gameIcon",convertFoodsImagePath("game-icon"));
        imageMap.put("grape",convertFoodsImagePath("grape"));
        imageMap.put("grilling",convertFoodsImagePath("grilling"));
        imageMap.put("hamLeg",convertFoodsImagePath("ham-leg"));
        imageMap.put("hamburger",convertFoodsImagePath("hamburger"));
        imageMap.put("hotPot",convertFoodsImagePath("hot-pot"));
        imageMap.put("iceCream",convertFoodsImagePath("ice-cream"));
        imageMap.put("jelly",convertFoodsImagePath("jelly"));
        imageMap.put("juices",convertFoodsImagePath("juices"));
        imageMap.put("lime",convertFoodsImagePath("lime"));
        imageMap.put("mango",convertFoodsImagePath("mango"));
        imageMap.put("milk",convertFoodsImagePath("milk"));
        imageMap.put("pancake",convertFoodsImagePath("pancake"));
        imageMap.put("peach",convertFoodsImagePath("peach"));
        imageMap.put("pineapple",convertFoodsImagePath("pineapple"));
        imageMap.put("pizza",convertFoodsImagePath("pizza"));
        imageMap.put("popcorn",convertFoodsImagePath("popcorn"));
        imageMap.put("popsicle",convertFoodsImagePath("popsicle"));
        imageMap.put("potatoFries",convertFoodsImagePath("potato-fries"));
        imageMap.put("ramen",convertFoodsImagePath("ramen"));
        imageMap.put("ramen2",convertFoodsImagePath("ramen2"));
        imageMap.put("salad",convertFoodsImagePath("salad"));
        imageMap.put("shrimp",convertFoodsImagePath("shrimp"));
        imageMap.put("strawberry",convertFoodsImagePath("strawberry"));
        imageMap.put("sweets",convertFoodsImagePath("sweets"));
        imageMap.put("topokki",convertFoodsImagePath("topokki"));
        imageMap.put("turkey",convertFoodsImagePath("turkey"));
        imageMap.put("waterMelon",convertFoodsImagePath("water-melon"));

        imageMap.put("airplane",convertNonFoodsImagePath("airplane"));
        imageMap.put("ancestors",convertNonFoodsImagePath("ancestors"));
        imageMap.put("book",convertNonFoodsImagePath("book"));
        imageMap.put("computer",convertNonFoodsImagePath("computer"));
        imageMap.put("dog",convertNonFoodsImagePath("dog"));
        imageMap.put("dress",convertNonFoodsImagePath("dress"));
        imageMap.put("flowerPot",convertNonFoodsImagePath("flower-pot"));
        imageMap.put("glasses",convertNonFoodsImagePath("glasses"));
        imageMap.put("horse",convertNonFoodsImagePath("horse"));
        imageMap.put("keyChain",convertNonFoodsImagePath("key-chain"));
        imageMap.put("lightBulb",convertNonFoodsImagePath("light-bulb"));
        imageMap.put("mountains",convertNonFoodsImagePath("mountains"));
        imageMap.put("phoneCall",convertNonFoodsImagePath("phone-call"));
        imageMap.put("sneakers",convertNonFoodsImagePath("sneakers"));
        imageMap.put("sun",convertNonFoodsImagePath("sun"));
        imageMap.put("tree",convertNonFoodsImagePath("tree"));
        imageMap.put("umbrella",convertNonFoodsImagePath("umbrella"));
        imageMap.put("vehicle",convertNonFoodsImagePath("vehicle"));
        imageMap.put("wristWatch",convertNonFoodsImagePath("wrist-watch"));
        imageMap.put("xbox",convertNonFoodsImagePath("xbox"));
    }
    private void addGameItemToList(String imagePath) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResource(imagePath)).toString()));
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        gameItemImageViewList.add(imageView);
    }

    public Image createImageByMapName(String imageMapName) {
        return new Image(Objects.requireNonNull(Main.class.getResource(imageMap.get(imageMapName))).toString());
    }

    public ImageView createImageViewByMapName(String imageMapName) {
        ImageView imageView = new ImageView(createImageByMapName(imageMapName));
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        return imageView;
    }

    public ImageView createImageViewByMapName(String imageMapName, double width, double height) {
        ImageView imageView = new ImageView(createImageByMapName(imageMapName));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public ObservableList<ImageView> getGameItemImageViewList() {
        return gameItemImageViewList;
    }

    public boolean isCatMouthAlreadyOpen(String imagePath) {
        String[] imagePathArray = imagePath.split("/");
        String fileName = imagePathArray[imagePathArray.length-1];
        imagePathArray = imageMap.get("catMouth4").split("/");
        String openMouthFileName = imagePathArray[imagePathArray.length-1];
        return fileName.equals(openMouthFileName);
    }

}
