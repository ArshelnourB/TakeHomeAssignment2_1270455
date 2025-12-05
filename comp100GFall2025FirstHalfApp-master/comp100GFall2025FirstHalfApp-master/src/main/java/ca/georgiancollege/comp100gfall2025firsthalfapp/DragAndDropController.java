package ca.georgiancollege.comp100gfall2025firsthalfapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.security.SecureRandom;
import java.util.stream.IntStream;

public class DragAndDropController {

    @FXML
    AnchorPane layout;
    @FXML
    Pane pane1, pane2, pane3;

    @FXML
    Label
    timer, score, message, category1, category2, category3, word1, word2, word3, word4, word5, word6, word7, word8;

    @FXML
    Button restartButton, nextModeButton;

    private int millisecond = 1000;
    private long timeStart = System.currentTimeMillis();

    private int maxTimeInSeconds, intervalInMS, timeBonus, scoreCount;

    private int gameId = 0;
    private double orginalPositionX, orginalPositionY;

    private int numberOfRectanglesToAnswer, numOfCorrectAnswers, numOfIncorrectAnswers;
    Timeline timelineGameOver, timelineRemoveMessage, timelineRunTimer;

    File winSound = new File("src/main/resources/sounds/win.mp3");
    File loseSound = new File("src/main/resources/sounds/lose.mp3");
    File correctSound = new File("src/main/resources/sounds/correct.wav");
    File incorrectSound = new File("src/main/resources/sounds/incorrect.wav");
    File switchSound = new File("src/main/resources/sounds/switch.WAV");
    File restartSound = new File("src/main/resources/sounds/restart.wav");

    Media media;
    MediaPlayer player;

    public DragAndDropController(){
        maxTimeInSeconds = 200;
        intervalInMS = 100;
        timeBonus = 222;
    }
    @FXML
    private void initialize(){

        clearScoreboard();
        timedGame();
        //gameNumbers();
//        gameWordsLetters();

        restartButton.setOnAction(e -> restartGame());
        nextModeButton.setOnAction(e -> nextGame());
        scoreCount = 0;
        // word1: 23, 594
        // word2: 136, 594
        // word3: 250, 594
        // word4: 366, 594
        // word5: 23, 658
        // word6: 136, 658
        // word7: 250, 658
        // word8: 366, 658
        gameWordsLength();
    }

    private void initializeColorGame(){
        clearScoreboard();
        timedGame();
        scoreCount = 0;
        gameColors();
    }

    private void clearScoreboard(){

        timer.setText("");
        score.setText("0");
        message.setText("");
        numOfCorrectAnswers = 0;
        numOfIncorrectAnswers = 0;
//        System.out.println(word1.getOnMouseReleased());
//        System.out.println(word1.getOnMousePressed());
//        System.out.println(word1.getOnMouseDragged());
    }

