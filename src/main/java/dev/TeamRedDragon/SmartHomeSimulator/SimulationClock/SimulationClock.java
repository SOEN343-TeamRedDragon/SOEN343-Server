package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observable;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SimulationClock implements Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    private static double timeSpeed = 1.0;

    private static SimulationClock simulationClock;

    private static String time;

    private SimulationClock() {}

    public static SimulationClock getSimulationClock() {
        if (simulationClock == null) {
            simulationClock = new SimulationClock();
        }
        return simulationClock;
    }

    public void setTimeSpeed(double timeSpeed) {
        if (timeSpeed == 0)
            this.timeSpeed = 0.000001;
        else
            this.timeSpeed = timeSpeed;
    }

    public String getTime() {return this.time;}

    public Runnable clockRunnable = new Runnable() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        int year = currentDateTime.getYear();
        int month = currentDateTime.getMonthValue();
        int day = currentDateTime.getDayOfMonth();
        int hour = currentDateTime.getHour();
        int min = currentDateTime.getMinute();

        public String tick() {
            int maxMonth = 0;

            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    maxMonth = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    maxMonth = 30;
                    break;
                case 2:
                    if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
                        maxMonth = 29;
                    else
                        maxMonth = 28;
                    break;
                default:
                    System.out.println("Invalid month.");
                    break;

            }

            if (min == 59) {
                min = 0;
                if (hour == 23) {
                    hour = 0;
                    if (day == maxMonth) {
                        day = 1;
                        if (month == 12) {
                            month = 1;
                            year += year;
                        } else
                            month += 1;
                    } else
                        day += 1;
                } else {
                    hour += 1;
                    notifyObservers();
                }
            } else
                min += 1;

            String output = String.format("%d-%02d-%02d %02d:%02d", year, month, day, hour, min);
            return output;
        }

        @Override
        public void run() {
            while (true) {
                time = tick();

                try {
                    Thread.sleep((long) (60000 / timeSpeed));
                } catch (InterruptedException e) {
                    continue;
                }
            }
        }
    };

    @Override
    public void subscribe(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.observers.remove(observer);

    }

    public void notifyObservers() {
        for (Observer ob : observers)
        {
            ob.update();
        }
    }
}
