package lp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Formatacao {
	
	
	public static BigDecimal formataValor(String valorJson){
		
		BigDecimal bd = null;
		
		Locale ptBR = new Locale("pt", "BR");
		
		try {
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(ptBR);
            df.setParseBigDecimal(true);
            bd = (BigDecimal) df.parseObject(valorJson);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		return bd;
		
	}
	
	public static String formataValorString(BigDecimal valorReal){
		     
        Locale ptBR = new Locale("pt", "BR");
        
        NumberFormat numberFormat = 
        		  NumberFormat.getNumberInstance(ptBR); //para números
		
		return numberFormat.format(valorReal);
		
	}

}
