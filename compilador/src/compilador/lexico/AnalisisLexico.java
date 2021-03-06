/**
 * @author Ramirez García Juana Rubi
 * @author Valle Rodriguez Julio Cesar
 */
package compilador.lexico;

import compilador.Token;
import java.awt.Color;
import java.util.ArrayList;

public class AnalisisLexico {
    
    private String a_codFuente;
    private String a_codFuenteHTML="";
    private String a_consola="";
    private int a_Linea=1;
    private ArrayList<Token> a_TablaDeSimbolos = new ArrayList<Token>();
    private ArrayList<Token> a_TablaLexico = new ArrayList<Token>();
    private boolean a_bdLexico=true;
    
    private StringBuilder sb_comentarios = new StringBuilder();
    
    
    public AnalisisLexico(String p_codFuente){
        a_codFuente=p_codFuente;
        m_anaLexico(a_codFuente);
        if(a_TablaDeSimbolos.size()==0){
            a_consola+="Error [120] Lex: No se encontró ningún lexema\n";
            a_bdLexico=false;
        }
    }
    
    private void m_anaLexico(String p_codFuente){
        String v_codFuente=p_codFuente;
        a_bdLexico=true;                                                    
        a_Linea=1;
        if(!"".equals(v_codFuente)){
            while(!"".equals(v_codFuente)){                                          
                v_codFuente=m_buscaLexema(v_codFuente);
            }
        }
        else{
            a_consola+="Error [110] Lex: No hay código fuente \n";
            a_consola+="Error en la linea: "+a_Linea+"\n";
            a_bdLexico=false;
        }
    }
    
    private String m_SaltoLinea_Espacio(String p_codFuente){
        String v_codFuente=p_codFuente;
        boolean v_bandera=true;
        while(!"".equals(v_codFuente)&&v_bandera){                                    
            if(v_codFuente.charAt(0)==32){
                v_bandera=true;
                v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                a_codFuenteHTML+="&nbsp;";
            }else{
                if(v_codFuente.charAt(0)==10){
                    v_bandera=true;
                    a_Linea++;
                    v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                    a_codFuenteHTML+="<br>";
                }else{
                    if(v_codFuente.charAt(0)==' '){
                        v_bandera=true;
                        v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                        a_codFuenteHTML+="&nbsp;";
                    }else{
                        if(v_codFuente.charAt(0)=='\t'){
                            v_bandera=true;
                            v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                            a_codFuenteHTML+="&#09;";
                        }else{
                            v_bandera=false;
                        }
                    }
                }
            }
        }
        return v_codFuente;
    }
    
    private String m_Comentario(String p_codFuente){
        String v_codFuente=p_codFuente;
        int v_contador=0;
        boolean v_bandera=true;
        if(v_codFuente.charAt(0)=='#'){
            a_codFuenteHTML+="<font color=#2b5ba8>";
            while(!"".equals(v_codFuente) && v_bandera){
                 if(v_codFuente.charAt(0)==10){
                     v_bandera=false;
                     a_codFuenteHTML+=v_codFuente.charAt(0);
                     v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                     
                 }else{
                    if(v_codFuente.charAt(0)==32){
                        a_codFuenteHTML+="&nbsp;";
                    }else{
                        if(v_codFuente.charAt(0)=='\t'){
                            a_codFuenteHTML+="&#09;";
                        }else{
                            a_codFuenteHTML+=v_codFuente.charAt(0);
                        }
                    }
                     v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                 }
                 v_contador++;
            }
            a_Linea++;
            a_codFuenteHTML+="</font><br>";
        }
        return v_codFuente;
    }
    
