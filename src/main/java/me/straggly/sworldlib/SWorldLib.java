package me.straggly.sworldlib;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.Arrays;

public final class SWorldLib
{
    private World worldInstance;

    /**
     * Create an instance of SWorld-lib.
     *
     * @param world - The world object of the world you'd like to assign.
     */
    public SWorldLib(World world){
        this.worldInstance = createWorld(world.getName());
    }

    /**
     * Create an instance of SWorld-li.
     *
     * @param worldName - The name of the world you'd like to assign.
     */
    public SWorldLib(String worldName){
        this.worldInstance = createWorld(worldName);
    }

    /**
     * This method will delete all files in the world assigned to the SWorld-lib instance's folder(s).
     *
     * @return true if success, false if fail.
     */
    public boolean deleteWorld(){
        if (worldInstance == null || !worldInstance.getWorldFolder().exists()) return false;

        try {
            Arrays.stream(worldInstance.getWorldFolder().listFiles()).forEach(file -> {
                file.delete();
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * If the "name" parameter doesn't already exist as a world, this method will then create one.
     *
     * @param name - Name of the world.
     * @return the existing world OR the world created.
     */
    public World createWorld(String name){
        return Bukkit.getWorld(name) != null ? Bukkit.getWorld(name) : new WorldCreator(name).createWorld();
    }

    /**
     * Unload the world assigned to the SWorld-lib instance.
     * This method loops through loaded chunks while saving and unloading them.
     *
     * @return true if success, false if fail.
     */
    public boolean unloadWorld(){
        try{
            Arrays.stream(worldInstance.getLoadedChunks()).forEach(chunk -> {
                chunk.unload(true);
            });
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get the world assigned to the SWorld-lib instance.
     *
     * @return Bukkit World
     */
    public World getAssignedWorld(){
        return worldInstance;
    }
}
