package haveric.woolDyer;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class WDPlayerInteract implements Listener{

	private static final int WHITE = 0;
	private static final int ORANGE = 1;
	private static final int MAGENTA = 2;
	private static final int LIGHT_BLUE = 3;
	private static final int YELLOW = 4;
	private static final int LIGHT_GREEN = 5;
	private static final int PINK = 6;
	private static final int GRAY = 7;
	private static final int LIGHT_GRAY = 8;
	private static final int CYAN = 9;
	private static final int PURPLE = 10;
	private static final int BLUE = 11;
	private static final int BROWN = 12;
	private static final int GREEN = 13;
	private static final int RED = 14;
	private static final int BLACK = 15;
	
	
	WoolDyer plugin;
	public WDPlayerInteract(WoolDyer woolDyer) {
		plugin = woolDyer;
	}

	@EventHandler (priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		
        Player player = event.getPlayer();

        PlayerInventory inventory = player.getInventory();
        
        ItemStack holding = player.getItemInHand();
		 if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.WOOL && holding.getType() == Material.INK_SACK){
			 Permission perm = plugin.getPerm();
		 	 if (perm == null || perm.has(player,  "wooldyer.dye")) {
				 Block block = event.getClickedBlock();
				 int wool = block.getData();
				 int dye = 15 - holding.getDurability();
	
				 int newData = wool;
				 if (plugin.canReplaceAll()){
					 newData = dye;
				 } else {
					 if (wool == WHITE && dye != WHITE){
						 newData = dye;
					 } else if (wool == ORANGE) {
						 if (dye == WHITE){
							 newData = YELLOW;
						 }
					 } else if (wool == MAGENTA) {
						 if (dye == WHITE){
							 newData = PINK;
						 }
					 } else if (wool == LIGHT_BLUE) {
	
					 } else if (wool == YELLOW) {
						 if (dye == RED){ 
							 newData = ORANGE;
						 }
					 } else if (wool == LIGHT_GREEN) {
	
					 } else if (wool == PINK) {
						 if (dye == PURPLE){
							 newData = MAGENTA;
						 }
					 } else if (wool == GRAY) {
						 if (dye == WHITE || dye == LIGHT_GRAY){
							 newData = LIGHT_GRAY; 
						 } else if (dye == BLACK){
							 newData = BLACK;
						 }
					 } else if (wool == LIGHT_GRAY) { 
						 if (dye == WHITE){
							 newData = WHITE; 
						 } else if (dye == BLACK){
							 newData = GRAY;
						 }
					 } else if (wool == CYAN) { 
						 
					 } else if (wool == PURPLE) {
						 if (dye == PINK || dye == WHITE){
							 newData = MAGENTA; 
						 }
					 } else if (wool == BLUE) {
						 if (dye == WHITE){
							 newData = LIGHT_BLUE;
						 } else if (dye == GREEN){
							 newData = CYAN; 
						 }
					 } else if (wool == BROWN) {
						 
					 } else if (wool == GREEN) {
						 if (dye == WHITE){
							 newData = LIGHT_GREEN;
						 }
					 } else if (wool == RED) {
						 if (dye == YELLOW || dye == WHITE){
							 newData = ORANGE;
						 } else if (dye == BLUE){
							 newData = PURPLE;
						 } else if (dye == WHITE){
							 newData = PINK;
						 }
					 } else if (wool == BLACK) {
						 if (dye == WHITE || dye == GRAY || dye == LIGHT_GRAY){
							 newData = GRAY;
						 }
					 }
					 
				 }		
				 if (wool != newData){
					 if (player.getGameMode() == GameMode.SURVIVAL){
						 int amt = holding.getAmount();
		                 if (amt > 1){
		                     holding.setAmount(--amt);
		                 } else {
		                	 inventory.setItemInHand(null);
		                 }
					 }
	                 
	            	 block.setData((byte)newData);
				 }
		 	 }
		 }
	}
}
