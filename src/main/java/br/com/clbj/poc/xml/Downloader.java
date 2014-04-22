/*
 * CLBJ's Java Common Library
 *
 * Copyright © CLBJ - Cloves Langendorf Barcellos Junior
 */

package br.com.clbj.poc.xml;

import br.com.clbj.util.FileHandler;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author clbj
 */
public class Downloader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        // ceffd92e39d802caf18d5f514a20a178
        System.out.println("Iniciando execução ...");
        for (int i = 200; i < 210; i++) {
            HashMap<String,Object> params = new HashMap<>();
            HashMap<String,String> headers = new HashMap<>();
            params.put("url", "http://jaws.clicrbs.com.br/gaucha/site/news-article/published/"+i);
            params.put("path", "/tmp/gaucha");
            params.put("overwrite", Boolean.TRUE);
            params.put("extension", "xml");
            headers.put("JAWS-KEY", "ceffd92e39d802caf18d5f514a20a178");
            FileHandler.urlToFile(params, headers);
        }
        System.out.println("Finalizado.");
    }
    
    
    
    
    
}