    private void restartGame(){

        timelineRunTimer.stop();

        resetPositions();
        if(gameId == 0){
            initialize();
        }
        else{
            initializeColorGame();
        }
        media = new Media(restartSound.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(0.2);
        player.play();
    }

    private void nextGame(){
        timelineRunTimer.stop();
        resetPositions();
        if(gameId == 0){
            initializeColorGame();
            gameId = 1;
        }
        else if (gameId == 1){
            initialize();
            gameId = 0;
        }
        media = new Media(switchSound.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(0.2);
        player.play();

    }

    private void resetPositions(){
        pane1.getChildren().clear();
        pane2.getChildren().clear();
        pane3.getChildren().clear();

        layout.getChildren().remove(word1);
        layout.getChildren().remove(word2);
        layout.getChildren().remove(word3);
        layout.getChildren().remove(word4);
        layout.getChildren().remove(word5);
        layout.getChildren().remove(word6);
        layout.getChildren().remove(word7);
        layout.getChildren().remove(word8);

        layout.getChildren().add(word1);
        layout.getChildren().add(word2);
        layout.getChildren().add(word3);
        layout.getChildren().add(word4);
        layout.getChildren().add(word5);
        layout.getChildren().add(word6);
        layout.getChildren().add(word7);
        layout.getChildren().add(word8);

        word1.setText("ABCDEFG");
        word2.setText("ABCDEFG");
        word3.setText("ABCDEFG");
        word4.setText("ABCDEFG");
        word5.setText("ABCDEFG");
        word6.setText("ABCDEFG");
        word7.setText("ABCDEFG");
        word8.setText("ABCDEFG");

        numberOfRectanglesToAnswer = 0;


//        layout.setLeftAnchor(word1, 23.0);
//        layout.setLeftAnchor(word2, 136.0);
//        layout.setLeftAnchor(word3, 250.0);
//        layout.setLeftAnchor(word4, 366.0);
//        layout.setLeftAnchor(word5, 23.0);
//        layout.setLeftAnchor(word6, 136.0);
//        layout.setLeftAnchor(word7, 250.0);
//        layout.setLeftAnchor(word8, 366.0);
//
//        layout.setTopAnchor(word1, 594.0);
//        layout.setTopAnchor(word2, 594.0);
//        layout.setTopAnchor(word3, 594.0);
//        layout.setTopAnchor(word4, 594.0);
//        layout.setTopAnchor(word5, 658.0);
//        layout.setTopAnchor(word6, 658.0);
//        layout.setTopAnchor(word7, 658.0);
//        layout.setTopAnchor(word8, 658.0);

        word1.setLayoutX(23);
        word2.setLayoutX(136);
        word3.setLayoutX(250);
        word4.setLayoutX(366);
        word5.setLayoutX(23);
        word6.setLayoutX(136);
        word7.setLayoutX(250);
        word8.setLayoutX(366);

        word1.setLayoutY(594);
        word2.setLayoutY(594);
        word3.setLayoutY(594);
        word4.setLayoutY(594);
        word5.setLayoutY(658);
        word6.setLayoutY(658);
        word7.setLayoutY(658);
        word8.setLayoutY(658);
    }

    private void timedGame(){

        timer.setText(String.valueOf(maxTimeInSeconds));
/*
        Runnable task = ()->{
            double currentTime = Double.parseDouble(timer.getText());
            currentTime*=1000;

            currentTime -= intervalInMS;
            timer.setText(String.valueOf(currentTime));
        };
        Platform.runLater(task);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(task, intervalInMS, intervalInMS, TimeUnit.MILLISECONDS);
*/


        EventHandler eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double currentTime = Double.parseDouble(timer.getText());
                currentTime*=millisecond;

                currentTime -= intervalInMS;
                currentTime/= millisecond;

                currentTime = Math.round(currentTime * 10) / 10.0;

                /*
                if(currentTime % 4 == 0)
                    calculateBonusPoints();
                */

                //System.out.println(currentTime);
                timer.setText(String.valueOf(currentTime));


            }
        };
        timelineRunTimer = new Timeline(new KeyFrame(Duration.millis(intervalInMS), eh));

        timelineRunTimer.setCycleCount(maxTimeInSeconds * millisecond / intervalInMS );
        timelineRunTimer.play();
    }

