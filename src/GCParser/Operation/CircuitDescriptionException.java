package GCParser.Operation;

import java.lang.Exception;


public class CircuitDescriptionException extends Exception {
  public int line;
  public CircuitDescriptionException( String desc ){
    this(desc,0);
  }
  public CircuitDescriptionException( String desc, int linenum ){
    super(desc);
    line = linenum;
  }
  public String getMessage(){
    if( line == 0 )
      return super.getMessage();
    return super.getMessage() + " on line "+line;
  }
}