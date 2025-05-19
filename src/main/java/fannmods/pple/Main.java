package fannmods.pple;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Config.load();
        CommandRegistrationCallback.EVENT.register((dispatcher, bool) -> dispatcher.register(
                CommandManager.literal("pistonpushlimit")
                        .then(CommandManager.argument("value", IntegerArgumentType.integer(0, Config.MaxRange))
                                .executes(ctx -> {
                                    int value = IntegerArgumentType.getInteger(ctx, "value");
                                    Config.PushLimit = value;
                                    Config.save();
                                    ctx.getSource().sendFeedback(Text.of("Push limit set to " + value), false);
                                    return 1;
                                }))
                        .then(CommandManager.literal("reset")
                                .executes(ctx -> {
                                    Config.PushLimit = 12;
                                    Config.save();
                                    ctx.getSource().sendFeedback(Text.of("Piston push limit reset to 12"), true);
                                    return 1;
                                }))
        ));
        CommandManager.literal("pistonpushlimit")
                .requires(source -> source.hasPermissionLevel(2));
    }
}
