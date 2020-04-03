package LemMem.MurdercraftUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldUtilities {
	
	// Delete a given world
	public void DestroyWorld(World world) {
		File del = world.getWorldFolder();
		if(del.exists()) {
	          File files[] = del.listFiles();
	          for(int i=0; i<files.length; i++) {
	              if(files[i].isDirectory()) {
	                  DestroyWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	}
	
	// Destroy a given world file
	public void DestroyWorld(File world) {
		if(world.exists()) {
	          File files[] = world.listFiles();
	          for(int i = 0; i < files.length; i++) {
	              if(files[i].isDirectory()) {
	                  DestroyWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	}
	
	// Copy a world from one place to another
	public World CopyWorld(World original, String newName) {	
		copyFileStructure(original.getWorldFolder(), new File(Bukkit.getWorldContainer(), newName));
		return new WorldCreator(newName).createWorld();
	}
	
	private static void copyFileStructure(File source, File target){
    try {
        ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
        if(!ignore.contains(source.getName())) {
            if(source.isDirectory()) {
                if(!target.exists())
                    if (!target.mkdirs())
                        throw new IOException("Couldn't create world directory!");
                String files[] = source.list();
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File destFile = new File(target, file);
                    copyFileStructure(srcFile, destFile);
                }
            } else {
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0)
                    out.write(buffer, 0, length);
                in.close();
                out.close();
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}
