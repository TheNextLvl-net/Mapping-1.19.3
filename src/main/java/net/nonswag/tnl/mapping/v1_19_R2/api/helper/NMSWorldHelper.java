package net.nonswag.tnl.mapping.v1_19_R2.api.helper;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.nonswag.core.api.annotation.MethodsReturnNonnullByDefault;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import net.nonswag.tnl.listener.api.world.Dimension;
import net.nonswag.tnl.listener.api.world.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class NMSWorldHelper extends WorldHelper {

    @Override
    public Dimension getDimension(World world) {
        ResourceKey<Level> key = ((CraftWorld) world).getHandle().dimension();
        if (key.equals(Level.OVERWORLD)) return Dimension.OVERWORLD;
        else if (key.equals(Level.NETHER)) return Dimension.NETHER;
        else if (key.equals(Level.END)) return Dimension.THE_END;
        else return Dimension.CUSTOM;
    }

    @Override
    public boolean isRegistered(Dimension dimension) {
        return ((CraftServer) Bukkit.getServer()).getServer().getLevel(switch (dimension) {
            case NETHER -> Level.NETHER;
            case THE_END -> Level.END;
            default -> Level.OVERWORLD;
        }) != null;
    }

    @Override
    public List<TNLPlayer> getPlayers(World world) {
        List<TNLPlayer> players = new ArrayList<>();
        ServerLevel handle = ((CraftWorld) world).getHandle();
        handle.players().forEach(serverPlayer -> players.add(TNLPlayer.cast(serverPlayer.getBukkitEntity())));
        return players;
    }

    @Override
    public void removePlayer(World world, TNLPlayer player) {
        ((CraftWorld) world).getHandle().players().remove(((CraftPlayer) player.bukkit()).getHandle());
    }
}
