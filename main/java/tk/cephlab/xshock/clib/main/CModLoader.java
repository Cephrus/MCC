package tk.cephlab.xshock.clib.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import net.minecraft.client.Minecraft;
import tk.cephlab.xshock.clib.mod.CModMetadata;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Loader;

public class CModLoader
{
	protected ClassLoader classLoader = Loader.instance().getModClassLoader();
	
	private CModLoader() {}
	
	public static CModLoader load()
	{
		return new CModLoader();
	}
	
	public void loadCMods(File dir) throws IOException
	{
		dir.mkdir();
		
		if(dir.isDirectory() && dir.listFiles() != null)
		{
			File[] mods = dir.listFiles();
			
			for(File mod : mods)
			{
				if(mod.getName().endsWith(".zip"))
				{
					System.out.println("xSHOCK-clib Loading Zip Mod");
					ZipInputStream zis = new ZipInputStream(new FileInputStream(mod));
					ZipEntry entry = zis.getNextEntry();
					List<ZipEntry> entries = Lists.newArrayList();
					
					while(entry != null)
					{
						String ext = dir.getAbsolutePath() + File.separator + entry.getName();
						
						if(!entry.isDirectory())
						{
							BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ext));
					        byte[] bytesIn = new byte[4096];
					        int read = 0;
					        while ((read = zis.read(bytesIn)) != -1) 
					        {
					            bos.write(bytesIn, 0, read);
					        }
					        bos.close();
						}
						else
						{
							File file = new File(ext);
							file.mkdir();
						}
						
						entries.add(entry);
						zis.closeEntry();
						entry = zis.getNextEntry();
					}
					
					zis.close();
					//CModMetadata meta;
					
					for(ZipEntry cmod : entries)
					{
						File file = new File(dir.getAbsolutePath() + File.separator + cmod.getName());
						
						if(file.getName().endsWith(".so") || file.getName().endsWith(".o") || file.getName().endsWith(".dylib") || file.getName().endsWith(".dll"))
						{
							if(file.getName().contains("main") || file.getName().contains("Main"))
							{
								//meta = new CModMetadata(file);
							}
						}
					}
				}
			}
		}
	}
	
	@Deprecated
	public void loadCMods() throws IOException
	{
		File cModDir = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "/mods/c");
		File[] mods = cModDir.listFiles();
		List<ZipFile> zipList = Lists.newArrayList();
		
		cModDir.mkdir();
		
		//if(mods == null) return;
		
		for(File mod : mods)
		{
			if(!mod.isFile()) continue;
			
			if(mod.getName().endsWith(".zip"))
			{
				ZipFile file = new ZipFile(mod);
				zipList.add(file);
			}
		}
		
		Iterator<ZipFile> zipIterator = zipList.iterator();
		
		while(zipIterator.hasNext())
		{
			ZipFile mod = zipIterator.next();
			Enumeration<? extends ZipEntry> entries = mod.entries();
			
			while(entries.hasMoreElements())
			{
				ZipEntry entry = entries.nextElement();
				String name = entry.getName();
				
				if(name.equalsIgnoreCase("main.so") || name.equalsIgnoreCase("main.o") || name.equalsIgnoreCase("main.dylib") || name.equalsIgnoreCase("main.dll"))
				{
					ZipInputStream zip = new ZipInputStream(new FileInputStream(mod.getName()));
					FileOutputStream stream = new FileOutputStream(cModDir.getAbsolutePath() + "/bin/" + mod.getName().replace('/', 'A'));
					
					int data = 0;
					while(data == zip.read() && data != -1)
					{
						stream.write(data);
					}
					
					stream.close();
					zip.close();
				}
			}
		}
	}
}
