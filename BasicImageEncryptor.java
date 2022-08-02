import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// ImageIO.read(new File("filepath"));

public class BasicImageEncryptor{
    public static void encryptor(int key){      

        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            File chosenFile = fileChooser.getSelectedFile(); 
            FileInputStream fis = new FileInputStream(chosenFile);
            try{
                BufferedImage img = ImageIO.read(chosenFile);
                ImageIcon icon = new ImageIcon(img);
                // return 0;
            }
            catch(Exception e){
                System.out.println("Image is already Encrypted");
                return;
            }
            byte []data = new byte[fis.available()];    
            fis.read(data);
            int i=0;
            for(byte b:data){
                // System.out.println(b);
                data[i] = (byte)(b^key);
                i++;
            }

            FileOutputStream fos = new FileOutputStream(chosenFile);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null , "Done");
            System.out.println("Image encrypted Successfully");


        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public static void decryptor(int key){      

        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            File chosenFile = fileChooser.getSelectedFile(); 
            FileInputStream fis = new FileInputStream(chosenFile);
            try{
                BufferedImage img = ImageIO.read(chosenFile);
                ImageIcon icon = new ImageIcon(img);
                System.out.println("Image is already decrypted");
                // return 0;
            }
            catch(Exception e){
                // System.out.println("Image is already Encrypted");
                byte []data = new byte[fis.available()];    
                fis.read(data);
                int i=0;
                for(byte b:data){
                    // System.out.println(b);
                    data[i] = (byte)(b^key);
                    i++;
                }

                FileOutputStream fos = new FileOutputStream(chosenFile);
                fos.write(data);
                fos.close();
                fis.close();
                JOptionPane.showMessageDialog(null , "Done");
                System.out.println("Image decrypted Successfully");

            }
            

        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public static void main(String[] args){
        System.out.println("main method running..");

        // UI
        JFrame frame = new JFrame();
        frame.setTitle("Image Encryptor");
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        Font font = new Font("Roboto", Font.BOLD, 25); /////////////////////////////////////////

        JButton encryptBtn = new JButton();
        encryptBtn.setText("Encrypt Image");
        encryptBtn.setFont(font);

        JButton decryptBtn = new JButton();
        decryptBtn.setText("Decrypt Image");
        decryptBtn.setFont(font);

        JTextField textField = new JTextField(10);
        textField.setFont(font);

        encryptBtn.addActionListener(e->{
            System.out.println("Encrypt Button clicked");
            String inputText=textField.getText();
            int key=Integer.parseInt(inputText);
            encryptor(key);
        });

        decryptBtn.addActionListener(e->{
            System.out.println("Decrypt Button clicked");
            String inputText=textField.getText();
            int key=Integer.parseInt(inputText);
            decryptor(key);
        });


        JLabel label1 = new JLabel();
 
        // add text to label
        label1.setText("Enter Key to Encrypt/Decrypt");
        label1.setFont(font);

        frame.setLayout(new FlowLayout());
        frame.add(label1);
        frame.add(textField);

        frame.add(encryptBtn);
        frame.add(decryptBtn);
        frame.setVisible(true);

        
        // ImageIcon image = new ImageIcon("C:\\Users\\HELLO\\OneDrive\\Desktop\\triall.png");
        // JLabel imageLabel = new JLabel(image); 
        // add(imageLabel);
    }
}