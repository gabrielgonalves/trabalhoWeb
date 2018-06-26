/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entidade.Usuario;
import gerenciador.GerenciadorUsuarios;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utilidades.Sessao;

/**
 *
 * @author gabriel
 */
@SessionScoped
@ManagedBean
public class LoginBean {

    private boolean logado;
    private Usuario usuario;

    public LoginBean() {
        logado = false;
        usuario = new Usuario();
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String entrar() {
        logado = true;
        Usuario usu = GerenciadorUsuarios.buscaUsuarioPorLogin(usuario.getLogin());
        if (usuario.getSenha().equals(usu.getSenha())) {
            usuario = usu;
            Sessao.getInstance().setUsuarioLogado(usuario.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login realizado com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro de autenticação!"));

        }
        return "dashboard.xhtml?faces-redirect=true";
    }
    
    public String sair(){
        Sessao.getInstance().encerrarSessao();
        return "index.xhtml?faces-redirect=true";
    }

    public String verificaLogin() {
        return (String) Sessao.getInstance().getAttribute("usuarioLogado");
    }
}
