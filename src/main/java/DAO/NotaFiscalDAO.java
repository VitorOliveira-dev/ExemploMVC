package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.NotaFiscal;
import util.GerenciadorConexao;

public class NotaFiscalDAO {

    public static boolean salvar(NotaFiscal obj) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;

        try {
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão

            conexao = GerenciadorConexao.abrirConexao();
            
            comandoSQL = conexao.prepareStatement("INSERT INTO notafiscal (NumeroNota,ValorNota,Data) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS); //Caso queira retornar o ID

            //ADICIONA OS PARÂMETROS AO COMANDOSQL
            comandoSQL.setInt(1, obj.getNumeroNota());
            comandoSQL.setDouble(2, obj.getValorNota());
            comandoSQL.setDate(3, new java.sql.Date(obj.getData().getTime()));

            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

                ResultSet generatedKeys = comandoSQL.getGeneratedKeys(); //Recupero o ID do cliente
                if (generatedKeys.next()) {
                    obj.setIdNotaFiscal(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID da Nota Fiscal !");
                }
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        }finally{

            //Libero os recursos da memória
            try {
                if(comandoSQL!=null)
                    comandoSQL.close();

                GerenciadorConexao.fecharConexao();

              } catch (SQLException ex) {
             }
        }

        return retorno;
    }

    public static NotaFiscal consultaPorId(int pIdNotaFiscal) {

        NotaFiscal retorno = null;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

           conexao = GerenciadorConexao.abrirConexao();

            comandoSQL = conexao.prepareStatement("SELECT * FROM notafiscal WHERE idNotaFiscal = ?");
            comandoSQL.setInt(1, pIdNotaFiscal);

            rs = comandoSQL.executeQuery();

            while (rs.next()) {
                retorno = new NotaFiscal();
                retorno.setIdNotaFiscal(rs.getInt("idNotaFiscal"));
                retorno.setNumeroNota(rs.getInt("NumeroNota"));
                retorno.setValorNota(rs.getDouble("ValorNota"));
                retorno.setData(rs.getDate("Data"));
            }

        } catch (Exception e) {
            retorno = null;
        }finally{

            //Libero os recursos da memória
            try {
                if(comandoSQL!=null)
                    comandoSQL.close();

                GerenciadorConexao.fecharConexao();

              } catch (SQLException ex) {
             }
        }
        return retorno;
    }

    public static ArrayList<NotaFiscal> listar() {

        ArrayList<NotaFiscal> listaNotas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            comandoSQL = conexao.prepareStatement("SELECT * FROM notafiscal");

            rs = comandoSQL.executeQuery();

            while (rs.next()) {
                NotaFiscal objNF = new NotaFiscal();

                objNF.setIdNotaFiscal(rs.getInt("idNotaFiscal"));
                objNF.setNumeroNota(rs.getInt("NumeroNota"));
                objNF.setValorNota(rs.getDouble("ValorNota"));
                objNF.setData(rs.getDate("Data"));

                listaNotas.add(objNF);
            }

        } catch (Exception e) {
            listaNotas = null;
        }finally{

            //Libero os recursos da memória
            try {
                if(comandoSQL!=null)
                    comandoSQL.close();

                GerenciadorConexao.fecharConexao();

              } catch (SQLException ex) {
             }
        }

        return listaNotas;

    }
    
        public static ArrayList<NotaFiscal> listarPorData(Date pInicio, Date pFim) {

        ArrayList<NotaFiscal> listaNotas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            conexao = GerenciadorConexao.abrirConexao();
            
            comandoSQL = conexao.prepareStatement("SELECT * FROM notafiscal WHERE data BETWEEN ? AND ?");
            comandoSQL.setDate(1, new java.sql.Date(pInicio.getTime()));
            comandoSQL.setDate(2, new java.sql.Date(pFim.getTime()));

            rs = comandoSQL.executeQuery();

            while (rs.next()) {
                NotaFiscal objNF = new NotaFiscal();

                objNF.setIdNotaFiscal(rs.getInt("idNotaFiscal"));
                objNF.setNumeroNota(rs.getInt("NumeroNota"));
                objNF.setValorNota(rs.getDouble("ValorNota"));
                objNF.setData(rs.getDate("Data"));

                listaNotas.add(objNF);
            }

        } catch (Exception e) {
            listaNotas = null;
        }finally{

            //Libero os recursos da memória
            try {
                if(comandoSQL!=null)
                    comandoSQL.close();

                GerenciadorConexao.fecharConexao();

              } catch (SQLException ex) {
             }
        }

        return listaNotas;

    }

    public static boolean atualizar(NotaFiscal obj) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;

        try {
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
           conexao = GerenciadorConexao.abrirConexao();

            comandoSQL = conexao.prepareStatement("UPDATE notafiscal SET numeroNota = ?, valorNota = ? WHERE idNotaFiscal = ? ");
            
            comandoSQL.setInt(1, obj.getNumeroNota());
            comandoSQL.setDouble(2, obj.getValorNota());
            comandoSQL.setInt(3, obj.getIdNotaFiscal());

            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
                
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        }finally{

            //Libero os recursos da memória
            try {
                if(comandoSQL!=null)
                    comandoSQL.close();

                GerenciadorConexao.fecharConexao();

              } catch (SQLException ex) {
             }
        }
        return retorno;
    }
    
    public static boolean excluir(int pIdNotaFiscal) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;

        try {
            
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            conexao = GerenciadorConexao.abrirConexao();
            comandoSQL = conexao.prepareStatement("DELETE FROM notafiscal WHERE idNotafiscal = ?");
            
            comandoSQL.setInt(1, pIdNotaFiscal);

            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
                
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        }finally{

            //Libero os recursos da memória
            try {
                if(comandoSQL!=null)
                    comandoSQL.close();

                GerenciadorConexao.fecharConexao();

              } catch (SQLException ex) {
             }
        }

        return retorno;
    }

}
