package xyz.galaxyy.lualink.addons.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.galaxyy.lualink.addons.vault.tables.LuaVaultEconomy;
import xyz.galaxyy.lualink.api.LuaLinkAPI;
import xyz.galaxyy.lualink.api.addons.LuaAddon;

public class LuaLinkVaultAddon extends JavaPlugin {
    public Economy econ = null;
    private LuaAddon addon;
    @Override
    public void onEnable() {
        if (!this.setupEconomy()) {
            this.getLogger().severe("Failed while hooking into Vault! Disabling plugin..");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.addon = new LuaAddon();
        addon.addTable("economy", new LuaVaultEconomy(econ));
        LuaLinkAPI.Companion.registerAddon("Vault", addon);
    }

    // Source: https://github.com/MilkBowl/VaultAPI
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}