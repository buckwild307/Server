package com.beeselmane.cubition.mod;

import com.beeselmane.cubition.exception.InvalidModException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.beeselmane.cubition.JavaGameServer.getPath;

public class JavaModInfo {
    private List<String> authors = new ArrayList<>();
    private boolean jsonConfiguration;
    private String mainClass;
    private String fileHash;
    private File modFile;
    private String name;

    public JavaModInfo(String modFile) {
        this.modFile = new File(modFile);
        this.load();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String getFileHash() {
        return this.fileHash;
    }

    public String getMain() {
        return this.mainClass;
    }

    public Class<? extends JavaMod> getMainAsClass() {
        try {
            ZipFile zmod = new ZipFile(this.modFile);
            ZipEntry modJar = zmod.getEntry("ModBase.jar");
            FileOutputStream stream = new FileOutputStream(getPath() + "mods/tmp/" + this.name + ".jar");
            InputStream jarStream = zmod.getInputStream(modJar);
            byte[] buffer = new byte[(int) modJar.getSize()];
            stream.write(jarStream.read(buffer));
            JarFile extractedJar = new JarFile(getPath() + "mods/tmp/" + this.name + ".jar");
            extractedJar.getInputStream(extractedJar.getEntry(this.mainClass.replaceAll("\\.", "/")));
            return (Class<? extends JavaMod>) Class.forName(this.mainClass, false, this.getClass().getClassLoader());
        } catch (IOException ex) {
            throw new InvalidModException(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new InvalidModException("Mod Main class could not be found: " + this.mainClass);
        } catch (ClassCastException ex) {
            throw new InvalidModException("Mod Main class does not extend JavaMod");
        }
    }

    private void load() {
        try {
            InputStream configStream = null;
            ZipFile zfile = new ZipFile(this.modFile);
            Enumeration<? extends ZipEntry> modFileEntries = zfile.entries();

            while (modFileEntries.hasMoreElements()) {
                ZipEntry entry = modFileEntries.nextElement();

                if (entry.getName().startsWith("modinfo")) {
                    if (entry.getName().endsWith(".yml") || entry.getName().endsWith(".yaml")) {
                        this.jsonConfiguration = false;
                        configStream = zfile.getInputStream(entry);
                        break;
                    } else if (entry.getName().endsWith(".json")) {
                        this.jsonConfiguration = true;
                        configStream = zfile.getInputStream(entry);
                        break;
                    }
                }
            }

            if (configStream == null)
                throw new InvalidModException("Mod does not contain a config file!" + this.modFile.getName());
            if (this.jsonConfiguration) {
                //
            } else {
                //
            }
        } catch (IOException ex) {
            throw new InvalidModException(ex.getMessage());
        }
    }
}
