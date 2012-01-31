package GCParser.Operation;

import GCParser.*;
import YaoGC.*;

public class LtesOperation extends OpDirections {
  public final static String NAME = "ltes";
  // less than or equal to signed
  public LtesOperation(){
    super(NAME);
  }
  public State execute( State[] inputs ) throws Exception {
    State[] switched = new State[2];
    switched[0] = inputs[1];
    switched[1] = inputs[0];
    return binaryOperation( new GTS_2L_1( inputs[0].getWidth() ), switched );
  }
  public int validate( Variable[] operands ) throws CircuitDescriptionException {
    binaryOperation( operands );
    return 1;
  }
}