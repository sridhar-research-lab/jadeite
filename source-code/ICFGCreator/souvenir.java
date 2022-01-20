import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class souvenir extends Applet implements Runnable
{
    private MediaTracker tracker;
    private Image orimage;
    private Image dbimage;
    private Graphics dbgraphics;
    private Thread thread;
    private Color bgcolor;
    private Vector images;
    private Vector urls;
    private int[] pixels;
    private int[] buffer;
    private String target;
    private int current_index;
    private int delay;
    private static int incr;
    private boolean fade_out;
    
    public void stop() {
        this.thread = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.orimage != null) {
            this.dbgraphics.drawImage(this.orimage, (this.size().width - this.orimage.getWidth(null)) / 2, (this.size().height - this.orimage.getHeight(null)) / 2, this);
        }
        if (this.dbimage != null) {
            graphics.drawImage(this.dbimage, 0, 0, this);
        }
    }
    
    public souvenir() {
        this.tracker = new MediaTracker(this);
        this.orimage = null;
        this.thread = null;
        this.bgcolor = new Color(255, 255, 255);
        this.images = new Vector();
        this.urls = new Vector();
        this.target = "_blank";
        this.current_index = 0;
        this.delay = 100;
        this.fade_out = false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final URL url = this.urls.elementAt(this.current_index);
        if (url != null) {
            this.getAppletContext().showDocument(url, this.target);
        }
        return true;
    }
    
    private void readFile() {
        final String parameter = this.getParameter("filename");
        if (parameter != null) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getDocumentBase(), parameter).openStream()));
                int n = 0;
                String line;
                do {
                    line = bufferedReader.readLine();
                    if (line != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
                        if (!stringTokenizer.hasMoreTokens()) {
                            continue;
                        }
                        final Image image = this.getImage(this.getDocumentBase(), stringTokenizer.nextToken());
                        this.images.addElement(image);
                        this.tracker.addImage(image, n++);
                        if (stringTokenizer.hasMoreTokens()) {
                            this.urls.addElement(new URL(this.getDocumentBase(), stringTokenizer.nextToken()));
                        }
                        else {
                            this.urls.addElement(null);
                        }
                    }
                } while (line != null);
            }
            catch (Exception ex) {}
        }
    }
    
    private void waitForImage(final int n) {
        while (!this.tracker.checkID(n, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void createEffect(final int n, final int n2, final int n3) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                final int n4 = i * n + j;
                final int n5 = this.pixels[n4];
                this.buffer[n4] = ((n5 & 0xFF000000) >> 24 << 24 | (((n5 & 0xFF0000) >> 16) * n3 + this.bgcolor.getRed() * (255 - n3)) / 255 << 16 | (((n5 & 0xFF00) >> 8) * n3 + this.bgcolor.getGreen() * (255 - n3)) / 255 << 8 | ((n5 & 0xFF) * n3 + this.bgcolor.getBlue() * (255 - n3)) / 255);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        souvenir.incr = 10;
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
        int n = 0;
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            this.waitForImage(this.current_index);
            final Image image = this.images.elementAt(this.current_index);
            final int n2 = (image.getWidth(null) < this.size().width) ? image.getWidth(null) : this.size().width;
            final int n3 = (image.getHeight(null) < this.size().height) ? image.getHeight(null) : this.size().height;
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n2, n3, this.pixels, 0, n2);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {}
            this.createEffect(n2, n3, n);
            this.orimage = this.createImage(new MemoryImageSource(n2, n3, this.buffer, 0, n2));
            if (this.fade_out) {
                n -= souvenir.incr;
            }
            else {
                n += souvenir.incr;
            }
            if (n > 255) {
                if (this.current_index + 1 < this.images.size()) {
                    this.waitForImage(this.current_index + 1);
                }
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex3) {}
                n -= souvenir.incr;
                this.fade_out = true;
            }
            if (n < 0) {
                n += souvenir.incr;
                this.fade_out = false;
                if (this.current_index + 1 < this.images.size()) {
                    ++this.current_index;
                }
                else {
                    this.current_index = 0;
                }
            }
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("delay");
        if (parameter != null) {
            this.delay = Integer.parseInt(parameter);
        }
        if (this.delay < 10) {
            this.delay = 10;
        }
        final String parameter2 = this.getParameter("bgcolor");
        if (parameter2 != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter2, 16));
        }
        final String parameter3 = this.getParameter("target");
        if (parameter3 != null) {
            this.target = parameter3;
        }
        final String parameter4 = this.getParameter("increment");
        if (parameter4 != null) {
            souvenir.incr = Integer.parseInt(parameter4);
        }
        if (souvenir.incr > 255) {
            souvenir.incr = 255;
        }
        this.pixels = new int[this.size().width * this.size().height];
        this.buffer = new int[this.size().width * this.size().height];
        this.setBackground(this.bgcolor);
        this.readFile();
        this.dbimage = this.createImage(this.size().width, this.size().height);
        this.dbgraphics = this.dbimage.getGraphics();
    }
}
