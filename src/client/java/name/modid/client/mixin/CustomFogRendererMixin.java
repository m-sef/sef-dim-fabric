package name.modid.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

@Mixin(BackgroundRenderer.class)
public class CustomFogRendererMixin {
	@Inject(at = @At("RETURN"), method = "applyFog")
	private static void
	customFog(
			Camera  camera,
			BackgroundRenderer.FogType fogType,
			float   viewDistance,
			boolean thickFog,
			float   tickDelta,
			CallbackInfo ci)
	{
		if (fogType == BackgroundRenderer.FogType.FOG_TERRAIN
			&& !thickFog
			&& MinecraftClient.getInstance().world != null
			&& MinecraftClient.getInstance().world.getRegistryKey().getValue().getNamespace().equals("sef-dim"))
		{
			RenderSystem.setShaderFogStart(0f);
        	RenderSystem.setShaderFogEnd(30f);
		}
	}
}