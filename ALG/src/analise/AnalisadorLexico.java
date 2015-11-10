package analise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AnalisadorLexico {
	public static String path = "src/analise/Teste.txt";
	 static long ponteiroArquivo=0;
	public  static int contadorToken;
	 static int estado=0;
	 static int c=0;
	static RandomAccessFile arquivo=null;
	static int tabelaDeTransicao[][] =
		// 	L	D	{	}	"	<	>	=	-	+	*	/	(	)	;	EOF	E	pt	out	sp	\n	\t	underL	
		{{	7,	1,	10,	-2,	8,	12,	13,	17,	20,	19,	21,	22,	23,	24,	25,	26,	-2,	-2,	-2,	0,	0,	0,	-2	},//0
		{	-2,	1,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	27,	4,	2,	27,	27,	27,	27,	-2	},//1 ok
		{	-2,	3,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//2
		{	-2,	3,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	28,	4,	-2,	28,	28,	28,	28,	-2	},//3 ok
		{	-2,	6,	-2,	-2,	-2,	-2,	-2,	-2,	5,	5,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//4
		{	-2,	6,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//5
		{	-2,	6,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	29,	-2,	-2,	29,	29,	29,	29,	-2	},//6 ok
		{	7,	7,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	31,	-2,	-2,	31,	31,	31,	31,	7	},//7 ok
		{	8,	8,	8,	8,	9,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8	},//8
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	2,	-2,	-2,	-2	},//9
		{	10,	10,	10,	11,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10	},//10
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//11
		{	-2,	-2,	-2,	-2,	-2,	-2,	16,	15,	18,	-2,	-2,	-2,	-2,	-2,	-2,	30,	-2,	-2,	30,	30,	30,	30,	-2	},//12 ok
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	14,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	30,	-2,	-2,	30,	30,	30,	30,	-2	},//13 ok
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	30,	-2,	-2,	30,	30,	30,	30,	-2	},//14 ok
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	30,	-2,	-2,	30,	30,	30,	30,	-2	},//15 ok
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	30,	-2,	-2,	30,	30,	30,	30,	-2	},//16 ok
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	30,	-2,	-2,	30,	30,	30,	30,	-2	},//17 ok
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	18,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//18 
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	32,	-2,	-2,	-2,	32,	32,	32,	-2	},//19 OPR
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	32,	-2,	-2,	-2,	32,	32,	32,	-2	},//20 OPR
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	32,	-2,	-2,	-2,	32,32,	32,	-2	},//21 OPR
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	32,	-2,	-2,	-2,	32,	32,	32,	-2	},//22 OPR
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//23
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//24
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//25
		{	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2	},//26
		{	-2,	-2,	-2,	-2,	-2,	-2,-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2, -2,	-2	},//27
		{	-2,	-2,	-2,	-2,	-2,	-2,-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2, -2,	-2	},//28
		{	-2,	-2,	-2,	-2,	-2,	-2,-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2, -2,	-2	},//29
		{	-2,	-2,	-2,	-2,	-2,	-2,-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2, -2,	-2	},//30
		{	-2,	-2,	-2,	-2,	-2,	-2,-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2, -2,	-2	},//31
		{	-2,	-2,	-2,	-2,	-2,	-2,-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2,	-2, -2,	-2	}//32
		};

	public Token lexico(String path) {	
		 estado=0;
		 try {
			arquivo=new RandomAccessFile(new File(path), "r");
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo Não encontrado");
		}
		try {
			arquivo.seek(ponteiroArquivo);
			c = arquivo.read();
			while(tabelaDeTransicao[estado][mapaCaracter(c).valor] != -2){
				estado = tabelaDeTransicao[estado][mapaCaracter(c).valor];
				c=arquivo.read();
				ponteiroArquivo++;
				if(c==-1)
					return mapaDeEstado(estado);
			}
		} catch (IOException e) {
			System.out.println("ERRO NA LEITURA DO ARQUIVO");
		}
		contadorToken++;
		return mapaDeEstado(estado);
	}
/* Mapea os estados */
	public Token mapaDeEstado(int estado) {
		
		switch (estado) {
		case 27:// Num ( inteiro) estado final 27
			return Token.NUM;
		case 28: // Num (real) estado final 28
			return Token.NUM;
		case 29:// Num (com notação cientifica real ou inteiro) estado final 29
			return Token.NUM;
		case 31:  // id estado final 31
			return Token.ID;
		case 9: // Literal
			return Token.LITERAL;
		case 11: // Comentário
			return Token.COMENTARIO;
		case 30: // Operador relacional – OPR (<)
			return Token.OPR;
		case 18: // Atribuição – RCB (<-)
			return Token.RCB;
		case 32: // Operadores aritméticos – OPM (+)
			return Token.OPM;
//		case 20: // Operadores aritméticos – OPM (-)
//			return Token.OPM;
//		case 21: // Operadores aritméticos – OPM (*)
//			return Token.OPM;
//		case 22: // Operadores aritméticos – OPM (/)
//			return Token.OPM;
		case 23: // Abre Parentes – AB_P (
			return Token.AB_P;
		case 24: // Fecha Parentes – FC_P )
			return Token.FC_P;
		case 25: // Ponto e Virgula – PT_V (;)
			return Token.PT_V;
		case 26: // Fim de Arquivo - EOF
			return Token.EOF;
		default:
			return Token.ERRO;
		}
	}

	public static Coluna mapaCaracter(int c)  {
		if (c == 69)
			return Coluna.E;
		if (c > 47 && c < 58)
			return Coluna.D;
		if (c > 64 && c < 91 || c > 96 && c < 123)
			return Coluna.L;
		if (c == 32){
			return Coluna.ESPACO;
		}
		if (c == 10)
			return Coluna.BARRA_N;
		if (c == 9)
			return Coluna.BARRA_T;
		if (c == 59)
			return Coluna.PT_V;
		if (c == 40)
			return Coluna.AB_P;
		if (c == 41)
			return Coluna.FC_P;
		if (c == 123)
			return Coluna.AB_CHAVE;
		if (c == 125)
			return Coluna.FC_CHAVE;
		if (c == 34)
			return Coluna.ASPAS;
		if (c == 61)
			return Coluna.IGUAL;
		if (c == -1)
			return Coluna.EOF;
		if (c == 47)
			return Coluna.BARRA;
		if (c == 42)
			return Coluna.VEZES;
		if (c == 43)
			return Coluna.MAIS;
		if (c == 45)
			return Coluna.MENOS;
		if (c == 62)
			return Coluna.MAIOR;
		if (c == 60)
			return Coluna.MENOR;
		if (c == 46)
			return Coluna.PONTO;
		if (c == 95)
			return Coluna.UNDER_LINE;
		return Coluna.OUTRO;
	}
	/*Metodo Principal **/
	public static void main(String args[]) {
		AnalisadorLexico an = new AnalisadorLexico();
		Token token=null;
		int n=0;
		do{
			 token = an.lexico(path);
			System.out.println(token);
			n++;
		}while(n<10);
		

	}
}
