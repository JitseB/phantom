package net.jitse.phantom.spigot.utilities.logging;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;

import java.util.Collection;
import java.util.Collections;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Bukkit.class})
public class SpigotLoggerTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    private SpigotLogger logger;
    private ConsoleCommandSender console;

    @Before
    public void setUp() {
        this.logger = new SpigotLogger("foo", LogLevel.INFO);
        this.console = PowerMockito.mock(ConsoleCommandSender.class);
        PowerMockito.mockStatic(Bukkit.class);
        Player player = PowerMockito.mock(Player.class);
        PowerMockito.when(player.hasPermission(Matchers.anyString())).thenReturn(true);
        Collection<? extends Player> collection = Collections.singleton(player);
        PowerMockito.doReturn(collection).when(Bukkit.getOnlinePlayers());
    }

    @Test
    public void testValid() {
        logger.log(LogLevel.FATAL, Mockito.anyString());
        Mockito.verify(console, Mockito.times(1)).sendMessage(Mockito.anyString());
    }

    @Test
    public void testInvalid() {
        logger.log(LogLevel.DEBUG, Mockito.anyString());
        Mockito.verify(console, Mockito.never()).sendMessage(Mockito.anyString());
    }
}