package haveric.woolDyer.guard;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Guard {

    private static WorldGuardPlugin worldGuard = null;
    private static Towny towny = null;

    public static void setWorldGuard(WorldGuardPlugin newWorldGuard) {
        worldGuard = newWorldGuard;
    }

    public static boolean worldGuardEnabled() {
        return (worldGuard != null);
    }

    public static void setTowny(Towny newTowny) {
        towny = newTowny;
    }

    private static boolean townyEnabled() {
        return (towny != null);
    }

    public static boolean canPlace(Player player, Location location) {
        boolean canPlace = true;

        if (worldGuardEnabled()) {
            canPlace = worldGuard.canBuild(player, location);
        }

        if (canPlace && townyEnabled()) {
            int woolTypeId = 35;
            byte woolData = (byte) (15 - player.getItemInHand().getDurability());

            canPlace = PlayerCacheUtil.getCachePermission(player, location, woolTypeId, woolData, ActionType.BUILD);
        }

        return canPlace;
    }
}