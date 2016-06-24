package scripts.TribotAPI.game.utiity.enums;

/**
 * Created by Sphiinx on 4/5/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public enum WorldType {

    FREE(1130),
    MEMBER(1131),
    PVP(1237),
    DEADMAN(1238),
    TOURNAMENT(1338);

    private final int TEXTURE_ID;

    WorldType(int TEXTURE_ID) {
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