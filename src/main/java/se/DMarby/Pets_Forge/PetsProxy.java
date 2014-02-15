package se.DMarby.Pets_Forge;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityBat;

public class PetsProxy {

    @SideOnly(Side.CLIENT)
    public void registerRenderInformation() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBat.class, new PetsBatRenderer());
    }
}
