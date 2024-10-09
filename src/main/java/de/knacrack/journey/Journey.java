package de.knacrack.journey;

import de.knacrack.journey.events.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Journey extends JavaPlugin {

    private static Journey instance;

    @Override
    public void onEnable() {
        instance = this;
        new FasterMinecart();
        new StepOnFarmland();
        new SpawnerTouch();
        new ObtainingAdvancements();
        new RunOnPaths();
        new OpenIronDoorsWithHand();
        new PiglinZombification();
        new LoomMaxLayers();
        new FriendlyFirePet();
        new InvisibleItemFrame();
        new ElytraBoost();
        new BreakSuspiciousBlocks();
        //new BreakBuddingAmethyst();
        new PlayerDeath();
        new PlayerJoin();
        new PlayerQuit();
        new PlayerChat();

        new TreeChopper();
        new Veinminer();
    }

    @Override
    public void onDisable() {
        getServer().getOnlinePlayers().forEach(Player::closeInventory);
    }

    public static Journey getInstance() {
        return instance;
    }

}
