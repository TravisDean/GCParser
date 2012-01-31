package GCParser.Operation;

import GCParser.*;
import YaoGC.*;
import GCParser.OperationNameResolver;
public class EquOperation extends OpDirections {
  public final static String NAME = "equ";
  public EquOperation(){
    super(NAME);
  }
  public State execute( State[] inputs ) throws Exception {
    State[] nequ = new State[1];
    nequ[0] = OperationNameResolver.executeFromName(NequOperation.NAME, inputs);
    return OperationNameResolver.executeFromName(NotOperation.NAME, nequ);
  }
  public int validate( Variable[] operands ) throws CircuitDescriptionException {
    binaryOperation( operands );
    return 1;
  }
}