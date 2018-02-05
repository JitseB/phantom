package net.jitse.phantom.spigot.listeners.account;

import net.jitse.api.account.Account;
import net.jitse.api.events.AccountLoadedAsyncEvent;
import net.jitse.phantom.spigot.Phantom;
import net.jitse.phantom.spigot.listeners.BaseListener;
import org.bukkit.event.EventHandler;

public class AccountLoadedAsyncListener extends BaseListener {

    public AccountLoadedAsyncListener(Phantom plugin) {
        super(plugin);
    }

    @EventHandler
    public void onAccountLoadedAsync(AccountLoadedAsyncEvent event) {
        Account account = event.getAccount();

        // Todo
    }
}
