/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbp.ent;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped

public class MbSession {

    private String usuario;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;

    public MbSession() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null) {
            usuario = httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
        }
    }

    public String testAction() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        return "Login.xhtml";
        

    }

//    public String testAction() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.invalidateSession();
//        ec.redirect("Login.xhtml");
//
//        return null;
//       
//    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
