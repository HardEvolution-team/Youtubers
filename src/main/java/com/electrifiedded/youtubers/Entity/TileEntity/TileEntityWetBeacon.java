package com.electrifiedded.youtubers.Entity.TileEntity;

import net.minecraft.tileentity.TileEntityBeacon;

/**
 * The 1.7 class carried a half-written 1.8 beacon copy whose methods were never called by 1.7 (wrong names), so in game it
 * behaved exactly like the vanilla beacon tile (pyramid check + beam) without a GUI. Keeping the vanilla behaviour
 * also keeps the vanilla beam renderer, which is looked up through the superclass.
 */
public class TileEntityWetBeacon extends TileEntityBeacon {
}
