package CalcApp.CalcPackage;


/**
* CalcApp/CalcPackage/DivisionByZero.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calc.idl
* lunes 20 de marzo de 2023 22H51' CET
*/

public final class DivisionByZero extends org.omg.CORBA.UserException
{

  public DivisionByZero ()
  {
    super(DivisionByZeroHelper.id());
  } // ctor


  public DivisionByZero (String $reason)
  {
    super(DivisionByZeroHelper.id() + "  " + $reason);
  } // ctor

} // class DivisionByZero
