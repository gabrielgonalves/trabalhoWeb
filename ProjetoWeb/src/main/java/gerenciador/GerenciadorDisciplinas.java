/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador;

import entidade.Comentario;
import entidade.Disciplina;
import entidade.Topico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Conexao;

/**
 *
 * @author gabriel
 */
public class GerenciadorDisciplinas {

    private static Connection conexao;

    public static void cadastrar(Disciplina disciplina) {
        conexao = Conexao.getConnection();
        String sql = "INSERT INTO disciplina(codigo, nome) VALUES ('" + disciplina.getCodigo() + "', '" + disciplina.getNome() + "');";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterar(Disciplina disciplina) {

    }

    public void excluir(Disciplina disciplina) {

    }

    public static List<Disciplina> listar() {
        List<Disciplina> lista = new ArrayList();
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM disciplina;";
        try {
            ResultSet rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("iddisciplina"));
                disciplina.setCodigo(rs.getString("codigo"));
                disciplina.setNome(rs.getString("nome"));

                lista.add(disciplina);
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Comentario> buscaComentariosTopico(int id) {
        return null;
    }
    
    public static void inserirComentario(Topico topico, Comentario comentario){
        conexao = Conexao.getConnection();
        String sql = "INSERT INTO comentario(comentario, idusuario) VALUES ('" + comentario.getComentario() + "', '" + comentario.getAutor().getId()+"');";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao = Conexao.getConnection();
        sql = "SELECT idcomentario FROM comentario WHERE idusuario = '"+comentario.getAutor().getId()+"' ORDER BY idcomentario desc LIMIT 1;";
        ResultSet rs;
        int id = 0;
        try {
            rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                id = rs.getInt("idcomentario");
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao = Conexao.getConnection();
        sql = "INSERT INTO topico_has_comentario(idtopico, idcomentario) VALUES ('" + topico.getId() + "', '"+id+"');";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cadastrarTopico(Topico topico, Disciplina disciplina){
        conexao = Conexao.getConnection();
        String sql = "INSERT INTO topico(assunto, descricao, idusuario) VALUES ('" + topico.getAssunto() + "', '" + topico.getConteudo()+ "', '"+ topico.getAutor().getId() +"');";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao = Conexao.getConnection();
        sql = "SELECT idtopico FROM topico WHERE idusuario = '"+topico.getAutor().getId()+"' ORDER BY idtopico desc LIMIT 1;";
        ResultSet rs;
        int id = 0;
        try {
            rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                id = rs.getInt("idtopico");
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao = Conexao.getConnection();
        sql = "INSERT INTO disciplina_has_topico(iddisciplina, idtopico) VALUES ('" + disciplina.getId() + "', '"+id+"');";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Topico> buscaTopicosDisciplina(int id) {
        List<Topico> lista = new ArrayList();
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM topico WHERE idtopico IN (SELECT idtopico FROM disciplina_has_topico WHERE iddisciplina = '"+id+"');";
        try {
            ResultSet rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Topico topico = new Topico();
                topico.setId(rs.getInt("idtopico"));
                topico.setAssunto(rs.getString("assunto"));
                topico.setConteudo(rs.getString("descricao"));
                topico.getAutor().setId(rs.getInt("idusuario"));

                lista.add(topico);
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static List<Comentario> buscaComentariosTopico(Topico topico) {
        List<Comentario> lista = new ArrayList();
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM comentario WHERE idcomentario IN (SELECT idcomentario FROM topico_has_comentario WHERE idtopico = '"+topico.getId()+"');";
        try {
            ResultSet rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getInt("idcomentario"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.getAutor().setId(rs.getInt("idusuario"));

                lista.add(comentario);
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
