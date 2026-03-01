import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CalisanEkrani extends JDialog implements KeyListener, ActionListener, MouseListener {

    JTable tablo;
    JScrollPane scrollPane;
    DefaultTableModel model;
    CalisanIslemleri islemler = new CalisanIslemleri();
    JTextField arama_cubugu;
    JSeparator separator;
    JLabel label_ad;
    JLabel label_soyad;
    JLabel label_departman;
    JLabel label_maas;
    JTextField tf_ad;
    JTextField tf_soyad;
    JTextField tf_departman;
    JTextField tf_maas;
    JLabel label_text;
    JButton button;
    JButton button_guncelle;
    JButton button_sil;

    public CalisanEkrani(){

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(720,720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        getContentPane().setBackground(Color.lightGray);

        String[] columns = {"ID", "Ad", "Soyad", "Departman", "Maaş"};

        model = new DefaultTableModel(columns, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tablo = new JTable(model);
        tablo.addMouseListener(this);
        scrollPane = new JScrollPane(tablo);
        scrollPane.setBounds(50,250,600,350);

        JTableHeader header = tablo.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(scrollPane);

        arama_cubugu = new JTextField(100);
        arama_cubugu.setFont(new Font("Arial", Font.PLAIN,30));
        arama_cubugu.setSize(600,30);
        arama_cubugu.setLocation(50,50);
        arama_cubugu.addKeyListener(this);
        this.add(arama_cubugu);

        separator = new JSeparator();
        separator.setBounds(50,90,600,3);
        this.add(separator);



        label_ad = new JLabel();
        label_ad.setText("Ad : ");
        label_ad.setBounds(50,100,80,20);
        label_ad.setOpaque(true);
        this.add(label_ad);


        label_soyad = new JLabel();
        label_soyad.setText("Soyad : ");
        label_soyad.setBounds(50,130,80,20);
        label_soyad.setOpaque(true);
        this.add(label_soyad);



        label_departman = new JLabel();
        label_departman.setText("Departman : ");
        label_departman.setBounds(50,160,80,20);
        label_departman.setOpaque(true);
        this.add(label_departman);


        label_maas = new JLabel();
        label_maas.setText("Maaş : ");
        label_maas.setBounds(50,190,80,20);
        label_maas.setOpaque(true);
        this.add(label_maas);


        tf_ad = new JTextField(30);
        tf_ad.setBounds(140,100,160,20);
        this.add(tf_ad);


        tf_soyad = new JTextField(30);
        tf_soyad.setBounds(140,130,160,20);
        this.add(tf_soyad);

        tf_departman = new JTextField(30);
        tf_departman.setBounds(140,160,160,20);
        this.add(tf_departman);

        tf_maas = new JTextField(30);
        tf_maas.setBounds(140,190,160,20);
        this.add(tf_maas);

        button = new JButton();
        button.setText("Yeni Çalışan Ekle");
        button.setBounds(490,100,160,20);
        button.setFocusable(false);
        button.addActionListener(this);
        this.add(button);

        button_guncelle = new JButton();
        button_guncelle.setText("Çalışan Güncelle");
        button_guncelle.setBounds(490,130,160,20);
        button_guncelle.setFocusable(false);
        button_guncelle.addActionListener(this);
        this.add(button_guncelle);

        button_sil = new JButton();
        button_sil.setText("Çalışan Sil");
        button_sil.setBounds(490,160,160,20);
        button_sil.setFocusable(false);
        button_sil.addActionListener(this);
        this.add(button_sil);

        label_text = new JLabel();
        label_text.setBounds(140,220,200,20);
        label_text.setText("");
        this.add(label_text);


        this.setVisible(true);

        calisanGoruntule();


    }

    public void calisanGoruntule(){

        model.setRowCount(0);
        ArrayList<Calisan> calisanlar;

        calisanlar = islemler.calisanlariGetir();

        if(!(calisanlar == null)){

            for(Calisan calisan : calisanlar){
                Object[] eklenecek = {calisan.getId(), calisan.getAd(), calisan.getSoyad(), calisan.getDepartman(), calisan.getMaas()};

                model.addRow(eklenecek);
            }
        }


    }


    public void dinamikAra(String ara){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tablo.setRowSorter(tr);

        if (ara.trim().isEmpty()) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter("(?iu)" + java.util.regex.Pattern.quote(ara), 1, 2, 3,4));
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        String ara = arama_cubugu.getText();

        dinamikAra(ara);

    }






    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == button){

            label_text.setText("");

            String ad = tf_ad.getText();
            String soyad = tf_soyad.getText();
            String departman = tf_departman.getText();
            String maas = tf_maas.getText();

            islemler.calisanEkle(ad, soyad, departman, maas);

            calisanGoruntule();

            label_text.setText("Yeni Çalışan Başarıyla Eklendi.");

        }


        if(e.getSource() == button_guncelle){

            String ad = tf_ad.getText();
            String soyad = tf_soyad.getText();
            String departman = tf_departman.getText();
            String maas = tf_maas.getText();

            int selectedRow = tablo.getSelectedRow();

            if(selectedRow == -1){

                if(model.getRowCount() == 0){
                    label_text.setText("Çalışanlar Tablosu Şuanda Boş.");
                }
                else{

                    label_text.setText("Lütfen Güncellenecek Bir Çalışan Seçiniz.");
                }

            }
            else{

                int id = (int) model.getValueAt(selectedRow,0);

                islemler.calisanGuncelle(id, ad, soyad, departman, maas);

                calisanGoruntule();

                label_text.setText("Çalışan Başarıyla Güncellendi.");



            }





        }


        if(e.getSource() == button_sil){

            label_text.setText("");

            int selectedRow = tablo.getSelectedRow();

            if(selectedRow == -1){

                if(model.getRowCount() == 0){
                    label_text.setText("Çalışan Tablosu Şuan Boş.");
                }
                else{
                    label_text.setText("Lütfen Silinecek Bir Çalışan Seçiniz.");
                }

            }
            else{

                int id =(int) model.getValueAt(selectedRow,0);

                islemler.calisanSil(id);

                calisanGoruntule();

                label_text.setText("Çalışan Başarıyla Silindi.");


            }

        }







    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int selectedRow = tablo.getSelectedRow();

        tf_ad.setText(model.getValueAt(selectedRow,1).toString());
        tf_soyad.setText(model.getValueAt(selectedRow,2).toString());
        tf_departman.setText(model.getValueAt(selectedRow,3).toString());
        tf_maas.setText(model.getValueAt(selectedRow,4).toString());







    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
