package practice;

import practice.CsvParser.ParseError;

public class CsvParser {
	static enum State {
		FIELD_START,
		REG_FIELD,
		OUTTER_QUOTE_START,
		IN_QOUTE,
		INNER_QUOTE_START,
		QUOTE_IN_QUOTE,
		INNER_QUOTE_END;
	}
	
	static class ParseError extends Exception {
		private static final long serialVersionUID = 1L;
		public ParseError(String msg) {
			super(msg);
		}
	}
	
	public static String parseCsv(String line) throws ParseError {
		StringBuilder builder = new StringBuilder();
		State state = State.FIELD_START;		
		
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			
			switch (state) {
				case FIELD_START:
					if (c == '"') {
						state = State.OUTTER_QUOTE_START;
					} else if (c == ',') {
						// this is an empty field
						builder.append('|');
						state = State.FIELD_START;
					} else {
						builder.append(c);
						state = State.REG_FIELD;
					}
					break;
				case REG_FIELD:
					if (c == '"') {
						throw new ParseError("Inner quotes not allowed where there's no outter quotes");
					} else if (c == ',') {
						builder.append('|');
						state = State.FIELD_START;
					} else {
						builder.append(c);						
					}
					break;
				case OUTTER_QUOTE_START:
					if (c == '"') {
						state = State.INNER_QUOTE_START;
					} else {
						builder.append(c);
						state = State.IN_QOUTE;					
					}
					break;
				case IN_QOUTE:
					if (c == '"') {
						if (i < line.length() - 1) {
							char nextC = line.charAt(i + 1);
							if (nextC == ',') {
								builder.append('|');
								state = State.FIELD_START;	
								i++;
							} else if (nextC == '"') {
								state = State.INNER_QUOTE_START;
							} else {
								// go to REG_FIELD
								throw new ParseError("cannot have both quoted fields and regular fields in a record: " + line);
							}
						}
					} else {
						builder.append(c);
					}
					break;
				case INNER_QUOTE_START:
					if (c != '"') {
						throw new ParseError("must have \" after innerquote started: " + line);
					} else {
						builder.append(c);
						state = State.QUOTE_IN_QUOTE;
					}
					break;
				case QUOTE_IN_QUOTE:
					if (c == '"') {
						if (line.charAt(i - 1) == '"') {
							throw new ParseError("Too many inner quotes: " + line);
						} else {
							if (i < line.length() - 1 && line.charAt(i + 1) == '"') {
								builder.append(c);
								state = State.INNER_QUOTE_END;
								i++;
							} else {
								throw new ParseError("inner quotes don't match: " + line);
							}
						}
					} else {
//						System.out.println("QinQ appending: " + c);
						builder.append(c);						
					}
					break;
				case INNER_QUOTE_END:
					if (c == '"') {
						if (i < line.length() - 1) {
							if (line.charAt(i + 1) == ',') {
								builder.append('|');
								state = State.FIELD_START;	
								i++;
							} else {
								// go to REG_FIELD
								throw new ParseError("outter quote does not end properly." + line);
							}
						}
					} else {
						builder.append(c);
						state = State.IN_QOUTE;
					}
					break;	
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		try {
			String csv = ",aa,bb,\"cc,dd\",ee";
			System.out.println(csv + " ==> " + CsvParser.parseCsv(csv));
	//
			csv = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
			System.out.println(csv + " ==> " + CsvParser.parseCsv(csv));
//			
			 csv = "\"\"\"A\"\"\"";
			 System.out.println(csv + " ==> " + CsvParser.parseCsv(csv));			

			csv = "\"Alexandra \"\"Alex\"\" B\",Menendez,alex.menendez@gmail.com,Miami,1";
			 System.out.println(csv + " ==> " + CsvParser.parseCsv(csv));			

			 csv = "\"Alexandra \"\"Alex\"\" B\"";
			System.out.println(csv + " ==> " + CsvParser.parseCsv(csv));			
} catch(ParseError e) {
			System.out.println(e.getMessage());
		}
	}
}
