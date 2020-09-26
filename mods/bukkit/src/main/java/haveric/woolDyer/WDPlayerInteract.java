package haveric.woolDyer;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class WDPlayerInteract implements Listener {

    private WoolDyer plugin;
    public WDPlayerInteract(WoolDyer woolDyer) {
        plugin = woolDyer;
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack holding = player.getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();
        Material dye = holding.getType();
         if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK && block != null && WDTools.isWool(block.getType()) && WDTools.isDye(dye)) {
              if (Perms.canDye(player)) {
                  Material wool = block.getType();

                  Material newWool = wool;

                  if (plugin.canReplaceAll()) {
                      newWool = WDTools.getWoolFromDye(dye);
                  } else {
                      if (wool == Material.WHITE_WOOL && dye != Material.WHITE_DYE) {
                          newWool = WDTools.getWoolFromDye(dye);
                      } else if (wool == Material.ORANGE_WOOL) {
                          if (dye == Material.WHITE_DYE) {
                              newWool = Material.YELLOW_WOOL;
                          }
                      } else if (wool == Material.MAGENTA_WOOL) {
                          if (dye == Material.WHITE_DYE) {
                              newWool = Material.PINK_WOOL;
                          }
                      //} else if (wool == Material.LIGHT_BLUE_WOOL) {

                      } else if (wool == Material.YELLOW_WOOL) {
                          if (dye == Material.RED_DYE) {
                              newWool = Material.ORANGE_WOOL;
                          }
                      //} else if (wool == Material.LIGHT_GREEN_WOOL) {

                      } else if (wool == Material.PINK_WOOL) {
                          if (dye == Material.PURPLE_DYE) {
                              newWool = Material.MAGENTA_WOOL;
                          }
                      } else if (wool == Material.GRAY_WOOL) {
                          if (dye == Material.WHITE_DYE || dye == Material.LIGHT_GRAY_DYE) {
                              newWool = Material.LIGHT_GRAY_WOOL;
                          } else if (dye == Material.BLACK_DYE) {
                              newWool = Material.BLACK_WOOL;
                          }
                      } else if (wool == Material.LIGHT_GRAY_WOOL) {
                          if (dye == Material.WHITE_DYE) {
                              newWool = Material.WHITE_WOOL;
                          } else if (dye == Material.BLACK_DYE) {
                              newWool = Material.GRAY_WOOL;
                          }
                      //} else if (wool == Material.CYAN_WOOL) {

                      } else if (wool == Material.PURPLE_WOOL) {
                          if (dye == Material.PINK_DYE || dye == Material.WHITE_DYE) {
                              newWool = Material.MAGENTA_WOOL;
                          }
                      } else if (wool == Material.BLUE_WOOL) {
                          if (dye == Material.WHITE_DYE) {
                              newWool = Material.LIGHT_BLUE_WOOL;
                          } else if (dye == Material.GREEN_DYE) {
                              newWool = Material.CYAN_WOOL;
                          }
                      //} else if (wool == BROWN) {

                      } else if (wool == Material.GREEN_WOOL) {
                          if (dye == Material.WHITE_DYE) {
                              newWool = Material.LIME_WOOL;
                          }
                      } else if (wool == Material.RED_WOOL) {
                          if (dye == Material.YELLOW_DYE) {
                              newWool = Material.ORANGE_WOOL;
                          } else if (dye == Material.BLUE_DYE) {
                              newWool = Material.PURPLE_WOOL;
                          } else if (dye == Material.WHITE_DYE) {
                              newWool = Material.PINK_WOOL;
                          }
                      } else if (wool == Material.BLACK_WOOL) {
                          if (dye == Material.WHITE_DYE || dye == Material.GRAY_DYE || dye == Material.LIGHT_GRAY_DYE) {
                              newWool = Material.GRAY_WOOL;
                          }
                      }
                  }
                  if (wool != newWool) {
                      replaceBlock(player, block, newWool);
                  }
              }
         }
    }

    private void replaceBlock(Player player, Block block, Material wool) {
        BlockState state = block.getState();
        block.setType(wool);

        BlockPlaceEvent placeEvent = new BlockPlaceEvent(state.getBlock(), state, block, player.getInventory().getItemInMainHand(), player, true, EquipmentSlot.HAND);
        plugin.getServer().getPluginManager().callEvent(placeEvent);

        if (placeEvent.isCancelled()) {
            state.update(true);
        } else {
            removeFromHand(player);

            if (Supports.swingHand()) {
                player.swingMainHand();
            }
        }
    }

    // Remove one item from hand
    private void removeFromHand(Player player) {
        if (player.getGameMode() != GameMode.CREATIVE) {
            ItemStack holding = player.getInventory().getItemInMainHand();

            int amt = holding.getAmount();
            if (amt > 1) {
                holding.setAmount(--amt);
            } else {
                player.getInventory().setItemInMainHand(null);
            }
        }
    }
}
