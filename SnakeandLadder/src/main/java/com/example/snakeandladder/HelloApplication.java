package com.example.snakeandladder;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Tile;
import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application {
  public int rand;
  public int num;
  public Label randResult;
  public int next[]=new int[101];
  public int numb[][]=new int[10][10];
  public int cirPosX[][]=new int[10][10];
  public int cirPosY[][]=new int[10][10];
  public int ladderPosition[][]=new int[6][3];
  public Circle player1;
  public  Circle player2;
  public int playerPosition1=1;
    public int playerPosition2=1;
    public boolean player1Turn=true;
    public boolean player2Turn=true;
    public static int player1XPos=30;
    public static int player1YPos=570;
    public static int player2XPos=30;
    public static int player2YPos=570;
    public int posCirc1=1;
    public int posCirc2=1;
    public boolean gameStart=false;
    public Button gameButton;
    public static final int Tile_Size=60;
    public static final int width=10;
    public static final int height=10;
    private Group tileGroup=new Group();
    HashMap<Integer,int[]> map;
    public int num2;
    private Parent createContent()
    {
        num=1;
        num2=1;
        map=new HashMap<>();
        Pane root=new Pane();
        root.setPrefSize(width*Tile_Size,(height*Tile_Size)+80);
        root.getChildren().addAll(tileGroup);
        for(int i=1;i<=100;i++)
        {
            next[i]=i;
        }
        next[3]=39;
        next[10]=12;
        next[16]=13;
        next[27]=53;
        next[31]=4;
        next[47]=25;
        next[66]=52;
        next[63]=60;
        next[72]=90;
        next[97]=75;
        next[61]=99;
        next[56]=84;

        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++) {
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j * Tile_Size);
                tile.setTranslateY(i * Tile_Size);
                tileGroup.getChildren().add(tile);
                cirPosX[i][j] = (j) * (Tile_Size) + 30;
                cirPosY[i][j] = (i) * (Tile_Size) + 30;

            }

        }
        int curr=100;
        for(int i=0;i<height;i++)
        {
          if(i%2==0)
          {
              for(int j=0;j<width;j++)
              {

                  numb[i][j]=curr;
                  int x=cirPosX[i][j];
                  int y=cirPosY[i][j];
                  int cor[]={x,y};
                  map.put(curr,cor);
                  curr--;
              }
          }
          else
          {
              for(int j=width-1;j>=0;j--)
              {

                  numb[i][j]=curr;
                  int x=cirPosX[i][j];
                  int y=cirPosY[i][j];
                  int cor[]={x,y};
                  map.put(curr,cor);
                  curr--;
              }
          }

        }
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {


            }
        }
        player1 =new Circle(25);
        player1.setFill(Color.AQUA);
        player1.setId("player1");
        player1.getStyleClass().add("main/style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 =new Circle(25);
        player2.setFill(Color.CHOCOLATE);
        player2.setId("player2");
        player2.getStyleClass().add("main/style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);
        Button button1=new Button("Player 1");
        button1.setTranslateX(450);
        button1.setTranslateY(600);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart)
                {
                    if(player1Turn)
                    {

                         getDiceValue();
                         randResult.setText(String.valueOf(rand));
                        move1Player();
                        translatePlayer(player1XPos,player1YPos,player1);
                         num+=rand;
                         if(num<=100) {
                             int r = next[num];
                             player1XPos = map.get(r)[0];
                             player1YPos = map.get(r)[1];
                             int p = (player1XPos - 30) / Tile_Size;
                             int q = (player1YPos - 30) / Tile_Size;
                             num = numb[q][p];
                             translatePlayer(player1XPos, player1YPos, player1);
                         }
                         player1Turn=false;
                         player2Turn=true;
                    }
                }
            }
        });
        Button button2=new Button("Player2");
        button2.setTranslateX(60);
        button2.setTranslateY(600);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart)
                {
                    if(player2Turn)
                    {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move2Player();
                        translatePlayer(player2XPos,player2YPos,player2);
                        num2+=rand;
                        if(num2<=100) {
                            int r = next[num2];
                            player2XPos = map.get(r)[0];
                            player2YPos = map.get(r)[1];
                            int p = (player2XPos - 30) / Tile_Size;
                            int q = (player2YPos - 30) / Tile_Size;
                            num2 = numb[q][p];
                            translatePlayer(player2XPos, player2YPos, player2);
                        }
                        player2Turn=false;
                        player1Turn=true;

                    }
                }

            }
        });
        gameButton =new Button("Start Game");
        gameButton.setTranslateX(240);
        gameButton.setTranslateY(620);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
                 player1XPos=30;
                 player1YPos=570;
                 player2XPos=30;
                 player2YPos=570;
                 player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart=true;
            }
        });
        randResult =new Label("0");
        randResult.setTranslateX(281);
        randResult.setTranslateY(650);
        Image img=new Image("snakebg.jpeg");
        ImageView bgImage=new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(600);
        bgImage.setFitWidth(600);
        tileGroup.getChildren().addAll(bgImage,player1,player2,button1,button2,gameButton,randResult);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }



    private void move1Player()
    {
      for(int i=0;i<rand;i++)
      {
          if(posCirc1%2==1)
          {

              player1XPos+=60;
          }
          if(posCirc1%2==0)
          {

              player1XPos-=60;
          }
           if(player1XPos>570)
          {
              player1YPos-=60;
              player1XPos-=60;
              posCirc1++;
          }
          if(player1XPos<30)
          {

              player1XPos+=60;
              player1YPos-=60;
              posCirc1++;
          }

         if(player1XPos<30||player1YPos<30)
          {
              player1XPos=30;
              player1YPos=30;
              randResult.setText("Player 1 Won the Game");
              gameButton.setText("Start Again");
          }
      }
    }
    private void move2Player()
    {
        for(int i=0;i<rand;i++)
        {
            if(posCirc2%2==1)
            {

                player2XPos+=60;
            }
          if(posCirc2%2==0)
            {
                player2XPos-=60;
            }
           if(player2XPos>570)
            {
                player2XPos-=60;
                player2YPos-=60;
                posCirc2++;
            }
            if(player2XPos<30)
            {
                player2XPos+=60;
                player2YPos-=60;
                posCirc2++;
            }

            if(player2XPos<20||player2YPos<20)
            {
                player2XPos=30;
                player2YPos=30;
                randResult.setText("Player 2 Won the Game");
                gameButton.setText("Start Again");
            }
        }
    }
   private void getDiceValue()
   {
       rand=(int)(Math.random()*6)+1;
   }
   private void translatePlayer(int x,int y, Circle b)
   {
       TranslateTransition animate =new TranslateTransition(Duration.millis(1000),b);
       animate.setToX(x);
       animate.setToY(y);
       animate.play();
   }
    public static void main(String[] args) {
        launch(args);
    }
}