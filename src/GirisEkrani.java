import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GirisEkrani extends JFrame implements ActionListener, KeyListener {

    JLabel label_kullanici_adi;
    JLabel label_parola;
    JTextField tf_kullanici_adi;
    JPasswordField pf_parola;
    JLabel label_text;
    JButton button_giris_yap;


    CalisanIslemleri islemler = new CalisanIslemleri();


    public GirisEkrani(){

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720,720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        getContentPane().setBackground(Color.lightGray);

        label_kullanici_adi = new JLabel();
        label_kullanici_adi.setFont(new Font("Arial", Font.PLAIN,30));
        label_kullanici_adi.setText("Kullanıcı Adı : ");
        label_kullanici_adi.setForeground(Color.BLACK);
        label_kullanici_adi.setSize(200,32);
        label_kullanici_adi.setLocation(100,100);
        label_kullanici_adi.setOpaque(false);
        this.add(label_kullanici_adi);


        label_parola = new JLabel();
        label_parola.setFont(new Font("Arial", Font.PLAIN,30));
        label_parola.setText("Parola : ");
        label_parola.setForeground(Color.BLACK);
        label_parola.setSize(200,32);
        label_parola.setLocation(100,160);
        label_parola.setOpaque(false);
        this.add(label_parola);


        tf_kullanici_adi = new JTextField(100);
        tf_kullanici_adi.setFont(new Font("Arial", Font.PLAIN,30));
        tf_kullanici_adi.setSize(200,32);
        tf_kullanici_adi.setLocation(330,100);
        this.add(tf_kullanici_adi);


        pf_parola = new JPasswordField();
        pf_parola.setFont(new Font("Arial", Font.PLAIN,30));
        pf_parola.setSize(200,32);
        pf_parola.setLocation(330,160);
        pf_parola.addKeyListener(this);
        this.add(pf_parola);


        label_text = new JLabel();
        label_text.setFont(new Font("Arial", Font.PLAIN,30));
        label_text.setText("");
        label_text.setSize(700,32);
        label_text.setLocation(100,280);
        label_text.setOpaque(false);
        this.add(label_text);


        button_giris_yap = new JButton();
        button_giris_yap.setFocusable(false);
        button_giris_yap.setText("Giriş Yap");
        button_giris_yap.setSize(160,32);
        button_giris_yap.setLocation(280,400);
        button_giris_yap.setFont(new Font("Arial", Font.PLAIN,30));
        button_giris_yap.setBorder(BorderFactory.createRaisedBevelBorder());
        button_giris_yap.setBackground(Color.lightGray);
        button_giris_yap.addActionListener(this);
        this.add(button_giris_yap);


        this.setVisible(true);




    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == button_giris_yap){

            label_text.setText("");
            String kullanici_adi = tf_kullanici_adi.getText();
            String parola = new String(pf_parola.getPassword());

            boolean girisBasarili = islemler.girisYap(kullanici_adi, parola);

            if(girisBasarili){
                label_text.setForeground(Color.green);

                CalisanEkrani calisanEkrani = new CalisanEkrani();
                this.setVisible(false);
                calisanEkrani.setVisible(true);


            }
            else{
                label_text.setForeground(Color.red);
                label_text.setText("Giriş Başarısız, Lütfen Tekrar Deneyin.");
            }


        }





    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_ENTER){

            label_text.setText("");
            String kullanici_adi = tf_kullanici_adi.getText();
            String parola = new String(pf_parola.getPassword());

            boolean girisBasarili = islemler.girisYap(kullanici_adi, parola);

            if(girisBasarili){
                label_text.setForeground(Color.green);

                CalisanEkrani calisanEkrani = new CalisanEkrani();
                this.setVisible(false);
                calisanEkrani.setVisible(true);


            }
            else{
                label_text.setForeground(Color.red);
                label_text.setText("Giriş Başarısız, Lütfen Tekrar Deneyin.");
            }


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
