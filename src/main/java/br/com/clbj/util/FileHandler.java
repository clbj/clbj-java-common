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
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe para manipulação de arquivos.
 *
 * @author Cloves Langendorf Barcellos Junior
 * @version ${project.version}
 */
public class FileHandler {

    /**
     * Armazena todo o conteúdo de uma url em um arquivo.
     *
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static String urlToFile(HashMap<String, Object> params, HashMap<String, String> headers) throws IOException {
        String fileExtension = "";
        String outputPath = params.get("path").toString();
        String filename = params.get("url").toString();
        StringTokenizer tokens = new StringTokenizer(params.get("url").toString(), "/");
        while (tokens.hasMoreElements()) {
            filename = tokens.nextElement().toString();
        }

        if (!outputPath.endsWith("/")) {
            outputPath += "/";
        }
        
        if (params.containsKey("extension")) {
            fileExtension = params.get("extension").toString().toLowerCase();
            if (!fileExtension.startsWith(".")) {
                fileExtension = "." + fileExtension;
            }
        }

        File file = new File(outputPath.concat(filename).concat(fileExtension));

        if ((Boolean) params.get("overwrite") || !file.exists()) {
            URLConnection connection = new URL(params.get("url").toString()).openConnection();
            if (!headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            file.setWritable(true);
            ByteStreams.copy(connection.getInputStream(), new FileOutputStream(file));
        }
        return file.getAbsolutePath();
    }
}