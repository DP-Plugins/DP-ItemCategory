<center><img src="https://i.postimg.cc/MKPVVR1s/dplogo-512.png" alt="logo"></center>

<center><img src="https://i.postimg.cc/RZ9dqPFx/introduce.png" alt="introduce"></center>

DP-ItemCategory is a plugin that lets server administrators create and manage custom **item categories** on their Minecraft server.  
Each category is a virtual inventory (GUI) of items. Admins can create, edit, and manage categories easily, then distribute items from those categories to players using simple commands.  
All category data is saved automatically and persists across server restarts.

---

<center><img src="https://i.postimg.cc/RZ9dqP08/description.png" alt="description"></center>

- **Create / Delete Categories** — Create new item categories or remove existing ones  
- **GUI-based Editing** — Edit category contents through an intuitive in-game inventory GUI  
- **Configurable Pages** — Set the maximum number of pages per category to store more items  
- **Item Distribution** — Give specific items from categories to players or all online players  
- **Automatic Saving** — All category data is saved automatically without manual file edits  

---

<center><img src="https://i.postimg.cc/rwcjzhpH/depend-plugin.png" alt="depend-plugin"></center>

- Requires the **`DPP-Core`** plugin (version 5.3.3 or higher)  
- The plugin will not work if **`DPP-Core`** is not installed  
- This plugin does **not** use PlaceholderAPI or any other external dependencies  

---

<center><img src="https://i.postimg.cc/dV01RxJB/installation.png" alt="installation"></center>

1️⃣ Place the **`DPP-Core`** plugin and **`DP-ItemCategory-*.jar`** into your server’s **`plugins`** folder  

2️⃣ Restart or reload the server, and the plugin will be automatically enabled  

3️⃣ After first run, you may edit **`config.yml`** if you want to change the message prefix or language  

---

<center><img src="https://i.postimg.cc/jSKcC85K/settings.png" alt="settings"></center>

- **`config.yml`**  
  - Plugin message prefix  
  - Language setting (`en_US` by default)  
- All other settings are handled directly through commands  

---

<center><img src="https://i.postimg.cc/SxqdjZKw/command.png" alt="command"></center>

❗ **All commands require OP (operator) permission**

**Command List and Examples** (use `/dic`)

| Command | Description | Example |
|------|-------------|---------|
| `/dic create <categoryName>` | Create a new item category | `/dic create TreasureHunt` |
| `/dic delete <categoryName>` | Delete an existing category | `/dic delete TreasureHunt` |
| `/dic edit <categoryName>` | Edit category items via GUI | `/dic edit TreasureHunt` |
| `/dic maxpage <categoryName> <maxPages>` | Set max pages for category | `/dic maxpage TreasureHunt 5` |
| `/dic open <categoryName>` | Open category inventory | `/dic open TreasureHunt` |
| `/dic give <category> <page> <slot> <player> [amount]` | Give item to player | `/dic give TreasureHunt 1 3 Steve 10` |
| `/dic giveall <category> <page> <slot> [amount]` | Give item to all players | `/dic giveall TreasureHunt 1 3 5` |

**❗Notes when using commands**

- Category names cannot contain spaces  
- Page and slot numbers must be valid integers  
- Items are cloned when given; original category items are not consumed  
- All commands require **OP** status  

---

<center><img src="https://i.postimg.cc/Z5ZH0fqL/api-integration.png" alt="api-integration"></center>

- No external API integrations are included  

---

<center><a href="https://discord.gg/JnMCqkn2FX"><img src="https://i.postimg.cc/4xZPn8dC/discord.png" alt="discord"></a></center>

- https://discord.gg/JnMCqkn2FX  
- For questions, bug reports, or feature suggestions, please join the official DP-Plugins Discord  

---
