package net.fabricmc.dakes.cornerlink.mixin;

import net.fabricmc.dakes.cornerlink.PortalHelper;
import net.minecraft.block.Block;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin extends Block implements Portal {

    public NetherPortalBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(
        method = "getOrCreateExitPortalTarget",
        at = @At("HEAD"),
        cancellable = true
    )
    private void cornerlink$fixOrientation(
        ServerWorld world,
        Entity entity,
        BlockPos pos,
        BlockPos scaledPos,
        boolean inNether,
        WorldBorder worldBorder,
        CallbackInfoReturnable<TeleportTarget> cir
    ) {
        var corners = PortalHelper.getCornersVectorAt(entity.getEntityWorld(), pos);

        if (!corners.hasLinkingBlocks()) {
            return;
        }

        var portalRect = PortalHelper.modifiedGetPortalRect(
            world,
            scaledPos,
            inNether,
            worldBorder,
            corners
        );

        if (portalRect.isEmpty()) {
            return;
        }

        // Preserve entity state (CRITICAL)
        float yaw = entity.getYaw();
        float pitch = entity.getPitch();
        Vec3d velocity = entity.getVelocity();

        var rect = portalRect.get();
        Vec3d exitPos = rect.center();

        TeleportTarget.PostDimensionTransition transition =
            TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(e ->
                e.addPortalChunkTicketAt(rect.lowerLeft)
            );

        TeleportTarget target = new TeleportTarget(
            world,
            exitPos,
            velocity,
            yaw,
            pitch,
            transition
        );

        cir.setReturnValue(target);
    }
}
