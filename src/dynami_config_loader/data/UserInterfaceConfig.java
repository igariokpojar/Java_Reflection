package dynami_config_loader.data;

import java.util.Arrays;

public class UserInterfaceConfig {
    private String tileColor;
    private String footerText;
    private short tileFontSize;
    private short footerFontSize;

    private String titleText;

    private String[]titleFonts;

    private short [] titleTextSizes;

    public String getTileColor(){
        return tileColor;
    }
    public String getFooterText(){
        return footerText;
    }
    public short getTileFontSize(){
        return tileFontSize;
    }
    public short getFooterFontSize(){
        return footerFontSize;
    }

    public String getTitleText(){return titleText;}
    public String[] getTitleFonts(){return titleFonts;}
    public short[]getTitleTextSizes(){return titleTextSizes;}

    @Override
    public String toString() {
        return "UserInterfaceConfig{" +
                "titleText='" + titleText + '\'' +
                ", titleFonts=" + Arrays.toString(titleFonts) +
                ", titleTextSizes=" + Arrays.toString(titleTextSizes) +
                '}';
    }
}