    private String m_buscaLexema(String p_codFuente){
        String v_codFuente=p_codFuente;
        int v_Recorrido=0,v_Indice=0;                                           // Variables de control para el recorrido del codigo fuente
        boolean v_Inserta=false;                                                // Bandera para control de insercion de token
        
        /***************  Saltos de Linea y Espacios  *************************/
        if(!"".equals(v_codFuente)){
            v_codFuente=m_SaltoLinea_Espacio(v_codFuente);
        }
        
        /***************  Comentarios  ****************************************/
        
        if(!"".equals(v_codFuente)){
            String v_Tem= m_Comentario(v_codFuente);
            if(v_codFuente!=v_Tem){
                v_codFuente=v_Tem;
                v_Inserta=true;
                v_Indice=0;
            }
        }
        
        /*********************  Palabras Reservadas  **************************/
        if(!"".equals(v_codFuente)&&v_Indice==0){
            PalabrasReservadas v_palabrasReservadas=new PalabrasReservadas();   // Llama a la clase PalabrasReservadas para detectar palabras reservadas en el código fuente
            v_Recorrido = v_palabrasReservadas.getPalabrasReservadas(v_codFuente);// Manda llamar el metodo getPalabrasReservadas para regresar el numero de caracteres que componen a la palabra reservada
            if(0!=v_Recorrido){                                                 // Compara si recorrido del codigo fuente es diferente de 0
                m_addToken(v_codFuente.substring(0,v_Recorrido),3,a_Linea,v_palabrasReservadas.m_getID());             // Si el recorrido es diferente de 0 encontro una palabra reservada y añade el token a la tabla de simbolos
                a_codFuenteHTML+="<font color=#b71939>"+v_codFuente.substring(0,v_Recorrido)+"</font>";
                v_Inserta=true;                                                 // Cambia la bandera de control de insercion a cierto
                v_Indice=v_Recorrido;                                           // Sustrae el recorrigo del codigo fuente y lo guarda en v_Indice
            }
        }
        
        /********************  Identificadores  *******************************/
        if(!"".equals(v_codFuente)&&v_Indice==0){        
            Identificadores v_Ident=new Identificadores();                      // Llama a la clase Identificadores para detectar los tipos de datos en el código fuente
            v_Recorrido = v_Ident.getIndentificador(v_codFuente);               // Manda llamar el método getIdentificadores para regresar el número de carácteres que componen al tipo de dato
            if(0!=v_Recorrido){                                                 // Compara si el recorrido del código fuente es diferente de 0
                if(v_Recorrido>0){
                    m_addToken(v_codFuente.substring(0,v_Recorrido),4,a_Linea,v_Ident.m_getID());             // Si el recorrido es mayor de 0 encontro un identificador y añade el token a la tabla de simbolos
                    a_codFuenteHTML+="<font color=#026311>"+v_codFuente.substring(0,v_Recorrido)+"</font>";
                    v_Inserta=true;                                                 // Cambia la bandera de control de insercion a cierto
                    v_Indice=v_Recorrido;                                           // Sustrae el recorrigo del codigo fuente y lo guarda en v_Indice
                }else{
                    v_Indice=m_errorSimbolo(v_codFuente,0);
                    a_consola+="Error [130] Lex: Posible identificador declarado o valor numérico incorrectamente: ' "+v_codFuente.charAt(v_Indice)+" '\n";
                    a_consola+="Error en la linea: "+a_Linea+"\n";
                    
                    v_Indice=m_errorLexema(v_codFuente,0);                  // Si el recorrido es menor de 0 indica un error en los tipos de datos cadena
                    a_codFuenteHTML+="<font color=color=#6f7077>"+v_codFuente.substring(0,v_Indice)+"</font>";
                    a_consola+="Error [130] Lex: Posible identificador declarado o valor numérico incorrectamente: ' "+v_codFuente.substring(0,v_Indice)+" '\n";
                    a_consola+="Error en la linea: "+a_Linea+"\n";
                    v_Inserta=true;                                             // Cambia la bandera de control de insercion a cierto
                    a_bdLexico=false;                                           // Cambia la bandera de analsis léxico a falso
                }
            }
        }
        
        /********************  Constantes  ************************************/
        if(!"".equals(v_codFuente)&&v_Indice==0){
            Constantes v_Constantes=new Constantes();                           // Llama a la clase Constantes para detectar los tipos de datos en el código fuente
            v_Recorrido = v_Constantes.getConstante(v_codFuente);                   // Manda llamar el método getConstante para regresar el número de carácteres que componen al tipo de dato
            if(0!=v_Recorrido){                                                 // Compara si recorrido del código fuente es diferente de 0
                if(v_Recorrido>0){                                              // Compara si el recorrido del código fue mayor a 0
                    m_addToken(v_codFuente.substring(0,v_Recorrido),5,a_Linea,v_Constantes.m_getID());         // Si el recorrido es mayor de 0 encontro un tipo de datos y añade el token a la tabla de simbolos
                    a_codFuenteHTML+="<font color=#d18c04>"+v_codFuente.substring(0,v_Recorrido)+"</font>";
                    v_Inserta=true;                                             // Cambia la bandera de control de insercion a cierto
                    v_Indice=v_Recorrido;                                       // Sustrae el recorrigo del codigo fuente y lo guarda en v_Indice
                }
                if(v_Recorrido==-1){
                    v_Indice=m_errorLexema(v_codFuente,0);                  // Si el recorrido es menor de 0 indica un error en los tipos de datos cadena
                    a_consola+="Error [140] Lex: Lexema imposible de clasificar: ' "+v_codFuente.substring(0,v_Indice)+" '\n";
                    a_consola+="Error en la linea: "+a_Linea+"\n";
                    a_codFuenteHTML+="<font color=color=#6f7077>"+v_codFuente.substring(0,v_Indice)+"</font>";
                    v_Inserta=true;                                             // Cambia la bandera de control de insercion a cierto
                    a_bdLexico=false;                                           // Cambia la bandera de analsis léxico a falso
                }
                if(v_Recorrido==-2){
                    v_Indice=m_errorLexema(v_codFuente,0);                  // Si el recorrido es menor de 0 indica un error en los tipos de datos cadena
                    a_consola+="Error [140] Lex: Lexema imposible de clasificar: ' "+p_codFuente.substring(0,v_Indice)+" '\n";
                    a_consola+="Error en la linea: "+a_Linea+"\n";
                    a_codFuenteHTML+="<font color=color=#6f7077>"+v_codFuente.substring(0,v_Indice)+"</font>";
                    v_Inserta=true;                                             // Cambia la bandera de control de insercion a cierto
                    a_bdLexico=false;                                           // Cambia la bandera de analsis léxico a falso
                }
            }              
        }
        
        /********************  Operadores  ************************************/
        if(!"".equals(v_codFuente)&&v_Indice==0){
            Operadores v_Operadores=new Operadores();                           // Llama a la clase Operadores para detectar operadores en el código fuente
            v_Recorrido = v_Operadores.getOperadores(v_codFuente);              // Manda llamar el método getOperadores para regresar el número de caráceres que componen al operador
            if(0!=v_Recorrido){                                                 // Compara si recorrido del codigo fuente es diferente de 0
                m_addToken(v_codFuente.substring(0,v_Recorrido),2,a_Linea,v_Operadores.m_getID());             // Si el recorrido es diferente de 0 encontro un operador y añade el token a la tabla de simbolos
                String Operador= v_codFuente.substring(0,v_Recorrido);
                System.out.println(Operador);
                if(Operador.equals("<")){
                    a_codFuenteHTML+="<font color=#000000>&lt;</font>";
                }else{
                    if(Operador.equals(">")){
                        a_codFuenteHTML+="<font color=#000000>&gt;</font>";
                    }else{
                        if(Operador.equals("<=")){
                            a_codFuenteHTML+="<font color=#000000>&lt;=</font>";
                        }else{
                            if(Operador.equals(">=")){
                                a_codFuenteHTML+="<font color=#000000>&gt;=</font>";
                            }else{
                                a_codFuenteHTML+="<font color=#000000>"+v_codFuente.substring(0,v_Recorrido)+"</font>";
                            }
                        }
                    }
                }
                
                v_Inserta=true;                                                 // Cambia la bandera de control de insercion a cierto
                v_Indice=v_Recorrido;                                           // Sustrae el recorrigo del codigo fuente y lo guarda en v_Indice
            }
        }
        
        /*********************  Delimitadores  ********************************/
        if(!"".equals(v_codFuente)&&v_Indice==0){
            Delimitadores v_Delimitadores=new Delimitadores();                  // Llama a la clase Delimitadores para detectar delimitadores en el código fuente
            v_Recorrido = v_Delimitadores.getDelimitadores(v_codFuente);        // Manda llamar el método getDelimitadores para regresar el número de carácteres que componen al delimitador
            if(0!=v_Recorrido){                                                 // Compara si recorrido del codigo fuente es diferente de 0
                m_addToken(v_codFuente.substring(0,v_Recorrido),1,a_Linea,v_Delimitadores.m_getID());             // Si el recorrido es diferente de 0 encontro un delimitador y añade el token a la tabla de simbolos
                a_codFuenteHTML+="<font color=#000000>"+v_codFuente.substring(0,v_Recorrido)+"</font>";
                v_Inserta=true;                                                 // Cambia la bandera de control de insercion a cierto
                v_Indice=v_Recorrido;                                           // Sustrae el recorrigo del codigo fuente y lo guarda en v_Indice
            }        
        }      
        
        /********************  Verificacion de Inserción   ********************/        
        if(v_Inserta){                                                          // Verifica que se haya insertado un lexema
            if(!"".equals(v_codFuente)){                                        // Verifica que el código fuente se haya terminado
                if(v_Indice!=v_codFuente.length())                              // Verifica que la palabra a analizar se haya terminando
                    v_codFuente=v_codFuente.substring(v_Indice);// Si no, recorta el codigo fuente a analizar
            }            
        }
        else{                                                                   // Si no se inserto un lexema
            if(!"".equals(v_codFuente)){
                if(v_Indice==0){
                    v_Indice=m_errorSimbolo(v_codFuente,0);
                    a_consola+="Error [140] Lex: Lexema imposible de clasificar: ' "+v_codFuente.charAt(v_Indice)+" '\n";
                    a_codFuenteHTML+="<font color=#000000>"+v_codFuente.charAt(v_Indice)+"</font>";
                    a_consola+="Error en la linea: "+a_Linea+"\n";
                    a_bdLexico=false;
                    if(v_Indice>0){
                        v_Indice=m_errorLexema(v_codFuente,0);                  // Si el recorrido es menor de 0 indica un error en los tipos de datos cadena
                        a_consola+="Error [140] Lex: Lexema imposible de clasificar: ' "+v_codFuente.substring(0,v_Indice)+" '\n";
                        a_consola+="Error en la linea: "+a_Linea+"\n";
                        a_bdLexico=false;
                    }else{
                        v_codFuente=v_codFuente.substring(1,v_codFuente.length());
                    }
                }
            }
        }
        return v_codFuente;
    }
    
