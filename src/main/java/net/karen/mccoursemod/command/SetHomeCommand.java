package net.karen.mccoursemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class SetHomeCommand {
    public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sethome") // Command to type on chat -> /sethome name
                  .then(Commands.argument("name", StringArgumentType.word()).executes(this::execute)));
    }

    // CUSTOM METHOD - When player to type the command is done area's position
    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        String name = StringArgumentType.getString(context, "name");
        if (player != null) {
            PlayerHomesData data = PlayerHomesData.get(player.level());
            CompoundTag homes = data.getHomes(player.getUUID());
            if (homes.contains(name)) {
                String hasHome = "A home named §6§l" + name + "§r§4 already exists!";
                context.getSource().sendFailure(componentLiteral(hasHome, darkRed));
                return -1; // Player's fail position save
            }
            BlockPos pos = player.blockPosition(); // Player's [X, Y, Z] block positions
            data.setHome(player.getUUID(), name, pos); // Save data of Player's position
            // Display the message in the chat
            String message = "Set home at §e[X: §l" + pos.getX() + "§r§e, Y: §l" + pos.getY() + "§r§e, Z: §l" + pos.getZ() + "§r§e]";
            context.getSource().sendSuccess(() -> componentLiteral(message, purple), false);
            return 1; // Player's position save with success
        }
        return 0;
    }
}