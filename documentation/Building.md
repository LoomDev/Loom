# Building Loom

Loom provides all of the scripts necessary to set up a build environment in this repository. To set up a fresh build environment, you need to have [Maven](https://maven.apache.org/) installed. Once installed, run the following in a bash shell ([WSL](https://docs.microsoft.com/en-us/windows/wsl/install-win10) is preferable on Windows systems):
```bash
git clone https://github.com/LoomDev/Loom
cd Loom
./loom setup
```
Once your environment is set up, you can open the project in your IDE of choice. If you have made an NMS modification and want to convert your changes to a patch, use `./loom rb` to rebuild patches. To apply all patches to existing NMS, use `./loom p`.

To build a complete Loom JAR, run the setup commands above, followed by `./loom jar`. The final JAR will be inside the root folder (the folder Loom was cloned into).
