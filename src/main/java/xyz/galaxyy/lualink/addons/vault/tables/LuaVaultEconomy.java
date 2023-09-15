package xyz.galaxyy.lualink.addons.vault.tables;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class LuaVaultEconomy extends LuaTable {
    private final Economy economy;
    public LuaVaultEconomy(Economy economy) {
        this.economy = economy;
        this.set("isEnabled", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return LuaValue.valueOf(economy.isEnabled());
            }
        });

        this.set("getName", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return LuaValue.valueOf(economy.getName());
            }
        });

        this.set("hasBankSupport", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return LuaValue.valueOf(economy.hasBankSupport());
            }
        });

        this.set("fractionalDigits", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return LuaValue.valueOf(economy.fractionalDigits());
            }
        });

        this.set("format", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue arg) {
                return LuaValue.valueOf(economy.format(arg.checkdouble()));
            }
        });

        this.set("currencyNamePlural", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return LuaValue.valueOf(economy.currencyNamePlural());
            }
        });

        this.set("currencyNameSingular", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return LuaValue.valueOf(economy.currencyNameSingular());
            }
        });

        this.set("hasAccount", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue offlinePlayer) {
                OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                return LuaValue.valueOf(economy.hasAccount(player));
            }
        });

        this.set("getBalance", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue offlinePlayer) {
                OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                return LuaValue.valueOf(economy.getBalance(player));
            }
        });

        this.set("has", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue offlinePlayer, LuaValue amount) {
                OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                return LuaValue.valueOf(economy.has(player, amount.checkdouble()));
            }
        });

        this.set("withdrawPlayer", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue offlinePlayer, LuaValue amount) {
                OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                return LuaValue.valueOf(economy.withdrawPlayer(player, amount.checkdouble()).transactionSuccess());
            }
        });

        this.set("depositPlayer", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue offlinePlayer, LuaValue amount) {
                OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                return LuaValue.valueOf(economy.depositPlayer(player, amount.checkdouble()).transactionSuccess());
            }
        });

        this.set("createBank", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue name, LuaValue offlinePlayer) {
                if (economy.hasBankSupport()) {
                    OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                    return LuaValue.valueOf(economy.createBank(name.checkjstring(), player).transactionSuccess());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("deleteBank", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue name) {
                if (economy.hasBankSupport()) {
                    return LuaValue.valueOf(economy.deleteBank(name.checkjstring()).transactionSuccess());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("bankBalance", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue name) {
                if (economy.hasBankSupport()) {
                    return LuaValue.valueOf(economy.bankBalance(name.checkjstring()).transactionSuccess());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("bankHas", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue name, LuaValue amount) {
                if (economy.hasBankSupport()) {
                    return LuaValue.valueOf(economy.bankHas(name.checkjstring(), amount.checkdouble()).transactionSuccess());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("bankWithdraw", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue name, LuaValue amount) {
                if (economy.hasBankSupport()) {
                    return LuaValue.valueOf(economy.bankWithdraw(name.checkjstring(), amount.checkdouble()).transactionSuccess());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("bankDeposit", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue name, LuaValue amount) {
                if (economy.hasBankSupport()) {
                    return LuaValue.valueOf(economy.bankDeposit(name.checkjstring(), amount.checkdouble()).transactionSuccess());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("getBanks", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                if (economy.hasBankSupport()) {
                    return CoerceJavaToLua.coerce(economy.getBanks());
                } else {
                    return LuaValue.valueOf(false);
                }
            }
        });

        this.set("createPlayerAccount", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue offlinePlayer) {
                OfflinePlayer player = (OfflinePlayer) offlinePlayer.checkuserdata(OfflinePlayer.class);
                return LuaValue.valueOf(economy.createPlayerAccount(player));
            }
        });
    }
}
