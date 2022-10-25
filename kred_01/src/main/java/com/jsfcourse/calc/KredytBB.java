package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private Double a ;//kwota
	private Long b;//lata
	private  Double c ;//rata
	private Double result;

	@Inject
	FacesContext ctx;

	public Double getA() {
		return a;
	}

	public void setA(Double a) {
		this.a = a;
	}

	public Long getB() {
		return b;
	}

	public void setB(Long b) {
		this.b = b;
	}
	public Double getC() {
		return c;
	}

	public void setC(Double c) {
		this.c = c;
	}


	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			int a = Integer.parseInt(this.a);
			int b = Integer.parseInt(this.b);
		 double c = Double.parseDouble(this.c);
			  
			b = b * 12;// bo mies
			
			c = c / 100;
			//wzor na kredyt
		
			result = (a * c) / (12 * (1 - (Math.pow((12 / (12 + c)), b))));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}


	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
