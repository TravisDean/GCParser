package GCParser.Operation;

import YaoGC.*;
import GCParser.OperationNameResolver;
import java.math.BigInteger;
import GCParser.*;
public class NegateOperation extends OpDirections {
  public final static String NAME = "negate";
  public NegateOperation(){
    super(NAME);
  }
  public State execute( State[] inputs ) throws Exception {
    State[] addarg = new State[2];
    addarg[0] = new State( BigInteger.ONE, inputs[0].getWidth() );
    addarg[1] = OperationNameResolver.executeFromName( NotOperation.NAME, inputs );
    return OperationNameResolver.executeFromName( AddOperation.NAME, addarg );
  }
  public int validate( Variable[] operands ) throws CircuitDescriptionException {
    if( operands.length != 1 )
      throw new CircuitDescriptionException( getOp_name() + " must have one operand" );
    return operands[0].validate();
  }
}