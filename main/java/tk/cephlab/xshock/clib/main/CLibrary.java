package tk.cephlab.xshock.clib.main;

import java.io.File;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import tk.cephlab.xshock.clib.mod.CModMetadata;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="clib", name="xSHOCK MCC-Library", version="1.0")
public class CLibrary 
{
	@Instance("clib")
	public static CLibrary clib;
	
	public static CModMetadata mccModContainer = new CModMetadata(clib);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		try
		{
			CModLoader.load().loadCMods(new File(Minecraft.getMinecraft().mcDataDir, "cmods"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		mccModContainer.jpreInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		mccModContainer.jinit();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		mccModContainer.jpostInit();
	}
}
