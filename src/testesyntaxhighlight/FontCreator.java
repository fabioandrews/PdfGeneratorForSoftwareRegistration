package testesyntaxhighlight;

import java.awt.Font;
import java.io.InputStream;
 
/**
 * With this class, you can create a {@link java.awt.Font} from TTF-files that are packaged with the jar.
 * 
 * @author G.J. Schouten
 *
 */
public class FontCreator {
 
    public static Font createFont(String name, int style, int size) {
 
        try {
            Font baseFont;
 
            if(name.equalsIgnoreCase("Verdana")) {
                if(style == Font.BOLD) {
                    if(style == Font.ITALIC) {
                        baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/verdana/", "VERDANAZ.TTF");
                    }
                    baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/verdana/", "VERDANAB.TTF");
                } else if(style == Font.ITALIC) {
                    baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/verdana/", "VERDANAI.TTF");
                } else {
                    baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/verdana/", "VERDANA.TTF");
                }
            } else { //Times new Roman is default
                if(style == Font.BOLD) {
                    if(style == Font.ITALIC) {
                        baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/timesnewroman/", "TIMESBI.TTF");
                    }
                    baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/timesnewroman/", "TIMESBD.TTF");
                } else if(style == Font.ITALIC) {
                    baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/timesnewroman/", "TIMESI.TTF");
                } else {
                    baseFont = FontCreator.getBaseFontFromFile("/META-INF/fonts/timesnewroman/", "TIMES.TTF");
                }
            }
 
            Font derivedFont = baseFont.deriveFont(style, size);
            return derivedFont;
 
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * To get a {@link Font} from a file on the filesystem or in a jar.
     * 
     * @param directory
     * @param filename
     * @return
     * @throws Exception
     */
    private static Font getBaseFontFromFile(String directory, String filename) throws Exception {
 
       InputStream is = null;
       try {
           is = FontCreator.class.getResourceAsStream(directory + filename);
 
           Font font = Font.createFont(Font.TRUETYPE_FONT, is);
           return font;
       } finally {
           if(is != null) {
               is.close();
           }
       }
    }
}
