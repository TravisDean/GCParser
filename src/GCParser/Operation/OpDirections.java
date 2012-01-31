package GCParser.Operation;

import YaoGC.*;
import GCParser.OperationNameResolver;
import GCParser.*;

public abstract class OpDirections {

  private String op_name;
  private Variable currentVar;
  public OpDirections(String name){
    op_name = name;
    OperationNameResolver.registerOp(op_name,this);
  }
  public String getOp_name(){
    return op_name;
  }
  public abstract State execute(State[] operands) throws Exception;

  // returns the number of bits this operation will output, or throws an exception
  public abstract int validate( Variable[] operands ) throws CircuitDescriptionException;
  public int validate( Variable[] operands, Variable caller ) throws CircuitDescriptionException {
    currentVar = caller;
    return validate( operands );
  }
  public CircuitDescriptionException createException(String mess){
    return new CircuitDescriptionException( mess, currentVar.getLineNum() );
  }

  // convenience methods
  protected void binaryOperation( Variable[] operands ) throws CircuitDescriptionException{
    if( operands.length != 2 )
      throw createException( getOp_name()+" operation not given 2 operands");
    if( operands[0].validate() != operands[1].validate() )
      throw createException( getOp_name()+" operands must have the same bit length" );
  }
  protected State binaryOperation( Circuit c, State[] operands ) throws Exception {
    c.build();
    State in = State.fromConcatenation(operands[0], operands[1]);
    return c.startExecuting( in );
  }
  protected static State fromMapping( State total, int[] mapping ) throws Exception {
    assert mapping.length == total.getWidth() : "Mapping length not equal to state length";
    State res = State.extractState( total, mapping[0], mapping[0]+1 );
    for( int i = 1; i < mapping.length; i++ ){
      State bit = State.extractState( total, mapping[i], mapping[i]+1 );
      res = State.fromConcatenation( res, bit );
    }
    return res;
  }
}