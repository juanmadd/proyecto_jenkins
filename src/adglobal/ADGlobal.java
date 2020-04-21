/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adglobal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jjgr
 */
public class ADGlobal 
{

   //jFrame nuevoframe=new jFrame();

   /* parámetros de conexión con la base de datos MySql */
   private static final String url = "jdbc:mysql://localhost:3306/login";
   private static final String user = "root";
   private static final String password = "123456";
  
   /*Inserción de datos en cada tabla.
a. Debes realizar un procedimiento almacenado que permita insertar los datos de los personas. Este
procedimiento debe actualizar la tabla cursos incrementado el campo numalum en 1, sin pasarse de
30.
b. Inserta al menos 2 cursos (1º DAM y 2º DAM).
c. Inserta un mínimo de 4 personas en cada curso.
d. Inserta las asignaturas de cada curso.
3.
*/
   
//   public static void procd_alm_inserta_personas(Connection conexion)
//   {
//        try {
//            // inserto 8 personas por curso. 4 de 1º y 4 de 2º
//          String sql="{call inserta_personas(?,?,?)}";//Nombre,direccion,curso
//          CallableStatement ProcAlmacenado = conexion.prepareCall(sql);
//          int filas=0;
//          for (int idcurso=1;idcurso<3;idcurso++)
//          {
//              for (int idalumno=1;idalumno<5;idalumno++)
//              {
//                  ProcAlmacenado.setString(1, "personas "+idalumno); //nombre
//                  ProcAlmacenado.setString(2, "Direccion "+idalumno); //direccion
//                  ProcAlmacenado.setInt(3, idcurso); //curso
//                  ProcAlmacenado.executeUpdate(); //filas afectadas                
//              }            
//          }
//          JOptionPane.showMessageDialog(null, "personas insertados con exito");
//        } catch (SQLException ex) {
////            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
//              JOptionPane.showMessageDialog(null, "Ha ocurrido una SQLException :"+ex);
//        }
//        
//     
//   }
   
//   public static void procd_alm_inserta_mas_de_30_personas(Connection conexion) 
//   {
//       String procd="";
//       
//        try {
//            String sql="{call inserta(?,?,?)}";//Nombre,direccion,curso
//            CallableStatement ProcAlmacenado = conexion.prepareCall(sql);
//           
//            // inserto 31 personas en 1º 
//            for (int i=1;i<31;i++)
//            {
//                   String nombre="Fulanito"+i;
//                   String dir="DirFulanito"+i;
//                   ProcAlmacenado.setString(1, nombre); //nombre
//                   ProcAlmacenado.setString(2, dir); //direccion
//                   ProcAlmacenado.setInt(3, 1); //curso
//                   ProcAlmacenado.execute(); //filas afectadas
//               
//            }
//        } catch (SQLException ex) {
////            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
//              JOptionPane.showMessageDialog(null, "Ha ocurrido una SQLException :"+ex);
//        }
//        
//       
//        
//        
//   }
   
  
   
   public static void consulta_usu(Connection conexion) 
   {
        try {
            //Consulta de los personas de un determinado curso.
            String sql="SELECT * FROM login"; 
            String cadena="";
                
                PreparedStatement pspt=(PreparedStatement)conexion.prepareStatement(sql);
                //pspt.setString(1,cadena);
                ResultSet result=pspt.executeQuery(sql);
             //   System.out.println(result.getRow());
            while (result.next())
            {
               //System.out.println(result.getRow());
               //System.out.println("curso :"+result.getString(1)+" nomcurso :"+result.getString(2)+" numalum :"+result.getString(3));
               cadena=cadena+ "login :"+result.getString(1)+" Password :"+result.getString(2)+"\n";
            }
            JOptionPane.showMessageDialog(null, cadena);
        } catch (SQLException ex) {
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(null, "Ha ocurrido una SQL Exception :"+ex);  
        }
       
   }
   
   
   
