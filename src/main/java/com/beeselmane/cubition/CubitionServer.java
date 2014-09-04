package com.beeselmane.cubition;

import com.beeselmane.cubition.exception.PathFindException;
import com.beeselmane.cubition.mod.JavaModLoader;
import com.beeselmane.cubition.mod.ServerModList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CubitionServer {
    public static final int GENERIC_ERROR = -1;
    public static final int INVALID_JARNAME = 2;
    public static final int PATH_CONTAINS_MULTIPLE_JARS = 3;
    public static final int INVALID_PATH = 4;
    public static final int CONFIG_ERROR = 5;
    public static final int FILE_NOT_FOUND = 6;

    private static Logger logger = Logger.getGlobal();
    private static CubitionServer server = null;
    private static String thisJarFile;
    private static String thisPath;

    private ServerModList modList = new ServerModList();

    public static String getPath() {
        return thisPath;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static String getJarFile() {
        return thisJarFile;
    }

    public static CubitionServer getServer() {
        return server;
    }

    public static void setupPath() throws PathFindException {
        String abstractPath = CubitionServer.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (abstractPath.endsWith("/")) {
            CubitionServer.thisPath = abstractPath;
            File path = new File(CubitionServer.thisPath);

            if (!path.isDirectory()) {
                throw new PathFindException("Jar name must end with .jar", INVALID_JARNAME);
            } else {
                String[] files = path.list();
                List<String> jars = new ArrayList<>();
                for (String file : files) if (file.endsWith(".jar")) jars.add(file);
                if (jars.size() > 1)
                    throw new PathFindException("The Running Path contains multiple jars, and the current one could not be determined", PATH_CONTAINS_MULTIPLE_JARS);

                CubitionServer.thisJarFile = thisPath + jars.get(1);
            }
        } else if (abstractPath.endsWith(".jar")) {
            int finals = abstractPath.lastIndexOf("/");
            CubitionServer.thisPath = abstractPath.substring(0, finals + 1);
            CubitionServer.thisJarFile = abstractPath.substring(finals + 1);
        } else {
            throw new PathFindException("Could not determine running path", INVALID_PATH);
        }
    }

    public static void main(String[] args) {
        System.out.println("Launching Cubition...");
        Thread.currentThread().setName("Game Server Main Thread");

        try {
            CubitionServer.setupPath();
        } catch (PathFindException ex) {
            ex.printStackTrace(System.err);
        }

        CubitionServer.server = new CubitionServer();

        try {
            server.entry(args);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            server.exit(GENERIC_ERROR);
        }
    }

    public synchronized void entry(String[] args) throws Exception {
        File modDir = new File(CubitionServer.thisPath + "mods/");
        File modTmpDir = new File(CubitionServer.thisPath + "mods/tmp/");
        File pluginDir = new File(CubitionServer.thisPath + "plugins/");
        File configDir = new File(CubitionServer.thisPath + "config/");

        if (!modTmpDir.exists())
            if (!modTmpDir.mkdirs()) throw new IOException("Could not create mods directory (or tmp directory)");
        if (!pluginDir.exists()) if (!pluginDir.mkdirs()) throw new IOException("Could not create plugin directory");
        if (!configDir.exists()) if (!configDir.mkdirs()) throw new IOException("Could not create config directory");

        for (String modFile : modDir.list()) {
            if (modFile.endsWith(".mod")) {
                JavaModLoader loader = new JavaModLoader(modFile);
                this.modList.register(loader.load(), loader);
            }
        }

        for (String name : this.modList.nameSet()) {
            this.modList.getLoader(name).enableHandledMod();
        }
    }

    public synchronized void exit(int code) {
        System.exit(code);
    }
}
