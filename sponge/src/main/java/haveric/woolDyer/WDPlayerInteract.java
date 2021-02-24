package haveric.woolDyer;

import java.util.Optional;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.block.trait.EnumTraits;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColor;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class WDPlayerInteract {

    private WoolDyer plugin;

    public WDPlayerInteract(WoolDyer woolDyer) {
        plugin = woolDyer;
    }

    @Listener
    public void onPlayerInteractSecondary(InteractBlockEvent.Secondary event) {
        BlockSnapshot block = event.getTargetBlock();
        BlockState state = block.getState();

        if (state.getType() == BlockTypes.WOOL) {
            Optional<Player> opPlayer = event.getCause().first(Player.class);

            if (opPlayer.isPresent()) {
                Player player = opPlayer.get();
                Optional<ItemStack> opHolding = player.getItemInHand();

                if (opHolding.isPresent()) {
                    ItemStack holding = opHolding.get();

                    if (holding.getItem() == ItemTypes.DYE && Perms.canDye(player)) {
                        Optional<BlockTrait<?>> opBlockTraitColor = state.getTrait(EnumTraits.WOOL_COLOR.getName());

                        if (opBlockTraitColor.isPresent()) {
                            BlockTrait<?> blockTraitColor = opBlockTraitColor.get();

                            String traitValue = state.getTraitValues().toArray()[0].toString();
                            DyeColor blockColor = getDyeColor(traitValue);
                            // TODO: Replace with holding.get(Keys.DYE_COLOR) when api supports it
                            DyeColor dyeColor = getDyeColor(getRawData(holding.toString()));
                            DyeColor newColor;

                            if (Config.canReplaceAll()) {
                                newColor = dyeColor;
                            } else {
                                newColor = addColor(blockColor, dyeColor);
                            }

                            if (!blockColor.equals(newColor)) {
                                Optional<Location<World>> opLocation = block.getLocation();
                                if (opLocation.isPresent()) {
                                    Location<World> location = opLocation.get();

                                    Optional<BlockState> opNewState = state.withTrait(blockTraitColor, newColor);
                                    if (opNewState.isPresent()) {
                                        replaceBlock(player, location, opNewState.get(), holding);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Temporary method for getting data value from the Dye name
    private String getRawData(String string) {
        return string.substring(string.lastIndexOf("@") + 1);
    }

    // Can accept data values (0-15) or color names
    private DyeColor getDyeColor(String string) {
        DyeColor color;

        switch (string) {
            case "15":
            case "white":
                color = DyeColors.WHITE;
                break;
            case "14":
            case "orange":
                color = DyeColors.ORANGE;
                break;
            case "13":
            case "magenta":
                color = DyeColors.MAGENTA;
                break;
            case "12":
            case "lightBlue":
                color = DyeColors.LIGHT_BLUE;
                break;
            case "11":
            case "yellow":
                color = DyeColors.YELLOW;
                break;
            case "10":
            case "lime":
                color = DyeColors.LIME;
                break;
            case "9":
            case "pink":
                color = DyeColors.PINK;
                break;
            case "8":
            case "gray":
                color = DyeColors.GRAY;
                break;
            case "7":
            case "silver":
                color = DyeColors.SILVER;
                break;
            case "6":
            case "cyan":
                color = DyeColors.CYAN;
                break;
            case "5":
            case "purple":
                color = DyeColors.PURPLE;
                break;
            case "4":
            case "blue":
                color = DyeColors.BLUE;
                break;
            case "3":
            case "brown":
                color = DyeColors.BROWN;
                break;
            case "2":
            case "green":
                color = DyeColors.GREEN;
                break;
            case "1":
            case "red":
                color = DyeColors.RED;
                break;
            case "0":
            case "black":
            default:
                color = DyeColors.BLACK;
                break;
        }

        return color;
    }

    private DyeColor addColor(DyeColor blockColor, DyeColor dyeColor) {
        DyeColor newColor = null;

        if (blockColor.equals(DyeColors.WHITE) && !dyeColor.equals(DyeColors.WHITE)) {
            newColor = dyeColor;
        } else if (blockColor.equals(DyeColors.ORANGE)) {
            if (dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.YELLOW;
            }
        } else if (blockColor.equals(DyeColors.MAGENTA)) {
            if (dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.PINK;
            }
        } else if (blockColor.equals(DyeColors.YELLOW)) {
            if (dyeColor.equals(DyeColors.RED)) {
                newColor = DyeColors.ORANGE;
            }
        } else if (blockColor.equals(DyeColors.PINK)) {
            if (dyeColor.equals(DyeColors.PURPLE)) {
                newColor = DyeColors.MAGENTA;
            }
        } else if (blockColor.equals(DyeColors.GRAY)) {
            if (dyeColor.equals(DyeColors.WHITE) || dyeColor.equals(DyeColors.SILVER)) {
                newColor = DyeColors.SILVER;
            } else if (dyeColor.equals(DyeColors.BLACK)) {
                newColor = DyeColors.BLACK;
            }
        } else if (dyeColor.equals(DyeColors.SILVER)) {
            if (dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.WHITE;
            } else if (dyeColor.equals(DyeColors.BLACK)) {
                newColor = DyeColors.GRAY;
            }
        } else if (blockColor.equals(DyeColors.PURPLE)) {
            if (dyeColor.equals(DyeColors.PINK) || dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.MAGENTA;
            }
        } else if (blockColor.equals(DyeColors.BLUE)) {
            if (dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.LIGHT_BLUE;
            } else if (dyeColor.equals(DyeColors.GREEN)) {
                newColor = DyeColors.CYAN;
            }
        } else if (blockColor.equals(DyeColors.GREEN)) {
            if (dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.LIME;
            }
        } else if (blockColor.equals(DyeColors.RED)) {
            if (dyeColor.equals(DyeColors.YELLOW) || dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.ORANGE;
            } else if (dyeColor.equals(DyeColors.BLUE)) {
                newColor = DyeColors.PURPLE;
            } else if (dyeColor.equals(DyeColors.WHITE)) {
                newColor = DyeColors.PINK;
            }
        } else if (blockColor.equals(DyeColors.BLACK)) {
            if (dyeColor.equals(DyeColors.WHITE) || dyeColor.equals(DyeColors.GRAY) || dyeColor.equals(DyeColors.SILVER)) {
                newColor = DyeColors.GRAY;
            }
        }

        if (newColor == null) {
            newColor = blockColor;
        }

        return newColor;
    }

    private void replaceBlock(Player player, Location<World> block, BlockState newBlock, ItemStack holding) {

        // TODO: Call a place event and check for cancellation
        block.setBlock(newBlock);

        removeFromHand(player, holding);
    }

    private void removeFromHand(Player player, ItemStack holding) {
        plugin.getLog().info("Game Mode: " + player.get(Keys.GAME_MODE).get());
        if (player.get(Keys.GAME_MODE).get() != GameModes.CREATIVE) {
            int amount = holding.getQuantity();

            if (amount > 1) {
                holding.setQuantity(amount - 1);
                player.setItemInHand(holding);
            } else {
                player.setItemInHand(null);
            }
        }
    }
}
