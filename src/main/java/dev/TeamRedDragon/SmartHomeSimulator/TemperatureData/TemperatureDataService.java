package dev.TeamRedDragon.SmartHomeSimulator.TemperatureData;

import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClockService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class TemperatureDataService {

    static SimulationClockService SimulationClockService = new SimulationClockService();

    static ArrayList<TemperatureData> csvTemperatureData = new ArrayList<>();

    static ArrayList<TemperatureData> temperatureDataParsed;


    public static void generateTemperatureCSV(int year) {
        int month;
        int day;
        int hour;
        int temp;
        int daysInMonth = 0;
        int minTemp = 0;
        int maxTemp = 0;
        String output;
        String filepath = "src/main/resources/data/temperatureData" + year + ".csv";
        FileWriter fileWriter = null;

        if (year > 9999) {
            System.out.println("Invalid Year.");
            return;
        }

        try {
            File file = new File(filepath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        try {
            fileWriter = new FileWriter(filepath);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        for (int m = 1; m <= 12; m++) {
            switch (m) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    daysInMonth = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    daysInMonth = 30;
                    break;
                case 2:
                    if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
                        daysInMonth = 29;
                    else
                        daysInMonth = 28;
                    break;
                default:
                    System.out.println("Invalid month.");
                    break;
            }

            switch (m) {
                case 1:
                    maxTemp = -4;
                    minTemp = -12;
                    break;
                case 2:
                    maxTemp = -3;
                    minTemp = -11;
                    break;
                case 3:
                    maxTemp = 2;
                    minTemp = -11;
                    break;
                case 4:
                    maxTemp = 9;
                    minTemp = 1;
                    break;
                case 5:
                    maxTemp = 17;
                    minTemp = 8;
                    break;
                case 6:
                    maxTemp = 24;
                    minTemp = 15;
                    break;
                case 7:
                    maxTemp = 26;
                    minTemp = 18;
                    break;
                case 8:
                    maxTemp = 26;
                    minTemp = 17;
                    break;
                case 9:
                    maxTemp = 21;
                    minTemp = 13;
                    break;
                case 10:
                    maxTemp = 13;
                    minTemp = 6;
                    break;
                case 11:
                    maxTemp = 6;
                    minTemp = 0;
                    break;
                case 12:
                    maxTemp = -1;
                    minTemp = -8;
                    break;
                default:
                    System.out.println("Invalid month.");
                    break;
            }

            for (int d = 1; d <= daysInMonth; d++) {
                for (int h = 0; h < 24; h++) {
                    temp = ThreadLocalRandom.current().nextInt(minTemp, maxTemp + 1);
                    output = String.format("%d-%02d-%02d,%02d:00,%d\n", year, m, d, h, temp);

                    try {
                        assert fileWriter != null;
                        fileWriter.write(output);
                    } catch (IOException e) {
                        System.out.println("Error writing to file");
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error closing fileWriter.");
            e.printStackTrace();
        }
    }


    public static ArrayList<TemperatureData> getTemperatureFromCSV() {


        String filePath = "src/main/resources/data/temperatureData2024.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0];
                String time = parts[1];
                double temperature = Double.parseDouble(parts[2]);
                TemperatureData weatherData = new TemperatureData(date, time, temperature);
                csvTemperatureData.add(weatherData);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return csvTemperatureData;

    }


    public double getTemperatureFromClockAndTemperatureData() {

        // Works with hard coded

        //String clockTime = "2024-03-24 15:00";
        String clockTime = SimulationClockService.getSimulationClockTime();

        List<String> splitClockInput = Arrays.asList(clockTime.split("\\s+"));
        String clockDateSplit = splitClockInput.get(0).substring(4, 10);
        String ClockTimeSplit = splitClockInput.get(1).substring(0, 2);

        temperatureDataParsed = getTemperatureFromCSV();

        for (int tempLine = 0; tempLine < temperatureDataParsed.size(); tempLine++) {
            TemperatureData weatherData = temperatureDataParsed.get(tempLine);

            String temperatureDataDate = weatherData.getDate().substring(4, 10);
            String temperatureDataTime = weatherData.getTime().substring(0, 2);

            if (temperatureDataDate.equalsIgnoreCase(clockDateSplit) && temperatureDataTime.equalsIgnoreCase(ClockTimeSplit)) {
                return weatherData.getTemperature();
            }
        }
        return 0;
    }

}
