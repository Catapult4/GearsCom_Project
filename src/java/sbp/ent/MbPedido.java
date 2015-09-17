package sbp.ent;

import app.sql.conexion;
import folderdb.Cliente;
import folderdb.Pedidos;
import folderdb.Transportadora;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.*;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

@ManagedBean(name = "beanPedido")
@SessionScoped

public class MbPedido {

    private ArrayList<SelectItem> selectitem = new ArrayList<SelectItem>();
    private ArrayList<SelectItem> numeros = new ArrayList<SelectItem>();

    public ArrayList<SelectItem> getSelectitem() {
        return selectitem;
    }

    public MbPedido() {

        selectitem.add(new SelectItem("Abrazadera"));
        selectitem.add(new SelectItem("Acople"));
        selectitem.add(new SelectItem("Pieza"));
        selectitem.add(new SelectItem("Tap"));
        selectitem.add(new SelectItem("T"));
        selectitem.add(new SelectItem("Union"));
        selectitem.add(new SelectItem("Universal"));
        selectitem.add(new SelectItem("Valvula"));
        selectitem.add(new SelectItem("Y"));
        //reinicia la instancia si se ha cerrado la pagina
        if (!FacesContext.getCurrentInstance().isPostback()) {
            FacesContext.getCurrentInstance().responseComplete();
        }

    }

    //categoria de los productos
    public String categoria = null;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    //cantidad del producto
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cant) {
        if (cant != 0) {
            this.cantidad = cant;
        }
    }

    //Id Producto
    private int idProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    //Lista que contiene los productos
    List<String[]> temp = new ArrayList<String[]>();

    public List<String[]> getTemp() {
        return temp;
    }

    public void setTemp(List<String[]> temp) {
        this.temp = temp;
    }

    //Descripcion de los productos
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Flag: booleano que controla el rendered del carro de compras
    private boolean flag = false;

    public boolean getFlag() {
        return flag;
    }

    //Flag2: booleano que controla parcialmente el rendered de la lista de productos
    private boolean flag2 = true;

    public boolean getFlag2() {
        return flag2;
    }
    
    private boolean flag3 = false;

    public boolean getFlag3() {
        return flag3;
    }

    private String a;
    ResultSet tabla = null;

    //metodo que trae los productos segun categorias
    //retorna ResultSet
    public ResultSet getProductos() throws SQLException {

        conexion cone = new conexion();
        Statement Sentencias = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("SELECT p.idProducto, p.Referencia, m.Nombre, p.Descripcion FROM productos p INNER JOIN materiaprima m on m.idMateriaPrima = p.idMateria WHERE Referencia like '" + this.categoria + "%'");
        return tabla;

    }//fin getProductos

    //metodo para agregar idProducto y cantidad al arreglo que posteriormente se utilizara para
    //crear producto
    public void agregarProducto() {
        if (cantidad!=0) {
           
        String[] producto = new String[3];
        producto[0] = ("" + idProducto);
        producto[1] = ("" + cantidad);
        producto[2] = descripcion;
        temp.add(producto);

        cantidad = 0;
        flag = true; 
        }
    }
     /**
     **Metodo para recibir la cantidad de productos y el id desde el Datatable
     * en la fila seleccionada
     */
    public void actionListenerAgregar(ActionEvent e) {

        UIInput cant = (UIInput) e.getComponent().findComponent("cantidadP");
        cantidad = (int) cant.getValue();

        UIOutput idpro = (UIOutput) e.getComponent().findComponent("idpro");
        idProducto = (int) idpro.getValue();

        UIOutput descrip = (UIOutput) e.getComponent().findComponent("descripcion");
        descripcion = (String) descrip.getValue();
        
    }

    //Metodos para cambiar la cantidad  del producto o eliminarlo del carrito
    public void actionListenerRetirar(ActionEvent e) {

        UIOutput idprod = (UIOutput) e.getComponent().findComponent("idprod");
        a = (String) idprod.getValue();

    }

    public void retirarProducto() {
        int i = 0;
        try {
            if (temp.size() != 1) {
                for (String[] item : temp) {

                    if (item[0].equals(a)) {
                        temp.remove(i);
                    } else {
                        i++;
                    }
                }
            } else {
                temp = new ArrayList<String[]>();
                flag2 = true;
                flag = false;
            }
        } catch (Exception e) {

        }

    }

    public void cerrarProd() {
        flag2 = false;
    }

    public void cerrarCarro() {
        flag = false;
        flag2 = flag;
    }

    Pedidos pedido = new Pedidos();
    int idPedido;
    Date fechaPedido = new Date();
    Date fechaEnvio = new Date();
    String nomUsuario;
    
    Transportadora idTransportadora;
    int idCliente;

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Transportadora getIdTransportadora() {
        return idTransportadora;
    }

    public void setIdTransportadora(Transportadora idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    String fechaP;
    String fechaE;
    
    public String getFechaP(){
        return fechaP;
    }
    public String getFechaE(){
        return fechaE;
    }

    
    public void crearPedido() {
        try {
            //Conexion con BD
            conexion cone = new conexion();
            Statement senten = null;
            senten= cone.getConnection().createStatement();
            //idCliente
            String nomUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser");
            String q = "select c.idCliente as idC from usuario u inner join cliente c on c.idUsuario = u.idUsuario where u.nombreUsuario="+nomUser;
            ResultSet us = senten.executeQuery(q);
            while(us.next()){
                idCliente = us.getInt("idC");
            }
            
            //Crear Pedido
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaP = fecha.format(fechaPedido);
            fechaE = fecha.format(fechaEnvio);
            String sql = "insert into Pedidos(fechaPedido, fechaEnvio, idTransportadora, idCliente) values "
                   + "('"+fechaP+"','"+fechaE+"',"+idTransportadora.getIdTransportadora()+","+idCliente+")";
            System.out.println(sql);
            senten.executeUpdate(sql);
            System.out.println("1");
            //insertar Producto
            ResultSet tem = senten.executeQuery("select max(idPedido) as idP from Pedidos");
            while(tem.next()) {
               idPedido = tem.getInt("idP"); 
            }
            for (String[] item : temp) {
                String inserta="insert into productopedido(idPedido, idProducto, Cantidad) values "
                        + " ("+idPedido+", "+item[0]+", "+item[1]+")";
                senten.executeUpdate(inserta);
            }
            
            
            flag3 = true;
            
        } catch (Exception e) {
        }
         }
        public void usuarioListener(ActionEvent event){
        String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser");
            System.out.println(idUsuario);
  }
        
     
        
   

}//fin ManagedBean MbPedido

