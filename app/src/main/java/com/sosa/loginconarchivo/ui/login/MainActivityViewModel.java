package com.sosa.loginconarchivo.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sosa.loginconarchivo.entidad.Usuario;
import com.sosa.loginconarchivo.request.ApiClient;
import com.sosa.loginconarchivo.ui.registro.Editar;
import com.sosa.loginconarchivo.ui.registro.Registro;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Integer> avisoVisibilityMutable;

    private MutableLiveData<String> avisoMutable;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<Integer> getAvisoVisibility() {
        if (avisoVisibilityMutable == null) {
            avisoVisibilityMutable = new MutableLiveData<>();
        }
        return avisoVisibilityMutable;
    }

    public LiveData<String> getAviso() {
        if (avisoMutable == null) {
            avisoMutable = new MutableLiveData<>();
        }
        return avisoMutable;
    }

    public void Login(String mail, String pass) {
        Usuario u = ApiClient.login(context, mail, pass);
        if (u == null) {
            avisoVisibilityMutable.setValue(View.VISIBLE);
            avisoMutable.setValue("Email o usuario incorrecto");
        } else {
            Intent intent = new Intent(context, Editar.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void Registrarse() {
        Intent intent = new Intent(context, Registro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
