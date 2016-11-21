package com.armytools.zipsample;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by nguyenbathanh on 11/21/16.
 */

public class ZipHelper {
    private static final String TAG = "ZipHelper";

    public static void deleteFileOrFolder(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles()) {
                deleteFileOrFolder(child);
            }

        fileOrDirectory.delete();

    }

    /**
     * copy a file to a folder
     *
     * @param fromFile
     * @param toFile
     */
    public static void copyFileToFolder(File fromFile, File toFile) {
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            outputChannel = new FileOutputStream(toFile).getChannel();
            inputChannel = new FileInputStream(fromFile).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
            //file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "copyFileToFolder 1");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "copyFileToFolder 2");
        } finally {
            try {
                if (inputChannel != null) inputChannel.close();
                if (outputChannel != null) outputChannel.close();
            } catch (IOException ex) {
                Log.e(TAG, "copyFileToFolder 3");
            }

        }

    }


    /**
     * Zip a folder
     *
     * @param inputFolderPath
     * @param outZipPath
     */
    public static void zipFolder(String inputFolderPath, String outZipPath) {
        try {
            FileOutputStream fos = new FileOutputStream(outZipPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            File srcFile = new File(inputFolderPath);
            File[] files = srcFile.listFiles();
            Log.d("", "Zip directory: " + srcFile.getName());
            for (int i = 0; i < files.length; i++) {
                Log.d("", "Adding file: " + files[i].getName());
                byte[] buffer = new byte[1024];
                FileInputStream fis = new FileInputStream(files[i]);
                zos.putNextEntry(new ZipEntry(files[i].getName()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
        } catch (IOException ioe) {
            Log.e("", ioe.getMessage());
        }
    }
}