   public static void elimina_usu(Connection conexion,String login) 
   {
        try {
            
            String sql1="select login from login where login='"+login+"'";
            String sql2="delete from login where login='"+login+"'";
            PreparedStatement pspt;
            ResultSet result;
            //int numero=0;
            
                pspt=(PreparedStatement)conexion.prepareStatement(sql1);
             //   pspt.setInt(1, login);
                
                result=pspt.executeQuery();
               
                    pspt=(PreparedStatement)conexion.prepareStatement(sql2);
                 //   pspt.setInt(1, login);
                    pspt.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Se ha borrado un usuario");
//            ///Eliminación de un determinado personas.
//            String sql="delete from personas "
//                    + "where personas.DNI = ?";
//            
//                PreparedStatement pspt=(PreparedStatement)conexion.prepareStatement(sql);
//                pspt.setInt(1,codalumno);
//                int filas=pspt.executeUpdate();
//
////                System.out.println("\nFilas actualizadas :"+filas);
//                JOptionPane.showMessageDialog(null,"Filas actualizadas :"+filas); 
        } catch (SQLException ex) {
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido una SQLException :"+ex);
        }
       
   }
   
   
   public static void modifica_usu(Connection conexion, String usu) 
   {
        try {
            //Modificación de los datos de un determinado personas, su nombre y dirección.
           
           String login = JOptionPane.showInputDialog("Introduce el nuevo login: ");
            String passwd = JOptionPane.showInputDialog("Introduce la nueva contrasena: ");
            String sql="update login "
                    + "set login='"+login+"',password='"+passwd+"' "
                    + "where login='"+usu+"'";
           
                PreparedStatement pstm=(PreparedStatement)conexion.prepareStatement(sql);
                
                int filas=pstm.executeUpdate();
//                System.out.println("\nFilas actualizadas :"+filas);
                JOptionPane.showMessageDialog(null,"Filas actualizadas :"+filas); 
        } catch (SQLException ex) {
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido una SQLException :"+ex);
        }
       
       
   }
   
//   public static void vista_total_personas(Connection conexion) 
//   {
//        try {
//            /*
//             Total de personas por curso, mostrando también el nombre del curso, y ordenado de menos a más
//             personas. Crea una vista para ello.
//             */       
//            String sql=
//                    "CREATE OR REPLACE VIEW totales(ncurso,tcursos) as "
//                    + "SELECT nomcurso,cursos.numalum "
//                    + "FROM cursos  "
//                    + "group by cursos.curso" ;                   
//                    
//            String cadena="";
//            
//                Statement sentencia=conexion.createStatement();
//                //sentencia.setString(1,ncurso);
//                sentencia.executeUpdate(sql);
//     //           System.out.print("\nresultado :"+resultado);
//                ResultSet result=sentencia.executeQuery("select * from totales;");
//                while (result.next())
//                {
//     //               System.out.println("\nNºCursos :"+result.getInt(1)+" Nº personas :"+result.getInt(2));
//                    cadena=cadena+"\nNombre curso :"+result.getString(1)+" Numero de cursos:"+result.getInt(2);
//                    //System.out.println("\nNombre curso :"+result.getString(1)+" Numero de cursos:"+result.getInt(2));
//                }
////                System.out.println("resultado :"+cadena);
//                JOptionPane.showMessageDialog(null, cadena);
//        } catch (SQLException ex) {
////           Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
//             JOptionPane.showMessageDialog(null, "Ha ocurrido una SQLException :"+ex);
//        }
//       
//   }
   
