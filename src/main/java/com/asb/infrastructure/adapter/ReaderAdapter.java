package com.asb.infrastructure.adapter;

import com.asb.domain.entity.Lawn;
import com.asb.domain.entity.Mower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderAdapter {


    private static boolean isFileAlreadyRead = false;

    public static Lawn readInputFile() throws IOException {
        if (isFileAlreadyRead) {
            return null;
        }
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/nominalCase.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        String[] lawnDimensions = reader.readLine().split(" ");

        int width = Integer.parseInt(lawnDimensions[0]);
        int height = Integer.parseInt(lawnDimensions[1]);

        Lawn lawn = new Lawn(width, height);
        String line;

        while ((line = reader.readLine()) != null) {
            String[] mowerData = line.split(" ");

            int x = Integer.parseInt(mowerData[0]);
            int y = Integer.parseInt(mowerData[1]);
            char orientation = mowerData[2].charAt(0);

            Mower mower = new Mower(x, y, orientation);
            mower.setInstructions(reader.readLine());

            lawn.addMower(mower);
        }
        reader.close();
        isFileAlreadyRead = true;
        return lawn;
    }
}

