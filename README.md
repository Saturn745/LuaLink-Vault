# LuaLink-Vault
Docs soon...

# Example
```lua
script.registerSimpleCommand(function(sender, args)
    if not utils.instanceOf(sender, "org.bukkit.entity.Player") then
        return
    end

    local vault = addons.get("Vault") -- Only for some addons it is required to get it when the command is executed. Some addons can be grabbed on script enable.

    sender:sendRichMessage("<green>Balance: <white>" .. vault.economy.getBalance(sender))
end, {
    name = "balexample"
})
```
