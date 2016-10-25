package opendfa.GraphViz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author terasud
 */
public class GraphGUI {

    /**
     * Detects the client's operating system.
     */
    private final static String osName = System.getProperty("os.name").replaceAll("\\s", "");

    /**
     * Load the config.properties file.
     */
    private final static String cfgProp = "/home/terasud/NetBeansProjects/OpenDFA/config/config.properties";
    private final static Properties configFile = new Properties() {
        private final static long serialVersionUID = 1L;

        {
            try {
                load(new FileInputStream(cfgProp));
            } catch (Exception e) {
            }
        }
    };

    /**
     * The dir. where temporary files will be created.
     */
    private static String TEMP_DIR = configFile.getProperty("tempDir");

    /**
     * Where is your dot program located? It will be called externally.
     */
    private static String DOT = configFile.getProperty("dotFor" + osName);

    /**
     * The image size in dpi. 96 dpi is normal size. Higher values are 10%
     * higher each. Lower values 10% lower each.
     *
     * dpi patch by Peter Mueller
     */
    private int[] dpiSizes = {46, 51, 57, 63, 70, 78, 86, 96, 106, 116, 128, 141, 155, 170, 187, 206, 226, 249};

    /**
     * Define the index in the image size array.
     */
    private int currentDpiPos = 7;

    /**
     * Increase the image size (dpi).
     */
    public void increaseDpi() {
        if (this.currentDpiPos < (this.dpiSizes.length - 1)) {
            ++this.currentDpiPos;
        }
    }

    /**
     * Decrease the image size (dpi).
     */
    public void decreaseDpi() {
        if (this.currentDpiPos > 0) {
            --this.currentDpiPos;
        }
    }

    public int getImageDpi() {
        return this.dpiSizes[this.currentDpiPos];
    }

    public static void createDotGraph(String dotFormat, String fileName) {
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        gv.add(dotFormat);
        gv.addln(gv.end_graph());
        String type = "gif";
        // gv.increaseDpi();
        gv.decreaseDpi();
        gv.decreaseDpi();
        File out = new File(fileName + "." + type);
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

    public void createPngGraph(String dotFormat, String filename) {
        createPngGraph(dotFormat, filename, "png");
    }

    public void createPngGraph(String dotFormat, String filename, String type) {
        File img;
        try {
            img = File.createTempFile("graph_", "." + type, new File(TEMP_DIR));
            String dot = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2015/Es1/Comma1Inv_Dot.dot";
            Runtime rt = Runtime.getRuntime();
            // patch by Mike Chenault
            String[] args = {DOT, "-T" + type, "-Gdpi=" + dpiSizes[this.currentDpiPos], dot, "-O"};
            Process p = rt.exec(args);

            p.waitFor();

        } catch (java.io.IOException ioe) {
            System.err.println("Error:    in I/O processing of tempfile in dir " + TEMP_DIR + "\n");
            System.err.println("       or in calling external command");
            ioe.printStackTrace();
        } catch (java.lang.InterruptedException ie) {
            System.err.println("Error: the execution of the external program was interrupted");
            ie.printStackTrace();
        }
    }

    /**
     * It will call the external dot program, and return the image in binary
     * format.
     *
     * @param dot Source of the graph (in dot language).
     * @param type Type of the output image to be produced, e.g.: gif, dot, fig,
     * pdf, ps, svg, png.
     * @return The image of the graph in .gif format.
     */
    private byte[] get_img_stream(File dot, String type) {
        File img;
        byte[] img_stream = null;

        try {
            img = File.createTempFile("graph_", "." + type, new File(TEMP_DIR));
            Runtime rt = Runtime.getRuntime();

            // patch by Mike Chenault
            String[] args = {DOT, "-T" + type, "-Gdpi=" + dpiSizes[this.currentDpiPos], dot.getAbsolutePath(), "-o", img.getAbsolutePath()};
            Process p = rt.exec(args);

            p.waitFor();

            FileInputStream in = new FileInputStream(img.getAbsolutePath());
            img_stream = new byte[in.available()];
            in.read(img_stream);
            // Close it if we need to
            if (in != null) {
                in.close();
            }

            if (img.delete() == false) {
                System.err.println("Warning: " + img.getAbsolutePath() + " could not be deleted!");
            }
        } catch (java.io.IOException ioe) {
            System.err.println("Error:    in I/O processing of tempfile in dir " + TEMP_DIR + "\n");
            System.err.println("       or in calling external command");
            ioe.printStackTrace();
        } catch (java.lang.InterruptedException ie) {
            System.err.println("Error: the execution of the external program was interrupted");
            ie.printStackTrace();
        }

        return img_stream;
    }

}
