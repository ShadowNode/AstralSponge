package ca.shadownode.astralsponge.mixins;

import hellfirepvp.astralsorcery.common.event.BlockModifyEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.world.BlockChangeFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Chunk.class, priority = 4000, remap = false)
public class MixinChunk {

    @Inject(method = "setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/block/state/IBlockState;Lorg/spongepowered/api/block/BlockSnapshot;Lorg/spongepowered/api/world/BlockChangeFlag;)Lnet/minecraft/block/state/IBlockState;", at = @At("TAIL"))
    public void callEvent(BlockPos pos, IBlockState newState, IBlockState currentState, BlockSnapshot newBlockSnapshot, BlockChangeFlag flag, CallbackInfoReturnable cir) {
        BlockModifyEvent event = new BlockModifyEvent((Chunk) (Object) this, pos, currentState, newState);
        MinecraftForge.EVENT_BUS.post(event);     
    }
}
