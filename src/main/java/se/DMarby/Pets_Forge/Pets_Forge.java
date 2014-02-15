package se.DMarby.Pets_Forge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Pets_Forge.MODID, version = Pets_Forge.VERSION)
public class Pets_Forge
{
    public static final String MODID = "Pets_Forge";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide="se.DMarby.Pets_Forge.PetsProxy")
    public static PetsProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenderInformation();
    }
}
