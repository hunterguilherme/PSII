package contaBancaria;

import dataBase.ConexaoJavaDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaBancariaDAO {

    private PreparedStatement stmC;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmR;
    private PreparedStatement stmRId;
    private PreparedStatement stmRNome;

    @SuppressWarnings("CallToPrintStackTrace")
    public ContaBancariaDAO(ConexaoJavaDB conexao) {

        try {
            Connection connect = conexao.getConexao();
            String sqlC = "insert into contabancaria(nomeTitular, saldo, nAgencia) VALUES(?,?,?)";
            String sqlU = "Update contabancaria SET nomeTitular =?, saldo=?, nAgencia=? where id=?";
            String sqlD = "Delete from contabancaria where id=?";
            String sqlR = "Select*from contabancaria";
            String sqlRId = "Select*from contabancaria where id=?";
            String sqlRNome = "Select*from contabancaria where nomeTitular=?";
            this.stmC = connect.prepareStatement(sqlC, Statement.RETURN_GENERATED_KEYS);
            this.stmU = connect.prepareStatement(sqlU);
            this.stmD = connect.prepareStatement(sqlD);
            this.stmR = connect.prepareStatement(sqlR);
            this.stmRId = connect.prepareStatement(sqlRId);
            this.stmRNome = connect.prepareStatement(sqlRNome);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ContaBancaria> readAll() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            List<ContaBancaria> contasBancarias = new ArrayList<>();

            while (rs.next()) {
                ContaBancaria aux = new ContaBancaria();
                aux.setNomeTitular(rs.getString("nomeTitular"));
                aux.setSaldo(rs.getDouble("saldo"));
                aux.setnAgencia(rs.getInt("nAgencia"));
                aux.setId(rs.getLong("id"));
                contasBancarias.add(aux);
            }
            return contasBancarias;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ContaBancaria> readId(long id) {
        try {

            this.stmRId.setLong(1, id);
            ResultSet rs = this.stmRId.executeQuery();
            List<ContaBancaria> contasBancarias = new ArrayList<>();

            while (rs.next()) {
                ContaBancaria aux = new ContaBancaria();
                aux.setNomeTitular(rs.getString("nomeTitular"));
                aux.setSaldo(rs.getDouble("saldo"));
                aux.setnAgencia(rs.getInt("nAgencia"));
                aux.setId(rs.getLong("id"));
                contasBancarias.add(aux);
            }
            return contasBancarias;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<ContaBancaria> readNome(String nome) {
//        try {
//
//            this.stmRNome.setString(1, nome);
//            ResultSet rs = this.stmRNome.executeQuery();
//            List<ContaBancaria> contasBancarias = new ArrayList<>();
//
//            while (rs.next()) {
//                ContaBancaria aux = new ContaBancaria();
//                aux.setNomeTitular(rs.getString("nomeTitular"));
//                aux.setSaldo(rs.getDouble("saldo"));
//                aux.setnAgencia(rs.getInt("nAgencia"));
//                aux.setId(rs.getLong("id"));
//                contasBancarias.add(aux);
//            }
//            return contasBancarias;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public ContaBancaria create(ContaBancaria cb) {
        try {
            this.stmC.setString(1, cb.getNomeTitular());
            this.stmC.setDouble(2, cb.getSaldo());
            this.stmC.setInt(3, cb.getnAgencia());

            this.stmC.executeUpdate();
            ResultSet rs = this.stmC.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                cb.setId(id);
                return cb;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean atualizar(ContaBancaria cb) {
        try {
            this.stmU.setString(1, cb.getNomeTitular());
            this.stmU.setDouble(2, cb.getSaldo());
            this.stmU.setInt(3, cb.getnAgencia());
            this.stmU.setLong(4, cb.getId());
            return this.stmU.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean apagar(long id) {
        try {
            this.stmD.setLong(1, id);
            return this.stmD.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
