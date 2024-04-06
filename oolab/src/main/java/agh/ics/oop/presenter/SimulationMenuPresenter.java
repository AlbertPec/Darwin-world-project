package agh.ics.oop.presenter;

import agh.ics.oop.Statitics;
import agh.ics.oop.model.*;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.Globe;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SimulationMenuPresenter {
    SimulationEngine engine = new SimulationEngine(new ArrayList<>());
    @FXML
    Spinner animal_nr;
    @FXML
    Spinner grass_nr;
    @FXML
    Spinner growth_nr;
    @FXML
    Spinner nutrition;
    @FXML
    Spinner fullEnergy;
    @FXML
    Spinner breedEnergy;
    @FXML
    Spinner width;
    @FXML
    Spinner heigth;
    @FXML
    Spinner minMut;
    @FXML
    Spinner maxMut;
    @FXML
    Spinner genomSize;
    @FXML
    Spinner startEnergy;
    @FXML
    RadioButton allRandom;
    @FXML
    RadioButton littleCorrection;
    @FXML
    RadioButton forestedEquator;
    @FXML
    RadioButton normalForestedEquator;
    @FXML
    RadioButton creapingJungle;
    @FXML
    Label Ex;


    @FXML
    void onStartSim(){
        Ex.setVisible(false);
        int grassConfig = 0;
        Mutation mutationConfig = null;
        try {
            grassConfig = getGrassConfig();
            mutationConfig = getMutationConfig();
        } catch (NotAllSelectedException e) {
            Ex.setVisible(true);
            return;
        }

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = null;
        try {
            viewRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SimulationPresenter presenter = loader.getController();

        WorldMap map = createMap(grassConfig,mutationConfig);
        Simulation sim = createSim(map);
        Statitics stat = new Statitics();

        sim.registerSubscriber(presenter);
        sim.registerSubscriber(stat);
        presenter.setSimulation(sim);
        presenter.setBorder((int) heigth.getValue(), (int) width.getValue());
        presenter.setFullEnergy((int) fullEnergy.getValue());
        presenter.setMapTitle(map.getId().toString());
        presenter.setStat(stat);

        configureStage(stage,viewRoot);
        stage.show();
    }
    private Mutation getMutationConfig() throws NotAllSelectedException{
        Mutation newMutation = null;
        if (littleCorrection.isSelected()) newMutation= new LittleCorection();
        if (allRandom.isSelected()) newMutation= new AllRandom();
        if (newMutation == null)throw new NotAllSelectedException();
        newMutation.setMutationBoundries((int) minMut.getValue(),(int) maxMut.getValue());
        return  newMutation;
    }

    private int getGrassConfig() throws NotAllSelectedException{
        if (forestedEquator.isSelected()) return 2;
        if (normalForestedEquator.isSelected()) return 0;
        if (creapingJungle.isSelected()) return 1;
        throw new NotAllSelectedException();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Symulacja");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
    private WorldMap createMap(int G, Mutation M){
        WorldMap map = new Globe(
                (int) width.getValue(),
                (int) heigth.getValue(),
                (int) growth_nr.getValue(),
                (int) grass_nr.getValue(),
                (int) breedEnergy.getValue(),
                G,
                M);
        return map;
    }

    private Simulation createSim(WorldMap map){
        Simulation newSim = new Simulation(
                map,
                (int) animal_nr.getValue(),
                (int) nutrition.getValue(),
                (int) fullEnergy.getValue(),
                (int) genomSize.getValue(),
                (int) startEnergy.getValue());

        return newSim;
    }
}
