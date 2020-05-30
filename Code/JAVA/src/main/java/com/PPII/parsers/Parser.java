package com.PPII.parsers;

import com.PPII.GUI.DatabaseGui;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
    private ArrayList<String> lexers;
    private BufferedReader file;

    public Parser() {
        this.lexers = new ArrayList<>();
    }

    public Parser(ArrayList<String> lexers) {
        replaceLexer(lexers);
    }

    Parser(String source) {
        this.lexers = new ArrayList<>();
        try {
            addSource(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Parser(ArrayList<String> lexers, String source) {
        replaceLexer(lexers);
        try {
            addSource(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addLexer(String lexer) {
        this.lexers.add(lexer);
    }

    public void replaceLexer(ArrayList<String> lexers) {
        this.lexers = lexers;
    }

    public void addLexer(String lexer, int i) {
        this.lexers.add(i, lexer);
    }

    public void removeAllLexer() {
        this.lexers = null;
    }

    public void removeLexer(String lexer) {
        this.lexers.remove(lexer);
    }

    public void removeLexer(int i) {
        this.lexers.remove(i);
    }

    public void addSource(String source) throws FileNotFoundException {
        this.file = openRessource(source);
    }

    public BufferedReader openRessource(String source) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream file = classLoader.getResourceAsStream(source);
        if (file != null) {
            return new BufferedReader(new InputStreamReader(file));
        } else {
            throw new FileNotFoundException(String.format("Ressource file %s was not loaded, be sure it is in the ressource folder", source));
        }
    }

    public File createSaveDirectory(){
        String path = "/Save/";
        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File parent = new File(jarPath.substring(0, jarPath.lastIndexOf("/")) + path);
        boolean res = parent.mkdirs();
        if (res) {
            DatabaseGui.println("Directory Save created");
        }
        return parent;
    }
    public void executeLexers(String destination, boolean header, String delimiter) throws Exception {

        File dest = new File(createSaveDirectory().getAbsolutePath() + "/" + destination);
        try {
            boolean resCreate = dest.createNewFile();
            if (resCreate) {
                DatabaseGui.println(String.format("File %s created", destination));
            }
            FileWriter destWrite = new FileWriter(dest, false);
            Scanner sc = new Scanner(this.file);
            String nextline = sc.nextLine();
            int nbrFields = nextline.split(delimiter).length;
            if (nbrFields != this.lexers.size()) {
                throw new Exception("Not enough lexers");
            }
            if (!header) {
                int i = 0;
                for (String results : applyLexers(nextline, delimiter)) {
                    destWrite.write(results);
                    i++;
                    if (i < nbrFields) {
                        destWrite.write(";;");
                    }
                }
                destWrite.write("\n");
            }
            while (sc.hasNextLine()) {
                int i = 0;
                for (String results : applyLexers(sc.nextLine(), delimiter)) {
                    destWrite.write(results);
                    i++;
                    if (i < nbrFields) {
                        destWrite.write(";;");
                    }
                }
                destWrite.write("\n");
            }
            destWrite.flush();
            destWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> applyLexers(String nextline, String delimiter) {
        String[] parts = nextline.split(delimiter);
        int i = 0;
        ArrayList<String> res = new ArrayList<>();
        for (String lexer : this.lexers) {
            res.add(parts[i].matches(lexer) ? parts[i].trim().replaceAll("^\"","").replaceAll("\"$","") : "NULL");
            i++;
        }
        return res;
    }

    public Queue<String> executeLexers(boolean header, String delimiter) throws Exception {
        Queue<String> queue=new ArrayDeque<>();
        Scanner sc = new Scanner(this.file);
        String nextline = sc.nextLine();
        int nbrFields = nextline.split(delimiter).length;
        if (nbrFields != this.lexers.size()) {
            throw new Exception("Not enough Queue lexers");
        }
        if (!header) {
            int i = 0;
            StringBuilder str=new StringBuilder();
            for (String results : applyLexers(nextline, delimiter)) {
                str.append(results);
                i++;
                if (i < nbrFields) {
                    str.append(";;");
                }
            }
            queue.add(str.toString());
        }
        while (sc.hasNextLine()) {
            int i = 0;
            StringBuilder str=new StringBuilder();
            for (String results : applyLexers(sc.nextLine(), delimiter)) {
                str.append(results);
                i++;
                if (i < nbrFields) {
                    str.append(";;");
                }
            }
            queue.add(str.toString());
        }
        return queue;
    }


}