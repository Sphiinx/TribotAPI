package scripts.tribotapi.game.clanchat.enums;

/**
 * Created by Sphiinx on 7/31/2016.
 */
public enum ClanRank {

    NONE(-1),
    FRIEND(1004),
    RECRUIT(1012),
    CORPORAL(1011),
    SERGEANT(1010),
    LIEUTENANT(1009),
    CAPTAIN(1008),
    GENERAL(1007),
    OWNER(1006);

    private final int TEXTURE_ID;

    ClanRank(int texture_id) {
        this.TEXTURE_ID = texture_id;
    }

    public int getTextureID() {
        return TEXTURE_ID;
    }

    public static ClanRank getRank(int texture_id) {
        switch (texture_id) {
            case -1:
                return NONE;
            case 1004:
                return FRIEND;
            case 1012:
                return RECRUIT;
            case 1011:
                return CORPORAL;
            case 1010:
                return SERGEANT;
            case 1009:
                return LIEUTENANT;
            case 1008:
                return CAPTAIN;
            case 1007:
                return GENERAL;
            case 1006:
                return OWNER;
        }

        return null;
    }

}
