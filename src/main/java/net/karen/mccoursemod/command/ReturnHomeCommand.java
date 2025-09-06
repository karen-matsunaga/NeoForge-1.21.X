package net.karen.mccoursemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import java.util.Optional;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ReturnHomeCommand {
    public ReturnHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("home") // Command to type on chat -> /home name
                  .then(Commands.argument("name", StringArgumentType.word()).executes(this::execute)));
    }

    // CUSTOM METHOD - When player to type this command it is returned on home
    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        String name = StringArgumentType.getString(context, "name");
        if (player != null) {
            CompoundTag homes = PlayerHomesData.get(player.level()).getHomes(player.getUUID());
            if (!homes.contains(name)) { // If there is no Set Home - Display the message in the chat (FAIL)
                context.getSource().sendFailure(componentLiteral("No home named §6§l" + name + "§r§4 found!",
                                                                 darkRed));
                return -1; // Player's position not save (FALSE)
            }
            Optional<int[]> homePos = homes.getIntArray(name);
            if (homePos.isPresent()) { // Display the message in the chat (FAIL)
                int[] pos = homePos.get();
                if (pos.length != 3) {
                    context.getSource().sendFailure(componentLiteral("Invalid home position for §6§l" + name + "§r§4!",
                                                                     darkRed));
                    return -1; // Player's position not save (FALSE)
                }
                // The Player will return to the saved [X, Y, Z] position - Player's saved position from /sethome name COMMAND
                player.teleportTo(pos[0] + 0.5, pos[1], pos[2] + 0.5);
                context.getSource().sendSuccess(() -> // Display the message in the chat (SUCCESS)
                        componentLiteral("Teleported to home! §6§l" + name, green), false);
                return 1; // Player's position save with success (TRUE)
            }
        }
        return 0; // Depends on result (1 - TRUE) or (-1 - FALSE)
    }
}