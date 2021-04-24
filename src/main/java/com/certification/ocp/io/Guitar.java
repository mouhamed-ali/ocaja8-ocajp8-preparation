package com.certification.ocp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Guitar {
    public void readMusic(File f) {
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String music = null;
            try {
                while((music = r.readLine()) != null)
                    System.out.println(music);
            } catch (IOException e) {}
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {}
    }}
