package bean;

import java.io.Serializable;
public class WeatherBean implements Serializable{
    private String weather;
    private String image_path;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_path() {
        return image_path;
    }
}