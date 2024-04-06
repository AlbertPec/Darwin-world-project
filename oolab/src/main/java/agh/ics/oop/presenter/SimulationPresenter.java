package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Statitics;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    Label statAnNrLabel;
    @FXML
    Label statGrNrLabel;
    @FXML
    Label statFrNrLabel;
    @FXML
    Label statAvLsLabel;
    @FXML
    Label statAvCiLabel;
    @FXML
    Label statAvEnLabel;
    @FXML
    Label dayL;
    @FXML
    GridPane mapGrid;
    @FXML
    Button runButton;
    @FXML
    Button startStopButton;
    @FXML
    ComboBox<Animal> animalsComboBox;
    @FXML
    Label mapId;
    @FXML
    Label gnmLabel;
    @FXML
    Label cgnLabel;
    @FXML
    Label eneLabel;
    @FXML
    Label eatLabel;
    @FXML
    Label chiLabel;
    @FXML
    Label offLabel;
    @FXML
    Label ageLabel;
    @FXML
    Label dodLabel;
    Simulation simulation;
    int width;
    int heigth;
    int fullEnergy;
    Animal followedAnimal=null;
    boolean active = true;
    Statitics stat;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
    private void drawMap(){
        Map<Vector2d, Boolean> vis = new HashMap<>();

        List<WorldElement> elements = simulation.getElements();

        for(int i=1;i<=width;i++){
            Text xy = new Text("" + (i-1));
            mapGrid.add(xy,i,0);
            mapGrid.setHalignment(xy, HPos.CENTER);
        }
        for(int i=1;i<=heigth;i++){
            Text xy = new Text("" + (heigth-i));
            mapGrid.add(xy,0,i);
            mapGrid.setHalignment(xy, HPos.CENTER);
        }
        Text xy = new Text("y\\x");
        mapGrid.add(xy,0,0);
        mapGrid.setHalignment(xy, HPos.CENTER);

        for(WorldElement e:elements){
            Vector2d pos = e.getPosition();

            if (vis.containsKey(pos)) continue;
            vis.put(pos, true);
            Label eleLabel = new Label(e.toString());
            eleLabel.setFont(new Font("Arial Bold",13.0));
            eleLabel.setAlignment(Pos.CENTER);
            eleLabel.setTextFill(e.getColor(fullEnergy));
            if (followedAnimal != null){
                if(pos.equals(followedAnimal.getPosition())) eleLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            }


            mapGrid.add(eleLabel, pos.getX()+1, heigth-pos.getY());
            mapGrid.setHalignment(eleLabel, HPos.CENTER);
        }
        //for
    }
    @Override
    public void mapChanged(Simulation simulation, String message) {
        Platform.runLater(()->{
            animalsComboBox.getItems().removeAll();
            animalsComboBox.getItems().addAll(simulation.getAnimals());
            animalsComboBox.getItems().add(null);

            drawFollowedAnimal();
            mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
            drawMap();
            drawStat();
            dayL.setText(simulation.getDay() + " (Oni nadal nic nie podejzewaja)");
        });
    }
    @FXML
    void runSim(){
        SimulationEngine engine= new SimulationEngine(List.of(simulation));
        engine.runAsync();
        runButton.setVisible(false);
    }
    void setBorder(int heigth,int width){
        this.heigth=heigth;
        this.width=width;
        Text xy;
        for (int i=0;i<=width;i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(25.0));
            if (i>0) {
                xy = new Text("" + (i-1));
                mapGrid.add(xy,i,0);
                mapGrid.setHalignment(xy, HPos.CENTER);
            }
        }
        for (int i=0;i<=heigth;i++){
            mapGrid.getRowConstraints().add(new RowConstraints(25.0));
            if (i<heigth) {
                xy = new Text("" + i);
                mapGrid.add(xy,0,heigth-i);
                mapGrid.setHalignment(xy, HPos.CENTER);
            }

        }
        xy = new Text("y\\x");
        mapGrid.add(xy,0,0);
        mapGrid.setHalignment(xy, HPos.CENTER);
    }

    public void setFullEnergy(int fullEnergy) {
        this.fullEnergy = fullEnergy;
    }
    @FXML
    void startStop(){
        synchronized (this){
            simulation.switchActive();
        }
        Platform.runLater(()->{
            if (this.active) {
                this.animalsComboBox.setVisible(true);
                this.startStopButton.setText("Start");
            }
            else {
                this.startStopButton.setText("Stop");
                this.animalsComboBox.setVisible(false);
                this.followedAnimal = animalsComboBox.getValue();
            }
            this.active = !this.active;
        });

    }
    private void drawFollowedAnimal(){
        if (followedAnimal==null){
            this.gnmLabel.setText("-");
            this.cgnLabel.setText("-");
            this.eneLabel.setText("-");
            this.eatLabel.setText("-");
            this.offLabel.setText("-");
            this.chiLabel.setText("-");
            this.ageLabel.setText("-");
            this.dodLabel.setText("-");
        }
        else {
            this.gnmLabel.setText(followedAnimal.getGenom().toString());
            this.cgnLabel.setText(followedAnimal.getGen(simulation.getCurrentActiveGen())+"");
            this.eneLabel.setText(followedAnimal.getEnergy()+"");
            this.eatLabel.setText(followedAnimal.getEatenGrass()+"");
            this.offLabel.setText(followedAnimal.getOffspring()+"");
            this.chiLabel.setText(followedAnimal.getChildren()+"");
            this.ageLabel.setText(followedAnimal.getAge()+"");
            if (followedAnimal.getDateOfDeath()==null)this.dodLabel.setText("-");
            else this.dodLabel.setText(followedAnimal.getDateOfDeath()+"");
        }

    }
    public void setMapTitle(String s){
        mapId.setText(s);
    }

    public void setStat(Statitics stat) {
        this.stat = stat;
    }
    private void drawStat(){
        statAnNrLabel.setText(stat.getAnimalNr()+"");
        statGrNrLabel.setText(stat.getGrassNr()+"");
        statFrNrLabel.setText(((heigth*width)-stat.getOccupiedArea())+"");
        statAvEnLabel.setText(stat.getAverageEnergy()+"");
        statAvCiLabel.setText(stat.getAverageChildren()+"");
        statAvLsLabel.setText(stat.getAverageLifeSpan()+"");
    }
}
