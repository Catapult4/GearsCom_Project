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

@ManagedBean(name = "beanOrden")
@SessionScoped

public class MbOrden {

   

    public MbOrden() {

       //reinicia la instancia si se ha cerrado la pagina
        if (!FacesContext.getCurrentInstance().isPostback()) {
            FacesContext.getCurrentInstance().responseComplete();
        }

    }



    //Id Pedido
    private int idPedido;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    //Lista que contiene los productos
    List<String[]> temp = new ArrayList<String[]>();

    public List<String[]> getTemp() {
        return temp;
    }

    public void setTemp(List<String[]> temp) {
        this.temp = temp;
    }

    //Empresa que hace el pedido
    private String empresa;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    String fechaPedido;

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    
    private String a;
    ResultSet tabla = null;

    //metodo que trae los pedidos 
    //retorna ResultSet
    public ResultSet getPedidos() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("SELECT p.idPedido, p.FechaPedido, "
                + "c.nombre_Empresa FROM pedidos p INNER JOIN cliente c on "
                + "c.idCliente = p.idCliente where idOrden is null");
        return tabla;

    }//fin getProductos

    //metodo para agregar idPedido arreglo que posteriormente se utilizara para
    //crear orden
    public void agregarPedido() {
        boolean flaggy = true;
        for (String[] temp1 : temp) {
            String pp = (""+idPedido);
            if (temp1[0].equals(pp)) {
                flaggy = false;
            }
        }
        if (flaggy) {
           String[] pedido = new String[3];
        pedido[0] = ("" + idPedido);
        pedido[1] = (empresa);
        pedido[2] = fechaPedido;
        temp.add(pedido);
        flag = true; 
        }
        
        
    }

    /**
     **Metodo para recibir la cantidad de productos y el id desde el Datatable
     * en la fila seleccionada
     *
     */
    public void actionListenerAgregar(ActionEvent e) {

        UIOutput idped = (UIOutput) e.getComponent().findComponent("idped");
        idPedido = (int) idped.getValue();
        UIOutput emp = (UIOutput) e.getComponent().findComponent("empresa");
        empresa = (String) emp.getValue();
        UIOutput fech = (UIOutput) e.getComponent().findComponent("fecha");
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaPedid = (Date) fech.getValue();
        fechaPedido = fecha.format(fechaPedid);
            
        
        
        
        
    }

    //Metodos para cambiar la cantidad  del producto o eliminarlo del carrito
    public void actionListenerRetirar(ActionEvent e) {

        UIOutput idped = (UIOutput) e.getComponent().findComponent("idPedi");
        a = (String) idped.getValue();

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
            } else if (temp.size() ==1) {
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

    Date fechaInicio;
    int idOrden;
    public Date getFechaInicio(){
        return fechaInicio;
    }
    
    String fechaOr;
    public String getFechaOr(){
        return fechaOr;
    }
    String fechaIn;
    public String getFechaIn(){
        return fechaIn;
    }
    String fechaEn;
    public String getFechaEn(){
        return fechaEn;
    }
    
    
    public void setFechaInicio(Date fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    
    public void crearOrden() {
        try {
            conexion cone = new conexion();
            Statement senten = null;
            senten= cone.getConnection().createStatement();
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaIn = fecha.format(fechaInicio);
            Date fechaOrden = new Date();
            fechaOr = fecha.format(fechaOrden);
            fechaEn = "0000-00-00";
            String sql = "insert into ordenproduccion (FechaOrden, FechaInicio, FechaEntrega, TotalMateria) "
                    + "values ('"+fechaOr+"', '"+fechaIn+"', '"+fechaEn+"', 0)";
            senten.executeUpdate(sql);
            ResultSet tem = senten.executeQuery("select max(idOrden) as idO from ordenproduccion");
            while(tem.next()) {
                System.out.println("correcto");
               idOrden = tem.getInt("idO"); 
                System.out.println("1");
            }
            System.out.println("2");
            for (String[] item : temp) {
                System.out.println("3");
                String update="update pedidos set idOrden="+idOrden+" where idPedido = "+item[0]+"";
                System.out.println(update);
                senten.executeUpdate(update);
                System.out.println("4");
            }
            
            flag3 = true;
        } catch (Exception e) {
        }
        
     
        
        
        
    }
    
           ResultSet productos = null;

           //List<String[]> productos;
    //metodo que trae los productos asociados a la orden de produccion
    //retorna ResultSet
    public ResultSet getProductos() throws SQLException {

        conexion cone = new conexion();
        Statement Sentencias = null;
        
        Sentencias = cone.getConnection().createStatement();
        String qu = "select p.Descripcion, pp.Cantidad, m.Nombre "
                + "from productos p inner join materiaprima m on m.idMateriaPrima = "
                + "p.idMateria inner join productopedido pp on p.idProducto = pp.idProducto "
                + "inner join pedidos pe on pe.idPedido = pp.idPedido inner join ordenproduccion "
                + "op on op.idOrden = pe.idOrden where op.idOrden = "+idOrden;
        System.out.println(qu);
        productos = Sentencias.executeQuery(qu);
        System.out.println("qu correcto");
//        while(tabla.next()){
//            String[] prod = new String[3];
//            prod[1] = 
//        }
        return productos;

    }//fin getProductos
    
 

}//fin ManagedBean MbPedido

