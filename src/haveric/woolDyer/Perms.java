package haveric.woolDyer;

import org.bukkit.entity.Player;

public class Perms {
	private static final String permDye = "wooldyer.dye";
	
	public static boolean canDye(Player player) {
		return player.hasPermission(permDye);
	}
}
