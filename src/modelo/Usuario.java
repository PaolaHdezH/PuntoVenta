/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author pao-o
 */
public class Usuario {
   private String usuario;
   private String password;
   private String palabraSecreta;


    Usuario() {
    }



    public String getUsuario() {
        return usuario;
    }

    

    public String getPassword() {
        return password;
    }

    

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public void setPalabraSecreta(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
    }
   
   public boolean evularUsuario  (String usuario){
       return !usuario.equals("");  
   }
   
   public boolean evularPassword  (String pasword){
       return !pasword.equals("");
       
   }
   
   public void setInformacion(String pasword,String usuario){
       usuario=this.usuario;
       pasword=this.password;
   }
  
    
}
