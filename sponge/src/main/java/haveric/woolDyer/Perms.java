package haveric.woolDyer;

import org.spongepowered.api.entity.living.player.Player;

public class Perms {
    private static final String permDye = "wooldyer.dye";

    public static boolean canDye(Player player) {
        return true; // TODO: Readd permission handling
        //return player.hasPermission(permDye);
    }

    public static String getPermDye() {
        return permDye;
    }
}
