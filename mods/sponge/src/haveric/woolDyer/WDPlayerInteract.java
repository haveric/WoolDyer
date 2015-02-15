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

                    if (holding.getItem() == ItemTypes.DYE) {
                        int dye = 15 - holding.getDamage();
                        String dyeColor = colors.values()[dye].toString();

                        Optional<BlockProperty<?>> opBlockProperty = block.getState().getPropertyByName("color");

                        if (opBlockProperty.isPresent()) {
                            BlockProperty<?> colorProperty = opBlockProperty.get();

                            BlockState newBlock = block.getState().withProperty(colorProperty, colorProperty.getValueForName(dyeColor).get());
                            block.replaceWith(newBlock);
                        }
                    }
                }
            }
        }
    }

}
