/*
 * CLBJ's Java Common Library
 *
 * Copyright © CLBJ - Cloves Langendorf Barcellos Junior
 */

package br.com.clbj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;
import com.google.common.io.ByteStreams;

/**
 *
 * @author clbj
 */
public class FileHandler {

    /**
     * Armazena todo o conteúdo de uma url em um arquivo.
     * 
     * @param url
     * @param outputPath
     * @param overwrite
     * @return
     * @throws IOException 
     */
    public static String urlToFile(String url, String outputPath, Boolean overwrite) throws IOException {

        String filename = url;
        StringTokenizer tokens = new StringTokenizer(url, "/");
        while (tokens.hasMoreElements()) {
            filename = tokens.nextElement().toString();
        }
        File file = new File(outputPath.concat(filename));
        
        if (overwrite || !file.exists()) {
            URL inputUrl = new URL(url);

            file.getParentFile().mkdirs();
            file.createNewFile();
            file.setWritable(true);

            ByteStreams.copy(inputUrl.openStream(), new FileOutputStream(file));
        }

        return file.getAbsolutePath();
    }
}
