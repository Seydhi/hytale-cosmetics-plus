# CosmeticsPlus - Premium Cosmetic System for Hytale

A powerful and flexible cosmetic plugin for Hytale servers featuring hats, wings, pets, and particle effects.

```
   ╔═════════════════════════════╗
   ║     🎨 COSMETICS PLUS v1.0.0      ║
   ║    ┌───┐   35+ Cosmetics!    ║
   ║   ╱  ╲                       ║
   ║  │  👑  │  Premium System      ║
   ║   ╲  ╱                       ║
   ║    └───┘                      ║
   ║    Hats, Wings, Pets, Particles  ║
   ╚═════════════════════════════╝
```

## Features

- **35 Unique Cosmetics** across 4 categories:
  - 10 Hats (Crowns, Wizard Hats, Party Hats, etc.)
  - 10 Wings (Angel, Dragon, Butterfly, etc.)
  - 5 Pets (Wolf, Cat, Dragon, Bunny, Parrot)
  - 10 Particle Effects (Hearts, Stars, Fire, Ice, etc.)

- **Full Command System**:
  - `/cosmetic list` - View all available cosmetics
  - `/cosmetic equip <id>` - Equip a cosmetic
  - `/cosmetic unequip <type>` - Unequip a cosmetic
  - `/cosmetic info <id>` - Get cosmetic details
  - `/unlock <id>` - Unlock a cosmetic
  - `/cosmetic menu` - Open cosmetic menu (coming soon)

- **Player Data Storage** - JSON-based storage system
- **Permission System** - Full permission support for all cosmetics
- **Configurable** - Easy-to-use configuration file
- **EULA Compliant** - All cosmetics are cosmetic-only (no pay-to-win)

## Installation

1. Download `CosmeticsPlus-1.0.0.jar`
2. Place it in your server's `plugins/` folder
3. Restart your server
4. Configure the plugin in `plugins/CosmeticsPlus/config.properties`

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/cosmetic list [type]` | List all cosmetics | cosmeticsplus.cosmetic |
| `/cosmetic equip <id>` | Equip a cosmetic | cosmeticsplus.cosmetic |
| `/cosmetic unequip <type>` | Unequip a cosmetic | cosmeticsplus.cosmetic |
| `/cosmetic info <id>` | Get cosmetic info | cosmeticsplus.cosmetic |
| `/cosmetic menu` | Open cosmetic menu | cosmeticsplus.cosmetic |
| `/unlock <id>` | Unlock a cosmetic | cosmeticsplus.unlock |

## Cosmetic Types

- `HAT` - Hats and headgear
- `WINGS` - Wing accessories
- `PET` - Pet companions
- `PARTICLE` - Particle effects

## Permissions

All cosmetics have individual permissions:
- `cosmeticsplus.hat.crown` - Golden Crown
- `cosmeticsplus.hat.tophat` - Top Hat
- `cosmeticsplus.wings.angel` - Angel Wings
- `cosmeticsplus.pet.wolf` - Wolf Pet
- `cosmeticsplus.particle.heart` - Heart Trail
- ...and many more!

## Configuration

Edit `plugins/CosmeticsPlus/config.properties`:

```properties
# Enable/disable cosmetic types
cosmetics.hats.enabled=true
cosmetics.wings.enabled=true
cosmetics.pets.enabled=true
cosmetics.particles.enabled=true

# Storage configuration
storage.type=json
storage.path=plugins/CosmeticsPlus/playerdata

# Customize messages
messages.prefix=[Cosmetics] 
messages.no_permission=You don't have permission!
messages.cosmetic_unlocked=You unlocked the %cosmetic% cosmetic!
```

## Development

Build from source:

```bash
git clone <repository-url>
cd hytale-cosmetic-mod
./gradlew shadowJar
```

Output: `build/libs/CosmeticsPlus-1.0.0.jar`

## Features for Server Owners

- Monetize your server with cosmetic shops
- Rank-based cosmetic unlocks
- Event reward cosmetics
- Player engagement booster
- EULA-compliant monetization

## Roadmap

- [ ] GUI-based cosmetic menu
- [ ] Cosmetic categories in GUI
- [ ] Preview cosmetics before unlocking
- [ ] Economy integration
- [ ] Database storage support
- [ ] Custom cosmetic creation tools
- [ ] Animated cosmetics
- [ ] Cosmetic bundles

## Support

- Issues: GitHub Issues
- Documentation: README.md
- Community: Discord (coming soon)

## License

MIT License - Free to use and modify

## Credits

Created by Cankayut
Built with official Hytale Plugin Template

---

**Note:** This is a beta version. Some features may be experimental.

Enjoy your new cosmetic system! 🎨✨
