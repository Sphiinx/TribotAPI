package scripts.TribotAPI.game.prayer.enums;

/**
 * Created by Sphiinx on 7/6/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public enum QuickPrayer {

    ENABLED_ICON(1066),
    DISABLED_ICON(1063);

    private final int TEXTURE_ID;

    QuickPrayer(int TEXTURE_ID) {
        this.TEXTURE_ID = TEXTURE_ID;
    }

    /**
     * Gets the specified textureID.
     *
     * @return The specified textureID.
     */
    public int getTextureID() {
        return TEXTURE_ID;
    }


}
