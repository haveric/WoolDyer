package haveric.woolDyer;

import org.bukkit.Material;

public class WDTools {
    public static boolean isDye(Material material) {
        switch (material) {
            case WHITE_DYE:
            case ORANGE_DYE:
            case MAGENTA_DYE:
            case LIGHT_BLUE_DYE:
            case YELLOW_DYE:
            case LIME_DYE:
            case PINK_DYE:
            case GRAY_DYE:
            case LIGHT_GRAY_DYE:
            case CYAN_DYE:
            case PURPLE_DYE:
            case BLUE_DYE:
            case BROWN_DYE:
            case GREEN_DYE:
            case RED_DYE:
            case BLACK_DYE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isWool(Material material) {
        switch (material) {
            case WHITE_WOOL:
            case ORANGE_WOOL:
            case MAGENTA_WOOL:
            case LIGHT_BLUE_WOOL:
            case YELLOW_WOOL:
            case LIME_WOOL:
            case PINK_WOOL:
            case GRAY_WOOL:
            case LIGHT_GRAY_WOOL:
            case CYAN_WOOL:
            case PURPLE_WOOL:
            case BLUE_WOOL:
            case BROWN_WOOL:
            case GREEN_WOOL:
            case RED_WOOL:
            case BLACK_WOOL:
                return true;
            default:
                return false;
        }
    }

    public static Material getWoolFromDye(Material dye) {
        return switch (dye) {
            case WHITE_DYE -> Material.WHITE_WOOL;
            case ORANGE_DYE -> Material.ORANGE_WOOL;
            case MAGENTA_DYE -> Material.MAGENTA_WOOL;
            case LIGHT_BLUE_DYE -> Material.LIGHT_BLUE_WOOL;
            case YELLOW_DYE -> Material.YELLOW_WOOL;
            case LIME_DYE -> Material.LIME_WOOL;
            case PINK_DYE -> Material.PINK_WOOL;
            case GRAY_DYE -> Material.GRAY_WOOL;
            case LIGHT_GRAY_DYE -> Material.LIGHT_GRAY_WOOL;
            case CYAN_DYE -> Material.CYAN_WOOL;
            case PURPLE_DYE -> Material.PURPLE_WOOL;
            case BLUE_DYE -> Material.BLUE_WOOL;
            case BROWN_DYE -> Material.BROWN_WOOL;
            case GREEN_DYE -> Material.GREEN_WOOL;
            case RED_DYE -> Material.RED_WOOL;
            default -> Material.BLACK_WOOL;
        };
    }
}
