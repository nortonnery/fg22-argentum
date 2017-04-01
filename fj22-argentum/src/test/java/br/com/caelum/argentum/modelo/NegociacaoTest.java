package br.com.caelum.argentum.modelo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;



@SuppressWarnings("deprecation")
public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		// se criar um negocio no dia 15
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH,15);
		Negociacao n = new Negociacao(10, 5, c);
		
		// ainda que eu tente mudar a data para 20
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		// ele continua no dia 15
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula(){
		new Negociacao(10,5,null);
			
	}
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia(){
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
		
	}
	@Test
	public void comHorariosDiferentesEhNoMesmodia() {
		// usando GregorioanCalendar(ano,mes, dia,hora,minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
		
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 11, 20, 15, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertFalse(negociacao.isMesmoDia(tarde));
		
	}
	@Test
	public void mesmodiaEMesMasAnoDiferentesNaoSaoDoMesmoDia() {
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 11, 20, 8, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertFalse(negociacao.isMesmoDia(tarde));
	}
  /*	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles(){
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5,100, hoje);
		Negociacao negociacao2 = new Negociacao(45.5,100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8,100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3,100, hoje);
		
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao5 = new Negociacao(48.8,100, hoje);
		Negociacao negociacao6 = new Negociacao(49.3,100, hoje);
		
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao7 = new Negociacao(51.8,100, hoje);
		Negociacao negociacao8 = new Negociacao(52.3,100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
				negociacao3, negociacao4,negociacao5, negociacao6,negociacao7,
				negociacao8);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles = fabrica .constroiCandles(negociacoes);
		
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(40.5, candles.get(0).getAbertura(),0.00001);
		Assert.assertEquals(42.3, candles.get(0).getFechamento(),0.00001);
		Assert.assertEquals(48.8, candles.get(1).getAbertura(),0.00001);
		Assert.assertEquals(49.3, candles.get(1).getFechamento(),0.00001);
		Assert.assertEquals(51.8, candles.get(2).getAbertura(),0.00001);
		Assert.assertEquals(52.3, candles.get(2).getFechamento(),0.00001);
		
		
				
	} */
}
