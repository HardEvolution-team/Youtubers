# Changelog

## 1.1.2
### Changed
- Performance: uses ArchaicLib 1.40.0 FastRandom instead of java.util.Random, plus the 1.40.0 cached block-state / allocation-free legacy world calls. Requires ArchaicLib 1.40.0+.

## 1.1.1
- Trees and grass inside structures are grown by the real vanilla generators (no simplified trees); they grow as soon as all chunks they can reach exist. Requires ArchaicLib 1.39.1+.

## 1.1.0
- Structure generation rewritten (requires ArchaicLib 1.39.0+): structures are no longer built after the chunk is generated. Sites are deterministic per world seed and planned ahead (6 chunks before generation reaches them) on a background thread against raw terrain; every chunk writes its own part of the structure during its own generation. No chunk loading, no cascading worldgen, no lag spikes when a structure appears.
- Youtubers, pets, paintings and end crystals of a structure spawn when their chunk exists.
- Structure Spawner item builds through the same staged path (no freeze on big structures).

## 1.0.0
- First release of the 1.12.2 (Cleanroom) port of Youtubers+ Mod 1.8.0 by supercat765. Requires ArchaicLib 1.38.0+.
- Dead skin links fixed: random-player NPCs (and youtubers, with the new config option "Use current youtuber skins") get real current skins via the Mojang profile service (64x64 and slim skins, no resource pack needed). Offline/unknown names use the default Steve/Alex skin.
- Trades: the client now shows the same offers the server uses and offer selection works (vanilla merchant container + offer sync).
- Fixed 1.7 bugs: youtuber-specific AI was always that of youtuber #0; Craig/Trayaurus could never be tamed; bank card auto-craft lost notes; mob-arena spawn eggs were invalid; events ran only on the client side; structures caused cascading worldgen (now built after population).
- Bank contents and other player data survive death.
