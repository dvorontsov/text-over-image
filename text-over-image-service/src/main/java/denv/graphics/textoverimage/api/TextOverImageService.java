package denv.graphics.textoverimage.api;

import com.google.gson.JsonSyntaxException;
import denv.graphics.textoverimage.dto.TextOverImageConfiguration;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * TextOverImageService.  An interface to read image configuration and apply it to image file.
 * 
 */
public interface TextOverImageService {

    /**
     * Generated BufferedImage object based on TextOverImageConfiguration.  
     * TextOverImageConfiguration is a file that describes what the image is composed of, 
     * such as width, height, background color, text overlays.
     * 
     * @param configuration - object that describes what goes into the image
     * @return BufferedImage object
     */
    BufferedImage generateImage(TextOverImageConfiguration configuration);

    
    /**
     * Writes image to filesystem.
     * 
     * @param image
     * @param formatName
     * @param path
     * @return False, if no appropriate writer is found.
     *
     * @throws IOException
     */
    boolean writeImage(BufferedImage image, String formatName, String path)
            throws IOException;
    
    
    /**
     * Reads configuration file from disk and parses into string.
     * 
     * @param filePath
     * @return String representation of configuration file.  Null, if exception occurred
     * during reading the file.
     */
    String configurationFileToString(String filePath);

    
    /**
     * Reads configuration file from disk and parses into configuration object.
     * 
     * @param filePath
     * @return TextOverImageConfiguration object.  Null, if file does not exist.
     */
    TextOverImageConfiguration buildConfigurationFromFile(String filePath);
    
    
    /**
     * Builds image configuration file from json string.  The configuration file
     * can then be used to generated BufferedImage object and saved to filesystem
     * as an image.
     * 
     * @param json
     * @return TextOverImageConfiguration object.
     * @throws JsonSyntaxException
     */
    TextOverImageConfiguration buildConfigurationFromJson(String json) throws JsonSyntaxException;
    
}
