> :warning: **Work in progress**: Loom, and by extension, the Loom API, is still under *heavy* development. Any part of the project is subject to change at any time (often breaking existing plugins) and this project is by no means in a *release* status. Contributions are much appreciated. 

# 🧵 Loom
### The next generation Minecraft server software.

Loom is an implementation of the [Loom API](/api) on vanilla Minecraft. Loom was inspired heavily by CraftBukkit and Sponge, but with the goal to make minimal NMS modifications in order to implement its API and completely open-sourced development. We hope to create a stable server platform for the future.

Loom uses Mojang's mapping names, which allows it to be quickly updated to new and snapshot releases of Minecraft. Along with that, our minimal NMS changes and smart API design allow us to easily be on top of game updates.

### Why Loom?
Loom was created because of the lack of fast version updates from Spigot, CraftBukkit's parent project. Not to mention, Bukkit's ancient, by today's standards, API is in need of rewrites and optimizations. Loom aims to stick as faithful to the original Bukkit API as possible to make it easy for plugin developers to switch over, while also introducing many modern features and concepts directly into the API. The end goal is to completely deprecate the usage of NMS in plugins.

### Building Loom
See [Building](/docs/Building.md).

We hope to create a stable and modern server software for many generations of Minecraft servers to come. Loom is completely open-source and licensed with the [MIT license](/LICENSE). Feel free to contribute to the project! ✨

### Special thanks
[![JProfiler](https://www.ej-technologies.com/images/product_banners/jprofiler_medium.png)](https://www.ej-technologies.com/products/jprofiler/overview.html)  

Special thanks to ej-technologies for providing open source licenses of their excellent Java profiler, [JProfiler](https://www.ej-technologies.com/products/jprofiler/overview.html), for the core developers of Loom. Their tools help us uncover performance bottlenecks and ensure that all of the software we create is performant and optimal.
