package de.simonsator.partyandfriends.clans.logtool;

import de.simonsator.partyandfriends.clan.api.events.ClanMessageEvent;
import de.simonsator.partyandfriends.clans.logtool.logger.ClansLogger;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 20.11.16
 */
public class LogToolMain extends Plugin implements Listener {
	private ClansLogger clansLogger;

	@Override
	public void onEnable() {
		try {
			clansLogger = new ClansLogger(new File(getDataFolder(), "clans.log"), this);
		} catch (IOException e) {
			System.out.println("Fatal error");
			e.printStackTrace();
		}
		getProxy().getPluginManager().registerListener(this, this);
	}

	@Override
	public void onDisable() {
		try {
			clansLogger.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void clansMessage(ClanMessageEvent pEvent) {
		clansLogger.writeln(pEvent.getSender(), pEvent.getClan(), pEvent.getMessage());
	}
}