    private boolean m_buscaToken(String p_Palabra){
        boolean v_Bandera=false;
        for(int v_indice=0;v_indice<a_TablaDeSimbolos.size();v_indice++){
            Token v_Temporal=a_TablaDeSimbolos.get(v_indice);
            if(v_Temporal.m_getLexema().equals(p_Palabra))
                v_Bandera=true;
        }
        return v_Bandera;
    }
    
    private void m_addToken(String p_Palabra,int p_Tipo,int p_noLinea,int p_idToken){
        if(!m_buscaToken(p_Palabra)){
            int v_noToken = a_TablaDeSimbolos.size()+1;
            Token v_newSimbolo = new Token(v_noToken,p_Palabra,p_Tipo,p_noLinea,p_idToken);
            a_TablaDeSimbolos.add(v_newSimbolo);
        }
        int v_noToken = a_TablaLexico.size()+1;
        Token v_newSimbolo = new Token(v_noToken,p_Palabra,p_Tipo,p_noLinea,p_idToken);
        a_TablaLexico.add(v_newSimbolo);
    }
    
    private int m_errorLexema(String p_codFuente,int p_Index){
        String v_codFuente=p_codFuente;
        int v_Index=p_Index;
        boolean v_bandera=true;
        while(!"".equals(v_codFuente)&&v_bandera){                                    
            if(p_codFuente.charAt(0)!=';'){
                if(p_codFuente.charAt(0)!='('){
                    if(p_codFuente.charAt(0)!=')'){
                        if(p_codFuente.charAt(0)!=')'){
                            if(p_codFuente.charAt(0)!='+'){
                                if(p_codFuente.charAt(0)!='-'){
                                    if(p_codFuente.charAt(0)!='*'){
                                        if(p_codFuente.charAt(0)!='/'){
                                            if(p_codFuente.charAt(0)!='<'){
                                                if(p_codFuente.charAt(0)!='>'){
                                                    if(p_codFuente.charAt(0)!='='){
                                                        if(p_codFuente.charAt(0)!=' '){
                                                            if(p_codFuente.charAt(0)!=10){
                                                                v_Index=m_errorLexema(v_codFuente.substring(1,v_codFuente.length()),v_Index+1);
                                                                v_bandera=false;
                                                            }else{v_bandera=false;}
                                                        }else{v_bandera=false;}
                                                    }else{v_bandera=false;}
                                                }else{v_bandera=false;}
                                            }else{v_bandera=false;}
                                        }else{v_bandera=false;}
                                    }else{v_bandera=false;}
                                }else{v_bandera=false;}
                            }else{v_bandera=false;}
                        }else{v_bandera=false;}
                    }else{v_bandera=false;}
                }else{v_bandera=false;}
            }else{v_bandera=false;}
        }
        return v_Index;
    }
    
