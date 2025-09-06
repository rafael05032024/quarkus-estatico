package app.services;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

public class ImageDownloaderService {

    public static String downloadImage(String imageUrl, String fileName) throws Exception {
        URL url = new URL(imageUrl);
        String localPath = Paths.get("src/main/resources/META-INF/resources/images", fileName).toString();

        try (InputStream in = url.openStream(); FileOutputStream fos = new FileOutputStream(localPath)) {
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer)) != -1) {
                fos.write(buffer, 0, n);
            }
        }

        // retorna caminho relativo que será usado no template
        return "/images/" + fileName;
    }
}
