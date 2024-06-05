package com.asb.infrastructure.adapter;


import com.asb.domain.entity.Mower;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class WriterAdapter {

    public static void writeOutputFile(String filePath, List<Mower> mowers) throws IOException {
        Path outputPath = Path.of(filePath);

        if (Files.exists(outputPath)) {
            log.error("---------- FILE ALREADY EXISTS ----------");
            return;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Mower mower : mowers) {
            log.info("{} {} {}", mower.getX(), mower.getY(), mower.getOrientation());
            writer.write(mower.getX() + " " + mower.getY() + " " + mower.getOrientation());
            writer.newLine();
        }

        writer.close();

    }
}

