
import CalcApp.*;
import CalcApp.CalcPackage.DivisionByZero;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import java.util.Properties;
import java.lang.Math;

class CalcImpl extends CalcPOA {

    @Override
    public float sum(float a, float b) {
        return a + b;
    }

    @Override
    public float div(float a, float b) throws DivisionByZero {
        if (b == 0) {
            throw new CalcApp.CalcPackage.DivisionByZero();
        } else {
            return a / b;
        }
    }

    @Override
    public float mul(float a, float b) {
        return a * b;
    }

    @Override
    public float sub(float a, float b) {
        return a - b;
    }
    // metodo agregado por juanmata8
    @Override
    public double log(float a){
        return Math.log(a);
    };
    // metodo agregado por juanmata8
    @Override
	public double sqrt(float a){
        return Math.sqrt(a);
    };
    // metodo agregado por juanmata8
    @Override
	public String binary(float a) {
        int bits = Float.floatToIntBits(a);
        String result = String.format("%32s", Integer.toBinaryString(bits)).replace(" ", "0");;
        return result;
    };


    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }
}

public class CalcServer {

    public static void main(String args[]) {
        try {
            // creamos e inicializamos el servidor ORB
            ORB orb = ORB.init(args, null);

            // Obtenemos una referencia a la raíz POA y la activamos
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // creamos el servant y lo configuramos el ORB
            CalcImpl helloImpl = new CalcImpl();
            helloImpl.setORB(orb);

            // referenciamos el objeto del servant, la interfaz
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            Calc href = CalcHelper.narrow(ref);

            // obtenemos el contexto de la raiz
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Obtenemos una referencia al servicio de nombres
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolvemos el objeto referenciado en NamingContext
            String name = "Calc";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Ready..");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Exiting ...");

    }
}
