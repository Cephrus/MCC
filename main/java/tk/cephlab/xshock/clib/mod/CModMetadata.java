package tk.cephlab.xshock.clib.mod;

import java.io.File;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scala.actors.threadpool.Arrays;
import tk.cephlab.xshock.clib.main.CLibrary;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModContainerFactory;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionRange;

public class CModMetadata implements ModContainer
{	
	public CModMetadata(CLibrary clib)
	{
		//TODO: Add useful code here.
	}
	
	static
	{
		System.loadLibrary("libcmod");
	}
	
	@Override
	public String getModId()
	{
		return "CMOD";
	}

	@Override
	public String getName() 
	{
		return "ctest";
	}

	@Override
	public String getVersion()
	{
		return "1";
	}

	@Override
	public File getSource() 
	{
		return null;
	}

	@Override
	public ModMetadata getMetadata() 
	{
		return null;
	}

	@Override
	public void bindMetadata(MetadataCollection mc)
	{
		
	}

	@Override
	public void setEnabledState(boolean enabled)
	{
		
	}

	@Override
	public Set<ArtifactVersion> getRequirements() 
	{
		return null;
	}

	@Override
	public List<ArtifactVersion> getDependencies()
	{
		return null;
	}

	@Override
	public List<ArtifactVersion> getDependants() 
	{
		return null;
	}

	@Override
	public String getSortingRules() 
	{
		return null;
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) 
	{
		return false;
	}

	@Override
	public boolean matches(Object mod) 
	{
		return false;
	}

	@Override
	public Object getMod() 
	{
		return null;
	}

	@Override
	public ArtifactVersion getProcessedVersion() 
	{
		return null;
	}

	@Override
	public boolean isImmutable() 
	{
		return false;
	}

	@Override
	public String getDisplayVersion() 
	{
		return null;
	}

	@Override
	public VersionRange acceptableMinecraftVersionRange() 
	{
		return null;
	}

	@Override
	public Certificate getSigningCertificate() 
	{
		return null;
	}

	@Override
	public Map<String, String> getCustomModProperties() 
	{
		return null;
	}

	@Override
	public Class<?> getCustomResourcePackClass() 
	{
		return null;
	}

	@Override
	public Map<String, String> getSharedModDescriptor() 
	{
		return null;
	}

	@Override
	public Disableable canBeDisabled() 
	{
		return null;
	}

	@Override
	public String getGuiClassName() 
	{
		return null;
	}

	@Override
	public List<String> getOwnedPackages() 
	{
		return null;
	}
	
	public void jpreInit()
	{
		this.preInit();
	}
	
	public void jinit()
	{
		this.init();
	}
	
	public void jpostInit()
	{
		this.postInit();
	}
	
	public void registerRecipe(String a, String b, String c, char[] chars, int[] locs, int ioutLoc, int ioutQnt)
	{
		ItemStack iout = new ItemStack(Item.getItemById(ioutLoc), ioutQnt);
		
		ArrayList<Character> list = Lists.newArrayList();
		for(char ch : chars)
		{
			list.add(ch);
		}
		
		if(chars.length != locs.length)
			return;
		
		String cmp = a + b + c;
		Object[] obj = new Object[1024];
		int currObjNum = 2;
		
		while(currObjNum >= chars.length)
		{	
			currObjNum--;
			currObjNum--;
			char cs = cmp.charAt(currObjNum);
			
			currObjNum++;
			obj[currObjNum] = Character.valueOf(cs);
			
			currObjNum++;
			obj[currObjNum] = Item.getItemById(locs[list.indexOf(cs)]);
			
			currObjNum++;
		}
		
		GameRegistry.addRecipe(iout, a, b, c, obj);
	}
	
	public native void preInit();
	public native void init();
	public native void postInit();
}
