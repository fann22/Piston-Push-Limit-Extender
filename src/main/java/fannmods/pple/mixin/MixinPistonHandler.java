package fannmods.pple.mixin;

import fannmods.pple.Config;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PistonHandler.class)
public class MixinPistonHandler {
    @ModifyConstant(method = "tryMove", constant = @Constant(intValue = 12))
    private int modifyPistonLimit(int original) {
        return Config.PushLimit;
    }
}
