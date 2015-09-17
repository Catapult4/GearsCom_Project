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

@ManagedBean(name = "beanCompletar")
@SessionScoped

public class MbCompletar {

   

    public MbCompletar() {

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
    public ResultSet getOrdenes() throws SQLException {
        conexion cone = new conexion();
        Statement Sentencias = null;
        Sentencias = cone.getConnection().createStatement();
        tabla = Sentencias.executeQuery("SELECT idOrden, FechaOrden, FechaInicio FROM ordenproduccion where fechaEntrega = '0000-00-00'");
        return tabla;

    }//fin getProductos

    //metodo para agregar idPedido arreglo que posteriormente se utilizara para
    //crear orden
    public void agregarOrden() {
        boolean flaggy = true;
        for (String[] temp1 : temp) {
            String pp = (""+idOrden);
            if (temp1[0].equals(pp)) {
                flaggy = false;
            }
        }
        if (flaggy) {
           String[] ordenes = new String[3];
        ordenes[0] = ("" + idOrden);
        ordenes[1] = (fechaOr);
        ordenes[2] = fechaIn;
        temp.add(ordenes);
        flag = true; 
        }
        
        
    }

    /**
     **Metodo para recibir la cantidad de productos y el id desde el Datatable
     * en la fila seleccionada
     *
     */
    public void actionListenerAgregar(ActionEvent e) {

        UIOutput idord = (UIOutput) e.getComponent().findComponent("idord");
        idOrden = (int) idord.getValue();
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        UIOutput fechaO = (UIOutput) e.getComponent().findComponent("fechaOr");
        Date fechaOrd = (Date) fechaO.getValue();
        fechaOr = fecha.format(fechaOrd);
        UIOutput fechaI = (UIOutput) e.getComponent().findComponent("fechaIn");
        Date fechaPedid = (Date) fechaI.getValue();
        fechaIn = fecha.format(fechaPedid);
            
        
        
        
        
    }

    //Metodos para cambiar la cantidad  del producto o eliminarlo del carrito
    public void actionListenerRetirar(ActionEvent e) {

        UIOutput id = (UIOutput) e.getComponent().findComponent("idOrde");
        a = (String) id.getValue();

    }
    

    public void retirarOrden() {
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

    public void cerrarOrd() {
        flag2 = false;
    }

    public void cerrarCarro() {
        flag = false;
        flag2 = flag;
    }

    Date fechaFin;
    int idOrden;
    public Date getFechaFin(){
        return fechaFin;
    }
    
    String fechaOr;
    public String getFechaOr(){
        return fechaOr;
    }
    String fechaIn;
    public String getFechaIn(){
        return fechaIn;
    
   }
   
    String fechaFinal;
    public String getFechaFinal(){
        return fechaFinal;
    }
    public void setFechaFinal(String fechaFinal){
        this.fechaFinal = fechaFinal;
    }
    
    Date fechaF;
    public Date getFechaF(){
        return fechaF;
    }
    
    public void setFechaF(Date fechaF){
        this.fechaF = fechaF;
    }
    
    public void setFechaFin(Date fechaFin){
        this.fechaFin = fechaFin;
    }
    
    public void cerrarOrden() {
        try {
            conexion cone = new conexion();
            Statement senten = null;
            senten= cone.getConnection().createStatement();
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaFinal = fecha.format(fechaF);
            System.out.println("2");
            for (String[] item : temp) {
                System.out.println("3");
                String update="update ordenproduccion set FechaEntrega='"+fechaFinal+"' where idOrden = "+item[0]+"";
                System.out.println(update);
                senten.executeUpdate(update);
                System.out.println("4");
                String envio = "update pedidos set FechaEnvio = ´"+fechaFinal+"´ where idOrden = "+item[0]+"";
                senten.executeUpdate(envio);
                String prod = "update productos p inner join productopedido pp on p.idProducto = pp.idProducto "
                        + "inner join pedidos pe on pp.idPedido = pe.idPedido inner join ordenproduccion op on "
                        + "op.idOrden = pe.idOrden set p.Cantidad = pp.Cantidad where op.idOrden = "+item[0]+"";
            }
            
            flag3 = true;
        } catch (Exception e) {
        }
        
     
        
        
        
    }
    
           
    //metodo que trae los productos asociados a la orden de produccion
    //retorna ResultSet


}//fin ManagedBean MbPedido

