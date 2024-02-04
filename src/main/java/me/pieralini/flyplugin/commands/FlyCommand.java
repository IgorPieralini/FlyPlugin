package me.pieralini.flyplugin.commands;

import me.pieralini.flyplugin.FlyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private final ArrayList<Player> list_of_flying_players = new ArrayList<>();
    private final FlyPlugin plugin;

    public FlyCommand(FlyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){

            Player player  = (Player) sender;

            if(args.length == 0){

                flyMethod(player);

            }else if(args.length == 1){

                if (player.hasPermission("flyplugin.admin")){

                    Player target = Bukkit.getPlayer(args[0]);
                    flyMethod(target);

                }else {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para isso");
                }
            }
        }
        return true;
    }

    private void flyMethod(Player player){
        if (player.hasPermission("flyplugin.fly")){

            if (list_of_flying_players.contains(player)){

                list_of_flying_players.remove(player);

                player.setAllowFlight(false);
                player.sendMessage(plugin.getConfig().getString("off-message", plugin.getConfig().getString("off-message")) );
            }else if(!list_of_flying_players.contains(player)){

                list_of_flying_players.add(player);

                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("on-message")));
            }
        }else {
            player.sendMessage(ChatColor.RED + "Você não tem permissão");
        }
    }
}
