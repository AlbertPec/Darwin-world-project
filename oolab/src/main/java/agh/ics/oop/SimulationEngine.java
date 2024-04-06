package agh.ics.oop;

import agh.ics.oop.model.Boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine{
    List<Simulation> simulations;
    List<Thread> threads = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    public SimulationEngine(List<Simulation> sims){
        this.simulations=sims;
    }

    public void runSync(){
        for (Simulation sim : simulations){
            sim.run();
        }
    }
    public void runAsync(){
        for(Simulation sim : simulations){
            Thread engineThread = new Thread(sim);
            threads.add(engineThread);
            engineThread.start();
        }
    }
    public void awaitSimulationsEnd() throws InterruptedException {
        if (!threads.isEmpty()) {
            for(Thread th : threads){
                th.join();
            }
        }
        else{
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }

    }
    public void runAsyncInThreadPool(){
        for(Simulation sim : simulations){
            executorService.submit(sim);
        }
        executorService.shutdown();
    }
}
