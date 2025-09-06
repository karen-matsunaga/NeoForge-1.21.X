package net.karen.mccoursemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class DeleteHomeCommand {
    public DeleteHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("delhome") // Command to type on chat -> /delhome name
                  .then(Commands.argument("name", StringArgumentType.word()).executes(this::execute)));
    }

    // CUSTOM METHOD - Delete home executes when type command
    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        String name = StringArgumentType.getString(context, "name"); // Home name -> Ex: /delhome tree
        if (player != null) {
            PlayerHomesData data = PlayerHomesData.get(player.level()); // Update Home list
            boolean removed = data.removeHome(player.getUUID(), name); // Remove exist Home's name from list
            if (removed) { // Home exist
                context.getSource().sendSuccess(() ->
                        componentLiteral("Deleted home! §6§l" + name, red), false);
                return 1; // Appears SUCCESS message (TRUE)
            }
            else { // Home not exist
                context.getSource().sendFailure(componentLiteral("No home named §6§l" + name + "§r§4 exists!",
                                                                 darkRed));
                return -1; // Appears FAIL message (FALSE)
            }
        }
        return 0;
    }
}