    private int m_errorSimbolo(String p_codFuente,int p_Recorrido){
        int v_Recorrido=p_Recorrido;
        if(!"".equals(p_codFuente)){
            if(p_codFuente.charAt(0)>=48&&p_codFuente.charAt(0)<=57||
               p_codFuente.charAt(0)>=65&&p_codFuente.charAt(0)<=90||
               p_codFuente.charAt(0)>=97&&p_codFuente.charAt(0)<=122)
                v_Recorrido=m_errorSimbolo(p_codFuente.substring(1),v_Recorrido+1);
            else{
                if(p_codFuente.charAt(0)!=';'){
                    if(p_codFuente.charAt(0)!='('){
                        if(p_codFuente.charAt(0)!=')'){
                            if(p_codFuente.charAt(0)!=')'){
                                if(p_codFuente.charAt(0)!='+'){
                                    if(p_codFuente.charAt(0)!='-'){
                                        if(p_codFuente.charAt(0)!='*'){
                                            if(p_codFuente.charAt(0)!='/'){
                                                if(p_codFuente.charAt(0)!='<'){
                                                    if(p_codFuente.charAt(0)!='>'){
                                                        if(p_codFuente.charAt(0)!='='){
                                                            if(p_codFuente.charAt(0)!=' '){
                                                                if(p_codFuente.charAt(0)!=10){
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return v_Recorrido;
    }
    
    public ArrayList<Token> m_getTablaLexico(){
        return a_TablaLexico;
    }
    
    public ArrayList<Token> m_getTablaDeSimbolos(){
        return a_TablaDeSimbolos;
    }
    
    public String m_getConsola(){
        return a_consola;
    }
    
    public boolean m_getLexico(){
        return a_bdLexico;
    }
    
    public String m_getCodFuenteHTML(){
        return a_codFuenteHTML;
    }
}
