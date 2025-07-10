package com.bach.api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataBaseBackup {

    private String dbUsername;
    private String dbPassword;
    private String dbUrl;
    private String outputDir;

    public DataBaseBackup(
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.url}") String url,
            @Value("${backup.output-dir:./backups}") String outputDir
    ) {
        this.dbUsername  = username;
        this.dbPassword  = password;
        this.dbUrl       = url;
        this.outputDir = outputDir;
    }

    private String extractDatabaseName() {
        Pattern p = Pattern.compile(".*/([a-zA-Z0-9_\\-]+)(?:\\?.*)?$");
        Matcher m = p.matcher(dbUrl);
        return m.find() ? m.group(1) : "unknown_db";
    }

    @PostConstruct
    public void crearCarpetaSiNoExiste() {
        new File(outputDir).mkdirs();
    }

    // Cada 6 horas
    @Scheduled(cron = "0 0 */6 * * *")
    public void realizarBackup() {
        String dbName    = extractDatabaseName();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String filename  = String.format("%s/backup_%s_%s.sql", outputDir, dbName, timestamp);

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "mysqldump",
                    "-u" + dbUsername,
                    "-p" + dbPassword,
                    dbName,
                    "-r", filename
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Backup creado: " + filename);
            } else {
                System.err.println("Error al ejecutar mysqldump (salida " + exitCode + ")");
            }
        } catch (Exception e) {
            System.err.println("Excepción durante el backup:");
            e.printStackTrace();
        }
    }
}
