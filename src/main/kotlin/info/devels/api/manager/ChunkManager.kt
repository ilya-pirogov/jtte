//package info.devels.api.manager
//
//import info.devels.jtte.entities.TileEntityBeacon
//import net.minecraft.tileentity.TileEntity
//import net.minecraftforge.common.ForgeChunkManager
//
//
//object chunkManager {
//
//    fun addChunkLoader(beacon: TileEntityBeacon) {
//        println("ADDED !!!")
//        if (reviving)
//            return
//
//        int dim = CommonUtils.getDimension(loader.getWorld());
//        if (dormant) {
//            HashSet<BlockCoord> coords = dormantLoaders.get(dim);
//            if (coords == null)
//                dormantLoaders.put(dim, coords = new HashSet<BlockCoord>());
//            coords.add(loader.getPosition());
//        } else {
//            forcedChunksByLoader.put(loader, new HashSet<ChunkCoordIntPair>());
//            forceChunks(loader, dim, loader.getChunks());
//        }
//        setDirty();
//    }
//
//    fun removeChunkLoader(beacon: TileEntityBeacon) {
//        println("REMOVED !!!")
//        int dim = CommonUtils.getDimension(loader.getWorld());
//        if (dormant) {
//            HashSet<BlockCoord> coords = dormantLoaders.get(dim);
//            if(coords != null)
//                coords.remove(loader.getPosition());
//        } else {
//            HashSet<ChunkCoordIntPair> chunks = forcedChunksByLoader.remove(loader);
//            if (chunks == null)
//                return;
//            unforceChunks(loader, dim, chunks, true);
//        }
//        setDirty();
//    }
//}