    private void gameWordsLength(){

        category1.setText("Len < 4");
        category2.setText("Len 4-6");
        category3.setText("Len 7+");

        String[] arr = {"shake","elide","wrack","beele","podiatry","siamang","subjoin","trommel"};
        int counter = 0;

        for(Node item : layout.getChildren()){

            if(item instanceof Label){

                Label current = ((Label)item);

                if(current.getText().equals("ABCDEFG")){
                    current.setTextFill(Paint.valueOf("#e80808"));
                    current.setStyle("-fx-background-color: lightgrey; -fx-border-color: black");
                    current.setText(arr[counter++]);
                    current.setOnMousePressed(this::pressedLabel);
                    current.setOnMouseReleased(this::releaseLabelWordLength);
                    current.setOnMouseDragged(this::dragLabel);

                    numberOfRectanglesToAnswer++;

                }
            }

        }

/*
        Runnable task = ()->{

            try{
                TimeUnit.SECONDS.sleep(maxTimeInSeconds);

            }
            catch (InterruptedException e){
                message.setText("");
            }
        };

        Platform.runLater(task);
  */
        EventHandler gameover = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                layout.setDisable(true);
            }
        };



        timelineGameOver = new Timeline(new KeyFrame(Duration.seconds(maxTimeInSeconds), gameover));
        Platform.runLater(timelineGameOver::play);




    }

    private void gameColors(){
        category1.setText("Red");
        category2.setText("Blue");
        category3.setText("Green");

        String[] arr = {"red","blue","green","red","blue","green","red","blue"};
        int counter = 0;

        for(Node item : layout.getChildren()){

            if(item instanceof Label){

                Label current = ((Label)item);

                if(current.getText().equals("ABCDEFG")){
                    current.setTextFill(Paint.valueOf("#000000"));
                    current.setStyle("-fx-background-color: " + arr[counter] + "; -fx-border-color: black");
                    current.setText(arr[counter++]);
                    current.setOnMousePressed(this::pressedLabel);
                    current.setOnMouseReleased(this::releaseLabelWordLength);
                    current.setOnMouseDragged(this::dragLabel);

                    numberOfRectanglesToAnswer++;
                }
            }

        }
/*
        Runnable task = ()->{

            try{
                TimeUnit.SECONDS.sleep(maxTimeInSeconds);

            }
            catch (InterruptedException e){
                message.setText("");
            }
        };

        Platform.runLater(task);
  */
        EventHandler gameover = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                layout.setDisable(true);
            }
        };

        timelineGameOver = new Timeline(new KeyFrame(Duration.seconds(maxTimeInSeconds), gameover));
        Platform.runLater(timelineGameOver::play);
    }
    private void gameNumbers(){

        category1.setText("# < 0");
        category2.setText("# < 50");
        category3.setText("# >= 50");

        SecureRandom secureRandom = new SecureRandom();
        IntStream ints = secureRandom.ints(8, -20, 150);

        int[] arr = ints.toArray();
        int counter = 0;

        for(Node item : layout.getChildren()){

            if(item instanceof Label){

                Label current = ((Label)item);
                if(current.getText().equals("ABCDEFG")){
                        current.setText(String.valueOf(arr[counter++]));
                        current.setOnMousePressed(this::pressedLabel);
                    current.setOnMouseReleased(this::releaseLabel);
                    current.setOnMouseDragged(this::dragLabel);

                    numberOfRectanglesToAnswer++;

                }
            }

        }

/*
        Runnable task = ()->{

            try{
                TimeUnit.SECONDS.sleep(maxTimeInSeconds);

            }
            catch (InterruptedException e){
                message.setText("");
            }
        };

        Platform.runLater(task);
  */
        EventHandler gameover = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                layout.setDisable(true);
            }
        };



        timelineGameOver = new Timeline(new KeyFrame(Duration.seconds(maxTimeInSeconds), gameover));
        Platform.runLater(timelineGameOver::play);




    }

    @FXML
    private void pressedLabel(MouseEvent event){

        Label current = (Label)event.getSource();

        orginalPositionX = current.getLayoutX();
        orginalPositionY = current.getLayoutY();
        System.out.println("orig = " + orginalPositionX + ", " + orginalPositionY );

    }
    @FXML
    private void dragLabel(MouseEvent event){

        // System.out.println("Dragged to " + event.getSceneX() + ", " + event.getSceneY());

        Label current = (Label)event.getSource();

        current.setLayoutX(event.getSceneX());
        current.setLayoutY(event.getSceneY());


    }
    @FXML
    private void releaseLabel(MouseEvent event){

        Label current = (Label)event.getSource();



        //System.out.println("Result = " + labelWithinPane(pane1, current.getLayoutX(), current.getLayoutY()));

        if(labelWithinPane(pane1, current.getLayoutX(), current.getLayoutY())){
            checkIfCorrect(1, current);
            addLabelToPane(pane1, current);
        }
        else if(labelWithinPane(pane2, current.getLayoutX(), current.getLayoutY())){
            checkIfCorrect(2, current);
            addLabelToPane(pane2, current);
        }
        else if(labelWithinPane(pane3, current.getLayoutX(), current.getLayoutY())){
            checkIfCorrect(3, current);
            addLabelToPane(pane3, current);
        }
        else{
            current.setLayoutX(orginalPositionX);
            current.setLayoutY(orginalPositionY);

        }

        orginalPositionX = 0;
        orginalPositionY = 0;

        checkToSeeIfGameIsOver();


    }
    private void releaseLabelWordLength(MouseEvent event){

        Label current = (Label)event.getSource();


        //System.out.println("Result = " + labelWithinPane(pane1, current.getLayoutX(), current.getLayoutY()));

        if(labelWithinPane(pane1, current.getLayoutX(), current.getLayoutY())){
            checkIfCorrectWordLength(1, current);
            addLabelToPane(pane1, current);
        }
        else if(labelWithinPane(pane2, current.getLayoutX(), current.getLayoutY())){
            checkIfCorrectWordLength(2, current);
            addLabelToPane(pane2, current);
        }
        else if(labelWithinPane(pane3, current.getLayoutX(), current.getLayoutY())){
            checkIfCorrectWordLength(3, current);
            addLabelToPane(pane3, current);
        }
        else{
            current.setLayoutX(orginalPositionX);
            current.setLayoutY(orginalPositionY);

        }

        orginalPositionX = 0;
        orginalPositionY = 0;

        checkToSeeIfGameIsOver();
    }

    private boolean labelWithinPane(Pane container, double x, double y){


        //System.out.println("X = " + x + " Container X = " + container.getLayoutX());

        return x >= container.getLayoutX() && x<= container.getLayoutX() + container.getWidth()
                &&
                y >= container.getLayoutY() && y<= container.getLayoutY() + container.getHeight();

    }
    private void checkIfCorrect(int i, Label current) {

        int value = Integer.parseInt(current.getText());
        switch (i){
            case 1:
                if(value < 0){
                    calculateBonusPoints();
                    splashMessage("Success!");
                    numOfCorrectAnswers++;

                }
                else{
                    splashMessage("Error");
                    numOfIncorrectAnswers++;

                }
                break;

            case 2:
                if(value >= 0 && value < 50){
                    calculateBonusPoints();
                    splashMessage("Success!");
                    numOfCorrectAnswers++;
                }
                else{
                    splashMessage("Error");
                    numOfIncorrectAnswers++;
                }
                break;
            case 3:
                if(value >= 50){
                    calculateBonusPoints();
                    splashMessage("Success!");
                    numOfCorrectAnswers++;
                }
                else{
                    splashMessage("Error");
                    numOfIncorrectAnswers++;
                }
                break;
        }
    }
    private void checkIfCorrectWordLength(int i, Label current) {

        if(gameId == 0){
            int value = current.getText().length();
            switch (i){
                case 1:
                    if(value < 4){
                        splashMessage("Success!");
                        numOfCorrectAnswers++;
                        scoreCount += 100;
                        media = new Media(correctSound.toURI().toString());
                    }
                    else{
                        splashMessage("Error");
                        numOfIncorrectAnswers++;
                        scoreCount -= 50;
                        media = new Media(incorrectSound.toURI().toString());
                    }
                    break;

                case 2:
                    if(value >= 4 && value < 6){
                        splashMessage("Success!");
                        numOfCorrectAnswers++;
                        scoreCount += 100;
                        media = new Media(correctSound.toURI().toString());
                    }
                    else{
                        splashMessage("Error");
                        numOfIncorrectAnswers++;
                        scoreCount -= 50;
                        media = new Media(incorrectSound.toURI().toString());
                    }
                    break;
                case 3:
                    if(value >= 7){
                        splashMessage("Success!");
                        numOfCorrectAnswers++;
                        scoreCount += 100;
                        media = new Media(correctSound.toURI().toString());
                    }
                    else{
                        splashMessage("Error");
                        numOfIncorrectAnswers++;
                        scoreCount -= 50;
                        media = new Media(incorrectSound.toURI().toString());
                    }
                    break;
            }
        }
        else if (gameId == 1) {
            String value = current.getText();
            switch (i){
                case 1:
                    if(value.equals("red")){
                        splashMessage("Success!");
                        numOfCorrectAnswers++;
                        scoreCount += 100;
                        media = new Media(correctSound.toURI().toString());
                    }
                    else{
                        splashMessage("Error");
                        numOfIncorrectAnswers++;
                        scoreCount -= 50;
                        media = new Media(incorrectSound.toURI().toString());

                    }
                    break;

                case 2:
                    if(value.equals("blue")){
                        splashMessage("Success!");
                        numOfCorrectAnswers++;
                        scoreCount += 100;
                        media = new Media(correctSound.toURI().toString());
                    }
                    else{
                        splashMessage("Error");
                        numOfIncorrectAnswers++;
                        scoreCount -= 50;
                        media = new Media(incorrectSound.toURI().toString());
                    }
                    break;

                case 3:
                    if(value.equals("green")){
                        splashMessage("Success!");
                        numOfCorrectAnswers++;
                        scoreCount += 100;
                        media = new Media(correctSound.toURI().toString());
                    }
                    else{
                        splashMessage("Error");
                        numOfIncorrectAnswers++;
                        scoreCount -= 50;
                        media = new Media(incorrectSound.toURI().toString());
                    }
                    break;
            }
        }

        calculateBonusPoints();
        player = new MediaPlayer(media);
        player.setVolume(0.2);
        player.play();
    }
    private void addLabelToPane(Pane pane, Label label){

        int numChildren = pane.getChildren().size();

        numChildren++;
        System.out.println(numChildren);
        double tx = 10;
        double ty = pane.getHeight() - 10 - (label.getHeight() * numChildren + 5);
        label.setLayoutX(tx);
        label.setLayoutY(ty );
//        System.out.println(tx + ", " + ty);

        pane.getChildren().add(label);
        label.setOnMouseReleased(null);
        label.setOnMousePressed(null);
        label.setOnMouseDragged(null);
    }

    private void splashMessage(String text){

        message.setText(text);
        /*
        Runnable task = ()->{

            try{
                TimeUnit.SECONDS.sleep(1);
                message.setText("");
            }
            catch (InterruptedException e){
            }
        };

        Platform.runLater(task);
        */

        EventHandler removemessage = (ActionEvent) -> message.setText("");



        timelineRemoveMessage = new Timeline(new KeyFrame(Duration.seconds(3), removemessage));
        Platform.runLater(timelineRemoveMessage::play);


    }
    private void calculateBonusPoints(){

        score.setText(String.valueOf(scoreCount));

    }
    private void checkToSeeIfGameIsOver(){
        System.out.println("****************");
        System.out.println(numOfCorrectAnswers);
        System.out.println(numOfIncorrectAnswers);
        System.out.println(numberOfRectanglesToAnswer);
        System.out.println("****************");


        if(numOfCorrectAnswers + numOfIncorrectAnswers == numberOfRectanglesToAnswer){

            StringBuilder message = new StringBuilder("Game is over");
            message.append("\n");
            long bonus = ( (System.currentTimeMillis() - timeStart)/1000L) * timeBonus;
            scoreCount+= (int) bonus;
            calculateBonusPoints();

            if(numOfCorrectAnswers == numberOfRectanglesToAnswer) {
                message.append("You win!");
                media = new Media(winSound.toURI().toString());
            }
            else{
                message.append("You lose!");
                media = new Media(loseSound.toURI().toString());
            }
            player = new MediaPlayer(media);
            player.setVolume(0.2);
            player.play();

            //new Alert(Alert.AlertType.INFORMATION, message.toString(), ButtonType.OK).show();
            //layout.setDisable(true);
            timelineGameOver.stop();
            timelineRemoveMessage.stop();
            timelineRunTimer.stop();
            results();

        }
    }

    private void results() {
        String gameMode;
        String gameResult;
        if(gameId == 0){
            gameMode = "Word Length";
        }
        else{
            gameMode = "Color";
        }

        if(numOfCorrectAnswers == numberOfRectanglesToAnswer) {
            gameResult = "You win!";
        }
        else{
            gameResult = "You lose...";
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox resultsVBox = new VBox();
        Text resultsText = new Text(gameResult +
                "\nTotal Score: " + scoreCount +
                "\nNumber of correct answers: " + numOfCorrectAnswers +
                "\nNumber of incorrect answers: " + numOfIncorrectAnswers +
                "\nTIme remaining: " + timer.getText() +
                "\nGame mode: " + gameMode +
                "\n\nPlay Again?"
        );
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            restartGame();
            stage.close();
        });
        resultsVBox.getChildren().addAll(resultsText, restartButton);

        Scene resultsScene = new Scene(resultsVBox, 200, 200);
        stage.setScene(resultsScene);
        stage.showAndWait();
    }

}
