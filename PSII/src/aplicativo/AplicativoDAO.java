package aplicativo;

import dataBase.ConexaoJavaDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AplicativoDAO {

    private PreparedStatement stmCreate;
    private PreparedStatement stmUpdate;
    private PreparedStatement stmDelete;
    private PreparedStatement stmRead;
    private PreparedStatement stmReadId;

    @SuppressWarnings("CallToPrintStackTrace")
    public AplicativoDAO(ConexaoJavaDB conexao) {

        try {
            Connection connect = conexao.getConexao();
            String sqlCreate = "Insert into aplicativo(nome,desenvolvedor, nDownloads) VALUES(?,?,?)";
            String sqlUpdate = "Update aplicativo SET nome =?, desenvolvedor=?, nDownloads=? where id=?";
            String sqlDelete = "Delete from aplicativo where id=?";
            String sqlRead = "Select*from aplicativo";

            String sqlReadId = "Select*from aplicativo where id=?";
            
            this.stmCreate = connect.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
            this.stmUpdate = connect.prepareStatement(sqlUpdate);
            this.stmDelete = connect.prepareStatement(sqlDelete);
            this.stmRead = connect.prepareStatement(sqlRead);
            this.stmReadId = connect.prepareStatement(sqlReadId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Aplicativo> readAll() {
        try {
            ResultSet rs = this.stmRead.executeQuery();
            List<Aplicativo> aplicativos = new ArrayList<>();

            while (rs.next()) {
                Aplicativo aux = new Aplicativo();
                aux.setNome(rs.getString("nome"));
                aux.setDesenvolvedor(rs.getString("desenvolvedor"));
                aux.setnDownloads(rs.getInt("nDownloads"));
                aux.setId(rs.getLong("id"));
                aplicativos.add(aux);
            }
            return aplicativos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Aplicativo> readId(long id) {
        try {

             this.stmReadId.setLong(1, id);
            ResultSet rs = this.stmReadId.executeQuery();
            List<Aplicativo> aplicativos = new ArrayList<>();

             while (rs.next()) {
                Aplicativo aux = new Aplicativo();
                aux.setNome(rs.getString("nome"));
                aux.setDesenvolvedor(rs.getString("desenvolvedor"));
                aux.setnDownloads(rs.getInt("nDownloads"));
                aux.setId(rs.getLong("id"));
                aplicativos.add(aux);
            }
            return aplicativos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Aplicativo create(Aplicativo a) {
        try {
            this.stmCreate.setString(1, a.getNome());
            this.stmCreate.setString(2, a.getDesenvolvedor());
            this.stmCreate.setInt(3, a.getnDownloads());

            this.stmCreate.executeUpdate();
            ResultSet rs = this.stmCreate.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                a.setId(id);
                return a;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean atualizar(Aplicativo a) {
        try {
            this.stmUpdate.setString(1, a.getNome());
            this.stmUpdate.setString(2, a.getDesenvolvedor());
            this.stmUpdate.setInt(3, a.getnDownloads());
            this.stmUpdate.setLong(4, a.getId());
            return this.stmUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean apagar(long id) {
        try {
            this.stmDelete.setLong(1, id);
            return this.stmDelete.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
