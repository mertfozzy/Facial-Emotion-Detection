// Student Name: Mert Altuntas   | Student ID:1804010005 

/* 
 
Welcome to the Facial Emotion Detector V5 !! 
 
If you want to see my progress step by step check it out our Github link!
https://github.com/mertfozzy/Facial-Emotion-Detection
  
Enjoy.

*/

package calling.photo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;



public class Work extends JFrame {
    JButton button;
    JLabel label;
    JLabel label2;

    public Work() {
        super("FED - calling photo test ");
        setTitle("Facial Emotion Detector v5");
        getContentPane().setBackground(Color.DARK_GRAY);

        /* -----------------CHOOSING A PICTURE OPTION------------------- */

        button = new JButton("Choose a Photo from library !");
        button.setForeground(Color.RED);
        button.setBackground(Color.BLACK);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        button.setBounds(613, 454, 334, 70);

        label = new JLabel(); //displaying the picture
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(485, 11, 554, 412);
        /* -------------------------------------------------------------- */
        

        /* ---------------BROWSING A CURL COMMAND OPTION----------------- */

        JTextField text1 = new JTextField(50); //textbox
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setText("Enter here..");
        text1.setForeground(Color.WHITE);
        text1.setBackground(Color.BLACK);
        text1.setBounds(110, 383, 334, 60);

        JButton buton1 = new JButton("Browse a URL adress !");
        buton1.setBackground(Color.BLACK);
        buton1.setForeground(Color.GREEN);
        buton1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        buton1.setBounds(110, 454, 334, 70);

        label2 = new JLabel(); //displaying result of curl command
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label2.setForeground(Color.WHITE);
        label2.setBounds(132, 28, 798, 352);

        /* -------------------------------------------------------------- */

        getContentPane().add(button);
        getContentPane().add(label);
        getContentPane().add(text1);
        getContentPane().add(buton1);
        getContentPane().add(label2);

        buton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
       /* -------------------CONVERTING URL into CURL--------------------- */	

                String url = text1.getText();
                String curlCommand = "curl -u \"apikey:BmpffSkPjESnRKDYCoHVKaPQSSHCxrVQU8YS3-WOildA\" -F \"classifier_ids=DefaultCustomModel_2030462599\" \"https://api.us-south.visual-recognition.watson.cloud.ibm.com/instances/acfa1ca5-43c1-4b38-9a3d-9eff8477b8f8/v3/classify?url=" + url + "&version=2018-03-19\"";
                String result = CurlUtil.executeCurlCommand(curlCommand.split(" "));
                System.out.println("Curl command result : " + result);
                label2.setText("<html><p style=\"width:900px\">" + result + "</p></html>"); //html code for displaying console result much better
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
        setSize(1065, 585);
        setVisible(true);
    }


    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        // Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        Image newImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public static void main(String[] args) {
        new Work();
    }
}
