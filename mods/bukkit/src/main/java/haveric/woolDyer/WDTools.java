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
        switch(dye) {
            case WHITE_DYE:
                return Material.WHITE_WOOL;
            case ORANGE_DYE:
                return Material.ORANGE_WOOL;
            case MAGENTA_DYE:
                return Material.MAGENTA_WOOL;
            case LIGHT_BLUE_DYE:
                return Material.LIGHT_BLUE_WOOL;
            case YELLOW_DYE:
                return Material.YELLOW_WOOL;
            case LIME_DYE:
                return Material.LIME_WOOL;
            case PINK_DYE:
                return Material.PINK_WOOL;
            case GRAY_DYE:
                return Material.GRAY_WOOL;
            case LIGHT_GRAY_DYE:
                return Material.LIGHT_GRAY_WOOL;
            case CYAN_DYE:
                return Material.CYAN_WOOL;
            case PURPLE_DYE:
                return Material.PURPLE_WOOL;
            case BLUE_DYE:
                return Material.BLUE_WOOL;
            case BROWN_DYE:
                return Material.BROWN_WOOL;
            case GREEN_DYE:
                return Material.GREEN_WOOL;
            case RED_DYE:
                return Material.RED_WOOL;
            case BLACK_DYE:
                return Material.BLACK_WOOL;
            default:
                return Material.BLACK_WOOL;
        }
    }
}
