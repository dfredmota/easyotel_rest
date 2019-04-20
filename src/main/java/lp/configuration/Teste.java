package lp.configuration;

import java.text.NumberFormat;
import java.util.Locale;

public class Teste {
	
	public static void main(String args[]){
		
		
//		    String numberString = "2.105,00";
//	        //using casting
//	        System.out.println(Formatacao.formataValor(numberString));        
//	        
//	        System.out.println(Formatacao.formataValorString(new BigDecimal("2105.00")));
	        
	        
	        Locale ptBR = new Locale("pt", "BR");
	        
	        NumberFormat numberFormat = 
	        		  NumberFormat.getNumberInstance(ptBR); //para números
	        		System.out.println(numberFormat.format(2.105));
	}

}
