package dynami_config_loader.data;

import java.util.Arrays;
import java.util.Random;

public class GameConfig {

    private final int releaseYear;
    private String gameName;
    private double price;

    private String [] characterNames;

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

    public String[] getCharacterNames(){return characterNames;}

    @Override
    public String toString() {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                ", characterNames=" + Arrays.toString(characterNames) +
                '}';
    }
}