   public static void inserta_usu(Connection conexion) 
   {
        try 
        {
            String insertaPersonas = "INSERT INTO login (login,password) VALUES"
                 + "('usuario1', 'contra1'),"
                 + "('usuario2','passw1')";
            Statement sentencia=conexion.createStatement();
            sentencia.execute(insertaPersonas);
            JOptionPane.showMessageDialog(null, "\nUsuarios insertados");
            //System.out.println("\nCursos insertados");
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "\nHa ocurrido una SQLException");
        }
       
            
   }
   
   
   public static void crea_bd(Connection conexion)
   {
        try {
            
            String borra_usu="DROP TABLE IF EXISTS `login` CASCADE";
            String crea_usu="CREATE TABLE `login` (`login` varchar(20) NOT NULL,"
                    +"`password` varchar(20)  NOT NULL"
                  +") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";
//                    + "DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;";
            
//            String borra_asignaturas="DROP TABLE IF EXISTS `asignaturas` CASCADE";
//            String crea_asignaturas="CREATE TABLE `asignaturas` ("
//                                      +"`codasig` int(10) AUTO_INCREMENT NOT NULL,"
//                                      +"`nomasig` char(30) NOT NULL,"
//                                      +"`horas` int(11) DEFAULT NULL,"
//                                      +"PRIMARY KEY (`codasig`)"
//                +") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";
//            
//            String borra_cursos="DROP TABLE IF EXISTS `cursos`";   
//            String crea_cursos="CREATE TABLE `cursos` ("
//                      +"`curso` int(10) NOT NULL COMMENT '		',"
//                      +"`nomcurso` char(30) NOT NULL,"
//                      +"`numalum` int(11) DEFAULT '0',"
//                      +"PRIMARY KEY (`curso`)"
//                +") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";
//            
//            String borra_notas="DROP TABLE IF EXISTS `notas` cascade";
//            String crea_notas="CREATE TABLE `notas` ("
//                                  +"`codalum` int(10) NOT NULL,"
//                                  +"`nota` int(11) DEFAULT NULL,"
//                                  +"`codasig` int(10) NOT NULL,"
//                                  +"PRIMARY KEY (`codalum`,`codasig`),"
//                                  +"KEY `fk_notas_1_idx` (`codalum`),"
//                                  +"KEY `fk_notas_asignaturas1_idx` (`codasig`),"
//                              +"CONSTRAINT `fk_notas_1` FOREIGN KEY (`codalum`) "
//                    + "REFERENCES `personas` (`codalum`) "
//                    + "ON DELETE CASCADE ON UPDATE CASCADE,"
//                  +"CONSTRAINT `fk_notas_asignaturas1` FOREIGN KEY (`codasig`) "
//                    + "REFERENCES `asignaturas` (`codasig`) "
//                    + "ON DELETE CASCADE ON UPDATE CASCADE)"
//                 +"ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";
//           String borra_procd="DROP PROCEDURE IF EXISTS `inserta` ";
//           String crea_procd=""
//+ "CREATE DEFINER=`root`@`localhost`PROCEDURE `inserta`(nombre char(30),direccion char(30),curso int(11)) "
//+ "BEGIN "
//+ "  if ( "
//+ "    select count(DNI) "
//+ "    from personas "
//+ "    where personas.curso=curso "
//+ "     )<30 then "
//   
//+ " insert into personas(`nombre`, `apellidos`,`DNI`) "
//+ " values (nombre,apellidos,DNI); "
//+ " update cursos "
//+ " set cursos.numalum=cursos.numalum+1 "
//+ " where cursos.curso=curso; "
//+ " end if; "
//+ " END ";
           
            Statement sentencia=conexion.createStatement();
            sentencia.execute("SET FOREIGN_KEY_CHECKS=0");
            sentencia.execute(borra_usu);
//            sentencia.execute(borra_asignaturas);
//            sentencia.execute(borra_notas);
//            sentencia.execute(borra_cursos);
            sentencia.execute("SET FOREIGN_KEY_CHECKS=1");
//            sentencia.execute(crea_cursos);
//            sentencia.execute(crea_asignaturas);
            
            sentencia.execute(crea_usu);
            
//            sentencia.execute(crea_notas);
            
//            
//            sentencia.execute(borra_procd);
//            sentencia.execute(crea_procd);
            
        } catch (SQLException ex) {
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Ha ocurrido una SQL Exception :"+ex);
        }
   }
   
   public static void main(String[] args) 
   {
        try {
            Connection conexion = null;
           
            ResultSet result = null;
           
            DatabaseMetaData dbmd;
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
           
            conexion = DriverManager.getConnection(url, user, password);
//            inserta_dos_cursos(conexion);
//            procd_alm_inserta_personas(conexion); //
            inserta_usu(conexion);
//            inserta_asignaturas(conexion);
//            Modifica_tabla_notas(conexion);
//            consulta_lista_notas(conexion,"Bases de Datos");
            consulta_usu(conexion);
//            vista_total_personas(conexion);
//            modifica_alumno(conexion,5,"nuevo_nombre","nueva_direccion");
//            modif_nota_alumno(conexion,2,121,1,8);
//            elimina_alumno(conexion,1);
//            elimina_asignatura(conexion,121);
//            elimina_asignatura(conexion,121);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido una SQLException :"+ex);
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido una ClassNotFoundException :"+ex);
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido una InstancionException :"+ex);
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido una IllegalAccessException :"+ex);
//            Logger.getLogger(ADGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }

   
}   