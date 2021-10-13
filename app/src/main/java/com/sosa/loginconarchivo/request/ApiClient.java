package com.sosa.loginconarchivo.request;

import android.content.Context;
import android.widget.Toast;

import com.sosa.loginconarchivo.entidad.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {


    public static void guardar(Context context, Usuario usuario){
        File archivo=new File(context.getFilesDir(),"datos.dat");

        try {
            //Nodo
            FileOutputStream fo = new FileOutputStream(archivo, false);
            BufferedOutputStream buff = new BufferedOutputStream(fo);
            ObjectOutputStream output = new ObjectOutputStream(buff);
            output.writeObject(usuario);
            output.close();


        }catch (FileNotFoundException ex){

        }catch (IOException io){

        }
    }
    public static Usuario leer(Context context){
        File archivo=new File(context.getFilesDir(),"datos.dat");
        Usuario usuario = null;
        try {

            //Read from the stored file
            FileInputStream is = new FileInputStream(archivo);
            BufferedInputStream buff = new BufferedInputStream(is);
            ObjectInputStream input = new ObjectInputStream(buff);

                usuario =  (Usuario) input.readObject();




            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public static Usuario login(Context context, String mail, String pass){
        Usuario usuario = null;
        Usuario usu = leer(context);

         if(mail !=null && pass != null) {
             if (mail.equals(usu.getMail()) && pass.equals(usu.getPassword())) {
                 usuario = usu;
             }
         }


        if(usuario == null){
            Toast.makeText(context.getApplicationContext(), "Datos Invalidos", Toast.LENGTH_LONG).show();
        }
        return usuario;

    }
}
