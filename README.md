> :warning: **Work in progress**: Loom, and by extension, the Loom API, is still under *heavy* development. Any part of the project is subject to change at any time and this project is by no means in a *release* status. Contributions are much appreciated. 

# 🧵 Loom
### The next generation Minecraft server software.

Loom is an implementation of the [Loom API](/api) on vanilla Minecraft. Loom was inspired heavily by CraftBukkit and Sponge, but with the goal to make minimal NMS modifications in order to implement its API and completely open-sourced development. We hope to create a stable server platform for the future.

Loom uses Mojang's mapping names, which allows it to be quickly updated to new and snapshot releases of Minecraft. Along with that, our minimal NMS changes and smart API design allow us to easily be on top of game updates.

### Why Loom?
Loom was created because of the lack of fast version updates from Spigot, CraftBukkit's parent project. Not to mention, Bukkit's ancient, by today's standards, API is in need of rewrites and optimizations. Loom aims to stick as faithful to the original Bukkit API as possible to make it easy for plugin developers to switch over, while also introducing many modern features and concepts directly into the API. The end goal is to completely deprecate the usage of NMS in plugins.

### Building Loom
Loom provides all of the scripts necessary to set up a build environment in this repository. To set up a fresh build environment, run the following in a bash shell (WSL is preferable on Windows systems):
```bash
git clone https://github.com/LoomDev/Loom
cd Loom
./loom setup
```
Once your environment is set up, you can open the project in your IDE of choice. If you have made an NMS modification and want to convert your changes to a patch, use `./loom rb` to rebuild patches. To apply all patches to existing NMS, use `./loom p`.

To build a complete Loom JAR, you will need to have maven installed. Once installed, run the setup commands above followed by `./loom jar`. The final JAR will be inside the `target/` folder.

We hope to create a stable and modern server software for many generations of Minecraft servers to come. Loom is completely open-source and licensed with the MIT license. Feel free to contribute to the project! ✨

### Special thanks
[![JProfiler](https://www.ej-technologies.com/images/product_banners/jprofiler_medium.png)](https://www.ej-technologies.com/products/jprofiler/overview.html)  

Special thanks to ej-technologies for providing open source licenses of their excellent Java profiler, [JProfiler](https://www.ej-technologies.com/products/jprofiler/overview.html), for the core developers of Loom. Their tools help us uncover performance bottlenecks and ensure that all of the software we create is performant and optimal.
