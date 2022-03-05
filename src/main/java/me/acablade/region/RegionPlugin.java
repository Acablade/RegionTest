package me.acablade.region;

import me.acablade.region.events.RegionEnterEvent;
import me.acablade.region.objects.Region;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class RegionPlugin extends JavaPlugin {

    private Location location1;
    private Location location2;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("alan").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

                if(!(sender instanceof Player)) return false;

                Player player = (Player) sender;

                if("location1".equalsIgnoreCase(args[0])){
                    location1 = player.getLocation();
                }else if("location2".equalsIgnoreCase(args[0])){
                    location2 = player.getLocation();
                }else if("create".equalsIgnoreCase(args[0])){
                    Location min = new Location(
                            location1.getWorld(),
                            Math.min(location1.getBlockX(),location2.getBlockX()),
                            Math.min(location1.getBlockY(),location2.getBlockY()),
                            Math.min(location1.getBlockZ(),location2.getBlockZ())
                    );
                    Location max = new Location(
                            location1.getWorld(),
                            Math.max(location1.getBlockX(),location2.getBlockX()),
                            Math.max(location1.getBlockY(),location2.getBlockY()),
                            Math.max(location1.getBlockZ(),location2.getBlockZ())
                    );

                    Region region = new Region(min,max, RegionPlugin.getProvidingPlugin(RegionPlugin.class));
                    region.addListener(RegionEnterEvent.class, (event) -> {
                        Player eventPlayer = event.getPlayer();
                        //if(!region.contains(eventPlayer)) return;
                        Location to = event.getTo();
                        Location from = event.getFrom();
                        Vector dir = to.toVector().subtract(from.toVector());
                        eventPlayer.setVelocity(dir.multiply(-3));
                        //event.setCancelled(true);
                    });
                }


                return false;
            }
        });


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
