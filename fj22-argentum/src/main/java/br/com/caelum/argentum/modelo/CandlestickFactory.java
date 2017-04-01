package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public  class CandlestickFactory {
	// ctrl + 1 para adicionar o return automaticamente
	public Candlestick constroiCandleParaData(Calendar data,
								List<Negociacao> negociacoes){
	
		//double maximo = negociacoes.get(0).getPreco();
		//double minimo = negociacoes.get(0).getPreco();
		double maximo = 0;
		double minimo = Double.MAX_VALUE;
		double volume = 0;
		
		// digite foreach e de um ctrl + espaco para ajudar a
		// criar o bloco abaixo!
		for (Negociacao negociacao : negociacoes){
			volume += negociacao.getVolume();
			
			if (negociacao.getPreco() > maximo){
				maximo = negociacao.getPreco();
			} else if (negociacao.getPreco() < minimo){
				minimo = negociacao.getPreco();
			}
		}
		
		//double abertura = negociacoes.get(0).getPreco();
		//double fechamento = negociacoes.get(negociacoes.size()-1).getPreco();
		
		double abertura = negociacoes.isEmpty() ? 0 :negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 :
							negociacoes.get(negociacoes.size() - 1).getPreco();
		
		return new Candlestick(abertura, fechamento, minimo, maximo,
								volume, data);
		
		
	}

	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for (Negociacao negociacao : todasNegociacoes){
			// se nao for mesmo dia, fecha candle e reinicia variaveis
			if (!negociacao.isMesmoDia(dataAtual)){
				Candlestick candleDoDia = constroiCandleParaData(dataAtual,
						negociacoesDoDia);
				candles.add(candleDoDia);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);			
		}
		// adiciona ultimo candle
		Candlestick candleDoDia = constroiCandleParaData(dataAtual,negociacoesDoDia);
		candles.add(candleDoDia);
		return candles;
	}
}
