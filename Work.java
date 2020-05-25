// Student Name: Mert Altuntas   | Student ID:1804010005 

/* 
 
Welcome to the Facial Emotion Detector V5 !! 
 
If you want to see my progress step by step check it out our Github link!
https://github.com/mertfozzy/Facial-Emotion-Detection
  
Enjoy.

*/

package calling.photo;
import java.awt.Image;
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class Work extends JFrame {
    JButton button;
    JLabel label;
    JLabel label2;
    JLabel label3;

    public Work() {
        super("FED - calling photo test ");
        setTitle("Facial Emotion Detector v6");
        getContentPane().setBackground(Color.BLACK);

        /* -----------------CHOOSING A PICTURE OPTION------------------- */

        button = new JButton("Choose a Photo from library !");
        button.setForeground(Color.RED);
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        button.setBounds(611, 495, 334, 54);

        label = new JLabel(); //displaying the picture
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(494, 11, 531, 473);
        /* -------------------------------------------------------------- */
        

        /* ---------------BROWSING A CURL COMMAND OPTION----------------- */

        JTextField text1 = new JTextField(50); //textbox
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setText("Enter here..");
        text1.setForeground(Color.WHITE);
        text1.setBackground(Color.DARK_GRAY);
        text1.setBounds(110, 424, 334, 60);

        JButton buton1 = new JButton("Browse a URL adress !"); // the button that takes actions
        buton1.setBackground(Color.DARK_GRAY);
        buton1.setForeground(Color.GREEN);
        buton1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        buton1.setBounds(110, 495, 334, 54);

        label2 = new JLabel(); //displaying result of curl command
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label2.setForeground(Color.WHITE);
        label2.setBounds(132, 28, 575, 352);
        
        label3 = new JLabel();
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setBounds(484, 11, 541, 450);

        /* -------------------------------------------------------------- */

        getContentPane().add(button);
        getContentPane().add(label);
        getContentPane().add(text1);
        getContentPane().add(buton1);
        getContentPane().add(label2);
        getContentPane().add(label3);
        

        buton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
       /* -------------------CONVERTING URL into CURL--------------------- */	

                String url = text1.getText();
                String curlCommand = "curl -u \"apikey:BmpffSkPjESnRKDYCoHVKaPQSSHCxrVQU8YS3-WOildA\" -F \"classifier_ids=DefaultCustomModel_2030462599\" \"https://api.us-south.visual-recognition.watson.cloud.ibm.com/instances/acfa1ca5-43c1-4b38-9a3d-9eff8477b8f8/v3/classify?url=" + url + "&version=2018-03-19\"";
                String result = CurlUtil.executeCurlCommand(curlCommand.split(" "));
                System.out.println("Curl command result : " + result);
                label2.setText("<html><p style=\"width:900px\">" + result + "</p></html>"); //html code for displaying console result much better
                URL url1;
                try {
                url1 = new URL(text1.getText());
                BufferedImage image = ImageIO.read(url1);
                label3.setIcon(new ImageIcon(image));
                }
                catch (MalformedURLException e1) {
                e1.printStackTrace();
                }
                catch (IOException ex) {
                ex.printStackTrace();
                }
                
                
            }
        });
        
        /* -------------------------------------------------------------- */
        
        
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));

                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    label.setIcon(ResizeImage(path));
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No File Select");
                }
            }
        });

        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1065, 620);
        setVisible(true);
    }


    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
      
        Image newImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public static void main(String[] args) {
        new Work();
    }
}
