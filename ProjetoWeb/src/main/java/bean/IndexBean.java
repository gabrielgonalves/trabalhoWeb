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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import utilidades.Sessao;

/**
 *
 * @author gabriel
 */
@ManagedBean
@ViewScoped
public class IndexBean {

    private Usuario usuario;

    public IndexBean() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String cadastrar() {
        GerenciadorUsuarios.cadastrar(usuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastrado com sucesso!"));
        return null;
    }

    

}
