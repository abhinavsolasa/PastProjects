package io;

import java.awt.*;

public class Complex {
	private final double re;
	private final double im;
	public Complex(double real, double imag)
	{
		re=real;
		im=imag;
	}
	public String toString() {
		if (im==0)
			return re+"";
		if(re==0)
			return im+"i";
		if(im<0)
			return re+"-"+(-im)+"i";
		else 
			return re+"+"+im+"i";
	}
	public Complex plus(Complex b)
	{
		Complex a=this;
		double real;
		double imag;
		real=a.re+b.re;
		imag=a.im+b.im;
		return new Complex(real,imag);
	}
	public Complex minus(Complex b)
	{
		double real;
		double imag;
		Complex a =this;
		real=a.re-b.re;
		imag=a.im-b.im;
		return new Complex(real, imag);
		
	}
	public Complex conjugate()
	{
		double real;
		real=this.re;
		double imag;
		imag=this.im*-1;
		return new Complex(real, imag);
	}
	public Complex times(Complex b)
	{
		Complex a = this;
		double real;
		double imag;
		imag=(b.im*a.re)+(a.im*b.re);
		real=(b.re*a.re)+(a.im*b.im*-1);
		return new Complex(real, imag);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Complex a=new Complex(5.0,6.0);
		Complex b=new Complex(-3.0, 4.0);
		System.out.println(a.times(b));
	}

}
