package sbp.ent;

import app.sql.conexion;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped

public class MbLogin {

    private String usuario;
    private String contrasenia;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext facesContext;
    private FacesMessage facesMessage;
    private String TipoUsuario;
    private String Referencia;
    private String Nombrerol;

    private String dato;

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public MbLogin() {
        facesContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.TipoUsuario = tipoUsuario;
    }

    public String sesion() throws SQLException {

        if (loginAction_Administrador()) {
            return "index";

        }
        if (loginAction_Empleado()) {

            return "index";
        }

        if (loginAction_Cliente()) {
            return "index";

        }
        if (loginAction_GerenteMercadeo()) {
            return "index";

        }
        if (loginAction_GerenteProduccion()) {
            return "index";

        } else {
            FacesContext.getCurrentInstance().addMessage("msj", new FacesMessage("Datos  Incorrectos"));
            return "incorrecto";
        }

    }

    private boolean loginAction_Administrador() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        ResultSet tabla = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("select * from usuario where nombreUsuario='" + usuario + "'and Contraseña='" + contrasenia + "' and idRol=4");
        while (tabla.next()) {
            TipoUsuario = tabla.getString("idRol");
            nrol(Integer.parseInt(TipoUsuario));
            return true;
        }
        return false;

    }

    private boolean loginAction_Empleado() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        ResultSet tabla = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("select * from usuario where nombreUsuario='" + usuario + "'and Contraseña='" + contrasenia + "'and idRol=1");
        while (tabla.next()) {
            TipoUsuario = tabla.getString("idRol");
            nrol(Integer.parseInt(TipoUsuario));
            return true;
        }
        return false;

    }

    public String getNombrerol() {
        return Nombrerol;
    }

    public void setNombrerol(String Nombrerol) {
        this.Nombrerol = Nombrerol;
    }

    private String nrol(int idrol) {
        switch (idrol) {
            case 1:
                Nombrerol = "Empleado";
                break;
            case 2:
                Nombrerol = "Gerente Mercadeo";
                break;
            case 3:
                Nombrerol = "Gerente Produccion";
                break;
            case 4:
                Nombrerol = "Administrador";
                break;
            case 5:
                Nombrerol = "Cliente";
                break;
            default:
                throw new AssertionError();
        }

        return Nombrerol;

    }

    private boolean loginAction_Cliente() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        ResultSet tabla = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("select * from usuario where nombreUsuario='" + usuario + "'and Contraseña='" + contrasenia + "'and idRol=5");
        while (tabla.next()) {
            TipoUsuario = tabla.getString("idRol");
            nrol(Integer.parseInt(TipoUsuario));
            return true;
        }
        return false;

    }

    private boolean loginAction_GerenteMercadeo() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        ResultSet tabla = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("select * from usuario where nombreUsuario='" + usuario + "'and Contraseña='" + contrasenia + "'and idRol=2");
        while (tabla.next()) {
            TipoUsuario = tabla.getString("idRol");
            nrol(Integer.parseInt(TipoUsuario));
            return true;
        }
        return false;

    }

    private boolean loginAction_GerenteProduccion() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        ResultSet tabla = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("select * from usuario where nombreUsuario='" + usuario + "'and Contraseña='" + contrasenia + "'and idRol=3");
        while (tabla.next()) {
            TipoUsuario = tabla.getString("idRol");
            nrol(Integer.parseInt(TipoUsuario));
            return true;
        }
        return false;

    }



    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

     public String getResultado(){
     if (usuario != null && contrasenia != null) {
      return "<p style=\"background-color:yellow;width:300px;"
              + "padding:5px\">Nombre: " + getUsuario()+ "<br/>Contraseña: "
              + getContrasenia()+"</p>";
    } else {
      return ""; 
    }
}
}
