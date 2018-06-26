/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author gabriel
 */
public class Sessao {

    private static Sessao instance;

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }

        return instance;
    }

    private Sessao() {

    }

    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public int getUsuarioLogado() {
        return (int) getAttribute("usuarioLogado");
    }

    public void setUsuarioLogado(int usuario) {
        setAttribute("usuarioLogado", usuario);
    }

    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

}
