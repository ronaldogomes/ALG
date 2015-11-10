package analise;

public enum Coluna {
	L(0), D(1), AB_CHAVE(2), FC_CHAVE(3), ASPAS(4), 
	MENOR(5), MAIOR(6), IGUAL(7), MENOS(8), MAIS(9), VEZES(10), 
	BARRA(11), AB_P(12), FC_P(13), PT_V(14), EOF(15), E(16), PONTO(17),
	OUTRO(18), ESPACO(19), BARRA_N(20), BARRA_T(21), UNDER_LINE(22);
	 final int valor;
	Coluna(int valor){
		this.valor=valor;
	};

}