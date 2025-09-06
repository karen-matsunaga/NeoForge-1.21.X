package net.karen.mccoursemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import java.util.Set;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ListHomesCommand {
    public ListHomesCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("homes").executes(this::execute)); // Command to type on chat -> /homes
    }

    // CUSTOM METHOD - List homes executes when type command
    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        if (player != null) {
            CompoundTag homes = PlayerHomesData.get(player.level()).getHomes(player.getUUID());
            Set<String> homeNames = homes.keySet();
            if (homeNames.isEmpty()) { // Player hasn't set home
                context.getSource().sendFailure(componentLiteral("You have no homes set!", darkRed));
                return -1; // Appears FAIL message (FALSE)
            }
            String homesList = String.join(" ", homeNames); // Player has set homes
            context.getSource().sendSuccess(() -> componentLiteral("Your homes: §6§l" + homesList, aqua),
                                            false);
            return 1; // Appears SUCCESS message (TRUE)
        }
        return 0;
    }
}