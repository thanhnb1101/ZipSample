package com.armytools.zipsample;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button btnZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnZip = (Button) findViewById(R.id.button);
        btnZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testZipFolder();
            }
        });
    }

    /**
     * for test
     */
    private void testCopyFile() {
        File folderInput = new File(Environment.getExternalStorageDirectory() + File.separator + "/MOD_MAKER/mob/icon/mob_icon_48.png");
        File fileOutput = new File(Environment.getExternalStorageDirectory() + File.separator + "/MOD_MAKER/mob_icon_48.png");
        ZipHelper.copyFileToFolder(folderInput, fileOutput);
    }

    /**
     * just for test
     */
    private void testZipFolder() {
        File folderInput = new File(Environment.getExternalStorageDirectory() + File.separator + "/MOD_MAKER/mob/icon/");
        File zipFileOutput = new File(Environment.getExternalStorageDirectory() + File.separator + "/MOD_MAKER/ziped.zip");

        String inputFolderPath =  folderInput.getPath();
        String outZipPath = zipFileOutput.getPath();
        ZipHelper.zipFolder(inputFolderPath, outZipPath);
    }

    /**
     * just for test
     */
    private void testDeleteFile() {
        File fileInput = new File(Environment.getExternalStorageDirectory() + File.separator + "/MOD_MAKER/mob_icon_48.png");
        ZipHelper.deleteFileOrFolder(fileInput);
    }
}
