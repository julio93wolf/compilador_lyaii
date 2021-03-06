/**
 * @author Ramirez García Juana Rubi
 * @author Valle Rodriguez Julio Cesar
 */
package compilador.sintactico;

import compilador.Token;
import java.util.ArrayList;

public class Principal {

    private ArrayList <Token> a_TablaLexico;
    private ArrayList <Token> a_TablaSimbolos;
    private int a_Recorrido;
    private String a_Consola="";
    private boolean a_Error=false;
    
    public Principal(ArrayList <Token> p_TablaLexico,ArrayList <Token> p_TablaSimbolos,int p_Recorrido,String p_Consola){
        a_TablaLexico=p_TablaLexico;
        a_TablaSimbolos=p_TablaSimbolos;
        a_Recorrido=p_Recorrido;
        a_Consola=p_Consola;
        m_start();
    }
    
    private void m_start(){
        Token v_Token = a_TablaLexico.get(a_Recorrido);
        if(v_Token.m_getIdToken()!=10){
            a_Consola+="Error[260]: Sin: No se declaró el método inicio \n";
            a_Consola+="Error en la linea: "+v_Token.m_getNoLinea()+" \n";
            a_Error=true;
        }else{
            a_Recorrido++;
        }
        BloqueCodigo o_bloqueCodigo=new BloqueCodigo(a_TablaLexico,a_TablaSimbolos,a_Recorrido,a_Consola);
        a_Consola=o_bloqueCodigo.m_getConsola();
        a_Recorrido=o_bloqueCodigo.m_getRecorrido();
        a_TablaSimbolos=o_bloqueCodigo.m_getTabla();
        a_TablaLexico=o_bloqueCodigo.m_getLexico();
        if(o_bloqueCodigo.m_getError())
            a_Error=true;
        m_end();
    }
    
    private void m_end(){
        if(a_TablaLexico.size()>a_Recorrido){
            Token v_Token = a_TablaLexico.get(a_Recorrido);
            if(v_Token.m_getIdToken()!=11){
                a_Consola+="Error[260]: Sin: No se declaró el método inicio \n";
                a_Consola+="Error en la linea: "+v_Token.m_getNoLinea()+" \n";
                a_Error=true;
            }
        }else{
            Token v_Token=a_TablaLexico.get(a_Recorrido-1);
            a_Consola+="Error[260]: Sin: No se declaró el método inicio \n";
            a_Consola+="Error en la linea: "+v_Token.m_getNoLinea()+" \n";
            a_Error=true;
        }
    }
    
    public String m_getConsola(){
        return a_Consola;
    }
    
    public int m_getRecorrido(){
        return a_Recorrido;
    }
    
    public boolean m_getError(){
        return a_Error;
    }
    
    public ArrayList <Token> m_getTabla(){
        return a_TablaSimbolos;
    }
    
    public ArrayList <Token> m_getLexico(){
        return a_TablaLexico;
    }
}
