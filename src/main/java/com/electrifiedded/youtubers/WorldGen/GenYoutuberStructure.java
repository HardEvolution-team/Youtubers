package com.electrifiedded.youtubers.WorldGen;

import com.electrifiedded.archaiclib.world.StagedWorld;
import com.electrifiedded.youtubers.YTConfig;
import com.electrifiedded.youtubers.YTList;
import com.electrifiedded.youtubers.Entity.EntityYoutuber;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenYoutuberStructure extends WorldGenerator implements IWorldGenerator {
   /** Structure sites lie on a 5x5 chunk grid; planning starts this many chunks before generation reaches them. */
   private static final int LOOKAHEAD = 6;

   /**
    * Sites are deterministic (world seed + grid cell). When a chunk populates, every site within {@link #LOOKAHEAD}
    * chunks is planned once on the ArchaicLib staged worldgen thread against raw terrain; each chunk then writes its
    * own part of the structure during its own populate (no chunk loading, no cascading, no build-after-generation).
    */
   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
      if (world.isRemote || !this.isDimensionAplicable(world.provider.getDimension())) return;
      PlannedSites planned = PlannedSites.get(world);
      int minX = Math.floorDiv(chunkX - LOOKAHEAD, 5) * 5, maxX = chunkX + LOOKAHEAD;
      int minZ = Math.floorDiv(chunkZ - LOOKAHEAD, 5) * 5, maxZ = chunkZ + LOOKAHEAD;
      for (int cx = minX; cx <= maxX; cx += 5) {
         for (int cz = minZ; cz <= maxZ; cz += 5) {
            if (Math.abs(cx - chunkX) > LOOKAHEAD || Math.abs(cz - chunkZ) > LOOKAHEAD) continue;
            if (!planned.mark(cx, cz)) continue;
            Random r = new Random(world.getSeed() ^ (cx * 341873128712L + cz * 132897987541L) ^ 0x59745475L);
            if (r.nextInt(Math.max(1, YTConfig.SpawnRarity)) != 0) continue;
            int x = cx * 16 + 8 + r.nextInt(16);
            int z = cz * 16 + 8 + r.nextInt(16);
            long seed = r.nextLong();
            StagedWorld.submit(world, () -> {
               if (StagedWorld.getTopBlock(world, x, z) != Blocks.WATER) {
                  this.remove_vegitation_circle(world, x, z, 3.0);
                  this.Select_generate(world, new Random(seed), new BlockPos(x, StagedWorld.getHeightValue(world, x, z), z));
               }
            });
         }
      }
   }

   /** Grid cells already decided (saved with the world, so restarts never plan a site twice). */
   public static class PlannedSites extends net.minecraft.world.storage.WorldSavedData {
      private static final String NAME = "youtubers_sites";
      private final java.util.Set<Long> cells = new java.util.HashSet<>();

      public PlannedSites(String name) { super(name); }

      static PlannedSites get(World w) {
         PlannedSites d = (PlannedSites)w.getPerWorldStorage().getOrLoadData(PlannedSites.class, NAME);
         if (d == null) {
            d = new PlannedSites(NAME);
            w.getPerWorldStorage().setData(NAME, d);
         }
         return d;
      }

      boolean mark(int cx, int cz) {
         if (!this.cells.add(net.minecraft.util.math.ChunkPos.asLong(cx, cz))) return false;
         this.markDirty();
         return true;
      }

      @Override
      public void readFromNBT(net.minecraft.nbt.NBTTagCompound nbt) {
         int[] a = nbt.getIntArray("c");
         for (int i = 0; i + 1 < a.length; i += 2) this.cells.add(net.minecraft.util.math.ChunkPos.asLong(a[i], a[i + 1]));
      }

      @Override
      public net.minecraft.nbt.NBTTagCompound writeToNBT(net.minecraft.nbt.NBTTagCompound nbt) {
         int[] a = new int[this.cells.size() * 2];
         int i = 0;
         for (long l : this.cells) { a[i++] = (int)l; a[i++] = (int)(l >>> 32); }
         nbt.setIntArray("c", a);
         return nbt;
      }
   }

   public boolean isDimensionAplicable(int dimID) {
      for (int i = 0; i < YTConfig.spawnDimensions.length; i++) {
         if (YTConfig.spawnDimensions[i] == dimID) {
            return true;
         }
      }

      return false;
   }

   public boolean Select_generate(World world, Random random, BlockPos pos) {
      int a = random.nextInt(YTList.ListSP.size());
      YTList.ListSP.get(a).spawnin(world, pos, random);
      return false;
   }

   public boolean generate(World world, Random random, BlockPos pos) {
      return false;
   }

   public void addPeople(World world, int i, int j, int k, int input) {
      StagedWorld.onMain(() -> this.addPeopleNow(world, i, j, k, input));
   }

   private void addPeopleNow(World world, int i, int j, int k, int input) {
      EntityYoutuber entity = new EntityYoutuber(world, input);
      entity.setLocationAndAngles(i + 0.5, j + 0.5, k + 0.5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
      entity.rotationYawHead = entity.rotationYaw;
      entity.renderYawOffset = entity.rotationYaw;
      entity.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);
      StagedWorld.spawnEntity(world, entity);
   }

   public boolean hasDemo() {
      return false;
   }

   public void genDemo(World world, Random random, BlockPos pos) {
   }

   public void genBox(World world, Block BlockID, int meta, int type, int i, int j, int k, int i2, int j2, int k2) {
      for (int x = i; x <= i2; x++) {
         for (int y = j; y <= j2; y++) {
            for (int z = k; z <= k2; z++) {
               switch (type) {
                  case 0:
                     StagedWorld.setBlock(world, x, y, z, BlockID, meta, 2);
                     break;
                  case 1:
                     if (x == i || x == i2 || z == k || z == k2) {
                        StagedWorld.setBlock(world, x, y, z, BlockID, meta, 2);
                     }
               }
            }
         }
      }
   }

   public void genBoxAir(World world, int type, int i, int j, int k, int i2, int j2, int k2) {
      for (int x = i; x <= i2; x++) {
         for (int y = j; y <= j2; y++) {
            for (int z = k; z <= k2; z++) {
               switch (type) {
                  case 0:
                     StagedWorld.setBlockToAir(world, x, y, z);
                     break;
                  case 1:
                     if (x == i || x == i2 || z == k || z == k2) {
                        StagedWorld.setBlockToAir(world, x, y, z);
                     }
               }
            }
         }
      }
   }

   public void GenCircle_empty(World world, Random random, Block BlockID, int meta, float i2, float j2, float k2, float r, float r2, char axis) {
      this.GenCircle(world, random, BlockID, meta, i2, j2, k2, r, r2, axis);
      this.GenCircle(world, random, Blocks.AIR, 0, i2, j2, k2, r2, 0.0F, axis);
   }

   public void GenElipseoid(World world, Block BlockID, int meta, float i2, float j2, float k2, float ri, float rj, float rk) {
      int i = (int)i2;
      int j = (int)j2;
      int k = (int)k2;

      for (float a = j - (int)(rj + 4.0F); a <= j + (int)(rj + 4.0F); a++) {
         for (float b = k - (int)(rk + 4.0F); b <= k + (int)(rk + 4.0F); b++) {
            for (float c = i - (int)(ri + 4.0F); b <= k + (int)(ri + 4.0F); b++) {
               float d = (float)Math.sqrt(Math.pow((a - j) / rj, 2.0) + Math.pow((b - k) / rk, 2.0) + Math.pow((c - i) / ri, 2.0));
               if (d <= 1.0F) {
                  StagedWorld.setBlock(world, i, (int)a, (int)b, BlockID, meta, 2);
               }
            }
         }
      }
   }

   public void GenCircle(World world, Random random, Block BlockID, int meta, float i2, float j2, float k2, float r, float r2, char axis) {
      int i = (int)i2;
      int j = (int)j2;
      int k = (int)k2;
      switch (axis) {
         case 'x':
            for (float a = j - (int)(r + 4.0F); a <= j + (int)(r + 4.0F); a++) {
               for (float bxx = k - (int)(r + 4.0F); bxx <= k + (int)(r + 4.0F); bxx++) {
                  float d = (float)Math.sqrt(Math.pow(a - j2, 2.0) + Math.pow(bxx - k2, 2.0));
                  if (d <= r && d >= r2) {
                     StagedWorld.setBlock(world, i, (int)a, (int)bxx, BlockID, meta, 2);
                  }
               }
            }
            break;
         case 'y':
            for (float a = i - (int)(r + 4.0F); a <= i + (int)(r + 4.0F); a++) {
               for (float bx = k - (int)(r + 4.0F); bx <= k + (int)(r + 4.0F); bx++) {
                  float d = (float)(Math.pow(a - i2, 2.0) + Math.pow(bx - k2, 2.0));
                  if (d <= Math.pow(r, 2.0) && d >= Math.pow(r2, 2.0)) {
                     StagedWorld.setBlock(world, (int)a, j, (int)bx, BlockID, meta, 2);
                  }
               }
            }
            break;
         case 'z':
            for (float a = i - (int)(r + 4.0F); a <= i + (int)(r + 4.0F); a++) {
               for (float b = j - (int)(r + 4.0F); b <= j + (int)(r + 4.0F); b++) {
                  float d = (float)Math.sqrt(Math.pow(a - i2, 2.0) + Math.pow(b - j2, 2.0));
                  if (d <= r && d >= r2) {
                     StagedWorld.setBlock(world, (int)a, (int)b, k, BlockID, meta, 2);
                  }
               }
            }
      }
   }

   public void boxReplace(World world, Block BlockID, int meta, Block BlockID2, int meta2, int i, int j, int k, int i2, int j2, int k2, String Pattern) {
      for (int x = i; x <= i2; x++) {
         for (int y = j; y <= j2; y++) {
            for (int z = k; z <= k2; z++) {
               if (StagedWorld.getBlock(world, x, y, z) == BlockID && StagedWorld.getBlockMetadata(world, x, y, z) == meta) {
                  if ("chekers".equals(Pattern) && (x + y + z) % 2 == 0) {
                     StagedWorld.setBlock(world, x, y, z, BlockID2, meta2, 2);
                  }

                  if ("all".equals(Pattern)) {
                     StagedWorld.setBlock(world, x, y, z, BlockID2, meta2, 2);
                  }

                  if ("random".equals(Pattern) && com.electrifiedded.archaiclib.util.FastRandom.shared().nextBoolean()) {
                     StagedWorld.setBlock(world, x, y, z, BlockID2, meta2, 2);
                  }
               }
            }
         }
      }
   }

   public void replace_top_block_circle(World world, Random random, Block BlockID, int meta, double i2, double k2, double r, double r2) {
      int i = (int)i2;
      int k = (int)k2;

      for (int a = i - (int)(r + 2.0); a <= i + (int)(r + 2.0); a++) {
         for (int b = k - (int)(r + 2.0); b <= k + (int)(r + 2.0); b++) {
            double d = Math.sqrt(Math.pow(a - i, 2.0) + Math.pow(b - k, 2.0));
            if (d <= r && d >= r2) {
               StagedWorld.setBlock(world, a, StagedWorld.getHeightValue(world, a, b) - 1, b, BlockID, meta, 3);
            }
         }
      }
   }

   public void remove_vegitation_circle(World world, int i, int k, double r) {
      for (int a = i - (int)(r + 2.0); a <= i + (int)(r + 2.0); a++) {
         for (int b = k - (int)(r + 2.0); b <= k + (int)(r + 2.0); b++) {
            double d = Math.sqrt(Math.pow(a - i, 2.0) + Math.pow(b - k, 2.0));
            if (d <= r) {
               this.remove_vegitation(world, a, b);
            }
         }
      }
   }

   public void remove_vegitation(World world, int i, int k) {
      Block block = StagedWorld.getBlock(world, i, StagedWorld.getHeightValue(world, i, k) - 1, k);
      if (block == Blocks.LOG || block == Blocks.LOG2 || block == Blocks.LEAVES || block == Blocks.LEAVES2) {
         StagedWorld.setBlockToAir(world, i, StagedWorld.getHeightValue(world, i, k) - 1, k);
         this.remove_vegitation(world, i, k);
      }
   }

   public BlockPos getTopBlock(World world, BlockPos pos) {
      BlockPos pos1 = new BlockPos(pos.getX(), world.getActualHeight(), pos.getZ());

      while (pos1.getY() >= 0) {
         BlockPos pos2 = pos1.down();
         Block block = StagedWorld.getBlock(world, pos2.getX(), pos2.getY(), pos2.getZ());
         if (block != Blocks.AIR) {
            break;
         }

         pos1 = pos2;
      }

      return pos1.down();
   }

   public BlockPos getSpawnLoc(BlockPos pos) {
      return pos;
   }

   public void setBlockState(World world, BlockPos pos, Block block, int meta) {
      StagedWorld.setBlock(world, pos.getX(), pos.getY(), pos.getZ(), block, meta, 3);
   }

   public void setBlockToAir(World world, BlockPos pos) {
      StagedWorld.setBlockToAir(world, pos.getX(), pos.getY(), pos.getZ());
   }

   public Block getBlockState(World world, BlockPos pos) {
      return StagedWorld.getBlock(world, pos.getX(), pos.getY(), pos.getZ());
   }

   public net.minecraft.block.state.IBlockState getState(World world, BlockPos pos) {
      return StagedWorld.getState(world, pos);
   }

   public void ClearSphere(World world, int i, int j, int k, double r) {
      for (int a = (int)(-r - 1.0); a <= (int)(r + 1.0); a++) {
         for (int b = (int)(-r - 1.0); b <= (int)(r + 1.0); b++) {
            for (int c = (int)(-r - 1.0); c <= (int)(r + 1.0); c++) {
               if (Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0) + Math.pow(c, 2.0)) <= r) {
                  StagedWorld.setBlockToAir(world, i + a, j + b, k + c);
               }
            }
         }
      }
   }
}
