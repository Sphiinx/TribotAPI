package scripts.tribotapi.game.worldswitcher.enums;

/**
 * Created by Sphiinx on 4/5/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public enum SwitcherWorldType {

    FREE(1130),
    MEMBER(1131),
    PVP(1237),
    DEADMAN(1238),
    TOURNAMENT(1338);

    private final int TEXTURE_ID;

    SwitcherWorldType(int texture_id) {
        this.TEXTURE_ID = texture_id;
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