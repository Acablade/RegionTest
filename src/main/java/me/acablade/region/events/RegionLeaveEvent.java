package me.acablade.region.events;

import me.acablade.region.objects.Region;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegionLeaveEvent extends PlayerMoveEvent {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Region region;

    public RegionLeaveEvent(Player player, Location from, Location to, Region region) {
        super(player, from, to);
        this.region = region;
    }


    public Region getRegion() {
        return region;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
