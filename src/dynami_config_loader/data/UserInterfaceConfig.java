package dynami_config_loader.data;

public class UserInterfaceConfig {
    private String tileColor;
    private String footerText;
    private short tileFontSize;
    private short footerFontSize;

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

    @Override
    public String toString() {
        return "UserInterfaceConfig{" +
                "tileColor='" + tileColor + '\'' +
                ", footerText='" + footerText + '\'' +
                ", tileFontSize=" + tileFontSize +
                ", footerFontSize=" + footerFontSize +
                '}';
    }
}
