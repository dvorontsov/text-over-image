package denv.graphics.textoverimage;

import denv.graphics.textoverimage.api.TextOverImageService;
import denv.graphics.textoverimage.dto.TextOverImageConfiguration;
import denv.graphics.textoverimage.service.TextOverImageServiceImpl;

import java.awt.image.BufferedImage;

/**
 * Runner.
 * 
 */
public class Runner {
    
    static String getFilePath(String[] arrrrrrrrrgs) {
        String result = "";
        if(arrrrrrrrrgs.length > 0) {
            result = arrrrrrrrrgs[0];
        }
        return result;
    }
    
    public static void main(String[] arrrrrgs) {
        System.out.println("=========== Text Over Image - Begin ===========");
        long tsBegin = System.currentTimeMillis();
        
        String filePath = getFilePath(arrrrrgs);
        System.out.println("Input file path:" + filePath);
      
        TextOverImageService textOverImageService = new TextOverImageServiceImpl();
        TextOverImageConfiguration configuration = textOverImageService.buildConfigurationFromFile(filePath);
        BufferedImage image = textOverImageService.generateImage(configuration);
        
        String outputFile = System.getProperty("user.dir") + "/image-output.png";
        System.out.println("Output file path: " + outputFile);
        textOverImageService.writeImage(image, "png", outputFile);

        long tsEnd = System.currentTimeMillis();
        long timeDelta = tsEnd - tsBegin;
        System.out.println("Finished running in " + timeDelta + "ms");
        
        System.out.println("=========== Text Over Image - End ===========");
    }
}
