package dynami_config_loader.data;

import java.util.Random;

public class GameConfig {

    private final int releaseYear;
    private String gameName;
    private double price;

    public GameConfig() {
        Random random = new Random();
        this.releaseYear = random.nextInt(2000);
    }

    public int getReleaseYear(){
        return this.releaseYear;}

    public String getGameName(){
        return this.gameName;}

    public double getPrice(){
        return this.price;}

    @Override
    public String toString() {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                '}';
    }

}
