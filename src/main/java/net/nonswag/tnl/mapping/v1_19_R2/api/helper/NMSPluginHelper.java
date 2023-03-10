package net.nonswag.tnl.mapping.v1_19_R2.api.helper;

import net.nonswag.core.api.annotation.MethodsReturnNonnullByDefault;
import net.nonswag.tnl.listener.api.plugin.PluginHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;

@MethodsReturnNonnullByDefault
public class NMSPluginHelper extends PluginHelper {

    @Override
    public SimpleCommandMap getCommandMap() {
        return ((CraftServer) Bukkit.getServer()).getServer().server.getCommandMap();
    }
}
