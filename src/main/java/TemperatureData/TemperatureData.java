package TemperatureData;

public class TemperatureData {

        private String date;
        private String time;
        private double temperature;

        public TemperatureData(String date, String time, double temperature) {
            this.date = date;
            this.time = time;
            this.temperature = temperature;
        }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "TemperatureData {" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
