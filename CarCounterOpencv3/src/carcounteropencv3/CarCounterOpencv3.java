package carcounteropencv3;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.MatOfPoint;
import static org.opencv.imgproc.Imgproc.INTER_CUBIC;
import static org.opencv.imgproc.Imgproc.resize;


public class CarCounterOpencv3 {

  
   
    public static JLabel lbl = new JLabel();
    public static JLabel lbl2 = new JLabel();
    public static JFrame frame = null;
    public static ImageIcon icon = null;
    public static ImageIcon icon2 = null;

    public static void main(String[] args) {

        int a = 0, frame_no = 0, frame_no2 = 0, contours_tut = 0;
        double ortx = 0, x = 0;
        double orty = 0, y = 0;
        double boyut = 0;
        int kucuk_arac = 0, orta_arac = 0, buyuk_arac = 0;
        int toplam_yol = 0;
        float toplam_zaman = 0;
        int speed = 0;
        boolean arac_bir, arac_iki, arac_uc, arac_dort;
        String say = "", say_kucuk = "", say_orta = "", say_buyuk = "", zaman_s = "", speeds = "";
        int frame_tut[] = new int[20];

        for (int i = 0; i < frame_tut.length; i++) {
            frame_tut[i] = 0;
        }
        
         System.load("C:\\Users\\Oğuz Kurtcebe\\Desktop\\open\\opencv\\build\\java\\x64\\opencv_java300.dll");
        System.load("C:\\Users\\Oğuz Kurtcebe\\Desktop\\open\\opencv\\build\\x64\\vc12\\bin\\opencv_ffmpeg300_64.dll");


 

       
         VideoCapture capture = new VideoCapture("C:\\Users\\Oğuz Kurtcebe\\Desktop\\Dersler\\BİTİRME\\vid\\yoll4.mp4");
      
        Mat image = new Mat();
        Mat NewImage = new Mat();
        Mat Bnary = new Mat();
        Mat Bnary2 = new Mat();
        Mat SourceImage = new Mat();
        Mat SourceImage2 = new Mat();
        Mat camImage = new Mat();
        BackgroundSubtractorMOG2 backgroundSubtractorMOG = Video.createBackgroundSubtractorMOG2(); 
       if (capture.isOpened()) {
            while (true) {
                capture.read(camImage);
                resize(camImage, camImage, new Size(640, 360), 0, 0, INTER_CUBIC);
                Imgproc.cvtColor(camImage, camImage, Imgproc.COLOR_RGB2GRAY);

                Mat fgMask = new Mat();
                backgroundSubtractorMOG.apply(camImage, fgMask, 0.1);
                Imgproc.threshold(fgMask, fgMask, 40, 255, Imgproc.THRESH_BINARY);
//                Imgproc.medianBlur(fgMask, fgMask, 15);
                Imgproc.blur(fgMask, fgMask, new Size(50, 50));
                Imgproc.threshold(fgMask, fgMask, 55, 255, Imgproc.THRESH_BINARY);

                Mat output = new Mat();
                Mat output2 = new Mat();
                camImage.copyTo(output, fgMask);
                camImage.copyTo(output2, fgMask);

                Mat heirarchy = new Mat();
                Rect boundingRect = new Rect(0, 0, 0, 0);
                List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

                Imgproc.findContours(output, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
                Imgproc.cvtColor(camImage, camImage, Imgproc.COLOR_GRAY2RGB);

                Imgproc.line(camImage, new Point(50, 100), new Point(500, 100), new Scalar(0, 255, 255), 2);
                Imgproc.line(camImage, new Point(0, 360), new Point(700, 360), new Scalar(0, 255, 255), 2);

                if (contours.size() < contours_tut) {
                    for (int i = 0; i < contours.size(); i++) {
                        frame_tut[i] = frame_tut[i + 1];
                    }
                    for (int i = contours.size(); i < frame_tut.length; i++) {
                        frame_tut[i] = 0;
                    }
                }
                for (int i = 0; i < contours.size(); i++) {
                    boundingRect = Imgproc.boundingRect(contours.get(i));

                   
                    contours_tut = contours.size();
                     
                    frame_tut[i] += 1;

                    boyut = boundingRect.area();
                    orty = (((boundingRect.br().y - boundingRect.tl().y) / 2) + boundingRect.tl().y);
                    ortx = (((boundingRect.br().x - boundingRect.tl().x) / 2) + boundingRect.tl().x);

                    toplam_yol = (int) Math.abs((360 - orty));
                    toplam_zaman = (float) ((frame_tut[i] + 2) / 23.0);
                    speed = (int) ((toplam_yol / toplam_zaman) / (3.6));
                    speeds = speed + "";

                    if (boundingRect.area() > 500) {
                        Imgproc.rectangle(camImage, boundingRect.tl(), boundingRect.br(), new Scalar(0, 0, 255), 2);
                        Imgproc.circle(camImage, new Point(ortx, orty), 5, new Scalar(0, 0, 255), -1);
                        Imgproc.putText(camImage, speeds, boundingRect.tl(), 0, 1, new Scalar(76, 255, 0), 2);
                       
                    }

                    if (orty >96.5 && orty <105 && ortx > 50.0 && ortx < 500.0) {
                        Imgproc.line(camImage, new Point(0, 100), new Point(500, 100), new Scalar(0, 0, 255), 3);
                        a++;

                        if (boyut <= 14000.0) {
                            kucuk_arac++;
                        } else if (boyut > 14100.0 && boyut < 20000.0) {
                            orta_arac++;
                        } else if (boyut >= 20100.0) {
                            buyuk_arac++;
                        }
                     System.out.println("dd:"+contours_tut);
                    }

                    if (orty > 93.9 && orty < 106 && ortx > 50.0 && ortx < 500.0) {
                        System.out.println(a + "-" + orty + "-" + boyut);
                    }
                }

                say = a + "";
                say_kucuk = kucuk_arac + "";
                say_orta = orta_arac + "";
                say_buyuk = buyuk_arac + "";
                zaman_s = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + "";
                Imgproc.putText(camImage, "SAYI:", new Point(0, 50), 0, 0.5, new Scalar(76, 255, 0), 2);
                Imgproc.putText(camImage, say, new Point(10, 100), 0, 2, new Scalar(76, 255, 0), 5);
                Imgproc.putText(camImage, "KUCUK:", new Point(0, 130), 0, 0.5, new Scalar(76, 255, 0), 2);
                Imgproc.putText(camImage, say_kucuk, new Point(10, 160), 0, 1, new Scalar(76, 255, 0), 3);
                Imgproc.putText(camImage, "ORTA:", new Point(0, 190), 0, 0.5, new Scalar(76, 255, 0), 2);
                Imgproc.putText(camImage, say_orta, new Point(10, 220), 0, 1, new Scalar(76, 255, 0), 3);
                Imgproc.putText(camImage, "BUYUK:", new Point(0, 250), 0, 0.5, new Scalar(76, 255, 0), 2);
                Imgproc.putText(camImage, say_buyuk, new Point(10, 280), 0, 1, new Scalar(76, 255, 0), 3);
                Imgproc.putText(camImage, "zaman: " + zaman_s, new Point(0, 30), 0, 0.5, new Scalar(76, 255, 0), 2);

                PushImage(ConvertMat2Image(camImage), ConvertMat2Image(output2));
            }

        }
    }
    

    private static BufferedImage ConvertMat2Image(Mat data) {

        MatOfByte byteMatVerisi = new MatOfByte();
        Imgcodecs.imencode(".jpg", data, byteMatVerisi);
        byte[] byteArray = byteMatVerisi.toArray();

        BufferedImage goruntu = null;

        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            goruntu = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return goruntu;
    }

    public static void WindowReady() {
        frame = new JFrame();
        frame.setLayout(new GridLayout(1, 2, 10, 10));
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void PushImage(Image img2, Image img3) {

        if (frame == null) {
            WindowReady();
        }
        if (lbl != null && lbl2 != null) {
            frame.remove(lbl);
            frame.remove(lbl2);
            icon = new ImageIcon(img2);
            icon2 = new ImageIcon(img3);
            lbl = new JLabel();
            lbl2 = new JLabel();
            lbl.setIcon(icon);
            lbl2.setIcon(icon2);
            frame.add(lbl);
            frame.add(lbl2);
            frame.revalidate();
        }
    }

}
