package com.github.funthomas424242.jpacker4gramps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileHelperTest {

    private static final String TEST_PROPERTIES_FILENAME = "test.properties";

    private static final String BEISPIEL1_GRAMPS_FILE = "beispiel1.gramps.file";
    private static final String BEISPIEL1_GRAMPS_FILE_LEN = "beispiel1.gramps.file.len";
    private static final String BEISPIEL1_MEDIA_FOLDER = "beispiel1.media.folder";

    protected static Configuration config;

    @BeforeClass
    public static void init() {
        Configurations configs = new Configurations();
        try {
            config = configs.properties(new File(TEST_PROPERTIES_FILENAME));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {

    }

    @Test
    public void readContentOfGrampsDatabaseFile_WithContent()
            throws IOException {
        // preparing
        final File file = new File(config.getString(BEISPIEL1_GRAMPS_FILE));
        final FileHelper fileHelper = new FileHelper(file);
        assertTrue("gramps.file does not exists:", file.exists());
        // execution
        final String content = fileHelper.getFileContent();
        // assertions
        assertEquals(config.getInt(BEISPIEL1_GRAMPS_FILE_LEN),
                content.length());
    }

    @Test
    public void listMediaFolderFiles_WithFile() {
        // preparing
        final File mediaFolder = new File(
                config.getString(BEISPIEL1_MEDIA_FOLDER));
        final FileHelper fileHelper = new FileHelper(mediaFolder);
        // execution
        final List<File> fileList = fileHelper.getFilelistOfFolder();
        // asserts
        assertEquals(1, fileList.size());
        assertEquals("Beispiel.jpg", fileList.get(0).getName());
    }

    @Test
    public void listMediaFolderDirectories_WithFolder() {
        // preparing
        final File mediaFolder = new File(
                config.getString(BEISPIEL1_MEDIA_FOLDER));
        final FileHelper fileHelper = new FileHelper(mediaFolder);
        // execution
        final List<File> fileList = fileHelper.getDirectorylistOfFolder();
        // asserts
        assertEquals(1, fileList.size());
        assertEquals("subfolder1", fileList.get(0).getName());
    }

}
