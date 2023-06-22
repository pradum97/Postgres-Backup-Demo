package com.example.postgresbackupdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        exportDb2();
    }
    public static void exportDb2() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process p;
        ProcessBuilder pb;
        rt = Runtime.getRuntime();
        pb = new ProcessBuilder(
                "C:\\Program Files\\PostgreSQL\\14\\bin\\pg_dump.exe",
                "--host", "localhost",
                "--port", "5200",
                "--username", "postgres",
                "--no-password",
                "--format", "custom",
                "--blobs",
                "--verbose", "--file", "D:\\service_station_backup.sql", "checkBackup");
        try {
            final Map<String, String> env = pb.environment();
            env.put("PGPASSWORD", "postgres");
            p = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            p.waitFor();
            System.out.println(p.exitValue());

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
