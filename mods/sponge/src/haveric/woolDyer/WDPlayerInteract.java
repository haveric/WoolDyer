package haveric.woolDyer;

import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.block.BlockProperty;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.EntityInteractionType;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.entity.living.player.PlayerInteractBlockEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.event.Subscribe;

import com.google.common.base.Optional;

public class WDPlayerInteract {

    private WoolDyer plugin;
    private enum colors { white, orange, magenta, light_blue, yellow, lime, pink, gray, silver, cyan, purple, blue, brown, green, red, black }

    private static final String WHITE = "white";
    private static final String ORANGE = "orange";
    private static final String MAGENTA = "magenta";
    private static final String LIGHT_BLUE = "light_blue";
    private static final String YELLOW = "yellow";
    private static final String LIGHT_GREEN = "lime";
    private static final String PINK = "pink";
    private static final String GRAY = "gray";
    private static final String LIGHT_GRAY = "silver";
    private static final String CYAN = "cyan";
    private static final String PURPLE = "purple";
    private static final String BLUE = "blue";
    private static final String GREEN = "green";
    private static final String RED = "red";
    private static final String BLACK = "black";

    public WDPlayerInteract(WoolDyer woolDyer) {
        plugin = woolDyer;
    }

    @Subscribe
    public void onPlayerInteract(PlayerInteractBlockEvent event) {
        if (event.getInteractionType() == EntityInteractionType.RIGHT_CLICK) {
            BlockLoc block = event.getBlock();

            if (block.getType() == BlockTypes.WOOL) {
                Player player = event.getPlayer();
                Optional<ItemStack> optionalHolding = player.getItemInHand();

                if (optionalHolding.isPresent()) {
                    ItemStack holding = optionalHolding.get();

                    if (holding.getItem() == ItemTypes.DYE && Perms.canDye(player)) {
                        int dye = 15 - holding.getDamage();
                        String dyeColor = colors.values()[dye].toString();

                        Optional<? extends Comparable<?>> opBlockColor = block.getState().getPropertyValue("color");
                        if (opBlockColor.isPresent()) {
                            String blockColor = opBlockColor.get().toString();
                            String newColor = null;

                            if (Config.canReplaceAll()) {
                                newColor = dyeColor;
                            } else {
                                if (blockColor.equals(WHITE) && !dyeColor.equals(WHITE)) {
                                    newColor = dyeColor;
                                } else if (blockColor.equals(ORANGE)) {
                                    if (dyeColor.equals(WHITE)) {
                                        newColor = colors.yellow.toString();
                                    }
                                } else if (blockColor.equals(MAGENTA)) {
                                    if (dyeColor.equals(WHITE)) {
                                        newColor = PINK;
                                    }
                                } else if (blockColor.equals(YELLOW)) {
                                    if (dyeColor.equals(RED)) {
                                        newColor = ORANGE;
                                    }
                                } else if (blockColor.equals(PINK)) {
                                    if (dyeColor.equals(PURPLE)) {
                                        newColor = MAGENTA;
                                    }
                                } else if (blockColor.equals(GRAY)) {
                                    if (dyeColor.equals(WHITE) || dyeColor.equals(LIGHT_GRAY)) {
                                        newColor = LIGHT_GRAY;
                                    } else if (dyeColor.equals(BLACK)) {
                                        newColor = BLACK;
                                    }
                                } else if (dyeColor.equals(LIGHT_GRAY)) {
                                    if (dyeColor.equals(WHITE)) {
                                        newColor = WHITE;
                                    } else if (dyeColor.equals(BLACK)) {
                                        newColor = GRAY;
                                    }
                                } else if (blockColor.equals(PURPLE)) {
                                    if (dyeColor.equals(PINK) || dyeColor.equals(WHITE)) {
                                        newColor = MAGENTA;
                                    }
                                } else if (blockColor.equals(BLUE)) {
                                    if (dyeColor.equals(WHITE)) {
                                        newColor = LIGHT_BLUE;
                                    } else if (dyeColor.equals(GREEN)) {
                                        newColor = CYAN;
                                    }
                                } else if (blockColor.equals(GREEN)) {
                                    if (dyeColor.equals(WHITE)) {
                                        newColor = LIGHT_GREEN;
                                    }
                                } else if (blockColor.equals(RED)) {
                                    if (dyeColor.equals(YELLOW) || dyeColor.equals(WHITE)) {
                                        newColor = ORANGE;
                                    } else if (dyeColor.equals(BLUE)) {
                                        newColor = PURPLE;
                                    } else if (dyeColor.equals(WHITE)) {
                                        newColor = PINK;
                                    }
                                } else if (blockColor.equals(BLACK)) {
                                    if (dyeColor.equals(WHITE) || dyeColor.equals(GRAY) || dyeColor.equals(LIGHT_GRAY)) {
                                        newColor = GRAY;
                                    }
                                }
                            }

                            if (!blockColor.equals(newColor)) {
                                if (!(blockColor.equals("lightBlue") && newColor.equals(LIGHT_BLUE))) {
                                    Optional<BlockProperty<?>> opBlockProperty = block.getState().getPropertyByName("color");

                                    if (opBlockProperty.isPresent()) {
                                        BlockProperty<?> colorProperty = opBlockProperty.get();
                                        BlockState newBlock = block.getState().withProperty(colorProperty, colorProperty.getValueForName(newColor).get());

                                        replaceBlock(player, block, newBlock, holding);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void replaceBlock(Player player, BlockLoc block, BlockState newBlock, ItemStack holding) {

        // TODO: Call a place event and check for cancellation
        block.replaceWith(newBlock);

        removeFromHand(player, holding);
    }

    private void removeFromHand(Player player, ItemStack holding) {
        //if (player.getGameMode() != GameModes.CREATIVE) {
            int amount = holding.getQuantity();

            if (amount > 1) {
                holding.setQuantity(amount - 1);
            } else {
                player.setItemInHand(null);
            }
        //}
    }
}
