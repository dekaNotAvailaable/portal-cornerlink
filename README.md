Portal Cornerlink

Control Nether portal linking by placing glazed terracotta at portal corners. Portals connect to destinations with matching corner patterns while preserving vanilla-compatible entity behavior.

This project is a downstream fork that fixes Nether portal exit orientation and entity velocity handling issues present in prior forks, without changing portal matching rules.

Origin & Credits

Original project
Corner Portal Linking
 by starbidou

Upstream fork
A community-maintained fork that updated the mod for Minecraft 1.21.x and introduced performance optimizations.

This fork
A fix-focused downstream fork that restores correct portal exit behavior (orientation and velocity).

This project is not affiliated with the original author or upstream fork maintainers.

What This Fork Changes

Compared to the upstream fork:

✅ Fixed Nether portal exit orientation
(matches vanilla Minecraft 1.21.5+ behavior)

✅ Preserves entity velocity across portal travel
→ minecarts no longer derail

❌ No changes to portal corner-matching rules

❌ No Minecraft version upgrade performed here

❌ No new gameplay mechanics added

If no linking blocks are present, vanilla Nether portal behavior is preserved.

Compatibility Notes
Nether portal chunk loaders

This fork restores vanilla entity orientation and velocity handling when traveling through Nether portals.
As a result, standard minecart-based Nether portal chunk loaders and rail-driven portal systems function correctly again.

No chunk loading mechanics are added or modified by this mod — this fix simply restores vanilla-compatible behavior that portal-based designs rely on.

How It Works

Place any colored glazed terracotta at the four corners of a Nether portal

On the destination side, portals with matching corner block patterns will link together

Block orientation does not matter (mirrored portals still match)

No corner blocks → vanilla Nether portal logic

Requirements

Minecraft 1.21.x

Fabric Loader ≥ 0.17.2

Fabric API ≥ 0.138.3

Java 21

Installation

Install Fabric Loader for your Minecraft version

Install Fabric API

Place the mod JAR in your .minecraft/mods folder

Launch the game