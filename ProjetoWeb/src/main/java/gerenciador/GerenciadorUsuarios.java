/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador;

import entidade.Disciplina;
import entidade.TipoUsuario;
import entidade.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Conexao;

/**
 *
 * @author gabriel
 */
public class GerenciadorUsuarios {

    private static Connection conexao;

    public static void cadastrar(Usuario usuario) {
        conexao = Conexao.getConnection();
        String sql = "INSERT INTO usuario(nome, email, login, senha, idtipousuario) VALUES ('" + usuario.getNome() + "', '" + usuario.getEmail() + "', '" + usuario.getLogin() + "', '" + usuario.getSenha() + "',1);";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(Usuario usuario) {

    }

    public void excluir(Usuario usuario) {

    }

    public List<Usuario> listar() {
        return null;
    }

    public static List<Disciplina> buscaDisciplinasUsuario(int id) {
        List<Disciplina> lista = new ArrayList();
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM disciplina WHERE iddisciplina IN (SELECT iddisciplina FROM usuario_has_disciplina WHERE idusuario = '" + id + "');";
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

    public static List<Disciplina> buscaDisciplinasUsuarioNaoCadastrado(int id) {
        List<Disciplina> lista = new ArrayList();
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM disciplina WHERE iddisciplina NOT IN (SELECT iddisciplina FROM usuario_has_disciplina WHERE idusuario = '" + id + "');";
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

    public static void inserirDisciplinaUsuario(Usuario usuario, Disciplina disciplina) {
        conexao = Conexao.getConnection();
        String sql = "INSERT INTO usuario_has_disciplina(idusuario, iddisciplina) VALUES ('" + usuario.getId() + "', '" + disciplina.getId() + "');";
        try {
            System.out.println(sql);
            conexao.prepareStatement(sql).executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Usuario buscaUsuarioPorId(int id) {
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM usuario WHERE idusuario = '" + id + "';";
        Usuario usuario = new Usuario();
        try {
            ResultSet rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(TipoUsuario.values()[rs.getInt("idtipousuario")-1]);
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public static Usuario buscaUsuarioPorLogin(String login) {
        conexao = Conexao.getConnection();
        String sql = "SELECT * FROM usuario WHERE login = '" + login + "';";
        Usuario usuario = new Usuario();
        try {
            ResultSet rs = conexao.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(TipoUsuario.values()[rs.getInt("idtipousuario")-1]);
            }
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}
