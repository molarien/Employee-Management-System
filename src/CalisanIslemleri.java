import java.sql.*;
import java.util.ArrayList;

public class CalisanIslemleri {

    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public CalisanIslemleri(){

        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/"  + Database.db_ismi + "?useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Bulunamadı");
        }

        try {
            con = DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);
            System.out.println("Bağlantı Başarılı.. ");

        } catch (SQLException e) {
            System.out.println("Bağlantı Başarısız");
        }

    }


    public boolean girisYap(String kullanici_adi , String parola){

        String sorgu = "Select * From adminler where username = ? and password = ?";

        try {
            preparedStatement = con.prepareStatement(sorgu);

            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2, parola);

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();


        } catch (SQLException e) {
            return false;
        }

    }


    public ArrayList<Calisan> calisanlariGetir(){

        ArrayList<Calisan> cikti = new ArrayList<>();



        try {
            statement = con.createStatement();

            String sorgu = "Select * From calisanlar";

            ResultSet rs = statement.executeQuery(sorgu);

            while(rs.next()) {
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String departman = rs.getString("departman");
                String maas = rs.getString("maas");

                cikti.add(new Calisan(id, ad, soyad, departman, maas));

            }


        } catch (SQLException e){
            return null;
        }

        return cikti;

    }

    public void calisanEkle(String ad , String soyad, String departman, String maas){


        String sorgu = "Insert Into calisanlar (ad, soyad, departman, maas) VALUES(?,?,?,?)";

        try {
            preparedStatement = con.prepareStatement(sorgu);

            preparedStatement.setString(1,ad);
            preparedStatement.setString(2,soyad);
            preparedStatement.setString(3,departman);
            preparedStatement.setString(4,maas);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void calisanGuncelle(int id, String new_ad, String new_soyad, String new_departman, String new_maas){

        String sorgu = "Update calisanlar set ad = ? , soyad = ? , departman = ? , maas = ? where id = ?";

        try {
            preparedStatement = con.prepareStatement(sorgu);

            preparedStatement.setString(1,new_ad);
            preparedStatement.setString(2,new_soyad);
            preparedStatement.setString(3,new_departman);
            preparedStatement.setString(4,new_maas);
            preparedStatement.setInt(5,id);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void calisanSil(int id){

        String sorgu = "Delete from calisanlar where id = ?";


        try {
            preparedStatement = con.prepareStatement(sorgu);

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





}
